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
    struct Node *childen[RANK];
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
    node->parent = NULL;
    return node;
}






//找寻节点插入位置
int find_node(Node *node,int data){
    int index;
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
    int index = find_node(node, data);
    for (int i = node -> key_num; i >= index; i--) {
        node -> keys[i + 1] = node -> keys[i];
    }
    node -> keys[index] = data;
    node -> key_num = node -> key_num + 1;
    if (node -> key_num == node -> level) {
        // 开始分裂，找到中间位置
        int mid = node -> level / 2 + node -> level % 2;
        // 初始化左孩子节点
        Node* lchild = node_init(node -> level);
        // 初始化有孩子节点
        Node* rchild = node_init(node -> level);
        // 将mid左边的值赋值给左孩子
        for (int i = 1; i < mid; i++) {
            add_node(lchild, node -> keys[i], T);
        }
        // 将mid右边的值赋值给右孩子
        for (int i = mid + 1; i <= node -> key_num; i++) {
            add_node(rchild, node -> keys[i], T);
        }
        // 将原先节点mid左边的孩子赋值给分裂出来的左孩子
        for (int i = 0; i < mid; i++) {
            lchild -> childen[i] = node -> childen[i];
            if (node -> childen[i] != NULL) {
                node -> childen[i] -> parent = lchild;
                lchild -> child_num ++;
            }
        }
        // 将原先节点mid右边的孩子赋值给分裂出来的右孩子
        int index = 0;
        for (int i = mid; i < node -> child_num; i++) {
            rchild -> childen[index++] = node -> childen[i];
            if (node -> childen[i] != NULL) {
                node -> childen[i] -> parent = rchild;
                rchild -> child_num ++;
            }
        }
        //判断当前节点是不是根节点
        if (node -> parent == NULL) {
            // 是根节点
            Node* newParent = node_init(node -> level);
            add_node(newParent, node -> keys[mid], T);
            newParent -> childen[0] = lchild;
            newParent -> childen[1] = rchild;
            newParent -> child_num = 2;
            lchild -> parent = newParent;
            rchild -> parent = newParent;
            *T = newParent;
        }
        else {
            // 不是根节点
            int index = find_node(node -> parent, node -> keys[mid]);
            lchild -> parent = node -> parent;
            rchild -> parent = node -> parent;
            node -> parent -> childen[index - 1] = lchild;
            if (node -> parent -> childen[index] != NULL) {
                for (int i = node -> parent -> child_num - 1; i >= index; i--) {
                    node -> parent -> childen[i + 1] = node -> parent -> childen[i];
                }
            }
            node -> parent -> childen[index] = rchild;
            node -> parent -> child_num ++;
            add_node(node -> parent, node -> keys[mid], T);
        }
    }
}
void insert_node(Node **T,int data){
    Node *node = find_leaf_node(*T,data);
    add_node(node,data,T);
}

//打印整棵树
void dis_play(Node *T){
    if(T!=NULL){
        for(int i = 1;i <= T->key_num;i++){
            cout<<T->keys[i]<<"    ";
        }
        cout<<endl;
        for(int i = 0;i < T->child_num;i++){
            dis_play(T->childen[i]);
        }
    }
}



int btreemain(){

    Node* T = node_init(5);
    insert_node(&T,1);
    insert_node(&T,2);
    insert_node(&T,3);
    insert_node(&T,4);
    insert_node(&T,5);
    insert_node(&T,6);
    insert_node(&T,7);
    insert_node(&T,8);
//

    dis_play(T);








    cout<<"我是b树";
    return 0;
}



