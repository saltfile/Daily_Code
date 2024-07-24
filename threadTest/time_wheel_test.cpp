//
// Created by saltfish on 24-5-4.
//

#include "basic_func.h"
#define WHEEL_SIZE 60
int arr[WHEEL_SIZE] = {0};
int wheel_idx = 0;//60秒

typedef struct task{
    void (*func)(void *arg);//被执行函数指针
};


void* time_fun(void *arg){
    while (1){
        cout<<wheel_idx<<" "<<arr[wheel_idx]<<endl;
        sleep(1);
        wheel_idx++;
        if (WHEEL_SIZE == wheel_idx){
            wheel_idx = 0;
        }
    }
    return NULL;
}


void* push_task(void *avg){
    int interval = 3;
    sleep(2);
    int flag = wheel_idx;
    for (int i = flag; i >= 0; i-=interval) {
        arr[i] = 1;
    }
    for (int i = flag+interval; i < WHEEL_SIZE; i+=interval) {
        arr[i] = 1;
    }
    return NULL;
}





int time_test(){
    pthread_t tid;
    pthread_create(&tid,NULL,time_fun,NULL);
    cout<<"time test..."<<endl;
    pthread_t  tid2;
    int avg = 3;
    pthread_create(&tid2,NULL,push_task,NULL);
    pthread_join(tid,NULL);
    return 0;
}





