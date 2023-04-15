package msgCase;

import java.io.File;
import java.io.RandomAccessFile;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Calendar;

public class Mortgage extends FinancialInstrument
{
        // Instance variables
    private String	mortgageID;	// mortgage ID (stored as chars)
    private String	mortgageeName;	// names of mortgagees
    private float	price;	// price paid for mortgaged property
    private String	dateMortgageIssued;	// date mortgage was issued
    private float	currentWeeklyIncome;	// weekly income of mortgagees
    private String weeklyIncomeUpdated;	// date weekly income updated
    private float	annualPropertyTax;	// annual property tax
    private float	annualInsurancePremium;	// annual insurance premium
    private float	mortgageBalance;	// mortgage balance
    // Data access methods
    public String getMortgageID() {return mortgageID;}
    public String getMortgageeName() {return mortgageeName; }
    public float getPrice() { return price; }
    public String getDateMortgageIssued() {return dateMortgageIssued; }
    public float getCurrentWeeklyIncome() {return currentWeeklyIncome; }
    public String getWeeklyIncomeUpdated() {return weeklyIncomeUpdated; }
    public float getAnnualPropertyTax() {return annualPropertyTax; }
    public float getAnnualInsurancePremium() { return annualInsurancePremium; }
    public float getMortgageBalance() { return mortgageBalance; }
    // Instance methods


    //------------------------------------------------------------------------------------
    public float totalWeeklyNetPayments() {
        // Computes the net total weekly payments made by the mortgagees, that is,
        // the expected total weekly mortgage amount less the expected total weekly grants.
        try {
            File mortgageFile = new File("mortgage.dat");
            float expectedTotalWeeklyMortgages = (float) 0.0;
            float expectedTotalWeeklyGrants = (float) 0.0;
            float capitalRepayment = (float) 0.0;
            float interestPayment = (float) 0.0; // interest payment
            float escrowPayment = (float) 0.0; // escrow payment
            float tempMortgage = (float) 0.0; // temporary mortgage payment
            float maximumPermittedMortgagePayment = (float) 0.0; // maximum a family is allowed to pay
            if (mortgageFile.exists()) {
                RandomAccessFile inFile = new RandomAccessFile(mortgageFile, "r");
                while (inFile.getFilePointer() != inFile.length()) {
                    readRecord(inFile);
                    capitalRepayment = price/MSGApplication.NUMBER_OF_MORTGAGE_PAYMENTS;
                    interestPayment = mortgageBalance*MSGApplication.INTEREST_RATE/MSGApplication.WEEKS_IN_YEAR;
                    escrowPayment = (annualPropertyTax + annualInsurancePremium)/MSGApplication.WEEKS_IN_YEAR;
                    tempMortgage = capitalRepayment + interestPayment + escrowPayment;
                    expectedTotalWeeklyMortgages += tempMortgage;
                    maximumPermittedMortgagePayment = currentWeeklyIncome * MSGApplication.MAXIMUM_PERC_OF_INCOME;
                    if (tempMortgage > maximumPermittedMortgagePayment)
                        expectedTotalWeeklyGrants += tempMortgage - maximumPermittedMortgagePayment;
                }
                inFile.close();
            } // if
            return (expectedTotalWeeklyMortgages - expectedTotalWeeklyGrants);
        } catch (Exception e) {
            System.out.println("Error Mortgage.totalWeeklyNetPayments()");
            System.out.println("\t" + e);
            return ((float) 0.0);
        }
    } //totalWeeklyNetPayments
    //------------------------------------------------------------------------------------
    public boolean findRecord(String findMortgageID) {
        // findRecord locates a given mortgage record if it exists.
        // Returns true if the mortgage is located, otherwise false.
        try {
            File mortgageFile = new File ("mortgage.dat");
            boolean	found = false;
            if (mortgageFile.exists()) {
                RandomAccessFile inFile = new RandomAccessFile (mortgageFile, "r");
                while (!found && (inFile.getFilePointer () != inFile.length())) {
                    readRecord (inFile);
                    if (mortgageID.compareTo (findMortgageID) == 0) found = true;
                }
                inFile.close();
            }
            return found;
        }
        catch (Exception e) {
            System.out.println("Error Mortgage.findRecord () ");
            System.out.println("\t" + e);
            return false;
        }
    } //findRecord

    //------------------------------------------------------------------------------------

    public void readRecord(RandomAccessFile fileName)
     {
        // readRecord reads a mortgage record from fileName.
        // Assumes that the existence o/fileName has already been established
      try {
            String	inputString = new String ();
            int	i = 0;
            inputString = fileName.readLine();
            StringBuffer input = new StringBuffer();
            while(inputString.charAt(i) != '|') {
                input.append(inputString.charAt(i));
                i++;
            }
            mortgageID = input.toString();
            i++;
            input = new StringBuffer ();
            while(inputString.charAt (i) != '|') {
                input.append (inputString.charAt (i));
                i++;
            }
            mortgageeName = input.toString();
            i++;
            input = new StringBuffer();
            while (inputString.charAt (i) != '|') {
                input.append (inputString.charAt(i));
                i++;
            }
                Float tempFloat = Float.parseFloat(input.toString());
                price = tempFloat.floatValue();
                i++;
                input = new StringBuffer();
        while (inputString.charAt(i) != '|')
        {
        input.append (inputString.charAt(i));
        i++;
        }
        String readRecordinput = input.toString();
//        DateFormat formatter = new SimpleDateFormat();
        dateMortgageIssued = readRecordinput;
        
        //dateMortgageIssued = new Date ();


        i++;
        
        input = new StringBuffer ();
        while (inputString.charAt(i) != '|')
        {
        input.append (inputString.charAt(i));
        i++;
        }
        tempFloat = Float.parseFloat(input.toString ());
        currentWeeklyIncome = tempFloat.floatValue();
        i++;
        
        input = new StringBuffer ();
        while (inputString.charAt (i) != '|')
        {
        input.append (inputString.charAt(i));
        i++;
        }

        String readweeklyIncomeUpdatedinput = input.toString();
//        DateFormat formatterWIU = new SimpleDateFormat("MM/dd/yyyy");
        weeklyIncomeUpdated = readweeklyIncomeUpdatedinput;
        
        //weeklyIncomeUpdated = new Date (input.toString ());
        i++;

        input = new StringBuffer ();
        while (inputString.charAt (i) != '|')
        {
        input.append (inputString.charAt(i));
        i++;
        }
        tempFloat = Float.parseFloat(input.toString());
        annualPropertyTax = tempFloat.floatValue();
        i++;

        input = new StringBuffer();
        while (inputString.charAt(i) != '|'){
                input.append (inputString.charAt(i));
                i++;
        }
        tempFloat = Float.parseFloat(input.toString());
        annualInsurancePremium = tempFloat.floatValue();
        i++;
        input = new StringBuffer();
        
        while (i < inputString.length()){
                input.append(inputString.charAt(i));
                i++;
        
        }
        
        tempFloat = Float.parseFloat(input.toString ());
        mortgageBalance = tempFloat.floatValue ();
        i++;
      }
        catch (Exception e){
                System.out.println("**************88 Error: Mortgage.readRecord () **************8");
                System.out.println ("\t" + e);
        

        }
        
   } //readRecord 
   
   //------------------------------------------------------------------------------------

   public void writeRecord (RandomAccessFile fileName)
//
// readRecord writes a mortgage record to specified file
       {
        try
        {
        fileName.writeBytes (mortgageID + "|" + mortgageeName + "|");
        fileName.writeBytes (price + "|" + dateMortgageIssued.toString() + "|");
        fileName.writeBytes (currentWeeklyIncome + "|" + weeklyIncomeUpdated.toString() + "|");
        fileName.writeBytes (annualPropertyTax + "|" + annualInsurancePremium + "|");
        fileName.writeBytes (mortgageBalance + "\n");
        }
        catch (Exception e)
        {
        System.out.println("************** Error Mortgage.writeRecord() ********************************");
        System.out.println ("\t" + e);
        }
        } //writeRecord
   
   //------------------------------------------------------------------------------------

   public void displayRecord ()
//
// displayRecord displays the names of the fields of a mortgage record and their values.
        
        {
        System.out.print ("Mortgage ID:" + mortgageID + "\n");
        System.out.println ("Mortgagee Name:" + mortgageeName);
        System.out.println ("Price :" + price);
        System.out.print ("Date mortgage issued:" + dateMortgageIssued + "\n");

//        Calendar dateMortgageIssued = Calendar.getInstance();
        //cal.add(Calendar.DATE, -1);
        


        //System.out.print ((dateMortgageIssued.getMonth () + 1) + "/");
//        System.out.print ((dateMortgageIssued.get(Calendar.MONTH) + 1) + "/");

        // System.out.print (dateMortgageIssued.getDate() + "/");
//        System.out.print ((dateMortgageIssued.get(Calendar.DATE) + "/"));

        //System.out.println (dateMortgageIssued.getYear());
//        System.out.println (dateMortgageIssued.get(Calendar.YEAR));

        System.out.println ("Current weekly income: " + currentWeeklyIncome);

//        Calendar weeklyIncomeUpdated = Calendar.getInstance();

        
        // System.out.print (" Date weekly income updated: ");
        // System.out.print ((weeklyIncomeUpdated.getMonth() + 1) + "/");
        // System.out.print (weeklyIncomeUpdated.getDate() + "/");
        // System.out.println (weeklyIncomeUpdated.getYear());
        // System.out.print ("Annual property tax:" + annualPropertyTax);
       
       
        System.out.print ("Date weekly income updated: " + weeklyIncomeUpdated + "\n");
//        System.out.print ((weeklyIncomeUpdated.get(Calendar.MONTH) + 1) + "/");
//        System.out.print (weeklyIncomeUpdated.get(Calendar.DATE) + "/");
//        System.out.println (weeklyIncomeUpdated.get(Calendar.YEAR));
        System.out.print ("Annual property tax:" + annualPropertyTax + "\n");
        
        
        System.out.println ("Annual insurance premium:" + annualInsurancePremium);
        System.out.println ("Mortgage balance: " + mortgageBalance);
        }


        //_________-__________________________________________________________________--\

        public void obtainNewData ()
                        //
                        // ObtainNewData reads a new mortgage record by calling readMortgageData.
                        //
                                {
                                readMortgageData ();
                                } //obtainNewData
 

        //-----------------------------------------------------------------------------------------
        public void performDeletion ()
//
// performDeletion performs the actual deletion of a mortgage record from a file.
//
        {
        try
        {
        File	mortgageFile = new File("mortgage.dat");// file of mortgage records
        File	tempMortgageFile = new File ("mortgage.tmp");
// temporary file of mortgage records
        Mortgage	mort = new Mortgage ();	// record to be checked
        if (!mortgageFile.exists ()) return;
        RandomAccessFile inFile = new RandomAccessFile (mortgageFile, "r");
        RandomAccessFile outFile = new RandomAccessFile (tempMortgageFile, "rw");
        while (inFile.getFilePointer() != inFile.length())
        {
        mort.readRecord (inFile);
        if (mortgageID.compareTo (mort.getMortgageID ()) != 0)
        {
        mort.writeRecord (outFile);
        }
        }
        inFile.close ();
        outFile.close ();
        mortgageFile.delete ();
        tempMortgageFile.renameTo (mortgageFile);
        } 
        catch (Exception e){
                System.out.println ("**********Error: Mortgage.performDeletion()*********");
                System.out.println ("\t" + e);
        }
       } //pertormDeletion

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
        readRecord (inFile);
        displayRecord ();
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

       public void putRecord ()
        //
        // putRecord writes an individual mortgage record into a file, ordered by mortgagelD.
        //
        {
        try
        {
        File	mortgageFile = new File ("mortgage.dat");// file of mortgage records
        File	tempMortgageFile = new File ("mortgage.tmp");//temporary file of mortgage records
        Mortgage	mort = new Mortgage();// H record read, then written
        boolean	found = false;	// terminates while-loop
        RandomAccessFile newFile = new RandomAccessFile (tempMortgageFile, "rw");
        if(!mortgageFile.exists ()){
        writeRecord (newFile);
        }

        else{
                RandomAccessFile oldFile = new RandomAccessFile (mortgageFile, "r");
                int	compareMortgages;	//tofind correct placefor the new mortgage
                
                while (oldFile.getFilePointer () != oldFile.length()){
                        
                        mort.readRecord(oldFile);
                        compareMortgages = mortgageID.compareTo (mort.getMortgageID());
                        if ((!found) && (compareMortgages < 0))
                        {
                        writeRecord (newFile);
                        mort.writeRecord (newFile);
                        found = true;
                        }
                        else if (compareMortgages == 0){
                        writeRecord (newFile);
                        found = true;
                        }
                        else
                        {
                        mort.writeRecord(newFile);
                        }
                        

                }//while
        
        if (!found) writeRecord (newFile);
        oldFile.close ();
        }
        newFile.close();
        mortgageFile.delete ();
        tempMortgageFile.renameTo (mortgageFile);
        }
        catch (Exception e)
        {
        System.out.println ("************* Error Mortgage.putRecord () *********** ");
        System.out.println ("\t" + e);
        }
        } //putRecord

        //-----------------------------------------------------------------------------------------
        public void readMortgageData(){
        //
        // readMortgageData asks user to enter information for the fields of a mortgage.
        //
        try
                {
                char c; // character entered by user
                StringBuffer input = new StringBuffer ();	// buffer for line of characters
                boolean valid = false;	// used to validate length of ID
                	
                
               
                while (!valid)
                {
                System.out.println ("Enter mortgage number (12 digits):");
                input = new StringBuffer ();
                while ((c = (char) System.in.read ()) != '\n')
                {
                input.append (c);
                }
                valid = (input.toString ().length() <= 12);
                if (!valid)
                System.out.println ("\n\nThe mortgage number must be 12 digits.");
                }
                mortgageID = input.toString ();
                System.out.println ("Enter mortgagee name: ");
                input = new StringBuffer ();
                while ((c = (char) System.in.read ()) != '\n')
                {
                
                input.append (c);
                }
                mortgageeName = input.toString ();
                System.out.println ("Enter price of home:");
                input = new StringBuffer ();
                while ((c = (char) System.in.read ()) != '\n')
                {
                input.append (c);
                }
                Float tempFloat = Float.parseFloat(input.toString());
                price = tempFloat.floatValue ();
                System.out.println ("Enter date mortgage was issued (MM/dd/yyyy):");
                input = new StringBuffer ();
                while ((c = (char) System.in.read ()) != '\n')
                {
                input.append (c);
                }
//                dateMortgageIssued = MSGUtilities.parseDate (input.toString());
                dateMortgageIssued = input.toString();
                System.out.println ("Enter current weekly income:");
                input = new StringBuffer ();
                while ((c = (char) System.in.read ()) != '\n')
                {
                input.append (c);
                }
                tempFloat = Float.parseFloat(input.toString ());
                currentWeeklyIncome = tempFloat.floatValue();
                Date now = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                String strDate = sdf.format(now);
                weeklyIncomeUpdated = strDate;
                System.out.println ("Enter annual property tax:");
                input = new StringBuffer ();
                while ((c = (char) System.in.read ()) != '\n')
                {
                input.append (c);
                }
                tempFloat = Float.parseFloat(input.toString());
                annualPropertyTax = tempFloat.floatValue ();
                System.out.println ("Enter mortgage balance:");
                input = new StringBuffer ();
                while ((c = (char) System.in.read ()) != '\n')
                {
                input.append (c);
                }
                tempFloat = Float.parseFloat(input.toString());
                mortgageBalance = tempFloat.floatValue ();
                }
                catch (Exception e){
                System.out.println ("********Error: Mortgage.readMortgageData()****************");
                System.out.println ("\t" + e);

                }
         } //readMortgageData


       //-----------------------------------------------------------------------------------------

                        
                public void updateRecord ()
                // 
                // updateRecord modifies a mortgage record.
                // 
                {
                try { char option = '0';// # of field to be changed
                      boolean done = false;// terminates whlle-loop
                      boolean   found = false;// tells if mortgage is found c;
                      char c; // character entered by user
                      StringBuffer   input = new StringBuffer (); // buffer for line of characters
                      byte	choice[] = new byte[10];	// user's choice
                                while (!found && !done)
                                {

                                
                                        System.out.println ("Please enter the number of the mortgage to be" +
                                        "updated (12 digits):");
                                        input = new StringBuffer ();
                                        while ((c = (char) System.in.read()) != '\n')
                                        {
                                        input.append (c);
                                        }
                                        found = findRecord (input.toString());
                                        
                                        if (!found)
                                        {
                                                System.out.println ("Mortgage" + input.toString () + " was not found.");
                                                System.out.println ("Would you like to enter another mortgage?");
                                                System.in.read (choice);
                                                if (((char) choice[0]) == 'n')
                                                {
                                                        done = true;
                                                        }
                                        }
                                }
                
                if (!found)
                {
                return;
                }
                while (!done)
                {
                while (!done)
                {
                MSGUtilities.clearScreen ();
                System.out.println ("\t UPDATE MORTGAGES\n\n");
                System.out.println ("\t MARTHA STOCKTON GREENGAGE FOUNDATIONS");
                System.out.println ("\t 1.Update mortgagee name\n");
                System.out.println ("\t  2.Update price of home\n");
                System.out.println ("\t 3.Update date mortgage issued\n");
                System.out.println ("\t 4.Update current weekly income");
                System.out.println ("\t 5.Update property tax\n");
                System.out.println ("\t 6.Update insurance premium\n");
                System.out.println ("\t  7.Update mortgage balanced\n");
                System.out.println ("\t 8.Exit to mortgage menu\n\n");
                System.out.println ("\t Enter your choice and press <ENTER>: ");
                
                                try
                                {
                                System.in.read (choice);
                                switch ( (char) choice[0])
                                {
                                case '1':
                                        option = '1';
                                        break;
                                case '2':
                                        option = '2';
                                        break;
                                case '3':
                                        option = '3';
                                        break;
                                case '4':
                                        option = '4';
                                        break;
                                case '5':
                                        option = '5';
                                        break;
                                case '6':
                                        option = '6';
                                        break;
                                case '7':
                                        option = '7';
                                        break;
                                case '8':

                                case '\n':
                                        done = true;
                                        break;
                                default:
                                        System.out.println ("\n\nNot a valid choice\n");
                                        System.out.println ("Press <ENTER> to return to menu...");
                                        System.in.read (choice);
                                        break;
                                }
                        }
                                catch (Exception e){
                                        System.out.println ("**********Error: Mortgage.updateRecord ()***********");
                                        System.out.println ("\t" + e);

                                }
                
                if(!done)
                {
                changeMortgageField (option);
                displayRecord ();
                System.out.println("\n\n Press ENTER to continue.. ");
                c = (char) System.in.read ();
                }
                }
                }
                putRecord ();
                }
        catch (Exception e)
                {
                System.out.println ("************ Error: Mortgage.updateRecord () ***********");
                System.out.println ("\t" + e);
                }
                
                 }       // updateRecord


        //-----------------------------------------------------------------------------------------

        public void changeMortgageField (char mortgageFieldNumber)
           {
                try
                        {
                        char c; // character entered by user
                        StringBuffer input = new StringBuffer (); // buffer for line of characters
                        Float tempFloat;
                        
                        switch (mortgageFieldNumber)
                        {
                        case '1':
                                System.out.println ("\n\n Old mortgagee name: " + mortgageeName);
                                System.out.println ("Enter new mortgagee name:");
                                while((c =(char) System.in.read ()) != '\n')
                                {
                                input.append (c);
                                }
                                mortgageeName = input.toString();
                                break;
                        case '2':
                                System.out.println ("\n\nOld price:" + price);
                                System.out.println ("Enter new price of home:");
                                while ((c = (char) System.in.read ()) != '\n')
                                {
                                input.append (c);
                                }
                                tempFloat = Float.parseFloat(input.toString ());
                                price = tempFloat.floatValue ();
                                break;
                        case '3':

                                
                                System.out.print ("\n\nOld mortgage date: ");

                                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                                Date pasedDate = sdf.parse(dateMortgageIssued);

                                Calendar cal = new GregorianCalendar();
                                cal.setTime (pasedDate);
                                // System.out.print ((dateMortgageIssued.getMonth () + 1) + "/");
                                // System.out.print (dateMortgageIssued.getDate () + "/");
                                
                                // System.out.println (dateMortgageIssued.getYear ());
                                
                                System.out.print ((cal.get(Calendar.MONTH) + 1) + "/");
                                System.out.print (cal.get(Calendar.DATE) + "/");
                                
                                System.out.println (cal.get(Calendar.YEAR));
                                
                                System.out.println ("Enter date the mortgage was issued (MM/dd/yyyy):");
                                while ((c = (char) System.in.read ()) != '\n')
                                {
                                input.append (c);
                                }
                                dateMortgageIssued = input.toString();
                                break;
                        case '4':
                                System.out.println ("\n\nOld weekly income;" + currentWeeklyIncome);
                                System.out.println ("Enter current weekly income:");
                                while ((c = (char) System.in.read()) != '\n')
                                {
                                input.append (c);
                                }
                                tempFloat = Float.parseFloat(input.toString());
                                currentWeeklyIncome = tempFloat.floatValue();
                                Date now = new Date();
                                SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");
                                String strDate = sdf2.format(now);
                                weeklyIncomeUpdated = strDate;
                                break;
                        case '5':
                                System.out.println ("\n\nOld annual property tax:" + annualPropertyTax);
                                System.out.println ("Enter new annual property tax:");
                                while ((c = (char) System.in.read ()) != '\n')
                                {
                                input.append (c);
                                }
                                tempFloat = Float.parseFloat(input.toString ());
                                annualPropertyTax = tempFloat.floatValue();
                                break;
                        case '6':
                                System.out.println ("\n\nOld insurance premium:" + annualInsurancePremium);
                                System.out.println ("Enter new annual insurance premium:");
                                while ((c = (char) System.in.read()) != '\n')
                                {
                                input.append (c);
                                }
                                tempFloat = Float.parseFloat(input.toString());
                                annualInsurancePremium = tempFloat.floatValue ();
                                break;
                        case '7':
                                System.out.println ("\n\nOld mortgagee balance;" + mortgageBalance);
                                System.out.println ("Enter new mortgage balance:");
                                while ((c = (char) System.in.read ()) != '\n')
                                {
                                input.append (c);
                                }
                                tempFloat = Float.parseFloat(input.toString ());
                                mortgageBalance = tempFloat.floatValue ();
                        } // switch
                        
                        }

                        catch (Exception e){
                        System.out.println ("*************** Error: Mortgage.changeMortgageField () ************");
                        System.out.println ("\t" + e);
                                }

                        }

        




}
    
   

