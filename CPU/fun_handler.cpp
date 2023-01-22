//
// Created by saltfish on 23-1-12.
//

#include "pub_references.h"

string to_binary(int arg){
    string res;

    if (arg > 255)return "11111111";


    for (;arg;arg /= 2) {
        if (arg % 2 == 0)
            res += '0';
        else
            res += '1';
        if (res.size() == 8) {
            break;
        }
    }
    //补0
    if (res.size() < 8){
        int ars = 8-res.size();
        for (int i = 0; i < ars; ++i) {
         res+='0';
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
    int num1 = to_number(arg1);
    int num2 = to_number(arg2);

    int num3 = num1+num2;

    return to_binary(num3);
}

string red(string arg1,string arg2){
    int num1 = to_number(arg1);
    int num2 = to_number(arg2);

    int num3 = num1-num2;

    return to_binary(num3);
}

string mul(string arg1,string arg2){
    int num1 = to_number(arg1);
    int num2 = to_number(arg2);

    int num3 = num1*num2;

    return to_binary(num3);
}

string div(string arg1,string arg2){
    int num1 = to_number(arg1);
    int num2 = to_number(arg2);

    int num3 = num1/num2;

    return to_binary(num3);
}



//寄存器拷贝
string copy(string &arg1,string arg2){
    arg1 = arg2;
    return "";
}


string vol(string &arg1,int arg2){
    arg1 = to_binary(arg2);
    return "";
}

string free(string &arg,string arg2){
    arg = "";
    return "";
}
