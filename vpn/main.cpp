
#include "MyAll.h"
/**
 * std::future它表示存储着一个未来会被初始化的变量。
 * 这个变量可以通过std::future提供的成员函数std::future::get()来得到。
 * 如果在这个变量被赋值之前就有别的线程试图通过std::future::get()获取这个变量，那么这个线程将会被阻塞到这个变量可以获取为止。
 * std::promise对象都有一个与之关联的std::future对象。
 * 当std::promise设置值的时候，这个值就会赋给std::future中的对象了
 * @param x
 * @param y
 * @param promiseObj
 */


int main() {

    thread t(ser_start, PORT);
    t.join();
    return 0;
}
