package com.lilanz.printer.service;

import com.lilanz.printer.entity.Printerloc;

import java.util.List;

public interface PrinterlocService {
    int deleteByPrimaryKey(Integer id);

    int insert(Printerloc record);

    Printerloc selectByPrimaryKey(Integer id);

    List<Printerloc> selectAll();

    int updateByPrimaryKey(Printerloc record);
}
