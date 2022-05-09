#include "Myany.h"
//truncate table 表名 清空语法


//int main(int argc,char *argv[])   {
//    int fd;
//    off_t offset;
//    fd = creat("/opt/Cpro/elsdemo/sql1.csv", 0777);   //创建一个权限为可读可写可执行的文件"tmp"
//    if(-1 == fd){   //如果出错返回-1
//        perror("creat");
//        return -1;
//    }
//    offset = lseek(fd, 1024 * 1024*64, SEEK_END);  //设置偏移的大小为1024ll*1024ll*1024ll,并偏移到文件尾//部
//    printf("offset = %d\n", offset);
//
//    lseek(fd, 0, SEEK_SET);
//    char *res =  "sdfsdfsdf\n";
//    write(fd,res,strlen(res));  //写空，写1个字节到文件描述符里
//    close(fd);   //关闭文件描述符
//
//    return 0;
//}
#include <fstream>
#include <pthread.h>





void hello(int i){
    for(int i = 0; i < 10; i++){
        cout <<i<<"  "<<  "hello concurrent \n";

    }
}

static int potion = 0;
void fun3(){
    int fd = open("/opt/Cpro/elsdemo/sss.txt", O_RDWR);
   cout<<fd;
    if(-1 == fd){   //如果出错返回-1
        perror("creat");
        return;
    }
    off_t offset;
    offset = lseek(fd, potion, SEEK_SET);
    printf("offset = %d\n", offset);
    char *str = "aaaafsdfsdfs\n";
    write(fd, str, strlen(str));
    potion += strlen(str);
    close(fd);

}




int main(int argc, char* argv[]){
//    thread t(hello,0);
//   t.detach();
//    t.join();
for(int j = 0;j < 5;j++) {
    for (int i = 0; i < 10; i++) {
        thread t(fun3);
        t.join();
    }
    sleep(4);
}

    return 0;

    }

