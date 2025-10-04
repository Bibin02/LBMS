package com.project.lbms.constants;

import java.net.URI;
import java.net.URISyntaxException;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LbmsConstants {

    public static final Integer PAGE_SIZE = 10;
    public static final String NOT_FOUND = " Not Found for the given id ";
    public static final String BOOK_NOT_FOUND = "Book" + NOT_FOUND;
    public static final String ORDER_NOT_FOUND = "Order" + NOT_FOUND;
    public static final String CART_NOT_FOUND = "Cart" + NOT_FOUND;
    public static final String USER_NOT_FOUND = "User" + NOT_FOUND;
    public static final String SELLER_NOT_FOUND = "Seller" + NOT_FOUND;
    public static final String FIELD_IS_REQUIRED = " field is required";
    public static final String ROLE_CONSTRAIN_REGEX = "(?:ADMIN|BUYER|SELLER)";
    public static final String COUNT_QUERY = "SELECT COUNT(*) from (";
    public static final String UNAUTHORIZED_USER = "Unauthorized User";

    public static URI createUri(String uri){
        try {
            return new URI(uri);
        } catch (URISyntaxException e) {
            return null;
        }
    }
}
