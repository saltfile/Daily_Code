#include "selector/sele_base.h"

int main(int argc,const char* argv[]) {
    printf("service_start\n");
    ser_selector_start("192.168.10.105","8484");
    return 0;
}
