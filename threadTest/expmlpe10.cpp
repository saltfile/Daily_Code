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


int event_idle(){}
int event_goingup(){}
int event_goingdown(){}
int event_attop(){}
int event_atbottom(){}
int event_malfuntion(){}
int event_unexpect(){}
int event_end(){}








int expampe10_main(){
    enum stat_code now_stat = ENTRY_STAT;
    enum ret_code rc;
    int (*stat_func)(void);
    cout<<"以当前状态开启状态机"<<endl;

    while (1){
        stat_func = event[now_stat];
        rc =(ret_code)stat_func();
        if (now_stat == END_STAT)
            break;

    }

    cout<<"有限状态机结束";








    return 0;
}




