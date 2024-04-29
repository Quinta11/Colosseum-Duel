// *********************************************************
// Class: Weapons
// Name: Adrian Quintero
// Date: 12/12/2022
//
// Purpose: Contains framework for weapons
//
// Attributes: 	-name: String
//              -might: double
//              -hit: double
//              -avoid: double
//              -critChance: int
//              -description: String
//              #rFormula: Formulas
//
// Methods: 	<<constructor>> Weapons()
//              +printInfo(): void
//              +beforeCombatEffect(String, PlayerDuelist, EnemyDuelist): void
//              +afterCombatEffect(String, PlayerDuelist, EnemyDuelist): void
//
//*************************************************************

public class Weapons {

    private String name;
    private double might;
    private double hit;
    private double avoid;
    private int critChance;
    private String description;
    protected Formulas rFormula = new Formulas();

    public Weapons() {
        this.name = null;
        this.description = null;
        this.might = 0;
        this.hit = 0;
    }

    public void printInfo() {
        System.out.println("[ " +this.name + "\t\t" + this.might + " MT\t" + this.hit + " HIT ]\n\"" + this.description + "\"");
    }

    public void beforeCombatEffect(String name, PlayerDuelist player, EnemyDuelist enemy) {}

    public void afterCombatEffect(String name, PlayerDuelist player, EnemyDuelist enemy) {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMight() {
        return might;
    }

    public void setMight(double might) {
        this.might = might;
    }

    public double getHit() {
        return hit;
    }

    public void setHit(double hit) {
        this.hit = hit;
    }

    public double getAvoid() { return avoid; }

    public void setAvoid(double avoid) { this.avoid = avoid; }

    public int getCritChance() { return critChance; }

    public void setCritChance(int critChance) { this.critChance = critChance; }

    public void setDescription(String description) { this.description = description; }
}
