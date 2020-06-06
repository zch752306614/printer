package com.lilanz.printer.dao;

import com.lilanz.printer.entity.Printer;
import java.util.List;

public interface PrinterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Printer record);

    Printer selectByPrimaryKey(Integer id);

    Printer selectByKey(String key);

    List<Printer> selectAll();

    int updateByPrimaryKey(Printer record);
}