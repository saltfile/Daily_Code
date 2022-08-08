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
typedef unsigned char byte;
typedef int (*cpu_fun)(byte *data);

#define MDR_SIZE 16
//日志
void log_info(string str);
void log_info(char *data);
void log_erro(char *data);
void log_erro(string data);
void log_debug(char *data);

#define stack_size 1024
//typedef char* elment_type;
//指令结构体
typedef struct instructions{
    byte *com;
    int com_size;
    byte *param1;
    int par1_size;
    byte *param2;
    int par2_size;
}instructions;




/**
 * 指令栈
 */
typedef struct com_stack{
    char* array[stack_size];
    int capacity;
    int stackTop;//栈顶

}com_stack;





















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
void add_list(list *root,char *ptr,int len,int head);
void dis_play(list *root);
list *remove_node(list *root,int len);
char *test_obtain();




//add命令 == 10010110  加法
//sub命令 == 00010110  减法
//mul命令 == 10001001  乘法
//div命令 == 00001001  除法
//asg命令 == 10000000  赋值寄存器命令
//free命令 == 11100000  清除寄存器内数据
//把寄存器编号变成4位 每个命令执行是8位
int add(byte *regs);
int sub(byte *regs);
int mul(byte *regs);
int div(byte *regs);
int asg(byte *regs);
int fre(byte *regs);//清空寄存器








//寄存器内存16字节大小
class MUR_mem{
    byte data[16];
public:
    MUR_mem(){}
    void copy_data(byte *data);
    void free_data();
    ~MUR_mem();
};
//总线指令
class commond{
    byte *data;
public:
    commond(){}
    void copy_data(byte *data);
    ~commond();
};

class CPU{
public:
    MUR_mem MURS[16];//寄存器
    commond CB[1024];//控制总线


};




//命令编码树左0右1
//
typedef struct code_tree{
    char flag;
    cpu_fun fun;
    code_tree *left;
    code_tree *right;
}code_tree;

code_tree *build_tree();
code_tree *code_add(code_tree *root,char *command,cpu_fun fun);
cpu_fun  code_sel(code_tree *root,byte *command);
code_tree *code_build();
code_tree *create_node();
code_tree *create_node(char flag);
code_tree *create_node(char flag,cpu_fun cpuFun);


//字符串轮子
//字符串相等
bool str_equal(char *str1,char *str2);
//字符串/字节拷贝
char * str_copy(char *str,char *arr);
byte * str_copy(byte *str,byte *arr);
//分割时用的方法
//获取长度
int spilt_size(char *a,char *b);
//分割字符
char ** split(char *str,char *dent);
//字符合并
char * str_merge(char *str,char * merstr);
//字符串部分替换
char *strrpc(char *dest,char *src,char *before,char *after);


//进制转换函数
//十进制转换十六位2进制
byte* dec_to_bin(int number);
int bin_to_dec(byte *data);









//特有byte函数
int bytelen(byte* byt);
//byte版合并函数
byte * byte_merge(byte *str,byte * merstr);


byte *command_conver_byte(char *command);

//拆读取命令的函数   例如`将add R3,R4拆解为三部分10010110    0011寄存器号  0100寄存器号








#endif //CPU_PUB_REFERENCES_H
