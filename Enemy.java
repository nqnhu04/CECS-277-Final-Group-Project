/** 
*	Enemy.java - a representation of generic type of Enemy, 
*	which extends from Entity
*/ 

public abstract class Enemy extends Entity{

  /**
	*	Enemy(String n, int mHp) - Enemy constructor. Initializes Enemy.
  * @param n name of enemy
  * @param mHp max hp of enemy
  */
  public Enemy(String n, int mHp){
    super(n, mHp);
  }
  /**
	*	String attack(Hero h) - generic type of attack, the attack decription
  * @param name of hero being attacked
  */
  public abstract String attack(Hero h);
}