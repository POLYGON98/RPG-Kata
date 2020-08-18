package com.mkodo;

public abstract class GameObject {
    protected int health;
    protected int level;
    private boolean alive = true;
    private int distance = 0;

    public GameObject(int level) {
        this.level = level;
    }

    public int getHealth() {
        return health;
    }

    public void receiveDamage(int damageReceived) {
        int healthAfterDamage = health - damageReceived;
        health = healthAfterDamage < 0 ? 0 : healthAfterDamage;
    }

    public int getLevel() {
        return level;
    }

    public boolean isAlive() {
        return alive;
    }

    public void die() {
        alive = false;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distanceAmount) {
        this.distance = distanceAmount;
    }
}
