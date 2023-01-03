//
// Created by saltfish on 23-1-3.
//
#include "../my_home.h"
//二分查找
int binary_search1(int* nums, int numsSize, int target){

    int lf = 0;

    int rt = numsSize - 1;

    while (lf <= rt){

        int mid = (lf + rt)/2;

        if (nums[mid] > target)
        {
            rt = mid + 1;
        }
        else if (nums[mid] < target)
        {
            lf = mid + 1;
        }
        else
        {
            return mid;
        }
    }


    return -1;
}

int binary_search2(int* nums, int numsSize, int target){
    int lf = 0;

    int rt = numsSize - 1;

    while (lf <= rt){

        int mid = lf + ((rt - lf) >> 1);;

        if (nums[mid] > target)
        {
            rt = mid;
        }
        else if (nums[mid] < target)
        {
            lf = mid + 1;
        }
        else
        {
            return mid;
        }
    }


    return -1;
}




/**
 * 给你一个数组 nums和一个值 val，你需要 原地 移除所有数值等于val的元素，并返回移除后数组的新长度。

不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。

元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/remove-element
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @return
 */
 //双指针  只得出答案没有挪动相对位置
int removeElement(int* nums, int numsSize, int val){
    int slow = 0;
     for (int fast = 0; fast < numsSize; fast++) {
         if (nums[fast] == val){
             nums[fast] = 0;
         } else{
             nums[slow++] = nums[fast];
         }
     }
     return slow;
}

int removeElement_move(int* nums, int numsSize, int val){
        int leftIndex = 0;
        int rightIndex = numsSize - 1;
        while (leftIndex <= rightIndex) {
            // 找左边等于val的元素
            while (leftIndex <= rightIndex && nums[leftIndex] != val){
                ++leftIndex;
            }
            // 找右边不等于val的元素
            while (leftIndex <= rightIndex && nums[rightIndex] == val) {
                -- rightIndex;
            }
            // 将右边不等于val的元素覆盖左边等于val的元素
            if (leftIndex < rightIndex) {
                nums[leftIndex++] = nums[rightIndex--];
            }
        }
        return leftIndex;   // leftIndex一定指向了最终数组末尾的下一个元素
    }



int removeDuplicates(int* nums, int numsSize){
    int slow = 0;
    for (int fast = 0; fast < numsSize; ++fast) {

        if (nums[fast] != nums[slow]){
            cout<<" 不同 "<<nums[slow]<<"   "<<nums[fast]<<endl;
           nums[++slow] = nums[fast];


        } else{
            cout<<" 相同 "<<nums[slow]<<"   "<<nums[fast]<<endl;
        }
    }
    return ++slow;



}


















int array_part_main(){

//int arr[9] = {1,5,6,8,7,9,15,25,65};
//
//   cout<<"二分搜索1 下标为"<<binary_search1(arr,9,65);

int arr[8] = {2,2,2,3,4,4,5,5};

//cout<<removeElement_move(arr,4,2)<<endl;
//    for (int i = 0; i < 4; ++i) {
//        cout<<arr[i]<<"   ";
//    }


    cout<<removeDuplicates(arr,8);


    return 0;
}


























