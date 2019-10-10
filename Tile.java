
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public abstract class Tile extends JComponent {
	
    private Color background;
    private int x;
    private int y;
    private GameTable g;

    /**
	 * Tile constructor 
	 * @param x integer column value on map
	 * @param y integer row value on map
	 * @param g gametable the tile is in
	 */
    public Tile(int x, int y, GameTable g) {
        this.background = new Color(39,142,79);
        this.x = x;
        this.y = y;
        this.g = g;
        
        g.setTile(x, y, this);
    }
    
    /**
     * Sets the background color
     */
    public void setColor(Color c) {
    	background = c;
    }
    
    /**
     * Gets the x position
     */
    public int getX() {
    	int posx = x;
    	return posx;
    }
    
    /**
     * Sets the x position
     */
    public void setX(int i) {
    	x = i;
    }
    
    /**
     * Gets the y position
     */
    public int getY() {
    	int posy = y;
    	return posy;
    }
    
    /**
     * Sets the y position
     */
    public void setY(int i) {
    	y = i;
    }
    
    /**
     * Gets the Game Table
     */
    public GameTable getGameTable() {
    	GameTable gt = g;
    	return gt;
    }
    
    /**
     * Determines if an empty tile
     */
    public boolean isEmpty() {
    	return false;
    }
    
    /**
     * Determines if a tiger tile
     */
    public boolean isTiger() {
    	return false;
    }
    
    /**
     * Determines if a goat tile
     */
    public boolean isGoat() {
    	return false;
    }
    
    
    /**
     * Determines if a move is valid
     */
    public boolean isValidMove(int x, int y) {
    	int ex = this.x % 2;
    	int ey = this.y % 2;
    	
    	if (x < 0 || x > 4 || y < 0 || y > 4) {
    		return false;
    	} else if((ex == 1) && (ey == 0) && (x != this.x) && (y != this.y) ) {
    		return false;
    	} else if ((ex == 0) && (ey == 1) && (x != this.x) && (y != this.y) ) {
    		return false;
    	} else if ((x == 0 && this.x == 4) || (x == 4 && this.x == 0) || 
    			(y == 0 && this.y == 4) || (y == 4 && this.y == 0)) {
    		return false;
    	} else {
    		return true;
    	}
    }

    /**
     * Draw the Tile TODO make sure there are params on everything even though style not graded?
     * @param g
     * @param x
     * @param y
     */
    public void draw(Graphics g, int x, int y) {
    	g.setColor(background);
        g.fillRect(x, y, GameTable.TILE_SIZE, GameTable.TILE_SIZE);
    }
    
    /**
     * Change the tiles
     */
    
    public void changeTiles(int x, int y, Tile t) {
    	g.setTile(x, y, this);
    	
    	this.x = x;
    	this.y = y;
    	 
    	
    }
    
    /**
	 * Moves the tile
	 */
    
    public void move(int x, int y) {
    	// determine if move is valid
    	if (!this.isValidMove(x, y)) {
    		return;
    	}
    	
    	Tile e = new EmptyTile(this.x, this.y, g);
    	changeTiles(x, y, e);
    	
    }
	
}
