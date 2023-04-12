#include "my_home.h"

typedef struct node{
    char *a;
    struct  node *next;
};


void free_val_tupl(node *p){
    while (p->next){

        node *as = p;
        p = p->next;
        free(as);
        as = NULL;
    }
    node *as = p;
    as = NULL;
}


int is_wans(int num,int yin){
    int g = num%10;
    int s = (num/10)%10;
    int b = num/100;


    if ((g+s+b) == yin){
        return 1;
    } else{
        return 0;
    }
}

int main1() {

//  array_part_main();
RBTreedemo();


    return 0;
}
