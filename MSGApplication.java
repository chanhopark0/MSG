package msgCase;

import java.io.*;
//import java.util.*;

public class MSGApplication {

    //Class variables--constants and parameters
    static final float WEEKS_IN_YEAR = (float) 52.0;
    static final float INTEREST_RATE = (float) 0.04;
    static final float MAXIMUM_PERC_OF_INCOME = (float) 0.28;
    static final int NUMBER_OF_MORTGAGE_PAYMENTS = 1560;

    //Class Variable-other
    private static float annualOperatingExpenses;

    //DATA ACCESS METHOD

    public static float getAnnualOperatingExpenses() {
        return annualOperatingExpenses;
    }


    //Class methods
    public static void setAnnualOperatingExpenses(float newAnnualOperatingExpenses) 
     //Validates new annual operating expenses, writes data to opexp.dat
     //for use the current execution
    {
        try {
            if (newAnnualOperatingExpenses >= 0.0) {
                File operatingExpensesFile = new File("opexp.dat");
                                                                //file containing annual operating expenses
                FileOutputStream newFileOutput = new FileOutputStream(operatingExpensesFile);
                                                                //output stream for newPrintStream
                PrintStream newFilePrint = new PrintStream(newFileOutput);
                                                                //to output newAnnualOperatingExpenses

                newFilePrint.println(newAnnualOperatingExpenses);
                newFilePrint.close();
                newFileOutput.close();
                annualOperatingExpenses = newAnnualOperatingExpenses;
            }
        } catch(Exception e) {
            System.out.println("Error: msgCase.MSGApplication.setAnnualOperatingExpenses ()");
            System.out.println("\t" + e);
        }
    } //setAnnualOperatingExpenses()
//---------------------------------------------------------------------------------------------------------------------------

    private static void initializeApplication()
    //InitializeS annual operating expenses from filw opexp.dat if it exists
    //If file does not exist, annual operating expenses are set to 0.0 by calling
    //setAnnuealOperatingExpenses
    {
        try {
            File operatingExpensesFile = new File("opexp.dat"); //file containing opexp.dat
            if (!operatingExpensesFile.exists()) {
                setAnnualOperatingExpenses((float) 0.0);
            } else {
                RandomAccessFile inFile = new RandomAccessFile(operatingExpensesFile, "r");
                char c; //character enter by user 
                StringBuffer input = new StringBuffer();
                                           //buffer for line of characters
                while ((c = (char) inFile.read()) != '\n') {
                    input.append(c);
                }
                Float tempFloat = Float.parseFloat(input.toString());
                annualOperatingExpenses = tempFloat.floatValue();
                inFile.close();
            }
        } catch (Exception e) {
            System.out.println("Error msgCase.MSGApplication.initializeApplication ()");
            System.out.println("\t" + e);
        }
    } // initializeApplication()
//-----------------------------------------------------------------------------------------------------------------------------
//MSW MAIN METHOD 
    public static void main(String args[]) {
        initializeApplication();
        MSGUtilities.displayMainMenu();
    } // main()
    
}
