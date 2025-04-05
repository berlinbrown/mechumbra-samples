package com.berlin.austin.rpg;

import java.util.Scanner;

/**
 * Hello world!
 */
public class ConsoleRpgApp {
    public static void main(final String[] args) {

        System.out.println("Hello World!");
        System.out.println("=== Starting Game === ");

        final Character hero = new Character("Hero Player");
        final Character enemy = new Character("Goblin Enemy");

        // Setup stats
        hero.strength = 30;
        hero.constitution = 20;
        hero.dexterity = 15;
        hero.defense = 30;
        hero.attackDamage = 40;
        hero.healthPoints = 100;

        // Setup enemy character stats
        enemy.strength = 30;
        enemy.constitution = 20;
        enemy.dexterity = 15;
        enemy.defense = 30;
        enemy.attackDamage = 40;
        enemy.healthPoints = 120;

        final Scanner scanner = new Scanner(System.in);
        System.out.println("A wild Goblin appears!");

        while (hero.isAlive() && enemy.isAlive()) {
            System.out.println("\nWhat do you want to do? (1) Attack (2) Run");
            String input = scanner.nextLine();

            if (input.equals("1")) {
                hero.attack(enemy);

                if (enemy.isAlive()) {
                    enemy.attack(hero);
                } else {
                    System.out.println("The Goblin is defeated!");
                }
            } else if (input.equals("2")) {
                System.out.println("You run away!");
                break;
            } else {
                System.out.println("Invalid input.");
            }

            if (!hero.isAlive()) {
                System.out.println("You have been defeated...");
            }
        }

        scanner.close();
    }
}
