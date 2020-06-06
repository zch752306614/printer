package com.lilanz.printer.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lilanz.printer.entity.Courier;
import com.lilanz.printer.entity.Printer;
import com.lilanz.printer.entity.Printerconf;
import com.lilanz.printer.service.GainschaPrinterService;
import com.lilanz.printer.service.PrinterService;
import com.lilanz.printer.service.PrinterconfService;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping(value = "/printOrder")
    public Map<String, String> print(@RequestBody String data) {
        System.out.println("into...");

        JSONObject jsonObject = JSONObject.parseObject(data);
        System.out.println("jsonObject:" + jsonObject);

        List<Printerconf> list = printerconfService.selectByTemplate(jsonObject.getString("templateid"));
        Printer printer = printerService.selectByKey(jsonObject.getString("key"));

        Map<String, String> message = new HashMap<>();
        try {
            GainschaPrinterService gainschaPrinterService = new GainschaPrinterService();
            gainschaPrinterService.printOrder(printer, jsonObject, list);

            message.put("message", "print success!");
            message.put("code", "1");
            System.out.println("print success!");
        } catch (Exception e) {
            message.put("message", "print fail!");
        } finally {
            return message;
        }
    }
}
