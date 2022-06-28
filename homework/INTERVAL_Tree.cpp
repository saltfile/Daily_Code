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
    int data;
    int cover;
    int   left,right;  //区间左右值
    seg_node *leftchild;
    seg_node   *rightchild;
};


seg_node *build(int arr[],int   l ,  int r ) //建立二叉树
{
    cout <<l<<"     "<<r<<endl;
    seg_node  *root = (seg_node *)malloc(sizeof(seg_node));
    memset(root,0,sizeof(root));
    root->cover = 0;
    root->left = l;
    root->right = r;     //设置结点区间
    root->leftchild = NULL;
    root->rightchild = NULL;

    if ( l < r )
    {
        int  mid = (r+l) >> 1;
        root->leftchild = build ( arr,l , mid ) ;
        root->rightchild = build ( arr,mid +1 , r) ;
    }
    if(l == r){
        root->data = arr[l];
    } else{
        root->data = root->leftchild->data+root->rightchild->data;
    }
    return    root;
}

//
//void  Delete (int c , int  d , seg_node  *root )
//{
//    if(c<= root->left&&d>= root->right)
//        root-> cover= root-> cover-1;
//    else
//    {
//        if(c < (root->left+ root->right)/2 ){
//            Delete ( c,d, root->leftchild  );
//            root->data = root->leftchild->data+root->rightchild->data;
//        }
//}

void Insert(int  c, int d ,int num, seg_node  *root )
{
    if(c<= root->left&&d>= root->right){
        root-> cover++;
        root->data = num;
    }else
    {
        if(c < (root->left+ root->right)/2 ) Insert (c,d,num, root->leftchild  );
        if(d > (root->left+ root->right)/2 ) Insert (c,d,num, root->rightchild  );
    }
}


void   Insert(seg_node  *root , int  a , int  b)
{


}


seg_node *update(seg_node *root,int start,int end,int idx,int val){
    if (root == NULL){
        return NULL;
    } else{
        cout<<start<<"      "<<end<<endl;
    }
    if(start == idx&&end == idx){
        root->data = val;
        return root;
    }

    if ( start < end )
    {
        int  mid = (start+end)/2;
        if ( idx >= start && idx <= mid) {
            root->leftchild = update( root->leftchild,start , mid,idx,val ) ;
        } else {
            root->rightchild = update( root->rightchild,mid +1 , end,idx,val) ;
        }
    }
    root->data = 0;
    if(root->leftchild != NULL)root->data += root->leftchild->data;
    if(root->rightchild != NULL)root->data += root->rightchild->data;
    return root;
}

/**
 *
 * @param root
 * @param start 开始范围
 * @param end 结束范围
 * @param L 查找开始范围
 * @param R 查找结束范围
 * @return
 */
int qureynode(seg_node *root,int start,int end,int L,int R){
    if(R < start || L > end){
        return 0;
    }
    if (root == NULL){
        return 0;
    } else{
        cout<<start<<"      "<<end<<endl;
    }

    if(start == end){
        return root->data;
    }
    if(L <= start && end <= R){
        //当在这个范围中时可以返回一个那个区间的数;
        return root->data;
    }
    int left = 0;
    int right = 0;
    if ( start <= end )
    {
        int  mid = (start+end)/2;
            left = qureynode( root->leftchild,start , mid,L,R) ;
            right = qureynode( root->rightchild,mid +1 , end,L,R) ;
    }
    return left+right;

}





























//主函数
int seg_treedemo(){
    /**
     * 第一种线段树
     */
    int arr[] = {1,3,5,7,9,11,25,31,55,8,9,7,5,2,5};
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
    seg_node *p = build(arr,0,10);

//    Delete(1,1,p);

//    Insert(p,1,2);
cout<<endl<<"===================================================="<<endl;
    update(p,0,10,4,25);

    cout<<endl<<"===================================================="<<endl;
    cout<<"qurey:"<<qureynode(p,0,10,2,5);

    return 0;
}





