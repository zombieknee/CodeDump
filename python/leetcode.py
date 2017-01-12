def main():

	l = [-3,4,3,90]
	t = 0
	end = len(l)-1

	for a , b in enumerate(l):
		print b + l[end], "b =",b, "End =", end
		if b + l[end] == t and b != l[end]:
			
			print b, l[end]
			print a, end, "sucsess!"
		
	
	
	




if __name__ == '__main__':
	main()