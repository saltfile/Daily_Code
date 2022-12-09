//
// Created by saltfish on 22-12-9.
//
#include "sele_base.h"



int ser_selector_start(const char* ip,const char* port){
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


    //初始化
    fd_set redset;
    FD_ZERO(&redset);
    FD_SET(lfd,&redset);


    int maxfd = lfd;

    char buf[1024] = {0};
    while (1){
        fd_set tmp = redset;
        int ret = select(maxfd+1,&tmp,NULL,NULL,NULL);
        // 判断是不是监听fd
        if (FD_ISSET(lfd,&tmp)){
            // 接受客户端的链接
            int cfd = accept(lfd,NULL,NULL);
            FD_SET(cfd,&redset);
            maxfd = cfd > maxfd ? cfd : maxfd;
        }
        for (int i = 0; i <= maxfd; ++i) {

            if (i!=lfd&& FD_ISSET(i,&tmp)){
                // 接收数据
                int len = recv(i,buf, sizeof(buf),0);
                if (len == -1){
                    perror("recv error");
                    exit(-5);
                } else if(len == 0){
                    printf("客户端断开链接\n");
                    // 如果客户端关闭就close
                    FD_CLR(i,&redset);
                    close(i);
                    break;
                }

                printf("read buf : %s\n",buf);

                ret = send(i,buf, strlen(buf)+1,0);
                if (ret == -1){
                    perror("send err");
                    exit(1);
                }
            }
        }
    }
    close(lfd);

    return 0;
}

