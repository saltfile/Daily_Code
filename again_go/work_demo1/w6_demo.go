package main

import (
	"fmt"
	"math/rand"
	"sync"
	"time"
)

// 假设有500张票 有三个窗口同时卖票请模拟同时买票的场景
var vote int = 10
var w6_lock = sync.NewCond(&sync.Mutex{})

func consumer(num int) {
	for {

		w6_lock.L.Lock()
		if vote == 0 {
			fmt.Println("窗口", num, ":票买光了")
			w6_lock.Wait()
			w6_lock.L.Unlock()
		}
		time.Sleep(time.Second * time.Duration(rand.Int31n(3)))
		vote--
		fmt.Println("卖出", vote)
		w6_lock.L.Unlock()

	}
}

func W6TotalThread() {
	go consumer(1)
	go consumer(2)
	go consumer(3)
	for {

	}
}
