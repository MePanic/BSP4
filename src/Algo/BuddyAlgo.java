package Algo;

import java.util.*;

public class BuddyAlgo {

	private static int size;
	static LinkedList<ArrayList<Integer>> blocks = null;
	static int[] blockSizePool = null;
	static ArrayList<Integer> indexPool = new ArrayList<Integer>();
	
	public BuddyAlgo(int size){	
		if(!checkForPower(size)) throw new IllegalArgumentException("Size muss eine Zweierpotenz sein");	
		this.size = size;
		blocks = new LinkedList<ArrayList<Integer>>();
		blocks.add(new ArrayList<Integer>());
		blocks.get(0).add(0, size);
		blocks.get(0).add(1, 0);
		blockSizePool = calculateBlockPool(size);
	}
	
	public static int allocateBlock(int allocateSize){
		recycle();
		int tempBlockSize = allocateSize;
		ArrayList<Integer> blockToAllocate = null;
		if(!checkForPower(allocateSize)){ tempBlockSize = nextBlockSize(allocateSize);}
		blockToAllocate = splitBlocks(tempBlockSize);
		blockToAllocate.set(1, 1);
		for(int i = 0; i< size; i++){
			if(!indexPool.contains(i)){
				if(blockToAllocate.size() == 2){blockToAllocate.add(2, i);}
				else {blockToAllocate.set(2, i);}
				indexPool.add(i);
				break;
			}
		}
		return blockToAllocate.get(2);
	}
	
	public static void deallocateBlock(int index){
		
		for(ArrayList<Integer> x : blocks){
			if(x.get(2) == index){
				blocks.get(index).set(1, 0);
				blocks.get(index).remove(2);
				break;
			}
		}
		recycle();
	}
	
	public static ArrayList<Integer> splitBlocks(int size){
		ArrayList<Integer> res = null;
		ArrayList<Integer> lowBlock = null;
		for(ArrayList<Integer> x : blocks){
			if(x.get(0) == size && x.get(1) == 0){
				return x;			
			}
		}
		res = smallestFittingBlock(size);
		return splitBlocksHelp(res, size);
	}
	
	public static ArrayList<Integer> smallestFittingBlock(int size){
		ArrayList<Integer> res = new ArrayList<Integer>();
		
		for(ArrayList<Integer> x : blocks){
			if(x.get(0) > size && x.get(1) == 0){res = x; break;}
		}
		
		for(ArrayList<Integer> x : blocks){
			if(x.get(0) > size && x.get(1) == 0 && res.get(0) > x.get(0)){res = x;}			
		}
		
		return res;
	}
	
	public static ArrayList<Integer> splitBlocksHelp(ArrayList<Integer> toSplit, int size){
		ArrayList<Integer> result = new ArrayList<Integer>();
		if(toSplit.get(0) == 0){System.out.println("why the hell"); return null;}
		if(toSplit.get(0) == size){
			result = toSplit;
		} else {
			ArrayList<Integer> temp1 = new ArrayList<Integer>();



			temp1.add(0, (toSplit.get(0)/2));
			temp1.add(1, 0);
			toSplit.set(0, (toSplit.get(0)/2));
			toSplit.set(1, 0);
System.out.println("temp: " + temp1);
System.out.println("Split: " + toSplit);
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
			if(x.get(1) == 0){
				for(ArrayList<Integer> y : blocks){

					if(y != x  && y.get(0) == x.get(0)&& y.get(1) == 0){				
						x.set(0, (y.get(0)*2));
						blocks.remove(y);
						break LOOP;
					}
//					System.out.println("recycle blub" + x + "," + blocks.indexOf(x) + " und " + y + "," + blocks.indexOf(y));	
//					if(y != x){
//						System.out.println("recycle blub, x!=y");	
//					}
//					if((blocks.indexOf(x)+1 == blocks.indexOf(y)||blocks.indexOf(x) == blocks.indexOf(y)+1)){
//						System.out.println("recycle blub, index ");	
//					}
//					if(y.get(0) == x.get(0)){
//						System.out.println("recycle blub, size ==");	
//					}
//					if(y.get(1) == 0){
//						System.out.println("recycle blub, in use y");	
//					}

				}
			}
		}
	}
	
	@Override
	public String toString(){
		
		System.out.println(blocks);
		
		return "";
	}
	
}
