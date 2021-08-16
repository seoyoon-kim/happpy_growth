a = 1
b = 2

if a :
    print("a입니다")
else :
    print("b입니다")

if a<b :
    print("b가 더 큽니다")

if 1 not in [1,2,3]:
    print("1 없습니다")
elif 2 in [1,2,3]:
    pass
else : 
    print("1 있습니다!")

score = 70
message = "success" if score >= 60 else "failure"
print(message)
 
result = [num*3 for num in a if num%2 == 0]
result=[]
for num in a:
    if num%2 == 0:
     result.append(num*3)

#이중 for문
result = [x*y for x in range(2,10) for y in range(1,10)]
result =[]
for x in range(2,10):
    for y in range(1,10):
        result.appned(x*y)


#Immutable : 정수, 실수, 문자열, 튜플!
#Muttable : 리스트, 딕셔너리, 집합




#