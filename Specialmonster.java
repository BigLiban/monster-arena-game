public class Specialmonster extends Monster{
  private int temper;

  public Specialmonster(int health, int level, int strength, int temper){
    super(health, level, strength);
    this.temper = temper;
  }

  public Specialmonster(){
    super();
    temper = 0;
  }

  public void setTemper(int temper){
    this.temper = temper;
  }

  public int getTemper(){
    return this.temper;
  }

  public int attack(){ //Everytime the special monster is hit, his temper grows by 1. If he has below 50 health, his temper increases by 3.
    if(this.getHealth() >= 50){
      this.temper += 1;
    } else if(this.getHealth() < 50){
      this.temper += 3;
    }

    return this.getStrength() + temper + 2;  //returns the attack damage plus anger damage.
  }
}