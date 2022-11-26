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
        root = (AVLNode*) malloc(sizeof(AVLNode));
        memset(root,0, sizeof(AVLNode));
        root->data = data;
        return root;
    }
        //辨别数据大小应该插入左子树还是右子树
        if (data > root->data){
           root->right = insert(root->right,data);
        } else if (data < root->data){
            root->left = insert(root->left,data);
        } else{
            root->data = data;
        }
         root->hegith = max(get_hight(root->left), get_hight(root->right))+1;
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

void update_banlen(AVLNode* root){
    if (root == NULL)return;

    if (root->left){
        update_banlen(root->left);
    }else if (root->right)
    {
        update_banlen(root->right);
    }
    root->hegith =  max(get_hight(root->left), get_hight(root->right) + 1);
}



















AVLNode *get_prev_parent(AVLNode *root){
    AVLNode *p = root;
    p = p->left;
    while (p->right->right){
        p = p->right;
    }
    return p;
}

AVLNode *get_carry_parent(AVLNode *root){
    AVLNode *p = root;
    p = p->right;
    while (p->left->left){
        p = p->left;
    }
    return p;
}

AVLNode *free_AVL(AVLNode *root){
    if (!root)return NULL;
    AVLNode *p = root;
    free(p);
    root = NULL;
    return NULL;
}


//删除业务
//bool remove(AVLNode *root,char data){
//    if (root == NULL)return false;
//    AVLNode *pn = root;
//    AVLNode *p = root;
//    int l_or_r = 1;   //1 左  2  右
//    while (p){
//        if (data < p->data){
//            pn = p;
//            p = p->left;
//            l_or_r = 1;
//        } else if (data > p->data){
//            pn = p;
//            p = p->right;
//            l_or_r = 2;
//        } else{
//            break;
//        }
//    }
//    if (!p)return false;
//    if (p->left == NULL&&p->right != NULL){
//        AVLNode *right = p->right;
//        if (l_or_r == 1){
//            pn->left = right;
//            p = free_AVL(p);
//        } else{
//            pn->right = right;
//            p = free_AVL(p);
//        }
//    } else if (p->left != NULL&&p->right == NULL) {
//        AVLNode *left = p->left;
//        if (l_or_r == 1) {
//            pn->left = left;
//            p = free_AVL(p);
//        } else {
//            pn->right = left;
//            p = free_AVL(p);
//        }
//    }
//
//
//
//
//
//
//}



AVLNode *remove(AVLNode *node,char data){
    if (node == NULL)
        return NULL;
    AVLNode *retNode;
    if (data < node->data) {
        node->left = remove(node->left, data);
        retNode = node;
    } else if (data > node->data) {
        node->right = remove(node->right, data);
        retNode = node;
    } else {   // e.compareTo(node.e) == 0
        // 待删除节点左子树为空的情况
        if (node->left == NULL) {
            AVLNode *rightNode = node->right;
            node->right = NULL;
            free_AVL(node);
            retNode = rightNode;
        }
            // 待删除节点右子树为空的情况
        else if (node->right == NULL) {
            AVLNode *leftNode = node->left;
            node->left = NULL;
            free_AVL(node);
            retNode = leftNode;
        } else {
            AVLNode *right = node->right;
            AVLNode *left = node->left;
            left->right = right;
            retNode = left;

        }
    }
    if (retNode == NULL)
        return NULL;
    //维护平衡
    //更新height
    retNode->hegith = max(get_hight(retNode->left), get_hight(retNode->right))+1;
    int balance = get_balance(retNode);

    // 通过平衡因子判断平衡应该是左旋还是右旋
    //如果平衡因子大于1说明左边比较高
    if (balance > 1 && get_balance(retNode->left) >= 0) {
        return LL(retNode);
    }
    //当平衡因子小于-1说明右边比较高
    if(balance < -1 && get_balance(retNode->right) <= 0){
        return RR(retNode);
    }


    //当平衡因子大于1时并且右边小于0 使用LR
    if (balance > 1 && get_balance(retNode->right) < 0) {
        return LR(retNode);
    }
    //当平衡因子小于-1时并且左边大于0 使用RL
    if (balance < -1 && get_balance(retNode->left) > 0){
        return RL(retNode);
    }
    return retNode;
}




/**
 * 主函数
 */
void TreeMain(){

    int m = max(1,4);


    AVLNode *root  = NULL;
    root = insert(root,'g');
//    free_AVL(root);
    root = insert(root,'x');
    root = insert(root,'d');
    root = insert(root,'b');
    root = insert(root,'e');
    root = insert(root,'a');
    root = insert(root,'c');
//    root = insert(root,'f');
    cout<<remove(root,'g');
    update_banlen(root);
    root = insert(root,'F');
    root = insert(root,'A');
//    cout<<remove(root,'z');

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






























