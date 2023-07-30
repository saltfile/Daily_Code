package main

import (
	"sync"
	"testing"
)

// 有a,b两个协程，要求a执行完才开始执行b，b执行完在执行a
func TestW1(t *testing.T) {

	var wg sync.WaitGroup
	wg.Add(1)
	go func() {
		defer wg.Done()
		TotalThread()
	}()
	wg.Wait()

}

// 两个协程轮流打印数字1~100
func TestW2(t *testing.T) {
	var wg sync.WaitGroup
	wg.Add(1)
	go func() {
		defer wg.Done()
		TotalFun()
	}()
	wg.Wait()
}

// 有两个协程，一个协程打印1~52，另一个打印A~Z     打印的顺序为12A34B56C
func TestW3(t *testing.T) {
	var wg sync.WaitGroup
	wg.Add(1)
	go func() {
		defer wg.Done()
		W3TotalThread()
	}()
	wg.Wait()

}

// 有三个协程，每个协程分别打印ABC，每个协程打印5遍
func TestW4(t *testing.T) {
	var wg sync.WaitGroup
	wg.Add(1)
	go func() {
		defer wg.Done()
		W4TotalThread()
	}()
	wg.Wait()
}

func TestW6(t *testing.T) {
	var wg sync.WaitGroup
	wg.Add(1)
	go func() {
		defer wg.Done()
		W6TotalThread()
	}()
	wg.Wait()
}
