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
 
 
