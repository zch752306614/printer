package com.lilanz.printer.service;

import com.lilanz.printer.entity.Printer;

import java.util.List;

public interface PrinterService {
    int deleteByPrimaryKey(Integer id);

    int insert(Printer record);

    Printer selectByPrimaryKey(Integer id);

    List<Printer> selectAll();

    Printer selectByKey(String key);

    int updateByPrimaryKey(Printer record);
}
