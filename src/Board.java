

public class Board {

	private Tile[][] map;
	private int numGoatsLeft;
	private int numGoatsEaten;
	private boolean selected;
	private GoatTile selTile;
	private TigerTile[] tigers;
	private boolean playing;
	
	/**
	 * The board constructor
	 * 
	 * @param map Tile[][] map of tile values
	 * @param l integer of the number of goats left
	 * @param e integer of number of goats eaten
	 * @param s boolean of if board is in selected
	 * @param g GoatTile of the selected tile. Null if no selected tile
	 * @param t TigerTile[] of the tiger tiles (including their placements on the board)
	 * @param p boolean of whether the board is in play
	 * @return none
	 */
	public Board (Tile[][] map, int l, int e, boolean s, GoatTile g, TigerTile[] t, boolean p) {
		this.numGoatsLeft = l;
		this.numGoatsEaten = e;
		this.selected = s;
		this.selTile = g;
		this.playing = p;
		this.map = new Tile[5][5];
		this.tigers = new TigerTile[4];
		
		for (int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				this.map[i][j] = map[i][j];
			}
		}
		
		for (int i = 0; i < 4; i++) {
			tigers[i] = t[i];
		}
	}
	
	/**
	 * Gets map
	 * 
	 * @param none
	 * @return Tile[][] map of tiles
	 */
	public Tile[][] getMap() {
		Tile[][] m = new Tile[5][5];
		for (int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				m[i][j] = map[i][j];
			}
		}
		return m;
	}
	
	/**
	 * Gets tigers
	 * 
	 * @param none
	 * @return TigerTile[] of tiger tiles 
	 */
	public TigerTile[] getTigers() {
		TigerTile[] t = new TigerTile[4];
		for (int i = 0; i < 4; i++) {
			t[i] = tigers[i];
		}
		return t;
	}
	
	/**
	 * Gets tiger tile
	 * 
	 * @param i integer value of array placement
	 * @return TigerTile in that part of the array
	 */
	public TigerTile getTigerTile(int i) {
		return tigers[i];
	}
	
	/**
	 * Get tile (for testing purposes)
	 * 
	 * @param i integer value of column
	 * @param j integer value of row
	 * @return tile in this placement
	 */
	public Tile getTile(int i, int j) {
		return map[i][j];
	}
	
	/**
	 * Gets numGoatsLeft
	 * 
	 * @param none
	 * @return integer number of goats left
	 */
	public int getNumGoatsLeft() {
		int n = numGoatsLeft;
		return n;
	}
	
	/**
	 * Gets numGoatsEaten
	 * 
	 * @param none
	 * @return integer number of goats eaten
	 */
	public int getNumGoatsEaten() {
		int n = numGoatsEaten;
		return n;
	}
	
	/**
	 * Gets selected
	 * 
	 * @param none
	 * @return boolean value if the board is selected
	 */
	public boolean getSelected() {
		boolean b = selected;
		return b;
	}
	
	
	/**
	 * Gets selTile
	 * 
	 * @param none
	 * @return GoatTile that is selected
	 */
	public GoatTile getSelTile() {
		return selTile;
	}
	
	/**
	 * Gets playing
	 * 
	 * @param none
	 * @return boolean value if the board is in play
	 */
	public boolean getPlaying() {
		return playing;
	}
}
