cmake_minimum_required(VERSION 3.17)
project(vpn)

set(CMAKE_CXX_STANDARD 11)

add_executable(vpn main.cpp MyAll.h log_fun.cpp str_fun.cpp server.cpp cilent.cpp homework4.cpp)
TARGET_LINK_LIBRARIES(vpn pthread boost_system boost_filesystem rt boost_thread)