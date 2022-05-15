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
int ser_start(int port) {
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
        return 0;
    }

    //开启监听 ，第二个参数是最大监听数
    if (0 > listen(iSocketFD, 128)) {
        printf("监听失败！\n");
        return 0;
    }

    printf("iSocketFD: %d\n", iSocketFD);
    //在这里阻塞知道接收到消息，参数分别是socket句柄，接收到的地址信息以及大小
    for(;;) {
        new_fd = accept(iSocketFD, (struct sockaddr *) &stRemoteAddr, &socklen);
        auto i = async(launch::async,server_main,new_fd);
        cout<<"接受连接并处理"<<endl;
//        thread run(server_main,new_fd);
//        run.join();

    }
    close(new_fd);
    close(iSocketFD);
    return 0;
}

/**
 * 这里读取出来的返回值需要返回
 * @param promiseObj 返回数值表示对端是否断掉
 * @param promiseObj2 返回的内容
 */
void rec_runtable(int new_fd, promise<int> &promiseObj, promise<char *> &promiseObj2) {
    char buf[1024 * 4];
    memset(buf,0,sizeof(buf));
    int singal = recv(new_fd, buf, sizeof(buf), 0);
    promiseObj.set_value(singal);
    promiseObj2.set_value(buf);
}

void send_runtable(int new_fd,char *buf){
send(new_fd, buf, strlen(buf), 0);
}

int server_main(int new_fd){
    int iRecvLen = 0;   //接收成功后的返回值
        if (0 > new_fd) {
            printf("接收失败！\n");
            return 0;
        }
        printf("new_fd: %d\n", new_fd);//分出一个线程处理接收



            promise<int> singal;
            promise<char *> readMes;
            //分出一个线程处理读问题
            thread t1(rec_runtable, new_fd, ref(singal), ref(readMes));
            t1.join();
            future<int> sin = singal.get_future();
            future<char *> mes = readMes.get_future();
            iRecvLen = sin.get();
            char *message = str_copy(message, mes.get());
            int pos = PORT;
            for(int i = 0;i < 4;i++) {
            message = str_copy(message, requs(message, pos, ports[i]));
            if (0 >= iRecvLen)    //对端关闭连接 返回0
            {
                printf("接收失败或者对端关闭连接！\n");
                return -5;
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
            if(resp[8] != '4'){
            thread send(send_runtable, new_fd, resp);
            send.join();
            break;
            }
            }
        }
    close(new_fd);//完成工作关闭链接
}

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





