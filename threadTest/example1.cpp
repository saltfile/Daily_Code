//
// Created by saltfish on 2022/9/8.
//
#include "basic_func.h"
struct Test{
    int data;
};



void* fun1(void *arg){
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



int expampe1_main() {
    pthread_t tid;
    pthread_create(&tid, NULL, fun1,NULL);

    cout<<"主线程id"<<pthread_self<<endl;
    void *ptr;
    pthread_join(tid,&ptr);
    struct Test *p = (struct Test *)ptr;
    cout<<p->data<<endl;


    return 0;
}




