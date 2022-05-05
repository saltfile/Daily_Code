//
// Created by saltfish on 2022/5/4.
//

#include "Myany.h"



char * str_copy(char *str,char *arr){
    str = (char *)malloc(strlen(arr)+1);
    memset(str,0,strlen(arr)+1);
    for(int i = 0;i < strlen(arr);i++){
        str[i] = arr[i];
    }
    str += '\0';
    return str;
}

int get_column(FILE *fp){
    int res = 0;
    char c;
    if (fseek(fp, 0, SEEK_SET)<0) {
        perror("Seek file textfile");
        exit(1);
    }
    while (!feof(fp)){
        c = fgetc(fp);
        if (c == '\n'){
            res++;
        }
    }
    fseek(fp, 0, SEEK_SET);
    return res+1;
}

long get_bytenumber(FILE *fp){
    fseek(fp, 0, SEEK_END);
    return ftell(fp);
}


/**
 * TODO:获取整个文件每一列的数据list
 * @param fp 文件指针
 * @return 文件结果
 */
list * get_filedata(FILE *fp){
    if (fseek(fp, 0, SEEK_SET)<0) {
        perror("Seek file textfile");
        exit(1);
    }

    list *res = (list *)malloc(sizeof(list));
    memset(res,0,sizeof(res));


    char *temp = (char *)malloc(100);
    memset(temp,0,100);
    int ptr_str = 0;

    while (!feof(fp)){
        char c = fgetc(fp);
        //遇到一行的时候
        if(c == '\n'){
            add_list(res,temp);
            ptr_str = 0;
            memset(temp,0,100);
        } else if(c == -1){
            //文件结尾
            add_list(res,temp);
            memset(temp,0,100);

        }else{
            temp[ptr_str]=c;
            ptr_str++;
        }
    }

    if (fseek(fp, 0, SEEK_SET)<0) {
        perror("Seek file textfile");
        exit(1);
    }
    return res;
}




list *get_gonfig(list *root){
    int list_size = get_list_size(root);
    list *res = (list *)malloc(sizeof(list));
    for(int i = 0;i < list_size ; i++){
        char *str = get_list_node(root,i);
        list *temp = split(str,"=");
        int temp_size = get_list_size(temp);
        if(temp_size == 2){
            if(strcmp(get_list_node(temp,0),"id") == 0){
                add_list(res,get_list_node(temp,1));
            }
            if(strcmp(get_list_node(temp,0),"port") == 0){
                add_list(res,get_list_node(temp,1));
            }
            if(strcmp(get_list_node(temp,0),"user") == 0){
                add_list(res,get_list_node(temp,1));
            }
            if(strcmp(get_list_node(temp,0),"password") == 0){
                add_list(res,get_list_node(temp,1));
            }
            if(strcmp(get_list_node(temp,0),"database") == 0){
                add_list(res,get_list_node(temp,1));
            }
        } else{
            log_erro("出错");
            return NULL;
        }
    }
    return res;
}
list *split(char *str,char *dent){
    int strlens = strlen(str);
    char  ch[strlens];
    strcpy(ch,str);
    char* str1 = strtok(ch, dent);
    list *res = (list *)malloc(sizeof(list));
    memset(res,0,sizeof(res));
    add_list(res,str1);
    while (str1 != NULL)
    {
        str1 = strtok(NULL, dent);
        if (str1!=NULL)
        add_list(res,str1);
    }
    return res;
}





void read(){
    FILE *fd;
    if ( (fd = fopen(CONFIGPATH,"r+")) == NULL) {
        perror("Open file textfile");
        exit(1);
    }
//    cout<<get_column(fd)<<endl;
//    cout<<get_column(fd)<<endl;
//
//    cout<<get_bytenumber(fd)<<endl;
//    cout<<get_bytenumber(fd)<<endl;
    cout<<get_column(fd)<<endl;
    int a =get_column(fd);
    list *root = get_filedata(fd);


    list *str = get_gonfig(root);
    dis_play(str);

}










