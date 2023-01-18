//
// Created by saltfish on 2022/7/15.
//

#ifndef CPU_PUB_REFERENCES_H
#define CPU_PUB_REFERENCES_H
#include <stdio.h>
#include <stdlib.h>
#include <stdarg.h>
#include <cstring>
#include <iostream>
#include <string.h>
#include <unistd.h>
#include <pthread.h>
#include <sched.h>
#include <vector>
#include <typeinfo>
#include <math.h>
using namespace std;
typedef unsigned char byte;
typedef void* (*cpu_fun)(void* arg1,void* arg2);

#define MDR_SIZE 16
//日志
void log_info(string str);
void log_info(char *data);
void log_erro(char *data);
void log_erro(string data);
void log_debug(char *data);
//转二进制
string to_binary(int arg);
int to_number(string arg);


// 加 减 乘 除
string add(string arg1,string arg2);

string red(string arg1,string arg2);

string mul(string arg1,string arg2);

string div(string arg1,string arg2);



//复制 被拷贝的 拷贝到的
string copy(int arg1,int arg2);

//赋值
string vol(int arg1,int num);

//释放
string free(string &arg);





//左零右一

class command_tree{

public:
    command_tree *left;
    command_tree *right;
    cpu_fun fun_run;
};
//构建编码树
command_tree *build_tree(command_tree *root,string commad,cpu_fun);
//查找对应功能
cpu_fun select_tree(command_tree *root,string commad);









#endif //CPU_PUB_REFERENCES_H
