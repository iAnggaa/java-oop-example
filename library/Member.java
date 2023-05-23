import java.util.ArrayList;

interface Borrowable {
	  void receiveBook(Book book);

	  void giveBook(Book book);

	  Book getBookById(String id);
	}

class Member implements Borrowable {
	  String id;
	  String name;
	  ArrayList<Book> borrowedBooks;


  public Member() {
    this.id = id;
    this.name = name;
    this.borrowedBooks = new ArrayList<Book>();
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }
  
  public ArrayList<Book> getborrowedBooks() {
	    return borrowedBooks;
	  }


  public void receiveBook(Book book) {
    this.borrowedBooks.add(book);
  }

  public void giveBook(Book book) {
    this.borrowedBooks.remove(book);
  }
  
  public Book getBookById(String id) {
	    for (Book book : this.borrowedBooks) {
	      if (book.getId().equals(id)) {
	        return book;
	      }
	    }
	    return null;
	  }
	}
