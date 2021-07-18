/*
=====================================================================================
SHOWCODE LEVEL-UP SOCIETY HACKATHON 2021 - Better Retail
Title: 
Contributors:  Abdullah Al Asif, Ahmad Saif, Fateen Tahseen Alam & Nazmul Hasan Fahim
Date: 18 July 2021
Purpose: 

Please refer to the README.txt for further explanation about the prototype program
=====================================================================================
*/

import java.io.*;
import java.util.*;

import javax.swing.text.Position;


public class mainProgram{

    public static void main (String [] args) throws IOException {
        
        introMsg();

        int ID = userLogin();
        mainMenu(ID);
        outroMsg();
        System.exit(0);

    }



    public static int userLogin() throws IOException{
        Scanner scanner = new Scanner(System.in);
        int UserID = 0;

        try{
            boolean invalidID = true;
            while(invalidID == true){
    
                System.out.println("Please enter your 6 digit User ID:");
                UserID = scanner.nextInt();
                    if(UserID < 100000 || UserID > 999999){
                        System.out.println();
                        System.out.println("Invalid Input!");
                        System.out.println("Hint: Enter any 6 digit number");
                        System.out.println();
                    }
                    else{
                        invalidID = false;
                    }
            }
            System.out.println();
        }
        catch(InputMismatchException e){
            errorMsg();
        }


        try{
            boolean invalidPIN = true;
            while(invalidPIN == true){
    
                System.out.println("Please enter your 4 digit User PIN:");
                int UserPIN = scanner.nextInt();
                    if(UserPIN < 1000 || UserPIN > 9999){
                        System.out.println();
                        System.out.println("Invalid Input!");
                        System.out.println("Hint: Enter any 4 digit number");
                        System.out.println();
                    }
                    else{
                        invalidPIN = false;
                    }
            }
        } catch (InputMismatchException e) {
            errorMsg();
        }
        return UserID;
    }

    public static void mainMenu(int ID) throws IOException{
        Scanner scanner = new Scanner(System.in);

        String userName = "John Doe";
        int currentPoints = 1510;   // A predefined number for the prototype program, actual values 
                                    // will be retrieved from a server

        System.out.println("");                           
        System.out.println("Welcome back, "+userName+"!   ID: "+ID);
        System.out.println("Points Available: "+currentPoints);
        System.out.println("");
        System.out.println("");

        System.out.println("Please select the size of plastic bottle you want to recycle.");
        System.out.println("0 : Skip Recycling");
        System.out.println("1 : 250 ml : 10 Points");
        System.out.println("2 : 500 ml : 15 Points");
        System.out.println("3 : 1 Litre : 20 Points");
        System.out.println("4 : 2 Litre++ : 25 Points");
        System.out.println("");
        
        
        boolean validNum = false;
        int bottleType = 0;

        try {
            while (validNum == false){
                System.out.println("");
                System.out.println("Enter number corresponding to the size of the bottle:");

                bottleType = scanner.nextInt();

                if (bottleType > 4 || bottleType <0){
                    System.out.println("Invalid Type, try again");
                    validNum = false;
                }
                else{
                    validNum = true;
                }
            }
    
        } catch (InputMismatchException e) {
            errorMsg();
        }

        System.out.println("");
        System.out.println("Bottle Type selected: "+bottleType);
        System.out.println("");


        int bottleNumber = 0;
        boolean numInvalid = true;

        try {
            while(numInvalid){
                System.out.println("How many bottles do you want to recycle? (Max 100 pcs)");
                bottleNumber = scanner.nextInt();

                if(bottleNumber < 1 || bottleNumber > 100){
                    System.out.println("Invalid Number, please try again");
                    numInvalid = true;
                }
                else{
                    numInvalid = false;
                }
            }
    
        } catch (InputMismatchException e) {
            errorMsg();
        }    

        System.out.println("");
        System.out.println("You are going to recycle "+ bottleNumber + " bottles");
        startProcessPrompt();
        wait3s();

        boolean bottleSubmit = false;

        if (bottleType == 1){
            currentPoints += bottleNumber*10;
            bottleSubmit = true;
        }
        else if(bottleType == 2){
            currentPoints += bottleNumber*15;
            bottleSubmit = true;
        }
        else if(bottleType == 3){
            currentPoints += bottleNumber*20;
            bottleSubmit = true;
        }
        else if(bottleType == 4){
            currentPoints += bottleNumber*25;
            bottleSubmit = true;
        }
        else{
            bottleSubmit = false;
        }
        System.out.println();
        System.out.println();

        if(bottleSubmit){
            System.out.println("Your bottles are submitted successfully!");
            System.out.println();
        }

        System.out.println("");
        System.out.println("Your available points: "+currentPoints);
        System.out.println("What do you want to do?");
        System.out.println("");
        System.out.println("1 : View Leaderboard");
        System.out.println("2 : Redeem Coupons");
        System.out.println("3 : Exit");

        boolean validChoice = false;
        int choice = 0;
        while(!validChoice){
            try {
                System.out.println("Enter your choice number:");
                choice = scanner.nextInt();

                if(choice < 1 || choice > 3){
                    validChoice = false;
                }
                else{
                    validChoice = true;
                }

            } catch (InputMismatchException e) {
                errorMsg();
            }
        }
        
        int position = 1;

        if(choice == 1){
            System.out.println("You have selected to view the Leaderboard");
            System.out.println("");
            System.out.println("");
            System.out.println("======================================");
            System.out.println("              LEADERBOARD");
            System.out.println("======================================");
            String filename = "leaderboard.csv";

            BufferedReader inputStream1 = new BufferedReader(new FileReader(filename));
            String leader = inputStream1.readLine();

            while(leader != null){
                String[] leaderComponents = leader.split(",");
                int leaderPoints = Integer.parseInt(leaderComponents[0]);

                if (leaderPoints > currentPoints){
                    System.out.println(position+ ": "+leaderComponents[1]+" with "+leaderPoints+ " Points");
                    position++;
                }

                leader = inputStream1.readLine();
            }
            inputStream1.close();

            System.out.println(position+": John with "+currentPoints+" Points (You)");
            position++;

            BufferedReader inputStream2 = new BufferedReader(new FileReader(filename));
            String leader2 = inputStream2.readLine();

            while(leader2 != null){
                String [] leaderComponents = leader2.split(",");
                int leaderPoints = Integer.parseInt(leaderComponents[0]);

                if (leaderPoints <= currentPoints){
                    System.out.println(position+ ": "+leaderComponents[1]+" with "+ leaderPoints+" Points");
                    position++;
                }
                leader2 = inputStream2.readLine();
            }
            inputStream2.close();
            System.out.println("         END OF THE LEADERBOARD");
            System.out.println("======================================");

        }
        else if(choice == 2){
            System.out.println("You have selected to redeem a coupon.");
            System.out.println("");
            System.out.println("You will receive a 5$ coupon for 1000 points");
            customPrompt("Press Enter key to continue");
            wait3s();
            System.out.println("");
            System.out.println("");
            System.out.println("Your Coupon Code is: 32XF3-FDS31-DUMMY-F2F11-1FS23");
            System.out.println("");
            System.out.println("Please receive the physical copy of your coupon from the printing bay.");
            System.out.println("");
            System.out.println("");
            try{
                Thread.sleep(1500);
            }
            catch(InterruptedException ex){
                Thread.currentThread().interrupt();
            }
        }
        else{
            System.out.println("You have selected to exit the session.");
            wait3s();
        }
    }





    public static void delay(int time){
        try{
            Thread.sleep(time);
        }
        catch(InterruptedException ex){
            Thread.currentThread().interrupt();
        }
    }

    public static void introMsg(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("WELCOME TO ----------");
        System.out.println();
        System.out.println("PRESS ENTER KEY TO ACCESS THE SYSTEM");
        String prompt = scanner.nextLine();
    }


    public static void outroMsg(){
        System.out.println("Thank you for taking part in saving planet Earth!");
    }

    public static void errorMsg(){
        System.out.println("");
        System.out.println("Critical input error, please restart the system.");
        System.out.println("Do not enter letters when number is asked.");
        System.out.println("");
        System.out.println("SESSION TERMINATED");
        System.exit(-1);
    }

    public static void startProcessPrompt(){
        System.out.println("Press Enter key to start processing");
        try{
            System.in.read();
        }
        catch(Exception e){}
    }
    public static void customPrompt(String prompt){
        System.out.println(prompt);
        try{
            System.in.read();
        }
        catch(Exception e){}
    }

    public static void wait3s(){
        System.out.print("Please wait");
        delay(1000);
        System.out.print(".");
        delay(1000);
        System.out.print(".");
        delay(1000);
        System.out.println(".");
    }
}