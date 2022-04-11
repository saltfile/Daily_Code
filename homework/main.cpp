#include "my_home.h"

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
    cout<<endl<<ptr.my->len;





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
