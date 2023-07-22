package test

import (
	"fmt"
	"math/rand"
	"strconv"
	"sync"
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
	//首先声明一个只传字符串且长度为5的channel
	ch := make(chan string, 5)
	go funchan1(&ch)
	go funchan2(&ch)
	time.Sleep(time.Second * 6)

}

//注意这里的ch当使用完了之后生产者的一端会关闭，消费者会继续消费，如果真的空了就消费默认值

// 利用管道特性停下来
// 生产者
func funpro(ch *chan string) {

	for i := 0; i < 10; i++ {
		time.Sleep(time.Duration(rand.Int31n(5)) * time.Second)
		var st string = "产品"
		st = st + strconv.Itoa(i)
		*ch <- st
	}

}

// 消费者
func funcon(ch *chan string, poer *chan bool) {
	flag := 1
	for {
		val := <-*ch
		fmt.Println("开始消费", val)
		flag++
		if flag >= 10 {
			*poer <- true
		}
	}

}

func TestChProCon(t *testing.T) {
	ch := make(chan string, 5)
	poer := make(chan bool)
	go funpro(&ch)
	go funcon(&ch, &poer)
	<-poer

}

//如果有一个分开的任务需要完成之后一起汇报该怎么做？

func funtask(ch *chan string, i int) {
	time.Sleep(time.Duration(rand.Int31n(10)) * time.Second)
	val := "任务"
	val = val + strconv.Itoa(i) + "已完成"
	*ch <- val
}

func TestSelect(t *testing.T) {
	ch1 := make(chan string)
	ch2 := make(chan string)
	ch3 := make(chan string)

	go funtask(&ch1, 1)
	go funtask(&ch2, 2)
	go funtask(&ch3, 3)
	for {
		select {
		case v1 := <-ch1:
			t.Log(v1)
		case v2 := <-ch2:
			t.Log(v2)
		case v3 := <-ch3:
			t.Log(v3)
		}

	}

}

//go的锁

// 声明一个互斥锁
var lock sync.Mutex
var num int = 0

func add1() {
	lock.Lock()
	defer lock.Unlock()
	num++
}

func funadd() {
	for i := 0; i < 100; i++ {
		add1()
		time.Sleep(time.Microsecond * time.Duration(rand.Int31n(10)))
	}

}

func TestFuncs(t *testing.T) {
	go funadd()
	go funadd()
	go funadd()
	go funadd()
	go funadd()
	go funadd()
	go funadd()
	go funadd()
	time.Sleep(time.Second * 5)
	t.Log(num)
}

// 读写锁
var fil int = 0

// 声明读写锁
var rwlock sync.RWMutex

func recv() {
	rwlock.RLock()
	defer rwlock.RUnlock()
	time.Sleep(time.Duration(rand.Int31n(10000)) * time.Microsecond)
	fmt.Println(fil)

}

func wire(ch *chan bool) {
	rwlock.Lock()
	defer rwlock.Unlock()
	for {
		time.Sleep(time.Duration(rand.Int31n(200)) * time.Microsecond)
		fil = fil + 2
		if fil == 1000 {
			*ch <- true
		}
	}

}

func TestRWLock(t *testing.T) {
	ch := make(chan bool)
	go wire(&ch)
	go wire(&ch)
	go recv()

	<-ch
	t.Log(fil)
}

// countdownlach
func TestCouD(t *testing.T) {
	cond := sync.NewCond(&sync.Mutex{})
	var wait sync.WaitGroup
	wait.Add(11)
	for i := 0; i < 10; i++ {
		go func(num int) {
			defer wait.Done()
			t.Log(num, "被添加")
			cond.L.Lock()
			cond.Wait()
			t.Log(num, "开始执行")
			cond.L.Unlock()
		}(i)
	}

	time.Sleep(2 * time.Second)
	go func() {
		defer wait.Done()
		t.Log("开始")
		//唤醒所有的逻辑
		cond.Broadcast()
	}()
	wait.Wait()

}
