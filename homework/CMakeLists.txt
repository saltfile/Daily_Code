cmake_minimum_required(VERSION 3.17)
project(homework)

set(CMAKE_CXX_STANDARD 11)




add_executable(homework main.cpp my_home.h home1.cpp home2.cpp home3.cpp Test.cpp thread_test.cpp RBTree.cpp AVLTree.cpp INTERVAL_Tree.cpp Btree.cpp)
TARGET_LINK_LIBRARIES(homework pthread)