package com.app.firstRestApi.controller;

import com.app.firstRestApi.model.Book;
import com.app.firstRestApi.model.Member;
import com.app.firstRestApi.model.BorrowingRecord;
import com.app.firstRestApi.service.LibraryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        book.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}