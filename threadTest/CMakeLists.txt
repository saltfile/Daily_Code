cmake_minimum_required(VERSION 3.17)
project(threadTest)

set(CMAKE_CXX_STANDARD 11)

add_executable(threadTest main.cpp basic_func.h example1.cpp expampe2.cpp expampe3.cpp example4.cpp)
TARGET_LINK_LIBRARIES(threadTest pthread boost_system boost_filesystem rt boost_thread)