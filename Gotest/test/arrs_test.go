package test

import (
	"fmt"
	"testing"
)

// 数组的多种形式
func TestArrayInit(t *testing.T) {
	var arr [3]int

	arr1 := [4]int{1, 2, 3, 4}
	arr2 := [...]int{11, 88, 6, 5}

	t.Log(arr, arr1, arr2)

}

// 数组的遍历方法
func TestShowArr(t *testing.T) {
	arr4 := [...]int{11, 5, 6, 8, 7, 5, 6, 4, 5, 5}
	for i := 0; i < len(arr4); i++ {
		fmt.Print(arr4[i], " ")
	}
	fmt.Println()

	for _, e := range arr4 {
		fmt.Print(e, " ")
	}

}

// 多维数组的声明
func TestDuoArr(t *testing.T) {
	c := [3][5]int{{1, 4, 5, 6, 6}, {2, 3, 4, 5, 6}}
	t.Log(c)
}

// 测试数组截取
func TestArrCut(t *testing.T) {
	arr := [10]int{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}

	t.Log(arr[0:6])
	t.Log(arr[:6])

}

// 可变长数组（切片）slice
func TestSlice(t *testing.T) {

	arr1 := []int{}
	t.Log(len(arr1), cap(arr1))
	arr1 = append(arr1, 1)
	arr1 = append(arr1, 2)
	t.Log(len(arr1), cap(arr1), arr1)
	t.Log(arr1[1])

	s2 := make([]int, 3, 5)
	t.Log(len(s2), cap(s2))
	t.Log(s2[0], s2[1], s2[2])
	s2 = append(s2, 3)
	t.Log(s2[0], s2[1], s2[2], s2[3])
	s2 = append(s2, 3)
	s2 = append(s2, 3)
	s2 = append(s2, 3)
	s2 = append(s2, 3)
	t.Log(s2[0], s2[1], s2[2], s2[3], s2[4], s2[6])
}

func TestSlice2(t *testing.T) {
	s := []int{}

	for i := 0; i < 10; i++ {
		//这里的赋值其实就是替换数组
		s = append(s, i)
		//每次扩容乘2
		t.Log(len(s), cap(s))

	}

}

//切片是共享内存模式

func TestSliceShare(t *testing.T) {
	word := []string{"aa", "bb", "cc", "dd", "ee"}
	o1 := word[0:3]
	t.Log(o1)
	//共享之后可以多数组操作
	obj := word[1:5]
	t.Log(obj)
	obj[0] = "xxx"
	t.Log(word, o1)
}
