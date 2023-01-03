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




int main() {

    array_part_main();





    return 0;
}
