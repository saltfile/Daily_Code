#include "pub_references.h"
/**
 * 一个虚拟的cpu　要求指令有读取内存到寄存器和将寄存器信息读取到内存
 * @return
 */
int main() {
    mem_inits();
    int a = 11;
    char* isd = (char *)&a;
    char* fir =  mem_in_data(isd,sizeof(a));
    int *data = (int *)mem_out_data(fir, 4);
    int das = *data;
    cout<<das;

    return 0;
}
