// *********************************************************
// Class: Lance
// Name: Adrian Quintero
// Date: 12/12/2022
//
// Purpose: Extended class of Weapons()
//
// Attributes:  N/A
//
// Methods: 	+<<constructor>> Lance()
//              +afterCombatEffect(String, PlayerDuelist, EnemyDuelist): void
//
//*************************************************************

public class Lance extends Weapons {
    public Lance() {
        setName("Lance");
        setMight(12);
        setHit(80);
        setDescription("A far-reaching weapon with high accuracy, albeit on the lower end of the damage spectrum. Applies [Heal] to unit after combat.");
    }

    public void afterCombatEffect(String name, PlayerDuelist player, EnemyDuelist enemy) {
        rFormula.applyHeal(name, player, enemy);
    }
}
