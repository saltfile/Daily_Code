//
// Created by saltfish on 2022/7/15.
//
#include "pub_references.h"

void MUR_mem::copy_data(byte *data) {
    int len =  bytelen(data);
    if (len <= 16){
        for (int i = 0; i < len; ++i) {
            this->data[i] = data[i];
        }
    } else{
        for (int i = 0; i < 16; ++i) {
            this->data[i] = data[i];
        }
    }
}

void MUR_mem::free_data() {
    memset(this->data,0,16);
}



























//将命令转化成二进制字节(象征性的)
//add命令 == 10010110  加法
//sub命令 == 00010110  减法
//mul命令 == 10001001  乘法
//div命令 == 00001001  除法
//asg命令 == 10000000  赋值寄存器命令
//free命令 == 11100000  清除寄存器内数据
byte *command_conver_byte(char *command){
    if (str_equal(command,"add")){
        byte *res = (byte *) "10010110";
        return res;
    }
    if (str_equal(command,"sub")){
        byte *res = (byte *) "00010110";
        return res;
    }
    if (str_equal(command,"mul")){
        byte *res = (byte *) "10001001";
        return res;
    }
    if (str_equal(command,"div")){
        byte *res = (byte *) "00001001";
        return res;
    }
    if (str_equal(command,"asg")){
        byte *res = (byte *) "10000000";
        return res;
    }
    if (str_equal(command,"free")){
        byte *res = (byte *) "11100000";
        return res;
    }
        return NULL;
};


















code_tree *create_node(){
    code_tree *res = (code_tree *)malloc(sizeof(code_tree));
    memset(res,0,sizeof(code_tree));
    return res;
}
code_tree *create_node(char flag){
    code_tree *res = (code_tree *)malloc(sizeof(code_tree));
    memset(res,0,sizeof(code_tree));
    res->flag = flag;
    return res;
}
code_tree *create_node(char flag,cpu_fun cpuFun){
    code_tree *res = (code_tree *)malloc(sizeof(code_tree));
    memset(res,0,sizeof(code_tree));
    res->flag = flag;
    res->fun = cpuFun;
    return res;
}



code_tree *code_build(){
    char *commands[6] = {"10010110","00010110","10001001","00001001","10000000","11100000"};
    cpu_fun funs[6] = {add,sub,mul,div,asg,fre};
    code_tree *root = create_node('-');
    for (int i = 0; i < 5; ++i) {
        root = code_add(root,commands[i],funs[i]);
    }
    return root;
}

cpu_fun  code_sel(code_tree *root, byte *command){
    code_tree *p = root;

    for (int i = 0; i < 8; ++i) {
        char c = command[i];
        if (c == '0'){
            p = p->left;
        } else if (c == '1'){
            p = p->right;
        }
    }
    cpu_fun ans = p->fun;
    if (ans != NULL){
        return ans;
    } else{
        log_erro("编码树未找到对应的命令函数");
        exit(-122);
    }
}

code_tree *code_add(code_tree *root,char *command,cpu_fun fun){
    code_tree *p =root;

    for(int i = 0;i < 8;i++){
        char c = command[i];
        if (c == '0'){
            if(p->left == NULL)
                p->left = create_node(c);
            p = p->left;
        } else if (c == '1'){
            if (p->right == NULL)
                p->right = create_node(c);
            p = p->right;
        }
    }
    if (p->fun != NULL){
        log_erro("有相同的函数指针造成冲突");
    } else{
        p->fun = fun;
    }
    return root;
}


int add(byte *regs){
    log_info("add");
    return 1;
}
int sub(byte *regs){
    log_info("sub");
    return 1;
}
int mul(byte *regs){
    log_info("mul");
    return 1;
}
int div(byte *regs){
    log_info("div");
    return 1;
}
int asg(byte *regs){
    log_info("asg");
    return 1;
}
int fre(byte *regs){
    log_info("fre");
    return 1;
}






























