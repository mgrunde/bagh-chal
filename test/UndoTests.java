

import static org.junit.Assert.*;

//import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;

import org.junit.Test;

public class UndoTests {
	// create a color variable
		JLabel l1 = new JLabel("");
		JLabel l2 = new JLabel("");
		JLabel l3 = new JLabel("");
		JButton l4 = new JButton("");
		
	// Test undo non-jump first step
	@Test
	public void undoNonJumpTest() {
		GameTable table = new GameTable(l1, l2, l3, l4);
		TigerTile[] tigers = table.getTigers();
		table.addGoatTile(5 * GameTable.TILE_SIZE, 5 * GameTable.TILE_SIZE);
		table.undo();
		Tile[][] map = table.getMap();
		
		// make sure goat not there
		assertTrue(map[2][2].isEmpty());
		
		// make sure tigers still same 
		assertEquals(table.getTile(0, 0), tigers[0]);
		assertEquals(table.getTile(0, 4), tigers[1]);
		assertEquals(table.getTile(4, 0), tigers[2]);
		assertEquals(table.getTile(4, 4), tigers[3]);
		
		// make sure tigers still have same x and y
		assertEquals(tigers[0].getX(), 0);
		assertEquals(tigers[0].getY(), 0);
		
		assertEquals(tigers[1].getX(), 0);
		assertEquals(tigers[1].getY(), 4);
		
		assertEquals(tigers[2].getX(), 4);
		assertEquals(tigers[2].getY(), 0);
		
		assertEquals(tigers[3].getX(), 4);
		assertEquals(tigers[3].getY(), 4);
		
		//add a goat to 3, 3
		table.addGoatTile(7 * GameTable.TILE_SIZE, 7 * GameTable.TILE_SIZE);
		map = table.getMap();
		
		// see that one tile jumped
		assertEquals(table.getTile(0, 0), tigers[0]);
		assertEquals(table.getTile(0, 4), tigers[1]);
		assertEquals(table.getTile(4, 0), tigers[2]);
		assertEquals(table.getTile(2, 2), tigers[3]);
		
		assertEquals(table.numTigers(), 4);
	}
	
	// REDO AFTER ALL OF THESE
	// Undo jump test
	public void undoJumpTest() {
		GameTable table = new GameTable(l1, l2, l3, l4);
		TigerTile[] tigers = table.getTigers();
		table.addGoatTile(7 * GameTable.TILE_SIZE, 3 * GameTable.TILE_SIZE);
		table.undo();
		
		// make sure tigers still same 
		assertEquals(table.getTile(0, 0), tigers[0]);
		assertEquals(table.getTile(0, 4), tigers[1]);
		assertEquals(table.getTile(4, 0), tigers[2]);
		assertEquals(table.getTile(4, 4), tigers[3]);
		
		// make sure tigers still have same x and y
		assertEquals(tigers[0].getX(), 0);
		assertEquals(tigers[0].getY(), 0);
		
		assertEquals(tigers[1].getX(), 0);
		assertEquals(tigers[1].getY(), 4);
		
		assertEquals(tigers[2].getX(), 4);
		assertEquals(tigers[2].getY(), 0);
		
		assertEquals(tigers[3].getX(), 4);
		assertEquals(tigers[3].getY(), 4);
		
		//add a goat to 4, 1
		table.addGoatTile(9 * GameTable.TILE_SIZE, 3 * GameTable.TILE_SIZE);
		
		// see that one tile jumped
		assertEquals(table.getTile(0, 0), tigers[0]);
		assertEquals(table.getTile(0, 4), tigers[1]);
		assertEquals(table.getTile(4, 0), tigers[2]);
		assertEquals(table.getTile(2, 2), tigers[3]);
		
		assertEquals(table.numTigers(), 4);
	}
	
	
	// undo multiple moves test
	public void undoMultTest() {
		GameTable table = new GameTable(l1, l2, l3, l4);
		TigerTile[] tigers = table.getTigers();
		table.addGoatTile(7 * GameTable.TILE_SIZE, 3 * GameTable.TILE_SIZE);
		table.addGoatTile(7 * GameTable.TILE_SIZE, 5 * GameTable.TILE_SIZE);
		table.addGoatTile(7 * GameTable.TILE_SIZE, 7 * GameTable.TILE_SIZE);
		table.addGoatTile(3 * GameTable.TILE_SIZE, 9 * GameTable.TILE_SIZE);
		table.undo();
		table.undo();
		table.undo();
		table.undo();
		
		// make sure tigers still same 
		assertEquals(table.getTile(0, 0), tigers[0]);
		assertEquals(table.getTile(0, 4), tigers[1]);
		assertEquals(table.getTile(4, 0), tigers[2]);
		assertEquals(table.getTile(4, 4), tigers[3]);
		
		// make sure tigers still have same x and y
		assertEquals(tigers[0].getX(), 0);
		assertEquals(tigers[0].getY(), 0);
		
		assertEquals(tigers[1].getX(), 0);
		assertEquals(tigers[1].getY(), 4);
		
		assertEquals(tigers[2].getX(), 4);
		assertEquals(tigers[2].getY(), 0);
		
		assertEquals(tigers[3].getX(), 4);
		assertEquals(tigers[3].getY(), 4);
		
		//add a goat to 4, 1
		table.addGoatTile(9 * GameTable.TILE_SIZE, 3 * GameTable.TILE_SIZE);
		
		// see that one tile jumped
		assertEquals(table.getTile(0, 0), tigers[0]);
		assertEquals(table.getTile(0, 4), tigers[1]);
		assertEquals(table.getTile(4, 0), tigers[2]);
		assertEquals(table.getTile(2, 2), tigers[3]);
		
		assertEquals(table.numTigers(), 4);
	}
	
	
	// select tile then undo
	public void undoSelectTileFirst() {
		GameTable table = new GameTable(l1, l2, l3, null);
		
		table.addGoatTile(5 * GameTable.TILE_SIZE, 5 * GameTable.TILE_SIZE);
		
		for(int i = 0; i < 19; i++) {
			table.addGoatTile(3 * GameTable.TILE_SIZE, 1 * GameTable.TILE_SIZE);
		}
		
		assertTrue(table.getNumGoatsLeft() == 0);
		table.select(5 * GameTable.TILE_SIZE, 5 * GameTable.TILE_SIZE);
		
		table.undo();
		
		assertTrue(table.getNumGoatsLeft() == 1);
		GoatTile t = (GoatTile) table.getTile(2, 2);
		assertFalse(t.getSelected());
		assertFalse(table.getSelected());
		assertEquals(table.getSelTile(), null);
		
		table.select(5 * GameTable.TILE_SIZE, 5 * GameTable.TILE_SIZE);
		assertTrue(t.getSelected());
		assertTrue(table.getSelected());
		assertEquals(table.getSelTile(), t);
	}
	
	
	// select multiple then undo
	public void undoSelectMultTileFirst() {
		GameTable table = new GameTable(l1, l2, l3, l4);
		
		table.addGoatTile(5 * GameTable.TILE_SIZE, 5 * GameTable.TILE_SIZE);
		table.addGoatTile(7 * GameTable.TILE_SIZE, 5 * GameTable.TILE_SIZE);
		
		for(int i = 0; i < 18; i++) {
			table.addGoatTile(3 * GameTable.TILE_SIZE, 1 * GameTable.TILE_SIZE);
		}
		
		assertTrue(table.getNumGoatsLeft() == 0);
		table.select(5 * GameTable.TILE_SIZE, 5 * GameTable.TILE_SIZE);
		table.select(7 * GameTable.TILE_SIZE, 5 * GameTable.TILE_SIZE);
		
		table.undo();
		
		assertTrue(table.getNumGoatsLeft() == 1);
		GoatTile t = (GoatTile) table.getTile(2, 2);
		assertTrue(t.getSelected());
		assertTrue(table.getSelected());
		assertEquals(table.getSelTile(), t);
		
		GoatTile t2 = (GoatTile) table.getTile(3, 2);
		assertFalse(t2.getSelected());
		
		table.select(5 * GameTable.TILE_SIZE, 7 * GameTable.TILE_SIZE);
		assertFalse(t.getSelected());
		assertTrue(t2.getSelected());
		assertTrue(table.getSelected());
		assertEquals(table.getSelTile(), t2);
	}

}
