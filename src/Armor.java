// *********************************************************
// Class: Armor
// Name: Adrian Quintero
// Date: 12/12/2022
//
// Purpose: Contains framework for armor
//
// Attributes: 	-name: String
//              -weight: double
//              -defense: double
//              -description: String
//
// Methods: 	<<constructor>> Armor()
//              +printInfo(): void
//
//*************************************************************

public class Armor {
    private String name;
    private String description;
    private double weight;
    private double defense;

    public Armor() {
        this.name = null;
        this.description = null;
        this.weight = 0;
        this.defense = 0;
    }

    public void printInfo() {
        System.out.println("[ " +this.name + "\t\t" + this.weight + " WGT\t" + this.defense + " DEF ]\n\"" + this.description + "\"");
    }

    public void setName(String name) { this.name = name; }

    public String getName() { return name; }

    public void setDescription(String description) { this.description = description; }

    public void setWeight(double weight) { this.weight = weight; }

    public double getWeight() { return weight; }

    public void setDefense(double defense) { this.defense = defense; }

    public double getDefense() { return defense; }

}
