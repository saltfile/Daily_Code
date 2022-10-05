//
// Created by saltfish on 2022/10/4.
//

#ifndef EPOLL_CLIENT_MY_BASES_H
#define EPOLL_CLIENT_MY_BASES_H
#include <error.h>
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
#include <exception>
#include <assert.h>
using namespace std;

int client_run(int port,char* address);
int buf_send(int sfd,char *buf);
int buf_read(int sfd,char* buf);




#endif //EPOLL_CLIENT_MY_BASES_H
