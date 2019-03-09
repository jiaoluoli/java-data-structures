package shujujiegou.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Maze{
	
	//按最优方向directi寻找下一个Elem
	public Elem parentElem(LinkedList LinkedList, int[][] maze, Elem end) {
		Elem now = LinkedList.head;
		//初始化 directi返回该点前进方向最佳组合
		int[] directi = direct(now, end); 
		int i = 0;
		int direct = directi[i];
		int x = now.data[0];
		int y = now.data[1];
		//如果是出栈则now带有direct属性，此时让direct从now的direct值开始
		if(direct != now.data[2]) {
			i++;
			direct = directi[i];
		}
		//data返回下一Elem的x,y以及maze在该坐标下是否有障碍,有返回1
		int[] parentElemData = data(direct, x, y, maze);
		//循环到下一坐标无障碍，若全有障碍则direct=4
		while(parentElemData[2] == 1 & direct < 4) {
			i++;
			if(i < 4) {
				direct = directi[i];
				parentElemData = data(direct, x, y, maze);
			}else {
				direct = 4;
			}
		}
		Elem parentElem = new Elem();
		//如果四个方向都有障碍，则出栈，返回前一个值
		if(direct == 4) {
			if(now.parent != null) {
				parentElem = now.parent;
				LinkedList.push();
			}
		//进栈，并设定如果出栈到该Elem下次从那个方向开始搜索
		}else {
			now.data[2] = direct+1;
			int[] dataco = new int[3];
			dataco[0] = parentElemData[0];
			dataco[1] = parentElemData[1];
			parentElem.data = dataco;
			LinkedList.put(parentElem);
		}
		/*System.out.println(y+""+x);*/
		//将maze在上一个Elem的坐标设为有障碍，防止下次搜索路径使陷入循环
		maze[y][x] = 1;
		return parentElem;
	}
	
	//走到指定位置的LinkedList
	public LinkedList goThrough(Elem start, Elem end, int[][] maze) {
		LinkedList goThrough = new LinkedList(start);
		Elem parentElem = start;
		int xs = start.data[0];
		int ys = start.data[1];
		int xe = end.data[0];
		int ye = end.data[1];
		//如果还没有到终点，则持续往下走
		while(xs != xe || ys != ye) {
			parentElem = parentElem(goThrough, maze, end);
			//如果出栈的下一Elem为空则返回null
			if(parentElem.data != null) {
				xs = parentElem.data[0];
				ys = parentElem.data[1];
			}else return null;
		}
		return goThrough;
	}
	
	//direct[i] = 0,1,2,3以此为向东南西北方向
	public int[] direct(Elem now, Elem end) {
		int[] direct = new int[4];
		//如果终点在该点东边则先往东再往西
		if(now.data[0] < end.data[0]) {
			direct[0] = 0;
			direct[2] = 3;
		}else {
			direct[0] = 3;
			direct[2] = 0;
		}
		//如果终点在该点南边则先往南再往北
		if(now.data[1] < end.data[1]) {
			direct[1] = 1;
			direct[3] = 2;
		}else {
			direct[1] = 2;
			direct[3] = 1;
		}
		return direct;
	}
	
	//输出data的数值依次为x坐标，y坐标，maze在该坐标下是否有障碍，有为1，没有为0
	public int[] data(int direct, int x, int y, int[][] maze) {
		int[] data = new int[3];
		switch(direct) {
		case 0:
			data[0] = x+1;
			data[1] = y;
			data[2] = maze[data[1]][data[0]];break;	
		case 1:
			data[0] = x;
			data[1] = y+1;
			data[2] = maze[data[1]][data[0]];break;
		case 2:
			data[0] = x-1;
			data[1] = y;
			data[2] = maze[data[1]][data[0]];break;
		case 3:
			data[0] = x;
			data[1] = y-1;
			data[2] = maze[data[1]][data[0]];break;
		default://go back
			System.err.println("该方向无效");;break;
		}
		return data;
	}
	
	
	
	//更新tree的lastElem,当未到达终点前，该函数始终返回空值,到达终点是返回终点值
	public Elem lastElem(Tree tree, int[][] maze, Elem end, int[] toEnd){
		List<Elem> lastElems = new ArrayList<>();
		lastElems.addAll(tree.getLastElem());
		Elem lastElem = new Elem();
		for(int i=0;i<lastElems.size();i++) {
			Elem now = lastElems.get(i);
			List<Elem> parentElems = parentElems(now, maze, end, toEnd);
			if(parentElems.isEmpty()) {
				tree.remove(now);
			}else {
				tree.putSubElem(now, parentElems);
				/*System.out.println(now.data[0]+" "+now.data[1]);*/
				if(toEnd[0] == 0) {
					lastElem = parentElems.get(0);
					break;
				}
			}
		}		
		return lastElem;
	}
	
	//寻找该位置所有下一个可走位置
	public List<Elem> parentElems(Elem now, int[][] maze, Elem end, int[] toEnd){
		List<Elem> parentElems = new ArrayList<>();
		int x = now.data[0];
		int y = now.data[1];
		int direct = 0;
		int[] endData = end.data;
		int[] parentElemTest = new int[3];
		while(direct < 4) {
			parentElemTest = data(direct, x, y, maze);
			if(parentElemTest[2] == 0) {
				int x1 = parentElemTest[0];
				int y1 = parentElemTest[1];
				int[] parentElemData = {x1, y1};
				//如果下一个坐标即终点，则终止循环，并将终点的Elem放在parentElems的第一个位置
				if(Arrays.equals(endData, parentElemData)) {
					toEnd[0] = 0;
					parentElems.add(0, new Elem(parentElemData));
					break;
				//如果下个坐标不是终点，则将其放入parentElems后将地图上该点标定为已走
				}else {
					parentElems.add(new Elem(parentElemData));
					maze[y1][x1] = 1;
				}
			}
			direct++;
		}
		
		return parentElems;
	}
	
	//用树结构走通迷宫，如果走通，将lastElem改为终点，并返回可由终点回溯的tree，如果走不通则返回的tree的lastElem为空
	public Tree goThrough1(Elem start, Elem end, int[][] maze) {
		Tree goThrough = new Tree(start);
		int[] toEnd = {1};
		Elem lastElem = new Elem();
		while(toEnd[0] == 1 && !goThrough.getLastElem().isEmpty()) {
			lastElem = lastElem(goThrough, maze, end, toEnd);
		}
		goThrough.setLastElem(lastElem);
		return goThrough;
	}

}

