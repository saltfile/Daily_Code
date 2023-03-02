package main

import (
	"fmt"
	"os"
)

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
