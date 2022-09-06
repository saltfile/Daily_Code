package practice

import (
	"fmt"
	"strconv"
	"time"
)

type Producer struct {
	product_channel chan interface{}
}

func New_Producer()*Producer {
	return &Producer{
		product_channel: make(chan interface{}),
	}
}

func Push(product_channel chan interface{}, times time.Duration)  {
	i := 1
	for{
		data := "产品"+strconv.Itoa(i)+"被生产"
		i++
		product_channel<-data
		time.Sleep(time.Second*times)
	}
}

type Consumer struct {
	name string
	waiting_time time.Duration
	product_channel chan interface{}
}

func New_Consumer(name string,nums time.Duration,p *Producer) *Consumer {
	return &Consumer{
		name: name,
		waiting_time: nums,
		product_channel: p.product_channel,
	}
}

func Get(name string,product_channel chan interface{},times time.Duration){
	for{
		value, ok := <-product_channel
		if ok {
			fmt.Println("消费者->",name,"消费了",value)
		}else {

			time.Sleep(time.Second*times)
		}
	}
}
func Producera(ch chan int) {
	i := 1
	for{
		ch <- i
		i++
		time.Sleep(time.Second*1)

	}
	close(ch)
}

func Consumera(id int, ch chan int, done chan bool) {
	for {
		value, ok := <-ch
		if ok {
			fmt.Printf("id: %d, recv: %d\n", id, value)
			
		} else {
			fmt.Printf("id: %d, closed\n", id)
			break
		}
	}
	done <- true
}
func ShowDemo2(){
	ch := make(chan int, 3)
	go Producera(ch)
	coNum := 4
	done := make(chan bool, coNum)
	for i := 1; i <= coNum; i++ {
		go Consumera(i, ch, done)
	}



	for i := 1; i <= coNum; i++ {
		<-done
	}

}

