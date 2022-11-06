import os

import flask
from flask import Flask, render_template, request, json
from werkzeug.utils import secure_filename

from Service import Speech_to_text, Swearing_processing_call, Speech_to_text_wav

app = Flask(__name__)


@app.route('/')
def hello_world():  # put application's code here
    return 'Hello World!'


@app.route('/main')
def mains():
    return flask.render_template("index.html")
#脏话过滤
@app.route('/Swear',methods=["POST"])
def Sweart():
    if flask.request.method=="POST":
        un=flask.request.form.get("un")
        data =json.loads( request.get_data())
        mes = data["text"]
        res = Swearing_processing_call(mes)
        return "{\"res\":\""+res+"\"}"
#语音转文字
@app.route('/upload', methods=['POST'])
def upload_file():
    if request.method == 'POST':
        f = request.files['file']
        print(request.files)
        ss = os.getcwd()
        filetype = secure_filename(f.filename)
        typu = str.split(filetype,".")[1]
        storagepath = ss+"\\static\\"+secure_filename(f.filename)
        f.save(storagepath)

        if typu == "mp3":
            res = Speech_to_text(storagepath)
            return "{\"res\":\""+res+"\"}"
        if typu == "wav":
            res = Speech_to_text_wav(storagepath)
            return "{\"res\":\""+res+"\"}"







if __name__ == '__main__':
    app.run()
