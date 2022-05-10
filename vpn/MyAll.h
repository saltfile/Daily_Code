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
char ** split(char *str,char *dent);
char * str_merge(char *str,char * merstr);










#endif //VPN_MYALL_H
