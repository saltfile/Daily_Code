//
// Created by saltfish on 2022/9/8.
//
#include "basic_func.h"
//这个例子是使用互斥锁的例子

int num = 0;
pthread_mutex_t mutex;
void *A(void *arg){
    for (int i = 0; i < 100; ++i) {
        pthread_mutex_lock(&mutex);
        int cur = num;
        cur++;
        num = cur;
        cout<<"线程A 目前获取到的值:"<<num<<endl;
        pthread_mutex_unlock(&mutex);
        usleep(15);
    }

    pthread_exit(NULL);
}

void *B(void *arg){
    for (int i = 0; i < 100; ++i) {
        pthread_mutex_lock(&mutex);
        int cur = num;
        cur++;
        num = cur;
        cout<<"线程B 目前获取到的值:"<<num<<endl;
        pthread_mutex_unlock(&mutex);
        usleep(5);
    }
    pthread_exit(NULL);
}



int expampe4_main(){
    pthread_t As;
    pthread_t Bs;
    pthread_mutex_init(&mutex,NULL);
    pthread_create(&As,NULL,A,NULL);
    pthread_create(&Bs,NULL,B,NULL);
    pthread_join(As,NULL);
    pthread_join(Bs,NULL);
    cout<<num;
    pthread_mutex_destroy(&mutex);
//    pthread_exit(NULL);
    return 0;
}

