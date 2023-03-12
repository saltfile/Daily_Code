cmake_minimum_required(VERSION 3.23)
project(concurrent_service)


set(CMAKE_CXX_STANDARD 11)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_CXX_EXTENSIONS OFF)


include(FindProtobuf)
find_package(Protobuf REQUIRED)
include_directories(/usr/bin/protobuf)




add_executable(concurrent_service main.cpp selector/sele_base.h selector/ser_selector.cpp poll/ser_poll.cpp poll/poll_base.h epoll/ser_epoll_lt.cpp epoll/epoll_base.h epoll/ser_epoll_et.cpp protos/add.pb.cc)
TARGET_LINK_LIBRARIES(concurrent_service pthread protoc)