package shujujiegou.entity;

import java.util.ArrayList;
import java.util.List;

public class LinkedList {

    public Elem head;
    
    //有头结点
    public LinkedList(){
        Elem Elem = new Elem();
        Elem.parent = null;
        head = Elem;
    }
    
    //无头结点
    public LinkedList(Elem head) {
		this.head = head;
	}
	
	//进栈，头部插入
	public void put(Elem elem) {
		elem.parent = head;
		head = elem;
	}
	
    //进栈，尾部插入
    public void append(int x){
        //设立尾指针一直指向表尾节点
        Elem tail = findByIndex(getLen());
        Elem Elem = new Elem(x);
        tail.parent = Elem;
        tail = Elem;
    }
    
    //出栈，返回出栈的Elem
  	public Elem push() {
  		Elem pushElem = head;
  		if(head.parent != null) {
  			head = head.parent;
  			pushElem.parent = null;
  		}else {
  			throw new ArrayIndexOutOfBoundsException("There is no Elem left!");
  		}
  		return pushElem;
  	}
  	
  	//将栈中元素从栈头到栈尾放入list中
  	public List<Elem> displayElem() {
  		List<Elem> listElem = new ArrayList<>();
  		Elem head = this.head;
  		listElem.add(head);
  		while(head.parent != null) {
  			head = head.parent;
  			listElem.add(head);
  		}
  		return listElem;
  	}

  //返回该链表长度
    public int getLen(){
        Elem p = head.parent;
        int i = 0;
        while(p != null){
            i++;
            p = p.parent;
        }
        return i;
    }
    
  //查找第i个节点值,并返回指向该节点的指针
    public Elem findByIndex(int i){
        if(i == 0){
            return head;
        }
        if(i < 0){
            return null;
        }
        int j = 1;
        Elem p = head.parent;
        while(p != null && j < i){
            p = p.parent;
            j++;
        }
        return p;
    }
    
    //根据Elem.data[0]在链表中查找指定的值，如果存在则返回其序号，否则返回-1
    public int findByVal0(int x){
        Elem p = head.parent;
        int n = 1;
        while(p != null && p.data[0] != x){
            p = p.parent;
            n++;
        }
        if(p==null) {
        	return -1;
        }else {
        	 return n;
        }
       
    }
    
    //删除指定位置的结点
    public boolean del(int i){
        if(i < 1 || i > getLen()){
            System.out.println("Delete index illegal");
            return false;
        }
        Elem p = head.parent;
        Elem q = head;
        int j = 1;
        while(p != null && j < i){
            q = p;
            p = p.parent;
            j++;
        }
        q.parent = p.parent;
        p.parent = null;
        return true;
    }
    
  //根据Elem.data[0]删除此节点后面的第i个节点
    public int deli0(LinkedList linkedList, Elem Elem, int i) {
    	
    	for(int j = 1;j<i;j++) {
    		Elem = Elem.parent;
    	}
    	Elem p = Elem.parent;
    	Elem.parent = p.parent; 
    	p.parent = null;
    	return p.data[0];
    }
    

}