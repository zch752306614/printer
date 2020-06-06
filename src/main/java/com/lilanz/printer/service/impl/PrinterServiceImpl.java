package com.lilanz.printer.service.impl;

import com.lilanz.printer.dao.PrinterMapper;
import com.lilanz.printer.entity.Printer;
import com.lilanz.printer.service.PrinterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PrinterServiceImpl implements PrinterService {

    @Resource
    private PrinterMapper printerMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return printerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Printer record) {
        return printerMapper.insert(record);
    }

    @Override
    public Printer selectByPrimaryKey(Integer id) {
        return printerMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Printer> selectAll() {
        return printerMapper.selectAll();
    }

    @Override
    public Printer selectByKey(String key) {
        return printerMapper.selectByKey(key);
    }

    @Override
    public int updateByPrimaryKey(Printer record) {
        return printerMapper.updateByPrimaryKey(record);
    }
}
