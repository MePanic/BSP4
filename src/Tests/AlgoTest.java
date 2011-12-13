package Tests;

import static org.junit.Assert.*;
import static Algo.BuddyAlgo.*;
import org.junit.Test;

import Algo.BuddyAlgo;

public class AlgoTest {

	@Test
	public void test() {
		
		BuddyAlgo blub = new BuddyAlgo(512);
		
		assertEquals(true, Algo.BuddyAlgo.checkForPower(4));
		assertEquals(true, Algo.BuddyAlgo.checkForPower(8));
		assertEquals(true, Algo.BuddyAlgo.checkForPower(16));
		assertEquals(true, Algo.BuddyAlgo.checkForPower(32));
		assertEquals(true, Algo.BuddyAlgo.checkForPower(64));
		assertEquals(true, Algo.BuddyAlgo.checkForPower(128));
		assertEquals(true, Algo.BuddyAlgo.checkForPower(4096));
		assertEquals(false, Algo.BuddyAlgo.checkForPower(555));
		assertEquals(false, Algo.BuddyAlgo.checkForPower(513));
		assertEquals(false, Algo.BuddyAlgo.checkForPower(511));
		assertEquals(false, Algo.BuddyAlgo.checkForPower(127));
		assertEquals(false, Algo.BuddyAlgo.checkForPower(65));
		
		for(int x : Algo.BuddyAlgo.calculateBlockPool(16)){
			System.out.println(x);
		}
		
		assertEquals(16, blub.nextBlockSize(12));
		assertEquals(512, blub.nextBlockSize(257));
		assertEquals(128, blub.nextBlockSize(65));
		assertEquals(64, blub.nextBlockSize(63));
	}

}
