from PIL import Image
from collections import Counter

def get_most_common_colors(image_path, top_n=12):
    # Load image
    img = Image.open(image_path).convert('RGB')
    
    # Resize (optional, for speed)
    img = img.resize((img.width // 2, img.height // 2))

    # Get all pixels
    pixels = list(img.getdata())

    # Count most common colors
    color_counts = Counter(pixels)
    most_common = color_counts.most_common(top_n)

    return most_common

# Example usage:
image_path = 'Background.png'
common_colors = get_most_common_colors(image_path)

# Print result
for i, (color, count) in enumerate(common_colors):
    #print(f"{i+1}. Color: {color}, Count: {count}")
    print(f"{color}")
