package com.lite.hris.exception;

public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException(long id) {
        super("Department (ID:"+id+") is not found");
    }
}
