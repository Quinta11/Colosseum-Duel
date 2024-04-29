// *********************************************************
// Class: Bow
// Name: Adrian Quintero
// Date: 12/12/2022
//
// Purpose: Extended class of Weapons()
//
// Attributes: N/A
//
// Methods: 	+<<constructor>> Bow()
//              +beforeCombatEffect(String, PlayerDuelist, EnemyDuelist): void
//              +afterCombatEffect(String, PlayerDuelist, EnemyDuelist): void
//
//*************************************************************

public class Bow extends Weapons {
    public Bow() {
        setName("Bow");
        setMight(8);
        setHit(70);
        setDescription("A ranged weapon granting 10 AVOID, lowering chances of getting hit. Inflicts [Poison] on foe after combat.");
    }

    public void beforeCombatEffect(String name, PlayerDuelist player, EnemyDuelist enemy) {
        setAvoid(10);
    }

    public void afterCombatEffect(String name, PlayerDuelist player, EnemyDuelist enemy) {
        rFormula.applyPoison(name, player, enemy);
    }
}
