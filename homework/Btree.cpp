//
// Created by saltfish on 2022/6/30.
//
//B树
#include "my_home.h"

/**
 * 父亲指针
 *树的阶数
 * 关键字key数组
 * 孩子数量
 * 孩子数组
 * 关键字数组
 */
#define RANK 5//阶数


typedef struct Node{
    struct Node *parent;
    int level;
    int key_num;
    int child_num;
    int *keys;
    struct Node **childen;
}Node;

//初始化节点
Node *node_init(int level){
    Node* node = (Node *)malloc(sizeof(Node));
    memset(node,0,sizeof(node));
    node->level = level;
    node->key_num = 0;
    node->child_num = 0;
    node->keys = (int *)malloc(sizeof(int)*(level+1));
    memset(node->keys,0,sizeof(node->keys));
    node->childen = (Node **)malloc(sizeof(Node*)*level);
    memset(node->childen,0,sizeof(node->childen));
    node->parent = NULL;
    return node;
}






//找寻节点插入位置
int find_node(Node *node,int data){
    int index = 1;
    for(index = 1;index <= node->key_num;index++){
        if(data < node->keys[index]){
            break;
        }
    }
    return index;
}

//找寻叶子节点
Node *find_leaf_node(Node *T,int data){
    if(T->child_num == 0){
        return T;
    } else{
        int index = find_node(T,data);
        return find_leaf_node(T->childen[index-1],data);
    }
}
//插入
void add_node(Node *node,int data,Node **T){
    //首先寻找合适的索引
    int index = find_node(node,data);
    //找到最后一个索引位置
    for(int i = node->key_num;i <= index;i--){
        node->keys[i+1] = node->keys[i];
    }
    //数组中的值空出来就可以进去赋值了
    node->keys[index] = data;
    node->key_num++;

    //判断是否需要进行分裂
    if(node->key_num == node->level){
        //找到分裂的位置
        int mid = node->level/2+node->level%2;
        Node *lchild = node_init(node->level);
        Node *rchilde = node_init(node->level);
        for(int i = 0;i < mid;i++){
            add_node(lchild,node->keys[i],T);
        }
        for(int i = mid+1;i <= node->key_num;i++){
            add_node(rchilde,node->keys[i],T);
        }
        for(int i = 0;i < mid;i++){
            lchild->childen[i] = node->childen[i];
            if(node->childen[i] != NULL){
                node->childen[i]->parent = lchild;
                lchild->child_num++;
            }
        }
        //这边处理右枝
        index = 0;
        for(int i = mid;i < node->child_num;i++){
            rchilde->childen[index++] = node->childen[i];
            if(node->childen[i] != NULL){
                node->childen[i]->parent = rchilde;
                rchilde->child_num++;
            }
        }
 //如果这个节点有父节点
    if(node->parent == NULL){
        Node *newParent = node_init(node->level);
        add_node(newParent,node->keys[mid],T);
        newParent->childen[0] = lchild;
        newParent->childen[1] = rchilde;
        newParent->key_num = 2;
        lchild->parent = newParent;
        rchilde->parent = newParent;
        *T = newParent;
    } else{
        int index = find_node(node->parent,node->keys[mid]);
        lchild->parent = node->parent;
        rchilde->parent = node->parent;
        node->parent->childen[index-1] = lchild;
        if(node->parent->childen[index] != NULL){
            for(int i = node->parent->child_num-1;i >= index;i--){
                node->parent->childen[i+1] = node->parent->childen[i];
            }
        }
        node->parent->childen[index] = rchilde;
        node->parent->child_num++;
        add_node(node->parent,node->keys[mid],T);
    }
    }



}

























int btreemain(){
    cout<<"我是b树";
    return 0;
}



