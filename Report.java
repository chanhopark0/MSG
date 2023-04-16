package msgCase;

import java.io.File;
import java.io.RandomAccessFile;

public class Report {
    public void produceInvestmentReport() {
        try {
            File investmentFile = new File("investment.dat");
            int i = 0;
            if (investmentFile.exists()) {
                RandomAccessFile inFile = new RandomAccessFile(investmentFile, "r");
                while (inFile.getFilePointer() != inFile.length()) {
                    if (((i % 4) == 0) && (i != 0)) {
                        System.out.println("\n\nPress <ENTER> to view the next screen...");
                        System.in.read();
                    }
                    if ((i % 4) == 0) {
                        MSGUtilities.clearScreen();
                        System.out.println("\n\n\t Martha Stockton Greengage Foundation");
                        System.out.println("\tINVESTMENT REPORT\n");
                    }
                    System.out.println("--------------------------------------------------------");
                    Investment	inv = new Investment ();	// investment record
                    inv.readRecord(inFile);
                    inv.displayRecord();
                    i++;
                }
                inFile.close();
            } else {
                System.out.println("\nNo investments currently exist.");
            }
            System.out.println("\n\n Press ENTER to continue ...");
            int c;
            c = System.in.read();
            System.out.println (c);
        } catch (Exception e) {
            System.out.println("Error: Investment.produceInvestmentReport()");
            System.out.println("\t" + e);
        }
    } // produceInvestmentReport()
    //-----------------------------------------------------------------------------------------

    public void produceMortgageReport (){
        try
        {
            File	mortgageFile = new File ("mortgage.dat");// file of mortgage records
            int	i = 0;	// used for screen clearing
            if (mortgageFile.exists ())
            {
                RandomAccessFile inFile = new RandomAccessFile (mortgageFile, "r");
                while (inFile.getFilePointer () != inFile.length ())
                {
                    //
                    // pause the screen after every three mortgages
                    //
                    if (((i %2) == 0) && (i !=0))
                    {
                        System.out.println ();
                        System.out.println ();
                        System.out.println (" Press <ENTER> to view the next screen...");
                        System.in.read();
                    }
                    //
                    // display a header message after every third mortgage
                    //
                    if ((i % 2) == 0)
                    {
                        MSGUtilities.clearScreen();

                        System.out.println ();
                        System.out.println ();
                        System.out.println ("\t Martha Stockton Greengage Foundation");
                        System.out.println ("\t MORTGAGE REPORT \n");
                    }
                    System.out.println ("---------------------------------------------------");
                    Mortgage	mort = new Mortgage ();	// mortgage record
                    mort.readRecord (inFile);
                    mort.displayRecord ();
                    i++;
                }
                inFile.close ();
            }
            else
            {
                System.out.println ("\n No mortgages currently exist.");
            }
            System.out.println ("\n\n Press ENTER to continue ...");

            int c = System.in.read ();

            System.out.println (c);

        }
        catch (Exception e)
        {
            System.out.println ("***************Error Mortgage.produceMortgageReport ()************");
            System.out.println ("\t" + e);
        }

    }

    //-----------------------------------------------------------------------------------------
    //Computes total funds available for porchasing new mortgages in the current week
    public static void fundsAvailable() {
        try {
            float expectedWeeklyInvestmentReturn = (float) 0.0;
            //EXPECTED WEEKLY INVESTMENT RETURN
            float expectedTotalWeeklyNetPayments = (float) 0.0;
            //EXPECTED  TOTAL MORTAGE PAYMENTS LESS TOTAL WEEKLY GRANTS

            msgCase.Investment	Inv = new msgCase.Investment ();	// investment record
            Mortgage	mort = new Mortgage ();	// mortgage record
            expectedWeeklyInvestmentReturn = Inv.totalWeeklyReturnOnInvestment();
            expectedTotalWeeklyNetPayments = mort.totalWeeklyNetPayments ();
            System.out.println("Funds available:"  + (expectedWeeklyInvestmentReturn -
                    (msgCase.AnnualOperatingExpenses.getAnnualOperatingExpenses () /
                            msgCase.MSGApplication.WEEKS_IN_YEAR) +
                    expectedTotalWeeklyNetPayments));
            System.out.println ("\n\n\n Press ENTER to continue ...");
            int	c;
            c = System.in.read ();
        }
        catch (Exception e)
        {
            System.out.println ("***** Error: msgCase.MSGUtilities.fundsAvailable()********** ");
            System.out.println ("\t" + e);
        }
    } //fundsAvailable ()
//-----------------------------------------------------------------------------------------
} // Class Report()
