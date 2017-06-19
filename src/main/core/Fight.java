package main.core;

public class Fight {

    /**
     * Проверка за сколько раундов победит первый боецэ
     * @param unitA
     * @param unitB
     * @param distance
     * @return
     */
    public int doSinglFight(Unit unitA, Unit unitB, int distance){
        int n = 1;
        while (!unitA.isDead()){
            if(unitA.isMelee() && distance> 0)
                distance = distance - unitA.getSpeed();
            else
                unitA.attack(unitB);

            if(unitB.isDead()) {
                return n;
            }
            n++;
        }
        return -1;
    }

    public int doFight(Unit unitA ,Unit unitB, int distance){
        int rounds = 1;
        while (!unitA.isDead() || !unitA.isDead()){
            if(unitA.isMelee() && distance > 0)
                distance = distance - unitA.getSpeed();
            else
                unitA.attack(unitB);

            if(unitB.isMelee() && distance > 0)
                distance = distance - unitB.getSpeed();
            else
                unitB.attack(unitA);

            if(!unitA.isDead() && unitB.isDead()) {
                return 1;
            }

            else if (unitA.isDead() && !unitB.isDead()) {
                return 2;
            }
            else if (unitA.isDead() && unitB.isDead()) {
                return 3;
            }
            rounds++;
        }

        return -1;
    }
}
