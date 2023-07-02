//
// Created by saltfish on 23-7-2.
//

#ifndef LEECODE_BASE_LEECODE_H
#define LEECODE_BASE_LEECODE_H

#include <iostream>
#include <stdio.h>
#include <vector>
#include <string.h>

using namespace std;
  class ListNode {
  public:
      int val;
      ListNode *next;
      ListNode() : val(0), next(nullptr) {}
      ListNode(int x) : val(x), next(nullptr) {}
      ListNode(int x, ListNode *next) : val(x), next(next) {}
  };













#endif //LEECODE_BASE_LEECODE_H
