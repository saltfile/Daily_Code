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
#include <sys/epoll.h>
#include <fcntl.h>
#include <thread>
#include <chrono>
#include <random>
#include <arpa/inet.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netinet/ip.h>
#include <future>
#include <cstdlib>
#include <boost/thread.hpp>
#include <exception>
using namespace std;
typedef unsigned char u8;
#define PORT 8484

//日志输出轮子
void log_info(string str);
void log_info(char *data);
void log_info(char *logs,int num,...);
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

void cil_start(int port,char *requert,promise<char *> &promiseObj);

int ser_start(int port);

void rec_runtable(int poell,int new_fd, promise<int> &promiseObj, promise<char *> &promiseObj2);
void rec_runtable(int epollfd,int fd,char *buf);

void send_runables(int epollfd,int fd,char *buf);

//int server_main(int new_fd);
int sckt_bind_fun(int port);

void run_epoll(int listenfd);

void add_event(int epollfd,int fd,int state);

void handler_accpet(int epollfd,int listenfd);

int server_main(int epollfd, int new_fd);
char *requs(char *request,int old,int new_);

char *strrpc(char *src,char *before,char *after);

char *strrpc_first(char *src,char *before,char *after);

void add_event(int epollfd,int fd,int state);

void delete_event(int epollfd,int fd,int state);

void modify_event(int epollfd,int fd,int state);

#endif //VPN_MYALL_H
