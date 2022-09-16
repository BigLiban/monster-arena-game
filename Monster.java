class Monster{
  private int health;
  private int level;
  private int strength;

  public Monster(int health, int level, int strength){
    this.health = health;
    this.level = level;
    this.strength = strength;
  }

  public Monster(){
    health = 100;
    level = 0;
    strength = 25;
  }

  public void setHealth(int health){
    this.health = health;
  }

  public void setLevel(int level){
    this.level = level;
  }

  public void setStrength(int strength){
    this.strength = strength;
  }

  public int getHealth(){
    return this.health;
  }

  public int getLevel(){
    return this.level;
  }

  public int getStrength(){
    return this.strength;
  }

  public int attack(){
    return this.strength + 2; 
  }

  public boolean isDead(){ //Checks if the monster is dead or not.
    if(this.health <= 0){
      return true;
    }
    return false;
  }
}