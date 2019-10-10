

import static org.junit.Assert.*;

//import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;

import org.junit.Test;

public class UserTests {
	JLabel l1 = new JLabel("");
	JLabel l2 = new JLabel("");
	JLabel l3 = new JLabel("");
	JButton l4 = new JButton("");
		
	
	// All tigers jump when possible
	@Test
	public void tigersJumpWhenPossibletest() {
		GameTable table = new GameTable(l1, l2, l3, l4);
		Tile[][] map = table.getMap();
		TigerTile[] tigers = table.getTigers();
		
		new GoatTile(1, 0, table);
		assertTrue(tigers[0].isJump(2, 0));
		
		table.moveTiger();
		
		assertTrue(map[0][0].isEmpty());
		assertTrue(map[1][0].isEmpty());
		assertTrue(map[2][0].isTiger());
		
		new GoatTile(1, 0, table);
		assertTrue(tigers[0].isValidMove(0, 0));
		
		table.moveTiger();
		 
		assertTrue(map[2][0].isEmpty());
		assertTrue(map[1][0].isEmpty());
		assertTrue(map[0][0].isTiger());
		
		
		new GoatTile(0, 3, table);
		assertTrue(tigers[1].isJump(0, 2));
		
		table.moveTiger();
		
		assertTrue(map[0][4].isEmpty());
		assertTrue(map[0][3].isEmpty());
		assertTrue(map[0][2].isTiger());
		
		new GoatTile(0, 3, table);
		assertTrue(tigers[1].isValidMove(0, 4));
		
		table.moveTiger();
		 
		assertTrue(map[0][2].isEmpty());
		assertTrue(map[0][3].isEmpty());
		assertTrue(map[0][4].isTiger());
		


		new GoatTile(3, 0, table);
		assertTrue(tigers[2].isJump(2, 0));
		
		table.moveTiger();
		
		assertTrue(map[4][0].isEmpty());
		assertTrue(map[3][0].isEmpty());
		assertTrue(map[2][0].isTiger());
		
		new GoatTile(3, 0, table);
		assertTrue(tigers[2].isValidMove(4, 0));
		
		table.moveTiger();
		 
		assertTrue(map[2][0].isEmpty());
		assertTrue(map[3][0].isEmpty());
		assertTrue(map[4][0].isTiger());
		


		new GoatTile(4, 3, table);
		assertTrue(tigers[3].isJump(4, 2));
		
		table.moveTiger();
		
		assertTrue(map[4][4].isEmpty());
		assertTrue(map[4][3].isEmpty());
		assertTrue(map[4][2].isTiger());
		
		new GoatTile(3, 2, table);
		assertTrue(tigers[3].isValidMove(2, 2));
		
		table.moveTiger();
		 
		assertTrue(map[4][2].isEmpty());
		assertTrue(map[3][2].isEmpty());
		assertTrue(map[2][2].isTiger());
	}
	
	// ADDGOAT TESTS
	// adding a goat to an un-jumpable location adds the goat
	@Test
	public void testAddGoat() {
		GameTable table = new GameTable(l1, l2, l3, l4);
		Tile[][] map = table.getMap();
		
		table.addGoatTile(5 * GameTable.TILE_SIZE, 5 * GameTable.TILE_SIZE);
		
		assertTrue(map[2][2].isGoat());
		assertTrue(table.getNumGoatsLeft() == 19);
		
		table.addGoatTile(5 * GameTable.TILE_SIZE, 5 * GameTable.TILE_SIZE);
		assertTrue(map[2][2].isGoat());
		assertTrue(table.getNumGoatsLeft() == 19);
	}
	
	// SELECT TESTS
	// select and move to valid spot
	@Test
	public void testSelect() {
		GameTable table = new GameTable(l1, l2, l3, l4);
		Tile[][] map = table.getMap();
		TigerTile[] tigers = table.getTigers();
		
		table.addGoatTile(5 * GameTable.TILE_SIZE, 5 * GameTable.TILE_SIZE);
		tigers[0].move(0, 0);
		tigers[1].move(0, 4);
		tigers[2].move(4, 0);
		tigers[3].move(4, 4);
		
		for(int i = 0; i < 19; i++) {
			table.addGoatTile(3 * GameTable.TILE_SIZE, 1 * GameTable.TILE_SIZE);
		}
		
		assertTrue(table.getNumGoatsLeft() == 0);
		table.select(5 * GameTable.TILE_SIZE, 5 * GameTable.TILE_SIZE);
		
		assertTrue(table.getSelected());
		assertTrue(((GoatTile) map[2][2]).getSelected());
		
		
		table.moveSelected(3 * GameTable.TILE_SIZE, 5 * GameTable.TILE_SIZE);
		assertFalse(table.getSelected());
		assertFalse(((GoatTile) map[1][2]).getSelected());
		assertTrue(map[2][2].isEmpty());
		assertTrue(table.getNumGoatsLeft() == 0);
	}
	
	// select and move to invalid spot (test tigers stay the same)
	@Test
	public void testSelectToInvalidSpot() {
		GameTable table = new GameTable(l1, l2, l3, l4);
		Tile[][] map = table.getMap();
		TigerTile[] tigers = table.getTigers();
		
		table.addGoatTile(5 * GameTable.TILE_SIZE, 5 * GameTable.TILE_SIZE);
		tigers[0].move(0, 0);
		tigers[1].move(0, 4);
		tigers[2].move(4, 0);
		tigers[3].move(4, 4);
		
		for(int i = 0; i < 19; i++) {
			table.addGoatTile(3 * GameTable.TILE_SIZE, 1 * GameTable.TILE_SIZE);
		}
		
		assertTrue(table.getNumGoatsLeft() == 0);
		table.select(5 * GameTable.TILE_SIZE, 5 * GameTable.TILE_SIZE);
		
		table.moveSelected(1 * GameTable.TILE_SIZE, 5 * GameTable.TILE_SIZE);
		assertTrue(table.getSelected());
		assertTrue(((GoatTile) map[2][2]).getSelected());
		assertTrue(map[2][2].isGoat());
		assertTrue(table.getNumGoatsLeft() == 0);
		
		assertTrue(map[2][0].isTiger());
		assertTrue(map[0][4].isTiger());
		assertTrue(map[4][0].isTiger());
		assertTrue(map[4][4].isTiger());
	}
	
	// select multiple (including same one twice) then move
	
	@Test
	public void testSelectMultBeforeMoving() {
		GameTable table = new GameTable(l1, l2, l3, l4);
		Tile[][] map = table.getMap();
		TigerTile[] tigers = table.getTigers();
		
		table.addGoatTile(5 * GameTable.TILE_SIZE, 5 * GameTable.TILE_SIZE);
		
		tigers[0].move(0, 0);
		tigers[1].move(0, 4);
		tigers[2].move(4, 0);
		tigers[3].move(4, 4);
		
		for(int i = 0; i < 18; i++) {
			table.addGoatTile(3 * GameTable.TILE_SIZE, 1 * GameTable.TILE_SIZE);
			table.setNumGoatsEaten(0);
		}
		
		table.addGoatTile(7 * GameTable.TILE_SIZE, 5 * GameTable.TILE_SIZE);
		tigers[0].move(0, 0);
		tigers[1].move(0, 4);
		tigers[2].move(4, 0);
		tigers[3].move(4, 4);
		
		assertTrue(table.getNumGoatsLeft() == 0);
		table.select(5 * GameTable.TILE_SIZE, 5 * GameTable.TILE_SIZE);
		assertTrue(((GoatTile) map[2][2]).getSelected());
		assertTrue(table.getSelected());
		
		table.moveSelected(7 * GameTable.TILE_SIZE, 5 * GameTable.TILE_SIZE);
		assertTrue(((GoatTile) map[3][2]).getSelected());
		assertFalse(((GoatTile) map[2][2]).getSelected());
		assertTrue(table.getSelected());
		
		table.moveSelected(7 * GameTable.TILE_SIZE, 3 * GameTable.TILE_SIZE);
		assertFalse(table.getSelected());
		assertFalse(((GoatTile) map[2][2]).getSelected());
		assertTrue(map[2][2].isGoat());
		assertTrue(map[3][1].isGoat());
		assertTrue(table.getNumGoatsLeft() == 0);
	}
	
	// select non-goat does nothing
	@Test
	public void testSelectNonGoat() {
		GameTable table = new GameTable(l1, l2, l3, l4);
		Tile[][] map = table.getMap();
		TigerTile[] tigers = table.getTigers();
		
		table.addGoatTile(5 * GameTable.TILE_SIZE, 5 * GameTable.TILE_SIZE);
		tigers[0].move(0, 0);
		tigers[1].move(0, 4);
		tigers[2].move(4, 0);
		tigers[3].move(4, 4);
		
		for(int i = 0; i < 19; i++) {
			table.addGoatTile(3 * GameTable.TILE_SIZE, 1 * GameTable.TILE_SIZE);
			table.setNumGoatsEaten(0);
		}
		
		assertTrue(table.getNumGoatsLeft() == 0);
		table.select(1 * GameTable.TILE_SIZE, 5 * GameTable.TILE_SIZE);
		
		assertFalse(table.getSelected());
		assertFalse(((GoatTile) map[2][2]).getSelected());
		assertTrue(map[2][2].isGoat());
		assertTrue(table.getNumGoatsLeft() == 0);
		
		assertEquals(table.numTigers(), 4);
	}
	
	// TERMINATE TESTS
	// Tigers win
	public void testTerminateTigerWin() {
		GameTable table = new GameTable(l1, l2, l3, l4);
		
		for (int i = 0; i < 5; i++) {
			table.addGoatTile(3 * GameTable.TILE_SIZE, 1 * GameTable.TILE_SIZE);
		}
		
		assertFalse(table.getPlaying());
	}
	
	// goats win
	public void testTerminateGoatWin() {
		GameTable table = new GameTable(l1, l2, l3, l4);
		
		new GoatTile(0, 2, table);
		new GoatTile(2, 0, table);
		new GoatTile(4, 2, table);
		new GoatTile(2, 4, table);
		new GoatTile(2, 2, table);
		
		new GoatTile(0, 3, table);
		
		new GoatTile(1, 0, table);
		new GoatTile(1, 1, table);
		new GoatTile(1, 3, table);
		new GoatTile(1, 4, table);
		
		new GoatTile(4, 1, table);
		new GoatTile(4, 3, table);
		
		new GoatTile(3, 0, table);
		new GoatTile(3, 1, table);
		new GoatTile(3, 3, table);
		new GoatTile(3, 4, table);
		
		
		table.addGoatTile(1 * GameTable.TILE_SIZE, 3 * GameTable.TILE_SIZE);
		assertFalse(table.getPlaying());

	}
}
