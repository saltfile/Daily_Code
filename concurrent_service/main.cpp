#include "selector/sele_base.h"

int main(int argc,const char* argv[]) {
    printf("service_start\n");
    ser_selector_start(argv[1],argv[2]);
    return 0;
}
