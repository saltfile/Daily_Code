//
// Created by saltfish on 23-1-12.
//

#include "pub_references.h"

string to_binary(int arg){
    string res;
    for (;arg;arg /= 2) {
        if (arg % 2 == 0)
            res += '0';
        else
            res += '1';
        if (res.size() == 8) {
            break;
        }
    }
    return res;
}

int to_number(string arg){

    int num=0;
    for(int i=arg.size()-1;i>=0;i--){
        num<<=1;
        num+=arg[i]-'0';
    }
    return num;
}





string add(string arg1,string arg2){
    return "";
}














