/**
*	Magical.java - Interface for Magical define the abilities of the Hero and enemies
* Assigns MAGIC_MENU to the magic menu choices and
*	NUM_MAGIC_MENU_ITEMS to the number of the magic menu of items
*/ 

public interface Magical{
  /**
  * magic menu choices
  */
  static final String MAGIC_MENU = "1. Magic Missile\n2. Fireball";

  /**
  * number of magic menu items
  */
  static final int NUM_MAGIC_MENU_ITEMS = 2;

  /**
  * fireball(Entity e) - magicMissile(Entity e) - magic missile attack on entity
  * @param e entity being missile attacked
  */
  public String magicMissile(Entity e);

  /**
  * firball attack on entity
  * @param e entity being fireball atacked
  */
  public String fireball(Entity e);
}