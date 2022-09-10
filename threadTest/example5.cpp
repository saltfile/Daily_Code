//
// Created by saltfish on 2022/9/8.
//
#include "basic_func.h"

pthread_mutex_t mutexT;

/**
 * 第一种死锁
 * 就一个锁但是第二个线程开启时需要第一个线程放锁
 *
 */


void *DA(void *arg){


    pthread_mutex_lock(&mutexT);

    cout<<"A锁住了"<<endl;

    pthread_mutex_unlock(&mutexT);
    pthread_exit(NULL);
}

void *DB(void *arg){
    pthread_mutex_lock(&mutexT);
    cout<<"B锁住了"<<endl;
    DA(NULL);
    pthread_mutex_unlock(&mutexT);
    pthread_exit(NULL);
}
/**
 * 方法二 : 这是第二种常见方法，C线程需要D线程的资源B，D线程需要C的资源A
 *
 */

pthread_mutex_t mutexA;
pthread_mutex_t mutexB;


void *DC(void *arg){
    pthread_mutex_lock(&mutexB);

    cout<<"C锁住了"<<endl;

    pthread_mutex_unlock(&mutexA);

    pthread_exit(NULL);
}

void *DD(void *arg){
    pthread_mutex_lock(&mutexA);

    cout<<"D锁住了"<<endl;

    pthread_mutex_lock(&mutexB);

    pthread_exit(NULL);
}



int expampe5_main(){
    //方法一
//    pthread_t  As;
//    pthread_t Bs;
//    pthread_create(&As,NULL,DA,NULL);
//    pthread_create(&Bs,NULL,DB,NULL);
//    pthread_join(As,NULL);
//    pthread_join(Bs,NULL);
//    pthread_exit(NULL);

//方法二

pthread_t Cs;
pthread_t Ds;
pthread_create(&Cs,NULL,DC,NULL);
pthread_create(&Ds,NULL,DD,NULL);
pthread_join(Cs,NULL);
pthread_join(Ds,NULL);
pthread_exit(NULL);



    return 0;
}



