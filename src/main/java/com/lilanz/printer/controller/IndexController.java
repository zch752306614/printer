package com.lilanz.printer.controller;

import com.alibaba.fastjson.JSONObject;
import com.lilanz.printer.entity.Printer;
import com.lilanz.printer.entity.Printerconf;
import com.lilanz.printer.entity.Printertemplate;
import com.lilanz.printer.service.GainschaPrinterService;
import com.lilanz.printer.service.PrinterService;
import com.lilanz.printer.service.PrinterconfService;
import com.lilanz.printer.service.PrintertemplateService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class IndexController {

    @Resource
    private PrinterService printerService;
    @Resource
    private PrinterconfService printerconfService;
    @Resource
    private PrintertemplateService printertemplateService;

    @RequestMapping(value = "/printOrder")
    public Map<String, String> print(@RequestBody String data) {
        System.out.println("into...");

        JSONObject jsonObject = JSONObject.parseObject(data);
        System.out.println("jsonObject:" + jsonObject);

        Printertemplate printertemplate = printertemplateService.selectByPrimaryKey(Integer.parseInt(jsonObject.getString("templateid")));
        List<Printerconf> list = printerconfService.selectByTemplate(jsonObject.getString("templateid"));
        Printer printer = printerService.selectByKey(jsonObject.getString("key"));

        Map<String, String> message = new HashMap<>();
        try {
            GainschaPrinterService gainschaPrinterService = new GainschaPrinterService(printertemplate);
            gainschaPrinterService.printOrder(printer, jsonObject, list);

            message.put("errcode", "0");
            message.put("errmsg", "print success!");
            System.out.println("print success!");
        } catch (Exception e) {
            message.put("errcode", "-1");
            message.put("errmsg", "print fail!");
        }
        return message;
    }
}
