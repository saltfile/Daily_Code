package test

import (
	"errors"
	"fmt"
	"testing"
)

// 这是常量
const (
	a = iota + 1
	b
	c
	de
)

// go的常用类型是int int8 int16 int32 int64
// 无符号整型 uint uint8 uint16 uint32 uint64
func TestInt_type(t *testing.T) {
	var i = 10
	t.Log("hello", i)

}

func TestString_type(t *testing.T) {
	var fg string = ""
	t.Log(fg)
}

//分支语句

func TestCheck(t *testing.T) {
	i := 10
	if i > 10 {
		t.Log("我大于10")
	} else if i == 10 {
		t.Log("我等于10")
	} else {
		t.Log("我没有大于10")
	}

	num := 12
	switch num {
	case 12:
		t.Log("这是12")
		num = 11
	default:
		t.Log(num)
	}
	//由于go的每一次都执行一次case不能范围执行所以延伸以下方式

	num2 := 1

	switch num2 {

	case 12:
		t.Log("这是12")
		num2 = 11
		fallthrough //使用这个就可以向下延伸
	default:
		t.Log(num)
	}

}

// 循环语句
func TestFor(t *testing.T) {
	//最常见的
	sum := 10
	for i := 0; i < 100; i++ {
		sum += 10
	}
	t.Log(sum)

	//while的for写法
	byd := 0

	for byd < 4 {
		sum -= 100
		byd++
	}

	t.Log(sum)

	//死循环

	i := 1
	for {
		i++
		if i%2 != 1 {
			continue
		}
		if i >= 10 {
			break
		}
		sum += 600
	}

	t.Log(sum)

}

//数组语句

func TestArr(t *testing.T) {
	arr1 := [4]string{"aaa", "bbb", "ccc", "ddd"}
	t.Log(arr1[0])

	arr2 := [...]string{"a1", "a2", "a3", "a4", "a5"}
	t.Log(arr2[0])

	arr3 := [2]string{0: "a"}
	t.Log(arr3[0], "显示", arr3[1])

	//遍历集合类型foreach
	for _, val := range arr2 {
		t.Log(val)
	}
	for idx, val := range arr2 {
		fmt.Printf("%d,%s \n", idx, val)
	}

}

//切片语句

/**
切片是一个结构体
属性：数据，长度，容量
从某一个数组中切出一部分
*/

func TestVuq(t *testing.T) {
	arr := [6]string{"aaa", "bbb", "ccc", "ddd", "eee", "fff"}
	//切片的左开右闭 大于等于2小于5
	slic := arr[2:5]

	t.Log(slic)

	slic2 := arr[:5]
	t.Log(slic2)
	slic3 := arr[2:]
	t.Log(slic3)

	//自定义切片
	//长度容量为5
	slic4 := make([]string, 5)
	slic4[0] = "asfff"
	slic4[1] = "asd"
	t.Log(cap(slic4))
	t.Log(len(slic4))
	//长度是5容量100
	slic5 := make([]string, 5, 100)
	t.Log(cap(slic5))
	t.Log(len(slic5))
	slic5[1] = "sadasd"
	t.Log(slic5)

	//这个就是切片没有长度标识
	slic6 := []string{"asd", "dsasfg"}
	t.Log(cap(slic6))
	t.Log(len(slic6))

	slic7 := append(slic6, "ads")
	t.Log(cap(slic7))
	t.Log(slic7)

	slic8 := append(slic7, slic4...)
	t.Log(cap(slic8))
	t.Log(slic8)

}

func TestMaps(t *testing.T) {
	//增
	smap := make(map[string]int)
	smap["aa"] = 1
	smap["bb"] = 2
	t.Log(smap)

	smap2 := map[string]int{"aa": 1, "bb": 2}
	t.Log(smap2)

	//删
	delete(smap2, "aa")

	//改
	smap2["bb"] = 4
	t.Log(smap2)

	//查
	res := smap2["bb"]
	t.Log(res)

	//遍历
	for key, val := range smap {
		t.Log("that key is ", key, ", that value is", val)
	}

	for key := range smap {
		t.Log(smap[key])
	}

}

// 字符串切片,如何做汉字切片
func TestStrA(t *testing.T) {
	s := "我爱中国"

	bs := []byte(s)
	//这肯定都是字节啦
	t.Log(bs[0], bs[1])

	//所以需要包装
	runs := []rune(s)

	t.Log(runs)
	t.Log(runs[1])

}

//函数语句

func fun(a int, b int) int {
	return a
}

// 一参+一错
func fun1(a int, b int) (int, error) {
	if a > b {
		return a - b, nil
	} else {
		return 0, errors.New("a参数不能小于b参数")
	}
}

// 可变参，注：可变参一定套在函数最末尾的位置不然会报错
// func fun2(pre ...int,sa string) {
func fun2(jli int, pre ...int) {
	for i := range pre {
		fmt.Println(" ", i)
	}
}

func MyFuncCreate(a int, b int) func() int {
	num := 0
	if a > 10 {
		return func() int {
			num++
			return num
		}
	} else {
		return func() int {
			num--
			return num
		}
	}

}

func TestFun2(t *testing.T) {
	fun2(1, 2, 3, 4, 5, 7, 8, 9, 123)

	//匿名函数
	hunFun := func(aos int) int {
		return aos + 2
	}
	t.Log(hunFun(12))

	usfunc := MyFuncCreate(12, 19)
	t.Log(usfunc())
	t.Log(usfunc())
	t.Log(usfunc())
	t.Log(usfunc())
	t.Log(usfunc())
	t.Log(usfunc())
	t.Log(usfunc())

}

// 定义类型
type String string

func TestIngers(t *testing.T) {
	a := String("jj")
	a.Print()
	a.Modify()
	a.Print()

}

// 值类型的type调用的时候是一个copy出来的副本
func (s String) Print() {
	fmt.Println("that is ", s)
}

// 指针类型的接受者是对本体内存的修改
func (s *String) Modify() {
	*s = String("asd")
}

// 接口
type PersonInface interface {
	introduction()
}

//结构体

type person struct {
	name string
	age  int
}

type student struct {
	p      person
	shcool string
}

func (p person) introduction() {
	fmt.Println("my name is ", p.name, " i am ", p.age)
}

func NewPerson(name string) (*person, error) {
	return &person{name: name}, nil
}

func TestObj(t *testing.T) {
	xm := person{name: "xm", age: 12}
	xm.age = 13
	xm.introduction()

	xh, _ := NewPerson("xh")
	xh.introduction()

	//类型断言判断是否实现接口

	var i interface{} = xh

	if o, ok := i.(PersonInface); ok {
		o.introduction()
	}

}
