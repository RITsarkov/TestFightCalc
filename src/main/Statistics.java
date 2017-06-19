package main;

import main.core.Unit;
import main.utils.StatMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class Statistics {
    //кол-во атака с юнита
    private static StatMap<String, Integer> attacCountMap = new StatMap<>();
    private static Map<String, StatMap> damageMap = new HashMap<>();
    private static Map<String, StatMap> critMap  = new StatMap<>();
    private static Map<String, StatMap> blockMap  = new StatMap<>();
    private static StatMap<String, Integer> dogeMap  = new StatMap<>();

    private static StatMap totalAttack = new StatMap<>();
    private static StatMap totalCrit = new StatMap<>();
    private static StatMap totalBlock = new StatMap<>();
    private static StatMap totalDoge = new StatMap<>();
    private static StatMap<String, Integer> totalTakeDamage = new StatMap<>();

    static public void printStatistic(){
        System.out.println("\n Statistic: ");


        System.out.println("=== Total attack done ===");
        for (Map.Entry<String, Integer> se : (Set<Map.Entry>) attacCountMap.entrySet()) {
            String name = se.getKey();
            System.out.println("Unit: " + name + "; attack done: " + se.getValue());
        }


        System.out.println("=== Dameg ===");
        for (Map.Entry entry : damageMap.entrySet()) {
           String name  = (String) entry.getKey();
           StatMap statMap = (StatMap) entry.getValue();
           int totalDamageDone = 0;
            for (Map.Entry<Integer, Integer> se : (Set<Map.Entry>) statMap.entrySet()) {
                Double d = Double.valueOf(se.getValue()) / (Integer)totalAttack.get(name) * 100;
                totalDamageDone = totalDamageDone + (se.getValue() * se.getKey());
                System.out.println("Unit: " + name + "; dmg type: " + se.getKey() + "; dmg count: " + se.getValue() + "; " + formatD(d)+"%" +"; damage done: " + se.getValue() * se.getKey() + "hp" );
            }
            System.out.println("Unit: " + name + "; total dmg done: " + totalDamageDone + " hp");
            Double medDmg = Double.valueOf(totalDamageDone)/Double.valueOf((Integer)attacCountMap.get(name));
            System.out.println("Unit: " + name + "; medium dmg per turn: " + formatD(medDmg)  + " hp");
        }

        System.out.println("\n === Crit ===");
        for (Map.Entry entry : critMap.entrySet()) {
            String name  = (String) entry.getKey();
            StatMap statMap = (StatMap) entry.getValue();
            for (Map.Entry<Integer, Integer> se : (Set<Map.Entry>) statMap.entrySet()) {
                Double d = Double.valueOf(se.getValue()) / (Integer)totalAttack.get(name) * 100;
                System.out.println("Unit: " + name + "; crit: " + se.getKey() + "; num: " + se.getValue() + "; " + formatD(d)+"%");
            }
//            Double critChance = Double.valueOf((Integer)totalCrit.get(name)) / Double.valueOf((Integer)totalAttack.get(name)) * 100;
//            System.out.println("Atack/Crit %:" + critChance);
        }

        System.out.println("\n === Block ===");
        for (Map.Entry entry : blockMap.entrySet()) {
            String name  = (String) entry.getKey();
            StatMap statMap = (StatMap) entry.getValue();
            for (Map.Entry<Integer, Integer> se : (Set<Map.Entry>) statMap.entrySet()) {
                Double d = Double.valueOf(se.getValue()) / (Integer)totalTakeDamage.get(name) * 100;
                System.out.println("Unit: " + name + "; damage: " + se.getKey() + "; num: " + se.getValue() + "; " + formatD(d)+"%");
            }
//            Double blockChance = Double.valueOf((Integer)totalBlock.get(name)) / Double.valueOf((Integer)totalTakeDamage.get(name)) * 100;
//            System.out.println("Damage/block %:" + blockChance);
        }

        System.out.println("\n === Doge ===");
        for (Map.Entry<String, Integer> se : (Set<Map.Entry>) dogeMap.entrySet()) {
            String name = se.getKey();
            Double dogeChance = Double.valueOf(se.getValue()) / (Integer)totalTakeDamage.get(name) * 100;
            System.out.println("Unit: " + name + "; doge num: " + se.getValue() + "; damage/doge = :" + formatD(dogeChance)+"%");
        }

    }

    private static String formatD(Double d){
        return String.format("%.2f", d);
    }

    static public void addAttack(Unit unit){
        attacCountMap.putInkKey(unit.getUnitName());
    }

    static public void addDamage(Unit unit, int damage){
        String name = unit.getUnitName();
        if (damageMap.containsKey(unit.getUnitName())){
            StatMap statMap = damageMap.get(name);
            statMap.putInkKey(damage);
        }
        else {
            StatMap statMap = new StatMap();
            statMap.putInkKey(damage);
            damageMap.put(name, statMap);
        }
        totalAttack.putInkKey(name);
    }

    static public void addCrit(Unit unit, int critVal){
        String name = unit.getUnitName();
        if (critMap.containsKey(unit.getUnitName())){
            StatMap statMap = critMap.get(name);
            statMap.putInkKey(critVal);
        }
        else {
            StatMap statMap = new StatMap();
            statMap.putInkKey(critVal);
            critMap.put(name, statMap);
        }
        totalCrit.putInkKey(name);
    }

    static public void addBlock(Unit unit, int block){
        String name = unit.getUnitName();
        if (blockMap.containsKey(unit.getUnitName())){
            StatMap statMap = blockMap.get(name);
            statMap.putInkKey(block);
        }
        else {
            StatMap statMap = new StatMap();
            statMap.putInkKey(block);
            blockMap.put(name, statMap);
        }
        totalBlock.putInkKey(name);
    }

    static public void addDoge(Unit unit){
        dogeMap.putInkKey(unit.getUnitName());
        totalDoge.putInkKey(unit.getUnitName());
    }

    public static void addDamageCount(Unit unit){
        totalTakeDamage.putInkKey(unit.getUnitName());
    }
}