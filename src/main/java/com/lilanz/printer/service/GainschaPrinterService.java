package com.lilanz.printer.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gp.command.EscCommand;
import com.gp.command.LabelCommand;
import com.gp.port.Internet;
import com.lilanz.printer.entity.Printer;
import com.lilanz.printer.entity.Printerconf;
import com.lilanz.printer.entity.Printertemplate;
import com.lilanz.printer.exception.MyException;
import com.lilanz.printer.util.PdfToImage;
import com.lilanz.printer.util.judgeField;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.*;
import java.util.List;
import java.util.Vector;

@Component
public class GainschaPrinterService {

    private LabelCommand tsc;

    private int h = 0;
    private int w = 0;

    @Resource
    private PrinterconfService printerconfService;

    public static GainschaPrinterService gainschaPrinterService;

    @PostConstruct
    public void init() {
        gainschaPrinterService = this;
        gainschaPrinterService.printerconfService = this.printerconfService;
    }

    public GainschaPrinterService() {
        tsc = new LabelCommand();
        tsc.addSize(100, 150);
        tsc.addCls();
        //添加初始化命令
        tsc.addResetPrinter();
        //设置标签长宽
        tsc.addSize(100, 150);
        //清除缓存，必须放在addSize后面才生效
        tsc.addCls();
        //设置标签间隙
        tsc.addGap(2);
        //设置打印浓度
        tsc.addDensity(LabelCommand.DENSITY.DNESITY15);
        //设置打印速度
        //tsc.addSpeed(LabelCommand.TSCSPEED.SPEED4);
        //设置打印方向
        tsc.addDirection(LabelCommand.DIRECTION.FORWARD, LabelCommand.MIRROR.NORMAL);
        //设置原点坐标
        tsc.addReference(0, 0);
        //查询打印机状态
        //tsc.addQueryPrinterStatus();
        System.out.println("打印机初始化成功！");
    }

    public GainschaPrinterService(Printertemplate printertemplate) {
        tsc = new LabelCommand();
        tsc.addSize(printertemplate.getPaperWidth(), printertemplate.getPaperHeight());
        tsc.addCls();
        //添加初始化命令
        tsc.addResetPrinter();
        //设置标签长宽
        tsc.addSize(printertemplate.getPaperWidth(), printertemplate.getPaperHeight());
        //清除缓存，必须放在addSize后面才生效
        tsc.addCls();
        //设置标签间隙
        tsc.addGap(printertemplate.getPaperGap());
        //设置打印浓度
        tsc.addDensity(LabelCommand.DENSITY.DNESITY15);
        //设置打印速度
        //tsc.addSpeed(LabelCommand.TSCSPEED.SPEED4);
        //设置打印方向
        tsc.addDirection(LabelCommand.DIRECTION.FORWARD, LabelCommand.MIRROR.NORMAL);
        //设置原点坐标
        tsc.addReference(0, 0);
        //查询打印机状态
        //tsc.addQueryPrinterStatus();
        System.out.println("打印机初始化成功！");
    }

    //判断是否连接
    public static void internetConnect(Printer printer, Internet internet) {
        //internet = new Internet();
        System.out.println(printer);
        boolean isConnect = internet.openNetPort(printer.getIp(), printer.getPort());
        if (isConnect)
            System.out.println("connect success!");
        else {
            throw new MyException("打印机连接不成功:" + printer.getIp() + ":" + printer.getPort());
        }
    }

    //判断是否断开连接
    public static void internetDisconnect(Internet internet) {
        boolean isDisconnect = internet.closeNetPort();
        if (isDisconnect) {
            System.out.println("disconnect success!");
        } else {
            System.out.println("disconnect fail!");
        }
    }

    //判断是否发送成功
    public static void sendSuccess(boolean isSend) {
        if (isSend) {
            System.out.println("sendMessage success!");
        } else {
            throw new MyException("打印机发送数据失败！");
        }
    }

    //发送指令
    public void sendPrint(Internet internet) {
        tsc.addPrint(1);
        Vector<Byte> data = tsc.getCommand();
        byte[] byteData = new byte[data.size()];
        for (int i = 0; i < data.size(); i++) {
            byteData[i] = data.get(i);
        }
        //发送
        boolean isSend = internet.sendMessage(byteData);
        if (isSend) {
            System.out.println("sendMessage success!");
        } else {
            System.out.println("sendMessage fail!");
        }
    }

    //获取旋转角度
    public LabelCommand.ROTATION getRotation(int rotate) {
        switch (rotate) {
            case 90:
                return LabelCommand.ROTATION.ROTATION_90;
            case 180:
                return LabelCommand.ROTATION.ROTATION_180;
            case 270:
                return LabelCommand.ROTATION.ROTATION_270;
            default:
                return LabelCommand.ROTATION.ROTATION_0;
        }
    }

    //获取字体大小
    public LabelCommand.FONTMUL getFontMul(int size) {
        switch (size) {
            case 2:
                return LabelCommand.FONTMUL.MUL_2;
            case 3:
                return LabelCommand.FONTMUL.MUL_3;
            case 4:
                return LabelCommand.FONTMUL.MUL_4;
            case 5:
                return LabelCommand.FONTMUL.MUL_5;
            case 6:
                return LabelCommand.FONTMUL.MUL_6;
            case 7:
                return LabelCommand.FONTMUL.MUL_7;
            case 8:
                return LabelCommand.FONTMUL.MUL_8;
            case 9:
                return LabelCommand.FONTMUL.MUL_9;
            default:
                return LabelCommand.FONTMUL.MUL_1;
        }
    }

    //获取打印矩形指令
    public void getBoxData(int starX, int starY, int endX, int endY, int thickness) {
        //添加打印矩形指令
        tsc.addBox(starX, starY, endX, endY, thickness);
    }

    //获取打印文字指令
    public void getTextData(int starX, int starY, int size, int rotate ,String text) {
        //添加指令
        tsc.addText(starX, starY, LabelCommand.FONTTYPE.SIMPLIFIED_CHINESE,
                getRotation(rotate), getFontMul(size), getFontMul(size), text);
    }

    //获取打印线条指令
    public void getBarData(int starX, int starY, int width, int height) {
        //添加打印线条指令
        tsc.addBar(starX, starY, width, height);
    }

    //获取打印条形码指令
    public void getBarcodeData(int x, int y, int size, int rotate, String text) {
        //添加打印条形码指令
        tsc.add1DBarcode(x, y, LabelCommand.TSCBARCODETYPE.CODE128, size, LabelCommand.READABEL.DISABLE,
                getRotation(rotate), text);
    }

    //获取打印二维码指令
    public void getQRcodeData(int x, int y, int size, int rotate, String text) {
        //添加打印二维码指令
        tsc.addQRCode(x, y, LabelCommand.EEC.LEVEL_L, size, getRotation(rotate), text);
    }

    //获取打印图片指令
    private void getPictureData(int x, int y, int width, int height, String path) {
        try {
            //获取目标图片的本地路径
            String Spath = path.trim();
            //int index = Spath.lastIndexOf(".jpg");
            //获取图片转换成位图后的本地存储路径
            //String Lpath = new StringBuilder(Spath).insert(index, "_bitMap").toString();
            String Lpath = Spath.replace(".jpg",".bmp");
            Lpath = Lpath.replace(".png",".bmp");
            System.out.println(Spath + "\n" + Lpath);
            //转换成位图
            String Bpath = bigorsmall(width, height, eve2bmp(Spath, Lpath), Lpath);
            System.out.println(Bpath);
            //添加打印位图指令
            tsc.addBitmap(Bpath, x, y, LabelCommand.BITMAP_MODE.OVERWRITE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //打印矩形
    public void printBox(int starX, int statY, int endX, int endY, int thickness, Printer printer) {
        Internet internet = new Internet();
        internetConnect(printer, internet);
        getBoxData(starX, statY, endX, endY, thickness);
        sendPrint(internet);
        internetDisconnect(internet);
    }

    //打印文字
    public void printText(int starX, int statY, int size, int rotate, String text, Printer printer) {
        Internet internet = new Internet();
        internetConnect(printer, internet);
        getTextData(starX, statY, size, rotate, text);
        sendPrint(internet);
        internetDisconnect(internet);
    }

    //打印线条
    public void printBar(int starX, int starY, int width, int height, Printer printer) {
        Internet internet = new Internet();
        internetConnect(printer, internet);
        getBarData(starX, starY, width, height);
        sendPrint(internet);
        internetDisconnect(internet);
    }

    private String bigorsmall(int mWidth, int mHeight, String Spath, String Lpath) {
        try {
            FileInputStream fileInputStream = new FileInputStream(Spath);
            BufferedImage srcImg = ImageIO.read(fileInputStream);

            int i = srcImg.getHeight((ImageObserver) null) * mWidth / 8 * 7;
            int height = i / srcImg.getWidth((ImageObserver) null);
            Image smallImg = srcImg.getScaledInstance(mWidth, mHeight, 1);
            BufferedImage tag = new BufferedImage(mWidth, mHeight, 12);
            Graphics g = tag.getGraphics();
            g.drawImage(smallImg, 0, 0, (ImageObserver) null);
            FileOutputStream fileOutputStream = new FileOutputStream(Lpath);
            ImageIO.write(tag, "BMP", fileOutputStream);

            fileOutputStream.close();
            g.dispose();
            tag.flush();
            smallImg.flush();
            srcImg.flush();
            fileInputStream.close();
        } catch (FileNotFoundException var10) {
            var10.printStackTrace();
        } catch (IOException var11) {
            var11.printStackTrace();
        }
        return Lpath;
    }

    public static String eve2bmp(String Spath, String Lpath) {
        try {
            FileInputStream fileInputStream = new FileInputStream(Spath);
            BufferedImage bufferedImage = ImageIO.read(fileInputStream);
            BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), 12);
            newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, (ImageObserver) null);
            FileOutputStream fileOutputStream = new FileOutputStream(Lpath);
            ImageIO.write(newBufferedImage, "BMP", fileOutputStream);

            fileOutputStream.close();
            newBufferedImage.flush();
            bufferedImage.flush();
            fileInputStream.close();
        } catch (IOException var5) {
            var5.printStackTrace();
        }
        return Lpath;
    }

    //打印图片
    public void printImg(String path, Printer printer, int star, int end) {
        System.out.println(path);
        path.replaceAll("\\\\", "/");
        System.out.println(path);
        int indexE = path.lastIndexOf(".pdf");
        int indexS = path.lastIndexOf("/");
        String fileAddress = path.substring(0, indexS);
        String fileName = path.substring(indexS, indexE);
        PdfToImage.pdf2png(fileAddress, fileName, star, end, "png");
        Internet internet = null;
        try {

            internet = new Internet();
            internetConnect(printer, internet);
            for (int i = star; i < end; i++) {
                String picPath = fileAddress + fileName + "_" + (i + 1) + ".png";
                //这个800可以用作宽度的打印
                getPictureData(0, 0, 800,1500, picPath);
                sendPrint(internet);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException(e.getMessage());
        } finally {
            if (internet != null) {
                internetDisconnect(internet);
            }
        }
    }

    public void printerConf(JSONObject jsonObject, List<Printerconf> list){
        for (Printerconf printerconf : list) {
            int leftOffset = printerconf.getLeftOffset();
            int topOffset = printerconf.getTopOffset();
            int wrap = printerconf.getWrap();
            int width = printerconf.getWidth();
            int height = printerconf.getHeight();
            int rowHeight = printerconf.getRowHeight();
            int size = printerconf.getSize();
            int rotate = printerconf.getRotate();
            int position = printerconf.getPosition();
            int enable = printerconf.getEnable();
            String templateID = printerconf.getTemplateid();
            String name = printerconf.getName();
            String type = printerconf.getType();
            String defaults = printerconf.getDefaultsValue();
            String path = "";
            String text = defaults;

            //横坐标
            int x = w;
            int y = h;
            x += leftOffset;
            y += topOffset;
            //position==1为绝对定位，y的值为偏移量
            if (position == 1) {
                x = leftOffset;
                y = topOffset;
            }

            if (judgeField.isExistFieldByJson(name, jsonObject)) {
                text = defaults.replaceAll("\\{\\$}", jsonObject.getString(name));
            }

            //判断是否启用该配置
            if(enable == 0){
                continue;
            }
            //判断打印类型
            if (type.equals("text")) {
                getTextData(x, y, size, rotate, text);
            } else if (type.equals("line")) {
                getBarData(x, y, width, height);
            } else if (type.equals("rectangle")) {
                getBoxData(x, y, x + width, y + height, size);
            } else if (type.equals("picture")) {
                getPictureData(x, y, width, height, text);
            } else if (type.equals("qrCode")) {
                getQRcodeData(x, y, size, rotate, text);
            } else if (type.equals("barCode")) {
                getBarcodeData(x, y, size, rotate, text);
            } else if (type.equals("list")) {
                //JSONObject object = JSONObject.parseObject(defaults);
                //System.out.println("defaults:" + object);//
                if (judgeField.isExistFieldByJson(name, jsonObject)) {
                    List<Printerconf> pcList = gainschaPrinterService.printerconfService.selectByTemplate(defaults);
                    JSONArray jsonArray = jsonObject.getJSONArray(name);
                    System.out.println("orderListConf:" + pcList);//
                    System.out.println("jsonArray:" + jsonArray);//
                    System.out.println("listSize:" + jsonArray.size());//
                    for (int i = 0; i < jsonArray.size(); i++) {
                        JSONObject order = jsonArray.getJSONObject(i);
                        System.out.println("order-" + (i + 1) + ":" + order);//
                        printerConf(order, pcList);
                    }
                }
            }

            //position==0为相对定位，并且此处需要换行，y坐标增加
            if(position == 0) {
                if (wrap == 1) {
                    h += rowHeight - topOffset;
                }else if(wrap == 0){
                    //w += width;
                }
            }
        }
    }

    public void printOrder(Printer printer, JSONObject jsonObject, List<Printerconf> list) {
        Internet internet = null;
        try {
            internet = new Internet();
            internetConnect(printer, internet);

            //起始纵坐标
            int w = 0;
            int h = 0;
            printerConf(jsonObject, list);
            //发送指令
            sendPrint(internet);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException(e.getMessage());
        } finally {
            if (internet != null) {
                internetDisconnect(internet);
            }
        }
    }

    public static void main(String[] args) {
        Internet internet =new Internet();
        internet.openNetPort("192.168.36.254", 9100);
        LabelCommand tsc = new LabelCommand();
        tsc.addSize(100, 150);
        tsc.addCls();
        tsc.addResetPrinter();
        tsc.addSize(100, 150);
        tsc.addCls();
        tsc.addGap(2);
        tsc.addDensity(LabelCommand.DENSITY.DNESITY15);
        //设置打印速度
        //tsc.addSpeed(LabelCommand.TSCSPEED.SPEED4);
        tsc.addDirection(LabelCommand.DIRECTION.FORWARD, LabelCommand.MIRROR.NORMAL);
        tsc.addReference(0, 0);
        /*tsc.addCodePage(LabelCommand.CODEPAGE.PC850);
        tsc.addText(10,150, LabelCommand.FONTTYPE.SIMPLIFIED_CHINESE, LabelCommand.ROTATION.ROTATION_0,
                LabelCommand.FONTMUL.MUL_2, LabelCommand.FONTMUL.MUL_2,"测试内容11111aaaaa");
        tsc.addPrint(1);*/
        Vector<Byte> data = tsc.getCommand();

        String str = "TEXT 10,100,\"1\",0,1,1,\"1号字体\"\r\n";
        str += "TEXT 10,150,\"2\",0,1,1,\"2号字体\"\r\n";
        str += "TEXT 10,200,\"3\",0,1,1,\"3号字体\"\r\n";
        str += "TEXT 10,250,\"4\",0,1,1,\"4号字体\"\r\n";
        str += "TEXT 10,300,\"5\",0,1,1,\"5号字体\"\r\n";
        str += "TEXT 10,350,\"6\",0,1,1,\"6号字体\"\r\n";
        str += "TEXT 10,400,\"7\",0,1,1,\"7号字体\"\r\n";
        str += "TEXT 10,450,\"8\",0,1,1,\"8号字体\"\r\n";
        str += "TEXT 10,500,\"9\",0,1,1,\"9号字体\"\r\n";
        str += "TEXT 10,550,\"10\",0,1,1,\"10号字体\"\r\n";
        str += "TEXT 10,600,\"11\",0,1,1,\"11号字体\"\r\n";
        str += "TEXT 10,600,\"12\",0,1,1,\"12号字体\"\r\n";
        str += "TEXT 10,600,\"55\",0,1,1,\"55号字体\"\r\n";
        str += "PRINT 1\r\n";
        byte[] bs = null;
        if (!str.equals("")) {
            try {
                bs = str.getBytes("GB2312");
            } catch (UnsupportedEncodingException var4) {
                var4.printStackTrace();
            }

            for(int i = 0; i < bs.length; ++i) {
                data.add(bs[i]);
            }
        }

        byte[] byteData = new byte[data.size()];
        for (int i = 0; i < data.size(); i++) {
            byteData[i] = data.get(i);
        }
        internet.sendMessage(byteData);
        internet.closeNetPort();
        System.out.println("success!");
    }

}
