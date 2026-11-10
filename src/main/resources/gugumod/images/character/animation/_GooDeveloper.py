from PIL import Image
import numpy as np
from scipy.ndimage import uniform_filter


def euclidean_distance(a, b):
    return np.linalg.norm(a - b, axis=-1)

def replace_colors_fast(image_path, original_colors, target_colors, output_path='output.png'):
    # Load with alpha preserved
    img = Image.open(image_path).convert('RGBA')
    pixels = np.array(img).astype(float)

    alpha = pixels[:, :, 3:]  # Keep alpha separate
    rgb_pixels = pixels[:, :, :3]

    # Convert colors to numpy arrays
    original_colors = np.array(original_colors, dtype=float)
    target_colors = np.array(target_colors, dtype=float)

    # Calculate distances to each of the original colors (shape: H x W x N)
    diffs = rgb_pixels[:, :, None, :] - original_colors[None, None, :, :]
    diffs = diffs ** 2 # This determines smoothness
    distances = np.linalg.norm(diffs, axis=-1) + 1e-6  # Avoid division by zero

    # Inverse distance weighting
    inv_dist = 1 / distances

    # Neighborhood smoothing (box blur)
    blur_size = 2
    smoothed_weights = np.stack([
        uniform_filter(inv_dist[:, :, i], size=blur_size, mode='nearest')
        for i in range(inv_dist.shape[-1])
    ], axis=-1)

    inv_dist = smoothed_weights #Try blur
    weights = inv_dist / np.sum(inv_dist, axis=-1, keepdims=True)

    # Blend base original color
    blended_base = np.sum(original_colors[None, None, :, :] * weights[..., None], axis=2)
    offset = rgb_pixels - blended_base

    # Blend target color
    blended_target = np.sum(target_colors[None, None, :, :] * weights[..., None], axis=2)
    new_rgb = np.clip(blended_target + offset, 0, 255)

    # Recombine with alpha
    result_pixels = np.concatenate([new_rgb, alpha], axis=-1).astype(np.uint8)
    out_img = Image.fromarray(result_pixels, mode='RGBA')
    out_img.save(output_path)
    print(f"Saved recolored image to {output_path}")



original_colors = [
(0, 0, 0),
(187, 227, 165), #Hair
(144, 181, 156), #back hair
(170, 225, 228), #teal hair bit
(5, 31, 94), #outline
#(218, 239, 206), #eyes

(251, 255, 236), #shirt
(244, 223, 138), #overalls
(231, 120, 125), #mouth
(255, 251, 228), #Skin
]

target_colors = [
(0, 0, 0),
(254, 156, 187), #Hair
(253, 116, 158), #back hair
(255, 221, 132), #teal hair bit
(191, 110, 87), #outline
#(79, 224, 230),     # eyes

(251, 255, 236), #shirt
(244, 223, 138), #overalls
(231, 120, 125), #mouth
(255, 251, 228), #Skin
]

imageName = 'Background'
replace_colors_fast(imageName + '.png', original_colors, target_colors, output_path=imageName + '_recolored.png')