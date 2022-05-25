//
// Created by saltfish on 2022/5/3.
//
#include "my_home.h"


void * thread_fun(void * val){
    int id = *((int*)val);
    while(1){
        cout << id << endl ;
        sleep(3);
    }
}

void thread_Test(){
    pthread_t tid1 , tid2;
    int id = 22 ;
    int ret = pthread_create(&tid1,NULL,thread_fun,&id);
    int id2 = 33;
    ret = pthread_create(&tid2,NULL,thread_fun,&id2);
    pthread_join(tid1,NULL);
    pthread_join(tid2,NULL);
}









