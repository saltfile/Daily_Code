package test

import "testing"

//测试字符串的各种差异

//string是数据类型，不是引用或者指针类型
//string是制度的byteslice len函数可以包含所有有的byte数
//string 的 byte数组可以存档任何数据

func TestStrings(t *testing.T) {
	var s string
	t.Log(s)
	s = "xxx"
	t.Log(len(s))
	//s[1] = '4'注意这里不可变
	s = "\xE4\x77\x65"
	t.Log(s)
	t.Log(len(s))

	//Unicode utf8
	s = "中"
	t.Log(len(s))

	//fune是从字符串中自动取出utf8编码的函数
	c := []rune(s)
	//取出的编码20013
	t.Log(c[0])
	t.Log(s)
}
