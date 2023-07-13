package main

import (
	"errors"
	"fmt"
)

type list_node struct {
	data string
	next *list_node
}

type link_list struct {
	list *list_node
	size int
}

func NewLinkList() (error, link_list) {
	return nil, link_list{
		list: nil,
		size: 0,
	}
}

func (this *link_list) add(val string) {
	var node *list_node = new(list_node)
	node.next = nil
	node.data = val
	if this.list == nil {
		this.list = node
		this.size++
		return
	}

	ptr := this.list

	for ptr.next != nil {
		ptr = ptr.next
	}

	ptr.next = node
	this.size++

}

func (this *link_list) remove(idx int) (error, int) {
	if idx < 0 || idx > this.size-1 {
		return errors.New("Linklist:违法下标"), 0
	} else if idx == 0 {
		ptr := this.list.next
		this.list = ptr
		this.size--
		return nil, 1
	} else if idx == this.size-1 {
		ptr := this.list
		for ptr.next.next != nil {
			ptr = ptr.next
		}
		ptr.next = nil
		this.size--
		return nil, 1
	}

	ptr := this.list
	for i := 0; i < idx-1; i++ {
		ptr = ptr.next
	}
	pnext := ptr.next.next
	ptr.next = pnext
	this.size--
	return nil, 1
}

func (this *link_list) get(idx int) string {
	ptr := this.list
	for i := 0; i < idx-1; i++ {
		ptr = ptr.next
	}
	return ptr.data
}

func (this *link_list) reversal() {

}

func (this link_list) Print() {
	fmt.Print("[ ")
	ptr := this.list
	for ptr.next != nil {
		fmt.Print(ptr.data, ",")
		ptr = ptr.next
	}
	fmt.Print(ptr.data)
	println("  ]")
}

func (this link_list) lentgh() int {
	return this.size
}
