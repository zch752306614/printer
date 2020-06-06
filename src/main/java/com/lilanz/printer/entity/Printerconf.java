package com.lilanz.printer.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Printerconf implements Serializable {
    private Integer id;

    private Integer bid;

    private String templateid;

    private Integer sort;

    private String name;

    private String type;

    private String defaultsValue;

    private Integer wrap;

    private Integer position;

    private Integer width;

    private Integer height;

    private Integer rowHeight;

    private Integer size;

    private Integer rotate;

    private Integer leftOffset;

    private Integer topOffset;

    private Integer enable;

}