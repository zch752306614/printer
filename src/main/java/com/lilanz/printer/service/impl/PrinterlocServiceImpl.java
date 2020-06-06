package com.lilanz.printer.service.impl;

import com.lilanz.printer.dao.PrinterlocMapper;
import com.lilanz.printer.entity.Printerloc;
import com.lilanz.printer.service.PrinterlocService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PrinterlocServiceImpl implements PrinterlocService {

    @Resource
    private PrinterlocMapper printerlocMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return printerlocMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Printerloc record) {
        return printerlocMapper.insert(record);
    }

    @Override
    public Printerloc selectByPrimaryKey(Integer id) {
        return printerlocMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Printerloc> selectAll() {
        return printerlocMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Printerloc record) {
        return printerlocMapper.updateByPrimaryKey(record);
    }
}
