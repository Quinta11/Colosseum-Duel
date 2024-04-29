// *********************************************************
// Class: PlayerDuelist
// Name: Adrian Quintero
// Date: 12/12/2022
//
// Purpose: Extends GenericDuelist, allowing user input in methods overridden
//
// Attributes: 	-input: Scanner
//
// Methods: 	<<constructor>> PlayerDuelist()
//              +statAllocation(): void
//              +inputHealth(): void
//              +inputAttack(): void
//              +inputSpeed(): void
//              +inputDefense(): void
//              +weaponSelect(): void
//              +armorSelect(): void
//              +statAllocationFromFile(String[]): void
//              +weaponSelectFromFile(String[]): void
//              +armorSelectFromFile(String[]): void
//              +statVerification(double): boolean
//              +confirmChoice(): boolean
//              +makeSelection(): int
//
//*************************************************************

import java.util.InputMismatchException;
import java.util.Scanner;
public class PlayerDuelist extends GenericDuelist {
    private Scanner input;
    public PlayerDuelist() {
        this.input = new Scanner(System.in);
        setName("Player");
    }

    @Override
    public void statAllocation() {
        double sum;
        System.out.println("Input your desired stats, in order of: [HP, ATK, SPD, DEF].\nNOTE: Total must add up to 125.");
        do {
            inputHealth();
            inputAttack();
            inputSpeed();
            inputDefense();
            sum = (getHealth() + getAttack() + getSpeed() + getDefense());
        } while(!statVerification(sum));
        setBaseHealth(getHealth());
    }

    public void inputHealth() {
        boolean done = false;
        do {
            try {
                System.out.println("HP (Range: 30 - 50): ");
                do {
                    setHealth(input.nextDouble());
                } while(!((getHealth() >= 30) && (getHealth() <= 50)));
                done = true;
            } catch(InputMismatchException e) {
                System.out.println("Invalid value. Please enter a numerical value.");
                input.nextLine();
            }
        } while (!done);
    }

    public void inputAttack() {
        boolean done = false;
        do {
            try {
                System.out.println("ATK (Range: 15 - 40): ");
                do {
                    setAttack(input.nextDouble());
                } while(!((getAttack() >= 15) && (getAttack() <= 40)));
                done = true;
            } catch(InputMismatchException e) {
                System.out.println("Invalid value. Please enter a numerical value.");
                input.nextLine();
            }
        } while (!done);
    }

    public void inputSpeed() {
        boolean done = false;
        do {
            try {
                System.out.println("SPD (Range: 15 - 40): ");
                do {
                    setSpeed(input.nextDouble());
                } while(!((getSpeed() >= 15) && (getSpeed() <= 40)));
                done = true;
            } catch(InputMismatchException e) {
                System.out.println("Invalid value. Please enter a numerical value.");
                input.nextLine();
            }
        } while (!done);
    }

    public void inputDefense() {
        boolean done = false;
        do {
            try {
                System.out.println("DEF (Range: 10 - 35): ");
                do {
                    setDefense(input.nextDouble());
                } while(!((getDefense() >= 10) && (getDefense() <= 35)));
                done = true;
            } catch(InputMismatchException e) {
                System.out.println("Invalid value. Please enter a numerical value.");
                input.nextLine();
            }
        } while (!done);
    }

    @Override
    public void weaponSelect() {
        int selection;
        do {
            System.out.println("Type an value from 1 to 4 to observe a weapon.");
            do {
                selection = makeSelection();
                switch (selection) {
                    case 1 -> this.weapon = new Sword();
                    case 2 -> this.weapon = new Lance();
                    case 3 -> this.weapon = new Axe();
                    case 4 -> this.weapon = new Bow();
                    default -> System.out.println("Input is out of range. Try again.");
                }
            } while(selection < 1 || selection > 4);
            this.weapon.printInfo();
        } while(!confirmChoice());
        setWeaponSelection(selection);
        setTotalATK();
    }

    public void armorSelect() {
        int selection;
        do {
            System.out.println("Type an value from 1 to 3 to observe a set of armor.");
            do {
                selection = makeSelection();
                switch (selection) {
                    case 1 -> this.armor = new LeatherSet();
                    case 2 -> this.armor = new SoldierSet();
                    case 3 -> this.armor = new RogueSet();
                    default -> System.out.println("Input is out of range. Try again.");
                }
            } while(selection < 1 || selection > 3);
            this.armor.printInfo();
        } while(!confirmChoice());
        setArmorSelection(selection);
        setTotalDEF();
    }


    public void statAllocationFromFile(String[] lineColumns) {
        setHealth(Double.parseDouble(lineColumns[0]));
        setAttack(Double.parseDouble(lineColumns[1]));
        setSpeed(Double.parseDouble(lineColumns[2]));
        setDefense(Double.parseDouble(lineColumns[3]));
        setBaseHealth(getHealth());
    }

    public void weaponSelectFromFile(String[] lineColumns) {
        setWeaponSelection(Integer.parseInt(lineColumns[4]));
        switch (getWeaponSelection()) {
            case 1 -> this.weapon = new Sword();
            case 2 -> this.weapon = new Lance();
            case 3 -> this.weapon = new Axe();
            case 4 -> this.weapon = new Bow();
        }
        setTotalATK();
    }

    public void armorSelectFromFile(String[] lineColumns) {
        setWeaponSelection(Integer.parseInt(lineColumns[5]));
        switch (getWeaponSelection()) {
            case 1 -> this.armor = new LeatherSet();
            case 2 -> this.armor = new SoldierSet();
            case 3 -> this.armor = new RogueSet();
        }
        setTotalDEF();
    }

    public boolean statVerification(double sum) {
        boolean verified = false;
        if(sum == 125) {
            verified = true;
        } else {
            System.out.println("BST does not add up to 125. Re-input your desired stats, in order of: [HP, ATK, SPD, DEF].");
        }
        return verified;
    }

    public boolean confirmChoice() {
        boolean decision;
        System.out.println("Confirm this choice? [YES / NO]");
        String choice = input.next();
        while (!(choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("no"))) {
            System.out.println("Invalid input. Please enter YES or NO.");
            choice = input.next();
        }
        decision = choice.equalsIgnoreCase("yes");
        return decision;
    }

    public int makeSelection() {
        int selection = 0;
        boolean done = false;
        do {
            try {
                selection = input.nextInt();
                done = true;
            } catch(InputMismatchException e) {
                System.out.println("Invalid value. Please enter a numerical value.");
                input.nextLine();
            }
        } while (!done);
        return selection;
    }
}
