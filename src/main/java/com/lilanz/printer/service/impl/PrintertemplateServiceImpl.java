package com.lilanz.printer.service.impl;

import com.lilanz.printer.dao.PrintertemplateMapper;
import com.lilanz.printer.entity.Printertemplate;
import com.lilanz.printer.service.PrintertemplateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PrintertemplateServiceImpl implements PrintertemplateService {

    @Resource
    private PrintertemplateMapper printertemplateMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return printertemplateMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Printertemplate record) {
        return printertemplateMapper.insert(record);
    }

    @Override
    public Printertemplate selectByPrimaryKey(Integer id) {
        return printertemplateMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Printertemplate> selectAll() {
        return printertemplateMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Printertemplate record) {
        return printertemplateMapper.updateByPrimaryKey(record);
    }
}
