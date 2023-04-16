package msgCase;

import java.io.*;
//import java.util.*;

public class MSGApplication {

    //Class variables--constants and parameters
    static final float WEEKS_IN_YEAR = (float) 52.0;
    static final float INTEREST_RATE = (float) 0.04;
    static final float MAXIMUM_PERC_OF_INCOME = (float) 0.28;
    static final int NUMBER_OF_MORTGAGE_PAYMENTS = 1560;


//-----------------------------------------------------------------------------------------------------------------------------
//MSW MAIN METHOD 
    public static void main(String args[]) {
        AnnualOperatingExpenses.initializeApplication();
        MSGUtilities.displayMainMenu();
    } // main()
    
}
