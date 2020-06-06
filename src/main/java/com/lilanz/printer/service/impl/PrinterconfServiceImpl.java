package com.lilanz.printer.service.impl;

import com.lilanz.printer.dao.PrinterconfMapper;
import com.lilanz.printer.entity.Printerconf;
import com.lilanz.printer.service.PrinterconfService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PrinterconfServiceImpl implements PrinterconfService {

    @Resource
    private PrinterconfMapper printerconfMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return printerconfMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Printerconf record) {
        return printerconfMapper.insert(record);
    }

    @Override
    public Printerconf selectByPrimaryKey(Integer id) {
        return printerconfMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Printerconf> selectAll() {
        return printerconfMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Printerconf record) {
        return printerconfMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Printerconf> selectByTemplate(String id) {
        return printerconfMapper.selectByTemplate(id);
    }
}
