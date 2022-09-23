//
// Created by saltfish on 2022/9/23.
//

#include "basic_function.h"

void pool_exit(thead_pool *pool){
    pthread_t tid = pthread_self();
    for (int i = 0; i < pool->max_size; ++i) {
        if (pool->works[i] == tid){
            pool->works[i] = 0;
            cout<<"线程"<<tid<<"退出"<<endl;
        }
    }
    pthread_exit(NULL);
}



