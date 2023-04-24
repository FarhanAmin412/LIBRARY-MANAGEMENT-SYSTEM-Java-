public interface BookMethods
{
    String toString();
    String getObject(int isbn);
    void AddRecord() throws Exception;
    void ReadRecord() throws Exception;
    void SearchRecord() throws Exception;
    void DeleteRecord() throws Exception;
    void UpdateRecord() throws Exception;
    int SearchIsbnORID(String CurrentLine);
}
