//
// Created by saltfish on 2022/7/15.
//
#include "pub_references.h"
#define MEM_SIZE 5     //多少mb
#define MEM_SIZE 1024

static char memery[1024];
static char *sp;
//初始化内存
//void mem_inits()
//{
//    memery = (char *)malloc(sizeof(MEM_SIZE));
//    memset(memery,0,sizeof(memery));
//    sp = memery;
//}


//空闲链表分配   从下标0起到8分别是2~512
static list *link_list[9];
//内存使用链表
static list *busy_list[9];

/**
 * 初始化空闲链表
 */
void mem_list_distr(){
int len = 0;
char *head = memery;//从0开始

    for (int i = 0; i < 2; ++i) {
        list *list = list_Init(1<<1);
        list->ptr = head;
        list->head = len;
        if (link_list[0] == NULL)
            link_list[0] = list;
        else
            add_list(link_list[0],head,1<<1,len);
        head+=(1<<i);
        len+=(1<<1);
    }
for(int i = 2;i < 10;i++){
    list *list = list_Init(1<<i);
    list->ptr = head;
    list->head = len;
    link_list[i-1] = list;
    head+=(1<<i);
    len+=(1<<i);
}

}


void test_assignment(){
    char str[2] = {'a','b'};
    int p = link_list[0]->head;
    for (int i = 0; i < link_list[0]->len; ++i) {
        memery[p] = str[i];
        p++;
    }
}




char *test_obtain(){
    char *p = link_list[0]->ptr;
    char *str = (char *)malloc(sizeof(link_list[0]->len));
    for (int i = 0; i < link_list[0]->len; ++i) {
        str[i] = (char)*p;
        p+=1;
    }
    return str;
}






