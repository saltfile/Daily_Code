#include "my_home.h"
class MyList{
public:
    int *arr;
    int len = 0;
    MyList(int num,...){
        this->arr = (int *)malloc(sizeof(int)*num);
        memset(this->arr,0,sizeof(this->arr));
        va_list arg_list;
        va_start(arg_list,num);
        while (num -- > 0){
            arr[len] = va_arg(arg_list,int);
            len++;
        }
    }
    int get(int num){
        return arr[num];
    }
    void dis(){
        for(int i = 0;i < len;i++){
            if(i == len-1){
                cout<<arr[i];
                continue;
            }
            cout<<arr[i]<<",";
        }
    }

    void merge(int newarr[],int lens){
        int *arrs = (int *)malloc(sizeof(int)*(this->len+lens));
        for(int i = 0;i < this->len;i++){
            arrs[i] = this->arr[i];
        }
        for(int i = this->len;i < this->len+lens;i++){
            arrs[i] = newarr[i- this->len];
        }
        int *p = this->arr;
        this->arr = arrs;
//        free(p);
        this->len = this->len+lens;
    }


    void undo(){
        arr = arr+1;
        len--;
    }
    ~MyList(){
        cout<<"被删除";
    }
};

class Ptr{
public:
    MyList *my;
    Ptr(MyList *m):my(m){
    }
    void dis(){
        my->dis();
    }
    int operator[](int num){
        return my->get(num);
    }
    void operator++(int){
        my->undo();

    }
    int operator*(){
        return my->get(0);
    }
    Ptr operator+(Ptr p1){
       this->my->merge(p1.my->arr,p1.my->len);
        return *this;
    }


};




int main() {
    string s = "asdd";
    string  sss = "sad";
    string  aaa = s+sss;
    Ptr ptr(new MyList(5,1,2,3,4,5));
    ptr.dis();
    cout<<endl;
    ptr++;

    cout<<endl;
    ptr.dis();
    cout<<endl;
    cout<<*ptr;
    cout<<endl;
    Ptr ptr1(new MyList(3,1,2,3));
    ptr = ptr+ptr1;
    ptr.dis();





//home1
//    init_cache();
//    set_tea(455,'W');
//    set_stu(1454,"sadasf");
//    set_tea(445,'N');ptr =
//    show_all();
//hmoe2
//demo_explme();








    return 0;
}
