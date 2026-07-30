package org.example;

import org.w3c.dom.ls.LSOutput;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    static void main() {
        bookCase bc = new bookCase();
        bc.bookList();
    }
}

// 책 자체
class Book {
    String bookName;
    String author;
    int callNumber;

    public Book (String bookname, String author, int callNumber){
        this.bookName = bookname;
        this.author = author;
        this.callNumber = callNumber;
    }
}

// 만화책
class Comics extends Book {
    boolean loanAvailable;

    // (+) 같은 책이 여러 권 있는 것도 해 보기... (수량-1 하다가 if 수량 == 0, loanAvailable = false;)
    public Comics(String bookName, String author, int callNumber, boolean loanAvailable) {
        super(bookName, author, callNumber);
        this.loanAvailable = loanAvailable;
    }

    @Override
    public String toString() {
        return bookName + "(" + callNumber + ")" + "-" + author;
    }
}

// 일반 책
class Novels extends Book {
    boolean loanAvailable;

    public Novels(String bookName, String author, int callNumber, boolean loanAvailable) {
        super(bookName, author, callNumber);
        this.loanAvailable = loanAvailable;
    }

    @Override
    public String toString() {
        return bookName + "(" + callNumber + ")" + "-" + author;
    }
}


// 회원
class Member {
    String memberName;
    String loanBook;
    int phoneNumber;
    LocalDate joinDate; // 가입일

    public Member(String memberName, String loanBook, int phoneNumber, LocalDate joinDate){
        this.memberName = memberName;
        this.loanBook = loanBook;
        this.phoneNumber = phoneNumber;
        this.joinDate = joinDate;
    }
}


// 도서 목록
class bookCase {
    ArrayList<Book> bookList = new ArrayList<>();

    public void bookList() {
        bookList.add(new Comics("Comics1", "Kim", 657, true));
        bookList.add(new Comics("Comics2", "Lee", 657, true));

        bookList.add(new Novels("Science", "Park", 400, true));
        bookList.add(new Novels("K-Novel", "Choi", 816, true));
        bookList.add(new Novels("UK-Novel", "Shin", 820, true));

        System.out.println("##############");
        for (int i = 0; i < bookList.size(); i++){
            Book b = bookList.get(i);
            System.out.println((i+1) + ". " + b.toString());

        }
    }

}

// 회원 관리
class memberManage extends Member{
    // 연체도 만들어보기ㅡ...
    public memberManage(String memberName, String loanBook, int phoneNumber, LocalDate joinDate) {
        super(memberName, loanBook, phoneNumber, joinDate);

        memberName
    }
}