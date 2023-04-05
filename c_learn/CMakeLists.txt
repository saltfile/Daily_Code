cmake_minimum_required(VERSION 3.23)
project(c_learn)

set(CMAKE_CXX_STANDARD 11)

add_executable(c_learn main.cpp class_base.cpp)
TARGET_LINK_LIBRARIES(c_learn pthread)
