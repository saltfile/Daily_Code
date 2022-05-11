#include "MyAll.h"


#define PORT 8686			//目标地址端口号
#define ADDR "localhost" //目标地址IP

int main()
{
    int iSocketFD = 0; //socket句柄
    unsigned int iRemoteAddr = 0;
    struct sockaddr_in stRemoteAddr = {0}; //对端，即目标地址信息
    socklen_t socklen = 0;
    char buf[4096] = {0}; //存储接收到的数据

    iSocketFD = socket(AF_INET, SOCK_STREAM, 0); //建立socket
    if(0 > iSocketFD)
    {
        printf("创建socket失败！\n");
        return 0;
    }

    stRemoteAddr.sin_family = AF_INET;
    stRemoteAddr.sin_port = htons(PORT);
    inet_pton(AF_INET, ADDR, &iRemoteAddr);
    stRemoteAddr.sin_addr.s_addr=iRemoteAddr;

    //连接方法： 传入句柄，目标地址，和大小
    if(0 > connect(iSocketFD, (const struct sockaddr *)&stRemoteAddr, sizeof(stRemoteAddr)))
    {
        printf("连接失败！\n");
        //printf("connect failed:%d",errno);//失败时也可打印errno
    }else{

        printf("连接成功！\n");
        recv(iSocketFD, buf, sizeof(buf), 0);// 将接收数据打入buf，参数分别是句柄，储存处，最大长度，其他信息（设为0即可）。
        printf("Received:%s\n", buf);
        send(iSocketFD,buf,sizeof(buf),0);


    }

    close(iSocketFD);//关闭socket
    return 0;
}



























//int main() {
//
//    char **strs = split("aaaa,bbb,ccc",",");
//    char *a = strs[1];
//    cout<<strs[0]<<endl;
//    cout<<strs[1]<<endl;
//    cout<<strs[2]<<endl;
//
//    char *aa = (char *)malloc(sizeof(3));
//    aa = "aaa";
//    char *bb = (char *)malloc(sizeof(8));
//    bb = "bbb";
//    aa = str_merge(aa,bb);
//
//    cout<<aa<<endl;
//
//    char str[] = "aaa,bbbaaasdassvvv";
//    char dest[1024];
//    memset(dest,0,1024);
//    strrpc(dest,str,"aaa","bbb");
//    cout<<dest;
//
//    int mun =  Str_FirFind("aaa,bbb,vvvcccedfsadfsdfsdfsdfAFAFSDGDGSA","bbb");
//    cout<<mun<<endl;
//
//    return 0;
//}
