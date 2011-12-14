package Tests;

import static org.junit.Assert.*;
import org.junit.Test;

import Algo.BuddyAlgo;

public class AlgoTest {

	@Test
	public void test() {
		
		assertEquals(true, true);
		
		BuddyAlgo blub = new BuddyAlgo(512);
		
		blub.allocateBlock(16);
		blub.toString();
		blub.allocateBlock(16);
		blub.toString();
		blub.allocateBlock(4);
		blub.toString();
		blub.allocateBlock(16);
		blub.toString();
		blub.allocateBlock(16);
		blub.toString();
		blub.deallocateBlock(2);
		blub.toString();
		blub.allocateBlock(16);
		blub.toString();
		
	}

}
