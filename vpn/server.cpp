//
// Created by saltfish on 2022/5/11.
//
#include <mutex>
#include "MyAll.h"
/**
 * TODO:正常的c语言服务端开启
 * @param port:端口号
 * @return 只是代表结束，没什么意义
 * */
int ports[4] = {8080,8181,7878,7979};


std::mutex cout_mutex;
int sckt_bind_fun(int port){
    int iSocketFD = 0;  //socket句柄
    int new_fd = 0;    //建立连接后的句柄
    struct sockaddr_in stLocalAddr = {0}; //本地地址信息结构图，下面有具体的属性赋值
    struct sockaddr_in stRemoteAddr = {0}; //对方地址信息
    socklen_t socklen = 0;

    iSocketFD = socket(AF_INET, SOCK_STREAM, 0); //建立socket
    if (0 > iSocketFD) {
        printf("创建socket失败！\n");
        return 0;
    }

    stLocalAddr.sin_family = AF_INET;  /*该属性表示接收本机或其他机器传输*/
    stLocalAddr.sin_port = htons(port); /*端口号*/
    stLocalAddr.sin_addr.s_addr = htonl(INADDR_ANY); /*IP，括号内容表示本机IP*/

    //绑定地址结构体和socket
    if (0 > bind(iSocketFD, (struct sockaddr *) &stLocalAddr, sizeof(stLocalAddr))) {
        printf("绑定失败！\n");
        exit(-1);
    }

    //开启监听 ，第二个参数是最大监听数
    if (0 > listen(iSocketFD, 128)) {
        printf("监听失败！\n");
        exit(-2);
    }
    printf("iSocketFD: %d\n", iSocketFD);
    return iSocketFD;
}


void handler_eventsserver(int epollfd,struct epoll_event *events,int num,int listenfd,char *buf){
    int i;
    int fd;
    /*进行选好遍历*/
    for (i = 0;i < num;i++){
        fd = events[i].data.fd;
        /*根据描述符的类型和事件类型进行处理*/
        if ((fd == listenfd) &&(events[i].events & EPOLLIN)) { }
        else if (events[i].events & EPOLLIN)
            rec_runtable(epollfd,fd,buf);
        else if (events[i].events & EPOLLOUT)
            send_runables(epollfd,fd,buf);
    }
}

void handler_accpet(int epollfd,int listenfd){
    int clifd;
    struct sockaddr_in cliaddr;
    socklen_t  cliaddrlen;
    clifd = accept(listenfd,(struct sockaddr*)&cliaddr,&cliaddrlen);
//    clifd = accept(listenfd, (struct sockaddr *) &stRemoteAddr, &socklen);
    if (clifd == -1)
        perror("accpet error:");
    else{
        printf("accept a new client: %s:%d\n",inet_ntoa(cliaddr.sin_addr),cliaddr.sin_port);
        /*添加一个客户描述符和事件*/
        add_event(epollfd,clifd,EPOLLIN);
    }
}



void run_epoll(int listenfd){
    int epollfd;
    struct epoll_event events[20];
    int ret;
    char buf[4096];
    memset(buf,0,4096);
    /*创建一个描述符*/
    epollfd = epoll_create(1024);
    /*添加监听描述符事件*/
    add_event(epollfd,listenfd,EPOLLIN);
    while(1){
        /*获取已经准备好的描述符事件*/
        ret = epoll_wait(epollfd,events,20,-1);
        handler_eventsserver(epollfd,events,ret,listenfd,buf);//重点注意这个ret，它返回的是已经有事儿的fd的个数
        //这样一会儿轮循的就不是所有的fd了
    }
    close(epollfd);
}



int ser_start(int port) {
    int  iSocketFD;
    iSocketFD = sckt_bind_fun(port);
    listen(iSocketFD,5);
    run_epoll(iSocketFD);
    return 0;
}
void send_runtable(int new_fd,char *buf){
    send(new_fd, buf, strlen(buf), 0);
}
/**
 * 这里读取出来的返回值需要返回
 * @param promiseObj 返回数值表示对端是否断掉
 * @param promiseObj2 返回的内容
 */
void rec_runtable(int epollfd,int fd,char *buf) {
    int nread;
    memset(buf,0,sizeof(buf));
    nread = read(fd,buf,4096);
    if (nread == -1){
        perror("read error:");
        close(fd);
        delete_event(epollfd,fd,EPOLLIN);
    }
    else if (nread == 0){
        fprintf(stderr,"client close.\n");
//        close(fd);
        delete_event(epollfd,fd,EPOLLIN);
    }
    else{
        printf("read message is : %s",buf);
        /*修改描述符对应的事件，由读改为写*/
        char *message = str_copy(message,buf);
        int pos = PORT;
        for(int i = 0;i < 4;i++) {
            message = str_copy(message, requs(message, pos, ports[i]));
            if (0 >= nread)    //对端关闭连接 返回0
            {
                printf("接收失败或者对端关闭连接！\n");
                return;
            } else {
                printf("buf: %s\n", message);
            }
            cout << "更该报文-------->" << endl;
            promise<char *> respone;
            future<char *> res = respone.get_future();
            thread cil(cil_start, ports[i], message, ref(respone));
            cil.join();
            char *resp = res.get();
            cout << "respone size:" << strlen(resp);
            cout << "respone:" << resp;
            char *strp = "false";
            pos = ports[i];
            if(strcmp(resp, strp) != 0){
                char c = resp[9];
                char c1 = resp[10];
                char c2 = resp[11];
                if(c == '2'||(c=='3'&&c1 == '0'&&c2=='4')){
                    buf = str_copy(buf,resp);
                    break;
                }
            }
        }
        modify_event(epollfd,fd,EPOLLIN);
      send_runtable(fd,buf);
    }
//    close(fd);
//    return buf;
}
void send_runables(int epollfd,int fd,char *buf){
    int nwrite;
    nwrite = write(fd,buf,strlen(buf));
    if (nwrite == -1){
        perror("write error:");
        close(fd);
        delete_event(epollfd,fd,EPOLLOUT);
    }
    else
        modify_event(epollfd,fd,EPOLLIN);
    memset(buf,0,4096);
}




//int server_main(int epollfd, int new_fd){
//    int iRecvLen = 0;   //接收成功后的返回值
//        if (0 > new_fd) {
//            printf("接收失败！\n");
//            return 0;
//        }
//        printf("new_fd: %d\n", new_fd);//分出一个线程处理接收
//
//
//
//            promise<int> singal;
//            promise<char *> readMes;
//            //分出一个线程处理读问题
//            thread t1(rec_runtable,epollfd, new_fd, ref(singal), ref(readMes));
//            t1.join();
//            future<int> sin = singal.get_future();
//            future<char *> mes = readMes.get_future();
//            iRecvLen = sin.get();
//            char *message = str_copy(message, mes.get());
//            int pos = PORT;
//            for(int i = 0;i < 4;i++) {
//            message = str_copy(message, requs(message, pos, ports[i]));
//            if (0 >= iRecvLen)    //对端关闭连接 返回0
//            {
//                printf("接收失败或者对端关闭连接！\n");
//                return -5;
//            } else {
//                printf("buf: %s\n", message);
//            }
//            cout << "更该报文-------->" << endl;
//            promise<char *> respone;
//            future<char *> res = respone.get_future();
//            thread cil(cil_start, ports[i], message, ref(respone));
//            cil.join();
//            char *resp = res.get();
//            cout << "respone size:" << strlen(resp);
//            cout << "respone:" << resp;
//            char *strp = "false";
//            pos = ports[i];
//            if(strcmp(resp, strp) != 0){
//            if(resp[8] != '4'){
//            thread send(send_runtable, new_fd, resp);
//            send.join();
//            break;
//            }
//            }
//        }
//    close(new_fd);//完成工作关闭链接
//}

char *requs(char *request,int old,int new_){
    char *oldhost = "localhost:";
    char *newhost = "localhost:";
    char *oldport = (char *)malloc(sizeof(5));
    memset(oldport,0,sizeof(oldport));
    sprintf(oldport,"%d",old);
    oldhost = str_merge(oldhost,oldport);
    char *newport = (char *)malloc(sizeof(5));
    memset(newport,0,sizeof(newport));
    sprintf(newport,"%d",new_);
    newhost = str_merge(newhost,newport);
    request = strrpc(request,oldhost,newhost);
    return request;
}


//////////////////////////////////////////////////////////////////

void add_event(int epollfd,int fd,int state){
    struct epoll_event ev;
    ev.events = state;
    ev.data.fd = fd;
    epoll_ctl(epollfd,EPOLL_CTL_ADD,fd,&ev);
}

void delete_event(int epollfd,int fd,int state){
    struct epoll_event ev;
    ev.events = state;
    ev.data.fd = fd;
    epoll_ctl(epollfd,EPOLL_CTL_DEL,fd,&ev);
}

void modify_event(int epollfd,int fd,int state){
    struct epoll_event ev;
    ev.events = state;
    ev.data.fd = fd;
    epoll_ctl(epollfd,EPOLL_CTL_MOD,fd,&ev);
}
