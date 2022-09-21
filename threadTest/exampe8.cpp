//
// Created by saltfish on 2022/9/9.
//
#include "basic_func.h"

pthread_mutex_t mutex_s;
sem_t semp;
sem_t semc;
typedef struct sgnal_ts{
    int data;
    pthread_t Respon;
    sgnal_ts* next;

}sgnal_ts;

sgnal_ts* head_s=NULL;


void* product(void* arg){
    while (true){

        sem_wait(&semp);
        pthread_mutex_lock(&mutex_s);
        sgnal_ts* new_node = (sgnal_ts *)malloc(sizeof(sgnal_ts));
        memset(new_node,0,sizeof(sgnal_ts));
        new_node->data = rand()%10000;
        new_node->Respon = pthread_self();
        new_node->next = head_s;
        head_s = new_node;
        printf("生产者id:%ld 生产了 %d 号产品\n",pthread_self(),new_node->data);
        pthread_mutex_unlock(&mutex_s);
        sem_post(&semc);
        sleep(rand()%3);
    }
}

void* consumer(void* arg){
    while (true){
        sem_wait(&semc);
        pthread_mutex_lock(&mutex_s);
        sgnal_ts * obtain = head_s;
        head_s = head_s->next;
        printf("消费者 id:%ld 拿到了 %ld生产的 %d 号产品\n",pthread_self(),obtain->Respon,obtain->data);
        free(obtain);
        pthread_mutex_unlock(&mutex_s);
        sem_post(&semp);
        sleep(rand()%5);
    }
}




int expampe8_main(){
    //生产者
    sem_init(&semp,0,3);
    //消费者
    sem_init(&semc,0,0);
    pthread_mutex_init(&mutex_s,NULL);
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
    pthread_mutex_destroy(&mutex_s);
    sem_destroy(&semc);
    sem_destroy(&semp);

   return 0;
}