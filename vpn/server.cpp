//
// Created by saltfish on 2022/5/11.
//
#include "MyAll.h"
/**
 * TODO:正常的c语言服务端开启
 * @param port:端口号
 * @return 只是代表结束，没什么意义
 * */
int ser_start(int port){
    int listenfd,connfd;
    socklen_t client;
    struct  sockaddr_in cliaddr,seraddr;

    listenfd = socket(AF_INET,SOCK_STREAM,0);
    bzero(&seraddr,sizeof(seraddr));
    seraddr.sin_family = AF_INET;
    seraddr.sin_addr.s_addr = htonl(INADDR_ANY);
    seraddr.sin_port = htons(port);

    bind(listenfd,(struct sockaddr *) &seraddr,sizeof(seraddr));
    listen(listenfd,1024);

    for(;;){
        client = sizeof(cliaddr);
        connfd = accept(listenfd,(struct  sockaddr *) &cliaddr,&client);
//        read_runtable(connfd);
    }
}
