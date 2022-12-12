//
// Created by saltfish on 22-12-10.
//
#include "poll_base.h"
int ser_poll_start(const char* ip,const char* port){

    printf("set ip:%s port: %s\n",ip,port);
    //首先是一个socket
    int lfd = socket(AF_INET,SOCK_STREAM,0);
    if (lfd == -1){
        perror("socket error");
        exit(-1);
    }
    //  服务端口的配置
    sockaddr_in serv_arr;
    memset(&serv_arr,0, sizeof(serv_arr));
    serv_arr.sin_family = AF_INET;
    serv_arr.sin_port = htons(atol(port));
    serv_arr.sin_addr.s_addr = inet_addr(ip);


    //绑定对应ip端口
    int ret = bind(lfd,(sockaddr*)&serv_arr,sizeof(serv_arr));
    if (ret == -1){
        perror("bind error");
        exit(-2);
    }

    ret= listen(lfd,1024);
    if (ret == -1){
        perror("lisent error");
        exit(-3);
    }


    struct pollfd fds[1024];
    for (int i = 0; i < 1024; ++i) {
        fds[i].fd = -1;//初始化fd是小于0的大于等于0是正常的链接
        fds[i].events = POLLIN;
    }
    //监听的socket描述符
    fds[0].fd = lfd;

    int maxfd = 0;

    while (1){
        //设置-1是阻塞，当有文件描述符时才会返回
        ret = poll(fds,maxfd+1,-1);
        if (ret == -1){
            perror("poll error");
            exit(0);
        }
        //检测到缓冲区有变化有新的事件
        if (fds[0].revents&POLLIN){
            //处理链接的请求
            sockaddr_in sockaddrin;
            int len = sizeof(sockaddrin);

            //注：这里不会阻塞
            int connfd = accept(lfd, (struct sockaddr*)&sockaddrin, reinterpret_cast<socklen_t *>(&len));

            //  内核检测到的connfd的读缓冲区
            int  i;
            for ( i = 0; i < 1024; ++i) {
                if (fds[i].fd == -1)
                {
                    fds[i].fd = connfd;
                    break;
                }
            }
            maxfd = i > maxfd ? i : maxfd;
        }

        for (int i = 0; i < maxfd; ++i) {

            if (fds[i].revents&POLLIN){
                char buf[1024];
                int ret = read(fds[i].fd,buf, sizeof(buf));
                if (ret == -1) {
                    perror("read error");
                    exit(0);
                }
                else if (ret == 0)
                {
                    printf("对方已经关闭连接");
                    close(fds[i].fd);
                    fds[i].fd = -1;
                } else{
                    printf("客户端:%s",buf);
                    write(fds[i].fd,buf, strlen(buf)+1);
                }
            }
        }
    }
    close(lfd);
    return 0;
}