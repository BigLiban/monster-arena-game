class Hero{
  private int level;
  private int experienceCounter;
  private int experienceCap; //experience cap for leveling up, increases every level
  private int strength;
  private int health;
  private int specialAbility;
  private int mana;
  private int amountOfBattles;
  private int money;
  private int healthPotions;
  private int manaPotions;

  public Hero(int level, int experienceCounter, int experienceCap, int strength, int health, int specialAbility, int mana, int amountOfBattles, int money, int healthPotions, int manaPotions){
    this.level = level;
    this.experienceCounter = experienceCounter;
    this.experienceCap = experienceCap;
    this.strength = strength;
    this.health = health;
    this.specialAbility = specialAbility;
    this.mana = mana;
    this.amountOfBattles = amountOfBattles;
    this.money = money;
    this.healthPotions = healthPotions;
    this.manaPotions = manaPotions;
  }

  public Hero(){
    level = 0;
    experienceCounter = 0;
    experienceCap = 100;
    strength = 25;
    health = 300;
    specialAbility = 30;
    mana = 100;
    amountOfBattles = 0;
    money = 0;
    healthPotions = 0;
    manaPotions = 0;
  }

  public void setLevel(int level){
    this.level = level;
  }

  public void setExperienceCounter(int experienceCounter){
    this.experienceCounter = experienceCounter;
  }

  public void setExperienceCap(int experienceCap){
    this.experienceCap = experienceCap;
  }


  public void setStrength(int strength){
    this.strength = strength;
  }


  public void setHealth(int health){
    this.health = health;
  }

  public void setSpecialAbility(int specialAbility){
    this.specialAbility = specialAbility;
  }

  public void setMana(int mana){
    this.mana = mana;
  }

  public void setAmountOfBattles(int amountOfBattles){
    this.amountOfBattles = amountOfBattles;
  }

  public void setMoney(int money){
    this.money = money;
  }

  public void setHealthPotions(int healthPotions){
    this.healthPotions = healthPotions;
  }

  public void setManaPotions(int manaPotions){
    this.manaPotions = manaPotions;
  }

  public int getLevel(){
    return this.level;
  }

  public int getExperienceCounter(){
    return this.experienceCounter;
  }

  public int getExperienceCap(){
    return this.experienceCap;
  }


  public int getStrength(){
    return this.strength;
  }

  public int getHealth(){
    return this.health;
  }

  public int getSpecialAbility(){
    return this.specialAbility;
  }

  public int getMana(){
    return this.mana;
  }

  public int getAmountOfBattles(){
    return this.amountOfBattles;
  }

  public int getMoney(){
    return this.money;
  }

  public int getHealthPotions(){
    return this.healthPotions;
  }

  public int getManaPotions(){
    return this.manaPotions;
  }

  public int attack(){
    return this.strength;
  }

  public int specialAbility(){
    return this.specialAbility;
  }

  public void levelUp(){
    this.level ++;
    this.experienceCounter = 0;
    this.experienceCap += 25;
    this.strength += 2;
    this.specialAbility += 3;
    this.health += 75;
    this.mana += 100;
  }

  public boolean isAlive(){
    if(this.health <= 0){
      return false;
    }
    return true;
  }
}