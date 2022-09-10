//
// Created by saltfish on 2022/9/9.
//
#include "basic_func.h"

int rw_data = 0;
pthread_rwlock_t rwlock;
void *write(void *arg){
    for (int i = 0; i < 100; ++i) {
      pthread_rwlock_wrlock(&rwlock);
        int cur = rw_data;

        cur++;
        rw_data = cur;
        cout<<"写线程改变为"<<rw_data<<endl;
        pthread_rwlock_unlock(&rwlock);
        usleep(10);
    }

    pthread_exit(NULL);
}

void *read(void *arg){
    for (int i = 0; i < 100; ++i) {

        pthread_rwlock_rdlock(&rwlock);
        cout<<"读线程获取到的值:"<<rw_data<<endl;
        pthread_rwlock_unlock(&rwlock);
        usleep(rand()%15);
    }
    pthread_exit(NULL);
}


int expampe6_main(){
    pthread_t Ws[2],Rs[5];
    pthread_rwlock_init(&rwlock,NULL);

    for(int i =0;i < 2;i++){
        pthread_create(&Ws[i],NULL,write,NULL);
    }
    for (int i = 0; i < 5; i++) {
        pthread_create(&Rs[i],NULL,read,NULL);
    }
    for(int i =0;i < 2;i++){
        pthread_join(Ws[i],NULL);
    }
    for (int i = 0; i < 5; i++) {
        pthread_join(Rs[i],NULL);
    }
    pthread_rwlock_destroy(&rwlock);
    return 0;
}