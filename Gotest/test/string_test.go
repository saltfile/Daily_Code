package test

import (
	"strconv"
	"strings"
	"testing"
)

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
	t.Logf("unicode %x", c[0])
	t.Logf("utf8 %x", s)
}

// 中文字符串遍历
func TestStingToRange(t *testing.T) {
	s := "我是超级小淘气，面对世界很好奇"
	for _, i2 := range s {
		t.Logf("%[1]c %[1]x", i2)
	}
}

//对应的字符串操作

func TestStringFun(t *testing.T) {
	s := "A,B,C D,E"
	//切割
	words := strings.Split(s, ",")
	//for _, i2 := range words {
	//	t.Log(i2)
	//}
	//链接
	merg := strings.Join(words, "-")
	t.Log(merg)

	//转化整数->字符串
	ns := strconv.Itoa(100)
	t.Log(ns)

	sn, _ := strconv.Atoi(ns)
	t.Log(sn)

}
