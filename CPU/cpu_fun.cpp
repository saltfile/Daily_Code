//
// Created by saltfish on 2022/7/15.
//

#include "pub_references.h"
//过年写
vector<string> code_arr;
vector<string> code_binary_arr;//编译过后的命令



void loading_code(char * path){
    FILE *fp = fopen(path, "r");
    fseek(fp,0L,SEEK_END); /* 定位到文件末尾 */
    int flen=ftell(fp); /* 得到文件大小 */
    char* p=(char *)malloc(flen+1); /* 根据文件大小动态分配内存空间 */
    if(p==NULL)
    {
        fclose(fp);
        return;
    }
    fseek(fp,0L,SEEK_SET); /* 定位到文件开头 */
    fread(p,flen,1,fp); /* 一次性读取全部文件内容 */

    p[flen]=0;
    fclose(fp);

    char **code_s = split(p,"\n");
    int code_len = spilt_size(p,"\n");

    for (int i = 0; i < code_len; ++i) {
        string s = code_s[i];
        code_arr.push_back(code_s[i]);
    }

}


void compile(){
    for (int i = 0; i < code_arr.size(); ++i) {
        string commod = code_arr[i];
        code_binary_arr.push_back(commod_binary(commod));
    }
};


string com_to_bin(string commad){
    if (commad.compare("add") == 0){
        return "10000000";
    } else if (commad.compare("red") == 0){
        return "01000000";
    }else if (commad.compare("mul") == 0){
        return "00100000";
    }else if (commad.compare("div") == 0){
        return "00010000";
    }else if (commad.compare("copy") == 0){
        return "11111000";
    }else if (commad.compare("mov") == 0){
        return "11111100";
    }else if (commad.compare("free") == 0){
        return "11111110";
    }
    return "";


}


string commod_binary(string commad){
    string res = "";
    vector<string> arr = split_str(commad," ");
    string com = com_to_bin(arr[0]);

    if (com.size() == 0){
        string str_log = commad;
        str_log += "没有被找到";

        throw new CommadNotFound(str_log);
        exit(-1);
    }

    res+=com;

    vector<string> args = split_str(arr[1],",");
    string arg1 = args[0].substr(1,args[0].size()-1);
    res+= to_binary(atol(arg1.c_str()));
    if (args.size() > 1){
        if (args[1][0] == 'R'){
            string arg2 = args[1].substr(1,args[0].size()-1);
            res+= to_binary(atol(arg2.c_str()));
        } else{
            res+= to_binary(atol(args[1].c_str()));
        }
    } else{
        res+="00000000";
    }
}


























command_tree *build_tree(command_tree *root,string commad,cpu_fun cpuFun){

    command_tree *p = root;
    for (int i = 0; i < commad.size(); ++i) {
        if (commad[i] == '0'){
            if (p->left == nullptr)
            p->left = new command_tree();
            p = p->left;
        } else{
            if (p->right == nullptr)
            p->right = new command_tree();
            p = p->right;
        }
    }

    if (p->fun_run == NULL){
        p->fun_run = cpuFun;
    } else{
        throw new InstructionRepetition("指令重复请重新插入");
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


void CPU::loading_binary() {
    for (int i = 0; i < code_binary_arr.size(); i++) {
        this->Bus[i] = code_binary_arr[i].substr(0,8);
        this->Bus[Bus_len/3+i] = code_binary_arr[i].substr(8,8);
        this->Bus[(Bus_len/3)*2+i] = code_binary_arr[i].substr(16,8);
    }





}

















