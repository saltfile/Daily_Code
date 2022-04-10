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






#endif //HOMEWORK_MY_HOME_H
