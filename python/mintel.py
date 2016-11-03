def main():
	a = [54,26,93,17,77,31,44,55,20]
	insertionSort(a)
	insertSort(a)




def insertSort(intArray):
	
	for i in range(1,len(intArray)):
		curval = intArray[i]
		pos = i
	
		while pos > 0 and intArray[pos-1] > curval:
			intArray[pos] = intArray[pos-1]
			pos = pos - 1

		intArray[pos] = curval
	print intArray

a = .25
b = 0
c = 2
while a <12:
	b += 1
	a = (a+a) * c
	print a
	print b,' = b'