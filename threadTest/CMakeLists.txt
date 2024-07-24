cmake_minimum_required(VERSION 3.17)
project(threadTest)

set(CMAKE_CXX_STANDARD 11)

add_executable(threadTest main.cpp basic_func.h example1.cpp example2.cpp example3.cpp example4.cpp example5.cpp example6.cpp example7.cpp exampe8.cpp expampe9.cpp expmlpe10.cpp design_mode.cpp time_wheel_test.cpp time_wheel_test.h)
TARGET_LINK_LIBRARIES(threadTest pthread boost_system boost_filesystem rt boost_thread)