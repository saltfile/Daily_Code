//
// Created by saltfish on 2022/9/8.
//
#include "basic_func.h"
struct Test{
    int data;
};

void *killer(void *arg){
    cout<<"杀手计划5秒内杀死person"<<endl;
    pthread_t pid=(pthread_t)arg;
    cout<<"准备动手"<<endl;
    for (int i = 1; i <= 3; ++i) {
        cout<<endl<<i<<endl;
        sleep(1);
    }
    pthread_cancel(pid);
    pthread_exit(NULL);
}



void* person(void *arg){
    cout<<"目标任务"<<pthread_self()<<"十五秒寿命"<<endl;
    for(int i = 1;i <= 15;i++){
        cout<<"活着"<<i<<"s"<<endl;
        sleep(1);
    }
    struct Test *t = (Test *)malloc(sizeof(Test));
    t->data = 1;
    //传递子线程的值进行函数回收
    pthread_exit(t);

    return NULL;
}


/**
 * 这个例子就是用一条线程去杀死另一条线程
 * 分离线程
 * @return
 */
int expampe3_main() {
    pthread_t tid;
    pthread_create(&tid, NULL, person,NULL);
    pthread_t kid;
    pthread_create(&kid,NULL,killer,(void*)tid);
    cout<<"主线程id"<<pthread_self<<endl;
    pthread_detach(tid);
    pthread_detach(kid);
    pthread_exit(NULL);
    return 0;
}









