//
// Created by saltfish on 23-3-28.
//
#include <functional>
#include "my_base.h"







bool isVaild(int i,int j,char val,vector<vector<char>>& map){
    for (int k = 0; k < 6; ++k) {
        if (map[i][k] == val){
            return false;
        }
    }
    for (int k = 0; k < 6; ++k) {
        if (map[k][j] == val){
            return false;
        }
    }
    return true;
}





bool backing(vector<vector<char>>& map){
    for (int i = 0; i < map.size(); ++i) {
        for (int j = 0; j < map[0].size(); ++j) {
            if (map[i][j] != 0)continue;
            for (char k = '1'; k <= '6'; ++k) {
                if (isVaild(i,j,k,map)){
                    map[i][j] = k;
                    if ( backing(map))return true;
                    map[i][j] = 0;
                }
            }
            return false;

        }
    }
    return true;
}
















/**
 * 这里的class也可以写成struct
 *
 * class和struct有什么区别?????
 *
 * 如果你的类上写的就是class那么默认的就是private
 * 而struct相反默认是pubilc
 *
 */




class person {
private:
    int id = 1;
    string name = "aaa";
public:

    person() {}

    person(int id, string name) : id(id), name(name) {}

    //拷贝构造函数
    person(person &p) {
        cout << p.name << "被拷贝了" << endl;
        this->id = p.id;
        this->name = p.name;
    }

    void dis() {
        cout << "id :" << this->id << "name :" << this->name << endl;
    }

    ~person() {
        cout << "被回收了" << endl;
    }

};

//继承一共有三种

class A {
private:
    int pus;
public:
    int id;

    A() {}

    A(int id, int pus) : id(id), pus(pus) {}

    virtual void dis() {
        cout << "id:" << this->id << "    " << "pus: " << this->pus << "    ";
    }

    virtual ~A() { cout << "A被回收" << endl; }
};
//1.第一种是public继承
/**
 * 规则：父类中的private子类无法继承
 * 如果用public继承,就是原样
 * 如果是protoed，那么继承都是protoed
 * 如果是private,那么就是private
 * 多态性体现于重载和重写
 */
class B : public A {
    int age;
public:
    B(int id, int pus, int age) : A(id, pus), age(age) {}

    //在重载的父类的时候就要先调用父类的函数
    void dis() {
        //引用父类方法
        A::dis();
        cout << "age,    " << this->age << endl;
    }

    ~B() {
        cout << "B被释放" << endl;
    }
};

//菱形继承
class N {
public:
    N() {
        cout << "N" << endl;
    }

    ~N() {
        cout << "~N" << endl;
    }
};


class M : virtual public N {
public:
    M() {
        cout << "M" << endl;
    }

    ~M() {
        cout << "~M" << endl;
    }
};


class P : virtual public N {
public:
    P() {
        cout << "P" << endl;
    }

    ~P() {
        cout << "~P" << endl;
    }
};


class Z : public P, public M {
public:
    Z() {
        cout << "Z" << endl;
    }

    ~Z() {
        cout << "~Z" << endl;
    }
};

//这里构造多了一个N是因为菱形继承多了两次构造N

void rhombus_show() {
    Z *z = new Z();
    delete z;
}


void exthend_show() {
    {
        B b(1, 2, 3);
        b.dis();
    }

    //如果如下这样A没有写虚方法会导致泄露 输出id:2    pus: 4    A被回收
    A *f = new B(2, 4, 5);
    f->dis();
    delete f;

}


void class_show() {

    /**
     *  这个例子可以看出c++和c的内存位置不一样，
     *  所以如果是malloc的话delete就不好使了.
     *  如果是new 的话free和delte都可以触发析构函数
     */


    {
        person p(12, "xxx");
        p.dis();
    }
    person *a = new person(15, "aaaa");
    a->dis();
    delete a;


    person *b = (person *) malloc(sizeof(person));
    free(b);

    person *c = new person(15, "ccc");
    c->dis();
    free(c);
    person *d = (person *) malloc(sizeof(person));
    delete d;

    /**
     * 这里实验拷贝构造函数
     */
    person p(111, "aaa");
    person p2 = p;


}


/**
 * c++友元展示
 */


class T {
    int id;
    string name;

    //当这里加上firend之后就对函数暴露自己的属性了
    friend void dis(T &t);

    friend class Dis;

public:
    T(int id, string name) : id(id), name(name) {}
};

void dis(T &t) {
    //引用的时候无法去打印一个private的属性
    cout << t.id << "     " << t.name << endl;
}

class Dis {
private:
    T t;
public:
    void dis() {
        cout << this->t.id << "    " << this->t.name << endl;
    }

    Dis(T &t) : t(t) {}
};


void firend_show() {
    T t(1, "ttt");
    dis(t);
    Dis u(t);
    u.dis();
}


class Idao {
public:
    virtual void save() = 0;//这里类比为抽象类根接口一样
};

class ADao : public Idao {
public:
    void save() {
        cout << "A" << "实现接口" << endl;
    }
};

class BDao : public Idao {
public:
    void save() {
        cout << "B" << "实现接口" << endl;
    }
};


void interface_show() {
    Idao *a = new ADao();
    Idao *b = new BDao();
    a->save();
    b->save();

    delete a;
    delete b;


}
class tuys{
    int *id;
public:

    tuys(tuys &p){
        this->id = p.id;
        cout<<"复制构造函数"<<endl;
    }

    tuys(tuys &&p){
        this->id = new int;
        *this->id = *p.id;
        p.id = nullptr;
        cout<<"移动构造函数"<<endl;
    }

    void operator=(tuys &p){
        this->id = p.id;
        cout<<"赋值运算符"<<endl;
    }

    tuys() {
        this->id = new int;
        *this->id = 12;
    }
};

void quote_show() {
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
    cout << b << endl;
    cout << a << endl << endl;

    b = 90;
    cout << b << endl;
    cout << a << endl;

    tuys p1;
    //move就是左值变右值的方法
    tuys p2= move(p1);

    tuys p3;
    p3 = p1;

}


/**
 * class类外实现
 */

class example_s {
    int id;
public:
    static int x1;

    int dis(int a);

    static int dis2(int a);
};

int example_s::x1;

int example_s::dis(int a) {
    this->x1 = a;
}

int example_s::dis2(int a) {
    cout << "静态函数没有this指针" << endl;
}

void clazz_out() {


    example_s *e = new example_s();
    cout << e->x1;
    e->dis(14);
    e->dis2(45);
    cout << e->x1;
}





/**
 * 模板编程
 * @tparam T
 */

/**
 * 注意这里的模板是连着的
 * @tparam T
 * @param a
 * @param c
 * @return
 */
template<typename T>
T mypius(T a, T c) {
    return a + c;
}

/**
 * 想当与在编译的时候生成了一个
 * int mypius(int a,int b)
 */

template<typename T>
class temp {
    T id;
public:
    temp(T id) : id(id) {}

    void dis() {
        cout << this->id << endl;
    }
};

//模板还可以是动态参数
template<typename T=int, int size = 5>
T func_tem(T a, T (&arr)[size]) {
    cout << a << endl;

    for (int i = 0; i < size; ++i) {
        cout << arr[i] << "   ";
    }
    cout << endl;
    return a;


}


void template_test() {

    cout << mypius(1, 4) << endl;
    cout << mypius(1.3f, 5.6f) << endl;

    temp<string> *p = new temp<string>("xxx");
    temp<int> *v = new temp<int>(12);
    p->dis();
    v->dis();

    int arr[10] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    cout << func_tem(1, arr);


}

/**
 * c++运算符重载
 */
class pointer {
    int x;
    int y;
public:
    pointer(int x, int y) : x(x), y(y) {}

    pointer operator+(pointer &other) {
        return pointer(this->x + other.x, this->y + other.y);
    }

    void operator<<(pointer &other) {
        printf("%d,%d\n", other.x, other.y);
    }

    pointer operator++() {
        return pointer(this->x += 1, this->y += 1);
    }

    void operator++(int) {
        this->x += 1;
        this->y += 1;
    }

    void dis() {
        cout << this->x << "  " << this->y << endl;
    }
};


void operator_test() {
    pointer a(1, 4), b(6, 8);
    a.dis();
    a = a + b;
    a.dis();
    a << b;
    ++a;
    a++;
    a.dis();

}


/**
 * 智能指针
 * 需要用来包一下做垃圾回收
 */

class expmle {
    int id;
public:
    expmle(int id) : id(id) {}

    ~expmle() {
        cout << "我被回收了" << endl;
    }

    void dis() {
        cout << this->id << endl;
    }
};


class my_ptr {
    expmle *p;
public:
    my_ptr(expmle *p) : p(p) {}

    ~my_ptr() {
        delete p;
    }

    expmle *operator->() {
        return this->p;
    }

};

template<typename T>
class smart_point {
    T *p;
public:
    smart_point(T *p) : p(p) {}

    ~smart_point() {
        delete p;
    }

    T *operator->() {
        return this->p;
    }
};

void smart_point_test() {
    //普通的指针
    my_ptr o(new expmle(10));
    o->dis();

    //堆内
    smart_point<expmle> *po = new smart_point<expmle>(new expmle(65));
    po->operator->()->dis();
    delete po;

    //真正的智能指针
    smart_point<expmle> u(new expmle(45));
    u->dis();

}


/**
 * c++ lambda 表达式
 *
 * auto原本是用坐局部变量的
 * 现在是用来做类型推导的
 * decltype可以和前面的类型生成相同的类型的东西具体lambda_test()
 * 这个就像是模板仿着谁做出谁
 */
template<typename T1, typename T2>
auto lams_test1(T1 a, T2 b) -> decltype(a + b) {
    return a + b;
}

int munsd(function<int(int, int)> f, int a, int b) {
    return f(a, b);
}


void lambda_test() {

    auto a = 1;//auto自动推导
    cout << a << endl;

    //decltype作用
    int v = 1;
    decltype(v) b;
    b = 12;
    cout << b << endl;

    //auto函数
    cout << lams_test1(1, 4) << endl;
    cout << lams_test1(1, "aaa") << endl;

    int d = 122;

    //[]引用外部的东西
    auto myplus = [=, &d](int a, int b) {
        d++;
        return a + b + d;
    };
    cout << myplus(45, 23) << "," << d << endl;
    //function用来做高阶函数
    auto funs = [](int a, int b) { return a + b; };
    auto fs = [](int a, int b) { return a * b; };
    cout << munsd(funs, 67, 88) << endl;
    cout << munsd(fs, 5, 6) << endl;
};


/**
 * 强制类型转换只能从左值变成右值
 * 左值可以转右值
 * 但右值绝不可以变左值
 */

void xxx(int &a) {
    cout << "左" << endl;
}

void xxx(int &&a) {
    cout << "右" << endl;
}
/**
 * 完美转发
 * 模板的&&既可以接左值又可以接右值
 */
template<typename T>
 void tans(T &&t){
    xxx(forward<T&&>(t));
 }

void type_zuo() {

    auto mus = []() { return 1; };
    //这些都是右值 变量名是左值，引用是右值
    int b = mus();
    int a = 1;
    int c = a + 2;
    //这样两边都是左值，左值只是对右值的引用
    int *d = &a;

    //普通的左边不能给引用的右边起别名
    //如下
//    int &s = 1;
    //但是const可以
    const int &s = 1;
    int bv = 2;
    const int &cs = bv;
    //所以const修饰的左值引用可以引用左也可以引右
    //下面是右值引用 &&用来表达右值
    int &&u = 1;
    //变左值
    int t = u;


    int ty = 1;
    xxx(ty);

    //完美转发
    tans(1);
    tans(ty);


}




