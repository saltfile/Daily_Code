//
// Created by saltfish on 2022/9/7.
//

#include "basic_function.h"

thead_pool *pool_init(int work_num,int min,int queue_size){
    thead_pool *pool = (thread_pool *)malloc(sizeof(thread_pool));
    memset(pool,0,sizeof(thread_pool));
    if (pool == NULL){
        perror("pool创建失败");
        return NULL;
    }

    pool->max_size = work_num;
    pool->min_size = min;
    pool->live_num = min;
    pool->working_num = 0;
    pool->kill_num = 0;

    pool->works = (pthread_t*)malloc(sizeof(pthread_t)*work_num);
    memset(pool->works,0,sizeof(pthread_t)*work_num);
    if (pool->works == NULL){
        perror("工作线程组创建失败");
        return NULL;
    }

    pool->tasks = (task *)malloc(sizeof(task)*queue_size);
    memset(pool->tasks,0,sizeof(task)*queue_size);
    if (pool->tasks == NULL){
        perror("创建任务队列错误");
        return NULL;
    }
    pool->capacity = queue_size;
    pool->current = 0;
     



    return NULL;
}













