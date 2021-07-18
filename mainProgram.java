/*
=========================================================================================================
SHOWCODE LEVEL-UP SOCIETY HACKATHON 2021 - Better Retail
Title: 
Contributors: Abdullah Al Asif, Abrar Mahbub Tanim, Ahmad Saif, Fateen Tahseen Alam & Nazmul Hasan Fahim
Date: 18 July 2021
Purpose: A gamified plastic bottle collection system which award points to the users. The points are used
         to stay at the top of leaderboard of a great community of people who help protect the world, and 
         points can also be consumed to get coupons for supported stores and marketplaces.

**Please refer to the ReadmeProto.txt for further explanation about the prototype program's source code
=========================================================================================================
*/

// Importing required Java libraries
//
import java.io.*;
import java.util.*;

// The mainProgram class
//
public class mainProgram{

    // main method
    //
    public static void main (String [] args) throws IOException {        
        introMsg();
        int ID = userLogin();
        mainMenu(ID);
        outroMsg();
        System.exit(0);
    }
    // END of main method


    // Method for user login using ID and PIN, which returns the ID
    //
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
                        System.out.println("Hint: Enter any 6 digit number, first digit cannot be 0");
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
                        System.out.println("Hint: Enter any 4 digit number, first digit cannot be 0");
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
    // END of userLogin


    // Method for the Menu which is displayed after login until the end of the session
    //
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

        // Displaying options for deposit
        System.out.println("Please select the size of plastic bottle you want to recycle.");
        System.out.println("0 : Skip Recycling");
        System.out.println("1 : 250 ml : 10 Points");
        System.out.println("2 : 500 ml : 15 Points");
        System.out.println("3 : 1 Litre : 20 Points");
        System.out.println("4 : 2 Litre++ : 25 Points");
        System.out.println("");
              
        boolean validNum = false;
        int bottleType = 0;

        // Selection of options
        try {
            while (validNum == false){
                System.out.println("");
                System.out.println("Enter number corresponding to the size of the bottle:");

                bottleType = scanner.nextInt();

                if (bottleType > 4 || bottleType <0){
                    System.out.println("Invalid Option, try again");
                    validNum = false;
                }
                else{
                    validNum = true;
                }
            }
    
        } catch (InputMismatchException e) {
            errorMsg();
        }

        // Confirmation of the number of bottles to be recycled
        System.out.println("");
        System.out.println("Bottle Type selected: "+bottleType);
        System.out.println("");

        int bottleNumber = 0;
        boolean numInvalid = true;

        // Entering the number of bottles
        if (bottleType == 0){
            System.out.println("You have skipped recycling bottles for this session");
        }
        if (bottleType != 0){
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
        }

        // Modifying the value of currentPoints based on the number and type of bottles submitted
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

        // Displaying the end screen menu which have options to view leaderboard, get coupons or exit
        boolean goBack = true;
        while(goBack){
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

        // Displaying the Leaderboard
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
            System.out.println("======================================");
   
            try{
                Thread.sleep(3000);
            }
            catch(InterruptedException ex){
                Thread.currentThread().interrupt();
            }
            System.out.println();
            System.out.println();
            System.out.println("Do you want to go to the previous menu? y/n");
            scanner.nextLine();
            String answer = scanner.nextLine();
            if (answer.equals("y")){
                goBack = true;
            }
            else{
                goBack = false;
            }
            System.out.println();
        }

        // Redeeming a coupon
        else if(choice == 2){
            scanner.nextLine();
            System.out.println("You have selected to redeem a coupon.");
            System.out.println("");
            System.out.println("You will receive a 5$ coupon for 1000 points");
            System.out.println("Press 1 to continue, 0 to Cancel");
            String prompt = scanner.nextLine();

            if(currentPoints < 1000){
                System.out.println("You don't have enough points.");
            }
            else if (prompt.equals("1")){
                wait3s();
                System.out.println("");
                System.out.println("");
                String coupon = couponGenerate();
                System.out.println("Your Coupon Code is: "+coupon);
                currentPoints -= 1000;
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
                System.out.println("Do you want to go to the previous menu? y/n");
                String answer = scanner.nextLine();
                if (answer.equals("y")){
                    goBack = true;
                }
                else{
                goBack = false;
                }
            }
        }

        // Exiting the system
        else{
            System.out.println("You have selected to exit the session.");
            goBack = false;
            wait3s();
        }
    }    
    }// END of mainMenu


    // Method for generating coupons
    //
    public static String couponGenerate(){
        Random ran = new Random();
        int x = ran.nextInt(5) + 1;

        if(x == 1){
            return "S4HG0-V2AJ3-DUMMY-SA3B8-DS10A";
        }
        else if(x == 2){
            return "F134V-FS12F-DUMMY-B12JF-SB31B";
        }
        else if(x == 3){
            return "13VCV-2SJV0-DUMMY-FH30N-SD91H";
        }
        else if(x ==4){
            return "H34FC-SA41G-DUMMY-GN328-GJA35";
        }
        else{
            return "F2EWG-GW23G-DUMMY-2JVW9-FW90H";
        }
    }
    // END of couponGenerate


    //Method to add a custom delay
    //
    public static void delay(int time){
        try{
            Thread.sleep(time);
        }
        catch(InterruptedException ex){
            Thread.currentThread().interrupt();
        }
    }
    //END of delay


    // Intro, Outro and Error message methods
    //
    public static void introMsg(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("WELCOME TO ----------");
        System.out.println();
        System.out.println("PRESS ENTER KEY TO ACCESS THE SYSTEM");
        String prompt = scanner.nextLine();
    }
    public static void outroMsg(){
        System.out.println();
        System.out.println("Thank you for taking part in saving planet Earth!");
        System.out.println();
        System.out.println();
    }
    public static void errorMsg(){
        System.out.println("");
        System.out.println("Critical input error, please restart the system.");
        System.out.println("Do not enter letters when number is asked.");
        System.out.println("");
        System.out.println("SESSION TERMINATED");
        System.exit(-1);
    }
    //END of message methods

    //Processing prompt, Custom prompt and 3 second wait prompt
    //
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
    //END of prompt methods

}
//END of mainProgram