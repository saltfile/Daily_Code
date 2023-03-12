#include "selector/sele_base.h"
#include "protos/add.pb.h"
#include "poll/poll_base.h"
#include "epoll/epoll_base.h"

string change(char c)

{

    string data;

    for(int i=0;i<8;i++)

    {

        //  data+=c&(0x01<<i);

        if ( ( c >>(i-1) ) & 0x01 == 1 )

        {

            data+="1";

        }

        else

        {

            data+="0";

        }

    }

    for(int a=1;a<5;a++)

    {

        char x=data[a];

        data[a]=data[8-a];

        data[8-a]=x;

    }

    return data;

}

string change1(string data)

{

    string result;



    for(int i=0;i<data.size();i+=8)
    {
        char c='\0';
        for (int j = i; j < i+8; ++j) {
            if(data[j]=='1') c=(c<<1)|1;
            else c=c<<1;
        }
        result+=(unsigned char)c;
    }



    return  result;

}
int main() {
    User user1;
    user1.set_id(100);
    user1.set_nickname("小明");

    //将结构序列化成string
    std::string str = "";
    user1.SerializeToString(&str);
    std::cout << str << std::endl;



//    printf("service_start\n");
//    ser_poll_start("127.0.0.1","8989");
//    ser_epoll_lt_start("127.0.0.1","8989");
//    ser_epoll_et_start("127.0.0.1","8989");

//    ser_selector_start(argv[1],argv[2]);
    return 0;
}
