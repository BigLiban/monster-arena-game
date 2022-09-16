/* Monster OOP Game
* By Mohamed Bekdach
* -----------------------------
* In this game, you are the hero that is tasked with entertaining humanity! Your goal is to survive in the arena 
* for as long as you can battling many monsters in order to level up and be rewarded with money to buy items from
* the item shop! There are 3 kinds of monsters with different special features so watch out!
*/

import java.util.Scanner;
import java.util.Random; 

class Main {
  public static void main(String[] args) {
    clearScreen(); //Gets rid of the java stuff in the console to make the game look cleaner at first
    Hero user = new Hero(); // initializes the level, experience counter, strength, health, special ability damage, mana, amount of battles fought, money, amount of health potions and amount of mana potions in that order. In their default states.
    Scanner input = new Scanner(System.in);
    Random rnd = new Random(); 

    System.out.println("Welcome to the Mystery Monster Arena Game!");
    System.out.println("\nYour name is Arthur and it is up to you to give us a good show!");
    System.out.println("\nYou must battle out different monsters in the arena for as long as you can! This is your true purpose in life. Feel free to use the shop to help you at any time! Oh and because we want to make your life as miserable as possible, any extra xp you still have after leveling up won't go to you at all! It will disappear into thin air MUAHAHAHAH!");

    while(user.isAlive()){ //Loop runs the whole game as long as the hero is still alive
      int randomEnemyChoice = rnd.nextInt(11); //Random number that chooses which monster you will fight Randomly

      System.out.println("\nWhat would you like to do? press 1 to start fighting, press 2 to take a breather, 3 to check your stats, 4 to open up the item shop, 5 to use a Mana potion or 6 to use a Health potion.");
      int userInput = input.nextInt();

      if(userInput == 1){
        if(randomEnemyChoice >= 0 && randomEnemyChoice <= 4){ // the series of conditional statements later determine which monster you'll fight based on the random number that the program chooses. The ghost and giant rock monsters are common, while the special boss battle of the book worm is fairly rare with a high reward
          clearScreen();
          monsterBattle(user);
        } else if(randomEnemyChoice >= 5 && randomEnemyChoice <=9){
          clearScreen();
          ghostBattle(user);
        } else if(randomEnemyChoice == 10){
          clearScreen();
          guessingBattle(user);
        }
      } else if(userInput == 2){
        clearScreen();
        System.out.println("You feel pretty rested now!");
      } else if(userInput == 3){
        clearScreen();
        hud(user); //Shows the user their statistics
      } else if(userInput == 4){
        clearScreen();
        itemShop(user); //Opens up the item shop
      } else if(userInput == 5){
        clearScreen();
        if(user.getManaPotions() > 0){ //checks if they have enough mana potions
          System.out.println("You used the Mana potion and gained 50 Mana!");

          user.setManaPotions(user.getManaPotions() - 1); //potion is used, so a bottle is removed
          user.setMana(user.getMana() + 50); //Mana increases by 50
        } else{
          System.out.println("You don't have any Mana potions!");
        }
      } else if(userInput == 6){
        clearScreen();

        if(user.getHealthPotions() > 0){ //Removes a potion from your inventory and adds 50 health if you use the health potion
          System.out.println("You used the Health potion and gained 50 Health!");

          user.setHealthPotions(user.getHealthPotions() - 1);
          user.setHealth(user.getHealth() + 50);
        } else{
          System.out.println("You don't have any Health potions!");
        }
      } else{
        clearScreen();
        System.out.println("Wrong input!");
      }
    }
    System.out.println("You died and lost a fearsome battle!!! Thanks for playing! You survived for " + user.getAmountOfBattles() + " rounds!"); //if the user dies, they get this message
  }

  public static void clearScreen() {  //This special method was taken from stack overflow https://stackoverflow.com/questions/2979383/how-to-clear-the-console   It simply clears the console
    System.out.print("\033[H\033[2J");  
    System.out.flush();  
  }  

  public static void hud(Hero user){ //Prints out stats to the user so they can see how much health they have, how many potions they own etc.
    System.out.println("\nYour Health: --" + user.getHealth() + "--");
    System.out.println("Your Mana: --" + user.getMana() + "--");
    System.out.println("Attack damage: --" + user.getStrength() + "--");
    System.out.println("Special Attack damage: --" + user.specialAbility() + "--");
    System.out.println("Level: --" + user.getLevel() + "--");
    System.out.println("xp: --" + user.getExperienceCounter() + "/" + user.getExperienceCap() + "--");
    System.out.println("coins: --" + user.getMoney() + "--");
    System.out.println("Amount of Mana Potions: --" + user.getManaPotions() + "--");
    System.out.println("Amount of Health Potions: --" + user.getHealthPotions() + "--");
    System.out.println("Amount of battles fought: --" + user.getAmountOfBattles() + "--");
    System.out.println("---------------------------------");
  }

  public static void monsterStats(Monster rock){ //Prints out monster stats to the user
    System.out.println("Enemy type: --Giant Rock--");
    System.out.println("Giant Rock's Health: --" + rock.getHealth() + "--");
    System.out.println("Giant Rock's Damage: --" + rock.attack() + "--");
    System.out.println("Level: --" + rock.getLevel() + "--\n");
  }

  public static void ghostStats(Specialmonster ghost){ //Prints out ghost stats to the user
    System.out.println("Enemy type: --Ghost--");
    System.out.println("Ghost's Health: --" + ghost.getHealth() + "--");
    System.out.println("Ghost damage: --" + ghost.attack() + "--");
    System.out.println("Ghost's Temper: --" + ghost.getTemper() + "--");
    System.out.println("Level: --" + ghost.getLevel() + "--\n");
  }

  public static void wormStats(Specialmonster worm){ //Prints out worm stats to the user
    System.out.println("Enemy type: --Book Worm--");
    System.out.println("Worm's health: --" + worm.getHealth() + "--") ;
    System.out.println("Worm's damage: --" + worm.attack() + "--");
    System.out.println("Worm's Temper: --" + worm.getTemper() + "--");
    System.out.println("Level: --" + worm.getLevel() + "--\n");
  }

  public static void endOfFight(Hero user, int xp){ //Gives xp and more to the user after they win a battle

    user.setAmountOfBattles(user.getAmountOfBattles() + 1); //Adds the amount of battles

    if(user.getAmountOfBattles() % 5 == 0){ //Every 5 battles, resets the players health to 300 if its under that, and adds 100 if its over
      if(user.getHealth() > 300){
        System.out.println("You won 5 battles! As a reward, you get an extra 100 hp");
        user.setHealth(user.getHealth() + 100);
      } else{
        System.out.println("You won 5 battles! As a reward because you're tired, your health is reset to 300!");
        user.setHealth(285); // sets the health to 285, and with the 15 extra health below, the user gets 300 health total.
      }
    }

    user.setHealth(user.getHealth() + 15); //Adds 15 health to the user
    user.setExperienceCounter(user.getExperienceCounter() + xp); //Adds xp to the user

    if(user.getExperienceCounter() >= user.getExperienceCap()){ //levels the player up if they go over 100 xp
      user.levelUp();
      user.setMoney(user.getMoney() + 25);

      System.out.println("You leveled up! You are now level " + user.getLevel() + "!!!");
    }
  }

  public static void monsterBattle(Hero user){ //Activates the giant rock battle
    Monster giantRock = new Monster(80 + 2 * user.getLevel(), user.getLevel(), 15 + 2 * user.getLevel()); // The health increases as the player levels up, sets the level to the same level as the hero, sets the attack damage to 15 plus the hero level x2 so the monsters get harder as you level up
    Scanner input = new Scanner(System.in);

    boolean inFight = true;

    System.out.println("A Giant Rock monster has appeared!"); 

    while(inFight){ // starts the fight while loop
      hud(user); //Shows the hero stats
      monsterStats(giantRock); //Shows the monster stats

      System.out.println("Press 1 to attack, press 2 to use your special attack, press 3 to use a Mana potion or press 4 to use a Health potion! When you attempt to use a potion, you will automatically attack after.\n");
      int userInput = input.nextInt();

      clearScreen();
      int damage = 0; //Makes my code WAY more efficient because now I dont have to repeat the same 15 lines of code twice

      if(userInput == 1){
        damage = user.attack(); //damage is set to normal attack damage
      } else if(userInput == 2){
        if(user.getMana() < 50){ //checks if you dont have enough mana to use the special ability
          System.out.println("You don't have enough Mana for your special ability! You use normal attack instead.");
          damage = user.attack(); //sets the damage to the normal attack damage
        } else{
          damage = user.specialAbility(); //sets the damage to the special ability damage
          user.setMana(user.getMana() - 50); //Removes 50 mana from the user
        }
      } else if(userInput == 3){
        if(user.getManaPotions() > 0){ //checks if they have enough mana potions
          user.setManaPotions(user.getManaPotions() - 1); //potion is used, so a bottle is removed
          user.setMana(user.getMana() + 50); //Mana increases by 50
          damage = user.attack(); //Also attacks automatically, doesn't use special ability damage just in case you didn't want to use it so you get a chance to later use it
        } else{
          System.out.println("You don't have any Mana potions!");
          damage = user.attack(); //Attacks automatically if you don't have any potions
        }
      } else if(userInput == 4){
        if(user.getHealthPotions() > 0){ //Removes a potion from your inventory and adds 50 health if you use the health potion
          user.setHealthPotions(user.getHealthPotions() - 1);
          user.setHealth(user.getHealth() + 50);
          damage = user.attack(); //attacks automatically
        } else{
          System.out.println("You don't have any Health potions!");
          damage = user.attack(); //attacks automatically if you don't have any health potions. same as the mana potion part above
        }
      } else{
        System.out.println("You missed!"); //If you type in a number other than what you're allowed to type in, it is considered a miss and you are punished by doing zero damage. AKA you missing.
      }

      giantRock.setHealth(giantRock.getHealth() - damage); // Attack damage onto the monster making it lose health

      if(giantRock.isDead()){ //checks if the monster is dead
        System.out.println("You killed the Giant Rock, Yay!!!");
        user.setMoney(user.getMoney() + (40 + 2*user.getLevel())); //gives you money as a reward which adds on 2x your level
        endOfFight(user, 50 + 2*user.getLevel()); // gives them the xp plus 2x their level
        inFight = false; //loop ends and you go back to the main method
      } else{
        System.out.println("You did " + damage + " damage to the Giant Rock! It has " + giantRock.getHealth() + " health!");
        user.setHealth(user.getHealth() - giantRock.attack()); //The monster hits you and you lose health

        if(!user.isAlive()){ //checks if the user is dead
          inFight = false;
        } else{
          System.out.println("The Giant Rock hit you with" + giantRock.attack() + " damage! You lost health!");
        }
      }
    }
  }

  public static void ghostBattle(Hero user){ //starts the ghost battle of the game, which is very similar to the monster battle above
    Specialmonster ghost = new Specialmonster(100 + 2 * user.getLevel(), user.getLevel(), 20 + 2 * user.getLevel(), 0); // The health increases as the player levels up, level of the ghost is equal to the user, the damage of the ghost increases as the level goes up
    Scanner input = new Scanner(System.in);

    boolean inFight = true;

    System.out.println("A Ghost has appeared!");

    while(inFight){ //Loop for the battle
      hud(user); //Shows the user's statistics
      ghostStats(ghost); //shows the ghosts statistics
      System.out.println("Press 1 to attack, press 2 to use your special attack, press 3 to use a Mana potion or press 4 to use a Health potion! When you attempt to use a potion, you will automatically attack after.\n");
      int userInput = input.nextInt();
      clearScreen();
      int damage = 0;

      if(userInput == 1){
        damage = user.attack(); //damage is set to the normal attack damage
      } else if(userInput == 2){
        if(user.getMana() < 50){ //checks if your mana is less than 50, if so, you automatically attack using the normal damage. If it's 50 or more, the damage is set to the special ability damage and you lose 50 mana.
          System.out.println("You don't have enough Mana for your special ability! You use normal attack instead.");
          damage = user.attack();
        } else{
          damage = user.specialAbility();
          user.setMana(user.getMana() - 50);
        }
      } else if(userInput == 3){
        if(user.getManaPotions() > 0){ //if you have 1 or more potions, you lose one from your inventory and gain 50 mana.
          user.setManaPotions(user.getManaPotions() - 1);
          user.setMana(user.getMana() + 50);
          damage = user.attack(); //you attack automatically
        } else{
          System.out.println("You don't have any Mana potions!");
          damage = user.attack(); //Also attacks automatically if you don't have any mana potions
        }
      } else if(userInput == 4){ //Same deal for the health, the program checks if you have 1 or more health potions, and if so, subtracts one from your inventory, adds 50 health, and attacks using normal damage. If you don't have any potions, you also attack automatically
        if(user.getHealthPotions() > 0){
          user.setHealthPotions(user.getHealthPotions() - 1);
          user.setHealth(user.getHealth() + 50);
          damage = user.attack();
        } else{
          System.out.println("You don't have any Health potions!");
          damage = user.attack();
        }
      } else{ //You miss if you hit another button
        System.out.println("You missed!!!");
      }

      ghost.setHealth(ghost.getHealth() - damage); //The ghost is damaged 

      if(ghost.isDead()){ //If the ghost is dead, you get money plus other rewards such as xp using the endOfFight method.
        System.out.println("You killed the Ghost, Yay!!!");
        user.setMoney(user.getMoney() + (60 + 2 * user.getLevel()));
        endOfFight(user, 75 + 2*user.getLevel()); // gives them the xp plus 2x their level
        inFight = false; //Ends the loop

      } else{
        System.out.println("You did " + damage + " damage to the Ghost! It has " + ghost.getHealth() + " health!");
        user.setHealth(user.getHealth() - ghost.attack()); //Ghost attacks you

        if(!user.isAlive()){
          inFight = false; //if the user is dead, the loop ends
        } else{
          System.out.println("The ghost hit you with " + ghost.attack() + " damage! You lost health!");
        }
      }
    }
  }

  public static void guessingBattle(Hero user){ //Starts the special rare battle of the bookworm. In this battle, your only weapon is your voice.
    Specialmonster bookWorm = new Specialmonster(1000, user.getLevel(), 15 + 2*user.getLevel(), 0); //sets the level of the bookworm to the user's level, makes the damage 25 plus 2x the user's level
    Random rnd = new Random();
    Scanner input = new Scanner(System.in);

    boolean inFight = true;
    int randomNum = rnd.nextInt(1001); //chooses a random number between 0-1000

    System.out.println("The Book Worm has appeared! The only way to kill this monster is to guess it's secret number! Your weapons and potions are rendered useless!");

    while(inFight){
      hud(user); //Shows the user's stats
      wormStats(bookWorm); //Shows the bookworm's stats

      System.out.println("Book Worm says: 'I'm thinking of a number between 0 and 1000. Haha! You'll never ever be able to guess it!'");

      System.out.println("Guess the number!!!:\n");
      int userGuess = input.nextInt();
      clearScreen();

      if(userGuess < 0 || userGuess > 1000){ //If the user guesses outside the range, they lose health. Also checks if the user is alive or dead
        System.out.println("Book Worm says: 'That's not even in the range!!!'");
        System.out.println("You lose " + bookWorm.attack() + " health!");
        user.setHealth(user.getHealth() - bookWorm.attack());
        if(!user.isAlive()){
          System.out.println("MUAHAHAHAHA! You'll make for a delicous meaty meal underground!");
          inFight = false;
        } else{
          System.out.println("The Book Worm hit you with " + bookWorm.attack() + " damage! You lost health!");
        }
      } else if(userGuess < randomNum){ //If the user guesses below the chosen number, they get a hint and they are also attacked. Also checks if the user is alive or dead
        System.out.println("Book Worm says: 'Wrong!!! That's lower than my secret number!'");
        System.out.println("You lose " + bookWorm.attack() + " health!");
        user.setHealth(user.getHealth() - bookWorm.attack());
        if(!user.isAlive()){
          System.out.println("MUAHAHAHAHA! You'll make for a delicous meaty meal underground!");
          inFight = false;
        } else{
          System.out.println("The Book Worm hit you with " + bookWorm.attack() + " damage! You lost health!");
        }
      } else if(userGuess > randomNum){ //If the user guesses above the chosen number, they get a hint and they are also attacked. Also checks if the user is alive or dead
        System.out.println("Book Worm says: 'Wrong!!! THat's higher than my secret number muahahahaha you'll never guess!'");
        System.out.println("You lose " + bookWorm.attack() + " health!");
        user.setHealth(user.getHealth() - bookWorm.attack());
        if(!user.isAlive()){
          System.out.println("MUAHAHAHAHA! You'll make for a delicous meaty meal underground!");
          inFight = false;
        } else{
          System.out.println("The Book Worm hit you with " + bookWorm.attack() + " damage! You lost health!");
        }
      } else{ //If they choose the correct number, the worm is killed and they get rewards
        System.out.println("Book Worm says: 'Impossible!!! AAAAHH!!! How could it be???' *Explodes*");
        user.setMoney(user.getMoney() + (500 + 2*user.getLevel())); //User gets 500 coins plus 2x their level for extra coins as they level up
        endOfFight(user, (100 + 2*user.getLevel())); //They get rewarded with xp plus 2x their level
        inFight = false; //loop ends
      }
    }
  }

  public static void itemShop(Hero user){ //Starts up the item shop where the user can buy potions
    Scanner input = new Scanner(System.in);
    boolean shopping = true;

    System.out.println("Oh hello there! Welcome to the Magical Item Shop! Here you can buy extra Mana or Health Potions!");

    while(shopping){ //Loop allows the user to shop
      System.out.println("You have " + user.getMoney() + " coins"); 

      System.out.println("Items availible:");
      System.out.println("Blue Mana Potion x1 - 50 coins");
      System.out.println("Health Potion x1 - 100 coins\n");

      System.out.println("Press 1 to buy the mana potion, 2 to buy the Health potion, or 3 to quit");
      int userInput = input.nextInt();
      clearScreen();

      if(userInput == 1){
        if(user.getMoney() >= 50){ //Checks if they can afford it, then adds one mana potions to their inventory, and takes away 50 coins
          System.out.println("You succesfully bought 1 Blue Mana Potion!");
          user.setMoney(user.getMoney() - 50);
          user.setManaPotions(user.getManaPotions() + 1);
        } else{
          System.out.println("You can't afford this item!");
        }
      } else if(userInput == 2){
        if(user.getMoney() >= 100){ //Checks if they can afford it, then adds one health potions to their inventory, and takes away 100 coins
          System.out.println("You succesfully bought 1 Health Potion!");
          user.setMoney(user.getMoney() - 100);
          user.setHealthPotions(user.getHealthPotions() + 1);
        } else{
          System.out.println("You can't afford this item!");
        }
      } else if(userInput == 3){ //Quits the item shop and goes back to the "Main Menu".
        System.out.println("Thank you for visiting, bye bye!");
        shopping = false; //Ends loop
      } else{
        System.out.println("Invalid input!");
      }
    }
  }
}