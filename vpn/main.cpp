
#include "MyAll.h"
/**
 * std::future它表示存储着一个未来会被初始化的变量。
 * 这个变量可以通过std::future提供的成员函数std::future::get()来得到。
 * 如果在这个变量被赋值之前就有别的线程试图通过std::future::get()获取这个变量，那么这个线程将会被阻塞到这个变量可以获取为止。
 * std::promise对象都有一个与之关联的std::future对象。
 * 当std::promise设置值的时候，这个值就会赋给std::future中的对象了
 * @param x
 * @param y
 * @param promiseObj
 */
void func2(int x, int y,promise<char *> &promiseObj) {
    char *res = "";
    if(x == 1){
        res = "aaa";
    }
    if (x!= 1){
        res = "bbb";
    }
    promiseObj.set_value(res);
}


//
//int main()
//{
//char *str = "localhost";
//thread t(cil_start,str,8686);
//t.join();


#define PORT 8181	//端口号
//#define BACKLOG 5	//最大监听数
//thread_local char *as = (char *)malloc(sizeof(1024));
int main()
{

//    promise<char *> promiseObj2;
//    future<char *> futureObj2 = promiseObj2.get_future();
//        thread t(ser_start,8484);
//        t.join();
        char *sss = "GET /VpnLab_war_exploded/indeax.html HTTP/1.1\n"
                    "Host: localhost:8484\n"
                    "Connection: keep-alive\n"
                    "sec-ch-ua: \" Not A;Brand\";v=\"99\", \"Chromium\";v=\"101\", \"Google Chrome\";v=\"101\"\n"
                    "sec-ch-ua-mobile: ?0\n"
                    "sec-ch-ua-platform: \"Linux\"\n"
                    "Upgrade-Insecure-Requests: 1\n"
                    "User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.54 Safari/537.36\n"
                    "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\n"
                    "Sec-Fetch-Site: none\n"
                    "Sec-Fetch-Mode: navigate\n"
                    "Sec-Fetch-User: ?1\n"
                    "Sec-Fetch-Dest: document\n"
                    "Accept-Encoding: gzip, deflate, br\n"
                    "Accept-Language: zh-CN,zh;q=0.9\n"
                    "Cookie: Idea-3571c926=a28cacb7-6273-4465-a8cb-550f550417e7";
        char dest[1024];
        memset(dest,0,1024);
        char *port = "7878";
        char *host = "localhost:";
        host = str_merge(host,port);
        strrpc(dest,sss,"localhost:8484",host);
        cout<<dest<<endl;
        cout<<strlen(dest)<<endl;


//char *sum = futureObj2.get();
//cout<<"传回来的值"<<sum;

////计算（a+b）/(x+y)
//    //用三个线程，一个线程计算a+b，另一个线程计算x+y
//    int a, b, x, y;
//    a = 1, b = 8, x = 2, y = 4;
//
//    char * sum1 = NULL;
//    char *sum2 = NULL;
//    //声明一个类
//    std::promise<char *> promiseObj;
//    //将future和promise关联
//    std::future<char *> futureObj = promiseObj.get_future();
//    //模板传参的时候使用ref，否则传参失败
//    std::thread t1(func2, a, b, ref(promiseObj));
//    t1.join();
//    //获取值
//    sum1 = str_copy(sum1,futureObj.get());
//
//    std::cout << "sum1=" << sum1 << std::endl;
//
//    //不能直接复用上面的future和promise
//    promise<char *> promiseObj2;
//    future<char *> futureObj2 = promiseObj2.get_future();
//
//    std::thread t2(func2, x, y, ref(promiseObj2));
//    t2.join();
//    sum2 = str_copy(sum2,futureObj2.get());
//    std::cout << "sum2=" << sum2 << std::endl;


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
