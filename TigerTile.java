

import java.awt.Color;

@SuppressWarnings("serial")
public class TigerTile extends Tile {

	/**
	 * Tiger Tile constructor 
	 * @param x integer column value on map
	 * @param y integer row value on map
	 * @param g gametable the tile is in
	 */
	public TigerTile(int x, int y,  GameTable g) {
		super(x, y, g);
		setColor(Color.ORANGE);
	}
	
	/**
     * Determines if an empty tile
     */
	@Override
    public boolean isEmpty() {
    	return false;
    }
    
	@Override
    /**
     * Determines if a tiger tile
     */
    public boolean isTiger() {
    	return true;
    }
    
	@Override
    /**
     * Determines if a goat tile
     */
    public boolean isGoat() {
    	return false;
    }
	
	/**
	 * Determines if jump is valid
	 */
	public boolean isJump(int x, int y) {
		if (x < 0 || x > 4 || y < 0 || y > 4) {
    		return false;
    	}
		
		int sx = super.getX();
		int sy = super.getY();
		int dx = sx+x;
		int dy = sy+y;
		GameTable g = super.getGameTable();
		
		int cx = Math.abs(sx - x);
		int cy = Math.abs(sy - y);
		
		
		if ((cx != 0 && cx != 2) || (cy != 0 && cy != 2)) {
			return false;
		} else if (!g.getTile(x,y).isEmpty()) { 
			return false;
		} else if (g.getTile(dx/2, dy/2).isGoat()) {
			return true; 
		} else {
			return false;
		}
	} 
	
	/**
	 * Jumps
	 */
	public void jump(int x, int y) {
		if (!isJump(x,y)) {
			return;
		} else {
			int sx = super.getX();
			int sy = super.getY();
			int dx = sx + x;
			int dy = sy + y;
			
			Tile e = new EmptyTile(this.getX(), this.getY(), this.getGameTable());
			
			new EmptyTile(dx/2,dy/ 2, this.getGameTable());
			changeTiles(x,y,e);
		}
	}
	
	/**
     * Determines if a move is valid
     */
	@Override
    public boolean isValidMove(int x, int y) {
		if (!super.isValidMove(x, y)) {
    		return false;
    	} else if (!super.getGameTable().getTile(x,y).isEmpty()) {
    		return false;
    	}else if (Math.abs(x - super.getX()) == 2 || Math.abs(y - super.getY()) == 2) {
			return this.isJump(x, y);
		} else if (Math.abs(x - super.getX()) > 1 || Math.abs(y - super.getY()) > 1) {
    		return false;
    	} else {
    		return true;
    	}
    }
	
	/**
	 * Overrides the Move method
	 */
	@Override
	public void move(int x, int y) {
		if (!isJump(x,y)) {
			super.move(x, y);
		} else {
			this.getGameTable().setTile(this.getX(), this.getY(), this);
			jump(x,y);
		}
	}

}
