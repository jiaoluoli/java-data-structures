package shujujiegou.entity;

import java.util.ArrayList;
import java.util.List;

public class HuffmanTree extends Tree{
	
	//本HuffmanTree的Elem.data 中data[0]为数据,data[1]为code
	public Elem setHuffmanTree(List<Integer> dataList) {
		List<Elem> elemList = new ArrayList<>();
		dataList.forEach(data->{
			int[] data1 = new int[2];
			data1[0] = data;
			Elem elem = new Elem(data1);
			elemList.add(elem);
		});
		setLastElem(elemList);
		while(elemList.size() > 1) {
			Elem aElem = minData(elemList, 0);
			elemList.remove(aElem);
			Elem bElem = minData(elemList, 0);
			elemList.remove(bElem);
			int[] c = new int[2];
			c[0] = aElem.data[0] + bElem.data[0];
			Elem cElem = new Elem(c);
			cElem.childern.add(aElem);
			cElem.childern.add(bElem);
			elemList.add(cElem);			
		}
		setHead(elemList.get(0));
		return elemList.get(0);
	}

	
	//给叶子节点编码，data[1]是code
	public void setmap(Elem head, String code) {
		if(!head.childern.isEmpty()) {
			code += "0";
			setmap(head.childern.get(0), code);
			code = code.substring(0, code.length()-1);
			code += "1";
			setmap(head.childern.get(1), code);
			code = code.substring(0, code.length()-1);
		}
		head.data[1] = Integer.parseInt(code);
	}
	
	

}
