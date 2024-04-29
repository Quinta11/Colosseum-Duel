// *********************************************************
// Class: GenericDuelist
// Name: Adrian Quintero
// Date: 12/12/2022
//
// Purpose: Generic class for creating duelists
//
// Attributes: 	-name: String
//              -baseHealth: double
//              -health: double
//              -attack: double
//              -totalATK: double
//              -speed: double
//              -defense: double
//              -totalDEF: double
//              -weaponSelection: int
//              -armorSelection: int
//              #weapon: Weapons
//              #armor: Armor
//
// Methods: 	+assignInfo(): void
//              +statAllocation(): void
//              +weaponSelect(): void
//              +armorSelect(): void
//
//*************************************************************

public class GenericDuelist {
    private String name;
    private double baseHealth;
    private double health;
    private double attack;
    private double totalATK;
    private double speed;
    private double defense;
    private double totalDEF;
    private int weaponSelection;
    private int armorSelection;
    protected Weapons weapon;
    protected Armor armor;

    public void assignInfo() {
        statAllocation();
        weaponSelect();
        armorSelect();
    }

    public void statAllocation() {
        double sum;
        do {
            this.health = Math.floor(Math.random()*(50 - 30 + 1)+ 30);
            this.attack = Math.floor(Math.random()*(40 - 15 + 1)+ 15);
            this.speed = Math.floor(Math.random()*(40 - 15 + 1)+ 15);
            this.defense = Math.floor(Math.random()*(35 - 10 + 1)+ 10);
            sum = (this.health + this.attack + this.speed + this.defense);
        } while (sum != 120.0);
        setBaseHealth(getHealth());
    }

    public void weaponSelect() {
        int selection = (int) Math.floor(Math.random()*(4 - 1 +1)+ 1);
        switch (selection) {
            case 1 -> this.weapon = new Sword();
            case 2 -> this.weapon = new Lance();
            case 3 -> this.weapon = new Axe();
            case 4 -> this.weapon = new Bow();
        }
        setTotalATK();
    }

    public void armorSelect() {
        int selection = (int) Math.floor(Math.random()*(3 - 1 +1)+ 1);
        switch (selection) {
            case 1 -> this.armor = new LeatherSet();
            case 2 -> this.armor = new SoldierSet();
            case 3 -> this.armor = new RogueSet();
        }
        setTotalDEF();
    }

    public void setTotalATK() { this.totalATK = this.attack + this.weapon.getMight(); }

    public double getTotalATK() { return totalATK; }

    public void setTotalDEF() { this.totalDEF = this.defense + this.armor.getDefense(); }

    public double getTotalDEF() { return totalDEF; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public double getHealth() { return health; }

    public void setHealth(double health) { this.health = health; }

    public double getBaseHealth() { return baseHealth; }

    public void setBaseHealth(double health) { this.baseHealth = health; }

    public double getAttack() { return attack; }

    public void setAttack(double attack) { this.attack = attack; }

    public double getSpeed() { return speed; }

    public void setSpeed(double speed) { this.speed = speed; }

    public double getDefense() {
        return defense;
    }

    public void setDefense(double defense) {
        this.defense = defense;
    }

    public String getWeaponName() {
        return weapon.getName();
    }

    public String getArmorName() { return armor.getName(); }

    public int getWeaponSelection() { return weaponSelection; }

    public void setWeaponSelection(int weaponSelection) { this.weaponSelection = weaponSelection; }

    public int getArmorSelection() { return armorSelection; }

    public void setArmorSelection(int armorSelection) { this.armorSelection = armorSelection; }
}
