cmake_minimum_required(VERSION 3.17)
project(CPU)

set(CMAKE_CXX_STANDARD 14)

add_executable(CPU main.cpp pub_references.h cpu_fun.cpp stack.cpp fun_handler.cpp tools.cpp memory_v.cpp)
TARGET_LINK_LIBRARIES(CPU pthread)