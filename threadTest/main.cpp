
#include "basic_func.h"
//#include <stdio.h>


#include <vector>
#include <queue>
#include <thread>
#include <mutex>
#include <condition_variable>
#include <functional>
#include <stdexcept>

class ThreadPool {
public:
    ThreadPool(size_t threads) : stop(false) {
        for(size_t i = 0; i < threads; ++i) {
            workerThreads.emplace_back(
                    [this] {
                        while(true) {
                            std::function<void()> task;

                            {
                                std::unique_lock<std::mutex> lock(this->queueMutex);
                                this->condition.wait(lock,
                                                     [this]{ return this->stop || !this->tasks.empty(); });
                                if(this->stop && this->tasks.empty())
                                    return;
                                task = std::move(this->tasks.front());
                                this->tasks.pop();
                            }

                            task();
                        }
                    }
            );
        }
    }

    template<class F, class... Args>
    void enqueue(F&& f, Args&&... args) {
        auto task = std::bind(std::forward<F>(f), std::forward<Args>(args)...);

        {
            std::unique_lock<std::mutex> lock(queueMutex);
            if(stop)
                throw std::runtime_error("enqueue on stopped ThreadPool");

            tasks.emplace(task);
        }
        condition.notify_one();
    }
    void set_stop(bool stop){
        this->stop = stop;
    }

    ~ThreadPool() {
        {
            std::unique_lock<std::mutex> lock(queueMutex);
//            stop = true;
        }
        condition.notify_all();
        for(std::thread &worker : workerThreads)
            worker.join();
    }

private:
    std::vector<std::thread> workerThreads;
    std::queue<std::function<void()>> tasks;

    std::mutex queueMutex;
    std::condition_variable condition;
    bool stop;
};

bool check(int n,int fgh){
    int arr[10] = {'a','a','a','a','a','a','a','a','a','a'};
    int temp = 0;
    for (int i = 0; i < 5; ++i) {
        temp = n%10;
        arr[temp] = temp;
        n=n/10;
    }
    for (int i = 0; i < 5; ++i) {
        temp = fgh%10;
        arr[temp] = temp;
        fgh = fgh/10;
    }
    for (int i = 0; i < 10; ++i) {
        if (arr[i] != i)return false;
    }
    return true;


}


void aa(int &a,int &b){
    a = 2;
    b = 3;
}







int main() {
//int n = 0;
//int max = 0;
//int min = 0;
//    for (int i = 0; i < 10; ++i) {
//        scanf("%d",&n);
//        if (max == 0&&min == 0){
//            max = n;
//            min = n;
//            continue;
//        }
//        max = n > max?n : max;
//        min = n < min?n : min;
//    }
//    printf("%d,%d",max,min);




//    design_mode_main();
//    int a = 0;
//    int *c = &a;
//    int *d = &a;
//    cout<<&c<<endl;
//    cout<<&d<<endl;

//    if (check(79546,1283)){
//        cout<<"aaaa"<<endl;
//    }


//时间池测试
//time_test();
    ThreadPool pool(1); // 创建一个有4个线程的线程池

    // 向线程池中添加任务
    pool.enqueue([]{
        sleep(5);
        std::cout << "Hello from thread pool1!" << std::endl; });

    pool.enqueue([]{
        std:cout<<"kaishi1"<<endl;
        sleep(2);
        std::cout << "Hello from thread pool!2" << std::endl; });

    int a = 0;
    scanf("%d",&a);
    if (a == 1){
        pool.set_stop(true);
    }




// expampe1_main();
//expampe2_main();
//expampe3_main();
//expampe4_main();
//expampe5_main();
//expampe6_main();
//expampe7_main();
//expampe8_main();
//    expampe9_main();
//expampe10_main();

























//    int a;
//    scanf("%d",&a);
//    int g = a%10;
//    int s = a/10%10;
//    int b = a/100;
//
//    printf("%d%d%d",g,s,b);


//int f1 = 0,f2 = 1,fn;
//for (int i = 1;i <= 40;i++)
//{
//    fn = f1+f2;
//    f1 = f2;
//    f2 = fn;
//    cout<<f1<<endl;
//}
//
//exit(6);

    return 0;
}
