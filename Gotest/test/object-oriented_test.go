package test

import (
	"fmt"
	"testing"
	"unsafe"
)

//GO语言中没有继承

type Employee struct {
	id   string
	name string
	age  int
}

// (e *Employee)是拿的原地址引用
// (e Employee)这个是重新复制了一份
//所以以后的函数绑定要使用(e *Employee)

//	func (e *Employee) String() string {
//		fmt.Printf("Address is %x", unsafe.Pointer(&e.name))
//		return fmt.Sprintf("ID:%s  Name:%s  Age:%d", e.id, e.name, e.age)
//	}
func (e Employee) String() string {
	fmt.Printf("Address is %x", unsafe.Pointer(&e.name))
	return fmt.Sprintf("ID:%s  Name:%s  Age:%d", e.id, e.name, e.age)
}
func TestSss(t *testing.T) {
	e := Employee{"0", "xxx", 20}
	e1 := Employee{name: "aaa", age: 143}
	//这里的e2是返回的引用/指针，相当于e := &Employee{}
	e2 := new(Employee)

	e2.id = "1"
	e2.name = "bbb"
	e2.age = 1

	t.Log(e.name)
	t.Log(e1.name)
	t.Log(e.String())
	fmt.Printf("name:%x", unsafe.Pointer(&e.name))
}
