//
// Created by saltfish on 23-3-15.
//
#include "tcp_socket.h"

int server(const char* ip,const char* port){
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
    sockaddr_in cli;

    socklen_t addrlen = sizeof(cli);

    int cfd = accept(lfd,(struct sockaddr *)&cli,&addrlen);
    if(cfd == -1)
    {
        perror("accept");
        return -1;
    }
    printf("cfd:%d\n",cfd);
    printf("client ip : %s\nport %d\n",inet_ntoa(cli.sin_addr),ntohs(cli.sin_port));

    if(cfd == -1)
    {
        perror("accept");
        return -2;
    }
    printf("cfd:%d\n",cfd);
    printf("client ip : %s\nport %d\n",inet_ntoa(cli.sin_addr),ntohs(cli.sin_port));

    unsigned char bunf[1024];
    int o = 1684234849;


    int rets = read(cfd,bunf, sizeof(bunf));


    for (int i = 0; i < rets; ++i) {
        printf("0x%x   ",bunf[i]);
    }

//    unsigned char bunf[4] = {0x61,0x62,0x63,0x64};
//    ssize_t send_bytes = send(cfd,bunf,sizeof(bunf),0);
//
//    printf("%d",send_bytes);


    close(lfd);
    close(cfd);
}














struct k1{
    char x1;
    int x2;
    char x3;
};

struct k2{
    char a;
    short b;
    int c;
};

void tcp_test_main(){

    void *p;

    long long a;

    cout<< sizeof(p)<<endl;

    cout<< sizeof(k2)<<endl;



    int arr[3][5] = {{1,2,3,4,5},
                     {6,7,8,9,10},
                     {11,12,13,14,15}};
    int *pu = *arr;
    for (int i = 0; i < 50; ++i) {
        int as = *pu;
        cout<<as<<endl;
        pu+=1;
    }

//    char bunf[4] = {0x61,0x62,0x63,0x64};
//    int *bus = (int *)bunf;
//    int b =0x61626364;
//
//    char *p = (char*)&b;
//    char *p1 = p+1;
//    printf("p = %p,p1 = %p\n",p,p1);
//
//    printf("%#x\n",*p);
//
//    printf("%#x\n",*p1);



//    server("127.0.0.1","8585");


}








