//
// Created by saltfish on 2022/10/5.
//

#include "my_bases.h"

#define BUF_LENS 4096







int client_run(int port,char* address){
    printf("\n"
          "  _____             _              _____   ____  \n"
          " |_   _|           | |            |  __ \\ |  _ \\ \n"
          "   | |   _ __    __| |  ___ __  __| |  | || |_) |\n"
          "   | |  | '_ \\  / _` | / _ \\\\ \\/ /| |  | ||  _ <       port:%d   \n"
          "  _| |_ | | | || (_| ||  __/ >  < | |__| || |_) |          host:localhost   \n"
          " |_____||_| |_| \\__,_| \\___|/_/\\_\\|_____/ |____/ \n"
          "    I just wrote some nonsense here. I'm still a beta \n"
          " version, so I can't think of anything                \n"
          "                                                 \n",port);






    int sfd,ret;
    char writebuf[BUF_LENS] ={0};
    char readbuf[BUF_LENS] = {0};
    ssize_t send_bytes,recv_bytes;
    struct sockaddr_in dest;
    socklen_t len = sizeof(dest);
    sfd = socket(AF_INET,SOCK_STREAM,0);
    if(sfd == -1)
    {
        perror("socket fail");
        return -1;
    }
    dest.sin_family = AF_INET;
    dest.sin_port = htons(port);
    dest.sin_addr.s_addr = inet_addr(address);
    ret = connect(sfd,(const struct sockaddr *)&dest,len);


    if(ret == -1)
    {
        perror("connect fail");
        return -1;
    }


    while(1)
    {
        //write
        if (!buf_send(sfd,writebuf)){
            continue;
        }
        if (buf_read(sfd,readbuf)){
//            printf("%s\n",readbuf);
        } else{
            printf("读取信息失败\n");
            continue;
        }

    }
    close(sfd);


}


//封包协议测试点
int buf_send(int sfd,char *buf){

    printf("IndexDB >");
    memset(buf,0,BUF_LENS);
    fgets(buf,BUF_LENS,stdin);
    if(strncmp(buf,"quit",4) == 0)
    {
        printf("client shutdown\n");
        exit(0);
    }
    packge send_packge;

    //拼接报文体
    u8 head = grammer_check(buf);
    if (head == 0)
    {
        cout<<"Unknown statement, please re-enter"<<endl;
        return 0;
    }


    if (head!=0){
        send_packge.create_package(buf,head);
    }


    int send_bytes = send(sfd,send_packge.all,strlen((char *)send_packge.all),0);



    if(send_bytes == -1)
    {
        perror("send fail");
        return 0;
    }
    return 1;
}



int buf_read(int sfd,char* buf){
    memset(buf,0,BUF_LENS);
    int recv_bytes = recv(sfd,buf,BUF_LENS,0);

    packge *packge1 = (packge *)malloc(sizeof(packge));
    packge1->solve_package((u8 *)buf);
    //展示结果
    cout<<packge1->result;

    if(recv_bytes == -1)
    {
        return 0;
    }
    else if(recv_bytes == 0)
    {
        return 0;
    }
    return 1;

}






