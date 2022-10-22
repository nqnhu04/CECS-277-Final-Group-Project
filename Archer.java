/**
*	Archer.java - Interface for Archer define the abilities of the Hero. 
*	Assigns ARCHER_MENU to the attack menu choices and  
*	NUM_ARCHER_MENU_ITEMS to the number of archer menu items
*/

public interface Archer{
  /**
  * archer attack menu choices
  */
  static final String ARCHER_MENU = "1. Arrow\n2. Fire Arrow";

  /**
  * number of archer menu items
  */
  static final int NUM_ARCHER_MENU_ITEMS = 2;

  /** arrow(Entity e) - arrow attack on entity e
  * @param e entity being arrow attacked
  */
  public String arrow(Entity e);

  /** fireArrow(Entity e) - fire arrow attack on entity e
  * @param e entity being fire arrow attacked
  */
  public String fireArrow(Entity e);
}