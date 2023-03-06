package test

import (
	"fmt"
	"math/rand"
	"testing"
	"time"
)

func MultValues() (int, int) {
	return rand.Intn(10), rand.Intn(20)
}

func TimeSpent(inner func(op int) int) func(op int) int {
	return func(op int) int {
		start := time.Now()
		ret := inner(op)
		fmt.Println("time spent:", time.Since(start).Seconds())
		return ret
	}
}

func slowFun(op int) int {
	time.Sleep(time.Second * 1)
	return op
}

func TestMV(t *testing.T) {
	a, b := MultValues()
	t.Log(a)
	t.Log(b)
}

func TestFncs(t *testing.T) {
	ts := TimeSpent(slowFun)
	t.Log(ts(10))
}

//可变长参数

func sum(ops ...int) int {
	s := 0
	for _, i := range ops {
		s += i
	}
	return s
}

func TestSum(t *testing.T) {
	t.Log(sum(1, 2, 3, 4, 5, 6, 7))
}

//defer函数就是匿名函数

func TestDefer(t *testing.T) {
	//defer是到最后抛出
	defer func() {
		t.Log("我是defer")
	}()
	//这句在上
	t.Log("Started")
	//就算报错也一样执行
	//panic("Fatal error")
}

// defer举例
func MyTFun(a int) {
	fmt.Println("我是测试我是测试", a)
}

func TestMyFun(t *testing.T) {
	defer MyTFun(5)
	defer MyTFun(2)
	defer MyTFun(4)
	fmt.Println("开始测试")

}
