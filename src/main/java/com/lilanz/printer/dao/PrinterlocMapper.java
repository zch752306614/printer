package com.lilanz.printer.dao;

import com.lilanz.printer.entity.Printerloc;
import java.util.List;

public interface PrinterlocMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Printerloc record);

    Printerloc selectByPrimaryKey(Integer id);

    List<Printerloc> selectAll();

    int updateByPrimaryKey(Printerloc record);
}