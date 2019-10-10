

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameTable extends JPanel  {
	
	public static final int TILE_SIZE = 60;

    private int tableSize;
    
    private Color color;

    @SuppressWarnings("unused")
	private JButton undo;
    private JLabel numGoatsLeftLabel;
    private JLabel numGoatsEatenLabel;
    private JLabel gameStatus;
    
    private int numGoatsLeft;
    private int numGoatsEaten;
    
    private boolean selected;
    private GoatTile selTile;
    
    private boolean playing;

    private Tile[][] map;
    private TigerTile[] tigers; 
    private List<Board> history;
    
    
    
	/**
	 * Constructs a GameTable
	 * 
	 * @param goatsLeft JLabel declaring number of goats left
	 * @param goatsEaten JLabel declaring number of goats eaten
	 * @param gameStatus JLabel declaring the game status
	 * @param undo JButton that reverts board back to before previous move
	 */
	public GameTable(JLabel goatsLeft, JLabel goatsEaten, JLabel gameStatus, JButton undo) {
		super();
		this.tableSize = 11 * TILE_SIZE;
		this.setSize(tableSize, tableSize);
		
		this.color = new Color(39,142,79); 
		
		this.undo = undo;
		this.numGoatsLeftLabel = goatsLeft;
		this.numGoatsEatenLabel = goatsEaten;
		this.gameStatus = gameStatus;
	
		this.numGoatsLeft = 20;
		this.numGoatsEaten = 0;
		
		this.selected = false;
		this.selTile = null;
		this.playing = true;
		
		this.map = new Tile[5][5];
		
		for(int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				Tile t = new EmptyTile(i, j, this);
				map[i][j] = t;
			}
		}
		
		tigers = new TigerTile[4];
		tigers[0] = new TigerTile(0, 0, this);
		tigers[1] = new TigerTile(0, 4, this);
		tigers[2] = new TigerTile(4, 0, this);
		tigers[3] = new TigerTile(4, 4, this);
		
		this.history = new LinkedList<>();
		
		// add mouse listener
		this.addMouseListener(new MouseAdapter() {
        	@Override
			public void mouseClicked(MouseEvent e) {
        		if (playing) {
        			int x = e.getX();
            		int y = e.getY();
            		if (numGoatsLeft != 0) {
            			addGoatTile(x,y);
            		} else if (!selected) {
            			select(x,y);
            		} else {
            			moveSelected(x,y);
            		}
            		repaint();
        		}
        	}
        });
		
		// add undo
		undo.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!history.isEmpty()) {
					undo();
				}
			}
		});
		
		repaint();
	
	}
	
	/**
	 * Resets all variables to the way they were before
	 * 
	 * @param none
	 * @return void
	 */
	public void undo() {
		Board g = history.remove(0);
		this.map = g.getMap();
		this.playing = g.getPlaying();
		
		this.numGoatsLeft = g.getNumGoatsLeft();
		this.numGoatsEaten = g.getNumGoatsEaten();
		
		if (selTile != null) {
			selTile.flipSelected();
		}
		
		this.selected = g.getSelected();
		this.selTile = g.getSelTile();
		
		if (selTile != null) {
			selTile.flipSelected();
		}
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				Tile t = g.getTile(i,j);
				map[i][j] = t;
				t.setX(i);
				t.setY(j);
			}
		}
		
		for(int i = 0; i < 4; i++) {
			tigers[i] = g.getTigerTile(i);
		}
		
		numGoatsLeftLabel.setText("Number of Goats Left = " + numGoatsLeft);
		numGoatsEatenLabel.setText("Goats Eaten = " + numGoatsEaten);
		
		repaint();
		requestFocusInWindow();
	}
	
	/**
	 * Creates an instance of the board class mimicking current GameTable state
	 * 
	 * @param none
	 * @return Board object mimicking current GameTable state
	 */
	public Board createBoard() {
		Board b = new Board(map, numGoatsLeft, numGoatsEaten, selected, selTile, tigers, playing);
		return b;
	}
	
	/**
	 * Gets the selected Tile
	 * 
	 * @param none
	 * @return GoatTile that is currently selected. Null if none currently selected
	 */
	public GoatTile getSelTile() {
		GoatTile g = selTile;
		return g;
	}
	
	/**
	 * Gets number of goats left
	 * 
	 * @param none
	 * @return integer number of goats left
	 */
	public int getNumGoatsLeft() {
		int n = numGoatsLeft;
		return n;
	}
	
	/**
	 * Gets value of if there is a selected tile on the board
	 * 
	 * @param none
	 * @return true if there is a selected tile, false otherwise
	 */
	public boolean getSelected() { //TODO should I unencapsulate all these?
		boolean s = selected;
		return s;
	}
	
	/**
	 * Gets map of tiles
	 * 
	 * @param none
	 * @return a Tile[][] map of the tiles on the board
	 */
	public Tile[][] getMap() { //TODO should copy to encapsuate but would have to copy tile
		Tile[][] m = map;
		return m;
	}
	
	/**
	 * Gets array of tiger tiles
	 * 
	 * @param none
	 * @return TigerTile[] of the tiger tiles on the board
	 */
	public TigerTile[] getTigers() { //TODO should I encapsulate this
		TigerTile[] t = tigers;
		return t;
	}
	
	/**
	 * Gets tile
	 * 
	 * @param i integer of column 
	 * @param j integer of row
	 * @return Tile at this column/row placement
	 */
	public Tile getTile(int i, int j) { //TODO should I encapsulate this?
		Tile t = map[i][j];
		return t;
	}
	
	/**
	 * Sets tile
	 * 
	 * @param i integer of column 
	 * @param j integer of row
	 * @param Tile to place at this column/row placement
	 */
	public void setTile(int i, int j, Tile t) {
		map[i][j] = t;
	}
	
	/**
	 * Gets x map position
	 * 
	 * @param x pixel value on panel
	 * @return integer column number
	 */
	public int getXMapPos(int x) {
		int i = (int) Math.ceil(x / TILE_SIZE);
		return i;
	}
	
	/**
	 * Gets y map position
	 * 
	 * @param y pixel value on panel
	 * @return integer row number
	 */
	public int getYMapPos(int y) {
		int j = (int) Math.ceil(y / TILE_SIZE);
		return j;
	}
	
	/**
	 * Gets playing value
	 * 
	 * @param none
	 * @return true if gametable is in play, false otherwise
	 */
	public boolean getPlaying() {
		return playing;
	}
	
	/**
     * Set numGoatsEaten
     * 
     * @param i integer number to set NumGoatsEaten to
     * @return void
     */
	public void setNumGoatsEaten(int i) {
		numGoatsEaten = i;
		
	}
	
	/**
	 * Moves tiger
	 */
	public void moveTiger() {
		TigerTile[] tile = new TigerTile[32];
		int[] possibleX = new int[32];
		int[] possibleY = new int[32];
		int count = 0;
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				for (int t = 0; t < 4; t++) {
					if (tigers[t].isValidMove(i, j)) {
						if(tigers[t].isJump(i, j)) {
							tigers[t].move(i, j);
							repaint();
							numGoatsEaten++;
							numGoatsEatenLabel.setText("Goats Eaten = " + numGoatsEaten);
							if (numGoatsEaten == 5) {
								gameStatus.setText("GAME OVER -- Tigers Win :(");
								playing = false;
							}
							return;
						} else {
							possibleX[count] = i;
							possibleY[count] = j;
							tile[count] = tigers[t];
							count++;
						}
					}
				}
			}
		}
		
		if (count == 0) {
			gameStatus.setText("GAME OVER -- Goats Win :)");
			playing = false;
		} else {
			int rand = (int) (Math.random() * (count)); 
			tile[rand].move(possibleX[rand], possibleY[rand]);
			repaint();
			requestFocusInWindow();
		}
	}
	
	/**
	 * Gets the number of Tigers in the map TODO take out
	 */
	public int numTigers() {
		int n = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (map[i][j].isTiger()) {
					n++;
				}
			}
		}
		return n;
	}
	
	/** 
	 * Adds a goat tile to the specified position
	 */
	public void addGoatTile(int x, int y) {
		if (numGoatsLeft == 0 ) {
			return;
		}
		int i = getXMapPos(x);
		int j = getYMapPos(y);
		
		if (i % 2 == 0 || j % 2 == 0) {
			return;
		} else {
			i = (i - 1) / 2;
			j = (j - 1) / 2;
			if (map[i][j].isEmpty()) {
				Board b = createBoard();
				history.add(0, b);
				map[i][j] = new GoatTile(i, j, this);
				numGoatsLeft--;
				numGoatsLeftLabel.setText("Number of Goats Left = " + numGoatsLeft);
				
				moveTiger();
				
				repaint();
				requestFocusInWindow();
			}
		}
	}
	
	/**
	 * Select a goat tile
	 */
	public void select(int x, int y) {
		if (numGoatsLeft != 0 ) {
			return;
		}
		int i = getXMapPos(x);
		int j = getYMapPos(y);
		
		if (i % 2 == 0 || j % 2 == 0) {
			return;
		} else {
			i = (i - 1) / 2;
			j = (j - 1) / 2;
			if (map[i][j].isGoat()) {
				selTile = (GoatTile) map[i][j];
				selTile.flipSelected();
				selTile.repaint();
				
				if (!selected) {
					selected = true;
				}
				
				repaint();
				requestFocusInWindow();
			}
		}
	}
	
	/**
	 * Select a goat tile
	 */
	public void moveSelected(int x, int y) {
		if (selTile == null) {
			return;
		}
		int i = getXMapPos(x);
		int j = getYMapPos(y);
		
		if (i % 2 == 0 || j % 2 == 0) {
			return;
		} else {
			i = (i - 1) / 2;
			j = (j - 1) / 2;
			
			if (map[i][j].isGoat()) {
				selTile.flipSelected();
				select(x, y);
				repaint();
			} else {
				
				if (selTile.isValidMove(i, j)) {
					Board b = createBoard();
					history.add(0, b);
					selTile.move(i, j);
					selTile.flipSelected();
					selTile = null;
					selected = !selected;
					
					moveTiger();
						
					repaint();
					requestFocusInWindow();
				}
			}
		}
	}
	
	/**
	 * Function that draws the table graphics
	 */
	public void drawTable(Graphics g) { //TODO should these be public or private? 
		g.setColor(color);
		g.fillRect(0, 0, tableSize, tableSize);
		
		int t = TILE_SIZE;
		int t0 = (int) (t * 1.5);
		int t1 = (int) (t * 3.5);
		int t2 = (int) (t * 5.5);
		int t3 = (int) (t * 7.5);
		int t4 = (int) (t * 9.5);
				
		g.setColor(Color.WHITE);
		g.drawLine(t0, t0, t4, t4);
		g.drawLine(t0, t4, t4, t0);
		
		g.drawLine(t0, t0, t0, t4);
		g.drawLine(t0, t0, t4, t0);
		g.drawLine(t4, t4, t4, t0);
		g.drawLine(t4, t4, t0, t4);
		
		
		g.drawLine(t0, t1, t4, t1);
		g.drawLine(t0, t2, t4, t2);
		g.drawLine(t0, t3, t4, t3);
		g.drawLine(t1, t0, t1,  t4);
		g.drawLine(t2, t0, t2,  t4);
		g.drawLine(t3, t0, t3,  t4);
		
		g.drawLine(t2, t0, t0, t2);
		g.drawLine(t2, t0, t4, t2);
		g.drawLine(t4, t2, t2, t4);
		g.drawLine(t2, t4, t0, t2);
	}
	
	/**
	 * Function that draws the tiles graphics
	 */
	public void drawTiles(Graphics g) {
		int x = TILE_SIZE ;
		int y = TILE_SIZE ;
		for(int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				map[i][j].draw(g, x, y);
				y += TILE_SIZE * 2;
			}
			x += TILE_SIZE * 2;
			y = TILE_SIZE;
		}
	}
	
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTable(g);
        drawTiles(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(tableSize, tableSize);
    }

}
