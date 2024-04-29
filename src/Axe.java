// *********************************************************
// Class: Axe
// Name: Adrian Quintero
// Date: 12/12/2022
//
// Purpose: Extended class of Weapons()
//
// Attributes:  N/A
//
// Methods: 	<<constructor>> Axe()
//              +beforeCombatEffect(String, PlayerDuelist, EnemyDuelist): void
//
//*************************************************************

public class Axe extends Weapons {
    public Axe() {
        setName("Axe");
        setMight(16);
        setHit(50);
        setDescription("A powerful weapon capable of landing critical blows. However, its hit rate leaves much to be desired.");
    }

    public void beforeCombatEffect(String name, PlayerDuelist player, EnemyDuelist enemy) {
        setCritChance(20);
    }
}
