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
//获取高度
int get_hegith(AVLNode *root);
//计算平衡因子
int get_balance(AVLNode *node);

AVLNode *del_node(AVLNode *root,int key);

//限制最低层数
AVLNode *minimum(AVLNode *node);


/**
 * 左旋
 */
AVLNode *left(AVLNode *root){
    AVLNode *res = root->right;
    AVLNode *tem = res->left;
    res->left = root;
    root->right = tem;

    root->hegith = max(get_hegith(root->left),get_hegith(root->right))+1;
    res->hegith = max(get_hegith(res->left),get_hegith(res->right))+1;
    return res;
}



/**
 * 右旋
 */
AVLNode *right(AVLNode *root){
    AVLNode *res = root->left;
    AVLNode *tem = res->right;
    res->right = root;
    root->left = tem;
    root->hegith = max(get_hegith(root->left),get_hegith(root->right))+1;
    res->hegith = max(get_hegith(res->left),get_hegith(res->right))+1;
    return res;
}

/**
 * 添加
 */
AVLNode *add_node(AVLNode *root,int data){

    if(root == NULL){
        root= (AVLNode *)malloc(sizeof(AVLNode));
        memset(root,0,sizeof(root));
        root->hegith = 1;
        root->data = data;
        return root;
    }

    //如果大于就往右边走，小于就往左边走
    if (data > root->data){
        root->right = add_node(root->right,data);
    }
    else if (data < root->data) {
        root->left = add_node(root->left, data);
    }
    root->hegith = 1 + max(get_hegith(root->left),get_hegith(root->right));
    //平衡因子计算
    int balance = get_balance(root);

    if(balance > 1 && get_balance(root->left) > 0){
        return right(root);
    }

    if(balance < -1 && get_balance(root->right) < 0){
        return left(root);
    }


    if(balance > 1 && get_balance(root->left) < 0){
        root->left = left(root->left);
        return right(root);
    }

    if(balance < -1 && get_balance(root->right) > 0){
        root->right = right(root->right);
        return left(root);
    }
    return root;


}


/**
 * 删除
 * @param root 节点参数
 * @param key 要删除的值
 * @return
 */
AVLNode *del_node(AVLNode *root,int key){
    if(root == NULL){
        return NULL;
    }

    AVLNode *Ret = NULL;

    if(key < root->data){
        root->left = del_node(root->left, key);
        Ret = root;
    }else if (key > root->data) {
        root->right = del_node(root->right, key);
        Ret = root;
    } else{

        if(root->left == NULL){
            AVLNode *rightNode = root->right;
            root->right = NULL;
            seizetree--;
            Ret = rightNode;
        } else if(root->right == NULL){
            AVLNode *letfNode = root->left;
            root->left = NULL;
            seizetree--;
            Ret = letfNode;
        } else{
            AVLNode *succes = minimum(root->right);
            succes->right = del_node(root->right, succes->data);
            succes->left = root->left;

            root->left = root->right = NULL;

            Ret = succes;
        }
    }
    if(Ret == NULL)return NULL;


    Ret->hegith = 1 + max(get_hegith(Ret->left),get_hegith(Ret->right));
    //平衡因子计算
    int balance = get_balance(Ret);

    if(balance > 1 && get_balance(Ret->left) > 0){
        return right(Ret);
    }

    if(balance < -1 && get_balance(Ret->right) < 0){
        return left(Ret);
    }


    if(balance > 1 && get_balance(Ret->left) < 0){
        root->left = left(Ret->left);
        return right(Ret);
    }

    if(balance < -1 && get_balance(Ret->right) > 0){
        root->right = right(Ret->right);
        return left(Ret);
    }
    return Ret;
}



AVLNode *minimum(AVLNode *node){
    if(node == NULL){
        return NULL;
    }
    while (node->left){
        node = node->left;
    }
    return node;

}



int get_hegith(AVLNode *root){
    if(root == NULL){
        return 0;
    }
    return root->hegith;
}

int get_balance(AVLNode *node){
    if(node == NULL){
        return 0;
    }
    return get_hegith(node->left) - get_hegith(node->right);
}

AVLNode *search_val(AVLNode *node,int key){
    AVLNode *m = (AVLNode *)malloc(sizeof(AVLNode));
    memset(m,0,sizeof(m));
    if(node != NULL){
        if(key == node->data){
            m->data = node->data;
            return m;
        } else if (key < node->data){
            return search_val(node->left,key);
        } else{
            return search_val(node->right,key);
        }
    } else{
        return NULL;
    }
    return NULL;
}

/**
 * 更新节点，就是删除之后再添加
 * @param node
 * @param old
 * @param update
 * @return
 */
AVLNode *update_node(AVLNode *node,int old,int update){
    if(!search_val(node,old)){
        cout<<"未找到修改失败"<<endl;
        return node;
    }

    node = del_node(node,old);
    node = add_node(node,update);
    return node;
}





/**
 * 主函数
 */
void TreeMain(){
    AVLNode *root  = NULL;
    //LR
//    root = add_node(root,31);
//    root = add_node(root,12);
//    root = add_node(root,34);
//    root = add_node(root,11);
//    root = add_node(root,14);
//    root = add_node(root,13);
//    root = add_node(root,16);
    //RL
    root = add_node(root,31);
    root = add_node(root,16);
    root = add_node(root,44);
    root = add_node(root,34);
    root = add_node(root,49);
    root = add_node(root,32);
    root = add_node(root,38);
    AVLNode *st = search_val(root,38);
    if(st != NULL)
        cout<<st->data;
    del_node(root,32);
    st = search_val(root,38);
    if(st != NULL)
        cout<<st->data;
    return;
}






























