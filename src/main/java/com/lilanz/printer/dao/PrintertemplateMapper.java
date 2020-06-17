package com.lilanz.printer.dao;

import com.lilanz.printer.entity.Printertemplate;
import java.util.List;

public interface PrintertemplateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Printertemplate record);

    Printertemplate selectByPrimaryKey(Integer id);

    List<Printertemplate> selectAll();

    int updateByPrimaryKey(Printertemplate record);
}