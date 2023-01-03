//
// Created by saltfish on 2022/4/10.
//

#include "my_home.h"

int home3_main(){
    //homework3
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
    cout<<endl<<ptr.my->len;
    return 0;
}