package Algo;

import java.util.*;

public class BuddyAlgo {

	private int size;
	static LinkedList<ArrayList<Integer>> blocks = null;
	static int[] blockSizePool = null;
	
	public BuddyAlgo(int size){	
		if(!checkForPower(size)) throw new IllegalArgumentException("Size muss eine Zweierpotenz sein");	
		this.size = size;
		blocks = new LinkedList<ArrayList<Integer>>();
		blocks.add(new ArrayList<Integer>());
		blocks.get(0).add(0, 0);
		blocks.get(0).add(1, size-1);
		blocks.get(0).add(2, 0); 
		blockSizePool = calculateBlockPool(size);
	}
	
	public static int allocateBlock(int size){
		recycle();
		int tempBlockSize = size;
		ArrayList<Integer> blockToAllocate = null;
		if(!checkForPower(size)){ tempBlockSize = nextBlockSize(size);}
		blockToAllocate = splitBlocks(tempBlockSize);
		blockToAllocate.set(2, 1);
		
		return blocks.indexOf(blockToAllocate);
	}
	
	public static void deallocateBlock(int index){
		
		blocks.get(index).set(2, 0);
	}
	
	public static ArrayList<Integer> splitBlocks(int size){
		ArrayList<Integer> res = null;
		ArrayList<Integer> lowBlock = null;
		for(ArrayList<Integer> x : blocks){
			if(x.get(1)-x.get(0)+1 == size && x.get(2) == 0){
				return x;			
			}
		}
		return splitBlocksHelp(smallestFittingBlock(size), size);
	}
	
	public static ArrayList<Integer> smallestFittingBlock(int size){
		ArrayList<Integer> res = null;
		
		for(ArrayList<Integer> x : blocks){
			if(res == null && x.get(2) == 0){res = x;}
			if(x.get(1)-x.get(0)+1 > size && x.get(2) == 0 && res.get(1)-res.get(0)+1 > x.get(1)-x.get(0)+1){res = x;}			
		}
		
		return res;
	}
	
	public static ArrayList<Integer> splitBlocksHelp(ArrayList<Integer> toSplit, int size){
		ArrayList<Integer> result = new ArrayList<Integer>();

		if(toSplit.get(1)-toSplit.get(0)+1 == size){
			result = toSplit;
		} else {
			ArrayList<Integer> temp1 = new ArrayList<Integer>();


			toSplit.set(0, toSplit.get(0));
			toSplit.set(1, ((toSplit.get(1)+1)/2)-1);
			toSplit.set(2, 0);
			temp1.add(0, (toSplit.get(1)+1)/2);
			temp1.add(1, toSplit.get(1));
			temp1.add(2, 0);

			blocks.add(blocks.indexOf(toSplit)+1, temp1);
			
			result = splitBlocksHelp(toSplit, size);
			
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
	
	public static void recycle(){
		ArrayList<Integer> temp = null;	
		LOOP:
		

		for(ArrayList<Integer> x : blocks){
			if(x.get(2) == 0){
				for(ArrayList<Integer> y : blocks){
					if(y != x && y.get(1)+1 == x.get(0)&& y.get(2) == 0){
						temp = new ArrayList<Integer>();
						temp.add(0, y.get(0));
						temp.add(1, x.get(1));
						temp.add(2, 0);
						
						break LOOP;
					}
					
					if(y != x && x.get(1)+1 == y.get(0) && y.get(2) == 0){
						temp = new ArrayList<Integer>();
						temp.add(0, x.get(0));
						temp.add(1, y.get(1));
						temp.add(2, 0);
						
						
						break LOOP;
					}
					
				}
				
			}
			
		}
		
	}
	
	@Override
	public String toString(){
		String string = "";
		int temp = 0;
		while(temp != size){
			for(ArrayList<Integer> x : blocks){
				if(temp == x.get(0)){
					System.out.print("[" + x.get(0) + ".." + x.get(2) + ".." + x.get(1) + "] ");
					temp = x.get(1)+1;
					break;
				}
				
			}
			
		}
		System.out.println();
		return string;
	}
	
}
