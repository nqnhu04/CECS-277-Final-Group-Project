/*
Group 30

CECS277 Sec05
Nhu Nguyen
Sarah Santos
Gloria Lee

4/24/2022

Dungeons & Monsters
A program that allows a user to explore a dungeon maze and fight monsters that they
encounter along the way.
*/ 

class Main {
  public static void main(String args[]){
    System.out.print("What is your name, traveler? ");
    String hName = CheckInput.getString();
    Hero h = new Hero(hName);
    System.out.print(h.toString());
    int choice = mainMenu(h);
    //while choice<5 (not quitting) and hero's hp is > 0 and level<=3, run the game
    while(choice<5 && h.getHp()>0 && h.getLevel()<=3){
      System.out.print(h.toString());
      choice = mainMenu(h);
    }
    //if the game ends and level is <4, print game over
    if(h.getLevel()<4){
      System.out.println("Game Over");
    //if the game ends and level is == 4, print congratulations
    }else{
      System.out.println("Congratulations you got through all 3 levels!");
    }
  }
  /** 
  * mainMenu(Hero h) -displays main menu for hero h
  * @param h hero
  * @return menu the number choice from menu
  */
  public static int mainMenu(Hero h){
    System.out.println("1. Go North\n2. Go South\n3. Go East\n4. Go West\n5. Quit");
    int menu = CheckInput.getIntRange(1,5);
    //create char variable to hold the char location of hero
    char direction;
    if(menu==1){
      direction = h.goNorth();
    }else if(menu==2){
      direction = h.goSouth();
    }else if(menu==3){
      direction = h.goEast();
    }else if(menu==4){
      direction = h.goWest();
    }else{
      return menu; //stop the code and return menu if menu == 5
    }
    if(direction=='x'){ //by default, the hero's direction code will return 'x' if loc did not change
      System.out.println("Location out of bounds.");
    }else if(direction=='n'){
      System.out.println("There was nothing here.");
    }else if(direction=='s'){
      store(h);
    }else if(direction=='f'){
      System.out.print("You find a locked gate. ");
      //if the hero has key, use key and advance to next level; if not, the hero will not
      if(h.hasKey()){
        if(h.getLevel()<3){
          System.out.println("Luckily you have a key! You proceed to the next area."); //print proceed to next area if there is a next area to proceed (lv<4)
        }else{
          System.out.println("Luckily you have a key!");
        }
        h.useKey();
        h.levelUp();
      }else{
        System.out.println("You do not have a key! You cannot proceed to the next area.");
      }
    }else if(direction=='i'){
      //randomly select either a potion or key
      int rand = (int)(Math.random()*2)+1;
      if(rand==1){
        System.out.println("You found a Potion!");
        h.pickUpPotion();
      }else{
        System.out.println("You found a Key!");
        h.pickUpKey();
      }
      //remove loc char (make it 'n') afterwards
      Map.getInstance().removeCharAtLoc(h.getLocation());
    }else if(direction=='m'){
      //construct an enemy generator to generate enemy
      EnemyGenerator generate = new EnemyGenerator();
      //construct an enemy based on enemy generator
      Enemy e = generate.generateEnemy(h.getLevel());
      monsterRoom(h,e);
    }
    //return menu
    return menu;
  }
  /** 
	*	monsterRoom(Hero h, Enemy e) - displays enemy and asks hero actions to take
  * @param h hero
  * @param e enemy
  * @return true if hero is alive after encounter
  */
  public static boolean monsterRoom(Hero h, Enemy e){
		System.out.println("You have encountered a "+ e.toString());
    int choice;
    //if hero has potion, then display correct prompt
    if(h.hasPotion()){
      System.out.println("1. Fight\n2. Run Away\n3. Drink Potion");
      choice = CheckInput.getIntRange(1,3);
    }else{
      System.out.println("1. Fight\n2. Run Away");
      choice = CheckInput.getIntRange(1,2);
    }
    while(h.getHp()>0){ //loop only runs if hero's hp>0
      //if choose to fight
      if(choice==1){
        fight(h, e);
        //if enemy is dead
        if(e.getHp()<=0){
          System.out.println("You defeated the "+e.getName()+"!");
          // the number of gold found changes depending on level
          int goldfound = (int)(Math.random()*(3*h.getLevel()))+(1*h.getLevel());  
          h.collectGold(goldfound);
          System.out.println("You find "+goldfound+ " gold on the corpse.");
          //remove char at the location
          Map.getInstance().removeCharAtLoc(h.getLocation());
          return true; //return true since enemy is dead and hero is alive
        }
        //if hero is dead
        if(h.getHp()<=0){
          System.out.print("You have died! ");
          return false; // hero is dead and stops loop
        }
       //if choose to run 
      }else if(choice==2){
        //random direction
        int rand = (int)(Math.random()*4)+1;
        if(rand==1){
          h.goNorth();
        }else if(rand==2){
          h.goSouth();
        }else if(rand==3){
          h.goEast();
        }else{
          h.goWest();
        }
        //stop loop by returning true (since the hero is alive)
        return true;
      //if choose to use potion
      }else{
        //if hero can use potion
        if(h.usePotion()){
          System.out.println("You used 1 Health Potion. You are now full health.");
        }else{
          System.out.println("You don't have any Health Potions!");
        }
      }
      //loop prompt and hero's choice until hero chooses 2 or defeats enemy
      if(h.hasPotion()){
        System.out.println("1. Fight\n2. Run Away\n3. Drink Potion");
        choice = CheckInput.getIntRange(1,3);
      }else{
        System.out.println("1. Fight\n2. Run Away");
        choice = CheckInput.getIntRange(1,2);
      }
    }
    return false; //return false if hero is dead
  }

  /** 
	*	fight(Hero h, Enemy e) - single round of damage to enemy
  * enemy attacks back if still alive
  * @param h hero
  * @param e enemy
  * @return true if enemy is still alive
  */
  public static boolean fight(Hero h, Enemy e){
    int mAttack, sAttack;
    System.out.println(h.getAttackMenu());
    mAttack = CheckInput.getIntRange(1,h.getNumAttackMenuItems());
    System.out.println(h.getSubAttackMenu(mAttack));
    sAttack = CheckInput.getIntRange(1,h.getNumSubAttackMenuItems(mAttack));
    System.out.println(h.attack(e, mAttack, sAttack));
    if(e.getHp()>0){
      System.out.println(e.attack(h));
      System.out.println(e.toString());
      return true; //return true since hero has not defeated enemy; enemy is alive
    }
    return false; //return false if enemy is no longer alive  
  }

  /** 
 	*	store(Hero h) - displays store menu of what hero can buy
  * @param h hero
  */
  public static void store(Hero h){
    System.out.println("Welcome to the store. What would you like to buy?");
    System.out.println("1. Health Potion - 25g\n2. Key - 50g\n3. Nothing, just browsing...");
    int choice = CheckInput.getIntRange(1,3);
    while(choice<3){
      if(choice==1){
        if(h.spendGold(25)){
          h.pickUpPotion();
          System.out.println("You bought a Health Potion! You now have "+h.getGold()+ " gold left.");
        }else{
          System.out.println("You do not have enough gold to buy a Health Potion! You currently have "+h.getGold()+ " gold.");
        }
      }else{
        if(h.spendGold(50)){
          h.pickUpKey();
          System.out.println("You bought a Key! You now have "+h.getGold()+ " gold left.");
        }else{
          System.out.println("You do not have enough gold to buy a Key! You currently have "+h.getGold()+ " gold.");
        }
      }
      System.out.println("1. Health Potion - 25g\n2. Key - 50g\n3. Nothing, just browsing...");
      choice = CheckInput.getIntRange(1,3);
    }
  }
}