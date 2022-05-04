cmake_minimum_required(VERSION 3.17)
project(elsdemo)

set(CMAKE_CXX_STANDARD 14)

add_executable(elsdemo main.cpp Myany.h read_config.cpp list_fun.cpp log_fun.cpp mysql_fun.cpp)