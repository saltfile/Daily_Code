//
// Created by saltfish on 22-12-14.
//

#ifndef CONCURRENT_SERVICE_EPOLL_BASE_H
#define CONCURRENT_SERVICE_EPOLL_BASE_H
#include <stdio.h>
#include <ctype.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <string.h>
#include <arpa/inet.h>
#include <sys/select.h>
#include <sys/epoll.h>
#include <poll.h>
#include <iostream>
using namespace std;

int ser_epoll_lt_start(const char* ip,const char* port);

int ser_epoll_et_start(const char* ip,const char* port);

#endif //CONCURRENT_SERVICE_EPOLL_BASE_H
