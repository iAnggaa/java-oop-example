import java.util.Scanner;
import java.util.InputMismatchException;


class Main {

  static Scanner scan = new Scanner(System.in);
  static Library library = new PublicLibrary();

  public static void main(String[] args) {
    initLibraryData();

    String isContinue = "y";
    while (isContinue.equals("y")) {
    	showMenu();
      int selectedMenu = chooseMenu();

      if (selectedMenu == 1) {
        showBooks();
      } else if (selectedMenu == 2) {
        showMembers();
      } else if (selectedMenu == 3) {
        addMember();
      } else if (selectedMenu == 4) {
        addBook();
      } else if (selectedMenu == 5) {
        borrowBook();
      } else if (selectedMenu == 6) {
        returnBook();
      } else {
        System.out.println("wrong input");
      }

      System.out.print("continue ? ");
      isContinue = scan.next();
    }
  }

  public static void showMenu() {
    System.out.println("================================");
    System.out.println("1. show books list");
    System.out.println("2. show members list");
    System.out.println("3. add member");
    System.out.println("4. add book");
    System.out.println("5. borrow book");
    System.out.println("6. return book");
    System.out.println("================================");
  }

  public static void initLibraryData() {
    Book book1 = new Book();
    book1.setId("1");
    book1.setTitle("pemrograman java");

    Book book2 = new Book();
    book2.setId("2");
    book2.setTitle("pemrograman oop");

    Book book3 = new Book();
    book3.setId("3");
    book3.setTitle("pemrograman android");

    Member member1 = new Member();
    member1.id = "1";
    member1.name = "aka";

    Member member2 = new Member();
    member2.id = "2";
    member2.name = "budi";

    Member member3 = new Member();
    member3.id = "3";
    member3.name = "tono";

    library.books.add(book1);
    library.books.add(book2);
    library.books.add(book3);

    library.members.add(member1);
    library.members.add(member2);
    library.members.add(member3);
  }

  public static int chooseMenu() {
    System.out.print("choose menu : ");
    try {
    int pilihan = scan.nextInt();
    return pilihan;
    } catch (InputMismatchException e) {
    	System.out.println("Harap masukkan inputan berupa angka!");
    	scan.nextLine();
        return chooseMenu();
    }
  }


  public static void showBooks() {
    for (Book book : library.books) {
      System.out.println(book.getId() + " " + book.getTitle());
    }
  }

  public static void showMembers() {
    for (Member member : library.members) {
      System.out.println(member.id + " " + member.name);
    }
  }

  public static void addMember() {
    Member member = new Member();

    System.out.print("id : ");
    member.id = scan.next();

    System.out.print("name : ");
    scan.nextLine();
    member.name = scan.nextLine();

    library.addMember(member);
  }
  public static void addBook() {
	    Book book = new Book();

	    System.out.print("id : ");
	    book.setId(scan.next());

	    System.out.print("Title : ");
	    scan.nextLine();
	    book.setTitle(scan.nextLine());

	    library.addBook(book);
	  }
  public static void borrowBook() {
    System.out.print("id book : ");
    String memberId = scan.next();

    System.out.print("id member : ");
    String bookId = scan.next();
    library.giveBook(memberId, bookId);
  }

  public static void returnBook() {
    System.out.print("id book : ");
    String memberId = scan.next();

    System.out.print("id member : ");
    String bookId = scan.next();
    library.receiveBook(memberId, bookId);
    
  }
}