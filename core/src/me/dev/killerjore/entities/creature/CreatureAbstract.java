package me.dev.killerjore.entities.creature;

import me.dev.killerjore.entities.Entity;

/*
Class made to decouple the specific properties the creature class is going to have
just to neat up the code a tad bit more :)
 */
public abstract class CreatureAbstract extends Entity {

    private int health;
    private int maxHealth;
    private int stamina;
    private int maxStamina;

    private float speed;
    private float attackSpeed;

    private boolean walking = false;
    private boolean attacking = false;

    public CreatureAbstract(float x, float y, int width, int height, int collisionWidth, int collisionHeight) {
        super(x, y, width, height, collisionWidth, collisionHeight);
    }


    public boolean isWalking() { return walking; }
    public void setWalking(boolean walking) { this.walking = walking; }
    public boolean isAttacking() { return attacking; }
    public void setAttacking(boolean attacking) { this.attacking = attacking; }

    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public int getMaxHealth() {
        return maxHealth;
    }
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getStamina() {
        return this.stamina;
    }
    public void setStamina(int stamina) {
        this.stamina = stamina;
    }
    public int getMaxStamina() {
        return this.maxStamina;
    }
    public void setMaxStamina(int maxStamina) {
        this.maxStamina = maxStamina;
    }

    public float getSpeed() {
        return speed;
    }
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setAttackSpeed(float attackSpeed) {
        this.attackSpeed = attackSpeed;
    }
    public float getAttackSpeed() {
        return this.attackSpeed;
    }
}
