package msgCase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.RandomAccessFile;

public class AnnualOperatingExpenses {
    private static float annualOperatingExpenses;

    public static float getAnnualOperatingExpenses() {
        return annualOperatingExpenses;
    }

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

    //-------------------------------------------------------------
    //OBTAINS the new annual operating expenses from user, validates, and then writes the data to the operating expenses file
    public static void updateAnnualOperatingExpenses() {

        try {
            char c;
            StringBuffer input;
            float newAnnualOperatingExpenses;

            MSGUtilities.clearScreen();
            System.out.println("\nEnter new annual operating expenses:");
            input = new StringBuffer ();
            while((c = (char) System.in.read ()) != '\n') {
                input.append (c);
            }
            Float tempFloat = Float.parseFloat(input.toString());
            newAnnualOperatingExpenses = tempFloat.floatValue();
            setAnnualOperatingExpenses(newAnnualOperatingExpenses);
        }
        catch (Exception e) {
            System.out.println ("Error: MSGUtil.updateAnnualOperatingExpenses()");
            System.out.println ("\t" + e);
        }
        // try {
        //     char c;
        //     StringBuffer input;
        //     float newAnnualOperatingExpenses;

        //     MSGUtilities.clearScreen();
        //     System.out.println("\nEnter new annual operating expenses:");
        //     input = new StringBuffer ();
        //     while((c = (char) System.in.read ()) != '\n') {
        //         input.append (c);
        //     }
        //     Float tempFloat = Float.parseFloat(input.toString());
        //     newAnnualOperatingExpenses = tempFloat.floatValue();
        //     MSGApplication.setAnnualOperatingExpenses(newAnnualOperatingExpenses);
        // }
        // catch (Exception e) {
        //     System.out.println ("Error: MSGUtil.updateAnnualOperatingExpenses()");
        //     System.out.println ("\t" + e);
        // }
    } // updateAnnualOperatingExpenses()

//-------------------------------------------------------------

    public static void initializeApplication()
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

}

