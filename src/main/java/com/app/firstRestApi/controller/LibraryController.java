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

@RestController
@RequestMapping("/api")
public class LibraryController{

    //Create a logger instance
    private static final Logger logger = LoggerFactory.getLogger(LibraryController.class);

    @Autowired
    private LibraryService libraryService;

}