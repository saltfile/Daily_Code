//
// Created by saltfish on 23-1-22.
//
#include "pub_references.h"


int spilt_size(char *a,char *b){
    char *str = a;
    char *dent = b;
    int strlens = strlen(str);
    char  ch[strlens];
    strcpy(ch,str);
    char* str1 = strtok(ch, dent);
    int i = 0;
    while (str1 != NULL)
    {
        str1 = strtok(NULL, dent);
        if (str1!=NULL){
            i++;
        }
    }

    return i+1;
}

char * str_copy(char *str,char *arr){
    str = (char *)malloc(strlen(arr)+1);
    memset(str,0,strlen(arr)+1);
    for(int i = 0;i < strlen(arr);i++){
        str[i] = arr[i];
    }
    str += '\0';
    return str;
}


char **split(char *str,char *dent){
    int size = spilt_size(str,dent);
    int strlens = strlen(str);
    char  ch[strlens];
    strcpy(ch,str);
    char* str1 = strtok(ch, dent);
    char **res =  (char**) malloc(size * sizeof(char*));
    memset(res,0,sizeof(res));
    int i = 0;
    res[i] = str_copy(res[i],str1);
    while (str1 != NULL)
    {
        str1 = strtok(NULL, dent);
        if (str1!=NULL){
            i++;
            res[i] = str_copy(res[i],str1);
        }
    }
    return res;
}

vector<string> split_str(const string& str,const string& delim) {
    vector<string> res;
    if("" == str) return res;
    //先将要切割的字符串从string类型转换为char*类型
    char * strs = new char[str.length() + 1] ; //不要忘了
    strcpy(strs, str.c_str());

    char * d = new char[delim.length() + 1];
    strcpy(d, delim.c_str());

    char *p = strtok(strs, d);
    while(p) {
        string s = p; //分割得到的字符串转换为string类型
        res.push_back(s);//存入结果数组
        p = strtok(NULL, d);
    }

    return res;
}