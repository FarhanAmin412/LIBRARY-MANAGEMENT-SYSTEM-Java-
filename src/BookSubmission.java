import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookSubmission extends Book
{
    private Date IssueDate;
    private  Date LastSubmitDate;
    private Date CurrentDate;
    private boolean IsSubmitted;
    private double Fine;
    User user;
    int UserID=0;
    public BookSubmission(Date issueDate, Date lastSubmitDate, Date currentDate)
    {
        super();
        setIssueDate(issueDate);
        setLastSubmitDate(lastSubmitDate);
        setCurrentDate(currentDate);
        this.Fine=0.0;
    }

    public void setIssueDate(Date issueDate) {
        this.IssueDate = issueDate;
    }
    public void setLastSubmitDate(Date lastSubmitDate) {
        this.LastSubmitDate = lastSubmitDate;
    }
    public void setCurrentDate(Date currentDate){this.CurrentDate = currentDate;}
    public void setSubmitted(boolean submitted)
    {
        this.IsSubmitted = submitted;
    }
    public Date getIssueDate()
    {
        return IssueDate;
    }
    public boolean getSubmitted()
    {
        return IsSubmitted;
    }
    public Date getLastSubmitDate() {
        return LastSubmitDate;
    }
    @Override
    public String toString()
    {
        user = new User();
        if(!CheckFine())
        {
             return  user.getUser(UserID) + " Have Issued the " + super.toString() +" on ( Date ) : " + getIssueDate().toString()
                    + " and not submitted while Last Submission ( Date ) was : " + getLastSubmitDate().toString() + " Your Total Fine is " +
                    Fine;
        }
        else
        {
            return  user.getUser(UserID) + " Have Issued the ( Book ) : " + super.toString() + " on ( Date ) : " + getIssueDate().toString() +  " Last Submission ( Date ) was : " + getLastSubmitDate().toString();
        }
    }
    public String getObject()
    {
        System.out.println("Enter Book ISBN = ");
        return super.getObject(input.nextInt());
    }
    public boolean CheckFine()
    {
        input = new Scanner(System.in);
        System.out.println("Submitted Book ? (true/false)");
        setSubmitted(input.nextBoolean());
        if(!getSubmitted())
        {
            if(CurrentDate.getYear() > LastSubmitDate.getYear())
            {
                Fine+=1200.00;
            }
            else
            {
                if(CurrentDate.getMonth() > LastSubmitDate.getMonth())
                {
                    Fine+=300.00;
                }
                else
                {
                    if(CurrentDate.getDay()> LastSubmitDate.getDay())
                    {
                        Fine+=100.00;
                    }
                    else
                    {
                        Fine+=0.0;
                    }
                }
            }
        }
        return getSubmitted();
    }
    @Override
    public void AddRecord() throws Exception
    {
        try {
            boolean flag = true;
            input = new Scanner(System.in);
            System.out.println("Enter User ID = ");
            UserID = input.nextInt();
            reader = new BufferedReader(new FileReader("BookSubmission.txt"));
            String CurrLine;
            while ((CurrLine = reader.readLine()) != null) {
                if (UserID == SearchIsbnORID(CurrLine)) {
                    flag = false;
                    System.out.println("Record Already Exists");
                }
            }
            if (flag) {
                output = new PrintWriter(new FileWriter("BookSubmission.txt", true));
                output.println(getObject());
            }
        }
        catch (FileNotFoundException exception1) {
            reader.close();
            output = new PrintWriter(new FileWriter("BookSubmission.txt", true));
            output.println(getObject());
        }
        catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (output != null) {
                output.close();
            }
            if (reader != null) {
                reader.close();
            }
        }
    }
    @Override
    public void SearchRecord() throws Exception
    {
        try
        {
            int userID;
            input= new Scanner(System.in);
            System.out.println("Enter user ID to be searched = ");
            userID =input.nextInt();
            boolean flag = true;
            reader = new BufferedReader(new FileReader("BookSubmission.txt"));
            String CurrentLine;
            while ((CurrentLine = reader.readLine()) != null)
            {
                if (userID == SearchIsbnORID(CurrentLine))
                {
                    flag = false;
                    System.out.println("Record found Successfully");
                    System.out.println(CurrentLine);
                }
            }
            if (flag)
            {
                System.out.println("Record not found");
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            if (reader != null)
            {
                reader.close();
            }
        }
    }
    @Override
    public void DeleteRecord() throws Exception
    {
        try
        {
            boolean flag = true;
            int ID;
            input= new Scanner(System.in);
            System.out.println("Enter User ID to be searched = ");
            ID=input.nextInt();
            reader = new BufferedReader(new FileReader("BookSubmission.txt"));
            String CurrLine;
            myLinkedList = new LinkedList<>();
            while ((CurrLine = reader.readLine()) != null)
            {
                if (ID == SearchIsbnORID(CurrLine))
                {
                    flag = false;
                    System.out.println(CurrLine);
                    System.out.println("Deleted Successfully");
                }
                else
                {
                    myLinkedList.add(CurrLine);
                }
            }
            if (flag)
            {
                System.out.println("Record Not Found");
            }
            else
            {
                output = new PrintWriter(new FileWriter("BookSubmission.txt"));
                output.close();
                output = new PrintWriter(new FileWriter("BookSubmission.txt", true));
                for (String object : myLinkedList)
                {
                    output.println(object);
                }
                if (output != null)
                {
                    output.close();
                }
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            reader.close();
        }
    }
    @Override
    public void ReadRecord() throws Exception
    {
        try
        {
            reader = new BufferedReader(new FileReader("BookSubmission.txt"));
            String CurrentLine;
            while ((CurrentLine=reader.readLine())!=null)
            {
                System.out.println(CurrentLine);
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            if(reader!=null)
            {
                reader.close();
            }
        }
    }
    @Override
    public void UpdateRecord() throws Exception
    {
        try
        {
            int userID;
            input= new Scanner(System.in);
            System.out.println("Enter User ID to be searched = ");
            userID =input.nextInt();
            boolean flag=true;
            reader = new BufferedReader(new FileReader("BookSubmission.txt"));
            String CurrLine;
            myLinkedList = new LinkedList<>();
            while((CurrLine=reader.readLine())!=null)
            {
                if(userID == SearchIsbnORID(CurrLine))
                {
                    flag=false;
                    myLinkedList.add(getObject());
                    System.out.println("Edited Successfully");
                }
                else
                {
                    myLinkedList.add(CurrLine);
                }
            }
            if(flag)
            {
                System.out.println("Record Not Found");
            }
            else
            {
                output = new PrintWriter(new FileWriter("BookSubmission.txt"));
                output.close();
                output = new PrintWriter(new FileWriter("BookSubmission.txt",true));
                for(String object : myLinkedList)
                {
                    output.println(object);
                }
                if(output!=null)
                {
                    output.close();
                }
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            reader.close();
        }
    }
    @Override
    public int SearchIsbnORID(String CurrentLine)
    {
        Pattern regex = Pattern.compile("\\d+");
        Matcher match = regex.matcher(CurrentLine);
        boolean b = match.find();
        if(b)
        {
            return Integer.parseInt(match.group());
        }
        return 0;
    }
}
