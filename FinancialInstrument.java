package msgCase;

import java.io.RandomAccessFile;


public abstract class FinancialInstrument {
    public abstract void readRecord(RandomAccessFile fileName);

    public abstract void displayRecord();

    public abstract void obtainNewData();

    public abstract void performDeletion();

    public abstract void putRecord();

    public abstract void updateRecord();

    public abstract void writeRecord(RandomAccessFile fileName);

    public abstract boolean findRecord(String findRecordID);

    public void deleteRecord() {
        try {
            char c;
            StringBuffer input = new StringBuffer();
            boolean done = false;
            boolean found = false;
            byte choice[] = new byte[10];

            while (!found && !done) {
                System.out.println("Please enter the number of the "  + getClass().getName() + " to be updated (12 digits): ");
                while ((c = (char) System.in.read()) != '\n') {
                    input.append(c);
                }
                found = findRecord(input.toString());
                if (!found) {
                    System.out.println(getClass().getName() + " " + input.toString() + "was not found.");
                    System.out.println("Would you like to enter another" + getClass().getName() + "? ");
                    System.in.read(choice);
                    if (((char) choice[0]) == 'n') {
                        done = true;
                    }
                }
            }
            if (!found) return;
            performDeletion();
            System.out.println("\nThe record has been deleted.");
            System.out.println("\n\n\n Press ENTER to continue ...");
            c = (char) System.in.read();
        } catch (Exception e) {
            System.out.println("Error: FinancialInstrument.deleteRecord()");
            System.out.println("\t" + e);
        }
    } //deleteRecord()

    public void inputNewRecord() {
        try {
            
            obtainNewData();
            putRecord();
            System.out.println("\nThe following record was inserted\n");
            displayRecord();
            System.out.println("\n\n\n Press ENTER to continue ...");
            int c = System.in.read();
            System.out.println (c);
        } catch (Exception e) {
            System.out.println("Error: Financiallnstrument.inputNewRecord () •••••");
            System.out.println("\t" + e);
        }
    } //inputNewRecord()
    
}
