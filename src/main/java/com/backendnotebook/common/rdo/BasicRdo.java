package com.backendnotebook.common.rdo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BasicRdo<T> {
    public Boolean status;
    public String message;
    public T data;
    public Long total;
    public Long pageNumber;
    public Long pageSize;

    public BasicRdo() {
    }

    public BasicRdo(Boolean status, String message, T data, Long total, Long pageNumber, Long pageSize) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.total = total;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public ResponseEntity<BasicRdo> getResponse(String message, HttpStatus responseStatus, T object) {
        BasicRdo<T> basicRdo = new BasicRdo<>();
        basicRdo.message = message;
        if (object == null) {
            basicRdo.status = false;
        } else {
            basicRdo.status = true;
            basicRdo.data = data;
        }

        return new ResponseEntity<BasicRdo>(basicRdo, responseStatus);
    }

    public ResponseEntity<BasicRdo> getResponse(String sucess, HttpStatus responseStatus, T object,
                                                Long pageNo, Long pageSize, Long total) {
        BasicRdo<T> basicRdo = new BasicRdo<>();
        basicRdo.message = message;
        if (object == null) {
            basicRdo.status = false;
        } else {
            basicRdo.status = true;
            basicRdo.data = object;
        }

        this.pageSize =pageSize;
        this.pageNumber = pageNo;
        this.total = total;
        return new ResponseEntity<BasicRdo>(basicRdo, responseStatus);
    }
}
