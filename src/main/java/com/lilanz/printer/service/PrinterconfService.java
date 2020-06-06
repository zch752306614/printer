package com.lilanz.printer.service;

import com.lilanz.printer.entity.Printerconf;

import java.util.List;

public interface PrinterconfService {
    int deleteByPrimaryKey(Integer id);

    int insert(Printerconf record);

    Printerconf selectByPrimaryKey(Integer id);

    List<Printerconf> selectAll();

    int updateByPrimaryKey(Printerconf record);

    List<Printerconf> selectByTemplate(String id);
}
