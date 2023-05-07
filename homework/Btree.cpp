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
        this->key = key;
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
    BtreeNode<T> *ptrs[RANK+1];//指针类型
    BtreeNode<T> *person;
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
    void push_node(BtreeNode<T> *root,data_node<T> node);
    void spilt_or_not(BtreeNode<T> *root);
};




template<typename T>
void BTree<T>::insert(int key,T data){
    BtreeNode<T> temp = this->root;

    data_node<T> node = new data_node<T>(key,data);

    //确定到叶子节点
    if (temp.ptrs[0] == NULL){
        push_node(temp,node);
    } else{
        while (temp.ptrs[0] != NULL){
            int i;
            int j = temp.key_num;
            for (i = 0; i < j; i++) {
                if (key < temp.dataNode[i].key){
                    temp = temp.ptrs[i];
                    break;
                }
            }
            if (i == j){
                temp = temp.ptrs[j];
            }

        }

        //TODO:放入key数值
        push_node(temp,node);
    }

}

template<typename T>
void BTree<T>::push_node(BtreeNode<T> *root,
                         data_node<T> node) {
    int i = ++root->key_num;
    root->dataNode[i] = node;
    for (int j = i-1;j >= 0;j--) {
        if (root->dataNode[j]->key > root->dataNode[i]->key){
            data_node<T> *temp = root->dataNode[j];
            root->dataNode[i] = root->dataNodep[j];
            root->dataNode[j] = temp;
        }
    }
    spilt_or_not(root);
}


template<typename T>
void BTree<T>::spilt_or_not(BtreeNode<T> *root) {








}









int btreemain(){
    BTree<string> *root = new BTree<string>();








    cout<<"我是b树";
    return 0;
}



