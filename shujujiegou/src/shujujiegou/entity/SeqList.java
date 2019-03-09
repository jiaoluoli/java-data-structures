package shujujiegou.entity;

public class SeqList{
	int lenList = 10;
	int a[] = new int[10];
	//要求一：依次插入13579
	public void initial() {
		int x = 1;
		for(int i = 0; i<=4 ;i++) {
			this.a[i] = x;
			x+=2;
		}
	}
	//查找适合位置插入新元素x
	public void add(int x){
		for(int i = 0;i<this.lenList;i++ )
		if(x<=this.a[i]){
			for(int j = this.lenList-2;j>=i;j--){
				this.a[j+1] = this.a[j];
			}
			this.a[i]=x;
			break;
		}else if(i>0) {
			if(a[i-1]!=0 & a[i]==0 & x>a[i-1]) {
				this.a[i]=x;
				break;
			}
			
		}
		
	}
	//依次显示顺序表
	public void printList(){
		int i;
		if(this.lenList==0){
			System.out.print("ERROR:Empty");
		}
		for(i=0;i<this.lenList;i++){
			System.out.print(this.a[i]+" ");
		}
	}
	//删除第n位数
	public void delList(int n){
		int i;
		if(n<1||n>this.lenList){
			System.out.println("Delete error!");
		}else{
			for(i=n;i<this.lenList;i++){
				this.a[i-1]=this.a[i];
			}
			this.a[9] = 0;
		}
	}
	//删除冗余数据
	public void reduncant()
	{
		int i,j;
		if(this.lenList==0){
			System.out.print("ERROR:Empty");
		}
		for(i=0;i<this.lenList;i++){
			if(a[0]==0&a[1]==0) {
				delList(2);
			}
			for(j=i+1;j<this.lenList;j++){
				if(a[i]!=0) {
					if(this.a[j]==this.a[i]){
						delList(j+1);
						--j;
					}
				}
				
			}
		}
	}
	//删除第n位数据后展示数据
	public void delAndPrint(int n){
		delList(n);
		printList();
	}
	//删除冗余数据展示数据
	public void redAndPrint() {
		reduncant();
		printList();
	}
	//插入新元素x后展示数据
	public void addAndPrint(int x) {
		add(x);
		printList();
	}
}
	



