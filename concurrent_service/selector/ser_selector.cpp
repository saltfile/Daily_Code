//
// Created by saltfish on 22-12-9.
//
#include "sele_base.h"
pthread_mutex_t lock;



typedef struct fdhandle{
    int fd;
    int *maxId;
    fd_set *rdset;
}FDinfo;

void* accept_conntion(void* arg){

    FDinfo *info = (FDinfo*)arg;
    printf("client id:%d",info->fd);
    // 接受客户端的链接
    int cfd = accept(info->fd,NULL,NULL);
    pthread_mutex_lock(&lock);
    FD_SET(cfd,info->rdset);
    //解引用
    *info->maxId = cfd > *info->maxId ? cfd : *info->maxId;
    pthread_mutex_unlock(&lock);
    return NULL;
}





void* commit_handler(void* arg){

    FDinfo *info = (FDinfo*)arg;
    printf("client id:%d",info->fd);
    char buf[1024];
    int len = recv(info->fd,buf, sizeof(buf),0);
    if (len == -1){
        perror("recv error");
        free(info);
        return NULL;
    } else if(len == 0){

        printf("客户端断开链接\n");
        // 如果客户端关闭就close
        pthread_mutex_lock(&lock);
        FD_CLR(info->fd,info->rdset);
        close(info->fd);
        pthread_mutex_unlock(&lock);
        free(info);
        return NULL;
    }

    printf("read buf : %s\n",buf);

    int ret = send(info->fd,buf, strlen(buf)+1,0);
    if (ret == -1){
        perror(info->fd+"send error");
        return NULL;
    }

    free(info);
    return NULL;
}








int ser_selector_start(const char* ip,const char* port){
    pthread_mutex_init(&lock,NULL);
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



    //这里考虑到的是线程的并发问题
    while (1){
        pthread_mutex_lock(&lock);
        fd_set tmp = redset;
        pthread_mutex_unlock(&lock);
        int ret = select(maxfd+1,&tmp,NULL,NULL,NULL);
        // 判断是不是监听fd
        if (FD_ISSET(lfd,&tmp)){
            //为了并发,创建子线程
            // 封装任务
            FDinfo *task = (FDinfo*) malloc(sizeof(FDinfo));
            memset(task,0, sizeof(FDinfo));
            task->fd = lfd;
            task->maxId = &maxfd;
            task->rdset = &redset;
            pthread_t pid;
            pthread_create(&pid,NULL,accept_conntion,task);
            //分离线程
            pthread_detach(pid);
        }
        for (int i = 0; i <= maxfd; ++i) {

            if (i!=lfd&& FD_ISSET(i,&tmp)){
                // 接收数据
                FDinfo *task = (FDinfo*) malloc(sizeof(FDinfo));
                memset(task,0, sizeof(FDinfo));
                task->fd = i;
                task->rdset = &redset;
                pthread_t pid;
                pthread_create(&pid,NULL,commit_handler,task);
                //分离线程
                pthread_detach(pid);

            }
        }
    }
    close(lfd);
    pthread_mutex_destroy(&lock);
    return 0;
}

