def main():


	l = [3,2,4]
	t = 6
	end = l[-1]
	for a , b in enumerate(l):

		if b + end == t:
			print a, len(l)-1
	


if __name__ == '__main__':
	main()