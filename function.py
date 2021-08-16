def sum_many(*args): #*args는 몇 개든 상관없이 인자를 다 받겠다
    sum = 0
    for i in args:
        sum = sum + i
    return sum
print(sum_many(1,2,3))


def print_keywordargs(**kwargs): #**kwargs는 파라미터가 몇 개든 상관없이 다 받는다
    for k in kwargs.keys():
        if(k== "name"):
            print("당신의 이름은 :" + k)
print(print_keywordargs(a="홍길동", b="홍범도")) 

def sum_and_mul(a,b):
    return a+b, a*b, a-b #리턴 값이 튜플 형태로 3개가 묶여져서 나온다
print(sum_and_mul(1,2))

def sum_and_mul(a,b):
    return a+b, a*b, a-b #리턴 값이 여러 개 중 하나만 사용하고자 할 때
print(sum_and_mul(1,2)[0])

#초기값 미리 설정하기
def say_myself(name, old, woman=True): #기본값이 맨 끝에 위치해야 생략이 가능하다
    print("나의 이름은 %s 입니다." % name)
    print("나이는 %d살입니다." % old)
    if woman:
        print("여자입니다.")
    else:
        print("남자입니다.")
say_myself("유관순", 16)
say_myself(old=25, name="세종대왕")

a = 1
b = 1
def vartest(a):
    a = a + 1
    global b #global은 전역변수
    b = b + 1

    vartest(a)
    print(a)  # 위 함수는 리턴이 없고 지역변수이기에 출력은 1
    print(b)


#Lambda

add = lambda a, b: a+b
print(add(1,2))

myList = [lambda c,d: c+d, lambda c,d:c*d]
print(myList[0](1,2)) #함수 이름이 없어도 호출이 가능

#내장함수
number = input("숫자를 입력하세요:") # 입력받기
print(number)

print("Life""is""short") #Lifeisshort출력
print("Life","is","short") #콤마 넣으면 띄어쓰기가 된다

for i in range(10):
    print(i, end=" ") #end는 엔터 없이 옆에 계속 출력


#절대값
print(abs(-3))

#모두 참인지 검사
all([1,2,3]) #True
all([1,2,3,0]) #0 이 false이기에 False

#하나라도 참이 있는지 검사
any([0, ""]) #False

#아스키 코드 입력받아 문자를 출력
chr(97) #'a'
chr(48) #'0'

#list에서 사용가능한 명령어 모음
print(dir([1,2,3]))

#몫과 나머지를 튜플 형태로 리턴
divmod(7,3) #(2,1)을 리턴

#열거 enumerate
for i, name in enumerate(['body', 'foo', 'bar']):
    print(i, name)
    #0 body \n 1 foo \n 2 bar 가 출력

#eval : 실행 후 결과값을 돌려준다
eval('1+2')
eval("'hi' + 'a'")
eval('divmode(4,3)')

#filter : 함수를 통과해 참인 것만 리턴
def positive(x):
    return x > 0

a = list(filter(positive, [1,-3,2,0,-5,6]))
print(a)

a = list(filter(lambda x:x > 0, [1,-3,2,0,-5,6]))
print(a)

#id : 주소 값
id(3)
id(a)

#int : 10진수 정수 형태로 변환
int('3')
int(3.4)
int('11',2) #3
int('1A',16) #26

#len : 길이
len("python")

#list : 리스트로 반환
list("python")
#['p','y','t','h','o','n']

#map : 각 요소가 수행한 결과를 리턴
def two_times(x) : return x*2
a = list(map(two_times, [1,2,3,4]))
print(a)

a = list(map(lambda a: a*2, [1,2,3,4]))
print(a)

#max : 최대값
max("python") #'y'

#min : 최소값
min("python") # 'h'

#pow : 제곱한 결과 리턴
pow(2,4)

#range : 범위
list(range(0, -10, -1)) #0부터 -10전까지(=9까지) -1씩 더한다

#round : 반올림
round(4.6)
round(5.678, 2)

#sorted : 정렬
sorted([3,1,2])
sorted(["zero"])

#str : 문자열 반환
str(3) #'3'
str('hi'.upper()) #'HI'

#tuple : 튜플 변환
tuple("abc") # ('a','b','c')
tuple((1,2,3)) # (1,2,3)

#zip : 자료형을 묶어준다
list(zip[1,2,3], [4,5,6]) #[(1,4),(2,5),(3,6)]




#file
f = open("newFile.txt", 'w') #새 파일 생성하고 write한다/r : 읽기모드/a:파일 마지막에 새로운 내용 추가
f.close()

#파일을 쓰기 모드로 열어 출력값 적기
f = open("C:/python/newFile.txt", 'w', encoding="UTF-8")
for i in range(1,11):
    data = "%d번째 줄입니다.\n" % i
    f.write(data)
f.close()

#파일의 한 줄 읽기
f = open("C:/python/newFile.txt", 'r', encoding="UTF-8")
line = f.readline() #첫째줄 읽어오기
print(line)
f.close()

#파일의 통째로 읽기
f = open("C:/python/newFile.txt", 'r', encoding="UTF-8")
line = f.read() #통째로 읽어오기
print(line)
f.close()


#파일 내용 모두 읽기
f = open("C:/python/newFile.txt", 'r', encoding="UTF-8")
while True:
    line = f.readline()
    if not line:break
    print(line)
f.close()

#list 형태로 파일 내용 모두 읽기
f = open("C:/python/newFile.txt", 'r', encoding="UTF-8")
list = f.readlines()
for line in list:
    print(line, end="") #end="" : 두 번씩 엔터가 아닌 한번만 엔터를 입력한다
    #print(line.strip("\n"))
    #print(line.strip("\n"),end="") 엔터 다 없애고 한 줄로 출력
f.close()

#write는 파일 초기화해서 적기, append는 추가

#close없이 파일 함수 사용하기
with open("foo.txt", "w") as f : #파일을 새로 하나 만들어서 f에 저장한다 
    f.write("python file") #f는 지역변수라 close가 필요없다.



#외장함수

#sys : 라이브러리 함수, 인자들을 리스트로 받는다
import sys
print(sys.argv)

#pickle : list, dict과 같은 객체를 그대로 저장하고 불러온다
import pickle
p = open("test.txt", 'wb')
data = {1:'python', 2: 'good'}
pickle.dump(data, p) #dump : 저장
p.close()

#time : 시간 라이브러리
import time
print(time.time())
#1970년 1월 1일 0시 0분 0초를 기준으로 지난 시간 초를 리턴

#time.sleep : x초 쉰다
import time
for i in range(5):
    print(i)
    time.sleep(1)
#random : 0~1 사이의 난수
import random
print(random.random())
random.randint(1,10)
data = [1,2,3,4,5]
random.shuffle(data)
lotto = sorted(random.sample(range(1,46), 6))
print(lotto)

#webbrowser
import webbrowser
webbrowser.open("http://google.com")
webbrowser.open_new_tab("http://google.com")

