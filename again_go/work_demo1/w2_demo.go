package main

import (
	"fmt"
	"sync"
)

// 两个协程轮流打印数字1~100
var num int = 0
var lock *sync.Cond = sync.NewCond(&sync.Mutex{})

func Aadd(ch *chan bool) {
	for {

		lock.L.Lock()

		if num == 100 {
			*ch <- true
			return
		}
		num = num + 1
		fmt.Println("A协程：", num)
		lock.Signal()
		lock.Wait()
		lock.L.Unlock()

	}
}
func Badd(ch *chan bool) {
	for {

		lock.L.Lock()
		if num == 100 {
			*ch <- true
			return
		}
		num = num + 1
		fmt.Println("B协程：", num)
		lock.Signal()
		lock.Wait()
		lock.L.Unlock()

	}
}

func TotalFun() {

	ch := make(chan bool)

	go Aadd(&ch)
	go Badd(&ch)
	<-ch

}
