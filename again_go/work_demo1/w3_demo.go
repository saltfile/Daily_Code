package main

import (
	"fmt"
	"sync"
)

//有两个协程，一个协程打印1~52，另一个打印A~Z     打印的顺序为12A34B56C

var w3_lock = sync.NewCond(&sync.Mutex{})

func AW3Task(ch *chan bool) {
	for i := 1; i <= 52; i += 2 {

		w3_lock.L.Lock()

		fmt.Printf("%d%d", i, i+1)
		w3_lock.Signal()
		w3_lock.Wait()
		w3_lock.L.Unlock()
	}
	*ch <- true
}

func BW3Task(ch *chan bool) {
	var i byte
	for i = 'A'; i <= 'Z'; i++ {
		w3_lock.L.Lock()
		fmt.Printf("%c", i)
		w3_lock.Signal()
		w3_lock.Wait()
		w3_lock.L.Unlock()
	}
	*ch <- true
}

func W3TotalThread() {
	chA := make(chan bool)
	chB := make(chan bool)
	go AW3Task(&chA)
	go BW3Task(&chB)

	for {
		select {
		case <-chA:
		case <-chB:
			return
		}
	}

}
