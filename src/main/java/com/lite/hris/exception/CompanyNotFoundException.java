package com.lite.hris.exception;

public class CompanyNotFoundException extends RuntimeException{
    public CompanyNotFoundException(long id){
        super("Company (ID:"+id+") is not found");
    }
}
