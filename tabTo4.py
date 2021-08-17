import sys

#탭을 4개의 공백으로 바꾸기
src = sys.argv[1] #입력
dst = sys.argv[2] #출력

f = open(src)
tab_content = f.read()
f.close()

print(src)
print(dst)

space_content = tab_content.replace("\t", " "*4)
print(space_content)

f = open(dst, 'w')
f.write(space_content)
f.close()

