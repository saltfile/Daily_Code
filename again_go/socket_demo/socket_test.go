package socket_demo

import (
	"fmt"
	"net"
	"os"
	"testing"
)

func tcp_server() {
	service := ":8997"
	tcpAddr, err := net.ResolveTCPAddr("tcp4", service)
	checkErrserver(err)
	listener, err := net.ListenTCP("tcp", tcpAddr)
	checkErrserver(err)
	for {
		conn, err := listener.Accept()
		if err != nil {
			continue
		}
		go handleClient(conn)
	}

}
func checkErrserver(err error) {
	if err != nil {
		fmt.Fprintf(os.Stderr, "Fatal error: %s", err.Error())
		os.Exit(1)
	}
}
func handleClient(conn net.Conn) {
	defer conn.Close()
	var buf [512]byte
	for {
		n, err := conn.Read(buf[0:])
		if err != nil {
			break
		}
		rAddr := conn.RemoteAddr()

		fmt.Println("receive from client", rAddr.String(), string((buf[0:n])))
		str := "我收到了："
		str = str + string((buf[0:n]))
		_, err2 := conn.Write([]byte(str))
		if err2 != nil {
			break
		}
	}
}

func TestTcp1(t *testing.T) {
	tcp_server()
}
