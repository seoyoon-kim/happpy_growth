# 정규 표현식 : 문자열의 규칙을 찾아 수정할 때 사용하곤 한다

import re

#match
p = re.compile('[a-z]+') #a-z까지 한번 이상 반복되는지 확인하기 위해 패턴 객체에 저장

m = p.match("python") #검사하고자 하는 문자열을 입력해서 매치 객체에 저장
print(m)
#실행결과 <re.Match object; span=(0, 6), match='python'>

m = p.match("3 python")
print(m)
#실행결과 None ->매치가 안된다

# search
m = p.search('3 python')
print(m)
# 실행결과 <re.Match object; span=(2, 8), match='python'>

#findall
m = p.findall('python is easy')
print(m)
# 실행결과 ['python', 'is', 'easy'] -> 일치하는 string을 리스트에 담아 리턴

#finditer
m = p.finditer('python is easy')
for r in m :
    print(r)
# 실행결과 <re.Match object; span=(0, 6), match='python'>
# <re.Match object; span=(7, 9), match='is'>
# <re.Match object; span=(10, 14), match='easy'>
# 매치 객체로 리턴

m = p.match('python')
print(m.group()) # 매치된 문자열
print(m.start()) # 첫 인덱스
print(m.end()) #마지막 인덱스
print(m.span()) #시작과 끝을 튜플로 리턴



#컴파일 옵션

# DOTALL, S
p = re.compile('a.b') #줄바꿈(\n)을 제외한 모든 문자와 매치되는지 확인
m = p.match('a\nb')
print(m) # 실행결과 None

p = re.compile('a.b', re.DOTALL) #줄바꿈을 포함하는 옵션, DOTALL 대신 S를 사용해도 무방 
m = p.match('a\nb')
print(m)

# ignorecase, I
p = re.compile('[a-z]') #소문자만 해당
p = re.compile('[a-z]', re.I) #대소문자 구별 없음
print(p.match('python'))
print(p.match('Python'))
print(p.match('PYTHON'))

# multiline, M
p = re.compile("^python\s\w+", re.M) # ^: 맨 처음에 위치 \s : 공백 \w : 알파벳, 숫자, _ 중 하나
#re.M이 없었을 때는 python one만 리턴하지만, 멀티라인 이용시 python 1,2,3 을 리스트로 리턴
data = """python one
life is short
python two
you need python
python three"""

print(p.findall(data))


#verbose, X
charref = re.compile(r'&[#](0[0-7]+|[0-9]+|x[0-9a-fA-F]+);')
#정규표현식을 줄바꿈을 하게되면 컴파일이 안되는데, 
# verbose를 이용하면 공백과 주석을 제거하여 가독성이 높아진다

charref = re.compile(r"""
&[#]
(
     0[0-7]+
    | [0-9]+
    | x[0-9a-fA-F]+
)
;
""", re.VERBOSE)
 
 # \ 사용
 #\section' # ection이 된다
p = re.compile('\\section') # 파이선에서 \\는 \ 하나와 같다
p = re.compile('\\\\section') #\두개를 쓰려면 네 번 써줘야 한다.
p = re.compile(r'\\section') # raw string으로 r 다음에 문자열 자체가 인식된다

# 메타문자 |
p = re.compile('Crow|Servo') #앞 혹은 뒤와 일치할 때 
m = p.match('CrowHello')
print(m)
# <re.Match object; span=(0, 4), match='Crow'>

# 메타문자 ^ : 맨 처음에 위치하고 있는지 확인
print(re.search('^Life', 'Life is too short')) #컴파일할 정규 표현식, 찾을 문자열
print(re.search('^Life', 'My Life'))

# 메타문자 $ : 맨 끝에 위치하고 있는지 확인
print(re.search('short$', 'Life is too short'))
print(re.search('short$', 'Life is too short, you need python'))

print(re.search('^Life', 'Life is too short'))
print(re.search('^Life', 'My Life'))
 
 # 메타문자 \b : 공백을 나타내는 문자
p = re.compile(r'\bclass\b') # 공백+class+공백인지 확인
print(p.search('no class at all'))
print(p.search('the declassified algorithm'))
print(p.search('one subclass is'))

# 그루핑 ()
p = re.compile('(ABC)+') #ABC를 그루핑
m = p.search('ABCABCABC OK?')
print(m)
print(m.group())

p = re.compile(r"(\w+)\s+\d+[-]\d+[-]\d+")
m = p.search("park 010-1234-5678")
print(m.group(1)) #그루핑 된 것 중 첫번째를 리턴

# 정규표현식 내에서 다시 사용하기 : \1
p = re.compile(r'(\b\w+)\s+\1') # \1
print(p.search('Paris in the the spring').group())
# the the 

# 그루핑된 문자열에 이름 붙이기 ?P<name>
p = re.compile(r"(?P<name>\w+)\s+((\d+)[-]\d+[-]\d+)")
m = p.search("park 010-1234-5678")
print(m.group("name"))

# 정규표현식 내에서 그루핑 문자열 이름 다시 사용하기 : ?P<name>
p = re.compile(r'(?P<word>\b\w+)\s+(?P=word)') 
print(p.search('Paris in the the spring').group())
# the the 

#전방탐색 : 긍정형(?=)
p = re.compile(".+:") # .문자열이 +반복되다가 :여기까지만 확인
m = p.search("http://google.com")
print(m.group()) #http:가 출력

p = re.compile(".+(?=:)") # .문자열이 +반복되다가 :여기 전까지만 확인
m = p.search("http://google.com")
print(m.group()) #http가 출력


#전방탐색 : 부정형(?!)
p = re.compile(".*[.](?!bat$|exe$).*$", re.M)
             #. 문자열이 *계속 있고 .다음에 확장자 중에서 bat or exe는 제외
l = p.findall("""
autoexec.exe
autoexec.bat
autoexec.jpg
""")
print(l) # ['autoexec.jpg']

#문자열 바꾸기 sub
p = re.compile('(blue|white|red)')
p.sub('colour', 'blue socks and red shoes')

# greedy vs non-greedy
s = '<html><head><title>Title</title>'
print(re.match('<.*>', s).group()) #<> 사이에 들어간 모든 문자열
#<html><head><title>Title</title>

print(re.match('<.*?>', s).group())
# <html> -> 최소한의 결과만 출력




