//
// Created by saltfish on 2022/5/4.
//

#ifndef ELSDEMO_MYANY_H
#define ELSDEMO_MYANY_H

#include <stdio.h>
#include <stdlib.h>
#include <stdarg.h>
#include <cstring>
#include <iostream>
#include <string.h>
#include <cstring>
#include <unistd.h>
#include <pthread.h>
#include <sched.h>
#include <typeinfo>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <thread>
#include <chrono>
#include <random>
#include <exception>
#include <mysql/mysql.h>
using namespace std;

#define CONFIGPATH "/opt/Cpro/elsdemo/appliction.properties"
typedef struct list{
    char *tree;//字符串
    int strlen;//字符串长短
    struct list *prev;//后继
    struct list *next;//前驱
}list;
void log_info(string str);
void log_info(char *data);
void log_erro(char *data);
void log_erro(string data);
void log_debug(char *data);
void read();
//获取文件行数
int get_column(FILE *fp);
//获取文件字节数
long get_bytenumber(FILE *fp);
//获取每一行数据
list * get_filedata(FILE *fp);
//字符串复制
char * str_copy(char *str,char *arr);
//字符串分割
list *split(char *str,char *dent);
//字符串链表函数
void add_list(list *root,char *node);

void dis_play(list *root);
int get_list_size(list *root);
char *get_list_node(list *root,int len);
list *remove_node(list *root,int len);



int sql_connect();












#endif //ELSDEMO_MYANY_H
