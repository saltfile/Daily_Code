//
// Created by saltfish on 2022/4/25.
//
#include "my_home.h"

enum colors{
    red,
    black
};


typedef struct RBNode{
    RBNode *left;
    RBNode *right;
    RBNode *parent;
    int data;
    colors color = black;
}RBNode;


RBNode *get_parent(RBNode *node){
    return node->parent;
}


int is_red(RBNode *node){return (node != NULL && node->color == red)?1:0;}

int is_black(RBNode *node){return (node != NULL && node->color == black)?1:0;}

void set_red(RBNode *node){if(node != NULL)node->color = red;}
void set_black(RBNode *node){if(node != NULL)node->color = black;}

colors get_color(RBNode *node){
    return node->color;
}


int RBTreedemo(){

    return 0;
}













