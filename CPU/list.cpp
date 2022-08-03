//
// Created by saltfish on 2022/7/28.
//

#include "pub_references.h"












list *list_Init(){
    list *res = (list *)malloc(sizeof(list));
    memset(res,0,sizeof(list));
    return res;
}
list *list_Init(int len){
    list *res = (list *)malloc(sizeof(list));
    memset(res,0,sizeof(list));
    res->len = len;
    return res;
}



void add_list(list *root,char *ptr,int len,int head){
    list *p = root;

    if(p->ptr == NULL){
        p->ptr = ptr;
        p->len = len;
        p->head = head;
        return;
    }
    while (p->next){
        p = p->next;
    }
    list *newnode = (struct list*)malloc(sizeof(list));
    newnode->ptr = ptr;
    newnode->len = len;
    newnode->head = head;
    newnode->next = NULL;
    p->next = newnode;
    newnode->prev = p;
}

void dis_play(list *root){
    list *p = root;
    if (p == NULL){
        log_info("这是空的了");
        return;
    }
    string str = "[";
    while (p->next){
        str +=p->len;
        str += "     ";
        p = p->next;
    }
    str+=p->len;
    str+="]";
    log_info(str);
}


int get_list_size(list *root){
    int res  = 0;
    list *p= root;
    while (p->next){
        res++;
        p=p->next;
    }
    return ++res;
}

list *get_list_node(list *root,int len){
    int arrsize = get_list_size(root);

    if(len > arrsize){
        log_erro("超出该链表长度------list_fun.cpp");
        return NULL;
    }else if (len <= 0){
        log_erro("违规下标位置------list_fun.cpp");
        return NULL;
    }
    list *p = root;
    for(int i = 1 ; i < len;i++){
        p = p->next;
    }
    return p;
}

list *remove_node(list *root,int len){
    int arrsize = get_list_size(root);
    if(len > arrsize){
        log_erro("超出该链表长度无法删除------link.cpp");
        return NULL;
    }else if (len <= 0){
        log_erro("违规下标位置无法删除---link.cpp");
        return NULL;
    }
    list *p = root;
    if(arrsize == 1){
        list *node = p;
        free (node);
        return NULL;
    }
    for(int i = 1 ; i < len;i++){
        p = p->next;
    }
    //尾节点
    if(p->next == nullptr){
        list *node = p->prev;
        list *nodep = p;
        node->next = NULL;
        free (nodep);
        while (node->prev){
            node = node->prev;
        }
        return node;
    } else if(p->prev == nullptr){
        list *node = p->next;
        list *nodep = p;
        node->prev = NULL;
        p = node;
        nodep->next = NULL;
        delete (nodep);
        return node;

    } else{
        list *pnode = p->prev;
        list *pres = p;
        list *nnode = p->next;
        pnode->next = nnode;
        nnode->prev = pnode;
        delete (pres);
        while (pnode->prev){
            pnode = pnode->prev;
        }
        return pnode;
    }
}