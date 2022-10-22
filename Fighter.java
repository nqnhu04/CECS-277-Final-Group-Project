/**
*	Fighter.java - Interface for Fighter define the abilities of the Hero and enemies
* Assigns FIGHTER_MENU to the fighter menu and
*	NUM_FIGHTER_MENU_ITEMS to the number of the fighter menu of items
*/ 

public interface Fighter{
  /**
  * fighter menu
  */
  static final String FIGHTER_MENU = "1. Sword\n2. Axe";

  /**
  * number of fighter menu of items
  */
  static final int NUM_FIGHTER_MENU_ITEMS = 2;

  /**
  * sword(Entity e) - sword attack
  * @param e entity being sword attacked
  */
  public String sword(Entity e);

  /**
  * axe(Entity e) - axe attack
  * @param e entity being axe attacked
  */
  public String axe(Entity e);
}