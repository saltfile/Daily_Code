cmake_minimum_required(VERSION 3.17)
project(epoll_client)

set(CMAKE_CXX_STANDARD 11)

add_executable(epoll_client main.cpp my_bases.h client_start.cpp)
TARGET_LINK_LIBRARIES(epoll_client pthread boost_system boost_filesystem rt boost_thread)