

import java.awt.Color;

@SuppressWarnings("serial")
public class EmptyTile extends Tile{

	/**
	 * Empty Tile constructor 
	 * @param x integer column value on map
	 * @param y integer row value on map
	 * @param g gametable the tile is in
	 */
	public EmptyTile(int x, int y,  GameTable g) {
		super(x, y, g);
		setColor(Color.BLACK);
	}
	
	/**
     * Determines if an empty tile
     * 
     * @param none
     * @return true because is an empty tile
     */
	@Override
    public boolean isEmpty() {
    	return true;
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
     * Determines if a goat tile
     * 
     * @param none
     * @return false because is not an empty tile
     */
	@Override
    public boolean isGoat() {
    	return false;
    }
	
	/**
     * Determines if a move is valid
     * 
     * @param x integer column to move to
     * @param y integer row to move to
     * @return false because it is impossible to move an empty tile
     */
	@Override
    public boolean isValidMove(int x, int y) {
		return false;
	}

}
