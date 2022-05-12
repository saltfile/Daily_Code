//
// Created by saltfish on 2022/5/12.
#include "MyAll.h"
//#define PORT 8686			//目标地址端口号
int cil_start(char *host,int port,promise<char *> &promiseObj){
    int iSocketFD = 0; //socket句柄
    unsigned int iRemoteAddr = 0;
    struct sockaddr_in stRemoteAddr = {0}; //对端，即目标地址信息
    socklen_t socklen = 0;
    char buf[4096] = {0}; //存储接收到的数据

    iSocketFD = socket(AF_INET, SOCK_STREAM, 0); //建立socket
    if(0 > iSocketFD)
    {
        printf("创建socket失败！\n");
        return 0;
    }

    stRemoteAddr.sin_family = AF_INET;
    stRemoteAddr.sin_port = htons(port);
    inet_pton(AF_INET, host, &iRemoteAddr);
    stRemoteAddr.sin_addr.s_addr=iRemoteAddr;

    //连接方法： 传入句柄，目标地址，和大小
    if(0 > connect(iSocketFD, (const struct sockaddr *)&stRemoteAddr, sizeof(stRemoteAddr)))
    {
        printf("连接失败！\n");
        //printf("connect failed:%d",errno);//失败时也可打印errno
    }else{

        printf("连接成功！\n");
        char *message = "hello";
        send(iSocketFD,message,strlen(message),0);
        recv(iSocketFD, buf, sizeof(buf), 0);// 将接收数据打入buf，参数分别是句柄，储存处，最大长度，其他信息（设为0即可）。
        printf("Received:%s\n", buf);
    }
    close(iSocketFD);//关闭socket
}
