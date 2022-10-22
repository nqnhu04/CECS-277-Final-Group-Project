import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
*	EnemyGenerator.java – factory to create random enemies to encounter on the map.
*/ 
public class EnemyGenerator {
  private HashMap<String, Integer> enemies = new HashMap<String, Integer>();

  /**
  * EnemyGenerator() - enemy generator constructor
  * reads enemies file and add enemies and hp to hashmap
  */
  public EnemyGenerator(){
    try{
      Scanner read = new Scanner(new File("Enemies.txt"));
      while(read.hasNext()){
        String line = read.nextLine(); //read the entire line
        String [] tokens = line.split(","); //split the line up into two sections
        enemies.put(tokens[0],Integer.valueOf(tokens[1])); //the first section is a string while the second section is an int
        }
      read.close();
    } catch(FileNotFoundException fnf){
      System.out.println("File was not found");
      }
  }

  /**
  * Enemy generateEnemy(int level) - randomly selects enemy from map
  * randomly selects ability type and cunstructs new enemy
  *	@param level value to modify base hp
	*	@return e contructs a new enemy of an ability type with enemy's name and hp
  */
  public Enemy generateEnemy(int level){
    //create a string arraylist to hold string keys of enemies
    ArrayList<String> enemy = new ArrayList<String>();
    //add string keys into arraylist
    for(HashMap.Entry<String,Integer> entry: enemies.entrySet()){
      enemy.add(entry.getKey());
    }
    //randomly generate index to get random enemy(key)
    int randE = (int)(Math.random()*enemy.size());
    String enemyName = enemy.get(randE); //get random enemy from arraylist
    Enemy e;
    
    //random ability type
    int randA = (int)(Math.random()*3)+1;
    //construct enemy of that type with name and hp
    if (randA==1){
      e = new Warrior(enemyName, (enemies.get(enemyName)*level));
    } else if(randA==2){
      e = new Wizard(enemyName, (enemies.get(enemyName)*level));
    }else{
      e = new Ranger(enemyName, (enemies.get(enemyName)*level));
    }
		return e;
  }
}