

import java.awt.Color;
import java.awt.Graphics;

@SuppressWarnings("serial")
public class GoatTile extends Tile {
	
	private boolean selected;

	/**
	 * Goat Tile constructor 
	 * @param x integer column value on map
	 * @param y integer row value on map
	 * @param g gametable the tile is in
	 */
	public GoatTile(int x, int y,  GameTable g) {
		super(x, y, g);
		setColor(new Color(175, 125, 43));
		selected = false;
	}
	
	/**
     * Determines if an empty tile
     * 
     * @param none
     * @return false because is not an empty tile
     */
	@Override
    public boolean isEmpty() {
    	return false;
    }
    
	/**
     * Determines if a tiger tile
     * 
     * @param none
     * @return false because is not a tiger tile
     */
	@Override
    public boolean isTiger() {
    	return false;
    }
    
	/**
     * Determines if an goat tile
     * 
     * @param none
     * @return true because is a goat tile
     */
	@Override
    public boolean isGoat() {
    	return true;
    }
	
	/**
     * Changes the value of selected
     * 
     * @param none
     * @return void
     */
    public void flipSelected() {
    	selected = !selected;
    	repaint();
    }
    
    /**
     * Gets if the tile is selected
     * 
     * @
     */
    public boolean getSelected() {
    	boolean b = selected;
    	return b;
    }
	
	/**
     * Determines if a move is valid
     */
	@Override
    public boolean isValidMove(int x, int y) {
    	if (!super.isValidMove(x, y)) {
    		return false;
    	}
    	
    	if (!super.getGameTable().getTile(x,y).isEmpty()) {
    		return false;
    	}
    	
    	if (Math.abs(x - super.getX()) > 1 || Math.abs(y - super.getY()) > 1) {
    		return false;
    	} 
    	
    	return true;
    }
	
	@Override
	public void draw(Graphics g, int x, int y) {
    	if (selected) {
    		g.setColor(Color.YELLOW);
    		g.drawRect(x, y, GameTable.TILE_SIZE, GameTable.TILE_SIZE);
    		repaint();
    	}
    	super.draw(g, x, y);
    }

}
