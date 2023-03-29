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
    virtual void dis(){
        cout<<"id:"<<this->id<<"    "<<"pus: "<<this->pus<<"    ";
    }
    virtual ~A(){cout<<"A被回收"<<endl;}
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

//菱形继承
class N{
public:
    N(){
        cout<<"N"<<endl;
    }
     ~N(){
        cout<<"~N"<<endl;
    }
};


class M:virtual public N{
public:
    M(){
        cout<<"M"<<endl;
    }
    ~M(){
        cout<<"~M"<<endl;
    }
};


class P:virtual public N{
public:
    P(){
        cout<<"P"<<endl;
    }
    ~P(){
        cout<<"~P"<<endl;
    }
};


class Z:public P,public M{
public:
    Z(){
        cout<<"Z"<<endl;
    }
    ~Z(){
        cout<<"~Z"<<endl;
    }
};

//这里构造多了一个N是因为菱形继承多了两次构造N

void rhombus_show(){
    Z *z = new Z();
    delete z;
}



void exthend_show(){
    {
        B b(1,2,3);
        b.dis();
    }

    //如果如下这样A没有写虚方法会导致泄露 输出id:2    pus: 4    A被回收
    A *f = new B(2,4,5);
    f->dis();
    delete f;

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


/**
 * c++友元展示
 */


class T{
    int id;
    string name;
    //当这里加上firend之后就对函数暴露自己的属性了
    friend void dis(T &t);
    friend class Dis;
public:
    T(int id,string name):id(id),name(name){}
};

void dis(T &t){
    //引用的时候无法去打印一个private的属性
    cout<<t.id<<"     "<<t.name<<endl;
}

class Dis{
private:
    T t;
public:
    void dis(){
        cout<<this->t.id<<"    "<<this->t.name<<endl;
    }
    Dis(T &t):t(t){}
};


void firend_show(){
    T t(1,"ttt");
    dis(t);
    Dis u(t);
    u.dis();
}




class Idao{
public:
    virtual void save()=0;//这里类比为抽象类根接口一样
};

class ADao:public Idao{
public:
    void save(){
        cout<<"A"<<"实现接口"<<endl;
    }
};
class BDao:public Idao{
public:
    void save(){
        cout<<"B"<<"实现接口"<<endl;
    }
};





void interface_show(){
    Idao *a = new ADao();
    Idao *b = new BDao();
    a->save();
    b->save();

    delete a;
    delete b;


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