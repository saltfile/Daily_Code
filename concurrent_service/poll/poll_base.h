//
// Created by saltfish on 22-12-10.
//

#ifndef CONCURRENT_SERVICE_POLL_BASE_H
#define CONCURRENT_SERVICE_POLL_BASE_H
#include <stdio.h>
#include <ctype.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <string.h>
#include <arpa/inet.h>
#include <sys/select.h>
#include <poll.h>
#include <iostream>
using namespace std;





int ser_poll_start(const char* ip,const char* port);
#endif //CONCURRENT_SERVICE_POLL_BASE_H
