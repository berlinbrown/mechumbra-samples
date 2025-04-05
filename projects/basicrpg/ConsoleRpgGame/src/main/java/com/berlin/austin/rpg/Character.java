package com.berlin.austin.rpg;

public class Character {
    String name;
    int strength;
    int constitution;
    int dexterity;
    int defense;
    int attackDamage;
    int healthPoints;

    public Character(final String name) {
        this.name = name;
    }

    public boolean isAlive() {
        return healthPoints > 0;
    }

    public void takeDamage(int damage) {
        int actualDamage = Math.max(0, damage - defense);
        healthPoints -= actualDamage;
        System.out.println(name + " takes " + actualDamage + " damage! Remaining HP: " + healthPoints);
    }

    public void attack(final Character target) {
        System.out.println(name + " attacks " + target.name + "!");
        target.takeDamage(attackDamage);
    }
}
