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
//日志
void log_info(string str);
void log_info(char *data);
void log_erro(char *data);
void log_erro(string data);
void log_debug(char *data);


//内存块空闲链表













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


/**
 * 内存链表
 */
typedef struct list{
    //内存首地址
    char *ptr;
    //内存长度
    int len;
    //内存首地址数值
    int head;
    struct list *prev;//后继
    struct list *next;//前驱
}list;
//链表
list *list_Init();
list *list_Init(int len);
void test_assignment();
void add_list(list *root,char *ptr,int len,int head);
void dis_play(list *root);
list *remove_node(list *root,int len);
char *test_obtain();





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



//GC
void mem_list_distr();
















#endif //CPU_PUB_REFERENCES_H
