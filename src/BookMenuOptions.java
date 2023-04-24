public enum BookMenuOptions
{
    AddBook(1),
    ReadBook(2),
    SearchBook(3),
    DeleteBook(4),
    UpdateBook(5),
    AddAnyIssuedBook(6),
    ReadAllIssuedBooks(7),
    SearchAnyIssuedBook(8),
    DeleteAnyIssuedBook(9),
    UpdateAnyIssuedBook(10),
    SaveExit(11);

    protected int Choices;

    BookMenuOptions(int choices)
    {
        setChoices(choices);
    }
    public void setChoices(int choices)
    {
        this.Choices=choices;
    }
}
