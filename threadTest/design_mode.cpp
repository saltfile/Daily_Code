//
// Created by saltfish on 2022/10/27.
//
#include <assert.h>
#include "basic_func.h"



/**
 * 单例模式：
 * 保证永远都创造一个实例，获得的实例永远否是只有一个
 * 只提供一个访问该实例的接口
 * @return 获得创建的实例
 */
single_utils* Singleton_mode(){
static single_utils *p = NULL;
if (p!=NULL)
    return p;
p = (single_utils*)malloc(sizeof(single_utils));
memset(p,0,sizeof(single_utils));
assert(p);
    return p;
}





int design_mode_main(){

    single_utils *utils = Singleton_mode();
    utils->id = 124;
    single_utils *p = Singleton_mode();
    cout<<p->id;






    return 0;
}