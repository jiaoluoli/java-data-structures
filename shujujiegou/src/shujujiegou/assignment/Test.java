package shujujiegou.assignment;

import java.util.ArrayList;
import java.util.List;

import shujujiegou.entity.Elem;
import shujujiegou.entity.HuffmanTree;
import shujujiegou.entity.Tree;

public class Test {
	public static void main(String[] args) {
		
/*		//测试Tree类
		List<Elem> rootElem = new ArrayList<>();
		int i = 0;
		Tree tree = new Tree(new Elem(0));
		while(i<1) {
			rootElem.add(new Elem(i+1));
			List<Elem> subElem = new ArrayList<>();
			rootElem.forEach(elem->{
				subElem.add(new Elem(elem));
			});
			tree.putSubElem(rootElem.get(i), subElem);
			i++;
		}
		tree.getLastElem().forEach(elem->{
			tree.displayElem(elem).forEach(e->{
				System.out.print(e.data[0]);
			});
			System.out.println();
		});*/
		//测试int数组是否会在函数中变化
		/*int[] a = new int[1];
		a[0] = 0;
		Test1 test1 = new Test1();
		test1.abc(a);
		System.out.println(a[0]);*/
		HuffmanTree huffmanTree = new HuffmanTree();
		List<Integer> dataList = new ArrayList<>();
		dataList.add(1);
		dataList.add(2);
		dataList.add(5);
		dataList.add(8);
		dataList.add(4);
		dataList.add(3);
		dataList.add(9);
		
		Elem head = huffmanTree.setHuffmanTree(dataList);
/*		huffmanTree.setmap(head, "1");
		huffmanTree.getLastElem().forEach(elem->{
			String code = elem.data[1]+"";
			code = code.substring(1);
			System.out.println(elem.data[0] + ":" + code);
		});*/
		huffmanTree.preTraversal(head);
		
		
	}

}
//测试int数组是否会在函数中变化
/*class Test1{
	public Test1() {};
	public void abc(int[] a) {
		 a[0] = 1;
	}
	
}*/