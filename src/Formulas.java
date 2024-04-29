// *********************************************************
// Class: Formulas
// Name: Adrian Quintero
// Date: 12/12/2022
//
// Purpose: Contains multiple calculations necessary for the game to function as intended
//
// Attributes: 	-damageDealt: double
//
// Methods: 	+playerTurn(PlayerDuelist, EnemyDuelist): void
//              +enemyTurn(PlayerDuelist, EnemyDuelist): void
//              +determineInitiator(PlayerDuelist, EnemyDuelist): boolean
//              +determineDouble(double, double): boolean
//              +determineIfHit(int): boolean
//              +determineIfCrit(String, int): void
//              +applyPoison(String, PlayerDuelist, EnemyDuelist): void
//              +applyHeal(String, PlayerDuelist, EnemyDuelist): void
//
//*************************************************************

// Random comment regarding reqs: "The program shall generate numbers for this, that and those variables to be used in Equation 7."
//                                "The program shall generate a file following the format detailed in Figure 12."
// Do not make test cases for RNG because it is impossible to

public class Formulas {
    private double damageDealt;

    public void playerTurn(PlayerDuelist player, EnemyDuelist enemy) {
        if(determineIfHit(player.weapon.getHit() - player.armor.getWeight() - enemy.weapon.getAvoid())) {
            setDamageDealt(player.getTotalATK() - enemy.getTotalDEF());
            if (getDamageDealt() < 0) { setDamageDealt(0); }
            if (player.getHealth() > 0) {
                determineIfCrit(player.getName(), player.weapon.getCritChance());
                System.out.println("> Player attacks for " + getDamageDealt() + " damage.");
                enemy.setHealth(enemy.getHealth() - getDamageDealt());
                if (enemy.getHealth() < 0) { enemy.setHealth(0); }
                System.out.println("- Enemy's health is now " + enemy.getHealth() + ".");
            }
        } else if(player.getHealth() > 0) { System.out.println("> Player attempted to attack, but missed."); }
    }

    public void enemyTurn(PlayerDuelist player, EnemyDuelist enemy) {
        if(determineIfHit(enemy.weapon.getHit() - enemy.armor.getWeight() - player.weapon.getAvoid())) {
            setDamageDealt(enemy.getTotalATK() - player.getTotalDEF());
            if (getDamageDealt() < 0) { setDamageDealt(0); }
            if (enemy.getHealth() > 0) {
                determineIfCrit(enemy.getName(), enemy.weapon.getCritChance());
                System.out.println("> Enemy attacks for " + getDamageDealt() + " damage.");
                player.setHealth(player.getHealth() - getDamageDealt());
                if (player.getHealth() < 0) { player.setHealth(0); }
                System.out.println("- Player's health is now " + player.getHealth() + ".");
            }
        } else if(enemy.getHealth() > 0) { System.out.println("> Enemy attempted to attack, but missed."); }
    }

    public boolean determineInitiator(PlayerDuelist player, EnemyDuelist enemy) {
        return !(player.getSpeed() <= enemy.getSpeed());
    }
    public boolean determineDouble(double speed1, double speed2) {
        return ((speed1 - speed2) >= 5);
    }

    public boolean determineIfHit(double hit) {
        return (((int) Math.floor(Math.random()*(100 + 1))) <= hit);
    }

    public void determineIfCrit(String name, int critChance) {
        if(((int) Math.floor(Math.random()*(100 -1 + 1)- 1)) <= critChance) {
            System.out.println("*** " + name + " lands a critical blow! ***");
            setDamageDealt(getDamageDealt()*2.0);
        }
    }

    public void applyPoison(String name, PlayerDuelist player, EnemyDuelist enemy) {
        if(name.contains("Player") && enemy.getHealth() > 0) {
            enemy.setHealth(enemy.getHealth()-3);
            if (enemy.getHealth() <= 0) { enemy.setHealth(1); }
            System.out.println("# " + enemy.getName() + " takes 3 damage from [Poison]. Health is now " + enemy.getHealth() + ".");
        } else if(name.contains("Enemy") && player.getHealth() > 0){
            player.setHealth(player.getHealth()-3);
            if (player.getHealth() <= 0) { player.setHealth(1); }
            System.out.println("# " + player.getName() + " takes 3 damage from [Poison]. Health is now " + player.getHealth() + ".");
        }
    }

    public void applyHeal(String name, PlayerDuelist player, EnemyDuelist enemy) {
        if(name.contains("Player") && player.getHealth() > 0) {
            player.setHealth(player.getHealth()+3);
            if(player.getHealth() > player.getBaseHealth()) { player.setHealth(player.getBaseHealth()); }
            System.out.println("# " + name + " recovers 3HP from [Heal]. Health is now " + player.getHealth() + ".");
        } else if(name.contains("Enemy") && enemy.getHealth() > 0){
            enemy.setHealth(enemy.getHealth()+3);
            if(enemy.getHealth() > enemy.getBaseHealth()) { enemy.setHealth(enemy.getBaseHealth()); }
            System.out.println("# " + name + " recovers 3HP from [Heal]. Health is now " + enemy.getHealth() + ".");
        }
    }

    public double getDamageDealt() { return damageDealt; }

    public void setDamageDealt(double damageDealt) {
        this.damageDealt = damageDealt;
    }

}
