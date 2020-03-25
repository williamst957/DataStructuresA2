/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameshop;

class HashTable
    {
        int tableLength;    // records the max size of the table
        int numItems;       // records number of items in the list
        ShopItem[] table; //hashtable itself

        public HashTable(int size)
        {
            tableLength = size;
            numItems = 0;
            table = new ShopItem[tableLength];
            for (int i = 0; i < tableLength; i++)
            {
                table[i] = null;
            }
        }

        public int hash(String key)
        {
            int value = 0;
            for (int i = 0; i < key.length(); i++)
                value = value + key.charAt(i);
            return value % tableLength;
        }

        public void put(Weapon item,int quantity)
        {
            int location = hash(item.weaponName); //gets location in table based on name
            while (table[location] != null)
            {
                location = (location + 1);      // look one down
                location = location % tableLength; // to ensure wraparound at end of array
            }
            table[location] = new ShopItem(item,quantity);
            numItems++;
        }

        public ShopItem get(String key)
        {
            int location = hash(key); //gets location in table based on key
            while (table[location] != null && key.compareTo(table[location].item.weaponName) != 0)
            {  // not empty and not item
                location = (location + 1);      // look one down
                location = location % tableLength; // to ensure wraparound at end of array
            }
            return table[location];
        }

        public void printTable()
        {
            int count = 0;
            for (int x = 0; x < tableLength; x++)
            {
                if (table[x] != null&&table[x].numberInStock>0)
                {
                    System.out.println("Name: " +table[x].item.weaponName+"   Damage:"+table[x].item.damage+"    Cost:"+table[x].item.cost+"     Quantity in stock:"+table[x].numberInStock);
                }
            }
        }
    }
