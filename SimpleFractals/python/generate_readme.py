#!/usr/local/bin/python3

import sys
import os
import math

def generate_readme(image_files, github_username, repo_name, branch, github_path, n_cols=4):
	n_rows = math.ceil(len(image_files) / n_cols)

	link_format = "<img src=\"https://raw.githubusercontent.com/{github_username}/{repo_name}/{branch}/{github_path}/{filename}\" width=\"200\">"
	
	lines = list()
	lines.append("<div align=\"center\">")
	lines.append("\t<table>")
	for row in range(n_rows):
		lines.append("\t\t<tr>")
		begin = row*n_cols
		end = begin + n_cols
		for filename in image_files[begin:end]:
			link = link_format.format(
				github_username=github_username,
				repo_name=repo_name,
				branch=branch,
				github_path=github_path,
				filename=os.path.basename(filename)
			)
			lines.append(f"\t\t\t<td>{link}</td>")
			
		lines.append("\t\t</tr>")
	lines.append("\t</table>")
	lines.append("</div>")
	readme_content = "\n".join(lines)

	filename = "Readme.md"
	with open(filename, "w") as fp:
		fp.write(readme_content)
	print(filename)

def main():
	github_username = "jagraves21"
	repo_name = "Fractals"
	branch = "main"
	github_path = "SimpleFractals/gif"
	image_files = sys.argv[1:]
	if image_files:
		generate_readme(image_files, github_username, repo_name, branch, github_path)
	else:
		print("Usage: python generate_readme.py file1 file2 file3 ...")

if __name__ == "__main__":
	main()

