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





void* add(void* arg1,void* arg2){
    string *a1 = static_cast<std::string*>(arg1);
    string *a2 = static_cast<std::string*>(arg2);

    string a = *a1;
    string b = *a2;
    int num1 = to_number(a);
    int num2 = to_number(b);

    int num3 = num1+num2;
    string res = to_binary(num3);
    a1->clear();
    a1->append(res);
    return nullptr;
}

void* red(void* arg1,void* arg2){
    string *a1 = static_cast<std::string*>(arg1);
    string *a2 = static_cast<std::string*>(arg2);

    string a = *a1;
    string b = *a2;
    int num1 = to_number(a);
    int num2 = to_number(b);

    int num3 = num1-num2;
    string res = to_binary(num3);
    a1->clear();
    a1->append(res);
    return nullptr;
}


void* mul(void* arg1,void* arg2){
    string *a1 = static_cast<std::string*>(arg1);
    string *a2 = static_cast<std::string*>(arg2);

    string a = *a1;
    string b = *a2;
    int num1 = to_number(a);
    int num2 = to_number(b);

    int num3 = num1*num2;
    string res = to_binary(num3);
    a1->clear();
    a1->append(res);
    return nullptr;
}

void* divs(void* arg1,void* arg2){
    string *a1 = static_cast<std::string*>(arg1);
    string *a2 = static_cast<std::string*>(arg2);

    string a = *a1;
    string b = *a2;
    int num1 = to_number(a);
    int num2 = to_number(b);

    int num3 = num1/num2;
    string res = to_binary(num3);
    a1->clear();
    a1->append(res);
    return nullptr;
}



//寄存器拷贝
void *copys(void *arg1,void* arg2){
    string *a = (string *)arg1;
    string *b = (string *)arg2;
    a->clear();
    a->append(b->data());
    return nullptr;
}


void* vols(void *arg1,void* arg2){

    string *a1 = static_cast<std::string*>(arg1);
    string *a2 = static_cast<std::string*>(arg2);

    string num = *a2;
    a1->clear();
    a1->append(num);
    return nullptr;
}

void* frees(void* arg,void* arg2){
    string *a1 = static_cast<std::string*>(arg);
    a1->clear();
    a1->append("00000000");
    return nullptr;
}
