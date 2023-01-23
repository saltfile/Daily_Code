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
#include <fstream>
#include <stdexcept>
using namespace std;
typedef unsigned char byte;
typedef void* (*cpu_fun)(void* arg1,void* arg2);

#define Bus_len 150

#define MDR_SIZE 16
//日志
void log_debug(char *data);
//转二进制
string to_binary(int arg);
int to_number(string arg);


// 加 减 乘 除
string add(string arg1,string arg2);

string red(string arg1,string arg2);

string mul(string arg1,string arg2);

string divs(string arg1,string arg2);



//复制 被拷贝的 拷贝到的
void * copys(void *arg1,void* arg2);

//赋值
string vols(string *arg1,string arg2);

//释放
string frees(string *arg,string arg2);





//左零右一

class command_tree{

public:
    command_tree *left;
    command_tree *right;
    cpu_fun fun_run;

    command_tree(){
        left = nullptr;
        right = nullptr;
        fun_run = nullptr;
    }
};
//构建编码树
command_tree *build_tree(command_tree *root,string commad,cpu_fun);
//查找对应功能
cpu_fun select_tree(command_tree *root,string commad);

class CPU{
public:
    int code_lens;//程序计数器
    //总线
    string Bus[Bus_len];
    string MDR[MDR_SIZE];
    command_tree *tree;
    CPU(){
        tree = new command_tree();
        for (int i = 0; i < Bus_len; ++i) {
            Bus[i] = "";
        }
        for (int i = 0; i < MDR_SIZE; ++i) {
            MDR[i] = "00000000";
        }
        //加
        tree = build_tree(tree, "10000000", (cpu_fun)add);
        //减
        tree = build_tree(tree,"01000000",(cpu_fun) red);
        //乘
        tree = build_tree(tree,"00100000",(cpu_fun)mul);
        //除
        tree = build_tree(tree,"00010000",(cpu_fun)divs);

        //寄存器拷贝
        tree = build_tree(tree,"11111000",(cpu_fun) copys);
        //寄存器赋值
        tree = build_tree(tree,"11111100",(cpu_fun) vols);
        //寄存器释放
        tree = build_tree(tree,"11111110",(cpu_fun) frees);
        code_lens = 0;
    }

    void loading_binary();



    void run();


};




string commod_binary(string commad);
void compile();





//拷贝
void loading_code(char * path);




//字符串拆分
char **split(char *str,char *dent);
int spilt_size(char *a,char *b);

vector<string> split_str(const string& str,const string& delim);







class CommadNotFound :public exception//自定义错误类
{
public:
    string error = ":";
    CommadNotFound(string s)
    {
        error = s;
    }

    virtual const char* what()const throw()
    {
        return  (error.c_str());
    }


};

class InstructionRepetition : public exception
{
public:
    InstructionRepetition(string s)
    {
        error = s;
    }

    virtual const char* what()const throw()
    {
        return  (error.c_str());
    }

    string error;
};








#endif //CPU_PUB_REFERENCES_H
