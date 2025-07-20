package com.project.lbms.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.lbms.exception.LbmsException;
import com.project.lbms.service.SellerService;
import com.project.lbms.util.LbmsResponseEntityBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/seller")
public class SellerController extends LbmsResponseEntityBuilder{
    private SellerService sellerService;
    private static final String SELLER_CONTROLLER_STR = "SellerController";

    public SellerController(SellerService sellerService){
        this.sellerService = sellerService;
    }

    @GetMapping(path = "/{sellerId}/books", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getSellerBooks(
        @PathVariable(name = "sellerId") String sellerUid,
        @RequestParam(defaultValue = "0") int pageNumber
    ) throws LbmsException{
        log.info("{} getSellerBooks ",SELLER_CONTROLLER_STR);
        return getResponseEntityOk(sellerService.getSellerBooks(sellerUid, pageNumber));
    }

    @GetMapping(path = "/{sellerId}/book/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getSellerBook(
        @PathVariable(name = "sellerId") String sellerUid,
        @PathVariable(name = "id") String bookUid
    ) throws LbmsException{
        log.info("{} getSellerBook {}", SELLER_CONTROLLER_STR, bookUid);
        return getResponseEntityOk(sellerService.getSellerBook(sellerUid, bookUid));
    }
}
