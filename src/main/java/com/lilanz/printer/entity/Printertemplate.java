package com.lilanz.printer.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Printertemplate implements Serializable {
    private Integer id;

    private String templateid;

    private String templateName;

    private Integer paperWidth;

    private Integer paperHeight;

    private Date creatTime;

    private String creator;

    private Integer paperGap;

}