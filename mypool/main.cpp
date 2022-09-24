#include "basic_function.h"

void fun(void* a){
    int* b = (int*)a;
    cout<<"执行任务"<<&b<<endl;
}



int main() {
    thead_pool *p = pool_init(10,3,100);
    for (int i = 0; i < 10; ++i) {
        void* arg = &i;
        pool_add_task(p, fun, arg);
        sleep(1);
    }

    sleep(10);

    pool_destory(p);



    return 0;
}
