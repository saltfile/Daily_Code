//
// Created by saltfish on 2022/4/24.
//
#include "my_home.h"

int seizetree = 0;

AVLNode * new_AVLNode(char data){
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

    gnode->hegith = max(get_hight(gnode->left), get_hight(gnode->right)) + 1;
    pnode->hegith = max(get_hight(pnode->left), get_hight(pnode->right))+ 1;
    return pnode;
}

//单旋：右旋转
AVLNode *RR(AVLNode * root){
    AVLNode *gnode = root;
    AVLNode *pnode = root->right;

    gnode->right = pnode->left;
    pnode->left = gnode;

    gnode->hegith = max(get_hight(gnode->left), get_hight(gnode->right)) + 1;
    pnode->hegith = max(get_hight(pnode->left), get_hight(pnode->right))+ 1;
    return pnode;
}


//双旋转:右左旋
AVLNode *RL(AVLNode *root){
    AVLNode *gnode = root;
    AVLNode *pnode = root->left;

    pnode = RR(pnode);
    gnode->left = pnode;
    gnode = LL(gnode);

    return gnode;
}

//双旋转:左右旋
AVLNode *LR(AVLNode *root){
    AVLNode  *gnode = root;
    AVLNode  *pnode = root->right;

    pnode = LL(pnode);
    gnode->right = pnode;
    gnode = RR(gnode);

    return gnode;
}
//先获取对应的高度
int get_hight(AVLNode *root){
    if (root == NULL)return 0;
    return root->hegith;
}

// 平衡因子的计算
int get_balance(AVLNode *root){
    if (root == NULL)return 0;
    return get_hight(root->left)- get_hight(root->right);
}




//插入业务
AVLNode *insert(AVLNode *root,char data){
    //先查看是否是空的
    if (root == NULL){
        AVLNode *p = (AVLNode*) malloc(sizeof(AVLNode));
        memset(p,0, sizeof(AVLNode));
        return p;
    }
        //辨别数据大小应该插入左子树还是右子树
        if (data > root->data){
            insert(root->right,data);
        } else if (data < root->data){
            insert(root->left,data);
        } else{
            root->data = data;
        }
         root->hegith = max(get_hight(root->left), get_hight(root->right) + 1);
         int balance = get_balance(root);




         // 通过平衡因子判断平衡应该是左旋还是右旋
         //如果平衡因子大于1说明左边比较高
    if (balance > 1 && get_balance(root->left) >= 0) {
        return LL(root);
    }
    //当平衡因子小于-1说明右边比较高
    if(balance < -1 && get_balance(root->right) <= 0){
        return RR(root);
    }


    //当平衡因子大于1时并且右边小于0 使用LR
    if (balance > 1 && get_balance(root->right) < 0) {
        return LR(root);
    }
    //当平衡因子小于-1时并且左边大于0 使用RL
    if (balance < -1 && get_balance(root->left) > 0){
        return RL(root);
    }
    return root;

}













/**
 * 主函数
 */
void TreeMain(){

    int m = max(1,4);
//    AVLNode *root  = NULL;
AVLNode *g= new_AVLNode('g');
    AVLNode *p= new_AVLNode('p');
    AVLNode *n= new_AVLNode('n');
    AVLNode *t0= new_AVLNode('0');
    AVLNode *t1= new_AVLNode('1');
    AVLNode *t2= new_AVLNode('2');
    AVLNode *t3 = new_AVLNode('3');
    g->left = p;
    p->left = t0;
    p->right = n;
    n->left = t1;
    n->right = t2;
    g->right = t3;
    g =  LR(g);

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






























