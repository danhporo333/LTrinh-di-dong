package hutech.example.buoi6.model;

public class Book {
    private String bookid;
    private String bookname;
    private int bookpage;
    private float bookprice;
    private String bookdescription;
    private String anhbook;

    public Book() {
    }

    public Book(String bookid, String bookname, int bookpage, float bookprice, String bookdescription, String anhbook) {
        this.bookid = bookid;
        this.bookname = bookname;
        this.bookpage = bookpage;
        this.bookprice = bookprice;
        this.bookdescription = bookdescription;
        this.anhbook = anhbook;
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public int getBookpage() {
        return bookpage;
    }

    public void setBookpage(int bookpage) {
        this.bookpage = bookpage;
    }

    public float getBookprice() {
        return bookprice;
    }

    public void setBookprice(int bookprice) {
        this.bookprice = bookprice;
    }

    public String getBookdescription() {
        return bookdescription;
    }

    public void setBookdescription(String bookdescription) {
        this.bookdescription = bookdescription;
    }

    public String getAnhbook() {
        return anhbook;
    }

    public void setAnhbook(String anhbook) {
        this.anhbook = anhbook;
    }
}
