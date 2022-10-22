import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.Point;

/**
*	Map.java - represents the dungeon maze. Map is a Singleton.
*/ 
public class Map{
  private char[][] map;
  private boolean[][] revealed;
  private static Map instance = null;

  /**
  * Map() - private constructor of map (singleton)
	*	Assigns map to a 5x5 set of characters representing the types of rooms in the maze
	* Assigns revealed a 5x5 set of booleans that allow you to determine if that room has been visited yet
  */
  private Map(){ 
		map = new char[5][5];
		revealed = new boolean[5][5];
  }
  /**
  * Map getInstance() - gets instance of certain map
  * @return instance of map
  */
  public static Map getInstance(){
    if(instance==null){
      instance = new Map();
    }
    return instance;
  }
  /**
  * loadMap(int mapNum) - loads certain map by number and stores it
	*	@param mapNum the map number
  */
  public void loadMap(int mapNum){
    if(mapNum>1){
      revealed = new boolean[5][5];
    }
		try {
      Scanner read = new Scanner(new File("Map"+String.valueOf(mapNum)+".txt"));
      for (int r = 0; r < map.length; r++) { //iterate through the map array (row)
        for (int c = 0; c < map[0].length; c++){ //iterate through the map array (column)
          map[r][c] = read.next().charAt(0); // read string (default) from read.next(), convert it to character, and insert into array at map[row][column]
        }
      }
      read.close();
    } catch (FileNotFoundException fnf) {
      System.out.println("File was not found");
    }
  }
  /**
  * getCharAtLoc(Point p) - gets character at location point
	* @param p the location point
  * @return map certain character at location
  */
  public char getCharAtLoc(Point p){
		return map[p.y][p.x];
  }
  /**
  * Point findStart() - checks where the start of the map is
  * @return new Point for the location of start
  */
  public Point findStart(){
		for(int row = 0; row < map.length; row++) {
			for (int col = 0; col < map[row].length; col++) {
				if(map[row][col] == 's') {
					return new Point(col, row);
				}
			}
		}
		return new Point();
  }
  /**
  * reveal(Point p) - reveals point p by setting boolean value of point p in revealed array to true
	*	@param p the location point
  */
  public void reveal(Point p){
		revealed[p.y][p.x] = true;
  }
  /**
  * removeCharAtLoc(Point p) - removes character at certain location
	*	@param p the location point
  * shows point removed with 'n'
  */
  public void removeCharAtLoc(Point p){
    map[p.y][p.x] = 'n';
  }
/** 
* mapToString returns a string of the map with
* the Hero’s current position, revealed rooms, 
* and any unrevealed rooms represented by ‘x’s
*	@param p the location point
* @return entireMap with current info
*/
  public String mapToString(Point p){
    String entireMap = "";
    //iterate through map
		for (int y = 0; y < map.length; y++) {
			for (int x = 0; x < map[y].length; x++) {
				if(y==p.y && x==p.x){
					entireMap+="*"; //if location in map equals hero's current location, add "*" to string
        }else if(revealed[y][x] == false){
          entireMap+="x"; //if not revealed, add "x" to string
				}else{
          entireMap+=Character.toString(map[y][x]); //if is revealed and not hero's current location, add character value of map to string
        }
				entireMap+=" "; //add space after each insert
			}
			entireMap+="\n"; //new line after every row
		}
  return entireMap; //return completed string of map
  }
}