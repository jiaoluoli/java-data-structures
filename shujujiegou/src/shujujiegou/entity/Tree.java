package shujujiegou.entity;

import java.util.ArrayList;
import java.util.List;

//包含头结点和叶子的树
public class Tree {
	private Elem head;
	private List<Elem> lastElem;
	
	public Tree(){};
	
	public Tree(Elem head) {
		this.head = head;
		setLastElem(head);
	}
	
	public Elem getHead() {
		return head;
	}

	public void setHead(Elem head) {
		this.head = head;
	}
	
	public List<Elem> getLastElem() {
		return lastElem;
	}

	public void setLastElem(List<Elem> lastElem) {
		this.lastElem = new ArrayList<Elem>();
		lastElem.forEach(elem->{
			this.lastElem.add(elem);
		});
	}
	
	public void setLastElem(Elem lastElem) {
		this.lastElem = new ArrayList<Elem>();
		this.lastElem.add(lastElem);
	}
	
	public void remove(Elem elem) {
		this.lastElem.remove(elem);
	}
	
	//根据datalist添加lastElem
	public void setLastElem(int[] dataList, int datalength) {
		List<Elem> lastElem = new ArrayList<Elem>();
		for(int i = 0; i < dataList.length; i++) {
			int[] data = new int[datalength];
			data[0] = dataList[i];
			Elem elem = new Elem(data);
			lastElem.add(elem);
		}
		this.lastElem = lastElem;
		
	}

	//向lastElem中 的指定Elem添加subElem
	public void putSubElem(Elem fatherElem, List<Elem> subElem) {
		subElem.forEach(elem->{
			elem.parent = fatherElem;
		});
		fatherElem.childern.addAll(subElem); 
		lastElem.remove(fatherElem);
		lastElem.addAll(subElem);
	}
	
	//返回指定最后一个子节点往回溯的所有节点
	public List<Elem> displayElem(Elem lastElem){
		List<Elem> elemList = new ArrayList<>();
		elemList.add(lastElem);
		while(lastElem != head) {
			lastElem = lastElem.parent;
			elemList.add(lastElem);
		}
		return elemList;
	}
	
    //返回List<Elem>中data[datai]最小的一个Elem
    public Elem minData(List<Elem> elemList, int datai) {
    	Elem min = elemList.get(0);
			for(int i = 1; i < elemList.size(); i++) {
				Elem a = elemList.get(i);
				if(a.data[datai] < min.data[datai]) min = a;
			}
    	return min;
    }
    
    //先序遍历
    public void preTraversal(Elem head){
    	System.out.println(head.data[0]);
    	if(!head.childern.isEmpty()) {
        	preTraversal(head.childern.get(0));
        	preTraversal(head.childern.get(1));
    	}
    }
    
    //中序遍历
    
	

}
