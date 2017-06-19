package main;


import main.core.Fight;
import main.core.Unit;
import main.utils.StatMap;

import java.util.Map;
import java.util.Set;

public class Main
{
    public static void main(String[] arg){
        Main main = new Main();

        main.doRoundCountCombat();
//        main.doSingleCombat();
        main.doMultipleCombat();
        Statistics.printStatistic();
    }

    static int ROUNDS_COUNTER = 1000000;
    private void doRoundCountCombat() {
        System.out.println("====== Archer solo fight ========");
        //Статистика за сколько ходов лучник убьет война
        StatMap<Integer,Integer> resultArcher = new StatMap();

        for(int i = 1; i <= ROUNDS_COUNTER; i++) {
            Fight arena = new Fight();
            int nRound = arena.doSinglFight(createArcher(), createWarrior(), 60);
            resultArcher.putInkKey(nRound);
        }
        printExtremum(resultArcher, "Archer");
        printResult(resultArcher, "Archer");

        //Статистика за сколько ходов воин убьет лучника
        System.out.println("====== Warrior solo fight ========");
        StatMap<Integer,Integer> resultWarrior = new StatMap();
        for(int i=1; i <= ROUNDS_COUNTER; i++) {
            Fight arena = new Fight();
            int nRound = arena.doSinglFight(createWarrior(), createArcher(), 60);
            resultWarrior.putInkKey(nRound);
        }
        printExtremum(resultWarrior, "Warrior");
        printResult(resultWarrior, "Warrior");
    }

    private void printExtremum(Map<Integer, Integer> map, String name){
        int extrem = 0;
        int extremRounds = 0;
        for(Map.Entry<Integer, Integer> e: map.entrySet()){
            int curVal = e.getValue();
            if(curVal > extrem) {
                extrem = curVal;
                extremRounds = e.getKey();
            }
        }
        if(extremRounds != 0)
            System.out.println(name + " maxWins in " + extremRounds + " val: " + extrem);
    }

    private void printResult(Map  result, String name){
        for (Map.Entry<Integer, Integer> entry : (Set<Map.Entry>) result.entrySet()) {
            Double d = Double.valueOf(entry.getValue())/ ROUNDS_COUNTER * 100;
            System.out.println(name + " wins. Rounds: " + entry.getKey() + "; Total wins: " + entry.getValue() + "; " + formatD(d)+"%");
        }

        for (Map.Entry<Integer, Integer> entry : (Set<Map.Entry>) result.entrySet()) {
            System.out.println(entry.getKey());
        }
        for (Map.Entry<Integer, Integer> entry : (Set<Map.Entry>) result.entrySet()) {
            Double d = Double.valueOf(entry.getValue())/ ROUNDS_COUNTER * 100;
            System.out.println(formatD(d)+"%");
        }
    }


    public static void doSingleCombat(){
        Fight arena = new Fight();
        int result = arena.doFight(createArcher(), createWarrior(), 60);
        if(result == 1) {
                System.out.println("Archer - is WIIIINNNNNEERRRR");
        }

        else if (result == 2) {
                System.out.println("Warrior - is WIIIINNNNNEERRRR");
        }
        else if (result == 3) {
                System.out.println("Archer and Warrior - RIP. WAR NEWER CHANGE!");
        }
    }

    public static void doMultipleCombat(){
        System.out.println("====== Warrior vs Archer battle ========");
        int archerWins= 0;
        int warriorWins= 0;
        int draw = 0;
        int errors= 0;

        for(int i=1; i <= ROUNDS_COUNTER; i++){
            Fight arena = new Fight();
            int result = arena.doFight(createArcher(), createWarrior(), 60);

            if (result == 1)
                archerWins ++;
            else if (result == 2)
                warriorWins ++;
            else if (result == 3)
                draw ++;
            else
                errors++;

        }
        System.out.println("Tests done =" + ROUNDS_COUNTER);
        Double warriorWinChance = Double.valueOf(warriorWins)/Double.valueOf(archerWins+warriorWins) * 100;
        Double archerWinChance = Double.valueOf(archerWins)/Double.valueOf(archerWins+warriorWins) * 100;
        System.out.println("Warrior win chance: " + formatD(warriorWinChance) + "%");
        System.out.println("Archer win chance: " + formatD(archerWinChance) + "%");
        Double warriorWinsPr = Double.valueOf(warriorWins)/Double.valueOf(ROUNDS_COUNTER) * 100;
        Double archerWinsPr = Double.valueOf(archerWins)/Double.valueOf(ROUNDS_COUNTER) * 100;
        Double drawPr = Double.valueOf(draw)/Double.valueOf(ROUNDS_COUNTER) * 100;
        System.out.println("Warrior wins count:" + warriorWins + "; " + formatD(warriorWinsPr) + "%");
        System.out.println("Archer wins count:" + archerWins + "; " + formatD(archerWinsPr) +"%");
        System.out.println("Draw count:" + archerWins + "; " + drawPr + "%");
    }

    private static String formatD(Double d){
        return String.format("%.2f", d);
    }

    public static Unit createArcher(){
        Unit archer = new Unit("Archer", 600){
            @Override
            public int crit(int damage) {
                return super.crit(damage) * 2;
            }
        };
        archer.setDamage(10);
        archer.setDogeChance(40);
        archer.setAttackSpeed(2);
        archer.setCritChane(25);
        archer.setSpeed(0);
        archer.setMelee(false);

        return archer;
    }

    public static Unit createWarrior(){
        Unit warrior = new Unit("Warrior", 800);
        warrior.setDamage(25);
        warrior.setBlockChance(50);
        warrior.setBlockVal(7);
        warrior.setAttackSpeed(1);
        warrior.setSpeed(10);

        return warrior;
    }

}
