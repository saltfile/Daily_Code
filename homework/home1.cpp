//
// Created by saltfish on 2022/4/6.
//
#include "my_home.h"
char *cache;
int count = 0;
void init_cache(){
    cache = (char *)malloc(sizeof(char)*600);
    memset(cache,0,sizeof(cache));
    count = 0;
}
//学生构成int id,char* name    set


void set_stu(int id,char *name){
    char head = 0x35;
    char len[4] = {};
    int objlen = 4+strlen(name)+1;
    char *len_ptr = (char *)&objlen;
    for(int i = 0;i < 4;i++){
        char p = (char)*len_ptr;
        len[i] = p;
        len_ptr++;
    }

    char ids[4] = {};
    char *id_ptr = (char *)&id;
    for(int i = 0;i < 4;i++){
        char p = (char)*id_ptr;
        ids[i] = p;
        id_ptr++;
    }
    cache[count] = head;
    count++;

    for(int i = 0;i < 4;i++){
        cache[count] = len[i];
        count++;
    }



    for(int i = 0;i < 4;i++){
        cache[count] = ids[i];
        count++;
    }

    for(int i = 0;i < strlen(name);i++){
        cache[count] = name[i];
        count++;
    }
    cache[count] = '\0';
    count++;

}








//老师构成int id char len = 5
void set_tea(int id,char N){
    char head = 0x34;
    char len[4] = {0x05,0x00,0x00,0x00};
    char id_str[4];
    char *isd = (char *)&id;
    for (int i = 0; i < 4; ++i) {
        char p = (char)*isd;
        id_str[i] = p;
        isd++;
    }
    cache[count] = head;
    count++;

    for(int i = 0;i < 4;i++){
        cache[count] = len[i];
        count++;
    }
    for(int i = 0;i < 4;i++){
        cache[count] = id_str[i];
        count++;
    }

    cache[count] = N;
    count++;
}


void show_all(){
    int role =0;
    while (true){
        if(role >= count){
            break;
        }
        if(cache[role] == 0x34){
            role++;
            int *len;
            char lems[4];
            for(int i = 0;i < 4;i++){
                lems[i] = cache[role];
                role++;
            }
            len = (int *)&lems;

            int *id;
            char ids[4];
            for (int i = 0; i < 4; ++i) {
                ids[i] = cache[role];
                role++;
            }
            id = (int *)&ids;

            char *sex = (char *)&cache[role];
            role++;
            printf("找到老师类长度:%d,老师id%d,老师性别%c\n",*len,*id,*sex);

        }
        if(cache[role] == 0x35){
            role++;
            int *len;
            char lems[4];
            for(int i = 0;i < 4;i++){
                lems[i] = cache[role];
                role++;
            }
            len = (int *)&lems;
            int *id;
            char ids[4];
            for (int i = 0; i < 4; ++i) {
                ids[i] = cache[role];
                role++;
            }
            id = (int *)&ids;

            char *str_name = (char *)malloc(*len-4);
            for(int i = 0;i < *len-4;i++){
                str_name[i] = cache[role];
                role++;
            }

            printf("找到学生类长度:%d,学生id%d,学生姓名%s\n",*len,*id,str_name);

        }


    }
}



int home1_main(){

//home1
    init_cache();
    set_tea(455,'W');
    set_stu(1454,"sadasf");
    set_tea(445,'N');
    show_all();
    return 0;
}



