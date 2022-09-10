//
// Created by saltfish on 2022/9/9.
//
#include "basic_func.h"

pthread_mutex_t mutex_pc;
pthread_cond_t cond_pc;

typedef struct task_pc{
    int data;
    pthread_t Respon;
    task_pc* next;

}task_pc;

task_pc* head=NULL;


void* product(void* arg){
    while (true){

      pthread_mutex_lock(&mutex_pc);
     task_pc* new_node = (task_pc *)malloc(sizeof(task_pc));
     memset(new_node,0,sizeof(task_pc));
     new_node->data = rand()%10000;
     new_node->Respon = pthread_self();
     new_node->next = head;
     head = new_node;
     printf("生产者id:%ld 生产了 %d 号产品\n",pthread_self(),new_node->data);
        //生产者生产完需要唤醒其他的消费者线程
        //随机唤醒
//     pthread_cond_signal(&cond_pc);

//        全部唤醒
     pthread_cond_broadcast(&cond_pc);
     pthread_mutex_unlock(&mutex_pc);

     sleep(rand()%3);
    }
}

void* consumer(void* arg){
    while (true){
        pthread_mutex_lock(&mutex_pc);
        while (head==NULL){
            pthread_cond_wait(&cond_pc,&mutex_pc);
        }
        task_pc* obtain = head;
        head = head->next;
        printf("消费者 id:%ld 拿到了 %ld生产的 %d 号产品\n",pthread_self(),obtain->Respon,obtain->data);
        free(obtain);
        pthread_mutex_unlock(&mutex_pc);
        sleep(rand()%5);
    }
}


int expampe7_main(){
    pthread_mutex_init(&mutex_pc,NULL);
    pthread_cond_init(&cond_pc,NULL);
    pthread_t Ps[3],Cs[5];
    for(int i = 0;i < 3;i++){
        pthread_create(&Ps[i],NULL,product,NULL);
    }
    for (int i = 0; i < 5; ++i) {
        pthread_create(&Cs[i],NULL,consumer,NULL);
    }

    for (int i = 0; i < 3; ++i) {
        pthread_join(Ps[i],NULL);
    }

    for (int i = 0; i < 5; ++i) {
        pthread_join(Cs[i],NULL);
    }

    pthread_mutex_destroy(&mutex_pc);
    pthread_cond_destroy(&cond_pc);
}









