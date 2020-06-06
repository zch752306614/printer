package com.lilanz.printer.util;

import com.lilanz.printer.exception.MyException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class PdfToImage {

    public static void pdf2png(String fileAddress, String filename, int indexOfStart, int indexOfEnd, String type) {
        // 将pdf装图片 并且自定义图片得格式大小
        File file = new File((fileAddress + "\\" + filename + ".pdf").replaceAll("\\\\", "/"));
        //File file = new File(fileAddress);
        try {
            PDDocument doc = PDDocument.load(file);
            PDFRenderer renderer = new PDFRenderer(doc);
            int pageCount = doc.getNumberOfPages();
            for (int i = indexOfStart; i < indexOfEnd; i++) {
                BufferedImage image = renderer.renderImageWithDPI(i, 288); // Windows native DPI
                //BufferedImage srcImage = resize(image, 240, 240);//产生缩略图
                ImageIO.write(image, type, new File((fileAddress + "\\" + filename + "_" + (i + 1) + "." + type).replaceAll("\\\\", "/")));
                image.flush();
            }
            doc.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.gc();
        }
    }


    public static String pdf2png(String fileAddress, String filename, String type) {
        // 将pdf装图片 并且自定义图片得格式大小
        File file = new File((fileAddress + "\\" + filename + ".pdf").replaceAll("\\\\", "/"));

        try {
            PDDocument doc = PDDocument.load(file);
            PDFRenderer renderer = new PDFRenderer(doc);
            int i = 0;
            BufferedImage image = renderer.renderImageWithDPI(i, 288); // Windows native DPI

//            String path = (fileAddress + "\\" + filename + "_" + (i + 1) + "." + type).replaceAll("\\\\", "/");
//            File file1 = new File(path);
//            ImageIO.write(image, type, file1);
//            byte[] fileByte = Files.readAllBytes(file1.toPath());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();//io流
            ImageIO.write(image, type, baos);
            byte[] fileByte = baos.toByteArray();//转换成字节

            String png_base64 = BASE64Encoder.encode(fileByte).trim();//转换成base64串
            png_base64 = png_base64.replaceAll("\n", "").replaceAll("\r", "");//删除 \r\n
            image.flush();
            doc.close();
            baos.close();
            return png_base64;
        } catch (IOException e) {
            e.printStackTrace();
            throw new MyException("出错了,原因为:" + e.getMessage());
        } finally {
            System.gc();
        }
    }


    public  static void  pdf2png(HttpServletResponse response, String fileAddress, String filename, String type) {
        // 将pdf装图片 并且自定义图片得格式大小
        File file = new File((fileAddress + "\\" + filename + ".pdf").replaceAll("\\\\", "/"));

        try {
            PDDocument doc = PDDocument.load(file);
            PDFRenderer renderer = new PDFRenderer(doc);
            int i = 0;
            BufferedImage image = renderer.renderImageWithDPI(i, 288); // Windows native DPI

//            String path = (fileAddress + "\\" + filename + "_" + (i + 1) + "." + type).replaceAll("\\\\", "/");
//            File file1 = new File(path);
//            ImageIO.write(image, type, file1);
//            byte[] fileByte = Files.readAllBytes(file1.toPath());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();//io流
            ImageIO.write(image, type, baos);
            byte[] fileByte = baos.toByteArray();//转换成字节


            OutputStream os = response.getOutputStream();
            os.write(fileByte);
            image.flush();
            doc.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new MyException("出错了,原因为:" + e.getMessage());
        } finally {
            System.gc();
        }
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        String s = pdf2png("pdf", "aaa", "bmp");
        System.out.println(s);
        long endtTime = System.currentTimeMillis();
        System.out.println("总时间：" + (endtTime - startTime));
    }

}

