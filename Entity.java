/**
*	Entity.java - a representation of generic type of Entity,
*	describes a character in the game
*/ 

public abstract class Entity{
  private String name;
  private int hp, maxHp;
  /**
  * Entity(String n, int mHp) - Entity constructor. Assgins the Entity's name and
	*	starting hit points. Assign the mHp value to maxHp and hp.
  * @param n name of entity
  * @param mHp max hp of entity
  */
  public Entity(String n, int mHp){
    name = n;
    maxHp = mHp;
    hp = maxHp;
  }
  /**
  * getName() - returns name
  * @return name of entity
  */ 
   public String getName(){
     return name;
   }
  /**
  * getHp() - returns current hp
  * @return current hp of entity
  */
  public int getHp(){
    return hp;
  }
  /**
  * heal() - restores hp to max hp
  */
  public void heal(){
    hp = maxHp;
  }
  /**
  * takeDamage(int d) - updates hp after taking damage
	*	@param d the number of damage
  */
  public void takeDamage(int d){
    hp-=d;
    if(hp<0){
      hp = 0;
    }
  }
  /**
  * toString() - displays name and current hp out of max hp
  * @return name and hp the current hp out of maxHp the max hp
  */
  @Override
  public String toString(){
    return name+"\nHP: "+hp+"/"+maxHp;
  }
}