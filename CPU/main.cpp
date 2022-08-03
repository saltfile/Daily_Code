#include "pub_references.h"
/**
 * 一个虚拟的cpu　要求指令有读取内存到寄存器和将寄存器信息读取到内存
 * @return
 */
int main() {
//    char *str =  "sadlkdsfjsl fdsaflsnflsdn dslfkmsldfk";
//    char **strs = split(str," ");
//    int strslen = spilt_size(str," ");
//    for (int i = 0; i < strslen; ++i) {
//        cout<<strs[i]<<endl;
//    }

byte *a = dec_to_bin(11);
    for (int i = 0; i < bytelen(a); ++i) {
        cout<<a[i]<<endl;
    }

cout<<bin_to_dec(a);


    return 0;
}
