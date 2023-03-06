package test

import (
	"testing"
)

// map声明
func TestInitMap(t *testing.T) {
	m1 := map[int]int{1: 1, 2: 4}
	t.Log(m1[1])
	m2 := map[int]int{}
	m2[6] = 15
	t.Log(len(m2))
	m3 := make(map[int]int, 10)
	t.Log(len(m3))
}

// 这里是区分mao1是否存在
func TestAccess(t *testing.T) {
	m1 := map[int]int{}
	t.Log(m1[1])
	m1[2] = 0
	t.Log(m1[1])
	t.Log(m1[2])
	//如何区分map的值是否存在
	if v, ok := m1[3]; ok {
		t.Log("key存在", v)
	} else {
		t.Log("key不存在")
	}
}

func TestMapTravel(t *testing.T) {
	m1 := map[int]int{1: 1, 2: 4, 5: 3}

	for k, v := range m1 {
		t.Log(k, v)
	}

}

// 最简单的工厂模式
func TestFactoryMap(t *testing.T) {
	m := map[int]func(op int) int{}
	m[1] = func(op int) int { return op }
	m[2] = func(op int) int { return op * op }
	m[3] = func(op int) int { return op * op * op }
	t.Log(m[1](2), m[2](2), m[3](2))
}

// GO语言中没有对set的实现但可以用map去实现
func TestMapforSet(t *testing.T) {
	set := map[int]bool{}
	set[1] = true
	key := 1
	if set[key] {
		t.Log("1存在", key)
	} else {
		t.Log("不存在", key)
	}
}
