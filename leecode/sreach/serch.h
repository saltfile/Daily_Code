//
// Created by saltfish on 23-6-27.
//

#ifndef LEECODE_SERCH_H
#define LEECODE_SERCH_H

#include <iostream>
#include <stdio.h>
#include <vector>
#include <string.h>

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




};














#endif //LEECODE_SERCH_H
