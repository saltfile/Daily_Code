#include "basic_function.h"

void fun(void* a){
    int* b = (int*)a;
    cout<<"执行任务"<<&b<<endl;
}



int main() {
    thead_pool *p = pool_init(10,3,100);
    for (int i = 0; i < 1; ++i) {
        pool_add_task(p, fun, (void *)(i));
    }

    sleep(50);

    pool_destory(p);



    return 0;
}
