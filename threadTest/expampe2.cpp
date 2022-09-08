//
// Created by saltfish on 2022/9/8.
//
//这个例子表示线程分离
#include "basic_func.h"



struct Test{
    int data;
};



void* fun2(void *arg){
    cout<<"子线程线程id"<<pthread_self()<<endl;
    for(int i = 0;i < 5;i++){
        cout<<"子线程的回复"<<i<<endl;
    }
    struct Test *t = (Test *)malloc(sizeof(Test));
    t->data = 1;
    //传递子线程的值进行函数回收
    pthread_exit(t);

    return NULL;
}


/**
 * 前面的第一个例子就是主线程死了，子线程也会消失
 * 这里要改成分离线程
 * @return
 */
int expampe2_main() {
    pthread_t tid;
    pthread_create(&tid, NULL, fun2,NULL);

    cout<<"主线程id"<<pthread_self<<endl;
    pthread_detach(tid);
    pthread_exit(NULL);

    return 0;
}


