package com.lilanz.printer.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Printerloc implements Serializable {
    private Integer id;

    private String printerkey;

    private String locid;

    private Date createtime;

    private String editor;

    private static final long serialVersionUID = 1L;
}