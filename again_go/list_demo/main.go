package main

import "fmt"

func main() {
	_, list := NewLinkList()
	list.add("aaaa")
	list.add("bbbb")
	list.add("cccc")
	list.add("dddd")
	list.Print()
	fmt.Println(list.lentgh())
	list.remove(3)
	list.Print()
	fmt.Println(list.lentgh())

}
