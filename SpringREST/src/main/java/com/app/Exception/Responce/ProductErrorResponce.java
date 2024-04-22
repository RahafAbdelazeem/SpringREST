package com.app.Exception.Responce;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class ProductErrorResponce {
    private int code;
    private String  message;
     private long timeStamp;
}
