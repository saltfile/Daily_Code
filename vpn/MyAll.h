//
// Created by saltfish on 2022/5/10.
//

#ifndef VPN_MYALL_H
#define VPN_MYALL_H
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


#include <string.h>
#include <arpa/inet.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netinet/ip.h>
#include <future>

#include <exception>
using namespace std;
//日志输出轮子
void log_info(string str);
void log_info(char *data);
void log_erro(char *data);
void log_erro(string data);
void log_debug(char *data);

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

int Str_FirFind(char *str_target,char *str_use);

int cil_start(char *host,int port,promise<char *> &promiseObj);

int ser_start(int port);

void rec_runtable(int new_fd, promise<int> &promiseObj, promise<char *> &promiseObj2);

void send_runtable(int new_fd,char *buf);

int server_main(int new_fd);



#endif //VPN_MYALL_H
