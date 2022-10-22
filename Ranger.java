/**
*	Ranger.java - representation of Ranger, a type of enemy, subclass of Enemy
*/ 

public class Ranger extends Enemy implements Archer{
  /**
  * Ranger(String n, int mHp) - constructor for ranger. Initializes Ranger.
  * @param n name of ranger
  * @param mHp max hp of ranger
  */
  public Ranger(String n, int mHp){
    super(n, mHp);
  }
  /**
  * attack(Hero h) - attacks hero with random choice
	*	overrides abstract attack method in Enemy and selects one of enemy's abilities to attack the hero with
  * @param h hero that is being attacked
  * @return fireArrow decription of type of fire arrow attack
  */
  @Override
  public String attack(Hero h){
    int abilities = (int)(Math.random()*2)+1;
    if (abilities==1){
      return arrow(h);
    }
    return fireArrow(h);
  }

  /**
  * arrow(Entity e) - arrow attack damage and description on entity
  * @param e entity that is being attacked
  * @return enemy's name, enemy's arrow attack description, hero's name, and
	*	dmg the amount of damage on the hero
  */
  @Override
	public String arrow(Entity e){
		int dmg = (int)(Math.random()*(e.getHp()/5))+1; //random dmg range of 1 to a number that is proportionate to the entity/hero's max hp
    e.takeDamage(dmg);
    return super.getName()+" hits "+e.getName()+" with an arrow for "+dmg+" damage!"; //entity attacks hero (super is the enemy; entity is the hero)
	}

  /**
  * fireArrow(Entity e) - fire arrow attack damage and description on entity
  * @param e entity being attacked
  * @return enemy's name, enemy's fire arrow attack description, hero's name, and
	*	dmg the amount of damage on the hero
  */
  @Override
	public String fireArrow(Entity e){
		int dmg = (int)(Math.random()*(e.getHp()/4))+2; //random dmg range of 2 to a number that is proportionate (bigger dmg in comparison to regular arrow) to the entity/hero's max hp
    e.takeDamage(dmg);
    return super.getName()+" hits "+e.getName()+" with a fire arrow for "+dmg+" damage!"; //entity attacks hero (super is the enemy; entity is the hero)
	}
}