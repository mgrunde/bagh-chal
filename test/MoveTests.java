

import static org.junit.Assert.*;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;

import org.junit.Test;

public class MoveTests {
	
	// create a color variable
	Color c = Color.BLACK;
	JLabel l1 = new JLabel("");
	JLabel l2 = new JLabel("");
	JLabel l3 = new JLabel("");
	JButton l4 = new JButton("");
	
	// TEST INVALID MOVE

	// test too far up and left
	@Test
	public void testOutOfBoundsUp() {
		GameTable table = new GameTable(l1, l2, l3, l4);
		Tile t = new GoatTile(0, 0, table);
		
		assertFalse(t.isValidMove(-1, 0));
		assertFalse(t.isValidMove(0, -1));
		assertFalse(t.isValidMove(-1, -1));
		assertFalse(t.isValidMove(-2, 0));
	}
	
	// test too far down and right
	@Test
	public void testOutOfBoundsDown() {
		GameTable table = new GameTable(l1, l2, l3, l4);
		Tile t = new GoatTile(4, 4, table);
		 
		assertFalse(t.isValidMove(5, 0));
		assertFalse(t.isValidMove(0, 5));
		assertFalse(t.isValidMove(5, 5));
		assertFalse(t.isValidMove(7, 4));
	}
	
	// test valid/invalid position for all the exceptions
	@Test
	public void testBoardMoveExceptions() {
		GameTable table = new GameTable(l1, l2, l3, l4);
		Tile t10 = new GoatTile(1, 0, table);
		Tile t30 = new GoatTile(3, 0, table);
		Tile t01 = new GoatTile(0, 1, table);
		Tile t21 = new GoatTile(2, 1, table);
		Tile t41 = new GoatTile(4, 1, table);
		Tile t12 = new GoatTile(1, 2, table);
		Tile t32 = new GoatTile(3, 2, table);
		Tile t03 = new GoatTile(0, 3, table);
		Tile t23 = new GoatTile(2, 3, table);
		Tile t43 = new GoatTile(4, 3, table);
		Tile t14 = new GoatTile(1, 4, table);
		Tile t34 = new GoatTile(3, 4, table);
		
		new EmptyTile(0, 0, table);
		new EmptyTile(4, 0, table);
		new EmptyTile(0, 4, table);
		new EmptyTile(4, 4, table);
		
		
		assertTrue(t10.isValidMove(0, 0));
		assertFalse(t10.isValidMove(0, 1));
		
		assertTrue(t30.isValidMove(2, 0));
		assertFalse(t30.isValidMove(2, 1));
		
		assertTrue(t01.isValidMove(0, 0));
		assertFalse(t01.isValidMove(1, 0));
		
		assertTrue(t21.isValidMove(1, 1));
		assertFalse(t21.isValidMove(1, 2));
		
		assertTrue(t41.isValidMove(4, 0));
		assertFalse(t41.isValidMove(0, 3));
		
		assertTrue(t12.isValidMove(0, 2));
		assertFalse(t12.isValidMove(2, 1));
		
		assertTrue(t32.isValidMove(4, 2));
		assertFalse(t32.isValidMove(4, 1));
		
		assertTrue(t03.isValidMove(0, 4));
		assertFalse(t03.isValidMove(1, 4));
		
		assertTrue(t23.isValidMove(2, 4));
		assertFalse(t23.isValidMove(3, 4));
		
		assertTrue(t43.isValidMove(4, 4));
		assertFalse(t43.isValidMove(3, 4));
		
		assertTrue(t14.isValidMove(2, 4));
		assertFalse(t14.isValidMove(0, 3));
		
		assertTrue(t34.isValidMove(2, 4));
		assertFalse(t34.isValidMove(4, 3));
		
	}
	
	// test move goat more than one step
	@Test
	public void testMultipleSteps() {
		GameTable table = new GameTable(l1, l2, l3, l4);
		Tile t = new GoatTile(2, 2, table);
		
		assertFalse(t.isValidMove(4, 2));
		assertFalse(t.isValidMove(0, 2));
		assertFalse(t.isValidMove(2, 4));
		assertFalse(t.isValidMove(2, 0));
	}
	
	// test move into spot already taken by goat Tile (goat and tiger)
	@Test
	public void testMoveIntoNotEmpty() {
		GameTable table = new GameTable(l1, l2, l3, l4);
		Tile gt = new GoatTile(0, 0, table);
		Tile tt = new TigerTile(0, 1, table);
		
		assertFalse(gt.isValidMove(0,1));
		assertFalse(tt.isValidMove(0,0));
		
		assertEquals(table.getTile(0, 0), gt);
		assertEquals(table.getTile(0, 1), tt);
	}
	
	// test can't move empty tile
	@Test
	public void testCannotMoveEmpty() {
		GameTable table = new GameTable(l1, l2, l3, l4);
		Tile et = new EmptyTile(2, 2, table);
		
		assertFalse(et.isValidMove(1, 2));
	}
	
	// test move tiger into valid next spot
	@Test
	public void testTigerMoves() {
		GameTable table = new GameTable(l1, l2, l3, l4);
		Tile t = new TigerTile(0, 0, table);
		
		assertTrue(t.isValidMove(0, 1));
		
		t.move(0,1);
		
		assertEquals(table.getTile(0, 1), t);
		assertNotEquals(table.getTile(0, 0), t);	
	}
	
	// test move goat into valid next spot
	@Test
	public void testGoatMoves() {
		GameTable table = new GameTable(l1, l2, l3, l4);
		Tile t = new GoatTile(0, 0, table);
		
		assertTrue(t.isValidMove(0, 1));
		
		t.move(0,1);
		
		assertEquals(table.getTile(0, 1), t);
		assertNotEquals(table.getTile(0, 0), t);	
	}
	
	// test can't move around the board
	@Test
	public void testMoveBehindBoard() {
		GameTable table = new GameTable(l1, l2, l3, l4);
		TigerTile[] tigers = table.getTigers();
		Tile gt = new GoatTile(0, 2, table);
		
		assertFalse(gt.isValidMove(4, 2));
		
		new EmptyTile(4, 0, table);
		new EmptyTile(0, 4, table);
		
		assertFalse(tigers[0].isValidMove(4, 0));
		assertFalse(tigers[0].isValidMove(0, 4));
		
		assertFalse(tigers[3].isValidMove(0, 4));
		assertFalse(tigers[3].isValidMove(4, 0));
		
		new EmptyTile(0, 0, table);
		new EmptyTile(4, 4, table);
		
		Tile t1 = new TigerTile(4, 0, table);
		Tile t2 = new TigerTile(0, 4, table);
		 
		assertFalse(t1.isValidMove(0, 0));
		assertFalse(t1.isValidMove(4, 4));
		
		assertFalse(t2.isValidMove(0, 0));
		assertFalse(t2.isValidMove(4, 4));
	}
	
	// test jump 3 down the side
	@Test
	public void testJumpThreeDownSide() {
		GameTable table = new GameTable(l1, l2, l3, l4);
		TigerTile[] tigers = table.getTigers();
		
		assertFalse(tigers[2].isValidMove(4, 3));
	}
	
	//TEST JUMPING
	
	// test jump tiger (through move)
	@Test
	public void testTigerJump() {
		GameTable table = new GameTable(l1, l2, l3, l4);
		Tile gt = new GoatTile(0, 1, table);
		Tile tt = new TigerTile(0, 0, table);
		
		
		assertTrue(tt.isValidMove(0, 2));
		
		tt.move(0, 2);
		
		assertEquals(table.getTile(0, 2), tt);
		assertNotEquals(table.getTile(0, 0), tt);
		assertNotEquals(table.getTile(0, 1), gt);
	}
	
		// test cannot jump goat (through move)
	
	@Test
	public void testGoatCannotJump() {
		GameTable table = new GameTable(l1, l2, l3, l4);
		Tile gt = new GoatTile(0, 0, table);
		Tile tt = new TigerTile(0, 1, table);
		
		assertFalse(gt.isValidMove(0, 2));
		
		gt.move(0, 2);
		
		
		assertEquals(table.getTile(0, 0), gt);
		assertEquals(table.getTile(0, 1), tt);
		assertNotEquals(table.getTile(0, 2), tt);
	}
	
	// test jump tiger jumps out of bounds (through move)
		@Test
		public void testTigerJumpOutOfBounds() {
			GameTable table = new GameTable(l1, l2, l3, l4);
			Tile gt = new GoatTile(0, 0, table);
			Tile tt = new TigerTile(0, 1, table);
			
			
			assertFalse(tt.isValidMove(0, -1));
			
			tt.move(0, -1);
			
			assertEquals(table.getTile(0, 0), gt);
			assertEquals(table.getTile(0, 1), tt);
			assertNotEquals(table.getTile(0, 2), tt);
		}
	
		// All tigers jump when possible
		@Test
		public void testIsJumpOneTigerCenter() {
			GameTable table = new GameTable(l1, l2, l3, l4);
			TigerTile t = new TigerTile(2, 2, table);
			new EmptyTile(0, 0, table);
			new EmptyTile(0, 4, table);
			new EmptyTile(4, 0, table);
			new EmptyTile(4, 4, table);
			
			new GoatTile(1, 1, table);
			new GoatTile(1, 2, table);
			new GoatTile(1, 3, table);
			new GoatTile(2, 1, table);
			new GoatTile(2, 3, table);
			new GoatTile(3, 1, table);
			new GoatTile(3, 2, table);
			new GoatTile(3, 3, table);
			
			assertTrue(t.isJump(0, 0));
			assertTrue(t.isJump(0, 2));
			assertTrue(t.isJump(0, 4));
			assertTrue(t.isJump(2, 0));
			assertTrue(t.isJump(2, 4));
			assertTrue(t.isJump(4, 0));
			assertTrue(t.isJump(4, 2));
			assertTrue(t.isJump(4, 4));
			
			assertFalse(t.isJump(0, 1));
			assertFalse(t.isJump(0, 3));
			assertFalse(t.isJump(1, 0));
			assertFalse(t.isJump(1, 1));
			assertFalse(t.isJump(1, 2));
			assertFalse(t.isJump(1, 3));
			assertFalse(t.isJump(1, 4));
			assertFalse(t.isJump(2, 1));
			assertFalse(t.isJump(2, 2));
			assertFalse(t.isJump(2, 3));
			assertFalse(t.isJump(3, 0));
			assertFalse(t.isJump(3, 1));
			assertFalse(t.isJump(3, 2));
			assertFalse(t.isJump(3, 3));
			assertFalse(t.isJump(3, 4));
			assertFalse(t.isJump(4, 1));
			assertFalse(t.isJump(4, 3));
		}
	// test isJump corner tigers
		@Test
		public void testIsJumpCornerTigers() {
			GameTable table = new GameTable(l1, l2, l3, l4);
			TigerTile[] tigers = table.getTigers();
			
			new GoatTile(1, 0, table);
			new GoatTile(1, 1, table);
			new GoatTile(0, 1, table);
			new GoatTile(3, 0, table);
			new GoatTile(3, 1, table);
			new GoatTile(4, 1, table);
			new GoatTile(1, 3, table);
			new GoatTile(1, 4, table);
			new GoatTile(0, 3, table);
			new GoatTile(3, 4, table);
			new GoatTile(3, 3, table);
			new GoatTile(4, 3, table);
			
			TigerTile t = tigers[0];
			assertTrue(t.isJump(0, 2));
			assertTrue(t.isJump(2, 2));
			assertTrue(t.isJump(2, 0));
			assertFalse(t.isJump(0, 0));
			assertFalse(t.isJump(0, 4));
			assertFalse(t.isJump(2, 4));
			assertFalse(t.isJump(4, 0));
			assertFalse(t.isJump(4, 2));
			assertFalse(t.isJump(4, 4));
			assertFalse(t.isJump(0, 1));
			assertFalse(t.isJump(0, 3));
			assertFalse(t.isJump(1, 0));
			assertFalse(t.isJump(1, 1));
			assertFalse(t.isJump(1, 2));
			assertFalse(t.isJump(1, 3));
			assertFalse(t.isJump(1, 4));
			assertFalse(t.isJump(2, 1));
			assertFalse(t.isJump(2, 3));
			assertFalse(t.isJump(3, 0));
			assertFalse(t.isJump(3, 1));
			assertFalse(t.isJump(3, 2));
			assertFalse(t.isJump(3, 3));
			assertFalse(t.isJump(3, 4));
			assertFalse(t.isJump(4, 1));
			assertFalse(t.isJump(4, 3));
			
			t = tigers[1];
			assertTrue(t.isJump(0, 2));
			assertTrue(t.isJump(2, 2));
			assertTrue(t.isJump(2, 4));
			assertFalse(t.isJump(0, 0));
			assertFalse(t.isJump(0, 4));
			assertFalse(t.isJump(2, 0));
			assertFalse(t.isJump(4, 0));
			assertFalse(t.isJump(4, 2));
			assertFalse(t.isJump(4, 4));
			assertFalse(t.isJump(0, 1));
			assertFalse(t.isJump(0, 3));
			assertFalse(t.isJump(1, 0));
			assertFalse(t.isJump(1, 1));
			assertFalse(t.isJump(1, 2));
			assertFalse(t.isJump(1, 3));
			assertFalse(t.isJump(1, 4));
			assertFalse(t.isJump(2, 1));
			assertFalse(t.isJump(2, 3));
			assertFalse(t.isJump(3, 0));
			assertFalse(t.isJump(3, 1));
			assertFalse(t.isJump(3, 2));
			assertFalse(t.isJump(3, 3));
			assertFalse(t.isJump(3, 4));
			assertFalse(t.isJump(4, 1));
			assertFalse(t.isJump(4, 3));
			
			t = tigers[2];
			assertTrue(t.isJump(4, 2));
			assertTrue(t.isJump(2, 2));
			assertTrue(t.isJump(2, 0));
			assertFalse(t.isJump(0, 0));
			assertFalse(t.isJump(0, 2));
			assertFalse(t.isJump(0, 4));
			assertFalse(t.isJump(2, 4));
			assertFalse(t.isJump(4, 0));
			assertFalse(t.isJump(4, 4));
			assertFalse(t.isJump(0, 1));
			assertFalse(t.isJump(0, 3));
			assertFalse(t.isJump(1, 0));
			assertFalse(t.isJump(1, 1));
			assertFalse(t.isJump(1, 2));
			assertFalse(t.isJump(1, 3));
			assertFalse(t.isJump(1, 4));
			assertFalse(t.isJump(2, 1));
			assertFalse(t.isJump(2, 3));
			assertFalse(t.isJump(3, 0));
			assertFalse(t.isJump(3, 1));
			assertFalse(t.isJump(3, 2));
			assertFalse(t.isJump(3, 3));
			assertFalse(t.isJump(3, 4));
			assertFalse(t.isJump(4, 1));
			assertFalse(t.isJump(4, 3));
			
			t = tigers[3];
			assertTrue(t.isJump(4, 2));
			assertTrue(t.isJump(2, 2));
			assertTrue(t.isJump(2, 4));
			assertFalse(t.isJump(0, 0));
			assertFalse(t.isJump(0, 2));
			assertFalse(t.isJump(0, 4));
			assertFalse(t.isJump(2, 0));
			assertFalse(t.isJump(4, 0));
			assertFalse(t.isJump(4, 4));
			assertFalse(t.isJump(0, 1));
			assertFalse(t.isJump(0, 3));
			assertFalse(t.isJump(1, 0));
			assertFalse(t.isJump(1, 1));
			assertFalse(t.isJump(1, 2));
			assertFalse(t.isJump(1, 3));
			assertFalse(t.isJump(1, 4));
			assertFalse(t.isJump(2, 1));
			assertFalse(t.isJump(2, 3));
			assertFalse(t.isJump(3, 0));
			assertFalse(t.isJump(3, 1));
			assertFalse(t.isJump(3, 2));
			assertFalse(t.isJump(3, 3));
			assertFalse(t.isJump(3, 4));
			assertFalse(t.isJump(4, 1));
			assertFalse(t.isJump(4, 3));
		}
		
		// test isJump middle tigers
		@Test
		public void testIsJumpMiddleTigers() {
			GameTable table = new GameTable(l1, l2, l3, l4);
			TigerTile t1 = new TigerTile(2, 0, table);
			TigerTile t2 = new TigerTile(4, 2, table);
			TigerTile t3 = new TigerTile(2, 4, table);
			TigerTile t4 = new TigerTile(0, 2, table);
			
			new EmptyTile(0, 0, table);
			new EmptyTile(0, 4, table);
			new EmptyTile(4, 0, table);
			new EmptyTile(4, 4, table);
			
			new GoatTile(1, 0, table);
			new GoatTile(1, 1, table);
			new GoatTile(0, 1, table);
			new GoatTile(3, 0, table);
			new GoatTile(3, 1, table);
			new GoatTile(4, 1, table);
			new GoatTile(1, 3, table);
			new GoatTile(1, 4, table);
			new GoatTile(0, 3, table);
			new GoatTile(3, 4, table);
			new GoatTile(3, 3, table);
			new GoatTile(4, 3, table);
			
			TigerTile t = t1;
			assertTrue(t.isJump(0, 0));
			assertTrue(t.isJump(4, 0));
			assertFalse(t.isJump(4, 2));
			assertFalse(t.isJump(2, 2));
			assertFalse(t.isJump(2, 4));
			assertFalse(t.isJump(0, 2));
			assertFalse(t.isJump(0, 4));
			assertFalse(t.isJump(2, 0));
			assertFalse(t.isJump(4, 4));
			assertFalse(t.isJump(0, 1));
			assertFalse(t.isJump(0, 3));
			assertFalse(t.isJump(1, 0));
			assertFalse(t.isJump(1, 1));
			assertFalse(t.isJump(1, 2));
			assertFalse(t.isJump(1, 3));
			assertFalse(t.isJump(1, 4));
			assertFalse(t.isJump(2, 1));
			assertFalse(t.isJump(2, 3));
			assertFalse(t.isJump(3, 0));
			assertFalse(t.isJump(3, 1));
			assertFalse(t.isJump(3, 2));
			assertFalse(t.isJump(3, 3));
			assertFalse(t.isJump(3, 4));
			assertFalse(t.isJump(4, 1));
			assertFalse(t.isJump(4, 3));
			
			t = t2;
			assertTrue(t.isJump(4, 0));
			assertTrue(t.isJump(4, 4));
			assertFalse(t.isJump(4, 2));
			assertFalse(t.isJump(2, 2));
			assertFalse(t.isJump(2, 4));
			assertFalse(t.isJump(0, 0));
			assertFalse(t.isJump(0, 2));
			assertFalse(t.isJump(0, 4));
			assertFalse(t.isJump(2, 0));
			assertFalse(t.isJump(0, 1));
			assertFalse(t.isJump(0, 3));
			assertFalse(t.isJump(1, 0));
			assertFalse(t.isJump(1, 1));
			assertFalse(t.isJump(1, 2));
			assertFalse(t.isJump(1, 3));
			assertFalse(t.isJump(1, 4));
			assertFalse(t.isJump(2, 1));
			assertFalse(t.isJump(2, 3));
			assertFalse(t.isJump(3, 0));
			assertFalse(t.isJump(3, 1));
			assertFalse(t.isJump(3, 2));
			assertFalse(t.isJump(3, 3));
			assertFalse(t.isJump(3, 4));
			assertFalse(t.isJump(4, 1));
			assertFalse(t.isJump(4, 3));
			
			t = t3;
			assertTrue(t.isJump(4, 4));
			assertTrue(t.isJump(0, 4));
			assertFalse(t.isJump(4, 2));
			assertFalse(t.isJump(2, 2));
			assertFalse(t.isJump(2, 4));
			assertFalse(t.isJump(0, 0));
			assertFalse(t.isJump(0, 2));
			assertFalse(t.isJump(2, 0));
			assertFalse(t.isJump(4, 0));
			assertFalse(t.isJump(0, 1));
			assertFalse(t.isJump(0, 3));
			assertFalse(t.isJump(1, 0));
			assertFalse(t.isJump(1, 1));
			assertFalse(t.isJump(1, 2));
			assertFalse(t.isJump(1, 3));
			assertFalse(t.isJump(1, 4));
			assertFalse(t.isJump(2, 1));
			assertFalse(t.isJump(2, 3));
			assertFalse(t.isJump(3, 0));
			assertFalse(t.isJump(3, 1));
			assertFalse(t.isJump(3, 2));
			assertFalse(t.isJump(3, 3));
			assertFalse(t.isJump(3, 4));
			assertFalse(t.isJump(4, 1));
			assertFalse(t.isJump(4, 3));
			
			t = t4;
			assertTrue(t.isJump(0, 4));
			assertTrue(t.isJump(0, 0));
			assertFalse(t.isJump(4, 2));
			assertFalse(t.isJump(2, 2));
			assertFalse(t.isJump(2, 4));
			assertFalse(t.isJump(0, 2));
			assertFalse(t.isJump(2, 0));
			assertFalse(t.isJump(4, 0));
			assertFalse(t.isJump(4, 4));
			assertFalse(t.isJump(0, 1));
			assertFalse(t.isJump(0, 3));
			assertFalse(t.isJump(1, 0));
			assertFalse(t.isJump(1, 1));
			assertFalse(t.isJump(1, 2));
			assertFalse(t.isJump(1, 3));
			assertFalse(t.isJump(1, 4));
			assertFalse(t.isJump(2, 1));
			assertFalse(t.isJump(2, 3));
			assertFalse(t.isJump(3, 0));
			assertFalse(t.isJump(3, 1));
			assertFalse(t.isJump(3, 2));
			assertFalse(t.isJump(3, 3));
			assertFalse(t.isJump(3, 4));
			assertFalse(t.isJump(4, 1));
			assertFalse(t.isJump(4, 3));
		}
		
	// can't jump illegally
	@Test
	public void testCannotIllegalJumpTigersSides() {
		GameTable table = new GameTable(l1, l2, l3, l4);
		TigerTile t1 = new TigerTile(0, 1, table);
		TigerTile t2 = new TigerTile(0, 3, table);
		TigerTile t3 = new TigerTile(4, 1, table);
		TigerTile t4 = new TigerTile(4, 3, table);
		
		new GoatTile(1, 2, table);
		new GoatTile(3, 2, table);
		
		new EmptyTile(0, 0, table);
		new EmptyTile(0, 4, table);
		new EmptyTile(4, 0, table);
		new EmptyTile(4, 4, table);
		
		assertFalse(t1.isValidMove(2, 3));
		assertFalse(t2.isValidMove(2, 1));
		assertFalse(t3.isValidMove(2, 3));
		assertFalse(t4.isValidMove(2, 1));
	}
	
	// can't jump illegally
		@Test
		public void testCannotIllegalJumpTigersSandwich() {
			GameTable table = new GameTable(l1, l2, l3, l4);
			TigerTile t1 = new TigerTile(1, 0, table);
			TigerTile t2 = new TigerTile(3, 0, table);
			TigerTile t3 = new TigerTile(1, 4, table);
			TigerTile t4 = new TigerTile(3, 4, table);
			
			new GoatTile(2, 1, table);
			new GoatTile(2, 3, table);
			
			new EmptyTile(0, 0, table);
			new EmptyTile(0, 4, table);
			new EmptyTile(4, 0, table);
			new EmptyTile(4, 4, table);
			
			assertFalse(t1.isValidMove(3, 2));
			assertFalse(t2.isValidMove(1, 2));
			assertFalse(t3.isValidMove(3, 2));
			assertFalse(t4.isValidMove(1, 2));
		}
		
	
		
}
