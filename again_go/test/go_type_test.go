package test

import (
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
