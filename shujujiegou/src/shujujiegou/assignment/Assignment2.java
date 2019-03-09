package shujujiegou.assignment;

import shujujiegou.entity.LinkedList;

/*
 * 2016202307李俊杰 2018/10/10
 */
public class Assignment2 {
	public static void main(String[] args) {
		LinkedList linkedlist = new LinkedList();
		//尾部插入新结点,如此时在尾部插入data=5,4,3,2的节点
		linkedlist.append(5);
		linkedlist.append(4);
		linkedlist.append(3);
		linkedlist.append(2);
		//删除指定位置的结点,如此时删除第一个节点
		linkedlist.del(1);
		//依次显示各个结点的值
		linkedlist.displayElem().forEach(elem->{
			if(elem.data != null)System.out.println(elem.data[0]);
		});;
		//返回该链表长度
		int lenth = linkedlist.getLen();
		System.out.println(lenth);
		//在链表中查找指定的值，如果存在则返回其序号，否则返回-1,如此时寻找链表中值为2的节点并返回其序号
		int x = linkedlist.findByVal0(2);
		System.out.println(x);
	}
}

