cmake_minimum_required(VERSION 3.17)
project(CPU)

set(CMAKE_CXX_STANDARD 14)

add_executable(CPU main.cpp pub_references.h mem_fun.cpp str_fun.cpp cpu_fun.cpp list.cpp log_fun.cpp cpu.cpp)
TARGET_LINK_LIBRARIES(CPU pthread)