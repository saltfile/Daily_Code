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

RBNode *search(RBNode *root, int key) {
    if (root == NULL)return NULL;
    if(root->data > key)return search(root->left,key);
    else if(root->data < key)return search(root->right,key);
    else return root;
}


int RBTreedemo() {

    return 0;
}













