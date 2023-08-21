package socket_demo

import (
	"fmt"
	"net"
)

func tcp_server() {
	service := ":5000"
	tcpAddr, _ := net.ResolveTCPAddr("tcp4", service)

	listener, _ := net.ListenTCP("tcp", tcpAddr)
	for {
		conn, err := listener.Accept()
		if err != nil {
			continue
		}
		go handleClient(conn)
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
		_, err2 := conn.Write([]byte("Welcome client!"))
		if err2 != nil {

		}

	}
}
