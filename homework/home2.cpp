//
// Created by saltfish on 2022/4/6.
//

#include "my_home.h"

void demo_explme(){
    Link_Node *head;
    head = init_head(head);
    memset(head,0,sizeof(Link_Node));

   lab_node *node = (lab_node *)malloc(sizeof(struct lab_node));
   node->id = 1;

    lab_node *node1 = (lab_node *)malloc(sizeof(struct lab_node));
    node1->id = 2;

    link_hadd(head,&node->list);
    link_hadd(head,&node1->list);

    struct lab_node *entry;
    Link_Node *p = head->next;
    entry = ((lab_node *)((char *)(p)-(unsigned long )(&((lab_node *)0)->list)));
    printf("%d",entry->id);

    p = p->next;


    entry = ((lab_node *)((char *)(p)-(unsigned long )(&((lab_node *)0)->list)));
    printf("%d",entry->id);


}






















Link_Node *init_head(Link_Node *head){
    head = (Link_Node *)malloc(sizeof(Link_Node));
    head->next = NULL;
    head->prve = NULL;
    return head;
}

void link_hadd(Link_Node *head,Link_Node *new_Node){
    Link_Node *p = head;
    while (p->next != NULL){
        p = p->next;
    }
    p->next = new_Node;
    new_Node->prve = p;
    new_Node->next = NULL;
}
void link_del(Link_Node *head,Link_Node *tail){
    //被删掉的中间节点
    Link_Node *p = head->next;

    head->next = tail;
    tail->prve = head;
    //放掉那里的节点内存
    free(p);
}









