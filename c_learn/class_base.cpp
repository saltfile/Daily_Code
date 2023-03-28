//
// Created by saltfish on 23-3-28.
//
#include "my_base.h"
/**
 * 这里的class也可以写成struct
 *
 * class和struct有什么区别?????
 *
 * 如果你的类上写的就是class那么默认的就是private
 * 而struct相反默认是pubilc
 *
 */




class person{
private:
    int id = 1;
    string name = "aaa";
public:

    person(){}
    person(int id,string name):id(id),name(name){}

    //拷贝构造函数
    person(person &p){
        cout<<p.name<<"被拷贝了"<<endl;
        this->id = p.id;
        this->name = p.name;
    }

    void dis(){
        cout<<"id :"<<this->id<<"name :"<<this->name<<endl;
    }
    ~person(){
        cout<<"被回收了"<<endl;
    }

};

//继承一共有三种

class A{
private:
    int pus;
public:
    int id;
    A(){}
    A(int id,int pus):id(id),pus(pus){}
    void dis(){
        cout<<"id:"<<this->id<<"    "<<"pus: "<<this->pus<<"    ";
    }
    ~A(){cout<<"A被回收"<<endl;}
};
//1.第一种是public继承
/**
 * 规则：父类中的private子类无法继承
 * 如果用public继承,就是原样
 * 如果是protoed，那么继承都是protoed
 * 如果是private,那么就是private
 * 多态性体现于重载和重写
 */
class B:public A{
    int age;
public:
    B(int id,int pus,int age):A(id,pus),age(age){}
    //在重载的父类的时候就要先调用父类的函数
    void dis(){
        //引用父类方法
        A::dis();
        cout<<"age,    "<<this->age<<endl;
    }

    ~B(){
        cout<<"B被释放"<<endl;
    }
};


void exthend_show(){
    {
        B b(1,2,3);
        b.dis();
    }
}




void class_show(){

    /**
     *  这个例子可以看出c++和c的内存位置不一样，
     *  所以如果是malloc的话delete就不好使了.
     *  如果是new 的话free和delte都可以触发析构函数
     */


    {
        person p(12,"xxx");
        p.dis();
    }
    person *a = new person(15,"aaaa");
    a->dis();
    delete a;


    person *b = (person*) malloc(sizeof(person));
    free(b);

    person *c = new person(15,"ccc");
    c->dis();
    free(c);
    person *d = (person*) malloc(sizeof(person));
    delete d;

    /**
     * 这里实验拷贝构造函数
     */
    person p(111,"aaa");
    person p2 = p;




}


void quote_show(){
    /**
     * 概念：
     * 值的类型：属于左值（左值  ， 范左值 ），属于右值（ 将亡值  ，纯右值 ）
     * 解析：
     * int a = 1
     * 左值     将亡值     纯右值
     * a                   1
     * 什么是左值引用????
     * 通常来说等号的左边就是左值，等号的右边就是右值
     * 如果将一个右值变成左值，在变得过程中的那一瞬间1就会变成将亡值。
     * 相当于当b给a叫了一个别名而已b的本质还是a还是1
     *
     * 将亡值底层,相当于两个标签都是1的
     * a:b:
     *   dw  1
     *
     *
     *
     *
     *
     */

    int a = 12;
    int &b = a;
    //左值引用
    cout<<b<<endl;
    cout<<a<<endl<<endl;

    b = 90;
    cout<<b<<endl;
    cout<<a<<endl;


}