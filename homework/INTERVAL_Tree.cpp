//
// Created by saltfish on 2022/6/25.
//
/**
 * 线段树
 */

#include "my_home.h"

#define Max_LEN 1000
/**
 * 第一种线段树：堆存储方式
 */
/**
 *
 * @param arr 数组
 * @param tree 装树的数组
 * @param node 从哪里出发
 * @param start 从开始
 * @param end 到结束
 */
void build_tree(int arr[],int tree[],int node,int start,int end){
//    cout<<node<<"    "<<start<<"    "<< end<<endl;
    if(start == end){
        tree[node] = arr[start];
    } else {
        //中间值
        int mid = (start + end) / 2;
        //首先算出左节点和右边节点的下标
        int left_node = 2 * node + 1;
        int right_node = 2 * node + 2;
        //利用递归算出左边和右边数组的下标
        build_tree(arr, tree, left_node, start, mid);
        build_tree(arr, tree, right_node, mid + 1, end);
        tree[node] = tree[left_node] + tree[right_node];
    }
}
//其中一个主要功能更新
//从根往下搜索并进行数组数据更新
/**
 *
 * @param arr 数组
 * @param tree 装树的数组
 * @param node 从哪里出发
 * @param start 从开始
 * @param end 到结束
 * @param idx 需要更改的数组下标
 * @param val 更改后的值
 */
void update(int arr[],int tree[],int node,int start,int end,int idx,int val){
//如果找到该节点就修改对应节点的值
    if(start == end){
        arr[idx] = val;
        tree[node] = val;
        return;
    }

        //老样子确定左右节点长度
        int mid = (start + end) / 2;
        int left_node = 2 * node + 1;
        int right_node = 2 * node + 2;
        //确定分支
        if ( idx >= start && idx <= mid) {
            update(arr, tree, left_node, start, mid, idx, val);
        } else {
            update(arr, tree, right_node, mid + 1, end, idx, val);
        }
        tree[node] = tree[left_node] + tree[right_node];

}





//qurey计算线段长(未优化)
int qurey(int arr[],int tree[],int node,int start,int end,int L,int R){
    cout<<node<<"    "<<start<<"    "<< end<<endl<<endl;
    if(R < start || L > end){
        return 0;
    } else if(start == end){
        return tree[node];
    } else{
    int mid = (start + end) / 2;
    int left_node = 2 * node + 1;
    int right_node = 2 * node + 2;
    int sun_left = qurey(arr,tree,left_node,start,mid,L,R);
    int sun_right = qurey(arr,tree,right_node,mid+1,end,L,R);
    return sun_left+sun_right;}
}
//qurey计算线段长(优化版)
int qurey_(int arr[],int tree[],int node,int start,int end,int L,int R){
    cout<<node<<"    "<<start<<"    "<< end<<endl<<endl;
    if(R < start || L > end){
        return 0;
    }
    if(start == end){
        return tree[node];
    }
    if(L <= start && end <= R){
        //当在这个范围中时可以返回一个那个区间的数;
        cout<<"走到这里";
        return tree[node];
    }
        int mid = (start + end) / 2;
        int left_node = 2 * node + 1;
        int right_node = 2 * node + 2;
        int sun_left = qurey_(arr,tree,left_node,start,mid,L,R);
        int sun_right = qurey_(arr,tree,right_node,mid+1,end,L,R);
        return sun_left+sun_right;
}




/**
 * 线段树：节点存储
 *
 */


typedef struct seg_node
{
    int   left,right;  //区间左右值
    seg_node *leftchild;
    seg_node   *rightchild;
};


seg_node *build(int   l ,  int r ) //建立二叉树
{
    seg_node  *root = (seg_node *)malloc(sizeof(seg_node));
    memset(root,0,sizeof(root));
    root->left = l;
    root->right = r;     //设置结点区间
    root->leftchild = NULL;
    root->rightchild = NULL;

    if ( l +1< r )
    {
        int  mid = (r+l) >>1;
        root->leftchild = build ( l , mid ) ;
        root->rightchild = build ( mid +1 , r) ;
    }

    return    root;
}

































//主函数
int seg_treedemo(){
    /**
     * 第一种线段树
     */
//    int arr[] = {1,3,5,7,9,11,25,31,55,8,9,7,5};
//    int size = 6;
//    int tree[Max_LEN] = {0};
//
//    build_tree(arr,tree,0,0,size-1);
//
//    for(int i = 0;i < 15;i++){
//        cout<<tree[i]<<"     ";
//    }
//    cout<<endl<<"=========================================="<<endl;

//
//    update(arr,tree,0,0,size-1,4,1);
//
//    for(int i = 0;i < 15;i++){
//        cout<<tree[i]<<"     ";
//    }
//    cout<<endl<<"=========================================="<<endl;
//    int s = qurey_(arr,tree,0,0,size-1,2,5);
//    cout<<"query:"<<s;
    /**
     * 第二种线段树
     */
    seg_node *p = build(1,10);





    return 0;
}





