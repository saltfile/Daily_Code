//
// Created by saltfish on 2022/4/25.
//
#include "my_home.h"

enum colors {
    red,
    black
};


typedef struct RBNode {
    RBNode *left;
    RBNode *right;
    RBNode *parent;
    int data;
    colors color = black;
} RBNode;


RBNode *get_parent(RBNode *node) {
    return node->parent;
}

void set_parent(RBNode *node, RBNode *parent) {
    if (node != NULL) {
        node->parent = parent;
    }
}

void set_color(RBNode *node, colors color) {
    if (node != NULL) {
        node->color = color;
    }
}

void pre_order(RBNode *tree) {
    if (tree != NULL) {
        cout << tree->data << "   ";
        pre_order(tree->left);
        pre_order(tree->right);
    }
}

int is_red(RBNode *node) { return (node != NULL && node->color == red) ? 1 : 0; }

int is_black(RBNode *node) { return (node != NULL && node->color == black) ? 1 : 0; }

void set_red(RBNode *node) { if (node != NULL)node->color = red; }

void set_black(RBNode *node) { if (node != NULL)node->color = black; }

colors get_color(RBNode *node) {
    return node->color;
}
//查找
RBNode *search(RBNode *root, int key) {
    if (root == NULL)return NULL;
    if(root->data > key)return search(root->left,key);
    else if(root->data < key)return search(root->right,key);
    else return root;
}
//查找最小的节点
RBNode *search_min(RBNode *root,int key){
    RBNode *node = root;
    if(node == NULL){
        return NULL;
    }
    while (node->left){
        node = node->left;
    }
    return node;
}
//查找最大的节点
RBNode *search_max(RBNode *root,int key){
    RBNode *node = root;
    if(node != NULL){
        return  NULL;
    }
    while (node->right){
        node = node->right;
    }
    return node;
}



//左旋
RBNode *left(RBNode *root){
    RBNode *node = root;
    RBNode *right = node->right;
    node->right = right->left;
    if(right->left != NULL){
        right->left->parent = node;
    }
    right->parent = node->parent;

    if(node->parent == NULL){

    } else{
        if(node->parent->left == node){
            node->parent->left = right;
        } else{
            node->parent->right = right;
        }
    }
    right->left = node;
    node->parent = right;
    return right;
}
//右旋
RBNode *right(RBNode *root){
    RBNode *node = root;
    RBNode *left = node->left;

    node->left = left->right;

    if(left->right != NULL){
        left->right->parent = node;
    }

    left->parent = node->parent;
    if(node->parent == NULL){

    } else{
        if (node->parent->left == node){
            node->parent->right = left;
        } else{
            node->parent->left = left;
        }
    }

    left->right = node;
    node->parent = left;
}
//插入修正函数
void fix_insert(RBNode *root){

}





















int RBTreedemo() {






    return 0;
}













