package shujujiegou.assignment;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shujujiegou.entity.Elem;
import shujujiegou.entity.HuffmanTree;

public class Assignment5 {
	public static void main(String[] args) {
		String[] character = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		Integer[] frequency = {817,149,278,425,1270,223,202,609,697,15,77,403,241,675,751,193,10,599,633,906,276,98,236,15,197,7};
		List<Integer> freList = Arrays.asList(frequency);
		HuffmanTree huffmanTree = new HuffmanTree();
		Elem head = huffmanTree.setHuffmanTree(freList);
		huffmanTree.setmap(head, "1");
		List<Elem> lastElems = huffmanTree.getLastElem();
		Map<String, String> charCode = new HashMap<>();
		for(int i = 0; i < character.length; i++) {
			String code = lastElems.get(i).data[1] + "";
			code = code.substring(1);
			charCode.put(character[i], code);
		}
		for(String key : charCode.keySet()) {
			System.out.println(key + ":" + charCode.get(key));
		}
	}
}
