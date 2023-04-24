import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Book implements BookMethods
{
    private int Isbn;
    private String BookName;
    private String Author;
    Scanner input;
    PrintWriter output;
    BufferedReader reader;
    LinkedList<String> myLinkedList;
    public Book()
    {
        this(0,"","");
    }
    public Book(int i, String s, String s1)
    {
        setIsbn(i);
        setBookName(s);
        setAuthor(s1);
    }
    public void setIsbn(int isbn)
    {
        this.Isbn = isbn;
    }
    public void setBookName(String bookName)
    {
        this.BookName = bookName;
    }
    public void setAuthor(String author)
    {
        this.Author = author;
    }
    public int getIsbn()
    {
        return Isbn;
    }
    public String getBookName()
    {
        return BookName;
    }
    public String getAuthor()
    {
        return Author;
    }
    @Override
    public String toString()
    {
        return "(Book) : " + getBookName() + " ( ID = " + getIsbn() + " ) " + " Author = " + getAuthor();
    }
    @Override
    public void AddRecord() throws Exception {
        int isbn = 0;
        try {
            boolean flag = true;
            input = new Scanner(System.in);
            System.out.println("Enter Book ISBN =");
            isbn = input.nextInt();
            String CurrentLine;
            reader = new BufferedReader(new FileReader("BookFile.txt"));
            while ((CurrentLine = reader.readLine()) != null) {
                if (isbn == SearchIsbnORID(CurrentLine)) {
                    flag = false;
                    System.out.println("Record Already Exists..Please Try Again");
                }
            }
            if (reader != null) {
                reader.close();
            }
            if (flag) {
                output = new PrintWriter(new FileWriter("BookFile.txt", true));
                output.println(getObject(isbn));
            }
        }
        catch (FileNotFoundException notFoundException) {
            reader.close();
            output = new PrintWriter(new FileWriter("BookFile.txt", true));
            output.println(getObject(isbn));
        }
        catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (output != null) {
                output.close();
            }
        }
    }
    @Override
    public void SearchRecord() throws Exception
    {
        try
        {
            int isbn;
            input= new Scanner(System.in);
            System.out.println("Enter ISBN to be searched = ");
            isbn=input.nextInt();
            boolean flag = true;
            reader = new BufferedReader(new FileReader("BookFile.txt"));
            String CurrentLine;
            while ((CurrentLine = reader.readLine()) != null)
            {
                if (isbn == SearchIsbnORID(CurrentLine))
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
            System.out.println("Enter ISBN to be searched = ");
            ID=input.nextInt();
            reader = new BufferedReader(new FileReader("BookFile.txt"));
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
                output = new PrintWriter(new FileWriter("BookFile.txt"));
                output.close();
                output = new PrintWriter(new FileWriter("BookFile.txt", true));
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
            reader = new BufferedReader(new FileReader("BookFile.txt"));
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
            int isbn;
            input= new Scanner(System.in);
            System.out.println("Enter ISBN to be searched = ");
            isbn=input.nextInt();
            boolean flag=true;
            reader = new BufferedReader(new FileReader("BookFile.txt"));
            String CurrLine;
            myLinkedList = new LinkedList<>();
            while((CurrLine=reader.readLine())!=null)
            {
                if(isbn== SearchIsbnORID(CurrLine))
                {
                    flag=false;
                    myLinkedList.add(getObject(isbn));
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
                output = new PrintWriter(new FileWriter("BookFile.txt"));
                output.close();
                output = new PrintWriter(new FileWriter("BookFile.txt",true));
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
    @Override
    public String getObject(int isbn)
    {
        input=new Scanner(System.in);
        setIsbn(isbn);
        System.out.println("ENTER BOOK NAME = ");
        setBookName(input.next());
        System.out.println("ENTER AUTHOR NAME = ");
        setAuthor(input.next());
        return toString();
    }

}
