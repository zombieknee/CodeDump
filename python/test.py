import re
text = """
hello      hello   
goodbye
bye   bye bye
hello goodbye
""" 
def main():
	text /^(?P<x>\S*)(\s+(?P=x))+\s*$/ = ""

	pat = re.compile (r"$.*\s*\0*^", re.MULTILINE)
	result = pat.sub("", text)
	print result
if __name__ == '__main__':
	main()