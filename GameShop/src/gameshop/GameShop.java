/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameshop;

import java.util.Scanner;

/**
 *
 * @author Adder
 */
public class GameShop {

        public static int getInteger(Scanner sc,String message){
            System.out.print(message);
            while (!sc.hasNextInt()) 
            {
                sc.nextLine(); //clear the invalid input ...
                System.out.print(message);
            }
            return sc.nextInt();
        }
        
        public static double getDouble(Scanner sc,String message){
            System.out.print(message);
            while (!sc.hasNextDouble()) 
            {
                sc.nextLine(); //clear the invalid input ...
                System.out.print(message);
            }
            return sc.nextDouble();
        }
        
    
        public static void addWeapons(HashTable h,Scanner sc)
        {
            System.out.println("***********WELCOME TO THE WEAPON ADDING MENU*********");
            String weaponName; int weaponRange; int weaponDamage; double weaponWeight; double weaponCost;
            int quantity;
            System.out.print("Please enter the NAME of the Weapon ('end' to quit):");
            weaponName=sc.next();
            while (weaponName.compareTo("end") != 0)
            {
                weaponRange= getInteger(sc,"Please enter the Range of the Weapon (0-10):"); 
                weaponDamage=getInteger(sc,"Please enter the Damage of the Weapon:"); 
                weaponWeight= getDouble(sc,"Please enter the Weight of the Weapon (in pounds):");
                weaponCost=getDouble(sc,"Please enter the Cost of the Weapon:");
                Weapon w = new Weapon(weaponName, weaponRange, weaponDamage, weaponWeight, weaponCost);
                quantity=getInteger(sc,"Please enter the quantity in stock:"); 
                h.put(w,quantity);
                System.out.print("Please enter the NAME of another Weapon ('end' to quit):");
                weaponName = sc.next();
            }
        }

        public static void showRoomMenu(HashTable ht,Player p){
            System.out.println("WELCOME TO THE SHOWROOM!!!!");
            ht.printTable();
            System.out.println("You have "+p.money+" money.");
            System.out.println("Please select a weapon to buy('end' to quit):");
        }
        
        public static void showRoom(HashTable ht, Player p,Scanner sc)
        {
            String choice;
            showRoomMenu(ht,p);
            choice=sc.next();
            while (choice.compareTo("end") != 0 && !p.inventoryFull())
            {
                ShopItem si = ht.get(choice);
                if (si != null)
                {
                    if (si.item.cost > p.money)
                    {
                        System.out.println("Insufficient funds to buy "+si.item.weaponName );
                    }
                    else
                    {
                        p.buy(si.item);
                        p.withdraw(si.item.cost);
                        si.numberInStock--;
                    }
                }
                else
                {
                    System.out.println(" ** "+choice+" not found!! **" );
                }
                showRoomMenu(ht,p);
                choice = sc.next();
            }
            System.out.println("");
        }
        
        public static void main(String[] args)
        {
            Scanner sc = new Scanner(System.in);
            String pname;
            System.out.println("Please enter Player name:");
            pname=sc.next();
            Player pl= new Player(pname,45);
            HashTable ht= new HashTable(101);
            addWeapons(ht,sc);
            showRoom(ht, pl,sc);
            pl.printCharacter();

        }

}
