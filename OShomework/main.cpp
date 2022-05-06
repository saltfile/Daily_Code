#include "MyAll.h"


static list *FJS= NULL;
list *into_queue(){
    list *lin = (list *)malloc(sizeof(list));
    add_list(lin,1,3);
    add_list(lin,2,2);
    add_list(lin,3,3);
    return lin;
}

void into_FJSqueue(){
      FJS = (list *)malloc(sizeof(list));
      add_list(FJS,1,3);
      sleep(2);
      add_list(FJS,2,1);
      sleep(3);
      add_list(FJS,3,1);
      add_list(FJS,4,3);
}

void run_FJS(){

        processType *ss;
        sleep(2);
        while ((ss = get_list_node(FJS,1))!=NULL){
            int lens = 1;
            int res = 1;
                for(int i =0;i < get_list_size(FJS);i++){
                    list *p = FJS;
                    int times = p->tree->servicetime;
                while (p->next) {
                    lens++;
                    if(p->tree->servicetime  < times){
                        ss = p->tree;
                        res = lens;
                    }
                    p = p->next;
                }
                lens++;
                    if(p->tree->servicetime  < times){
                        ss = p->tree;
                        res = lens;
                    }
                    task_run(ss);
                    cout<<"线程:"<<ss->pro_id<<endl;
                    cout<<"到达时间:"<<ss->submittime<<endl;
                    cout<<"服务时间:"<<ss->servicetime<<endl;
                    cout<<"开始时间:"<<ss->starttime<<endl;
                    cout<<"结束时间:"<<ss->endtime<<endl;
                    cout<<"周转时间:"<<ss->roundtime<<endl;
                    cout<<"带权周转时间:"<<ss->wRoundTime<<endl;
                    cout<<endl;
                    FJS = remove_node(FJS,res);
                    lens = 1;
                }

    }



}



void task_run(processType *node){
    node->starttime = time(0);
    sleep(node->servicetime);
    node->endtime = time(0);
    node->roundtime = node->endtime - node->submittime;
    node->wRoundTime = node->roundtime-(node->endtime-node->starttime);
}


void runtable(){
    list *root = into_queue();
    for(int i = 1;i <= get_list_size(root);i++){
        processType *ss = get_list_node(root,i);
        task_run(ss);
        cout<<"线程:"<<ss->pro_id<<endl;
        cout<<"到达时间:"<<ss->submittime<<endl;
        cout<<"服务时间:"<<ss->servicetime<<endl;
        cout<<"开始时间:"<<ss->starttime<<endl;
        cout<<"结束时间:"<<ss->endtime<<endl;
//        cout<<"结束时间:"<<8+end->tm_hour<<"时"<<end->tm_min<<"分"<<end->tm_sec<<"秒"<<endl;
        cout<<"周转时间:"<<ss->roundtime<<endl;
        cout<<"带权周转时间:"<<ss->wRoundTime<<endl;
        cout<<endl;
    }
}

int main() {
    thread t(into_FJSqueue);
    thread t1(run_FJS);
    t.join();
    t1.join();
    return 0;
}
