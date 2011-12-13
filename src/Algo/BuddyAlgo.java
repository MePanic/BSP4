package Algo;

import java.util.*;

public class BuddyAlgo {

	private int size;
	static ArrayList<ArrayList<Integer>> blocks = null;
	static int[] blockSizePool = null;
	
	public BuddyAlgo(int size){	
		if(!checkForPower(size)) throw new IllegalArgumentException("Size muss eine Zweierpotenz sein");	
		this.size = size;
		blocks = new ArrayList<ArrayList<Integer>>();
		blocks.add(new ArrayList<Integer>());
		blocks.get(0).add(0, 0);
		blocks.get(0).add(1, size-1);
		blocks.get(0).add(2, 0); 
		blockSizePool = calculateBlockPool(size);
	}
	
	public static int allocateBlock(int size){
		int tempBlockSize = size;
		ArrayList<Integer> blockToAllocate = null;
		if(!checkForPower(size)){ tempBlockSize = nextBlockSize(size);}
		blockToAllocate = splitBlocks(tempBlockSize);
		blockToAllocate.set(2, 1);
		
		return blocks.indexOf(blockToAllocate);
	}
	
	public static ArrayList<Integer> splitBlocks(int size){
		ArrayList<Integer> res = null;
		ArrayList<Integer> lowBlock = null;
		for(ArrayList<Integer> x : blocks){
			if(x.get(1)-x.get(0)+1 == size && x.get(2) == 0){
				return x;
			} else if(x.get(1)-x.get(0)+1 > size && x.get(2) == 0 && lowBlock == null) {
				lowBlock = x;			
			} else if(x.get(1)-x.get(0)+1 > size && x.get(2) == 0 && lowBlock.get(1)-lowBlock.get(0)+1 > size){
				lowBlock = x;				
			}
		}
		return splitBlocksHelp(lowBlock, size);
	}
	
	public static ArrayList<Integer> splitBlocksHelp(ArrayList<Integer> toSplit, int size){
		ArrayList<Integer> result = null;
		if(toSplit.get(1)-toSplit.get(0)+1 == size){
			result = toSplit;
		} else {
			ArrayList<Integer> temp1 = new ArrayList<Integer>();
			ArrayList<Integer> temp2 = new ArrayList<Integer>();
			
			temp1.add(0, toSplit.get(0));
			temp1.add(1, ((toSplit.get(1)+1)%2)-1);
			temp1.add(2, 0);
			temp1.add(0, (toSplit.get(1)+1)%2);
			temp1.add(1, toSplit.get(1));
			temp1.add(2, 0);
			
			blocks.remove(toSplit);
			blocks.add(temp1);
			blocks.add(temp2);
			result = splitBlocksHelp(temp2, size);
			
		}
		return result;
	}
	
	public static int nextBlockSize(int oldSize){
		for(int x : blockSizePool){
			if(x > oldSize){ return x;}
		}
		return 0;
	}
	
	public static boolean checkForPower(int x){
		int i = 0;
		while(Math.pow(2, i) <= x){
			if(Math.pow(2, i) == x){return true;}
			i++;
		}	
		return false;
	}
	
	public static int[] calculateBlockPool(int s){
		int i;
		for(i = 0; Math.pow(2, i) <= s; i++);		
		int[] pool = new int[i];	
		for(int k = 0; k < i; k++){ pool[k] = (int) Math.pow(2, k);}
		return pool;
	}
	
}
