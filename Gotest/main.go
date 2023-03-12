package main

import (
	"fmt"
	"os"
)

//go函数一等公民

/**
可以有多个返回值
所有参数都是值传递
函数可以作为变量的值
函数可以作为参数和返回值
*/

func main() {
	//practice.ShowDemo2();'

	/**
	  GO语言中也不支持传入参数
	要是想要传入参数得用下面的
	*/

	if len(os.Args) > 1 {
		fmt.Println("hello world", os.Args[1])
	}
	/**
	GO语言中main函数不支持任何返回值只通过os.exit来返回状态
	*/
	os.Exit(-2)

}
