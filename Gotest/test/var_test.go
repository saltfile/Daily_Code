package test

import (
	"fmt"
	"math"
	"testing"
)

func TestBase1(t *testing.T) {
	t.Log("我是一条测试日志")

}

func TestFib(t *testing.T) {
	t.Log("此测试写了一个斐波那契数列的实现过程")

	var dp1 int = 1
	var dp2 int = 1
	fmt.Print(dp1, " ")
	for i := 0; i < 9; i++ {
		fmt.Print(dp2, " ")
		var f int = dp1 + dp2

		dp1 = dp2
		dp2 = f
	}
	fmt.Println()
}

/**
Go的基本数据类型
bool
string
int int8 int16 int32 int64
uint uint8 uint16 uint32 uint64 u intptr
byte
rune
float32 float64
complex64 complex1238

GO语言不允许隐式类型转换
别名和原有类型也不能进行隐式类型转换
*/

// GO对于隐式类型转换的支持
type myint int64

func TestImplicit(t *testing.T) {
	//如下敲会报错不会隐式转换
	//var a int = 1;
	//var b int64;
	//b = a;
	//就算是type也不行只有显示转换
	var d myint = 1
	var c int64
	c = int64(d)
	t.Log(c)

	/**
	GO类型中预定义的值
	math.MaxInt64
	math.MaxFloat64
	math.MaxUint32
	*/

	t.Log(math.MaxInt64)
}

/**
指针类型 不支持指针运算
string 是值类型，默认的是初始化值为空的字符串不是nil
*/

func TestPoint(t *testing.T) {
	a := 1
	ptr := &a
	//ptr = ptr+1 不支持
	t.Log(a, ptr)

}

// 证明string 的初始值为空字符串
func TestString(t *testing.T) {
	var s string
	t.Log("-" + s + "-")

}

//在go语言中是不能有前置自增++或--只有后置

// GO语言中数组是可以去比较的
func TestCompareArr(t *testing.T) {
	a := [...]int{1, 2, 3, 4}
	b := [...]int{1, 2, 3, 4}
	c := [...]int{2, 4, 6, 8}

	t.Log(a == b)
	t.Log(a == c)

}

//特殊位运算符

func TestBitCount(t *testing.T) {
	//&^按位换零
	a := 6     //0110
	a = a &^ 2 //0110 -> 0100
	t.Log(a)

}
