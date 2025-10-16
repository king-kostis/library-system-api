package com.app.firstRestApi.controller;

import com.app.firstRestApi.model.Book;
import com.app.firstRestApi.model.Member;
import com.app.firstRestApi.model.BorrowingRecord;
import com.app.firstRestApi.service.LibraryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateRequestCustomizer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LibraryController{

    //Create a logger instance
    private static final Logger logger = LoggerFactory.getLogger(LibraryController.class);

    @Autowired
    private LibraryService libraryService;

    //============Book Endpoints================
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> books = libraryService.getAllBooks();
        logger.info("The list of books returned "+ books);

        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        Optional<Book> book = libraryService.getBookById(id);
        logger.info("The book returned "+book);
        return book.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        libraryService.addBook(book);
        logger.info("The book was added");
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook){
        if (libraryService.getBookById(id).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        updatedBook.setId(id);
        libraryService.updateBook(updatedBook);
        logger.info("The book has been updated"+updatedBook);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @DeleteMapping("book/{id}")
    public ResponseEntity<Book> deleteById(@PathVariable Long id){
        Optional<Book> deletedBook = libraryService.getBookById(id);
        libraryService.deleteBookById(id);
        logger.info("The book has been deleted"+deletedBook);
        return deletedBook.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //=====================Members Endpoints===========================
    @GetMapping("/members")
    public ResponseEntity<List<Member>> getAllMembers(){
        List<Member> members = libraryService.getAllMembers();
        logger.info("The list of members returned"+members);
        
        return new ResponseEntity<>(members, HttpStatus.OK); 
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        Optional<Member> member = libraryService.getMemberById(id);

        return member.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/members")
    public ResponseEntity<Member> addMember(@RequestBody Member member){
        libraryService.addMember(member);
        logger.info("The member has been added"+member);
        return new ResponseEntity<>(member, HttpStatus.CREATED);
    }

    @PutMapping("/members/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Long id, @RequestBody Member updatedMember){
        updatedMember.setId(id);
        if(!libraryService.getMemberById(id).isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        libraryService.updateMember(updatedMember);
        logger.info("The member has been updated"+updatedMember);
        return new ResponseEntity<>(updatedMember, HttpStatus.OK);
    }

    @DeleteMapping("members/{id}")
    public ResponseEntity<Member> deleteMemberById(@PathVariable Long id){
        Optional<Member> deletedMember = libraryService.getMemberById(id);
        libraryService.deleteMemeberById(id);
        logger.info("The member has been deleted"+deletedMember);
        return deletedMember.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}