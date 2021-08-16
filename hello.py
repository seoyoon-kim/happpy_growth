print("git hub test") 

a = 1
b = 4

#타입 확인하기
print(type(a))
#<class 'int'>

print(type(a/b))

#제곱
print(type(a**b))


c = "Hello python"
print(c)
print(type(c))
#<class 'str'>

d = "This is \"python\""
print(d)

e = """ Life is short
you need Python"""
print(e)
print(e.split()) #문자열 쪼개기
print(e.replace("is","isn't"))

f = "summer"
g = "is gone"
print(f+g)
print(f*100)

print(f[0]) # 맨 앞
print(f[-1]) # 맨 뒤
print(f[0:5:2]) # 이상 : 미만 : 간격 sme 출력 ->slicing
print(f[:3]) #처음부터 3미만까지
print(f[::2])

print(f.count('s')) # 문자의 숫자 세기
print(f.find('e')) # 문자의 위치 찾기
print(f.find('x')) # 없으면 -1 출력

print("join: "+f.join(' 1234')) # 문자열 삽입 join:  summer1summer2summer3summer4 -> 뒤에 있는 문자열을 쪼개서 앞에 있는 문자열을 삽입
print(f.upper()) # 대문자로 바꾸기
print(f.join(["a", "b", "c"]))

number = 10
day = "three"
g = "I ate %d apples. So I was happy for %s days"%(number, day)
print(g)

h = "age: {age} name: {name}".format(name="bboya", age="5")
print(h)

i = "%10s" % "hi"
print(i) # %1s : 띄어쓰기


j = "%-10sjane." % 'hi' 
print(j)

k = "%0.4f" % 3.123456789
print(k)

#배열
l = [1,2,"int",["a","b","c"], 1,2]
print(l[3][1])
print(l[0:2])

l[0:2] = [] #배열 삭제하기 
del l[3] 
l.append("append") #배열에 추가하기
print(l)


m = [3,4,5]
print(l+m) #배열 합치기
m.insert(0,4) #0번째 인덱스에 4를 추가
print(m) 
m.remove(5) #요소 삭제
print(m)
print(m.pop()) #마지막 요소 꺼내오기
print(m.count(3))

n = [1,2,3,4,5]
n.sort() # 오름차순 정렬
print(n)
n.reverse() # 내림차순 정렬
print(n)
n.extend([6,7,8])
print(n)
 
#튜플은 변경 불가 및 길이가 고정 
o = (1,2,3,'a','b','c')

#딕셔너리->JSON 형태로 많이 쓰인다
p = {'name':'dictionary', 'age':'25', 'birth':'0101'}
p['phone']="123456"
print(p) #새로운 키와 밸류 할당
del p["birth"] #키와 밸류 삭제 
print(p)
print(p.keys()) #key만 보기
print(p.values()) #value만 보기
for k in p.keys(): #반복문으로 key 보기
    print(k)

print(p.get(10)) #none 출력
print(p.get(10,'없음')) #없음 출력
print( 4 in p) # 값이 있는지 검색해 true or false로 출력 

p.clear() #모두 지우기

#set 집합
q = set([1,2,3]) # or set{1,2,3}
print(q)

r = {2,3,4,5,6,7}

print(q&r) #교집합 추출
print(q.intersection(r))#교집합 추출

print(q|r) #합집합
print(q.union(r))  #합집합

print(r-q) #차집합
print(r.difference(q)) #차집합

r.add(8) #요소 추가
print(r)

r.update([10,11,12]) #여러 요소 추가

r.remove(3) #요소 삭제

# 문자열 자료형은 True
# "" False
# [] False
# () False
# {} False
# 0 False
# None False

if False: #첫글자 대문자로 쓰기
    print(r)
while r ==3:
    r+=1

#변수 할당
s, t = ('study','try')
#위와 같음 (s, t) = 'study','try'
 
print(s)

u = v = 'okay'

w = 3
x = 5
w,x = x,w # 값을 서로 바꾸기

