//
// Created by saltfish on 2022/10/21.
//
#include "basic_func.h"


#define TOP_FLOOR 9
#define BOTTOM_FLOOR 1
#define ENTRY_STAT idle
#define END_STAT _end

enum stat_code {idle = 0,going_up = 1,going_down = 2,at_top = 3,at_bottom = 4,mal_funtion = 5,un_expect = 6,_end = 7};
enum ret_code {up = 0,down = 1,halt = 2,top = 3,bottom = 4,fait = 5,quit = 6};
//状态函数指针的数组
int (*event[])(void) = {event_idle,event_goingup,event_goingdown,event_attop,event_atbottom,event_malfuntion,event_unexpect,event_end};




int target_floor_number = 0;
int current_floor_number = 1;
int accumulated_floor_number = 0;


int event_idle(){
    cout<<"我是开机状态"<<endl;
    sleep(5);

    return up;
}
int event_goingup(){

    cout<<"上行"<<endl;
    sleep(3);

    return bottom;
}
int event_goingdown(){

    cout<<"下降"<<endl;
    sleep(3);
    return top;


}
int event_attop(){
    cout<<"顶部"<<endl;
    sleep(4);
    return at_bottom;
}
int event_atbottom(){
    cout<<"底部"<<endl;
    sleep(6);
    return mal_funtion;

}
int event_malfuntion(){
    cout<<"结束"<<endl;
    return _end;

}
int event_unexpect(){
    cout<<"结束"<<endl;
    return _end;
}
int event_end(){
    cout<<"结束"<<endl;
    return _end;
}




int lookup_transitions[][7] = {
        // return codes:
        //      up       down       halt          top         bottom         fail          quit
        [idle]      = {going_up,    going_down,  idle,     un_expect, un_expect,  mal_funtion,  _end},
        [going_up]   = {going_up,    un_expect, idle,     at_top,      at_bottom,    mal_funtion,  _end},
        [going_down] = {un_expect, going_down,  idle,     at_top,      at_bottom,    mal_funtion,  _end},
        [at_top]     = {un_expect, going_down,  at_top,     at_bottom, un_expect,  mal_funtion,  _end},
        [at_bottom]  = {going_up,    going_down,  at_bottom, un_expect, un_expect,  mal_funtion,  _end},
        [mal_funtion] = {_end, _end, _end, _end, _end, _end, _end},
        [un_expect]  = {_end, _end, _end, _end, _end, _end, _end}
};

void* runable(void *arg){
    enum stat_code now_stat = ENTRY_STAT;
    enum ret_code rc;
    int (*stat_func)(void);
    cout<<"以当前状态开启状态机"<<endl;

    while (1){
        stat_func = event[now_stat];
        rc =(ret_code)stat_func();
        if (now_stat == END_STAT)
            break;
        now_stat = (enum stat_code)lookup_transitions[now_stat][rc];
    }

    cout<<"有限状态机结束";
    pthread_exit(0);

}


int expampe10_main(){

    pthread_t pid;
    pthread_create(&pid,NULL,runable,NULL);
    void *ptr;
    pthread_join(pid,&ptr);
    cout<<"整体结束"<<endl;
    return 0;
}




