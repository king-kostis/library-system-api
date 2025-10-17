package com.app.firstRestApi.model;

import java.time.LocalDate;

public class BorrowingRecord{

    private Long id;
    private Book book;
    private Member member;

    private LocalDate borrowDate;
    private LocalDate returnDate;
    private LocalDate dueDate;

    public BorrowingRecord() {}

    public BorrowingRecord(Book book, Member member, LocalDate borrowDate, LocalDate dueDate) {
        this.id = id;
        this.book = book;
        this.member = member;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Book getBook(){
        return book;
    }

    public void setBook(Book book){
        this.book = book;
    }

    public Member getMember(){
        return member;
    }

    public void setMember(Member member){
        this.member = member;
    }

    public LocalDate getBorrowDate(){
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate){
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate(){
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate){
        this.returnDate = returnDate;
    }

    public LocalDate getDueDate(){
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate){
        this.dueDate = dueDate;
    }

    @Override
    public String toString(){
        return "BorrowingRecord{" +
                "id=" + id +
                ", book=" + book +
                ", member=" + member +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                ", dueDate=" + dueDate +
                '}';
    }
}