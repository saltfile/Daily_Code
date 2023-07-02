//
// Created by saltfish on 23-6-27.
//

#ifndef LEECODE_SERCH_H
#define LEECODE_SERCH_H

#include <iostream>
#include <stdio.h>
#include <vector>
#include <string.h>
#include "../base_leecode.h"
using namespace std;

//binary search 二分查找

class binary_search{
public:
    //返回下标值
    int search(vector<int>& nums, int target){
       int left = 0;
       int right = nums.size()-1;
        while (left <= right){
            int mid = (right+left)/2;

            if (nums[mid] < target){
                left = mid+1;
            } else if (nums[mid] > target){
                right = mid-1;
            } else{
                return mid;
            }
        }
        return -1;
    }
    //力扣  两数相加
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        ListNode *res =new  ListNode(-1);
        ListNode *cons = res;
        ListNode *ul = l1;
        ListNode *ur = l2;
        int cadr = 0;
        while(ul!= NULL|| ur != NULL || cadr != 0 )
        {
            int lval = ul != NULL ? ul->val : 0;
            int rval = ur != NULL ? ur->val : 0;
            int smf = lval + rval +cadr;
            cadr = smf /10;
            ListNode *node = new ListNode(smf%10);
            cons->next = node;
            cons = node;

            if(ul != NULL) ul = ul->next;
            if(ur != NULL) ur = ur->next;
        }
        return res->next;
    }




};














#endif //LEECODE_SERCH_H
