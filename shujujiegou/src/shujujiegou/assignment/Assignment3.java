package shujujiegou.assignment;

import shujujiegou.entity.Elem;
import shujujiegou.entity.LinkedList;

public class Assignment3 {
	public static void main(String[] args) {
		LinkedList linkedlist = new LinkedList();
		//构造41个人的链表，并将每个链表的数据设为其序号
		for(int i=1;i<=41;i++) {
			linkedlist.append(i);
		}
		//使链表收尾相连
		int lenth = linkedlist.getLen();
		linkedlist.findByIndex(lenth).parent = linkedlist.findByIndex(1);
		Elem stay = new Elem(linkedlist.findByIndex(1));
		//当还有2人以上未出列则循环
		while(lenth>2) {
			//从第一个人开始，每次出列其后面第二个人
			int deldata = linkedlist.deli0(linkedlist,stay,2);
			lenth--;
			stay = stay.parent.parent;
			//输出出列人序号
			System.out.println(deldata);
		}
		//最后两个出列者
		System.out.println(stay.data[0]);
		System.out.println(stay.parent.data[0]);
	}
	
}


