package test

import "testing"

const (
	Monday = iota + 1
	Tuesday
	Wednesday
)

const (
	Ats = 1 << iota
	Bts
	Cts
	Dts
	Ets
)

func TestConstant(t *testing.T) {
	t.Log(Monday, " ", Tuesday)
	t.Log(Ats, " ", Bts)

	//位运算
	s := 7         //0111
	t.Log(s & Ats) //0111 & 0001 = 0001
	t.Log(s | Dts) //0111 | 1000 = 1111
}
