//
// Created by saltfish on 2022/4/6.
//

#ifndef HOMEWORK_MY_HOME_H
#define HOMEWORK_MY_HOME_H
#include <stdio.h>
#include <stdlib.h>
#include <stdarg.h>
#include <cstring>
#include <iostream>
#include <string.h>
#include <unistd.h>
#include <pthread.h>
#include <sched.h>
#include <typeinfo>
#include <math.h>
using namespace std;
//home1
void init_cache();
void set_stu(int id,char *name);
void set_tea(int id,char N);
void show_all();
//hemo2
typedef struct Link_Node{
    struct Link_Node *prve;
    struct Link_Node *next;
}Link_Node;

Link_Node* init_head(Link_Node *head);
void link_hadd(Link_Node *head,Link_Node *new_Node);
void link_del(Link_Node *head,Link_Node *tail);

typedef struct lab_node{
    Link_Node list;
    int id = 0;
}lab_node;
void demo_explme();



class MyList{
public:
    int *arr;
    int *head;
    int len = 0;
    MyList(int num,...){
        this->arr = (int *)malloc(sizeof(int)*num);
        this->head = this->arr;
        memset(this->arr,0,sizeof(this->arr));
        va_list arg_list;
        va_start(arg_list,num);
        while (num -- > 0){
            arr[len] = va_arg(arg_list,int);
            len++;
        }
    }
    int get(int num){
        return arr[num];
    }
    void dis(){
        for(int i = 0;i < len;i++){
            if(i == len-1){
                cout<<arr[i];
                continue;
            }
            cout<<arr[i]<<",";
        }
    }

    void merge(int newarr[],int lens){
        int *arrs = (int *)malloc(sizeof(int)*(this->len+lens));
        for(int i = 0;i < this->len;i++){
            arrs[i] = this->arr[i];
        }
        for(int i = this->len;i < this->len+lens;i++){
            arrs[i] = newarr[i- this->len];
        }
        this->len = this->len+lens;
        free(head);
        this->arr = arrs;
        this->head = this->arr;
    }


    void undo(){
        arr = arr+1;
        len--;
    }
    ~MyList(){
        cout<<"被删除";
    }
};

void thread_Test();


class Ptr{
public:
    MyList *my;
    Ptr(MyList *m):my(m){
    }
    void dis(){
        my->dis();
    }
    int operator[](int num){
        return my->get(num);
    }
    void operator++(int){
        my->undo();

    }
    int operator*(){
        return my->get(0);
    }
    Ptr operator+(Ptr p1){
        this->my->merge(p1.my->arr,p1.my->len);
        return *this;
    }


};



/**
 * AVLTree
 */
void TreeMain();
/**
 * RBTree
 * @return
 */
int RBTreedemo();


//线段树
int seg_treedemo();


//B树
int btreemain();


//B+树





#endif //HOMEWORK_MY_HOME_H
