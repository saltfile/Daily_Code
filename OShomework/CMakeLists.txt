cmake_minimum_required(VERSION 3.17)
project(OShomework)

set(CMAKE_CXX_STANDARD 11)

add_executable(OShomework main.cpp log_fun.cpp list_fun.cpp MyAll.h)
TARGET_LINK_LIBRARIES(OShomework pthread)