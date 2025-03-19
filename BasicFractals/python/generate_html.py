#!/usr/local/bin/python3

import sys
import math

def generate_webpage(image_files, images_per_tab=10):
	num_tabs = math.ceil(len(image_files) / images_per_tab)

	# Build tab buttons with a `data-index` attribute to help with styling the active tab
	tab_buttons = "\n\t\t".join(
		f"<button class=\"tab-button\" data-index=\"{ii}\" onclick=\"showTab({ii})\">Page {ii+1}</button>"
		for ii in range(num_tabs)
	)

	# Build tab content divs
	tab_contents = ""
	for ii in range(num_tabs):
		start = ii * images_per_tab
		end = start + images_per_tab
		chunk = image_files[start:end]
		images_html = "\n\t\t".join(
			f"<img src=\"{filename}\" alt=\"{filename}\">"
			for filename in chunk
		)
		tab_contents += f"""
	<div class="tab-content" id="tab-{ii}">
		{images_html}
	</div>"""

	html_content = f"""<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Fractals</title>
	<style>
		body {{
			font-family: Arial, sans-serif;
			padding: 20px;
			background: #f0f0f0;
		}}
		.tabs {{
			margin-bottom: 20px;
			text-align: center;
		}}
		.tab-button {{
			padding: 10px 20px;
			margin: 0 5px;
			cursor: pointer;
			border: none;
			background-color: #4285f4;
			color: white;
			border-radius: 5px;
			font-size: 16px;
			transition: background-color 0.2s;
		}}
		.tab-button:hover {{
			background-color: #3367d6;
		}}
		.tab-button.active {{
			background-color: #34a853;
		}}
		.tab-content {{
			display: none;
			flex-wrap: wrap;
			justify-content: center;
			gap: 20px;
		}}
		img {{
			max-width: 500px;
			border: 2px solid #ccc;
			border-radius: 10px;
			box-shadow: 2px 2px 8px rgba(0,0,0,0.1);
			transition: transform 0.2s;
		}}
		img:hover {{
			transform: scale(1.05);
		}}
	</style>
</head>
<body>
	<div class="tabs">
		{tab_buttons}
	</div>
	{tab_contents}
	<script>
		function showTab(index) {{
			const tabs = document.querySelectorAll('.tab-content');
			const buttons = document.querySelectorAll('.tab-button');
			tabs.forEach((tab, i) => {{
				tab.style.display = (i === index) ? 'flex' : 'none';
			}});
			buttons.forEach((btn, i) => {{
				if (i === index) {{
					btn.classList.add('active');
				}} else {{
					btn.classList.remove('active');
				}}
			}});
		}}
		window.onload = () => showTab(0);
	</script>
</body>
</html>
"""

	filename = "index.html"
	with open(filename, "w") as fp:
		fp.write(html_content)
	print(filename)

def main():
	image_files = sys.argv[1:]
	if image_files:
		generate_webpage(image_files, images_per_tab=12)
	else:
		print("Usage: python generate_gallery.py file1.gif file2.gif file3.gif ...")

if __name__ == "__main__":
	main()

