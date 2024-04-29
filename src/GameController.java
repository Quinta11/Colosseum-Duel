// *********************************************************
// Class: GameController
// Name: Adrian Quintero
// Date: 12/12/2022
//
// Purpose: Has the processes being called in main to make the game function
//
// Attributes: 	-file: File
//              -enemy: EnemyDuelist[5]
//              -player: PlayerDuelist
//              -rFormulas: Formulas
//              -numberOfHeals: int
//
// Methods: 	<<constructor>> GameController()
//              +createDuelists(): void
//              +eventOrder(int): void
//              +endGame(): void
//              +writeFile(): void
//              +readFile(): void
//              +runBeforeCombatEffects(int): void
//              +playTurn(int): void
//              +playerInitiates(int): void
//              +enemyInitiates(int): void
//              +endDuel(int, int): boolean
//              +determineIfHeal(): void
//              +playerStatus(): boolean
//              +battleForecast(int): void
//              +makeDecision(): boolean
//
//*************************************************************

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

public class GameController {
    private File file = new File("PlayerStats.txt");
    private EnemyDuelist[] enemy = new EnemyDuelist[5];
    private PlayerDuelist player = new PlayerDuelist();
    private Formulas rFormulas = new Formulas();
    private int numberOfHeals = 3;

    public GameController() {
        for (int i = 0; i < 5; i++) {
            this.enemy[i] = new EnemyDuelist();
        }
        createDuelists();
    }

    public void createDuelists() {
        for(int i = 0; i < 5; i++) { enemy[i].assignInfo(); }
        System.out.println("Welcome to Colosseum Duel. Would you like to import save data before starting? [YES / NO]");
        boolean decision = makeDecision();
        if(decision && file.exists()) {
            readFile();
            System.out.println("Save data has been imported successfully.");
        } else {
            if(decision && !(file.exists())) { System.out.println("Save data does not exist. Continuing with program."); }
            player.statAllocation();
            player.weaponSelect();
            player.armorSelect();
        }
    }

    public void eventOrder(int i) {
        int j = 1;
        battleForecast(i);
        runBeforeCombatEffects(i);
        while(!endDuel(i, j)) {
            System.out.println("-------- ROUND " + j + " --------");
            playTurn(i);
            j++;
        }
    }

    public void endGame() {
        if(playerStatus()) {
            System.out.println("\nCongratulations! All enemies have been defeated. Would you like to save your duelist's build? [YES / NO]");
        } else {
            System.out.println("\nYou have been defeated. Would you like to save your duelist's build? [YES / NO]");
        }
        if(makeDecision()) {
            writeFile();
        }
    }

    public void writeFile() {
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(player.getBaseHealth()+",");
            bufferedWriter.write(player.getAttack()+",");
            bufferedWriter.write(player.getSpeed()+",");
            bufferedWriter.write(player.getDefense()+",");
            bufferedWriter.write(player.getWeaponSelection()+",");
            bufferedWriter.write(player.getArmorSelection()+",");
            bufferedWriter.close();
        } catch(Exception e) {}
    }

    public void readFile() {
        String line;
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                String[] lineColumns;
                lineColumns = line.split(",");
                player.statAllocationFromFile(lineColumns);
                player.weaponSelectFromFile(lineColumns);
                player.armorSelectFromFile(lineColumns);
            }
        } catch(Exception e) {}
    }

    public void runBeforeCombatEffects(int i) {
        player.weapon.beforeCombatEffect(player.getName(), player, enemy[i]);
        enemy[i].weapon.beforeCombatEffect(enemy[i].getName(), player, enemy[i]);
    }

    public void playTurn(int i) {
        if(rFormulas.determineInitiator(player, enemy[i])) {
            playerInitiates(i);
            player.weapon.afterCombatEffect(player.getName(), player, enemy[i]);
            enemy[i].weapon.afterCombatEffect(enemy[i].getName(), player, enemy[i]);
        } else {
            enemyInitiates(i);
            enemy[i].weapon.afterCombatEffect(enemy[i].getName(), player, enemy[i]);
            player.weapon.afterCombatEffect(player.getName(), player, enemy[i]);
        }
    }

    public void playerInitiates(int i) {
        rFormulas.playerTurn(player, enemy[i]);
        rFormulas.enemyTurn(player, enemy[i]);
        if(rFormulas.determineDouble(player.getSpeed(), enemy[i].getSpeed()) && ((player.getHealth() > 0) && (enemy[i].getHealth() > 0))) {
            System.out.println("> Player gets to double.");
            rFormulas.playerTurn(player, enemy[i]);
        }
    }

    public void enemyInitiates(int i) {
        rFormulas.enemyTurn(player, enemy[i]);
        rFormulas.playerTurn(player, enemy[i]);
        if(rFormulas.determineDouble(enemy[i].getSpeed(), player.getSpeed()) && ((player.getHealth() > 0) && (enemy[i].getHealth() > 0))) {
            System.out.println("> Enemy gets to double.");
            rFormulas.enemyTurn(player, enemy[i]);
        }
    }

    public boolean endDuel(int i, int j) {
        boolean endDuel = false;
        if (enemy[i].getHealth() <= 0) {
            endDuel = true;
            System.out.println("\n------ ENEMY DEFEATED ------\n");
            if(i < 4) { System.out.println("Proceeding to the next duel..."); }
            if((numberOfHeals != 0) && (i < 4)) { determineIfHeal(); }
        } else if (player.getHealth() <= 0) {
            endDuel = true;
            System.out.println("\n------ GAME OVER ------\n");
        } else if(j == 200) {
            endDuel = true;
            System.out.println("\n------ DRAW ------\n");
        }
        return endDuel;
    }

    public void determineIfHeal() {
        System.out.println("Would you like to heal your duelist for 20HP? [YES / NO]");
        if(makeDecision()) {
            numberOfHeals--;
            player.setHealth(player.getHealth()+20);
            if(player.getHealth() > player.getBaseHealth()) {
                player.setHealth(player.getBaseHealth());
            }
            System.out.println("Player has been healed for 20HP. Health is now " + player.getHealth() + ".");
        }
    }

    public boolean playerStatus() { return (player.getHealth() > 0); }

    public void battleForecast(int i) {
        System.out.println("|-------------Duel " + (i + 1) + "-------------");
        System.out.println("|\t\t\t Player\t\t\t\tEnemy");
        System.out.println("| HP:\t\t " + player.getHealth() + "\t\t\t\t" + enemy[i].getHealth());
        System.out.println("| ATK:\t\t " + player.getTotalATK() + "\t\t\t\t" + enemy[i].getTotalATK());
        System.out.println("| SPD:\t\t " + player.getSpeed() + "\t\t\t\t" + enemy[i].getSpeed());
        System.out.println("| DEF:\t\t " + player.getTotalDEF() + "\t\t\t\t" + enemy[i].getTotalDEF());
        System.out.println("| WP:\t\t " + player.getWeaponName() + "\t\t\t\t" + enemy[i].getWeaponName());
        System.out.println("| ARMOR:\t " + player.getArmorName() + "\t\t" + enemy[i].getArmorName());
        System.out.println("|--------------------------------");
    }

    public boolean makeDecision() {
        boolean decision;
        Scanner input = new Scanner(System.in);
        String choice = input.next();
        while (!(choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("no"))) {
            System.out.println("Invalid input. Please enter YES or NO.");
            choice = input.next();
        }
        decision = choice.equalsIgnoreCase("yes");
        return decision;
    }
}
