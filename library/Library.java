import java.util.ArrayList;

abstract class Library {
  public ArrayList<Book> books = new ArrayList<Book>();
  public ArrayList<Member> members = new ArrayList<Member>();
  public abstract void addMember(Member member);
  public abstract Boolean isMemberIdExist(String id);
  public abstract void addBook(Book book);
  public abstract Boolean isBookIdExist(String id);
  public abstract void giveBook(String bookId, String memberId);
  public abstract void receiveBook(String bookId, String memberId);
  
  protected int getMemberIndex(Member member) {
	    return this.members.indexOf(member);
	  }

	  protected Member getMemberById(String id) throws Exception {
	    for (Member member : this.members) {
	      if (member.getId().equals(id)) {
	        return member;
	      }
	    }
	    throw new Exception("Member dengan id " + id + " tidak ditemukan");
	  }

	  protected Book getBookById(String id) throws Exception {
	    for (Book book : this.books) {
	      if (book.getId().equals(id)) {
	        return book;
	      }
	    }
	    throw new Exception("Buku dengan id " + id + " tidak ditemukan");
	  }
	}

  class PublicLibrary extends Library {
	  // constructor
	  public PublicLibrary() {
	    super();
	  }
	  @Override
  public void addMember(Member member) {
	  if (!isMemberIdExist(member.getId())) {
	      this.members.add(member);
	      System.out.println("Member dengan ID " + member.getId() + " berhasil ditambahkan");
	    } else {
	      System.out.println("Data Member dengan ID " + member.getId() + " sudah ada");
	    }
	  }
	  @Override
  public void addBook(Book book) {
	  if (isBookIdExist(book.getId())) {
	      System.out.println("Data Buku dengan ID " + book.getId() + " sudah ada");
	  }	else if (isBookBorrowed(book.getId())) {
		  System.out.println("Data Buku dengan ID " + book.getId() + " sedang dipinjam");
	  }else {
	      this.books.add(book);
	      System.out.println("Buku Berhasil ditambahkan");
	    }
	  }
  private Boolean isBookBorrowed(String bookId) {
	    for (Member member : this.members) {
	      if (member.getBookById(bookId) != null) {
	        return true;
	      }
	    }
	    return false;
	  }
  @Override
  public Boolean isMemberIdExist(String id) {
    Boolean isExist = false;
    for (Member member : this.members) {
      if (member.getId().equals(id)) {
        isExist = true;
      }
    }
    return isExist;
  }
  @Override
  public Boolean isBookIdExist(String id) {
	    Boolean isExist = false;
	    for (Book book : this.books) {
	      if (book.getId().equals(id)) {
	        isExist = true;
	      }
	    }
	    return isExist;
	  }

  public void giveBook(String bookId, String memberId) {
	  try {
	      Book book = this.getBookById(bookId);

	      Member member = this.getMemberById(memberId);
	      int memberIndex = this.getMemberIndex(member);
	      this.books.remove(book);
	      this.members.get(memberIndex).getborrowedBooks().add(book);
	      System.out
	          .println(
	              "Buku dengan id " + book.getId() + " telah berhasil dipinjam oleh member dengan ID " + member.getId());
	    } catch (Exception e) {
	      System.out.println(e.getMessage());
	    }
	  }

  public void receiveBook(String bookId, String memberId) {
	  try {
	      Member member = this.getMemberById(memberId);
	      int memberIndex = this.getMemberIndex(member);

	      Book book = this.members.get(memberIndex).getBookById(bookId);

	      if (book == null) {
	        throw new Exception("Buku dengan id " + bookId + " tidak ada/tidak dipinjam oleh member dengan ID " + member.getId());
	      }

	      this.books.add(book);
	      this.members.get(memberIndex).borrowedBooks.remove(book);
	      System.out.println(
	          "Buku dengan id " + book.getId() + " telah berhasil dikembalikan oleh member dengan ID " + member.getId());
	    } catch (Exception e) {
	      System.out.println(e.getMessage());
	    }
  }
}