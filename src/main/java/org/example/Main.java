package org.example;

import org.w3c.dom.ls.LSOutput;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void pm() {
        System.out.println("##### 필요한 기능 입력 #####");
        System.out.println("1. 회원 메뉴 2. 도서 메뉴");
    }

    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String menu = ("1. 등록 2. 삭제 3. 전체 회원 4. 조회");
        String menu2 = ("1. 대출 2. 반납 3. 조회");

        bookCase bc = new bookCase();
        memberManage mMg = new memberManage();
        //bookManage bMg = new bookManage();

        while(true){
            pm();
            System.out.print("숫자만 입력(종료 0): ");
            int mainNum = sc.nextInt();

            if (mainNum == 1){ // 회원 메뉴
                System.out.println(menu);
                System.out.print("숫자만 입력(종료 0): ");

                int subNum = sc.nextInt();

                if (subNum == 1){ // 회원 등록
                    mMg.addMember();
                    System.out.println();
                    //pm();
                }

                if (subNum == 2){ // 회원 삭제
                    mMg.rvMember();
                    //pm();
                }

                if (subNum == 3){ // 전체 회원
                    mMg.allMember();
                    //pm();
                }

                if (subNum == 4){ // 회원 조회
                    mMg.searchMember();
                    //pm();
                }

            }

            if (mainNum == 2){ // 도서 메뉴
                System.out.println(menu2);
                System.out.print("숫자만 입력(종료 0): ");

                int subNum = sc.nextInt();

                if (subNum == 1){ // 대출
                    bc.bookList();
                    System.out.println();
                    bc.borrowBook();
                }

                if (subNum == 2){ // 반납
                    bc.returnBook();
                }

                if (subNum == 3){ // 조회
                    bc.searchBook();
                }
            }

            if (mainNum == 0){ // 종료
                break;
            }
        }
    }
}



// 책 자체
class Book {
    String bookName;
    String author;
    int callNumber;
    boolean loanAvailable;

    public Book (String bookname, String author, int callNumber, boolean loanAvailable){
        this.bookName = bookname;
        this.author = author;
        this.callNumber = callNumber;
        this.loanAvailable = loanAvailable;
    }
}

// 만화책
class Comics extends Book {

    // (+) 같은 책이 여러 권 있는 것도 해 보기... (수량-1 하다가 if 수량 == 0, loanAvailable = false;)
    public Comics(String bookName, String author, int callNumber, boolean loanAvailable) {
        super(bookName, author, callNumber, loanAvailable);
    }

    @Override
    public String toString() {
        return bookName + "(" + callNumber + ")" + "-" + author;
    }
}

// 일반 책
class Novels extends Book {

    public Novels(String bookName, String author, int callNumber, boolean loanAvailable) {
        super(bookName, author, callNumber, loanAvailable);
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
    String phoneNumber;
    String joinDate; // 가입일
    int memberID;

    ArrayList<Member> memberList = new ArrayList<>();
    Random rd = new Random();
    Scanner sc = new Scanner(System.in);

    public Member(String memberName, String phoneNumber, String joinDate, int memberID){
        this.memberName = memberName;
        this.phoneNumber = phoneNumber;
        this.joinDate = joinDate;
        this.memberID = memberID;
    }

    @Override
    public String toString(){
        return "이름 - " + memberName + ", 번호 - " + phoneNumber + ", 가입일 - " + joinDate + ", 아이디 - " + memberID;
    }

}


// 도서 목록과 관리 (대출 가능 여부 표기되도록 수정하기)
class bookCase {
    ArrayList<Book> bookList = new ArrayList<>();

    public bookCase(){
        bookList.add(new Comics("Comics1", "Kim", 657, true));
        bookList.add(new Comics("Comics2", "Lee", 657, true));

        bookList.add(new Novels("Science", "Park", 400, true));
        bookList.add(new Novels("K-Novel", "Choi", 816, true));
        bookList.add(new Novels("UK-Novel", "Shin", 820, true));

    }

    public void bookList() {
        System.out.println();
        System.out.println("[책장]");
        System.out.println("##############");
        for (int i = 0; i < bookList.size(); i++){
            Book b = bookList.get(i);
            System.out.println((i+1) + ". " + b.toString());
        }
    }

    // 대출 기능
    public void borrowBook(){
        Scanner sc = new Scanner(System.in);
        boolean isFound = false;
        System.out.print("대출 도서명: ");
        String loanName = sc.next(); // 빌릴 책 이름

        for (int j = 0; j<bookList.size(); j++){
            Book b = bookList.get(j);

            if(b.bookName.equals(loanName)) {
                isFound = true;

                if(b.loanAvailable == false){
                    System.out.println("대출 중인 도서");
                    System.out.println();
                    break;
                }

                System.out.println("대출 완료");
                System.out.println();

                b.loanAvailable = false;
                //isFound = true;
                break;
            }
        }

        if(!isFound){
            System.out.println("등록되지 않은 책");
            System.out.println();
        }
    }

    // 반납 기능
    public void returnBook(){
        Scanner sn = new Scanner(System.in);
        boolean isFound = false;
        System.out.print("반납 도서명: ");
        String rtName = sn.next(); // 반납할 책 이름

        for (int j = 0; j<bookList.size(); j++){
            Book b = bookList.get(j);

            if(b.bookName.equals(rtName)) {
                System.out.println("반납 완료");
                System.out.println();

                b.loanAvailable = true;
                isFound = true;
                break;
            }
        }

        if(!isFound){
            System.out.println("등록되지 않은 책");
            System.out.println();
        }
    }

    // 조회 기능
    public void searchBook(){
        Scanner sc = new Scanner(System.in);
        boolean isFound = false;

        System.out.print("조회할 도서명 입력: ");
        String findName = sc.next();

        for (int j = 0; j<bookList.size(); j++){
            Book b = bookList.get(j);

            if(b.bookName.equals(findName)){
                System.out.println("일치하는 도서 정보: " + b);
                System.out.println();
                isFound = true;
                break;
            }
        }

        if(!isFound){
            System.out.println("등록되지 않은 책");
        }
    }
}

// 회원 관리
class memberManage{

    ArrayList<Member> memberList = new ArrayList<>();
    Random rd = new Random();
    Scanner sc = new Scanner(System.in);

    // 등록
    public void addMember (){
        System.out.print("이름 입력: ");
        String scName = sc.nextLine();

        System.out.print("번호 입력: ");
        String scNum = sc.nextLine();

        System.out.print("가입일 입력: ");
        String scJoin = sc.nextLine();

        int memberID = rd.nextInt(50000);

        memberList.add(new Member(scName, scNum, scJoin, memberID));

        System.out.println("등록 완료: 이름 - " + scName + ", 전화번호 - " + scNum + ", 가입일 - " + scJoin + ", 아이디 - " + memberID);
        System.out.println();
    }

    // 삭제
    public void rvMember() {
        boolean isDeleted = false;

        System.out.print("삭제할 회원의 아이디: ");
        int findID = sc.nextInt();

        for (int j = 0; j<memberList.size(); j++){
            Member m = memberList.get(j);

            if(findID == m.memberID){
                memberList.remove(j);
                System.out.println("삭제 완료");

                isDeleted = true;
                break;
            }
        }

        if(!isDeleted){
            System.out.println("등록되지 않은 회원");
            System.out.println();
        }
    }

    // 전체 회원 출력
    public void allMember(){
        for (int i = 0; i < memberList.size(); i++){
            System.out.println(memberList.get(i));
        }
        System.out.println();

        if (memberList.isEmpty()){
            System.out.println("비어있음");
        }
        System.out.println();
    }

    // 조회
    public void searchMember(){
        boolean isFound = false;

        System.out.print("조회할 이름 입력: ");
        String findName = sc.next();

        for (int j = 0; j<memberList.size(); j++){
            Member m = memberList.get(j);

            if(m.memberName.equals(findName)){
                System.out.println("일치하는 회원 정보: " + m);
                System.out.println();

                isFound = true;
                break;
            }
        }

        if(!isFound){
            System.out.println("등록되지 않은 회원");
        }
    }
}