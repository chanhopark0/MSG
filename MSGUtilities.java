package msgCase;
import java.util.*;
public class MSGUtilities {
    //CLASS METHODS

    public static void clearScreen () {
        int	i;
        for (i = 0; i < 26; i++) {
            System.out.println ();
        }
    } // clearScreen()

    public  static void printMenu(String[] menuItems) {
        for (String menuItem : menuItems) {
            System.out.println(menuItem);
        }
    }
//_______________________________________________________________________________________________________
//

    public static void displayMainMenu () {
        boolean done;//terminates while loop
        byte choice[] = new byte[10]; //user's choice 

        done = false;
        while (!done) {
            clearScreen();
            String[] menuItems = {
                    "\t MAIN MENU\n\n",
                    "\t MARTHA STOCKTON GREENGAGE FOUNDATION\n\n",
                    "\t 1. Investment Data\n",
                    "\t 2. Mortgage Data\n",
                    "\t 3. Operating Expenses\n",
                    "\t 4. Produce Reports\n",
                    "\t 5. Quit\n\n",
                    "Enter your choice and press <ENTER>: "
            };
            printMenu(menuItems);

            try {
                System.in.read(choice);
                switch ((char) choice[0]) {
                    case '1':
                       displaylnvestmentMenu();
                        break;
                    case '2':
                       displayMortgageMenu();
                        break;
                    case '3':
                        msgCase.AnnualOperatingExpenses.updateAnnualOperatingExpenses();
                        break;
                    case '4':
                       displayReportMenu();
                        break;
                    case '5':
                    case '\n':
                        done = true;
                        break;
                    default:
                        System.out.println("\n\nNot a valid choice\n");
                        System.out.println("Press <ENTER> to return to menu...");
                        System.in.read(choice);
                        break;
                }
            } catch (Exception e) {
                System.out.println("***********Error: msgCase.MSGUtilities.displayMainMenu()******************");
                System.out.println("\t" + e);
            }
        }
    }//displayMainMenu 0
//       II---------------------------------------------------------------------------------------
    /**
     * 
     */
    public static void displaylnvestmentMenu ()
//
// displaylnvestmentMenu displays investment, offering the user the
// choice of adding, modifying, or deleting an investment; the appropriate
// method is then called, namely InputNewRecord, updateRecord. or
//deleteRecord, respectively.	The user may also choose to return to main menu.
// r
        { boolean	done;	// terminates while loop
            byte	choice[] = new byte[10];	// user's choice
            Investment	inv = new Investment();	// investment record
            done = false;
            while (!done){
                clearScreen();
                String[] menuItems = {
                        "\t INVESTMENTS",
                        "\t MARTHA STOCKTON GREENGAGE FOUNDATION",
                        "\t 1. Add an investment \n",
                        "\t 2. Modify a investment\n",
                        "\t 3. Delete a investment\n",
                        "\t 4. Exit to main menu\n",
                        "\t Enter your choice and press <ENTER>: "
                };
                printMenu(menuItems);

                try
                    {
                        System.in.read (choice);
                        switch ((char) choice[0])
                        {
                            case '1':
                                clearScreen();
                                inv.inputNewRecord();
                                break;
                            case '2':
                                clearScreen();
                                inv.updateRecord();
                                break;
                            case '3':
                                clearScreen();
                                inv.deleteRecord();
                                break;
                            case '4':

                            case '\n':
                                done = true;
                                break;
                            
                            default:
                                System.out.println("\n\n Not a valid choice\n");
                                System.out.println("Press <ENTER> to return to menu...");
                                System.in.read (choice);
                                break;
                        }
                    }
                catch (Exception e){
                    System.out.println ("************ Error: MSGUtilitles.displaylnvestmentMenu () *********");
                    System.out.println ("\t" + e);
                }

            }



            }// displaylnvestmentMenu ()


//-----------------------------------------------------------------------------------------

public static void displayMortgageMenu ()
//
// displayMortgageMenu displays mortgage menu, offering the user the
// choice of adding, modifying, or deleting a mortgage: the appropriate
// method is then called, namely inputNewRecord, updateRecord, or
// deleteRecord, respectively The user may also choose to return to main menu.
//* */
        {
            boolean	done;	//terminates whUe-loop
            byte	choice[] = new byte[10];	// user's choice
            Mortgage	mort = new Mortgage ();	// mortgage record
            done = false;
            while(!done){
                clearScreen ();
                String[] menuItems = {
                        "\t MORTGAGES \n\n",
                        "\t MARTHA STOCKTON GREENGAGE FOUNDATION \n\n",
                        "\t 1. Add a mortgage \n",
                        "\t 2. Modify a mortgage\n",
                        "\t 3. Delete a mortgage \n",
                        "\t 4. Exit to main menu\n",
                        "\t Enter your choice and press <ENTER>: "
                };
                printMenu(menuItems);
                try
                {
                    System.in.read (choice);
                    switch ((char) choice[0])
                    {
                        case '1':
                            clearScreen();
                            mort.inputNewRecord();
                            break;
                        case '2':
                            clearScreen();
                            mort.updateRecord();
                            break;
                        case '3':
                            clearScreen();
                            mort.deleteRecord();
                            break;
                        case '4':

                        case '\n':
                            done = true;
                            break;
                        
                        default:
                            System.out.println("\n\n Not a valid choice\n");
                            System.out.println("Press <ENTER> to return to menu...");
                            System.in.read (choice);
                            break;
                    }
                }
            catch (Exception e){
                System.out.println ("************ Error: MSGUtilitles.displayMortgageMenu () *********");
                System.out.println ("\t" + e);

            }
        }

    }//DISPLAY MORTGAGE MENU

//     _________________________________________________________________________________________
/**
 *  displayReportMenu displays report menu, offering the user the choice of
    investment report, mortgage report, or funds availability report;
    the appropriate method is then called, namely producelnvestmentReport,
    produceMortgageReport, or fundsAvailable, respectively.
    The user may also choose to return to main menu.
 * 
 * 
 */
public static void displayReportMenu ()

                
{
    boolean	done;	//terminates whUe-loop
    byte	choice[] = new byte[10];	// user's choice
    Report report = new Report();
//    Investment inv = new Investment ();	// investment record
//    Mortgage	mort = new Mortgage ();	// mortgage record
    done = false;
    while(!done){
        clearScreen ();
        String[] menuItems = {
                "\t REPORTS \n\n",
                "\t MARTHA STOCKTON GREENGAGE FOUNDATION \n\n",
                "\t 1. List of investments \n",
                "\t 2. List of mortgages\n",
                "\t 3. Funds available \n",
                "\t 4. Exit to main menu\n",
                "\t Enter your choice and press <ENTER>: "
        };
        printMenu(menuItems);

        try
        {
            System.in.read (choice);
            switch ((char) choice[0])
            {
                case '1':

                    report.produceInvestmentReport();;
                    break;
                case '2':

                    report.produceMortgageReport();
                    break;
                case '3':
                    report.fundsAvailable();
                    break;
                case '4':

                case '\n':
                    done = true;
                    break;
                
                default:
                    System.out.println("\n\n Not a valid choice\n");
                    System.out.println("Press <ENTER> to return to menu...");
                    System.in.read (choice);
                    break;
            }
        }
    catch (Exception e){
        System.out.println ("************ Error: MSGUtilitles.displayMortgageMenu () *********");
        System.out.println ("\t" + e);

    }
}

}//Display Report Menu 

//     _________________________________________________________________________________________
//-----—..........................................................................................
    /**
     * @param s
     * @return
     */
    public static Date parseDate(String s)
     // // parseDate parses a string of the form mm/dd/yy and creates //			a new Date
     {
     int theMonth = -1;
     System.out.print(theMonth);
     int  theDay = -1;
     int theYear = -1;
     System.out.print(theYear);
     int i = 0;
     int c = -1;
     int  prevc = 0;
     int  n = -1;	
    
     syntax: {	if(s == null) 
                    break syntax;
                    int	limit = s.length ();//number of characters in S


                    while (i < limit)
                            {
                                c = s.charAt (i);
                                i++;
                                if (c == '/')
                                {
                                    prevc = c;
                                    continue;
                                }
                                else if ('0' <= c && c <= '9')
                                    {
                                            n = c - '0';
                                        while (i < limit && '0' <= (c = s.charAt (i)) && c <= '9')
                                            {
                                                n = n * 10 + c - '0';
                                                i++;
                                            }
                                                if(prevc==0)
                                                    theMonth = n;
                                                else if (prevc == '/' && theDay > 0)
                                                    theYear = n;
                                                else
                                                    theDay = n;
                                    }//while
                                else    
                                    break syntax;
                                
                            
                        }
                        //Date goodDate =  new Date(theYear, theMonth -1, theDay); 
                        //Initialize your Date however you like it.

                        // //Calendar calendar = new GregorianCalendar();
                        // calendar.setTime(goodDate);
                        // int year = calendar.get(Calendar.YEAR);
                        // //Add one to month {0 - 11}
                        // int month = calendar.get(Calendar.MONTH) + 1;
                        // int day = calendar.get(Calendar.DAY_OF_MONTH);
                        

                        Date goodDate = new Date();
                        
                        
                        return goodDate;
                        
                    }
                    throw new IllegalArgumentException();
                    
                    
                  

        }// parseDate
}//CLASS MSG UTILITIS
