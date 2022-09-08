cmake_minimum_required(VERSION 3.17)
project(mypool)

set(CMAKE_CXX_STANDARD 11)

add_executable(mypool main.cpp basic_function.h all_init.cpp)
TARGET_LINK_LIBRARIES(mypool pthread boost_system boost_filesystem rt boost_thread)