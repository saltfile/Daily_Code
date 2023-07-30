package main

import (
	"fmt"
	"sync"
	"time"
)

//有三个协程，每个协程分别打印ABC，每个协程打印5遍

var w4_lock = sync.NewCond(&sync.Mutex{})

var aExtend bool = false
var bExtend bool = false
var cExtend bool = true

func AW4task(ch *chan bool) {
	for i := 0; i < 5; i++ {
		w4_lock.L.Lock()
		for !cExtend {
			w4_lock.Wait()
		}
		fmt.Print("A")
		aExtend = true
		cExtend = false
		w4_lock.Broadcast()
		w4_lock.Wait()
		w4_lock.L.Unlock()
	}
	*ch <- true
}

func BW4task(ch *chan bool) {
	for i := 0; i < 5; i++ {
		w4_lock.L.Lock()
		for !aExtend {
			w4_lock.Wait()
		}
		fmt.Print("B")
		aExtend = false
		bExtend = true
		w4_lock.Broadcast()
		w4_lock.Wait()
		w4_lock.L.Unlock()
	}
	*ch <- true
}

func CW4task(ch *chan bool) {
	for i := 0; i < 5; i++ {
		w4_lock.L.Lock()
		for !bExtend {
			w4_lock.Wait()
		}
		fmt.Print("C")
		if i == 4 {
			//说明是最后一个ABC
			bExtend = true
			cExtend = true
			w4_lock.Broadcast()
			w4_lock.L.Unlock()
			break
		}
		bExtend = false
		cExtend = true
		w4_lock.Broadcast()
		w4_lock.Wait()
		w4_lock.L.Unlock()

	}
	*ch <- true
}

func W4TotalThread() {
	chA := make(chan bool)
	chB := make(chan bool)
	chC := make(chan bool)
	go AW4task(&chA)
	time.Sleep(10 * time.Microsecond)
	go BW4task(&chB)
	time.Sleep(10 * time.Microsecond)
	go CW4task(&chC)

	for {
		select {
		case <-chA:
		case <-chB:
		case <-chC:
			return
		}
	}

}
