package Tests;

import static org.junit.Assert.*;
import org.junit.Test;

import Algo.BuddyAlgo;

public class AlgoTest {

	@Test
	public void test() throws Exception {
		
		assertEquals(true, true);
		
		BuddyAlgo blub = new BuddyAlgo(512);
		
		blub.allocateBlock(16);
		blub.toString();
		blub.allocateBlock(4);
		blub.allocateBlock(4);
		blub.allocateBlock(4);
		blub.allocateBlock(4);
		blub.allocateBlock(4);
		blub.allocateBlock(4);
		blub.allocateBlock(4);
		blub.toString();
		blub.deallocateBlock(3);
		blub.toString();
		blub.deallocateBlock(4);
		blub.toString();
		blub.deallocateBlock(5);
		blub.toString();
		blub.deallocateBlock(6);
		blub.toString();
		blub.deallocateBlock(1);
		blub.deallocateBlock(2);
		blub.deallocateBlock(0);
		blub.toString();
		
	}

}
