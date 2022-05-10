#include "MyAll.h"

int main() {

    char **strs = split("aaaa,bbb,ccc",",");
    char *a = strs[1];
    cout<<strs[0]<<endl;
    cout<<strs[1]<<endl;
    cout<<strs[2]<<endl;

    char *aa = (char *)malloc(sizeof(3));
    aa = "aaa";
    char *bb = (char *)malloc(sizeof(8));
    bb = "bbb";
    aa = str_merge(aa,bb);

    cout<<aa;



    return 0;
}
