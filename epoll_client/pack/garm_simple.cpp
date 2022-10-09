//
// Created by saltfish on 2022/10/8.
//

#include "../my_bases.h"

u8 grammer_check(char* mes){
int mes_klen = spilt_size(mes," ");

char** arrs = split(mes," ");
    if (strcmp(arrs[0],"use")==0){
        return USE_DATABASE;
    }



    return 0;

}









