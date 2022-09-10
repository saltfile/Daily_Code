//
// Created by saltfish on 2022/9/8.
//

#ifndef THREADTEST_BASIC_FUNC_H
#define THREADTEST_BASIC_FUNC_H
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <cstring>
#include <pthread.h>
#include <zconf.h>
#include <sys/types.h>
#include <sys/stat.h>
using namespace std;


//例子1
int expampe1_main();
//例子2 线程分离
int expampe2_main();
//例子3 主线程在线程中杀死另一个线程
int expampe3_main();
//例子4 线程并发练习 互斥锁
int expampe4_main();
//例子5 线程死锁的两种方式
int expampe5_main();
//例子6 线程读写锁例子
int expampe6_main();
//例子7 生产者消费者模型
int expampe7_main();
#endif //THREADTEST_BASIC_FUNC_H
