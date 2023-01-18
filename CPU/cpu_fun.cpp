//
// Created by saltfish on 2022/7/15.
//
#include "pub_references.h"
//过年写



command_tree *build_tree(command_tree *root,string commad,cpu_fun cpuFun){

    command_tree *p = root;
    for (int i = 0; i < commad.size(); ++i) {
        if (commad[i] == '0'){
            p->left = new command_tree();
            p = p->left;
        } else{
            p->right = new command_tree();
            p = p->right;
        }
    }

    if (p->fun_run == NULL){
        p->fun_run = cpuFun;
    } else{
        log_erro("指令重复请重新插入");
    }
    return root;
}
//找到命令对应的功能
cpu_fun select_tree(command_tree *root,string commad){
    command_tree *p = root;

    for (int i = 0; i < commad.size(); ++i) {
        if (commad[i] == '0'){
            p = p->left;
        } else{
            p = p->right;
        }
    }

    if (p->fun_run != NULL){
        return p->fun_run;
    } else{
        return NULL;
    }

}




































