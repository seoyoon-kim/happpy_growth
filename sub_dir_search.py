import os

def search(dirname):
    try:    
        filenames = os.listdir(dirname)
        for filename in filenames:
            full_filename = os.path.join(dirname, filename)

            #폴더가 있으면 한번 더 하위폴더로 들어가서 검색한다
            if os.path.isdir(full_filename):
                    search(full_filename) #재귀함수
            else:
                ext = os.path.splitext(full_filename)[-1] #확장자 변수에 넣기
                if ext =='.py':
                    print(full_filename)
    except PermissionError : #권한 없음 에러 발생 시
        pass
search("C:/")

