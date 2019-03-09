package shujujiegou.entity;

import java.util.ArrayList;
import java.util.List;

public class Elem {

	public int[] data;
	public Elem parent;
	public List<Elem> childern; 
	
	public Elem(int[] data) {
		this.data = data;
		this.childern = new ArrayList<>();
	}
    public Elem(int[] data, Elem parent){
        this.data = data;
        this.parent = parent;
        this.childern = new ArrayList<>();
    }
    
	public Elem(){
		this.childern = new ArrayList<>();
	};
    
    public Elem(Elem elem) {
    	this.data = elem.data;
    	this.parent = elem.parent;
    	this.childern = elem.childern;
    }
    

    //data只有一个值
	public Elem(int data) {
		int[] datal = {data};
		this.data = datal;
		this.childern = new ArrayList<>();
	}
	
    public Elem(int data, Elem parent){
        this.data[0] = data;
        this.parent = parent;
        this.childern = new ArrayList<>();
    } 
    
}
