#include "selector/sele_base.h"
#include "poll/poll_base.h"
#include "epoll/epoll_base.h"
int main(int argc,const char* argv[]) {
    printf("service_start\n");
//    ser_poll_start("127.0.0.1","8989");
//    ser_epoll_lt_start("127.0.0.1","8989");
    ser_epoll_et_start("127.0.0.1","8989");




//    ser_selector_start(argv[1],argv[2]);
    return 0;
}
