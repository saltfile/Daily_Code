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
#define RANK 3 //阶数

template<typename T>
class data_node{
public:
    int key;
    T data;
    data_node(int k,T data){
        this->key = k;
        this->data = data;
    }
    data_node(){}
    ~data_node(){}
};
template<typename T>
class BtreeNode{
public:
    int key_num;
    data_node<T> *dataNode[RANK];//值类型
    int prt_num;
    BtreeNode<T> *ptrs[RANK+1];//指针类型
    BtreeNode<T> *preant;
    BtreeNode(){
        this->key_num = 0;
    }
};

template<typename T>
class BTree{
public:
    BtreeNode<T> *root;

    BTree(){
        this->root = new BtreeNode<T>();
    }
    void insert(int key,T data);
    void push_node(BtreeNode<T> *root,data_node<T> *node,BtreeNode<T> *left,BtreeNode<T> *right);
    void push_ptr(BtreeNode<T> *root,BtreeNode<T> *child);
    void spilt_or_not(BtreeNode<T> *root);
};



//插入数据
template<typename T>
void BTree<T>::insert(int key,T data){
    BtreeNode<T> *temp = this->root;

    data_node<T> *node = new data_node<T>(key,data);

    //确定到叶子节点
    if (temp->ptrs[0] == NULL){
        push_node(temp,node,NULL,NULL);
    } else{
        while (temp->ptrs[0] != NULL){
            int i;
            int j = temp->key_num;
            for (i = 0; i < j; i++) {
                if (key < temp->dataNode[i]->key){
                    temp = temp->ptrs[i];
                    break;
                }
            }
            if (i == j){
                temp = temp->ptrs[j];
            }

        }

        //TODO:放入key数值
        push_node(temp,node,NULL,NULL);
    }
}

template<typename T>
void BTree<T>::push_ptr(BtreeNode<T> *root, BtreeNode<T> *child) {
    int i = root->prt_num++;
    root->ptrs[i] = child;
    for (int j = i-1; j >= 0; j--) {
        if (root->ptrs[j]->dataNode[0]->key > root->ptrs[i]->dataNode[0]->key){
            BtreeNode<T> *temp = root->ptrs[i];
            root->ptrs[i] = root->ptrs[j];
            root->ptrs[j] = temp;
            i = j;
        }
    }

}


//正常的放入key等数据
template<typename T>
void BTree<T>::push_node(BtreeNode<T> *root,
                         data_node<T> *node,
                         BtreeNode<T> *left,
                         BtreeNode<T> *right) {
    int i = root->key_num++;
    root->dataNode[i] = node;
    for (int j = i-1;j >= 0;j--) {
        if (root->dataNode[j]->key > root->dataNode[i]->key){
            data_node<T> *temp = root->dataNode[i];
            root->dataNode[i] = root->dataNode[j];
            root->dataNode[j] = temp;
            i=j;
        }
    }

    if (left!=NULL){

        int j = root->prt_num++;
        root->ptrs[j] = left;
        for (int k = j-1; k >= 0 ; k--) {
            BtreeNode<T> *prt = root->ptrs[k];
            if (root->ptrs[j]->dataNode[root->ptrs[j]->key_num-1]->key < prt->dataNode[prt->key_num-1]->key){
                BtreeNode<T> *temp = root->ptrs[k];
                root->ptrs[k] = root->ptrs[j];
                root->ptrs[j] =temp;
            }
        }}

        if (right!=NULL){

            int j = root->prt_num++;
            root->ptrs[j] = right;
            for (int k = j-1; k >= 0 ; k--) {
                BtreeNode<T> *prt = root->ptrs[k];
                if (root->ptrs[j]->dataNode[root->ptrs[j]->key_num-1]->key < prt->dataNode[prt->key_num-1]->key){
                    BtreeNode<T> *temp = root->ptrs[k];
                    root->ptrs[k] = root->ptrs[j];
                    root->ptrs[j] =temp;
                }
            }
    }
    spilt_or_not(root);


}

//判断是否分裂
template<typename T>
void BTree<T>::spilt_or_not(BtreeNode<T> *root) {
    if (root->key_num == RANK){
        BtreeNode<T> *left = new BtreeNode<T>();
        BtreeNode<T> *right = new BtreeNode<T>();

        for (int i = 0; i < (RANK/2); ++i) {
            push_node(left,root->dataNode[i],root->ptrs[i],root->ptrs[i+1]);
        }
        for (int i = (RANK/2+1); i < RANK;i++) {
            push_node(right,root->dataNode[i],root->ptrs[i],root->ptrs[i+1]);
        }
        root->dataNode[0] = root->dataNode[RANK/2];
        for (int i = root->key_num-1; i > 0 ; i--) {
            root->dataNode[i] = NULL;
            root->key_num--;
        }


        push_ptr(root,left);
        left->preant = root;

        push_ptr(root,right);
        right->preant = root;
    }
    }







int btreemain(){
    BTree<string> *root = new BTree<string>();
    root->insert(30,"aaaa");
    root->insert(20,"aaaa");
    root->insert(10,"aaaa");





    cout<<"我是b树";
    return 0;
}



