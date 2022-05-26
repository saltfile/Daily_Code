//
// Created by saltfish on 2022/5/5.
//

#ifndef OSHOMEWORK_MYALL_H
#define OSHOMEWORK_MYALL_H

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
#include <ctime>
using namespace std;
//任务程序
typedef struct processType {
    int pro_id;//进程号
    time_t submittime;//到达时间 进入队列时间
    int servicetime;//要求时间
    time_t starttime;//开始时间
    time_t endtime;
    int roundtime;//周转时间
    int wRoundTime;//带权周转时间
}processType;



void demo3();

//打印日志功能
void log_info(string str);
void log_info(char *data);
void log_erro(char *data);
void log_erro(string data);
void log_debug(char *data);
void log_info(time_t time2);

//任务链表功能
typedef struct list{
    struct processType *tree;//字符串
    struct list *prev;//后继
    struct list *next;//前驱
}list;
//添加任务
void add_list(list *root,int id,int servicetime);
//展示所有任务程序
void dis_play(list *root);
//任务队列长度
int get_list_size(list *root);
//返回任务
processType *get_list_node(list *root,int len);
//产出节点
list *remove_node(list *root,int len);

void task_run(processType *node);


int bankrun();





















#endif //OSHOMEWORK_MYALL_H
