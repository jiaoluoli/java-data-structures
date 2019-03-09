package shujujiegou.assignment;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 2019.01.01
 * @author 李俊杰 2016202307
 *
 */
public class Assignment7 {
	 public static void main(String[] args) {
		 int[] rawnum = {27, 38, 1, 10, 27, 38, 3, 6, 9, 11, 18, 27, 9, 5, 100000, 1000001, 0, 100022, 20011, 11, 56, 79, 78, 5, 12, 8, 13, 9, 12, 10, 12
};
		 System.out.println("请选择排序算法，插入排序：1；希尔排序：2；冒泡排序：3；快速排序：4；选择排序：5；堆排序：6；二路归并排序：7");	
		 transferStation(rawnum);
	 }
    public static void output(int[] rawnum,int[] numbers, long longTime) {		
		for(int i = 0; i<numbers.length; i++) {
			System.out.print(","+numbers[i]);
		}
		
		
		//程序用时
		System.out.println("程序运行时间： "+(longTime)+"纳秒");
		System.out.println("=================================");
	    System.out.print("是否测试另一种算法，插入排序：1；希尔排序：2；冒泡排序：3；快速排序：4；选择排序：5；堆排序：6；二路归并排序：7；退出输入其他任意值: ");
        transferStation(rawnum);
    }
    
    public static void transferStation(int[] rawnum){
    	int[] numbers = Arrays.copyOf(rawnum, rawnum.length);
    	Scanner scan = new Scanner(System.in);
    	boolean stop = false;
    	//开始执行时间
    	long startTime = 0;
    	//结束执行时间
        long endTime = 0;
        switch (scan.nextInt()) {
 		case 1:
 			startTime = System.nanoTime();
 			insertSort(numbers);
 			endTime = System.nanoTime();
 			break;
 		case 2:
 			startTime = System.nanoTime();
 			shellSort(numbers);
 			endTime = System.nanoTime();
 			break;
 		case 3:
 			startTime = System.nanoTime();
 			bubbleSort(numbers);
 			endTime = System.nanoTime();
 			break;
 		case 4:
 			startTime = System.nanoTime();
 			quickSort(numbers,0,numbers.length-1);
 			endTime = System.nanoTime();
 			break;
 		case 5:
 			startTime = System.nanoTime();
 			selectSort(numbers);
 			endTime = System.nanoTime();
 			break;
 		case 6:
 			startTime = System.nanoTime();
 			heapSort(numbers);
 			endTime = System.nanoTime();
 			break;
 		case 7:
 			startTime = System.nanoTime();
 			guibing(numbers);
 			endTime = System.nanoTime();
 			break; 			
 		default:
 			stop = true;
 			break;
 		}
        if(stop == false)output(rawnum,numbers,endTime-startTime);//输出原始数组、排序后数组及运行时间
    }	 
	 
     /**  
     * 插入排序
     * 
     * 从第一个元素开始，该元素可以认为已经被排序
     * 取出下一个元素，在已经排序的元素序列中从后向前扫描 
     * 如果该元素（已排序）大于新元素，将该元素移到下一位置  
     * 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置  
     * 将新元素插入到该位置中  
     * 重复步骤2  
     * @param numbers  待排序数组
     */  
    public static int[] insertSort(int[] numbers)
    {
    int size = numbers.length;
    int temp = 0 ;
    int j =  0;
    
    for(int i = 0 ; i < size ; i++)
    {
        temp = numbers[i];
        //假如temp比前面的值小，则将前面的值后移
        for(j = i ; j > 0 && temp < numbers[j-1] ; j --)
        {
        numbers[j] = numbers[j-1];
        }
        numbers[j] = temp;
    }
    return numbers;
    }
    
	    /**希尔排序的原理:根据需求，如果你想要结果从大到小排列，它会首先将数组进行分组，然后将较大值移到前面，较小值
	     * 移到后面，最后将整个数组进行插入排序，这样比起一开始就用插入排序减少了数据交换和移动的次数，可以说希尔排序是加强
	     * 版的插入排序
	     * 拿数组5, 2, 8, 9, 1, 3，4来说，数组长度为7，当increment为3时，数组分为两个序列
	     * 5，2，8和9，1，3，4，第一次排序，9和5比较，1和2比较，3和8比较，4和比其下标值小increment的数组值相比较
	     * 此例子是按照从大到小排列，所以大的会排在前面，第一次排序后数组为9, 2, 8, 5, 1, 3，4
	     * 第一次后increment的值变为3/2=1,此时对数组进行插入排序，
	     *实现数组从大到小排
	     */
        public static int[] shellSort(int[] numbers) 
        {
            int j = 0;
            int temp = 0;
            //每次将步长缩短为原来的一半
            int lengthn = numbers.length;
            for (int increment = lengthn / 2; increment > 0; increment /= 2)
            {
            for (int i = increment; i < lengthn; i++) 
            {
                temp = numbers[i];
                for (j = i; j >= increment; j -= increment) 
                {
                if(temp < numbers[j - increment])
                {   
                	numbers[j] = numbers[j - increment];
                }
                else
                {
                    break;
                }
                
                } 
                numbers[j] = temp;
            }
        
            }
            return numbers;
     }	
        
    /**
     * 冒泡排序
     * 比较相邻的元素。如果第一个比第二个大，就交换他们两个。  
     * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。  
     * 针对所有的元素重复以上的步骤，除了最后一个。
     * 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。 
     * @param numbers 需要排序的整型数组
     */
    public static int[] bubbleSort(int[] numbers)
    {
        int temp = 0;
        int size = numbers.length;
        for(int i = 0 ; i < size-1; i ++)
        {
        for(int j = 0 ;j < size-1-i ; j++)
        {
            if(numbers[j] > numbers[j+1])  //交换两数位置
            {
            temp = numbers[j];
            numbers[j] = numbers[j+1];
            numbers[j+1] = temp;
            }
        }
        }
        return numbers;
    }
    
    /**
     * 查找出中轴（默认是最低位low）的在numbers数组排序后所在位置
     * 
     * @param numbers 带查找数组
     * @param low   开始位置
     * @param high  结束位置
     * @return  中轴所在位置
     */
    public static int[] quickSort(int[] numbers,int start,int end)
    {
        if(start<end)
        {
            int key=numbers[start];//初始化保存基元
            int i=start,j;//初始化i,j
            for(j=start+1;j<=end;j++)
            {
                if(numbers[j]<key)//如果此处元素小于基元，则把此元素和i+1处元素交换，并将i加1，如大于或等于基元则继续循环
                {
                    int temp=numbers[j];
                    numbers[j]=numbers[i+1];
                    numbers[i+1]=temp;
                    i++;
                }
                
            }
            numbers[start]=numbers[i];//交换i处元素和基元
            numbers[i]=key;
            quickSort(numbers, start, i-1);//递归调用
            quickSort(numbers, i+1, end);
            
        }
        return numbers;
        
    } 
	 /**
     * 选择排序算法
     * 在未排序序列中找到最小元素，存放到排序序列的起始位置  
     * 再从剩余未排序元素中继续寻找最小元素，然后放到排序序列末尾。 
     * 以此类推，直到所有元素均排序完毕。 
     * @param numbers
     */
	 public static int[] selectSort(int[] numbers)
	    {
	    int size = numbers.length; //数组长度
	    int temp = 0 ; //中间变量
	    
	    for(int i = 0 ; i < size ; i++)
	    {
	        int k = i;   //待确定的位置
	        //选择出应该在第i个位置的数
	        for(int j = size -1 ; j > i ; j--)
	        {
	        if(numbers[j] < numbers[k])
	        {
	            k = j;
	        }
	        }
	        //交换两个数
	        temp = numbers[i];
	        numbers[i] = numbers[k];
	        numbers[k] = temp;
	    }
	    return numbers;
	    }
	 /**
     * 堆排序算法
     * 包括buildMaxHeap swap两个子方法
     * @param numbers
     */	 
	 public static int[] heapSort(int[] numbers)
	    {
		 	int arrayLength = numbers.length;
	        //循环建堆  
	        for(int i=0;i<arrayLength-1;i++){  
	            //建堆  
	            buildMaxHeap(numbers,arrayLength-1-i);  
	            //交换堆顶和最后一个元素  
	            swap(numbers,0,arrayLength-1-i);  
	        }
	        return numbers;
	    }
   //对data数组从0到lastIndex建大顶堆
    private static void buildMaxHeap(int[] data, int lastIndex){
         //从lastIndex处节点（最后一个节点）的父节点开始 
        for(int i=(lastIndex-1)/2;i>=0;i--){
            //k保存正在判断的节点 
            int k=i;
            //如果当前k节点的子节点存在  
            while(k*2+1<=lastIndex){
                //k节点的左子节点的索引 
                int biggerIndex=2*k+1;
                //如果biggerIndex小于lastIndex，即biggerIndex+1代表的k节点的右子节点存在
                if(biggerIndex<lastIndex){  
                    //若果右子节点的值较大  
                    if(data[biggerIndex]<data[biggerIndex+1]){  
                        //biggerIndex总是记录较大子节点的索引  
                        biggerIndex++;  
                    }  
                }  
                //如果k节点的值小于其较大的子节点的值  
                if(data[k]<data[biggerIndex]){  
                    //交换他们  
                    swap(data,k,biggerIndex);  
                    //将biggerIndex赋予k，开始while循环的下一次循环，重新保证k节点的值大于其左右子节点的值  
                    k=biggerIndex;  
                }else{  
                    break;  
                }  
            }
        }
    }
    //交换
    private static void swap(int[] data, int i, int j) {  
        int tmp=data[i];  
        data[i]=data[j];  
        data[j]=tmp;  
    } 
    
    
    /**
     * 归并排序
     * 简介:将两个（或两个以上）有序表合并成一个新的有序表 即把待排序序列分为若干个子序列，每个子序列是有序的。然后再把有序子序列合并为整体有序序列
     * 时间复杂度为O(nlogn)
     * 稳定排序方式
     * @param nums 待排序数组
     * @return 输出有序数组
     */
    public static int[] guibing(int[] numbers) {
    	return sortgui(numbers, 0, numbers.length-1);
    }
    private static int[] sortgui(int[] nums, int low, int high) {
        int mid = (low + high) / 2;
        if (low < high) {
            // 左边
            sortgui(nums, low, mid);
            // 右边
            sortgui(nums, mid + 1, high);
            // 左右归并
            merge(nums, low, mid, high);
        }
        return nums;
    }

    /**
     * 将数组中low到high位置的数进行排序
     * @param nums 待排序数组
     * @param low 待排的开始位置
     * @param mid 待排中间位置
     * @param high 待排结束位置
     */
    private static void merge(int[] nums, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int i = low;// 左指针
        int j = mid + 1;// 右指针
        int k = 0;

        // 把较小的数先移到新数组中
        while (i <= mid && j <= high) {
            if (nums[i] < nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }

        // 把左边剩余的数移入数组
        while (i <= mid) {
            temp[k++] = nums[i++];
        }

        // 把右边边剩余的数移入数组
        while (j <= high) {
            temp[k++] = nums[j++];
        }

        // 把新数组中的数覆盖nums数组
        for (int k2 = 0; k2 < temp.length; k2++) {
            nums[k2 + low] = temp[k2];
        }
    }
}
