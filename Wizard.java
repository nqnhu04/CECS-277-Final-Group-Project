/**
*	Wizard.java - respresentation of a Wizard, a type of enemy, subclass of Enemy
*/ 

public class Wizard extends Enemy implements Magical{

  /**
  * Wizard(String n, int mHp) - wizard constructor. Initializes Wizard.
  * @param n name of wizard
  * @param mHp max hp of wizard
  */
  public Wizard(String n, int mHp){
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
      return magicMissile(h);
    }
    return fireball(h);
  }

  /**
  * magicMissile(Entity e) - missile attack damage and description on entity
  * @param e entity that is being attacked
  * @return enemy's name, enemy's missile attack description, hero's name, and
	* dmg the amount of damage on the hero
  */
  @Override
	public String magicMissile(Entity e){
		int dmg = (int)(Math.random()*(e.getHp()/4))+2; //random dmg range of 2 to a number that is proportionate to the entity/hero's max hp
    e.takeDamage(dmg);
    return super.getName()+" zaps "+e.getName()+" with a magic missle for "+dmg+" damage!"; //entity attacks hero (super is the enemy; entity is the hero)
	}

  /**
  * fireball(Entity e) - fireball attack damage and description on entity
  * @param e entity that is being attacked
  * @return enemy's name, enemy's fireball attack description, hero's name, and
	*	dmg the amount of damage on the hero
  */
  @Override
	public String fireball(Entity e){
		int dmg = (int)(Math.random()*(e.getHp()/5))+1; //random dmg range of 1 to a number that is proportionate (bigger dmg in comparison to regular arrow) to the entity/hero's max hp
    e.takeDamage(dmg);
    return super.getName()+" hits "+e.getName()+" with a fireball for "+dmg+" damage!"; //entity attacks hero (super is the enemy; entity is the hero)
	}
}