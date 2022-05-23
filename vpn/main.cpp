
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
void do_readclient(int epollfd,int fd,int sockfd,char *buf,promise<char *> res){
    int nread;
    nread = read(fd,buf,4096);
    if (nread == -1){
        perror("read error:");
        close(fd);
        res.set_value("err");
    }
    else if (nread == 0){
        fprintf(stderr,"server close.\n");
        close(fd);
        res.set_value("err");
    }
    else{
        cout<<"servers say:"<<buf<<endl;
        res.set_value(buf);
        modify_event(epollfd,fd,EPOLLOUT);
    }
}

void epoll_writeclient(int epollfd,int fd,char *buffer){
    cout<<"调用";
    if (write(fd,buffer, strlen(buffer))==-1){
        perror("write error:");
        close(fd);
        delete_event(epollfd,fd,EPOLLOUT);
        return;
    }
    modify_event(epollfd,fd,EPOLLIN);
    memset(buffer,0, 4096);
}


void thread_write(int epollfd,int fd,int listenfd,char *buf){

}






void handler_conntie(int epollfd,struct epoll_event *events,int num,int listenfd,char *buf){
    int i;
    int fd;
    for (i = 0;i < num;i++) {
        fd = events[i].data.fd;
        if (events[i].events & EPOLLIN) {
            do_readclient(epollfd, fd, listenfd, buf);
        } else if (events[i].events & EPOLLOUT){
            epoll_writeclient(epollfd, fd, buf);
        }
    }
}





void Client(){
    int clientfd;
    struct sockaddr_in  clientAdd;  //使用之前需要对其清0
    if((clientfd=socket(AF_INET,SOCK_STREAM,0))==-1){
        cout<<"create client socket erro"<<endl;
        exit(0);
    }
    char buf[4096];
    memset(&clientAdd,0,sizeof (clientAdd));
//    bzero(&clientAdd,sizeof (clientAdd));
    clientAdd.sin_family=AF_INET;
    clientAdd.sin_port=htons(8484);
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
        handler_conntie(epollfd,events,n,clientfd,buf);
    }
//    n = recv(clientfd, buffer, 1024, 0);
//    buffer[n] = '\0';
//    cout << "接受数据：" << buffer<<endl;
    close(clientfd);
    close(epollfd);
}






int main() {
//    auto a = async(launch::async,ser_start,PORT);

    auto b = async(launch::async,Client);



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
