## Code kata for Pillar Technologies
## Clinton Sexton
from collections import OrderedDict

def ArabicToRoman(num):
    retStr = ""

    roman = {1000:'M', 900:'CM', 500:'D', 400:'CD', 100:'C', 90:'XC', 50:'L', 40:'XL', 10:'X', 9:'IX', 5:'V', 4:'IV', 1:'I'}
    roman2 = OrderedDict(sorted(roman.items(), key = lambda t: t[0], reverse = True))
    if num > 4000:
        print "Must be less than 4000"
        return 
    for key in roman2.keys():
        places = num / key 
        z = roman2[key] * places
        num -= (key*places)
        retStr+= z
    print retStr
def RomanToArabic(roNum):
    aNum = 0
    roman = {1000:'M', 900:'CM', 500:'D', 400:'CD', 100:'C', 90:'XC', 50:'L', 40:'XL', 10:'X', 9:'IX', 5:'V', 4:'IV', 1:'I'}
    roman2 = OrderedDict(sorted(roman.items(), key = lambda t: t[0], reverse = True))
    roman2inv = {v: k for k, v in roman2.iteritems()}
    def value(s):
        return roman2inv[s]
    res = 0
    # print roman2inv
    l=[]
    for c in roNum: l.append(c)

    for x in range(0,len(roNum)):
        # print x
        # print l[x]
        # print len(l)
         

        if len(l) > x+1:
            print value (l[x+1])
            if value(l[x]) < value(l[x+1]):
                res += value(l[x+1])-value(l[x])
        #     print res
        
            # print value(l[x])
        # print x
        res += value(l[x])

    print res

def main():
    #ArabicToRoman(2000)
    RomanToArabic('XX')

if __name__ == '__main__':
    main()

    # convert arabic number to roman numeral
