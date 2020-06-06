package com.lilanz.printer.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Printer implements Serializable {
    private Integer id;

    private String mark;

    private String ip;

    private Integer port;

    private String key;

    private Date createtime;

    private String editor;

    private Integer isvalid;

    private static final long serialVersionUID = 1L;
}