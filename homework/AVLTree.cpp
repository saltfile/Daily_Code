//
// Created by saltfish on 2022/4/24.
//
#include "my_home.h"

typedef struct AVLNode{
    AVLNode *left;
    AVLNode *right;
    int data;
    int hegith;
}AVLNode;
int seizetree = 0;

AVLNode * new_AVLNode(int data){
    AVLNode * root = (AVLNode*) malloc(sizeof(AVLNode));
    memset(root,0, sizeof(AVLNode));
    root->data = data;
    return root;
}



//单旋：左旋转
AVLNode * LL(AVLNode * root){
    AVLNode *gnode = root;
    AVLNode *pnode = root->left;
    gnode->left = pnode->right;
    pnode->right = gnode;
    return pnode;
}

//单旋：右旋转
AVLNode *RR(AVLNode * root){
    AVLNode *gnode = root;
    AVLNode *pnode = root->right;
    gnode->right = pnode->left;
    pnode->left = gnode;
    return pnode;
}





/**
 * 主函数
 */
void TreeMain(){
//    AVLNode *root  = NULL;
AVLNode *g= new_AVLNode(1);
    AVLNode *p= new_AVLNode(2);
    AVLNode *n= new_AVLNode(3);
    AVLNode *t0= new_AVLNode(4);
    AVLNode *t1= new_AVLNode(5);
    AVLNode *t2= new_AVLNode(6);
    AVLNode *t3 = new_AVLNode(7);
    g->left = p;
    g->right = t3;
    p->left = n;
    p->right = t2;
    n->left = t0;
    n->right = t1;

     g = LL(g);


    //LR
//    root = add_node(root,31);
//    root = add_node(root,12);
//    root = add_node(root,34);
//    root = add_node(root,11);
//    root = add_node(root,14);
//    root = add_node(root,13);
//    root = add_node(root,16);
    //RL
//    root = add_node(root,31);
//    root = add_node(root,16);
//    root = add_node(root,44);
//    root = add_node(root,34);
//    root = add_node(root,49);
//    root = add_node(root,32);
//    root = add_node(root,38);
//    AVLNode *st = search_val(root,38);
//    if(st != NULL)
//        cout<<st->data;
//    del_node(root,32);
//    st = search_val(root,38);
//    if(st != NULL)
//        cout<<st->data;
    return;
}






























