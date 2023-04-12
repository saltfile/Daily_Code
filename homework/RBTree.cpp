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
RBNode *fix_insert(RBNode *node){
    RBNode *parent, *gparent;

    // 若“父节点存在，并且父节点的颜色是红色”
    while (((parent = get_parent(node)) != NULL) && is_red(parent)){
        gparent = get_parent(parent);
        //若“父节点”是“祖父节点的左孩子”
        if (parent == gparent->left){
            //1.父节点为红色且 叔叔节点parent.right不为空
            RBNode *uncle = gparent->right;
            //如果叔叔是红色，就将父节点和叔叔节点置为黑色；将祖父节点置为红色
            if (uncle!=NULL&& is_red(uncle)){
                set_red(gparent);
                set_black(parent);
                set_black(uncle);
                node = gparent;
                continue;
//                return node;
            }


            //当前右边节点不平衡
            if (parent->right == node){
                RBNode *tmp;
               parent = left(parent);
               tmp = parent;
               parent = node;
               node = tmp;
            }
            set_black(parent);
            set_red(gparent);
            gparent = right(gparent);
        } else{
            RBNode *uncle = gparent->left;
            if (uncle!=NULL&& is_red(uncle)){
                set_red(gparent);
                set_black(parent);
                set_black(uncle);
                node = gparent;
                continue;
            }
            if (parent->left == node){
                RBNode *tmp;
                parent = right(parent);
                tmp = parent;
                parent = node;
                node = tmp;
            }
            set_black(parent);
            set_red(gparent);
            gparent =left(gparent);
        }
    }
    return node;
}

RBNode *insert(int data,RBNode *root){
    RBNode *node = (RBNode*) malloc(sizeof(RBNode));
    memset(node,0, sizeof(node));
    node->data = data;
    node->color = black;
    if (root == NULL)return node;
    int cmp;

    RBNode *y = NULL;
    RBNode *x = root;
    while (x != NULL){
        y = x;
        if (node->data < x->data){
            x = x->left;
        } else{
            x = x->right;
        }
    }
        node->parent = y;
        if (y != NULL){
            if (node->data < y->data)
                y->left = node;
            else
                y->right = node;
        } else{
            root = node;
        }
        node->color = red;

    x = fix_insert(node);
    while (x->parent != NULL){
        x = x->parent;
    }
    x->color = black;

//    set_black(x);
    return x;
    }
































int RBTreedemo() {
    RBNode *root = NULL;


    root = insert(3,root);
    root = insert(1,root);
    root = insert(2,root);
    cout<< sizeof(int)<<endl;

    return 0;
}













