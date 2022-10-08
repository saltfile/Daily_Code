//
// Created by saltfish on 2022/10/8.
//

#include "../my_bases.h"

u8 grammer_check(char* mes){
int mes_klen = spilt_size(mes," ");
char** arrs = split(mes," ");
    for (int i = 0; i <= mes_klen; ++i) {
        cout<<arrs[i]<<endl;
    }

    if (strcmp(arrs[0],"use")==0){
        return USE_DATABASE;
    }





    return 0;

}









