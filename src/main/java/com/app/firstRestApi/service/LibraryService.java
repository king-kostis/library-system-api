package com.app.firstRestApi.service;

import com.app.firstRestApi.model.BorrowingRecord;
import com.app.firstRestApi.model.Book;
import com.app.firstRestApi.model.Member;
import com.app.firstRestApi.model.BorrowingRecord;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibraryService{

    private List<Book> books = new ArrayList<>();
    private List<Member> members = new ArrayList<>();
    private List<BorrowingRecord> borrowingRecords = new ArrayList<>();

    //===================Book Methods=====================
    public List<Book> getAllBooks(){
        return books;
    }

    public Optional<Book> getBookById(Long id){
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();
    }

    public void addBook(Book book){
        books.add(book);
    }

    public void updateBook(Book updatedBook){
        for (int i = 0; i < books.size(); i++){
            if (books.get(i).getId().equals(updatedBook.getId())){
                books.set(i, updatedBook);
                break;
            }
        }
    }

    public void deleteBookById(Long id){
        books.remove(id);
    }

    public Collection getBooksByGenre(String genre){
        return books.stream()
                .filter(book -> book.getGenre().equals(genre))
                .collect(Collectors.toList());
    }

    //=================Members Methods===================
    public List<Member> getAllMembers(){
        return members;
    }

    public Optional<Member> getMemberById(Long id){
        return members.stream()
                .filter(member -> member.getId().equals(id))
                .findFirst();
    }

    public void addMember(Member member){
        members.add(member);
    }

    public void updateMember(Member updatedMember) {
        for(int i = 0; i < members.size(); i++){
            if(members.get(i).getId().equals(updatedMember.getId())){
                members.set(i, updatedMember);
                break;
            }
        }
    }

    public void deleteMemeberById(Long id){
        members.remove(id);
    }

    //========================BorrowingRecords Methods============================
    public List<BorrowingRecord> getAllBorrowingRecords(){
        return borrowingRecords;
    }

    public void borrowBook(BorrowingRecord borrowingRecord){
        //Create a new borrowing record and set the borrow date and due date
        borrowingRecord.setBorrowDate(LocalDate.now());
        borrowingRecord.setDueDate(LocalDate.now().plusDays(14));
        borrowingRecords.add(borrowingRecord);

        //Decrease available copies of the book
        Book book = borrowingRecord.getBook();
        book.setAvailableCopies(book.getAvailableCopies() - 1);
    }

    public void returnBook(Long recordId, LocalDate returnDate){
        for(BorrowingRecord record : borrowingRecords){
            if (record.getId().equals(recordId)){
                record.setReturnDate(returnDate);

                Book book = record.getBook();
                book.setAvailableCopies(book.getAvailableCopies() + 1);
                break;
            }
        }
    }
}