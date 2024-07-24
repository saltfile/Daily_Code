#include "my_home.h"
#include <iostream>
#include <vector>
#include <utility>
#include <boost/shared_ptr.hpp>
// 定义时间事件
class TimeEvent {
public:
    int time; // 事件发生的时间
    std::string event; // 事件的描述

    TimeEvent(int t, const std::string& e) : time(t), event(e) {}
};

// 时间轮算法的实现
class TimerWheel {
public:
    int wheelSize; // 时间轮的大小
    std::vector<std::vector<TimeEvent>> buckets; // 时间轮的桶


    TimerWheel(int size) : wheelSize(size), buckets(size) {}

    // 添加事件
    void addEvent(int time, const std::string& event) {
        TimeEvent evt(time, event);
        int bucketIndex = time % wheelSize;
        buckets[bucketIndex].push_back(evt);
    }

    // 处理当前时间点的事件
    void process(int time) {
        while (!buckets[time % wheelSize].empty()) {
            TimeEvent evt = buckets[time % wheelSize].front();
            if (evt.time > time) break; // 如果事件时间大于当前时间，则停止处理
            std::cout << "处理事件: " << evt.event << std::endl;
            buckets[time % wheelSize].erase(buckets[time % wheelSize].begin());
        }
    }
};

int sharedptr2()
{
    boost::shared_ptr<int> i1(new int(1));
    cout<<i1.get()<<endl;
    boost::shared_ptr<int> i2(i1);
    *i2=2;
    cout<<i1.get()<<endl;
    cout<<i2.get()<<endl;
    i1.reset(new int(3));
    cout<<i1.get()<<endl;
    cout<<i2.get()<<endl;


    unique_ptr<int> n1(new int (1));
    cout<<n1.get()<<endl;
    cout<<i1.get()<<endl;
    cout<<i2.get()<<endl;
    return 0;
}
class psr{
public:
    int ass;
    char *arr;
    psr(int ass,char *arr){
        this->ass = ass;
        this->arr = arr;
    }
    ~psr(){
        cout<<"被释放了"<<endl;
    }
};

int sharedptr1()
{
    vector<boost::shared_ptr<psr> > v;
    v.push_back(boost::shared_ptr<psr>(new psr(1,"aaa")));
    v.push_back(boost::shared_ptr<psr>(new psr(2,"bbb")));
    vector<boost::shared_ptr<psr>>::iterator iter;
    for (iter=v.begin();iter!=v.end();iter++){
        cout<<*iter->get()->arr<<" ";
    }
    return 0;
}

int main() {
//    cout<<get_num_sum(10);
//    long long push;
//    cin >> push;
//    if (push >= 10){
//        cout<<get_num_sum(push);
//    }
//    TimerWheel timerWheel(8); // 假设时间轮大小为8
//    timerWheel.addEvent(3, "事件A");
//    timerWheel.addEvent(10, "事件B");
//    timerWheel.addEvent(7, "事件C");

    // 处理到时间点5
//    timerWheel.process(5);
//    int a ;
//    scanf("%d",&a);

//sharedptr2();
sharedptr1();

























//    pers *p = new pers();
//    p->id = 1;
//    delete p;
//    cout<<p<<endl;
//    p = NULL;
//    cout<<p<<endl;



//  array_part_main();
//RBTreedemo();
//btreemain();
    return 0;
}
