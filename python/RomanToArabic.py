## Code kata for Pillar Technologies
## Clinton Sexton
from collections import OrderedDict

def ArabicToRoman(num):
    retStr = ""

    roman = {1000:'M', 900:'CM', 500:'D', 400:'CD', 100:'C', 90:'XC', 50:'L', 40:'XL', 10:'X', 9:'IX', 5:'V', 4:'IV', 1:'I'}
    roman2 = OrderedDict(sorted(roman.items(), key = lambda t: t[0], reverse = True))
    places = [0,] * len(roman2.keys())
    print roman2
    print num
    print places
    for i in roman.keys():
        cnt = num/i
        print cnt
        if cnt:
            num -= cnt * i
    if roman2.has_key(num):
        retStr+= roman2[num]
        print retSt
    else:
        print "ERROR NOT FOUND"


def RomanToArabic(roNum):
    pass


def main():

    ArabicToRoman(1)

if __name__ == '__main__':
    main()

    # convert arabic number to roman numeral
