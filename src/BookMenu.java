import java.util.Calendar;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class BookMenu
{
    private Scanner input;
    public BookMenuOptions choice;
    public final BookMenuOptions [] Choices =
            {
              BookMenuOptions.AddBook,
              BookMenuOptions.ReadBook,
              BookMenuOptions.SearchBook,
              BookMenuOptions.DeleteBook,
              BookMenuOptions.UpdateBook,
              BookMenuOptions.AddAnyIssuedBook,
              BookMenuOptions.ReadAllIssuedBooks,
              BookMenuOptions.SearchAnyIssuedBook,
              BookMenuOptions.DeleteAnyIssuedBook,
              BookMenuOptions.UpdateAnyIssuedBook,
              BookMenuOptions.SaveExit
            };
    public void ImplementBookMenu() throws Exception {
        boolean option=true;
        Book book = new Book();
        input = new Scanner(System.in);
        Date issueDate = new Date();
        Date lastSubmitDate = new Date();
        Date currentDate = new Date();
        System.out.println("Enter DAY = ");
        issueDate.setDay(input.nextInt());
        System.out.println("Enter MONTH = ");
        issueDate.setMonth(input.nextInt());
        System.out.println("Enter YEAR = ");
        issueDate.setYear(input.nextInt());
        lastSubmitDate.setDay(issueDate.getDay()+8);
        lastSubmitDate.setMonth(issueDate.getMonth());
        lastSubmitDate.setYear(issueDate.getYear());
        currentDate.setDay(Calendar.getInstance().get(Calendar.DATE));
        currentDate.setMonth((Calendar.getInstance().get(Calendar.MONTH))+1);
        currentDate.setYear(Calendar.getInstance().get(Calendar.YEAR));
        BookSubmission bookSubmission = new BookSubmission(issueDate,lastSubmitDate,currentDate);
        while(option)
        {
            choice = getBookMenuOptions();
            switch (choice)
            {
                case AddBook -> book.AddRecord();
                case ReadBook -> book.ReadRecord();
                case SearchBook -> book.SearchRecord();
                case DeleteBook -> book.DeleteRecord();
                case UpdateBook -> book.UpdateRecord();
                case AddAnyIssuedBook -> bookSubmission.AddRecord();
                case ReadAllIssuedBooks -> bookSubmission.ReadRecord();
                case SearchAnyIssuedBook -> bookSubmission.SearchRecord();
                case DeleteAnyIssuedBook -> bookSubmission.DeleteRecord();
                case UpdateAnyIssuedBook -> bookSubmission.UpdateRecord();
                case SaveExit -> option = false;
            }
        }
        System.exit(0);
    }
    public BookMenuOptions getBookMenuOptions()
    {
        input = new Scanner(System.in);
        int option = 0;
        System.out.println("TYPE 1 >> Add Book to datafile");
        System.out.println("TYPE 2 >> Read All Books from datafile");
        System.out.println("TYPE 3 >> Search Books");
        System.out.println("TYPE 4 >> Delete Books");
        System.out.println("TYPE 5 >> Update Books");
        System.out.println("TYPE 6 >> Add Issued Book to datafile");
        System.out.println("TYPE 7 >> Read Issued Book to datafile");
        System.out.println("TYPE 8 >> Search Issued Book to datafile");
        System.out.println("TYPE 9 >> Delete Issued Book to datafile");
        System.out.println("TYPE 10 >> Update Issued Book to datafile");
        System.out.println("TYPE 11 >> Save Exit");
        try
        {
            do
            {
                System.out.print("\n");
                System.out.println("ENTER ANY NUMBER FROM (1-11) = ");
                option = input.nextInt();
            }
            while ((option<1) || (option>11));
        }
        catch (NoSuchElementException exception)
        {
            System.out.println("Invalid Input");
            System.exit(1);
        }
        return Choices[option-1];
    }
}
