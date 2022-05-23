//
// Created by lyq on 2022/5/13.
//
#include <thread>
#include <future>
#include <sys/socket.h>
#include <netinet/in.h>
#include <cstring>
#include <unistd.h>
#include<arpa/inet.h>
#include <sys/epoll.h>

#include "iostream"
using namespace std;

int count=0;
void add_event(int epollfd,int fd,int state);

void delete_event(int epollfd,int fd,int state);

void change_event(int epollfd,int fd,int state);

void epoll_write(int epollfd,int fd,char *buffer);

void do_read(int epollfd,int fd,int sockfd,char *buf){
    int nread;
    nread = read(fd,buf,4096);
    if (nread == -1){
        perror("read error:");
        close(fd);
    }
    else if (nread == 0){
        fprintf(stderr,"server close.\n");
        close(fd);
    }
    else{
        if (fd == STDIN_FILENO)
            add_event(epollfd,sockfd,EPOLLOUT);
        else{
            delete_event(epollfd,sockfd,EPOLLIN);
            add_event(epollfd,STDOUT_FILENO,EPOLLOUT);
        }
    }
}




string Client(char *buf){
    int clientfd;
    struct sockaddr_in  clientAdd;  //使用之前需要对其清0
    if((clientfd=socket(AF_INET,SOCK_STREAM,0))==-1){
        cout<<"create client socket erro"<<endl;
        exit(0);
    }
    memset(&clientAdd,0,sizeof (clientAdd));
//    bzero(&clientAdd,sizeof (clientAdd));
    clientAdd.sin_family=AF_INET;
    clientAdd.sin_port=htons(8080);
    //inet_pton是一个IP地址转换函数，
    // 可以在将IP地址在“点分十进制”和“二进制整数”之间转换而且，
    // inet_pton和inet_ntop这2个函数能够处理ipv4和ipv6。
    if(inet_pton(AF_INET,"127.0.0.1",&clientAdd.sin_addr)<=0){
        cout<<"inet_pton client socket erro"<<endl;
        exit(0);
    }
    if (connect(clientfd,( struct sockaddr*)&clientAdd,sizeof (clientAdd))>0){
        printf("connect error: %s(errno: %d)\n",strerror(errno),errno);
        exit(0);
    }
    int n;
    struct epoll_event events[10];
    int epollfd;
    epollfd= epoll_create(1024);
    memset(&events,0, sizeof (events));
    add_event(epollfd,clientfd,EPOLLOUT);
    while (1){
        n= epoll_wait(epollfd,events,20,-1);
        if (n<0){
            cout<<"断开连接"<<endl;
            break;
        }
        int fd;
        int i;
//        cout<<n<<endl;
        for (i = 0;i < n;i++) {
            fd = events[i].data.fd;
            if (events[i].events & EPOLLIN) {
                do_read(epollfd, fd, clientfd, buf);
                return buf;
            } else if (events[i].events & EPOLLOUT){
                epoll_write(epollfd, fd, buf);
            }
        }
    }
//    n = recv(clientfd, buffer, 1024, 0);
//    buffer[n] = '\0';
//    cout << "接受数据：" << buffer<<endl;
    close(clientfd);
    close(epollfd);
}





void messageHandler(char *buffer,char *s){
    char *newBuffer= strstr(buffer,"localhost:");
    int n= strlen("localhost:");
    newBuffer+=n;
    for (int i = 0; i < strlen(s); ++i) {
        *newBuffer=s[i];
        newBuffer++;
    }
}







void add_eventa(int epollfd,int fd,int state){
    struct epoll_event ev;
    ev.events = state;
    ev.data.fd = fd;
    //将被监听的描述符添加到红黑树或从红黑树中删除或者对监听事件进行修改
    epoll_ctl(epollfd,EPOLL_CTL_ADD,fd,&ev); //向interest list添加一个需要监视的描述符
}





void change_event(int epollfd,int fd,int state){
    struct epoll_event ev;
    ev.events = state;
    ev.data.fd = fd;
    epoll_ctl(epollfd,EPOLL_CTL_MOD,fd,&ev); //修改interest list中一个描述符
}





void epoll_accept(int listenfd,struct sockaddr_in stRemoteAddr,socklen_t socklen,int epollfd){
    int clifd;
    if ((clifd = accept(listenfd, (struct sockaddr *) &stRemoteAddr, &socklen)) == -1) {
        cout << "accept socket erro" << endl;
        exit(0);
    }
    add_event(epollfd,clifd,EPOLLIN);
}





void delete_eventa(int epollfd,int fd,int state){
    struct epoll_event ev;
    ev.events = state;
    ev.data.fd = fd;
    epoll_ctl(epollfd,EPOLL_CTL_DEL,fd,&ev); //从interest list中删除一个描述符
}





void epoll_read(int epollfd,int fd,char *buffer){
    int n;
    if((n = read(fd, buffer, 4096))<=0){
        cout <<"客户端-"<<fd<< "-连接中断" << endl;
        delete_event(epollfd,fd,EPOLLIN);
        return;
    }
    buffer[n] = '\0';
    cout << "客户端-" << fd << "-发送数据：\n" << buffer;
    messageHandler(buffer,"8080");
    cout << "修改数据：\n" << buffer;
    auto i=async(launch::deferred,Client,buffer);
    string s=i.get();
    cout<<"接受数据：\n"<<buffer<<endl;

    change_event(epollfd,fd,EPOLLOUT);
}






void epoll_write(int epollfd,int fd,char *buffer){
    if (write(fd,buffer, strlen(buffer))==-1){
        perror("write error:");
        close(fd);
        delete_event(epollfd,fd,EPOLLOUT);
        return;
    }
    change_event(epollfd,fd,EPOLLIN);
    memset(buffer,0, 4096);
}






void Server(){
    int listenfd,fd;
    struct sockaddr_in  serverAdd;
    struct sockaddr_in stRemoteAddr = {0}; //对方地址信息
    socklen_t socklen = 0;
    if((listenfd=socket(AF_INET,SOCK_STREAM,0))==-1){
        //AF_INET 代表发的包为ipv4协议,
        // type参数的作用是设置通信的协议类型,
        // SOCK_STREAM 提供面向连接的稳定数据传输，即TCP协议。
        //参数protocol用来指定socket所使用的传输协议编号。
        cout<<"create socket erro"<<endl;
        exit(0);
    }
    memset(&serverAdd,0,sizeof (serverAdd));
    serverAdd.sin_family=AF_INET;               //注册协议簇
    serverAdd.sin_addr.s_addr = htonl(INADDR_ANY);//将主机的无符号长整形数转换成网络字节顺序。
    serverAdd.sin_port=htons(8888);//将一个无符号短整型数值转换为网络字节序，即大端模式(big-endian)　
    if(bind(listenfd,(struct sockaddr *)&serverAdd,sizeof (serverAdd))==-1){
        cout<<"bind socket erro"<<endl;
        exit(0);
    }
    if (listen(listenfd,10)==-1){
        //指的是在完成TCP三次握手后的队列。即在系统accept之前的队列。已经完成的队列。
        // 如果系统没有调用accpet把这个队列的数据拿出来。一旦这个队列满了。未连接队列的请求过不来。
        // 导致未连接队列里的请求会超时或者拒绝。如果系统调用了accpet队列接受请求数据。
        // 那么就会把接受到请求移除已完成队列。 这时候已完成队列又可以使用了。
        cout<<"listen socket erro"<<endl;
        exit(0);
    }
    struct epoll_event event[20];
    int epollfd= epoll_create(1024);
    /*添加监听描述符事件*/
    add_event(epollfd,listenfd,EPOLLIN);
    cout<<"服务器开启"<<endl;
    int ret;
    char buffer[4096];

    try {
        while (1) {
            ret= epoll_wait(epollfd,event,20,-1);   //ret返回的是有事的fd的个数
            for (int i = 0; i < ret; ++i) {
                fd=event[i].data.fd;
                if (fd==listenfd&&(event[i].events&EPOLLIN)){
                    epoll_accept(listenfd,stRemoteAddr,socklen,epollfd);
                } else if (event[i].events&EPOLLIN){
                    epoll_read(epollfd,fd,buffer);
                } else if (event[i].events&EPOLLOUT){
                    epoll_write(epollfd,fd,buffer);
                }
            }
        }
    }catch (exception exception){
        close(fd);
    }

}



//int main(){
////    auto ser= async(launch::async,Server);
//    Server();
//}