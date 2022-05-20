
#include "MyAll.h"
#define MAXSIZE     1024
#define IPADDRESS   "127.0.0.1"
//#define SERV_PORT   8383
#define FDSIZE        1024
#define EPOLLEVENTS 20
#define LISTENQ     5
int count1=0;
void add_event(int epollfd,int fd,int state);
void delete_event(int epollfd,int fd,int state);
void modify_event(int epollfd,int fd,int state);

//=============================================================================================
/*函数声明*/
/*创建套接字并进行绑定*/
int socket_bind(const char* ip,int port);
/*IO多路复用epoll*/
void do_epoll(int listenfd);
/*事件处理函数*/
void handle_eventsserver(int epollfd,struct epoll_event *events,int num,int listenfd,char *buf);
/*处理接收到的连接*/
void handle_accpet(int epollfd,int listenfd);
/*读处理*/
void do_readserver(int epollfd,int fd,char *buf);
/*写处理*/
void do_writeserver(int epollfd,int fd,char *buf);

int serverepoll1(){
    int  listenfd;
    listenfd = socket_bind(IPADDRESS,PORT);
    listen(listenfd,LISTENQ);
    do_epoll(listenfd);
    return 0;
}

int socket_bind(const char* ip,int port){
    int  listenfd;
    struct sockaddr_in servaddr;
    listenfd = socket(AF_INET,SOCK_STREAM,0);
    if (listenfd == -1){
        perror("socket error:");
        exit(1);
    }
    bzero(&servaddr,sizeof(servaddr));
    servaddr.sin_family = AF_INET;
    inet_pton(AF_INET,ip,&servaddr.sin_addr);
    servaddr.sin_port = htons(port);
    if (bind(listenfd,(struct sockaddr*)&servaddr,sizeof(servaddr)) == -1){
        perror("bind error: ");
        exit(1);
    }
    return listenfd;
}

void do_epoll(int listenfd){
    int epollfd;
    struct epoll_event events[EPOLLEVENTS];
    int ret;
    char buf[MAXSIZE];
    memset(buf,0,MAXSIZE);
    /*创建一个描述符*/
    epollfd = epoll_create(FDSIZE);
    /*添加监听描述符事件*/
    add_event(epollfd,listenfd,EPOLLIN);
    while(1){
        /*获取已经准备好的描述符事件*/
        ret = epoll_wait(epollfd,events,EPOLLEVENTS,-1);
        handle_eventsserver(epollfd,events,ret,listenfd,buf);//重点注意这个ret，它返回的是已经有事儿的fd的个数
        //这样一会儿轮循的就不是所有的fd了
    }
    close(epollfd);
}

void handle_eventsserver(int epollfd,struct epoll_event *events,int num,int listenfd,char *buf){
    int i;
    int fd;
    /*进行选好遍历*/
    for (i = 0;i < num;i++){
        fd = events[i].data.fd;
        /*根据描述符的类型和事件类型进行处理*/
        if ((fd == listenfd) &&(events[i].events & EPOLLIN))
            handle_accpet(epollfd,listenfd);
        else if (events[i].events & EPOLLIN){
             rec_runtable(epollfd,fd,buf);
//            do_writeserver(epollfd,fd,buf);
        }
        else if (events[i].events & EPOLLOUT){
//            send_runables(epollfd,fd,buf);
        }
//            do_writeserver(epollfd,fd,buf);
    }
}

void handle_accpet(int epollfd,int listenfd){
    int clifd;
    struct sockaddr_in cliaddr;
    socklen_t  cliaddrlen;
    clifd = accept(listenfd,(struct sockaddr*)&cliaddr,&cliaddrlen);
    if (clifd == -1)
        perror("accpet error:");
    else{
        printf("accept a new client: %s:%d\n",inet_ntoa(cliaddr.sin_addr),cliaddr.sin_port);
        /*添加一个客户描述符和事件*/
        add_event(epollfd,clifd,EPOLLIN);
    }
}

void do_readserver(int epollfd,int fd,char *buf){
    int nread;
    nread = read(fd,buf,MAXSIZE);
    if (nread == -1){
        perror("read error:");
        close(fd);
        delete_event(epollfd,fd,EPOLLIN);
    }
    else if (nread == 0){
        fprintf(stderr,"client close.\n");
        close(fd);
        delete_event(epollfd,fd,EPOLLIN);
    }
    else{
        printf("read message is : %s",buf);
        /*修改描述符对应的事件，由读改为写*/
        modify_event(epollfd,fd,EPOLLIN);
    }
}
//
void do_writeserver(int epollfd,int fd,char *buf){
    int nwrite;
    nwrite = write(fd,buf,strlen(buf));
    if (nwrite == -1){
        perror("write error:");
        close(fd);
        delete_event(epollfd,fd,EPOLLOUT);
    }
    else
        modify_event(epollfd,fd,EPOLLIN);
    memset(buf,0,MAXSIZE);
}
//====================================================================================



//int epoll1(){
////    clientepoll1();
////    serverepoll1();
//    return 0;
//}


int main() {
//    auto a = async(launch::async,ser_start,PORT);

//    log_info("ss{}",1,"sdaasda");
//cout<<itoa_fun(1,"aa",3);

//    auto i =  async(launch::async,epoll1);
//
//log_info("我说：{}{}",2,"aaa","bbb");
//log_debug("asdasdasd");
//log_erro("asdasdasd");

//    thread t(ser_start,PORT);
//    t.join();
    return 0;
}
