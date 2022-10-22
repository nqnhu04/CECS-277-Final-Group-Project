/**
*	Warrior.java - representation of Warrior, a type of enemy, subclass of Enemy
*/ 
public class Warrior extends Enemy implements Fighter{
  /**
  * Warrior(String n, int mHp) - warrior constructor. Initializes Warrior.
  * @param n name of warrior
  * @param mHp max hp of warrior
  */
  public Warrior(String n, int mHp){
    super(n, mHp);
  }
  /**
  * attack(Hero h) - attacks hero with random choice
	*	overrides abstract attack method in Enemy and selects one of enemy's abilities to attack the hero with
  * @param h hero that is being attacked
  * @returns decription of type of attack
  */
   @Override
  public String attack(Hero h){
    int abilities = (int)(Math.random()*2)+1;
    if (abilities==1){
      return sword(h);
    }
    return axe(h);
  }

  /**
  * sword(Entity e) - sword damage and description on entity
  * @param e entity that is being attacked
  * @return enemy's name, enemy's sword attack description, hero's name, and 
	*	dmg the amount of damage on the hero
  */
  @Override
	public String sword(Entity e){
		int dmg = (int)(Math.random()*(e.getHp()/5))+1; //random dmg range of 1 to a number that is proportionate to the entity/hero's max hp
    e.takeDamage(dmg);
    return super.getName()+" slashes "+e.getName()+" with a sword for "+dmg+" damage!"; //entity attacks hero (super is the enemy; entity is the hero)
	}

  /**
  * axe(Entity e) - axe attack damage and description on entity
  * @param e entity that is being attacked
  * @return enemy's name, enemy's axe attack description, hero's name, and
	*	dmg the amount of damage on the hero
  */
  @Override
	public String axe(Entity e){
		int dmg = (int)(Math.random()*(e.getHp()/4))+2; //random dmg range of 2 to a number that is proportionate (bigger dmg in comparison to regular arrow) to the entity/hero's max hp
    e.takeDamage(dmg);
    return super.getName()+" slashes "+e.getName()+" with an axe for "+dmg+" damage!"; //entity attacks hero (super is the enemy; entity is the hero)
	}
}