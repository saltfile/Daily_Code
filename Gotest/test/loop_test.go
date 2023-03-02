package test

import (
	"fmt"
	"testing"
)

/*
*
GO语言中没有while只有关键字for
正常的使用for九九乘法表
*/
func TestForUse(t *testing.T) {
	for i := 1; i < 10; i++ {
		for j := 1; j <= i; j++ {
			fmt.Print(i, " * ", j, " =  ", i*j, "    ")
		}
		fmt.Println()
	}

}

/*
*
在GO语言当中，switch是默认有break的
如果switch中直接是关系式那么就根else if 是一样的
*/
func TestSwitch(t *testing.T) {
	//正常用法
	sum := 5
	switch sum {
	case 0, 2:
		t.Log("aaaaaa")
	case 3:
		t.Log("bbbbbbbbb")
	default:
		t.Log("不是要选的数")
	}

	num := 1
	//特殊用法
	switch {
	case 0 < num && num < 4:
		t.Log("0000000000")
	case num > 5:
		t.Log("111111111")

	}

}
