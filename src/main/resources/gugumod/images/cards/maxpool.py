import os
from PIL import Image
import numpy as np

POOL_SIZE = 2

def max_pool_rgba(img: Image.Image, pool_size: int = 2) -> Image.Image:
    arr = np.array(img)

    if arr.shape[0] % pool_size != 0 or arr.shape[1] % pool_size != 0:
        pad_height = pool_size - arr.shape[0] % pool_size if arr.shape[0] % pool_size else 0
        pad_width = pool_size - arr.shape[1] % pool_size if arr.shape[1] % pool_size else 0
        arr = np.pad(arr, ((0, pad_height), (0, pad_width), (0, 0)), mode='constant', constant_values=0)

    h, w, c = arr.shape
    arr = arr.reshape(h // pool_size, pool_size, w // pool_size, pool_size, c)
    pooled = arr.max(axis=(1, 3))

    return Image.fromarray(pooled.astype(np.uint8), 'RGBA')

def process_images(base_path='.'):
    for root, _, files in os.walk(base_path):
        for file in files:
            if file.endswith('_p.png'):
                output_path = os.path.join(root, file.replace('_p.png', '.png'))
                if os.path.exists(output_path):
                    continue
                full_path = os.path.join(root, file)
                print(f"Processing {full_path}")
                try:
                    img = Image.open(full_path).convert('RGBA')
                    pooled = max_pool_rgba(img, POOL_SIZE)
                    pooled.save(output_path)
                except Exception as e:
                    print(f"Failed to process {full_path}: {e}")

if __name__ == "__main__":
    process_images()
