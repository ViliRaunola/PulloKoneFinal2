package com.example.pullokone;

    import android.widget.Toast;

    import java.util.ArrayList;

public class BottleDispenser {

        private int bottles;
        public ArrayList<Bottle> bottle_arraylist = new ArrayList<Bottle>();
        private double money;
        private String a;
        private String a2;
        private String a3;
        private String a4;
        private String a5;
        private int nspullo = 0;

        private static BottleDispenser kone = new BottleDispenser();

        public static BottleDispenser getInstance() {
            return kone;
        }



        private BottleDispenser() {

            bottles = 5;

            money = 0;

            // Initialize the array

            // Add Bottle-objects to the array
            //LUODAAN YKSI PULLO 0

            a = Integer.toString(nspullo);
            Bottle a = new Bottle("Pepsi Max", "Pepsi", 0.5, 1.8, 1);
            bottle_arraylist.add(a);


            //LUODAAN YKSI PULLO 1
            nspullo =+ 1;
            a2 = Integer.toString(nspullo);
            Bottle a2 = new Bottle("Pepsi Max", "Pepsi", 1.5, 2.2, 2);
            bottle_arraylist.add(a2);


            //LUODAAN YKIS PULLO 2

            nspullo =+ 1;
            a3 = Integer.toString(nspullo);
            Bottle a3 = new Bottle("Coca-Cola Zero", "Coca-Cola", 0.5, 2.0,3 );
            bottle_arraylist.add(a3);


            //LUODAAN YKIS PULLO 3

            nspullo =+ 1;
            a4 = Integer.toString(nspullo);
            Bottle a4 = new Bottle("Coca-Cola Zero", "Coca-Cola", 1.5, 2.5,4);
            bottle_arraylist.add(a4);

            //LUODAAN YKIS PULLO 4

            nspullo =+ 1;
            a5 = Integer.toString(nspullo);
            Bottle a5 = new Bottle("Fanta Zero", "Fanta", 0.5, 1.95,5);
            bottle_arraylist.add(a5);
        }




    //RAHAA LISÄÄ

        public void addMoney(double i) {

            money += i;

            System.out.println("Klink! Added more money!");
        }






    //OSTA PULLO

    public String buyBottle(int x) {
        int siirrin = x - 1;
        double y = bottle_arraylist.get(siirrin).getPrice();
        String temp = bottle_arraylist.get(siirrin).getName();
        if(money >= y) {
            bottles -= 1;
            money = money - y;
            removeBottle(siirrin);
            //System.out.println("KACHUNK! " + temp + " came out of the dispenser!");
            temp = "You bought! " + temp + " !";
        }else {
            temp = "Add money first!";
            //System.out.println(temp);
        }
        return temp;
    }





    //KERRO RAHATILANNE

        public void returnMoney() {

            if(money == 0) {
                System.out.println("Klink klink!! All money gone!");
            }else {
                System.out.println("Klink klink. Money came out!");
            }
        }




    //POISTAA PULLON KONEESTA KUN SE OSTETAAN

        public void removeBottle(int s) {
            bottle_arraylist.remove(s);
        }





    //KERTOO KONEEN SISÄLLÖN

        public void getBottles() {
            for(int i = 0;i<bottles;i++) {
                int h = i + 1;
                a = Integer.toString(i);
                System.out.print(h + ". Name: " + bottle_arraylist.get(i).getName()+"\n");
                System.out.println("\tSize: " + bottle_arraylist.get(i).getSize() + "\tPrice: " + bottle_arraylist.get(i).getPrice());
            }
        }





    //OTTAA RAHAT ULOS

        public double getMoney() {
            double temp;
            //System.out.printf("Klink klink. Money came out! You got %.2f€ back\n", money);
            temp = money;
            money = 0.0;
            return temp;
        }


}



