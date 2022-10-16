//
// Created by saltfish on 2022/10/15.
//

#include "basic_func.h"
/**
 * 此例子是为了探究线程中的fork而实验用的cpp
 */

void fork_fun1(){
    cout<<"我是线程数字等于0"<<endl;
}

void fork_fun2(){
    cout<<"我是线程数字大于0"<<endl;
}
void fork_fun3(){
    cout<<"我是线程数字小于0"<<endl;
}









int expampe9_main(){
//父线程分出一个子线程继续执行以下的函数
    pid_t pid1 = fork();
if (pid1>0){
    fork_fun2();
}
if (pid1 == 0){
    fork_fun1();
}

    cout<<"hello world"<<endl;
    return 0;
}







