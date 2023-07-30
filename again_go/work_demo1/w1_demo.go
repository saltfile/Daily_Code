package main

import (
	"fmt"
	"time"
)

//有a,b两个协程，要求a执行完才开始执行b，b执行完在执行a

func ATask(ch *chan bool) {

	time.Sleep(2 * time.Second)
	fmt.Println("我是A协程")
	*ch <- true
}

func Btask(ch *chan bool) {
	time.Sleep(2 * time.Second)
	fmt.Println("我是B协程")
	*ch <- true
}

func TotalThread() {
	chA := make(chan bool)
	chB := make(chan bool)
	for {
		go ATask(&chA)
		<-chA
		go Btask(&chB)
		<-chB

	}

}
