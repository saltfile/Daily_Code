//
// Created by saltfish on 22-12-14.
//
#include "epoll_base.h"
int ser_epoll_et_start(const char* ip,const char* port){
    //监听的socket()
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

    //设置端口复用
    int optv = 1;
    setsockopt(lfd,SOL_SOCKET,SO_REUSEADDR,&optv,sizeof(optv));


    //绑定对应ip端口
    int ret = bind(lfd,(sockaddr*)&serv_arr,sizeof(serv_arr));
    if (ret == -1){
        perror("bind error");
        exit(-2);
    }

    //设置监听
    ret= listen(lfd,1024);
    if (ret == -1){
        perror("lisent error");
        exit(-3);
    }

    // 创建epoll对象,；里面的数字大于0就行
    int epoll_fd = epoll_create(1);
    if (epoll_fd == -1){
        perror("epoll_tree create error");
        exit(0);
    }

    //EOPLL事件 改为epoll et
    epoll_event event;
    event.events = EPOLLIN | EPOLLET;
    event.data.fd = lfd;
    epoll_ctl(epoll_fd,EPOLL_CTL_ADD,lfd,&event);
    epoll_event events[1024];
    int evs_lens = sizeof(events)/ sizeof(epoll_event);

    while (1){
        //阻塞等待
        int num = epoll_wait(epoll_fd,events,evs_lens,-1);
        printf("num = %d",num);


        for (int i = 0; i < num; ++i) {
            //有事的事件fd
            int fd = events[i].data.fd;
            if (fd == lfd){
                int cfd = accept(fd,NULL,NULL);
                event.events = EPOLLIN|EPOLLET;
                event.data.fd = cfd;
                epoll_ctl(epoll_fd,EPOLL_CTL_ADD,cfd,&event);
            } else{
                char buf[5];
                memset(buf,0, sizeof(buf));
                int len = recv(fd,buf, sizeof(buf),0);
                if (len == -1){
                    perror("recv error");
                    exit(1);
                } else if(len == 0){

                    printf("客户端断开链接\n");
                    // 如果客户端关闭就close  先删除再关闭
                    epoll_ctl(epoll_fd,EPOLL_CTL_DEL,fd,NULL);
                    close(fd);
                    break;
                }

                printf("read buf : %s\n",buf);
                printf("\n");
                int ret = send(fd,buf, strlen(buf)+1,0);
                if (ret == -1){
                    perror("send error");
                    exit(0);
                }
            }
        }
    }
    close(lfd);

    return 0;

}