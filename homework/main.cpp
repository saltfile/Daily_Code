#include "my_home.h"
//template<class T,int size>
//void xxx(T (&t)[size]){
//    for(T x:t){
//        cout<<x<<endl;
//    }
//    cout<<size<<endl;
//}
//class person{
//public:
//    int i=999;
//    person(int i){
//        cout<<"构造"<<endl;
//    }
//    ~person(){
//        cout<<"~person"<<endl;
//    }
//    person(person &p){
//        this->i=p.i;
//        cout<<"copy"<<endl;
//    }
//    person(person &&p){//移动构造函数
//        this->i=p.i;
//        p.i=0;
//        cout<<"转移所有权"<<endl;
//    }
//};
//person&& transform(person &&p){
//    cout<<"====="<<endl;
//    return move(p);//static_cast<xxx &&>(x)
//}
//template <class T>
//T&& xxx(T&& t){
//    return forward<T&&>(t);//完成转发
//}
//
//
//template<class  T,class U>
//auto plus1(T a,U b)->decltype(a+b){
//    return a+b;
//}
//auto xxx()->int(*)(int,int){
//    return plus1;
//}



















int main() {
//thread_Test();
//TreeMain();
RBTreedemo();



























//    person pafd(25);
//    cout<<pafd.i;
////    cout<<plus1(2,4)<<endl;
//
//    auto res = plus1(1.2,5);
//    cout<<typeid(res).name()<<endl;

//lamda表达式
//int c=1;//值传递    =值传递只读 &引用只在内部修改
//int *d=&c;
//int e=0;
//auto f=[&d](){
//    (*d)++;
//    return *d;
//};
//cout<<c<<"\t"<<f()<<"\t"<<"\t"<<*d<<endl;
//cout<<typeid(f).name();
//int a = 1;
//int b = 2;
//
//const int *ptr = &a;int a = 1;
//int b = 2;
//
//const int *ptr = &a;
//
//int a = 1;
//auto f=[&a]{
//    a++;
//    return a;
//};
//;
//cout<<f()<<a<<endl;




//homework3
//    string s = "asdd";
//    string  sss = "sad";
//    string  aaa = s+sss;
//    Ptr ptr(new MyList(5,1,2,3,4,5));
//    ptr.dis();
//    cout<<endl;
//    ptr++;
//
//    cout<<endl;
//    ptr.dis();
//    cout<<endl;
//    cout<<*ptr;
//    cout<<endl;
//    Ptr ptr1(new MyList(3,1,2,3));
//    ptr = ptr+ptr1;
//    ptr.dis();
//    cout<<endl<<ptr.my->len;





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
