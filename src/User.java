import java.util.Scanner;

public class User
{
    private int UserID;
    private String Name;
    public User()
    {
        this(0,"");
    }
    public User(int UserID,String Name )
    {
        setUserID(UserID);
        setName(Name);
    }
    public void setUserID(int userID)
    {
        this.UserID = userID;
    }
    public void setName(String name)
    {
        this.Name = name;
    }
    public String getName()
    {
        return Name;
    }
    public int getUserID()
    {
        return UserID;
    }

    @Override
    public String toString() {
        return "User{" +
                "UserID=" + getUserID() +
                ", Name='" + getName() + '\'' +
                '}';
    }
    public String getUser(int userID)
    {
        Scanner input = new Scanner(System.in);
        setUserID(userID);
        System.out.println("ENTER USER NAME =  ");
        setName(input.next());
        return toString();
    }
}

