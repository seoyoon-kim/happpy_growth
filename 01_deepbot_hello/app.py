#!/usr/bin/python
# -*- coding: utf-8 -*

from flask import Flask, json, render_template, request, jsonify
#라이브러리

# Flask 서버 설정
app = Flask(__name__, static_url_path='')
app.config['JSON_AS_ASCII'] = False



# API 호출 테스트
@app.route('/hello', methods=['GET'])
def hello():
    response_msg = {
        "message": "안녕하세요.", #딕셔너리
    }
    return jsonify(response_msg)


## 메시지 처리
@app.route('/message', methods=['GET', 'POST'])
def chatting():
    message = request.json['message']
       
    if "안녕" in message:
        response = "반갑다"
    elif "날씨" in message:
        #response = openapi.get_wether(today)
        response = "오늘 날씨는 너무 좋아요"
    else :
        response = "몰라요"

    response_msg = {
        "message":response,
    }
    return jsonify(response_msg)
    


## HTML 랜더링
@app.route('/', methods=['GET', 'POST'])
def index():
    return render_template('index.html')


if __name__ == '__main__':
    app.run(debug=True, port=5000)
