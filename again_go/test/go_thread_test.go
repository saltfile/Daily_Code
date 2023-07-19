package test

import (
	"fmt"
	"testing"
	"time"
)

//go的协程

/**
首先先阐述一下go的协程是什么
与之前的线程不同，协程可以理解为是
用户态自己的线程任务交给内核态线程去做
，就像是处理器处理自己的任务一样


*/

func runable() {
	fmt.Println("开始执行协程任务了")
	time.Sleep(time.Second * 2)
	fmt.Println("协程执行完了")
}

func TestSrun(t *testing.T) {
	// 切记，如果直接调用协程的话主线程是不会等的
	go runable()
	//强行等几秒
	time.Sleep(time.Second * 4)
	t.Log("主进程结束")
}

func funchan1(ch *chan string) {

	fmt.Println("我是p1 ,我要放入值")
	time.Sleep(time.Second * 3)
	*ch <- "12"
	fmt.Println("我是p1,我放好了")

}

func funchan2(ch *chan string) {
	fmt.Println("我是p2，我要拿值")
	val := <-*ch
	time.Sleep(time.Second * 2)
	fmt.Println("我是p2，我拿到了值：", val)

}

// go 协程的共享变量模式
// 他不像其他语言一样搞什么volitale啥的用的是channl
func TestChan(t *testing.T) {
	//首先声明一个只传字符串的channel
	ch := make(chan string)
	go funchan1(&ch)
	go funchan2(&ch)
	time.Sleep(time.Second * 6)

}
