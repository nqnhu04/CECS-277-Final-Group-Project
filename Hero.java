import java.awt.Point;

/**
*	Hero.java - describes the character that represents the user.
* Hero is a representation of a hero, subclass of Entity
*/ 
public class Hero extends Entity implements Fighter, Magical, Archer{
  private Point loc;
  private int level, gold, keys, potions;

  /**
  * Hero(String n) - constructor of hero. Initializes Hero. 
	* The Hero is constructed with a name, begins at level 1 at the start position of the map
	*	and is given default values for hp, gold, potions, and keys
  * @param n the name of hero
  */
  public Hero(String n){
		super(n, 25);
		level = 1;
		gold = 25;
		keys = 0;
		potions = 1;
    Map map = Map.getInstance();
    map.loadMap(level);
		loc = map.findStart();
		map.reveal(loc);
  }
  /**
  * Point getLocation() - returns hero's location
	*	@return loc the hero's location
  */
  public Point getLocation(){
    return loc;
  }
  /**
  * toString() - displays info on hero
  * @return hero's name, current hp out of max hp, level, gold, potions, keys and location on map
  */
  @Override
  public String toString(){
		return super.toString() + "\nLevel: " + level + "\nGold: " + gold + "\nP: " + potions + " K: " + keys + "\n" + Map.getInstance().mapToString(loc);
  }

  /**
  * levelUp() - increments hero's level and loads next map
  */
  public void levelUp(){
    level++;
		Map.getInstance().loadMap(level);
  }

  /**
  * getLevel() - gets current level
  * @return level the current level
  */
  public int getLevel(){
		return level;
  }
  /**
  * goNorth() - updates location when hero goes north
  * @return char location ('x' if out of bounds) 
  */
  public char goNorth(){
    Map map = Map.getInstance();
    if(loc.y-1>=0){
		  loc.setLocation(loc.x, loc.y-1);
			map.reveal(loc);
      return map.getCharAtLoc(loc);
    }
    return 'x';
  }

  /**
  * goSouth() - updates location when hero goes south
  * @return char location ('x' if out of bounds)
  */
  public char goSouth(){
		Map map = Map.getInstance();
    if(loc.y+1<=4){
		  loc.setLocation(loc.x, loc.y+1);
			map.reveal(loc);
      return map.getCharAtLoc(loc);
    }
    return 'x';
  }

  /**
  * goEast() - updates location when hero goes east
  * @return char location ('x' if out of bounds)
  */
  public char goEast(){
		Map map = Map.getInstance();
    if(loc.x+1<=4){
		  loc.setLocation(loc.x+1, loc.y);
			map.reveal(loc);
      return map.getCharAtLoc(loc);
    }
    return 'x';
  }

  /**
  * goWest() - updates location when hero goes west
  * @return char location ('x' if out of bounds)
  */
  public char goWest(){
		Map map = Map.getInstance();
    if(loc.x-1>=0){
		  loc.setLocation(loc.x-1, loc.y);
			map.reveal(loc);
      return map.getCharAtLoc(loc);
    }
    return 'x';
  }

  /**
  * getAttackMenu() - displays attack menu for hero
  * @return menu from physical, magical, and ranged attack
  */
  public String getAttackMenu(){
		return "1. Physical Attack" + "\n2. Magical Attack" + "\n3. Ranged Attack";
  }
  /**
  * getNumAttackMenuItems() - returns number of attack menu items
  * @return 3
  */
  public int getNumAttackMenuItems(){
    return 3;
  }

  /**
  * getSubAttackMenu(int choice) - gets the menu of specific attack
	* @param choice the chosen attack
  * @return certain attack menu
  */
  public String getSubAttackMenu(int choice){
		if(choice == 1){
			return FIGHTER_MENU;
		}
		else if(choice == 2){
			return MAGIC_MENU;
		}
		else{
			return ARCHER_MENU;
		}
  }
  /**
  * getNumSubAttackMenuItems(int choice) - returns number of sub attack menu items
	*	@param choice the selected menu from the interface
  * @return 2
  */
  public int getNumSubAttackMenuItems(int choice){
    if(choice == 1){
			return NUM_FIGHTER_MENU_ITEMS;
		}
		else if(choice == 2){
			return NUM_MAGIC_MENU_ITEMS;
		}
		else{
			return NUM_ARCHER_MENU_ITEMS;
		}
  }
	/**
  * attack(Enemy e, int choice, int subChoice) - string description of attack hero chooses
	* @param e the enemy
	* @param choice the selected attack menu
	* @param subChoice the selected sub attack menu from the interface
  * @return attack description
  */
  public String attack(Enemy e, int choice, int subChoice){
     if(choice == 1){ //physical attack
			 if(subChoice == 1){
				 return sword(e);
			 }
			 else{
				 return axe(e);
			 }
		 }else if(choice == 2){ //magical attack
			if(subChoice == 1){
				return magicMissile(e);
			}
			else{
				return fireball(e);
			}
		}else{ //ranged attack
			if(subChoice == 1){
				return arrow(e);
			}
			else{
				return fireArrow(e);
			}
		}
  }

  /**
  * int getGold() - gets number of gold hero has
  * @return gold the amount of gold
  */
  public int getGold(){
    return gold;
  }

  /**
  * collectGold(int g) - adds gold to hero's gold
	* @param g the hero's gold
  */
  public void collectGold(int g){
    gold+= g;
  }

  /**
  * spendGold(int g) - updates gold if hero spends it
	*	@ g the hero's gold
  * @return true or false, depending whether the hero can spend that much gold
  */
  public boolean spendGold(int g){
    if(gold-g>=0){
      gold-=g;
      return true;
    }
    return false;
  }

  /**
  * hasKey() - checks if hero has keys
  * @return true if user has keys
  */
  public boolean hasKey(){
    if(keys>0){
      return true;
    }
    return false;
  }

  /**
  * pickUpKey() - updates number of keys 
  */
  public void pickUpKey(){
    keys++;
  }

  /**
  * useKey() - updates number of keys if hero uses one
  * @return true is hero can use key
  */
  public boolean useKey(){
    if(hasKey()){
      keys--;
      return true;
    }
    return false;
  }

  /**
  * hasPotion() - checks if hero has potions
  * @return true if hero has keys
  */
  public boolean hasPotion(){
    if (potions>0){
      return true;
    }
    return false;
  }

  /**
  * pickUpPotion() - updates num of potions 
  */
  public void pickUpPotion(){
    potions++;
  }

  /**
  * usePotion() - updates num of potions if hero uses one
  * @return true if hero can use a potion
  */
  public boolean usePotion(){
    if(hasPotion()){
      potions--;
      heal();
      return true;
    }
    return false;
  }

	/** 
	*	sword(Entity e) - override fighter interface method for sword 
	*	(but this time the enemy is the entity and hero is super)
	*	@param e the generic enemy
	*	@return hero's name, hero's sword attack description, enemy's name, and
	*	dmg the amount of damage on the enemy
	*/ 
  @Override
  public String sword(Entity e){
    int dmg = (int)(Math.random()*4)+1; //random dmg range of 1-4
    e.takeDamage(dmg);
    return super.getName()+" slashes "+e.getName()+" with a sword for "+dmg+" damage!"; //hero attacks entity
  }

	/**
	*	axe(Entity e) - override fighter interface method for axe
	*	(but this time the enemy is the entity and hero is super)
	*	@param e the generic enemy
	* @return hero's name, hero's axe attack description, enemy's name, and 
	*	dmg the amount of damage on the enemy
	*/ 
  @Override
  public String axe(Entity e){
    int dmg = (int)(Math.random()*5)+2; //random dmg range of 2-5
    e.takeDamage(dmg);
    return super.getName()+" hits "+e.getName()+" with an axe for "+dmg+" damage!"; 
  }

	/**
	* magicMissile(Entity e) - override magical interface method magicMissle 
	*	(but this time the enemy is the entity and hero is super)
	*	@param e the generic enemy
	*	@return hero's name, hero's magic missle attack description, enemy's name, and 
	*	dmg the amount of damage on the enemy
	*/ 
  @Override
  public String magicMissile(Entity e){
    int dmg = (int)(Math.random()*6)+3; //random dmg range of 3-6
    e.takeDamage(dmg);
    return super.getName()+" zaps "+e.getName()+" with an magic missle for "+dmg+" damage!"; //hero attacks entity
  }

	/**
	* fireball(Entity e) - override magical interface method fireball 
	*	(but this time the enemy is the entity and hero is super)
	*	@param e the generic enemy
	*	@return hero's name, hero's fireball attack description, enemy's name, and 
	*	dmg the amount of damage on the enemy
	*/
  @Override
  public String fireball(Entity e){
    int dmg = (int)(Math.random()*4)+2; //random dmg range of 2-4
    e.takeDamage(dmg);
    return super.getName()+" hits "+e.getName()+" with a fireball for "+dmg+" damage!";
  }

	/**
	*	arrow(Entity e) - override archer interface method arrow 
	*	(but this time the enemy is the entity and hero is super)
	*	@param e the generic enemy
	*	@return hero's name, hero's arrow attack description, enemy's name, and 
	*	dmg the amount of damage on the enemy
	*/ 
  @Override
  public String arrow(Entity e){
    int dmg = (int)(Math.random()*4)+1; //random dmg range of 1-4
    e.takeDamage(dmg);
    return super.getName()+" hits "+e.getName()+" with an arrow for "+dmg+" damage!"; //hero attacks entity
  }

	/**
	*	fireArrow(Entity e) - override archer interface method fireArrow 
	*	(but this time the enemy is the entity and hero is super)
	*	@param e the generic enemy
	*	@return hero's name, hero's fire arrow attack description, enemy's name, and 
	*	dmg the amount of damage on the enemy
	*/ 
  @Override
  public String fireArrow(Entity e){
    int dmg = (int)(Math.random()*5)+2; //random dmg range of 2-5
    e.takeDamage(dmg);
    return super.getName()+" hits "+e.getName()+" with a fire arrow for "+dmg+" damage!";
  }
}