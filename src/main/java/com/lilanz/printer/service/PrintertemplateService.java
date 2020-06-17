package com.lilanz.printer.service;

import com.lilanz.printer.entity.Printertemplate;

import java.util.List;

public interface PrintertemplateService {
    int deleteByPrimaryKey(Integer id);

    int insert(Printertemplate record);

    Printertemplate selectByPrimaryKey(Integer id);

    List<Printertemplate> selectAll();

    int updateByPrimaryKey(Printertemplate record);
}
