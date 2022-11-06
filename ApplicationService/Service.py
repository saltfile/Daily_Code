import json
import os

import requests as requests
from requests_toolbelt import MultipartEncoder


def Swearing_processing_call(sentence):
    url = 'https://gwgray.tvs.qq.com/ai/sensitive-detect'  # 脏话过滤

    headers = {
        'Appkey': '31812ff08af611eb91b2bf61ee5e21b0',
        'Content-Type': 'application/json',
    }
    data = json.dumps({
        "header": {
        },
        "payload": {
            "content": sentence,
            # "synthetic_method":0,#合成方式
            # "model":"level_1",#合成采用的模型, 标准模型:level_1, 高质模型:level_3, 默认:level_1
            # "index":0
        }
    })
    r = requests.post(url, data, headers=headers)
    print(r.text)
    status = json.loads(r.text)["payload"]['status']
    if status == True:
        strdatas = json.loads(r.text)["payload"]["sensitive_words"]
        print(strdatas)
        for i in strdatas:
            print(i)
            zang = i["pattern"]
            print(zang)
            sentence = sentence.replace(zang, '****')
            print(sentence)
    return sentence





def Speech_to_text(filepath):
    print("测试文件绝对路径：" + filepath)
    url = 'https://gwgray.tvs.qq.com/ai/asr'
    headers = {
        'Appkey': 'fa344ca04d8611eb93763d03417560a2',
        'Content-Type': 'multipart/form-data;boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW'
    }
    payload = json.dumps({  # json.dumps() 是把python对象转换成json对象的一个过程，生成的是字符串
        "payload": {
            "audioMeta": {
                "format": "mp3",  # 音频格式: pcm/wav/amr/opus/mp3
                "sampleRate": "16K",  # 采样率: 8K/16K
                "channel": 1,  # 音频通道数: 1/2
                "lang": "zh-CN"  # 语言类型, 中文: zh-CN, 英文: en-US
            },
            "offset": 0,  # 语音片在语音流中的偏移
            "needPunc": True,  # 是否加标点
            "transNum": True,  # 是否开启文字转数字, 如一二三 to 123
            "useCloudVad": True,  # 是否使用云端vad, 由云端来停止语音，调用方不用发送 'finished'
            "vadThreshold": 111150,  # 云端vad静音阈值, 建议设置500, 单位ms
            "finished": True  # 语音是否结束
        }
    })

    data = MultipartEncoder(  # 封装的请求数据
        fields={
            "audio": (None, open(filepath, 'rb'), 'audio/mp3'),
            # form-data; name="audio"; filename="/D:/123/voiceprint.3.wav" 字段为audio,文件名的key是filename,类型是audio/mp3
            "metadata": (None, payload, "application/json; charset=utf8"),  # form-data; name="metadata"
        }, boundary="----WebKitFormBoundary7MA4YWxkTrZu0gW"
    )
    r = requests.post(url, data=data, headers=headers)
    # print(r.text)
    print(json.loads(r.text)["payload"]["text"])  # 是将字符串传化为字典
    return json.loads(r.text)["payload"]["text"]


def Speech_to_text_wav(filepath):
    print("测试文件绝对路径：" + filepath)
    url = 'https://gwgray.tvs.qq.com/ai/asr'

    headers = {
        'Appkey': 'fa344ca04d8611eb93763d03417560a2',
        'Content-Type': 'multipart/form-data;boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW'
    }
    payload = json.dumps({  # json.dumps() 是把python对象转换成json对象的一个过程，生成的是字符串
        "payload": {
            "audioMeta": {
                "format": "wav",  # 音频格式: pcm/wav/amr/opus/mp3
                "sampleRate": "16K",  # 采样率: 8K/16K
                "channel": 1,  # 音频通道数: 1/2
                "lang": "zh-CN"  # 语言类型, 中文: zh-CN, 英文: en-US
            },
            "offset": 0,  # 语音片在语音流中的偏移
            "needPunc": True,  # 是否加标点
            "transNum": True,  # 是否开启文字转数字, 如一二三 to 123
            "useCloudVad": True,  # 是否使用云端vad, 由云端来停止语音，调用方不用发送 'finished'
            "vadThreshold": 111150,  # 云端vad静音阈值, 建议设置500, 单位ms
            "finished": True  # 语音是否结束
        }
    })
    data = MultipartEncoder(  # 封装的请求数据
        fields={
            "audio": (None, open(filepath, 'rb'), 'audio/mp3'),
            # form-data; name="audio"; filename="/D:/123/voiceprint.3.wav" 字段为audio,文件名的key是filename,类型是audio/mp3
            "metadata": (None, payload, "application/json; charset=utf8"),  # form-data; name="metadata"
        }, boundary="----WebKitFormBoundary7MA4YWxkTrZu0gW"
    )
    r = requests.post(url, data=data, headers=headers)
    # print(r.text)
    print("识别到的文字：" + json.loads(r.text)["payload"]["text"])  # 是将字符串传化为字典  # 是将字符串传化为字典
    return json.loads(r.text)["payload"]["text"]





