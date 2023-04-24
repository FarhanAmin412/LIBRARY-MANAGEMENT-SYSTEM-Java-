public class Date
{
    private int day;
    private int month;
    private int year;
    public Date()
    {
        this(0,0,0);
    }
    public Date(int i, int i1,int year)
    {
        setDay(i);
        setMonth(i1);
        setYear(year);
    }
    public void setYear(int year)
    {
        this.year = year;
    }
    public void setDay(int day)
    {
        this.day = day;
    }
    public void setMonth(int month)
    {
        this.month = month;
    }
    public int getDay()
    {
        return day;
    }
    public int getMonth()
    {
        return month;
    }
    public int getYear()
    {
        return year;
    }


    public String toString()
    {
        return getDay()+" - "+getMonth()+" - "+ getYear();
    }
}
