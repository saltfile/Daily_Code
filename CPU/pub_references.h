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
#include <typeinfo>
#include <math.h>
using namespace std;
#define MDR_SIZE 16
//寄存器
typedef struct MDR{
    char mem[MDR_SIZE];
    char* ptr;
};

enum MDRs{
    ax,
    bx,
    cx,
    dx
};

typedef struct CPU{
    MDR *ax;
    MDR *bx;
    MDR *cx;
    MDR *dx;
}CPU;



//字符串轮子
char * str_copy(char *str,char *arr);
//分割时用的方法
//获取长度
int spilt_size(char *a,char *b);
//分割字符
char ** split(char *str,char *dent);
//字符合并
char * str_merge(char *str,char * merstr);

char *strrpc(char *dest,char *src,char *before,char *after);



//mem_fun内存操作函数
void mem_inits();
char* mem_in_data(char *data,int len);
char* mem_out_data(char *fir,int len);
char *mem_info_mdr(MDR* m,char *mem,int len);
char * mrd_move_mem(MDR* m);
















#endif //CPU_PUB_REFERENCES_H
