cmake_minimum_required(VERSION 3.23)
project(concurrent_service)

set(CMAKE_CXX_STANDARD 11)

add_executable(concurrent_service main.cpp selector/sele_base.h selector/ser_selector.cpp poll/ser_poll.cpp poll/poll_base.h)
TARGET_LINK_LIBRARIES(concurrent_service pthread)