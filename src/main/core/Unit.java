package main.core;

import main.Statistics;

/**
 * ����� �����. ������ ��� ��������������
 */
public class Unit {
    private final String unitName;
    //���� �� �����
    private int damage;
    //��������� �����
    private int attackSpeed;
    //���� �������. ���� ��������� ���������� ����
    private int dogeChance = 0;
    //���� ������������� ����
    private int blockChance = 0;
    //���������� ������������ �����
    private int blockVal = 0;
    //�������� ������������
    private int speed = 0;
    //���������� ��� ������� ���� (�� ��������� ����������)
    private boolean isMelee = true;

    //���� �����
    private int critChane = 0;

    //���-�� ������
    private int hp;
    private final int maxHp;

    public Unit(String unitName, int hp){
        this.unitName = unitName;
        this.maxHp = hp;
        this.hp = hp;
    }

//    public void getNewOne(){
//
//    }

    public void attack(Unit enemyUnit){
        //�������� �� �������� �����
        for(int i = 1; i<= attackSpeed; i++){
            attackSingl(enemyUnit);
            Statistics.addAttack(this);
        }
    }

    private void attackSingl(Unit enemyUnit){
        int curDamage = damage;

        double random = Math.random();
        if (critChane>0 && (random * 100) <= critChane) {
            curDamage = crit(damage);
            Statistics.addCrit(this, curDamage);
        }

        enemyUnit.takeDamage(curDamage, this);
    }

    //�������� ����� (��� ��������������� � ����������)
    public int crit(int damage){
        return damage;
    }

    public void takeDamage(int damageVal, Unit enemyUnit){
        Statistics.addDamageCount(this);
        //�������� �����
        double random1 = Math.random();
        if (blockChance > 0 && (random1 * 100) <= blockChance) {
            Statistics.addBlock(this, blockVal);
            damageVal = damageVal - blockVal;
        }else{
            Statistics.addBlock(this, 0);
        }

        //�������� �������
        double random2 = Math.random();
        if (dogeChance > 0 && (random2 * 100) <= dogeChance){
            Statistics.addDoge(this);
            damageVal = 0;
        }


        if(damageVal < 0)
            damageVal = 0;
        //�������� ���������� ���� �� ��
        hp = hp - damageVal;
        if(hp <= 0)
            unitDead();

        Statistics.addDamage(enemyUnit, damageVal);
    }

    private void unitDead() {
//        System.out.println(unitName + " - isDead ");
    }

    public boolean isDead(){
        return hp <= 0;
    }


    //GETTERS & SETTERS

    public String getUnitName() {
        return unitName;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public int getDogeChance() {
        return dogeChance;
    }

    public void setDogeChance(int dogeChance) {
        this.dogeChance = dogeChance;
    }

    public int getBlockChance() {
        return blockChance;
    }

    public void setBlockChance(int blockChance) {
        this.blockChance = blockChance;
    }

    public int getBlockVal() {
        return blockVal;
    }

    public void setBlockVal(int blockVal) {
        this.blockVal = blockVal;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getCritChane() {
        return critChane;
    }

    public void setCritChane(int critChane) {
        this.critChane = critChane;
    }

    public boolean isMelee() {
        return isMelee;
    }

    public void setMelee(boolean melee) {
        isMelee = melee;
    }
}
