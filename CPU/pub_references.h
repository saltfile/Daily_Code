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

#define MDR_SIZE 16
//日志
void log_info(string str);
void log_info(char *data);
void log_erro(char *data);
void log_erro(string data);
void log_debug(char *data);


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
//把寄存器编号变成4位 每个命令执行是8位
class CPU{
public:






    int add(byte *regs);
    int sub(byte *regs);
    int mul(byte *regs);
    int div(byte *regs);




};












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











#endif //CPU_PUB_REFERENCES_H
