package msgCase;
import java.io.File;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
public class Investment extends FinancialInstrument
{
    private String investmentID;
    private String investmentName;
    private float expectedAnnualReturn;
//    private SimpleDateFormat expectedAnnualReturnUpdated;
    private String expectedAnnualReturnUpdated;
    public String getInvestmentID() {
        return investmentID;
    }
    public String getInvestmentName() {
        return investmentName;
    }
    public float getExpectedAnnualReturn() {
        return expectedAnnualReturn;
    }
     public String getExpectedAnnualReturnUpdated() {
         return expectedAnnualReturnUpdated;
     }

    public float totalWeeklyReturnOnInvestment()
    {
        try {
            File	investmentFile = new File("investment.dat");
            float	totalAnnualReturn = (float) 0.0;

            if(investmentFile.exists ()) {
                RandomAccessFile inFile = new RandomAccessFile(investmentFile, "r");
                while (inFile.getFilePointer() != inFile.length()) {
                    readRecord (inFile);
                    totalAnnualReturn += expectedAnnualReturn;
                }
                inFile.close ();
            }
            return (totalAnnualReturn / MSGConstants.WEEKS_IN_YEAR);
        }
        catch (Exception e) {
            System.out.println ("Error: msgCase.Investment.totalWeeklyReturnOnInvestment() eeturned a value of 0");
            System.out.println ("\t" + e);
            return (float) 0.0;
        }
    } // totalWeeklyReturnOnInvestment()
 //----------------------------------------------------------------------------------
    public boolean findRecord(String findRecordID) {
        try {
            File investmentFile = new File("investment.dat");
            boolean found = false;
            if (investmentFile.exists()) {
                RandomAccessFile inFile = new RandomAccessFile(investmentFile, "r");
                while (!found && (inFile.getFilePointer() != inFile.length())) {
                    readRecord(inFile);
                    if (investmentID.compareTo(findRecordID) == 0) found = true;
                }
                inFile.close();
            }
            return found;
        }
        catch (Exception e) {
            System.out.println("Error: msgCase.Investment.findRecord()");
            System.out.println("\t" + e);

            return false;
        }
    } // findRecord()
 //----------------------------------------------------------------------------------
 @Override
 public void readRecord(RandomAccessFile fileName) {
    try {
        String inputString = new String ();
        int	i = 0;

        inputString = fileName.readLine();
        StringBuffer input = new StringBuffer();
        while (inputString.charAt(i) != '|') {
            input.append(inputString.charAt(i));
            i++;
        }
        investmentID = input.toString();
        i++;

        input = new StringBuffer ();
        while (inputString.charAt (i) != '|') {
            input.append(inputString.charAt (i));
            i++;
        }
        investmentName = input.toString();
        i++;

        input = new StringBuffer ();
        while (inputString.charAt (i) != '|') {
            input.append(inputString.charAt (i));
            i++;
        }
        Float tempFloat = Float.parseFloat(input.toString());
        expectedAnnualReturn = tempFloat.floatValue();
        i++;

        input = new StringBuffer ();
        while (i < inputString.length()) {
            input.append(inputString.charAt(i));
            i++;
        }

        expectedAnnualReturnUpdated = input.toString();
    }
    catch (Exception e) {
        System.out.println ("Error: msgCase.Investment.readRecord()");
        System.out.println ("\t" + e);
    }
 } //readRecord()
    //-----------------------------------------------------
    public void performDeletion(){
    try {
        File	investmentFile = new File("investment.dat");
        File	tempInvestmentFile = new File ("investment.tmp");
        Investment	inv = new Investment();
        if (!investmentFile.exists ()) return;
        RandomAccessFile inFile = new RandomAccessFile(investmentFile, "r");
        RandomAccessFile outFile = new RandomAccessFile(tempInvestmentFile, "rw");
        while (inFile.getFilePointer() != inFile.length()) {
            inv.readRecord (inFile);
            if (investmentID.compareTo (inv.getInvestmentID()) != 0) {
                inv.writeRecord (outFile);
            }
        }
        inFile.close ();
        outFile.close ();
        investmentFile.delete();
        tempInvestmentFile.renameTo(investmentFile);
    }
    catch (Exception e) {
        System.out.println ("Error: Investment.performDeletion()");
        System.out.println ("\t" + e);
    }
  } //performDeletion()
 //----------------------------------------------------------------------------------
   //Put Record
  public void putRecord() {
    try {
        File investmentFile = new File("investment.dat");
        File tempInvestmentFile = new File("investment.tmp");
        Investment inv = new Investment();
        boolean found = false;
        RandomAccessFile newFile = new RandomAccessFile(tempInvestmentFile, "rw");
        if (!investmentFile.exists()) writeRecord(newFile);
        else {
            RandomAccessFile oldFile = new RandomAccessFile(investmentFile, "r");
            int compareInvestments;
            while (oldFile.getFilePointer() != oldFile.length()) {
                inv.readRecord(oldFile);
                compareInvestments = investmentID.compareTo(inv.getInvestmentID());
                if((found) && (compareInvestments < 0)) {
                    writeRecord(newFile);
                    inv.writeRecord(newFile);
                    found = true;
                }
                else if(compareInvestments == 0) {
                    writeRecord(newFile);
                    found = true;
                }
                else inv.writeRecord(newFile);
            } //while
            if (!found) writeRecord(newFile);
            oldFile.close();
        } // else
        newFile.close();
        investmentFile.delete();
        tempInvestmentFile.renameTo(investmentFile);
    } catch (Exception e) {
        System.out.println("Error: Investment.putRecord ()");
        System.out.println("\t" + e);
    }
   } //putRecord()
   //----------------------------------------------------------------

   public void readInvestmentData () {
    try {
        char c;
        StringBuffer input = new StringBuffer();
        boolean valid = false;
        while (!valid) {
            System.out.println("Enter investment number (12 digits): ");
            input = new StringBuffer();
            while ((c = (char) System.in.read()) != '\n') {
                input.append(c);
            }
            valid = (input.toString().length() <= 12);
            if (!valid)
                System.out.println("\n\nThe investment number must be 12 digits long.");
        }
        investmentID = input.toString();
        System.out.println("Enter investment name: ");
        input = new StringBuffer();
        while ((c = (char) System.in.read()) != '\n') {
            input.append(c);
        }
        investmentName = input.toString();
        System.out.println("Enter expected annual return: ");
        input = new StringBuffer();
        while ((c = (char) System.in.read()) != '\n') {
            input.append(c);
        }
        Float newFloat = Float.parseFloat(input.toString());
        expectedAnnualReturn = newFloat.floatValue();
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String strDate = sdf.format(now);
        expectedAnnualReturnUpdated = strDate;
    } catch (Exception e) {
        System.out.println("Error: Investment.readInvestmentData()");
        System.out.println("\t" + e);
    }
    } // readInvestmentData(()

 //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
  public void changelnvestmentField (char investmentFieldNumber)
    // ChangelnvestmentField gets new input for field in investment record to be changed
        {
            try
            {
                char	c;	// character entered by user
                StringBuffer	input = new StringBuffer (); //bufferfor line of characters
                switch (investmentFieldNumber)
                {
                    case '1':
                        
                        System.out.println ("\n\n Old investment name:" + investmentName);
                        System.out.println ("Enter new investment name:");
                        while ((c=(char) System.in.read()) != '\n')
                            {
                                input.append(c);
                            }
                        investmentName = input.toString();
                        break;
                    case '2':
                        System.out.println("\n\nOld expected return: " + expectedAnnualReturn);
                        System.out.println ("Enter new expected return: ");
                        while ((c = (char) System.in.read()) != '\n')
                        {
                            input.append (c);
                        }
                        Float tempFloat =Float.parseFloat(input.toString());
                        expectedAnnualReturn = tempFloat.floatValue();
                        Date now = new Date();
                        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                        String strDate = sdf.format(now);
                        expectedAnnualReturnUpdated = strDate;
                } //SWITCH
            }
            catch (Exception e){
                System.out.println ("*************888 Error: Investment.changelnvestmentFleld () *********************");
                System.out.println ("\t" + e);
            }
            
        } 
 
 public void updateRecord() {
        try {
            char	option = '0';
            boolean	done = false;
            boolean	found = false;
            char	c;
            StringBuffer	input = new StringBuffer();
            byte	choice[] = new byte[10];

            while (!found && !done) {
                System.out.println("Please enter the number of the investment to be updated (12 digits): ");
                input = new StringBuffer();
                while ((c = (char) System.in.read()) != '\n') {
                    input.append(c);
                }
                found = findRecord(input.toString());
                if (!found) {
                    System.out.println("Investment " + input.toString() + "was not found.");
                    System.out.println("Would you like to enter another Investment? ");
                    System.in.read(choice);
                    if (((char) choice[0]) == '\n') done = true;
                }
            }
            if(!found) return;
            while(!done) {
                while (!done) {
                    MSGUtilities.clearScreen ();
                    System.out.println ("\t     UPDATE INVESTMENTS\n");
                    System.out.println ("\tMARTHA STOCKTON GREENGAGE FOUNDATION\n\n");
                    System.out.println ("\t1.	Update investment name\n");
                    System.out.println ("\t2.	Update expected return\n");
                    System.out.println ("\t3.	Exit to investment menu\n\n");
                    System.out.println ("Enter your choice and press <ENTER>:");
                    try {
                        System.in.read(choice);
                        switch((char) choice[0]) {
                            case '1':
                                option = '1';
                                break;
                            case '2':
                                option = '2';
                                break;
                            case '3':
                            case '\n':
                                done = true;
                                break;
                            default:
                                System.out.println ("\n\nNot a valid choice\n");
                                System.out.println ("Press <ENTER> to return to menu...");
                                System.in.read(choice);
                            break;
                        }
                    }
                    catch (Exception e) {
                        System.out.println ("Error; Investment.updateRecord()");
                        System.out.println ("\t" + e);
                    }
                    if (!done) {
                        changelnvestmentField(option);
                        displayRecord();
                        System.out.println ("\n\n Press ENTER to continue ...");
                        c = (char)System.in.read ();
                    }
                }
            }
            putRecord();
        }
        catch(Exception e) {
            System.out.println("Error Investment.updateRecord()");
            System.out.println("\t" + e);
        }
        } //updateRecord()

    //------------------------------------------------------------------------------------------------

    public void writeRecord(RandomAccessFile fileName) {
        try {
            fileName.writeBytes(investmentID + "|" + investmentName + "|");
            fileName.writeBytes(expectedAnnualReturn + "|");
            fileName.writeBytes(expectedAnnualReturnUpdated.toString() + "\n");
        }
        catch (Exception e) {
            System.out.println ("Error: msgCase.Investment.writeRecord()");
            System.out.println ("\t" + e);
        }
    } //writeRecord()
    

    @Override
    public void displayRecord() {
        System.out.print ("msgCase.Investment ID: " + investmentID);
        System.out.println ("\tlnvestment Name: " + investmentName);
        System.out.println ("Expected return: " + expectedAnnualReturn);
        System.out.print (" Date expected return was updated: ");
        //SimpleDateFormat sdf  = new SimpleDateFormat("dd/MM/yyyy");
        // Date parse = sdf.parse(input.toString());
        // Calendar  expectedAnnualReturnUpdated = Calendar.getInstance();
        // expectedAnnualReturnUpdated.setTime(parse);
        // System.out.println(expectedAnnualReturnUpdated.get(Calendar.MONTH) + expectedAnnualReturnUpdated.get(Calendar.DATE) + expectedAnnualReturnUpdated.get(Calendar.YEAR));
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        try{

            Date parse = sdf.parse(expectedAnnualReturnUpdated);

            Calendar ch = Calendar.getInstance();
            ch.setTime(parse);
            
            System.out.print ((ch.get(Calendar.MONTH) + 1) + "/");
            System.out.print (ch.get(Calendar.DATE) + "/");
            System.out.println (ch.get(Calendar.YEAR));
        }
        catch (ParseException e) {
            System.out.println ("Error: Investment.displayRecord()");
            System.out.println ("\t" + e);
            e.printStackTrace();
        }
        


        // System.out.print ((expectedAnnualReturnUpdated.get(Calendar.MONTH) + 1) + "/");
        // System.out.print (expectedAnnualReturnUpdated.get(Calendar.DATE) + "/");
        // System.out.println (expectedAnnualReturnUpdated.get(Calendar.YEAR));

    } //displayRecord()
    @Override
    public void obtainNewData() {
        readInvestmentData();
    } //obtainNewData()

}
