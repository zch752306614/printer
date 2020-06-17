package com.lilanz.printer;

import gnu.io.CommPortIdentifier;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.gp.command.CpclCommand;
import com.gp.command.CpclCommand.ALIGNMENT;
import com.gp.command.CpclCommand.BARCODERATIO;
import com.gp.command.CpclCommand.BOLD;
import com.gp.command.CpclCommand.COMMAND;
import com.gp.command.CpclCommand.CPCLBARCODETYPE;
import com.gp.command.CpclCommand.CPCLSPEED;
import com.gp.command.CpclCommand.TEXT_FONT;
import com.gp.command.EscCommand;
import com.gp.command.EscCommand.ENABLE;
import com.gp.command.EscCommand.FONT;
import com.gp.command.EscCommand.HEIGHT_ZOOM;
import com.gp.command.EscCommand.HRI_POSITION;
import com.gp.command.EscCommand.JUSTIFICATION;
import com.gp.command.EscCommand.STATUS;
import com.gp.command.EscCommand.WIDTH_ZOOM;
import com.gp.command.LabelCommand;
import com.gp.command.LabelCommand.BITMAP_MODE;
import com.gp.command.LabelCommand.DENSITY;
import com.gp.command.LabelCommand.DIRECTION;
import com.gp.command.LabelCommand.EEC;
import com.gp.command.LabelCommand.FONTMUL;
import com.gp.command.LabelCommand.FONTTYPE;
import com.gp.command.LabelCommand.MIRROR;
import com.gp.command.LabelCommand.READABEL;
import com.gp.command.LabelCommand.ROTATION;
import com.gp.command.LabelCommand.TSCBARCODETYPE;
import com.gp.command.LabelCommand.TSCSPEED;
import com.gp.port.Drive;
import com.gp.port.Internet;
import com.gp.port.SerialPortDao;
import com.gp.port.UsbNative;

public class Main {
	private static HashPrintRequestAttributeSet pras;
	private static DocFlavor flavor;
	private static PrintService printservice;
	private static Drive dr = null;
	private static UsbNative usb=null;
	private static SerialPortDao sp = null;
	private static Internet in = null;
	private static JRadioButton netport;
	private static JRadioButton serialport;
	private static JRadioButton driveport;
	private static JRadioButton usbport;
	private static JLabel portchoose;
	private static JComboBox drives = new JComboBox();
	private static JFrame f;
	private static ButtonGroup port_bg = new ButtonGroup();
	private static JTextField input = new JTextField(15);
	private static JTextField bmppath = new JTextField(15);
	private static JTextField bmplpath = new JTextField(15);
	private static JTextField ipinput = new JTextField(15);
	private static JTextField portinput = new JTextField(15);
	private static JTextField bmpsizeinput = new JTextField(5);
	private static JComboBox jcombo = new JComboBox();
	private static JComboBox baud = new JComboBox();
	private static JComboBox escprintmode = new JComboBox();
	private static JComboBox Lusb = new JComboBox();
	private static OutputStream os;
	private static InputStream is;
	private static WindowListener windowListener = new WindowListener() {

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			if (sp != null) {
				boolean s = sp.closeSerialPort();
				if (s)
					System.out.println("关闭串口成功!");
			}
			if (in != null) {
				boolean s = in.closeNetPort();
				if (s)
					System.out.println("关闭网口成功!");
			}
			System.out.println("关闭窗口中");

		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}

	};

	private static ActionListener close_ActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			boolean s = sp.closeSerialPort();
			if (s)
				JOptionPane.showMessageDialog(f.getContentPane(), "关闭串口成功",
						"系统信息", JOptionPane.INFORMATION_MESSAGE);
			else {
				JOptionPane.showMessageDialog(f.getContentPane(), "请打开串口",
						"系统信息", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	};

	private static ActionListener print_ActionListener = new ActionListener() {

		@Override//F:\LXH\软件\visual studio\Common7\Tools\
				//VS100COMNTOOLS
				//F:\LXH\软件\vs\Common7\Tools\  VS140COMNTOOLS
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			CpclCommand cpcl = new CpclCommand();
			cpcl.addInitializePrinter(55, 1);
			cpcl.addNote("Center justify text");
			cpcl.addJustification(ALIGNMENT.CENTER);
			cpcl.addNote("Print the words 'A COMMENT'");
			cpcl.addText(TEXT_FONT.FONT_5, 1, 0, 5, "A COMMENT");
			cpcl.addNote("Print the label and go to top of next form");
			cpcl.addForm();
			cpcl.addPrint();
			Vector<Byte> datas = cpcl.getCommand();
			byte[] bdatas = new byte[datas.size()];
			for (int i = 0; i < datas.size(); i++) {
				bdatas[i] = datas.get(i);
			}
			if (serialport.isSelected()) {
				boolean s = sp.sendMessage(bdatas);
				ccc(s);
			} else if (netport.isSelected()) {
				boolean s = in.sendMessage(bdatas);
				ccc(s);
			} if(driveport.isSelected()){
				boolean s = dr.sendMessage(bdatas);
				ccc(s);
			} else {
				boolean s=usb.sendMessage(bdatas,bdatas.length,Lusb.getSelectedIndex());
				ccc(s);
			}
		}

	};

	public static void ccc(boolean b) {
		if (b) {
			JOptionPane.showMessageDialog(f.getContentPane(), "发送成功", "系统信息",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(f.getContentPane(),
					"发送失败，注意端口选择或端口是否打开", "系统信息",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private static ActionListener init_ActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			CpclCommand cpcl = new CpclCommand();
			cpcl.addInitializePrinter();
			cpcl.addJustification(ALIGNMENT.LEFT);
			cpcl.addText(TEXT_FONT.FONT_4, 30, 40, "Hello World");
			cpcl.addPrint();
			Vector<Byte> datas = cpcl.getCommand();
			byte[] bdatas = new byte[datas.size()];
			for (int i = 0; i < datas.size(); i++) {
				bdatas[i] = datas.get(i);
			}
			if (serialport.isSelected()) {
				boolean s = sp.sendMessage(bdatas); 
				ccc(s);
			} else if (netport.isSelected()) {
				boolean s = in.sendMessage(bdatas);
				ccc(s);
			} else if(driveport.isSelected()){
				boolean s = dr.sendMessage(bdatas);
				ccc(s);
			} else {
				boolean s=usb.sendMessage(bdatas,bdatas.length,Lusb.getSelectedIndex());
				ccc(s);
			} 
		}
	};

	private static ActionListener send_ActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			try {
				byte[] datas=input.getText().getBytes("gb2312");
				if (serialport.isSelected()) {
					boolean s = sp.sendMessage(datas); 
					ccc(s);
				} else if (netport.isSelected()) {
					boolean s = in.sendMessage(datas);
					ccc(s);
				} else if(driveport.isSelected()){
					boolean s = dr.sendMessage(datas);
					ccc(s);
				} else {
					boolean s=usb.sendMessage(datas,datas.length,Lusb.getSelectedIndex());
					ccc(s);
				} 
			} catch (UnsupportedEncodingException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
		}
	};

	private static ActionListener text_ActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			CpclCommand cpcl = new CpclCommand(); // 如果 是
			cpcl.addInitializePrinter();
			cpcl.addText(TEXT_FONT.FONT_0, 200, 100, "TEXT");
			cpcl.addText90(TEXT_FONT.FONT_0, 200, 100, "T90");
			cpcl.addText180(TEXT_FONT.FONT_0, 200, 100, "T180");
			cpcl.addText270(TEXT_FONT.FONT_0, 200, 100, "T270");
			String[] str = { "2 2 5 $", "3 3 0 12", "4 2 5 34" };
			cpcl.addTextConcat(75, 75, str);
			cpcl.addPrint();
			Vector<Byte> datas = cpcl.getCommand();
			byte[] bdatas = new byte[datas.size()];
			for (int i = 0; i < datas.size(); i++) {
				bdatas[i] = datas.get(i);
			}
			if (serialport.isSelected()) {
				boolean s = sp.sendMessage(bdatas);
				ccc(s);
			} else if (netport.isSelected()) {
				boolean s = in.sendMessage(bdatas);
				ccc(s);
			} else if(driveport.isSelected()){
				boolean s = dr.sendMessage(bdatas);
				ccc(s);
			} else {
				boolean s=usb.sendMessage(bdatas,bdatas.length,Lusb.getSelectedIndex());
				ccc(s);
			} 
		}

	};

	private static ActionListener setmag_ActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			CpclCommand cpcl = new CpclCommand();
			cpcl.addInitializePrinter();
			cpcl.addJustification(ALIGNMENT.CENTER);
			cpcl.addSetmag(1, 1);
			cpcl.addText(TEXT_FONT.FONT_0, 0, 10, "Font 0-0 at SETMAG 1 1");
			cpcl.addSetmag(1, 2);
			cpcl.addText(TEXT_FONT.FONT_0, 0, 40, "Font 0-0 at SETMAG 1 2");
			cpcl.addSetmag(2, 1);
			cpcl.addText(TEXT_FONT.FONT_0, 0, 80, "Font 0-0 at SETMAG 2 1");
			cpcl.addSetmag(2, 2);
			cpcl.addText(TEXT_FONT.FONT_0, 0, 110, "Font 0-0 at SETMAG 2 2");
			cpcl.addSetmag(2, 4);
			cpcl.addText(TEXT_FONT.FONT_0, 0, 145, "Font 0-0 at SETMAG 2 4");
			cpcl.addNote("Restore default font sizes");
			cpcl.addSetmag(0, 0);
			cpcl.addPrint();
			Vector<Byte> datas = cpcl.getCommand();
			byte[] bdatas = new byte[datas.size()];
			for (int i = 0; i < datas.size(); i++) {
				bdatas[i] = datas.get(i);
			}
			if (serialport.isSelected()) {
				boolean s = sp.sendMessage(bdatas);
				ccc(s);
			} else if (netport.isSelected()) {
				boolean s = in.sendMessage(bdatas);
				ccc(s);
			} else if(driveport.isSelected()){
				boolean s = dr.sendMessage(bdatas);
				ccc(s);
			} else {
				boolean s=usb.sendMessage(bdatas,bdatas.length,Lusb.getSelectedIndex());
				ccc(s);
			} 
		}
	};

	private static ActionListener barcode_ActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			CpclCommand cpcl = new CpclCommand();
			cpcl.addInitializePrinter();
			cpcl.addBarcode(COMMAND.BARCODE, CPCLBARCODETYPE.CODE128, 1,
					BARCODERATIO.Point20, 50, 150, 10, "HORIZ");
			cpcl.addText(TEXT_FONT.FONT_7, 210, 70, "HORIZ");
			cpcl.addBarcode(COMMAND.VBARCODE, CPCLBARCODETYPE.CODE128, 1,
					BARCODERATIO.Point20, 50, 10, 200, "VERT");
			cpcl.addText90(TEXT_FONT.FONT_7, 70, 140, "VERT");
			cpcl.addPrint();
			Vector<Byte> datas = cpcl.getCommand();
			byte[] bdatas = new byte[datas.size()];
			for (int i = 0; i < datas.size(); i++) {
				bdatas[i] = datas.get(i);
			}
			if (serialport.isSelected()) {
				boolean s = sp.sendMessage(bdatas);
				ccc(s);
			} else if (netport.isSelected()) {
				boolean s = in.sendMessage(bdatas);
				ccc(s);
			} else if(driveport.isSelected()){
				boolean s = dr.sendMessage(bdatas);
				ccc(s);
			} else {
				boolean s=usb.sendMessage(bdatas,bdatas.length,Lusb.getSelectedIndex());
				ccc(s);
			} 
		}
	};

	private static ActionListener barcodetext_ActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			CpclCommand cpcl = new CpclCommand();
			cpcl.addInitializePrinter(400, 1);
			cpcl.addJustification(ALIGNMENT.CENTER);
			cpcl.addBarcodeText(7, 5);
			cpcl.addBarcode(COMMAND.BARCODE, CPCLBARCODETYPE.CODE128, 1,
					BARCODERATIO.Point20, 50, 0, 20, "123456789");
			cpcl.addBarcode(COMMAND.VBARCODE, CPCLBARCODETYPE.CODE128, 1,
					BARCODERATIO.Point20, 50, 40, 400, "112233445");
			cpcl.addBarcodeTextOff();
			cpcl.addPrint();
			Vector<Byte> datas = cpcl.getCommand();
			byte[] bdatas = new byte[datas.size()];
			for (int i = 0; i < datas.size(); i++) {
				bdatas[i] = datas.get(i);
			}
			if (serialport.isSelected()) {
				boolean s = sp.sendMessage(bdatas);
				ccc(s);
			} else if (netport.isSelected()) {
				boolean s = in.sendMessage(bdatas);
				ccc(s);
			} else if(driveport.isSelected()){
				boolean s = dr.sendMessage(bdatas);
				ccc(s);
			} else {
				boolean s=usb.sendMessage(bdatas,bdatas.length,Lusb.getSelectedIndex());
				ccc(s);
			} 
		}
	};

	private static ActionListener QRcode_ActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			CpclCommand cpcl = new CpclCommand();
			cpcl.addInitializePrinter(500, 1);
			cpcl.addJustification(ALIGNMENT.CENTER);
			cpcl.addBQrcode(10, 100, 2, 10, "QR code ABC123");
			cpcl.addText(TEXT_FONT.FONT_4, 10, 400, "QR code ABC123");
			cpcl.addPrint();
			Vector<Byte> datas = cpcl.getCommand();
			byte[] bdatas = new byte[datas.size()];
			for (int i = 0; i < datas.size(); i++) {
				bdatas[i] = datas.get(i);
			}
			if (serialport.isSelected()) {
				boolean s = sp.sendMessage(bdatas);
				ccc(s);
			} else if (netport.isSelected()) {
				boolean s = in.sendMessage(bdatas);
				ccc(s);
			} else if(driveport.isSelected()){
				boolean s = dr.sendMessage(bdatas);
				ccc(s);
			} else {
				boolean s=usb.sendMessage(bdatas,bdatas.length,Lusb.getSelectedIndex());
				ccc(s);
			} 
		}
	};

	private static ActionListener box_ActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			CpclCommand cpcl = new CpclCommand();
			cpcl.addInitializePrinter();
			cpcl.addBox(0, 0, 200, 200, 1);
			cpcl.addPrint();
			Vector<Byte> datas = cpcl.getCommand();
			byte[] bdatas = new byte[datas.size()];
			for (int i = 0; i < datas.size(); i++) {
				bdatas[i] = datas.get(i);
			}
			if (serialport.isSelected()) {
				boolean s = sp.sendMessage(bdatas);
				ccc(s);
			} else if (netport.isSelected()) {
				boolean s = in.sendMessage(bdatas);
				ccc(s);
			} else if(driveport.isSelected()){
				boolean s = dr.sendMessage(bdatas);
				ccc(s);
			} else {
				boolean s=usb.sendMessage(bdatas,bdatas.length,Lusb.getSelectedIndex());
				ccc(s);
			} 
		}
	};

	private static ActionListener line_ActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			CpclCommand cpcl = new CpclCommand();
			cpcl.addInitializePrinter();
			cpcl.addLine(0, 0, 200, 0, 1);
			cpcl.addLine(0, 0, 200, 200, 2);
			cpcl.addLine(0, 0, 0, 200, 3);
			cpcl.addJustification(ALIGNMENT.CENTER);
			cpcl.addSetbold(BOLD.ON);
			cpcl.addText(TEXT_FONT.FONT_1, 300, 0, "SET BOLE ON!");
			cpcl.addSetbold(BOLD.OFF);
			cpcl.addText(TEXT_FONT.FONT_1, 300, 30, "SET BOLE OFF!");
			cpcl.addPrint();
			Vector<Byte> datas = cpcl.getCommand();
			byte[] bdatas = new byte[datas.size()];
			for (int i = 0; i < datas.size(); i++) {
				bdatas[i] = datas.get(i);
			}
			if (serialport.isSelected()) {
				boolean s = sp.sendMessage(bdatas);
				ccc(s);
			} else if (netport.isSelected()) {
				boolean s = in.sendMessage(bdatas);
				ccc(s);
			} else if(driveport.isSelected()){
				boolean s = dr.sendMessage(bdatas);
				ccc(s);
			} else {
				boolean s=usb.sendMessage(bdatas,bdatas.length,Lusb.getSelectedIndex());
				ccc(s);
			} 
		}
	};

	private static ActionListener inverse_line_ActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			CpclCommand cpcl = new CpclCommand();
			cpcl.addInitializePrinter();
			cpcl.addText(TEXT_FONT.FONT_4, 2, 30, 20, "$123456");
			cpcl.addText(TEXT_FONT.FONT_4, 2, 30, 70, "$678.90");
			cpcl.addInverseLine(25, 40, 350, 40, 90);
			cpcl.addText(TEXT_FONT.FONT_4, 2, 30, 120, "$432.10");
			cpcl.addPrint();
			Vector<Byte> datas = cpcl.getCommand();
			byte[] bdatas = new byte[datas.size()];
			for (int i = 0; i < datas.size(); i++) {
				bdatas[i] = datas.get(i);
			}
			if (serialport.isSelected()) {
				boolean s = sp.sendMessage(bdatas);
				ccc(s);
			} else if (netport.isSelected()) {
				boolean s = in.sendMessage(bdatas);
				ccc(s);
			} else if(driveport.isSelected()){
				boolean s = dr.sendMessage(bdatas);
				ccc(s);
			} else {
				boolean s=usb.sendMessage(bdatas,bdatas.length,Lusb.getSelectedIndex());
				ccc(s);
			} 
		}
	};

	private static ActionListener e_graphics_ActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			CpclCommand cpcl = new CpclCommand();
			String textpath = bmppath.getText().toString().trim();
			String Lpath=bmplpath.getText().toString().trim();
			int size = Integer
					.valueOf(bmpsizeinput.getText().toString().trim());
			String path = cpcl.bigorsmall(size,
					cpcl.eve2bmp(textpath, Lpath), Lpath);
			cpcl.addEGraphics(path, 0, 0);
			cpcl.addText(TEXT_FONT.FONT_4, 300, 0, "I'm EGraphics");
			cpcl.addPrint();
			Vector<Byte> datas = cpcl.getCommand();
			byte[] bdatas = new byte[datas.size()];
			for (int i = 0; i < datas.size(); i++) {
				bdatas[i] = datas.get(i);
			}
			if (serialport.isSelected()) {
				boolean s = sp.sendMessage(bdatas);
				ccc(s);
			} else if (netport.isSelected()) {
				boolean s = in.sendMessage(bdatas);
				ccc(s);
			} else if(driveport.isSelected()){
				boolean s = dr.sendMessage(bdatas);
				ccc(s);
			} else {
				boolean s=usb.sendMessage(bdatas,bdatas.length,Lusb.getSelectedIndex());
				ccc(s);
			} 

		}
	};

	private static ActionListener internet2_ActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (in != null) {
				boolean s = in.closeNetPort();
				if (s)
					JOptionPane.showMessageDialog(f.getContentPane(), "网口断开连接",
							"系统信息", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	};

	private static ActionListener c_graphics_ActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			CpclCommand cpcl = new CpclCommand();
			String textpath = bmppath.getText().toString().trim();
			String Lpath= bmplpath.getText().toString().trim();
			int size = Integer
					.valueOf(bmpsizeinput.getText().toString().trim());
			String path = cpcl.bigorsmall(size,
					cpcl.eve2bmp(textpath, Lpath), Lpath);
			cpcl.addCGraphics(path, 0, 0);
			cpcl.addText(TEXT_FONT.FONT_4, 300, 0, "I'm CGraphics");
			cpcl.addPrint();
			Vector<Byte> datas = cpcl.getCommand();
			byte[] bdatas = new byte[datas.size()];
			for (int i = 0; i < datas.size(); i++) {
				bdatas[i] = datas.get(i);
			}
			if (serialport.isSelected()) {
				boolean s = sp.sendMessage(bdatas);
				ccc(s);
			} if (netport.isSelected()) {
				boolean s = in.sendMessage(bdatas);
				ccc(s);
			} else if(driveport.isSelected()){
				boolean s = dr.sendMessage(bdatas);
				ccc(s);
			} else {
				boolean s=usb.sendMessage(bdatas,bdatas.length,Lusb.getSelectedIndex());
				ccc(s);
			} 
		}
	};

	private static ActionListener justification_ActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			CpclCommand cpcl = new CpclCommand();
			cpcl.addInitializePrinter();
			cpcl.addJustification(ALIGNMENT.CENTER);
			cpcl.addText(TEXT_FONT.FONT_4, 0, 0, "C");
			cpcl.addJustification(ALIGNMENT.RIGHT);
			cpcl.addText(TEXT_FONT.FONT_4, 0, 0, "R");
			cpcl.addJustification(ALIGNMENT.LEFT);
			cpcl.addText(TEXT_FONT.FONT_4, 0, 0, "L");
			cpcl.addPrint();
			Vector<Byte> datas = cpcl.getCommand();
			byte[] bdatas = new byte[datas.size()];
			for (int i = 0; i < datas.size(); i++) {
				bdatas[i] = datas.get(i);
			}
			if (serialport.isSelected()) {
				boolean s = sp.sendMessage(bdatas);
				ccc(s);
			} else if (netport.isSelected()) {
				boolean s = in.sendMessage(bdatas);
				ccc(s);
			} else if(driveport.isSelected()){
				boolean s = dr.sendMessage(bdatas);
				ccc(s);
			} else {
				boolean s=usb.sendMessage(bdatas,bdatas.length,Lusb.getSelectedIndex());
				ccc(s);
			} 
		}
	};

	private static ActionListener page_width_ActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			CpclCommand cpcl1 = new CpclCommand();
			CpclCommand cpcl2 = new CpclCommand();
			cpcl1.addInitializePrinter();
			cpcl2.addInitializePrinter();

			cpcl1.addJustification(ALIGNMENT.LEFT);
			cpcl2.addJustification(ALIGNMENT.LEFT);

			cpcl1.addPagewidth(374);
			cpcl2.addPagewidth(574);

			cpcl1.addText(TEXT_FONT.FONT_4, 0, 0, "测试文本123abc");
			cpcl2.addText(TEXT_FONT.FONT_4, 0, 0, "测试文本456def");

			cpcl1.addPrint();
			cpcl2.addPrint();
			Vector<Byte> datas1 = cpcl1.getCommand();
			Vector<Byte> datas2 = cpcl2.getCommand();
			byte[] bdatas1 = new byte[datas1.size()];
			byte[] bdatas2 = new byte[datas2.size()];
			for (int i = 0; i < datas1.size(); i++) {
				bdatas1[i] = datas1.get(i);
			}
			for (int i = 0; i < datas2.size(); i++) {
				bdatas2[i] = datas2.get(i);
			}
			if (serialport.isSelected()) {
				boolean s1 = sp.sendMessage(bdatas1);
				ccc(s1);
				boolean s2 = sp.sendMessage(bdatas2);
				ccc(s2);

			} else if (netport.isSelected()) {
				boolean s1 = in.sendMessage(bdatas1);
				ccc(s1);
				boolean s2 = in.sendMessage(bdatas2);
				ccc(s2);
			} else if(driveport.isSelected()){
				boolean s1 = dr.sendMessage(bdatas1);
				ccc(s1);
				boolean s2 = dr.sendMessage(bdatas2);
				ccc(s2);
			} else {
				boolean s1=usb.sendMessage(bdatas1,bdatas1.length,Lusb.getSelectedIndex());
				ccc(s1);
				boolean s2=usb.sendMessage(bdatas2,bdatas2.length,Lusb.getSelectedIndex());
				ccc(s2);
			} 
		}
	};

	private static ActionListener speed_ActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			CpclCommand cpcl = new CpclCommand();
			cpcl.addInitializePrinter(450, 1);
			cpcl.addJustification(ALIGNMENT.LEFT);
			cpcl.addSpeed(CPCLSPEED.SPEED4);
			cpcl.addText(TEXT_FONT.FONT_5, 0, 20, "PRINTS AT SPEED 4");
			cpcl.addPrint();
			Vector<Byte> datas = cpcl.getCommand();
			byte[] bdatas = new byte[datas.size()];
			for (int i = 0; i < datas.size(); i++) {
				bdatas[i] = datas.get(i);
			}
			if (serialport.isSelected()) {
				boolean s = sp.sendMessage(bdatas);
				ccc(s);
			} else if (netport.isSelected()) {
				boolean s = in.sendMessage(bdatas);
				ccc(s);
			} else if(driveport.isSelected()){
				boolean s = dr.sendMessage(bdatas);
				ccc(s);
			} else {
				boolean s=usb.sendMessage(bdatas,bdatas.length,Lusb.getSelectedIndex());
				ccc(s);
			} 
		}
	};

	private static ActionListener beep_ActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			CpclCommand cpcl = new CpclCommand();
			cpcl.addInitializePrinter();
			cpcl.addJustification(ALIGNMENT.CENTER);
			cpcl.addText(TEXT_FONT.FONT_5, 0, 10, "beeps for two seconds");
			cpcl.addBeep(16);
			cpcl.addPrint();
			Vector<Byte> datas = cpcl.getCommand();
			byte[] bdatas = new byte[datas.size()];
			for (int i = 0; i < datas.size(); i++) {
				bdatas[i] = datas.get(i);
			}
			if (serialport.isSelected()) {
				boolean s = sp.sendMessage(bdatas);
				ccc(s);
			} else if (netport.isSelected()) {
				boolean s = in.sendMessage(bdatas);
				ccc(s);
			} else if(driveport.isSelected()){
				boolean s = dr.sendMessage(bdatas);
				ccc(s);
			} else {
				boolean s=usb.sendMessage(bdatas,bdatas.length,Lusb.getSelectedIndex());
				ccc(s);
			} 
		}
	};

	private static ActionListener internet_ActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String ip = ipinput.getText().toString().trim();
			int port = Integer.valueOf(portinput.getText().toString().trim());
			boolean b = in.openNetPort(ip, port);
			if (b)
				JOptionPane.showMessageDialog(f.getContentPane(),
						"网口连接成功,注意图片路径不能为空", "系统信息",
						JOptionPane.INFORMATION_MESSAGE);
			else {
				JOptionPane.showMessageDialog(f.getContentPane(), "网口连接失败",
						"系统信息", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	};

	private static ActionListener hortab_ActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			EscCommand esc = new EscCommand();
			esc.addInitializePrinter();
			esc.addPrintAndFeedLines((byte) 3);
			esc.addSelectJustification(JUSTIFICATION.CENTER);// 设置打印居中
			esc.addSelectPrintModes(FONT.FONTA, ENABLE.OFF, ENABLE.ON,
					ENABLE.ON, ENABLE.OFF);// 设置为倍高倍宽
			esc.addText("Sample\n"); // 打印文字
			esc.addPrintAndLineFeed();

			/* 打印文字 */
			esc.addSelectPrintModes(FONT.FONTA, ENABLE.OFF, ENABLE.OFF,
					ENABLE.OFF, ENABLE.OFF);// 取消倍高倍宽
			esc.addSelectJustification(JUSTIFICATION.LEFT);// 设置打印左对齐
			esc.addText("Print text\n"); // 打印文字
			esc.addText("Welcome to use SMARNET printer!\n"); // 打印文字

			/* 打印繁体中文 需要打印机支持繁体字库 */
			String message = "佳博智匯票據打印機\n";
			esc.addText(message, "BIG5");
			esc.addPrintAndLineFeed();

			/* 绝对位置 具体详细信息请查看GP58编程手册 */
			esc.addText("智汇");
			esc.addSetHorAndVerMotionUnits((byte) 7, (byte) 0);
			esc.addSetAbsolutePrintPosition((short) 6);
			esc.addText("网络");
			esc.addSetAbsolutePrintPosition((short) 10);
			esc.addText("设备");
			esc.addPrintAndLineFeed();

			/* 打印图片 */
			esc.addText("Print bitmap!\n"); // 打印文字
			esc.addUserCommand(new byte[]{31,27,31,86,1});
			String textpath = bmppath.getText().toString().trim();
			String Lpath= bmplpath.getText().toString().trim();
			int size = Integer
					.valueOf(bmpsizeinput.getText().toString().trim());
			//System.out.print(size + "");
			String path = esc.bigorsmall(size,
					esc.eve2bmp(textpath, Lpath),Lpath);
			esc.addRastBitImageWithMethod(path, 3);
			/* 打印一维条码 */
			esc.addText("Print code128\n"); // 打印文字
			esc.addSelectPrintingPositionForHRICharacters(HRI_POSITION.BELOW);//
			// 设置条码可识别字符位置在条码下方
			esc.addSetBarcodeHeight((byte) 60); // 设置条码高度为60点
			esc.addSetBarcodeWidth((byte) 1); // 设置条码单元宽度为1
			esc.addCODE128(esc.genCodeB("SMARNET")); // 打印Code128码
			esc.addPrintAndLineFeed();

			/*
			 * QRCode命令打印 此命令只在支持QRCode命令打印的机型才能使用。 在不支持二维码指令打印的机型上，则需要发送二维条码图片
			 */
			esc.addText("Print QRcode\n"); // 打印文字
			esc.addSelectErrorCorrectionLevelForQRCode((byte) 0x31); // 设置纠错等级
			esc.addSelectSizeOfModuleForQRCode((byte) 3);// 设置qrcode模块大小
			esc.addStoreQRCodeData("www.smarnet.cc");// 设置qrcode内容
			esc.addPrintQRCode();// 打印QRCode
			esc.addPrintAndLineFeed();

			/* 打印文字 */
			esc.addSelectJustification(JUSTIFICATION.CENTER);// 设置打印左对齐
			esc.addText("Completed!\r\n"); // 打印结束
			// 开钱箱
			esc.addGeneratePlus(LabelCommand.FOOT.F5, (byte) 255, (byte) 255);
			esc.addPrintAndFeedLines((byte) 8);
			Vector<Byte> datas = esc.getCommand();
			byte[] bdatas = new byte[datas.size()];
			for (int i = 0; i < datas.size(); i++) {
				bdatas[i] = datas.get(i);
			}
			if (serialport.isSelected()) {
				boolean s = sp.sendMessage(bdatas);
				ccc(s);
			} else if (netport.isSelected()) {
				boolean s = in.sendMessage(bdatas);
				ccc(s);
			} else if(driveport.isSelected()){
				boolean s = dr.sendMessage(bdatas);
				ccc(s);
			} else {
				boolean s=usb.sendMessage(bdatas,bdatas.length,Lusb.getSelectedIndex());
				ccc(s);
			} 
		}

	};

	private static ActionListener downloadbmp_ActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			EscCommand esc = new EscCommand();
			String textpath = bmppath.getText().toString().trim();
			String Lpath= bmplpath.getText().toString().trim();
			int size = Integer
					.valueOf(bmpsizeinput.getText().toString().trim());
			String[] path = { esc.bigorsmall(size,
					esc.eve2bmp(textpath, Lpath), Lpath) };
			esc.addDownloadNvBitImage(path);
			Vector<Byte> datas = esc.getCommand();
			byte[] bdatas = new byte[datas.size()];
			for (int i = 0; i < datas.size(); i++) {
				bdatas[i] = datas.get(i);
			}
			if (serialport.isSelected()) {
				boolean s = sp.sendMessage(bdatas);
				ccc(s);
			} else if (netport.isSelected()) {
				boolean s = in.sendMessage(bdatas);
				ccc(s);
			} else if(driveport.isSelected()){
				boolean s = dr.sendMessage(bdatas);
				ccc(s);
			} else {
				boolean s=usb.sendMessage(bdatas,bdatas.length,Lusb.getSelectedIndex());
				ccc(s);
			} 
		}

	};
	private static ActionListener printbmp_ActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			EscCommand esc = new EscCommand();
			esc.addPrintNvBitmap((byte) 1, (byte) 3);
			Vector<Byte> datas = esc.getCommand();
			byte[] bdatas = new byte[datas.size()];
			for (int i = 0; i < datas.size(); i++) {
				bdatas[i] = datas.get(i);
			}
			if (serialport.isSelected()) {
				boolean s = sp.sendMessage(bdatas);
				ccc(s);
			} else if (netport.isSelected()) {
				boolean s = in.sendMessage(bdatas);
				ccc(s);
			} else if(driveport.isSelected()){
				boolean s = dr.sendMessage(bdatas);
				ccc(s);
			} else {
				boolean s=usb.sendMessage(bdatas,bdatas.length,Lusb.getSelectedIndex());
				ccc(s);
			} 
		}

	};

	private static ActionListener timestate_ActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			EscCommand esc = new EscCommand();
			esc.RealtimeStatusTransmission(STATUS.PRINTER_STATUS);
			Vector<Byte> datas = esc.getCommand();
			byte[] bdatas = new byte[datas.size()];
			for (int i = 0; i < datas.size(); i++) {
				bdatas[i] = datas.get(i);
			}
			if (serialport.isSelected()) {
				boolean s = sp.sendMessage(bdatas);
				if (s) {
					System.out.println("发送成功!");
					sp.readMessage();
					String str = "";
					if ((sp.readBuffer[0] & 0x04) > 0) {
						str += "两个钱箱都关闭 ";
					} else {
						str += "一个或两个钱箱打开 ";
					}
					if ((sp.readBuffer[0] & 0x08) > 0) {
						str += "脱机 ";
					} else {
						str += "联机 ";
					}
					System.out.print(str + "\n");
				} else {
					System.out.println("发送失败!");
				}
			} else {
				boolean s = in.sendMessage(bdatas);
				if (s) {
					System.out.println("发送成功!");
					in.readMessage();
					String str = "";
					if ((in.readBuffer[0] & 0x04) > 0) {
						str += "两个钱箱都关闭 ";
					} else {
						str += "一个或两个钱箱打开 ";
					}
					if ((in.readBuffer[0] & 0x08) > 0) {
						str += "脱机 ";
					} else {
						str += "联机 ";
					}
					System.out.print(str + "\n");
				} else {
					System.out.println("发送失败!");
				}
			}

		}

	};
	private static ActionListener sound_ActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			EscCommand esc = new EscCommand();
			esc.addSetRightSideCharacterSpacing((byte) 30);
			esc.addText("测试打印:右间距abc123!\n");
			esc.addText("测试打印:右间距abc123!\n");
			esc.addSetKanjiLefttandRightSpace((byte) 20, (byte) 20);
			esc.addText("测试打印:汉字间距,你好！\n");
			esc.addText("测试打印:汉字间距,你好！\n");
			esc.addText("测试打印:汉字间距,你好！\n");
			esc.addSound((byte) 2, (byte) 4);
			Vector<Byte> datas = esc.getCommand();
			byte[] bdatas = new byte[datas.size()];
			for (int i = 0; i < datas.size(); i++) {
				bdatas[i] = datas.get(i);
			}
			if (serialport.isSelected()) {
				boolean s = sp.sendMessage(bdatas);
				ccc(s);
			} else if (netport.isSelected()) {
				boolean s = in.sendMessage(bdatas);
				ccc(s);
			} else if(driveport.isSelected()){
				boolean s = dr.sendMessage(bdatas);
				ccc(s);
			} else {
				boolean s=usb.sendMessage(bdatas,bdatas.length,Lusb.getSelectedIndex());
				ccc(s);
			} 
		}

	};

	private static ActionListener printmode_ActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int no = escprintmode.getSelectedIndex();
			EscCommand esc = new EscCommand();
			switch (no) {
			case 0:
				esc.addSelectPrintModes(FONT.FONTA, ENABLE.ON, ENABLE.OFF,
						ENABLE.OFF, ENABLE.OFF);
				break;

			case 1:
				esc.addSelectPrintModes(FONT.FONTA, ENABLE.OFF, ENABLE.OFF,
						ENABLE.OFF, ENABLE.ON);
				break;
			case 2:
				esc.addSelectPrintModes(FONT.FONTA, ENABLE.OFF, ENABLE.ON,
						ENABLE.OFF, ENABLE.OFF);
				break;
			case 3:
				esc.addSelectPrintModes(FONT.FONTA, ENABLE.OFF, ENABLE.OFF,
						ENABLE.ON, ENABLE.OFF);
				break;
			case 4:
				esc.addSelectPrintModes(FONT.FONTB, ENABLE.OFF, ENABLE.OFF,
						ENABLE.OFF, ENABLE.OFF);
				break;
			case 5:
				esc.addSelectPrintModes(FONT.FONTA, ENABLE.OFF, ENABLE.OFF,
						ENABLE.OFF, ENABLE.OFF);
				break;
			}
			esc.addText("你好,123,abc!\n");
			Vector<Byte> datas = esc.getCommand();
			byte[] bdatas = new byte[datas.size()];
			for (int i = 0; i < datas.size(); i++) {
				bdatas[i] = datas.get(i);
			}
			if (serialport.isSelected()) {
				boolean s = sp.sendMessage(bdatas);
				ccc(s);
			} else if (netport.isSelected()) {
				boolean s = in.sendMessage(bdatas);
				ccc(s);
			} else if(driveport.isSelected()){
				boolean s = dr.sendMessage(bdatas);
				ccc(s);
			} else {
				boolean s=usb.sendMessage(bdatas,bdatas.length,Lusb.getSelectedIndex());
				ccc(s);
			} 
		}

	};

	private static ActionListener absoluteprint_ActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			EscCommand esc = new EscCommand();
			esc.addInitializePrinter();
			Vector<Byte> datas = esc.getCommand();
			byte[] bdatas = new byte[datas.size()];
			for (int i = 0; i < datas.size(); i++) {
				bdatas[i] = datas.get(i);
			}
			if (serialport.isSelected()) {
				boolean s = sp.sendMessage(bdatas);
				ccc(s);
			} else if (netport.isSelected()) {
				boolean s = in.sendMessage(bdatas);
				ccc(s);
			} else if(driveport.isSelected()){
				boolean s = dr.sendMessage(bdatas);
				ccc(s);
			} else {
				boolean s=usb.sendMessage(bdatas,bdatas.length,Lusb.getSelectedIndex());
				ccc(s);
			} 
		}
	};

	private static ActionListener tscprint_ActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			LabelCommand tsc = new LabelCommand();
			tsc.addSize(60, 60); // 设置标签尺寸，按照实际尺寸设置
			tsc.addGap(0); // 设置标签间隙，按照实际尺寸设置，如果为无间隙纸则设置为0
			tsc.addDirection(DIRECTION.BACKWARD, MIRROR.NORMAL);// 设置打印方向
			tsc.addReference(0, 0);// 设置原点坐标
			tsc.addTear(ENABLE.ON); // 撕纸模式开启
			tsc.addCls();// 清除打印缓冲区
			// 绘制简体中文
			tsc.addText(20, 30, FONTTYPE.KOREAN, ROTATION.ROTATION_0,
					FONTMUL.MUL_1, FONTMUL.MUL_1, "조선말");
			tsc.addText(100, 30, FONTTYPE.SIMPLIFIED_CHINESE,
					ROTATION.ROTATION_0, FONTMUL.MUL_1, FONTMUL.MUL_1, "简体字");
			tsc.addText(180, 30, FONTTYPE.TRADITIONAL_CHINESE,
					ROTATION.ROTATION_0, FONTMUL.MUL_1, FONTMUL.MUL_1, "繁體字");
			// 绘制图片
			String textpath = bmppath.getText().toString().trim();
			String Lpath= bmplpath.getText().toString().trim();
			int size = Integer
					.valueOf(bmpsizeinput.getText().toString().trim());
			String path = tsc.bigorsmall(size,
					tsc.eve2bmp(textpath, Lpath), Lpath);
			tsc.addBitmap(path, 200, 60, BITMAP_MODE.OVERWRITE);
			// 绘制二维码
			tsc.addQRCode(105, 75, EEC.LEVEL_L, 5, ROTATION.ROTATION_0,
					" www.smarnet.cc");
			// 绘制一维条码
			tsc.add1DBarcode(50, 350, TSCBARCODETYPE.CODE128, 100,
					READABEL.EANBEL, ROTATION.ROTATION_0, "SMARNET");
			tsc.addPrint(1); // 打印标签
			tsc.addSound(2, 100); // 打印标签后 蜂鸣器响
			tsc.addCashdrwer(LabelCommand.FOOT.F5, 255, 255);
			Vector<Byte> datas = tsc.getCommand(); // 发送数据
			byte[] bdatas = new byte[datas.size()];
			for (int i = 0; i < datas.size(); i++) {
				bdatas[i] = datas.get(i);
			}
			if (serialport.isSelected()) {
				boolean s = sp.sendMessage(bdatas);
				ccc(s);
			} else if (netport.isSelected()) {
				boolean s = in.sendMessage(bdatas);
				ccc(s);
			} else if(driveport.isSelected()){
				boolean s = dr.sendMessage(bdatas);
				ccc(s);
			} else {
				boolean s=usb.sendMessage(bdatas,bdatas.length,Lusb.getSelectedIndex());
				ccc(s);
			} 
		}
	};

	private static ActionListener choosebmp_ActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			EscCommand esc = new EscCommand();
			String textpath = bmppath.getText().toString().trim();
			String Lpath= bmplpath.getText().toString().trim();
			int size = Integer
					.valueOf(bmpsizeinput.getText().toString().trim());
			String path = esc.bigorsmall(size,
					esc.eve2bmp(textpath, Lpath), Lpath);
			esc.addSetLineSpacing((byte) 0);
			esc.addChoosebmp(path);
			Vector<Byte> datas = esc.getCommand();
			byte[] bdatas = new byte[datas.size()];
			for (int i = 0; i < datas.size(); i++) {
				bdatas[i] = datas.get(i);
			}
			if (serialport.isSelected()) {
				boolean s = sp.sendMessage(bdatas);
				ccc(s);
			} else if (netport.isSelected()) {
				boolean s = in.sendMessage(bdatas);
				ccc(s);
			} else if(driveport.isSelected()){
				boolean s = dr.sendMessage(bdatas);
				ccc(s);
			} else {
				boolean s=usb.sendMessage(bdatas,bdatas.length,Lusb.getSelectedIndex());
				ccc(s);
			} 
		}
	};

	private static ActionListener lineoder_ActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			EscCommand esc = new EscCommand();
			esc.addSelectDefualtLineSpacing();
			esc.addText("测试打印:默认行距!\n");
			esc.addText("测试打印:默认行距!\n");
			esc.addText("测试打印:默认行距!\n");
			esc.addText("测试打印:默认行距!\n");
			esc.addSetLineSpacing((byte) 0);
			esc.addText("测试打印:0行距!\n");
			esc.addText("测试打印:0行距!\n");
			esc.addText("测试打印:0行距!\n");
			esc.addText("测试打印:0行距!\n");
			esc.addSetLineSpacing((byte) 100);
			esc.addText("测试打印:100行距!\n");
			esc.addText("测试打印:100行距!\n");
			esc.addText("测试打印:100行距!\n");
			esc.addText("测试打印:100行距!\n");
			Vector<Byte> datas = esc.getCommand();
			byte[] bdatas = new byte[datas.size()];
			for (int i = 0; i < datas.size(); i++) {
				bdatas[i] = datas.get(i);
			}
			if (serialport.isSelected()) {
				boolean s = sp.sendMessage(bdatas);
				ccc(s);
			} else if (netport.isSelected()) {
				boolean s = in.sendMessage(bdatas);
				ccc(s);
			} else if(driveport.isSelected()){
				boolean s = dr.sendMessage(bdatas);
				ccc(s);
			} else {
				boolean s=usb.sendMessage(bdatas,bdatas.length,Lusb.getSelectedIndex());
				ccc(s);
			} 
		}

	};

	private static ActionListener roation_ActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			EscCommand esc = new EscCommand();
			esc.addTurn90ClockWiseRotatin(ENABLE.ON);
			esc.addText("测试打印:旋转90度!\n");
			esc.addText("测试打印:旋转90度!\n");
			esc.addTurn90ClockWiseRotatin(ENABLE.OFF);
			esc.addText("测试打印:取消旋转90度!\n");
			esc.addText("测试打印:取消旋转90度!\n");
			esc.addTurnUpsideDownModeOnOrOff(ENABLE.ON);
			esc.addText("测试打印:倒置打印!\n");
			esc.addText("测试打印:倒置打印!\n");
			esc.addTurnUpsideDownModeOnOrOff(ENABLE.OFF);
			esc.addText("测试打印:取消倒置打印!\n");
			esc.addText("测试打印:取消倒置打印!\n");
			esc.addSetCharcterSize(WIDTH_ZOOM.MUL_1, HEIGHT_ZOOM.MUL_1);
			esc.addText("测试打印:1倍高1倍宽!\n");
			esc.addSetCharcterSize(WIDTH_ZOOM.MUL_2, HEIGHT_ZOOM.MUL_1);
			esc.addText("测试打印:1倍高2倍宽!\n");
			esc.addSetCharcterSize(WIDTH_ZOOM.MUL_4, HEIGHT_ZOOM.MUL_1);
			esc.addText("测试打印:1倍高4倍宽!\n");
			esc.addSetCharcterSize(WIDTH_ZOOM.MUL_8, HEIGHT_ZOOM.MUL_1);
			esc.addText("测试打印:1倍高8倍宽!\n");
			esc.addSetCharcterSize(WIDTH_ZOOM.MUL_1, HEIGHT_ZOOM.MUL_1);
			esc.addText("测试打印:1倍高1倍宽!\n");
			esc.addSetCharcterSize(WIDTH_ZOOM.MUL_1, HEIGHT_ZOOM.MUL_2);
			esc.addText("测试打印:2倍高1倍宽!\n");
			esc.addSetCharcterSize(WIDTH_ZOOM.MUL_1, HEIGHT_ZOOM.MUL_4);
			esc.addText("测试打印:4倍高1倍宽!\n");
			esc.addSetCharcterSize(WIDTH_ZOOM.MUL_1, HEIGHT_ZOOM.MUL_8);
			esc.addText("测试打印:8倍高1倍宽!\n");
			esc.addSetCharcterSize(WIDTH_ZOOM.MUL_1, HEIGHT_ZOOM.MUL_1);
			esc.addText("测试打印:1倍高1倍宽!\n");
			esc.addSetCharcterSize(WIDTH_ZOOM.MUL_2, HEIGHT_ZOOM.MUL_2);
			esc.addText("测试打印:2倍高2倍宽!\n");
			esc.addSetCharcterSize(WIDTH_ZOOM.MUL_4, HEIGHT_ZOOM.MUL_4);
			esc.addText("测试打印:4倍高4倍宽!\n");
			esc.addSetCharcterSize(WIDTH_ZOOM.MUL_8, HEIGHT_ZOOM.MUL_8);
			esc.addText("测试打印:8倍高8倍宽!\n");
			esc.addSetCharcterSize(WIDTH_ZOOM.MUL_1, HEIGHT_ZOOM.MUL_1);
			esc.addTurnReverseModeOnOrOff(ENABLE.ON);
			esc.addText("测试打印:反显打印!\n");
			esc.addText("测试打印:反显打印!\n");
			esc.addText("测试打印:反显打印!\n");
			esc.addTurnReverseModeOnOrOff(ENABLE.OFF);
			esc.addText("测试打印:取消反显打印!\n");
			Vector<Byte> datas = esc.getCommand();
			byte[] bdatas = new byte[datas.size()];
			for (int i = 0; i < datas.size(); i++) {
				bdatas[i] = datas.get(i);
			}
			if (serialport.isSelected()) {
				boolean s = sp.sendMessage(bdatas);
				ccc(s);
			} else if (netport.isSelected()) {
				boolean s = in.sendMessage(bdatas);
				ccc(s);
			} else if(driveport.isSelected()){
				boolean s = dr.sendMessage(bdatas);
				ccc(s);
			} else {
				boolean s=usb.sendMessage(bdatas,bdatas.length,Lusb.getSelectedIndex());
				ccc(s);
			} 
		}
	};

	private static ActionListener escbarcode_ActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			EscCommand esc = new EscCommand();
			esc.addSetBarcodeHeight((byte) 50);
			esc.addSetFontForHRICharacter(FONT.FONTB);
			esc.addSelectPrintingPositionForHRICharacters(HRI_POSITION.NO_PRINT);
			esc.addText("UPCA条码\n");
			esc.addUPCA("01234567898");
			esc.addSelectPrintingPositionForHRICharacters(HRI_POSITION.ABOVE);
			esc.addText("UPCE条码\n");
			esc.addUPCE("12345678901");
			esc.addSelectPrintingPositionForHRICharacters(HRI_POSITION.BELOW);
			esc.addText("EAN13条码\n");
			esc.addEAN13("012345678912");
			esc.addSelectPrintingPositionForHRICharacters(HRI_POSITION.ABOVE_AND_BELOW);
			esc.addText("EAN8条码\n");
			esc.addEAN8("1234567");
			esc.addSetFontForHRICharacter(FONT.FONTA);
			esc.addSelectPrintingPositionForHRICharacters(HRI_POSITION.ABOVE);
			esc.addText("CODE39条码\n");
			esc.addCODE39("1223423");
			esc.addSelectPrintingPositionForHRICharacters(HRI_POSITION.BELOW);
			esc.addText("ITF条码\n");
			esc.addITF("12313");
			esc.addSelectPrintingPositionForHRICharacters(HRI_POSITION.ABOVE_AND_BELOW);
			esc.addText("CODE93条码\n");
			esc.addCODE93("165156"); // 首字符为A-D且末字符为A-D
			esc.addText("CODABAR条码\n");
			esc.addCODABAR("A9516165D");
			esc.addText("CODE128条码\n");
			esc.addCODE128("abc123456");
			Vector<Byte> datas = esc.getCommand();
			byte[] bdatas = new byte[datas.size()];
			for (int i = 0; i < datas.size(); i++) {
				bdatas[i] = datas.get(i);
			}
			if (serialport.isSelected()) {
				boolean s = sp.sendMessage(bdatas);
				ccc(s);
			} else if (netport.isSelected()) {
				boolean s = in.sendMessage(bdatas);
				ccc(s);
			} else if(driveport.isSelected()){
				boolean s = dr.sendMessage(bdatas);
				ccc(s);
			} else {
				boolean s=usb.sendMessage(bdatas,bdatas.length,Lusb.getSelectedIndex());
				ccc(s);
			} 
		}
	};

	private static ActionListener escQRcode_ActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			EscCommand esc = new EscCommand();
			esc.addSelectErrorCorrectionLevelForQRCode((byte) 49);
			esc.addSelectSizeOfModuleForQRCode((byte) 8);
			esc.addStoreQRCodeData("Smarnet");
			esc.addPrintQRCode();
			esc.addText("Smarnet\n");
			Vector<Byte> datas = esc.getCommand();
			byte[] bdatas = new byte[datas.size()];
			for (int i = 0; i < datas.size(); i++) {
				bdatas[i] = datas.get(i);
			}
			if (serialport.isSelected()) {
				boolean s = sp.sendMessage(bdatas);
				ccc(s);
			} else if (netport.isSelected()) {
				boolean s = in.sendMessage(bdatas);
				ccc(s);
			} else if(driveport.isSelected()){
				boolean s = dr.sendMessage(bdatas);
				ccc(s);
			} else {
				boolean s=usb.sendMessage(bdatas,bdatas.length,Lusb.getSelectedIndex());
				ccc(s);
			} 
		}
	};

	private static ActionListener tsctext_ActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			LabelCommand label = new LabelCommand();
			label.addSize(75, 50);
			label.addGap(0);
			label.addCls();
			label.addText(10, 0, FONTTYPE.SIMPLIFIED_CHINESE,
					ROTATION.ROTATION_0, FONTMUL.MUL_1, FONTMUL.MUL_1,
					"测试打印:1倍高1倍宽!");
			label.addText(10, 20, FONTTYPE.SIMPLIFIED_CHINESE,
					ROTATION.ROTATION_0, FONTMUL.MUL_2, FONTMUL.MUL_2,
					"测试打印:2倍高2倍宽!");
			label.addText(10, 50, FONTTYPE.SIMPLIFIED_CHINESE,
					ROTATION.ROTATION_0, FONTMUL.MUL_4, FONTMUL.MUL_4,
					"测试打印:4倍高4倍宽!");
			label.addText(10, 130, FONTTYPE.SIMPLIFIED_CHINESE,
					ROTATION.ROTATION_0, FONTMUL.MUL_10, FONTMUL.MUL_10,
					"测试打印:10倍高10倍宽!");
			label.addPrint(1);
			Vector<Byte> datas = label.getCommand();
			byte[] bdatas = new byte[datas.size()];
			for (int i = 0; i < datas.size(); i++) {
				bdatas[i] = datas.get(i);
			}
			if (serialport.isSelected()) {
				boolean s = sp.sendMessage(bdatas);
				ccc(s);
			} else if (netport.isSelected()) {
				boolean s = in.sendMessage(bdatas);
				ccc(s);
			} else if(driveport.isSelected()){
				boolean s = dr.sendMessage(bdatas);
				ccc(s);
			} else {
				boolean s=usb.sendMessage(bdatas,bdatas.length,Lusb.getSelectedIndex());
				ccc(s);
			} 
		}
	};

	private static ActionListener tscrotation_ActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			LabelCommand label = new LabelCommand();
			label.addSize(75, 65);
			label.addGap(0);
			label.addCls();
			label.addText(200, 200, FONTTYPE.SIMPLIFIED_CHINESE,
					ROTATION.ROTATION_0, FONTMUL.MUL_1, FONTMUL.MUL_1,
					"测试打印:旋转0度!");
			label.addText(200, 200, FONTTYPE.SIMPLIFIED_CHINESE,
					ROTATION.ROTATION_90, FONTMUL.MUL_1, FONTMUL.MUL_1,
					"测试打印:旋转90度!");
			label.addText(200, 200, FONTTYPE.SIMPLIFIED_CHINESE,
					ROTATION.ROTATION_180, FONTMUL.MUL_1, FONTMUL.MUL_1,
					"测试打印:旋转180度!");
			label.addText(200, 200, FONTTYPE.SIMPLIFIED_CHINESE,
					ROTATION.ROTATION_270, FONTMUL.MUL_1, FONTMUL.MUL_1,
					"测试打印:旋转270度!");
			label.addSpeed(TSCSPEED.SPEED4);
			label.addDensity(DENSITY.DNESITY15);
			label.addDirection(DIRECTION.BACKWARD, MIRROR.NORMAL);
			label.addText(0, 410, FONTTYPE.SIMPLIFIED_CHINESE,
					ROTATION.ROTATION_0, FONTMUL.MUL_1, FONTMUL.MUL_1,
					"测试打印:反向不镜像!");
			label.addDirection(DIRECTION.FORWARD, MIRROR.MIRROR);
			label.addText(0, 440, FONTTYPE.SIMPLIFIED_CHINESE,
					ROTATION.ROTATION_0, FONTMUL.MUL_1, FONTMUL.MUL_1,
					"测试打印:镜像不反向!");
			label.addDirection(DIRECTION.BACKWARD, MIRROR.MIRROR);
			label.addText(0, 470, FONTTYPE.SIMPLIFIED_CHINESE,
					ROTATION.ROTATION_0, FONTMUL.MUL_1, FONTMUL.MUL_1,
					"测试打印:镜像且反向!");
			label.addDirection(DIRECTION.FORWARD, MIRROR.NORMAL);
			label.addPrint(1);
			label.addFeed(50);
			label.addSound(3, 300);
			Vector<Byte> datas = label.getCommand();
			byte[] bdatas = new byte[datas.size()];
			for (int i = 0; i < datas.size(); i++) {
				bdatas[i] = datas.get(i);
			}
			if (serialport.isSelected()) {
				boolean s = sp.sendMessage(bdatas);
				ccc(s);
			} else if (netport.isSelected()) {
				boolean s = in.sendMessage(bdatas);
				ccc(s);
			} else if(driveport.isSelected()){
				boolean s = dr.sendMessage(bdatas);
				ccc(s);
			} else {
				boolean s=usb.sendMessage(bdatas,bdatas.length,Lusb.getSelectedIndex());
				ccc(s);
			} 
		}
	};

	private static ActionListener tscbar_ActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			LabelCommand label = new LabelCommand();
			label.addSize(75, 200);
			label.addGap(0);
			label.addCls();
			label.addText(0, 0, FONTTYPE.SIMPLIFIED_CHINESE,
					ROTATION.ROTATION_0, FONTMUL.MUL_1, FONTMUL.MUL_1,
					"测试打印:矩形线条!");
			label.addBar(0, 30, 500, 2);
			label.addText(0, 60, FONTTYPE.SIMPLIFIED_CHINESE,
					ROTATION.ROTATION_0, FONTMUL.MUL_1, FONTMUL.MUL_1,
					"测试打印:矩形线条!");
			label.addBar(0, 90, 500, 5);
			label.addText(0, 120, FONTTYPE.SIMPLIFIED_CHINESE,
					ROTATION.ROTATION_0, FONTMUL.MUL_1, FONTMUL.MUL_1,
					"测试打印:矩形!");
			label.addBar(20, 150, 300, 300);
			label.addText(0, 460, FONTTYPE.SIMPLIFIED_CHINESE,
					ROTATION.ROTATION_0, FONTMUL.MUL_1, FONTMUL.MUL_1,
					"测试打印:条码!");
			label.add1DBarcode(0, 490, TSCBARCODETYPE.CODE128, 50,
					READABEL.EANBEL, ROTATION.ROTATION_0, "abc123");
			label.add1DBarcode(0, 570, TSCBARCODETYPE.CODE128, 50,
					READABEL.DISABLE, ROTATION.ROTATION_0, "abc123");
			label.addBox(20, 630, 270, 810, 8);
			String textpath = bmppath.getText().toString().trim();
			String Lpath= bmplpath.getText().toString().trim();
			int size = Integer
					.valueOf(bmpsizeinput.getText().toString().trim());
			String path = label.bigorsmall(size,
					label.eve2bmp(textpath, Lpath), Lpath);
			label.addBitmap(path, 10, 805, BITMAP_MODE.OVERWRITE);
			label.addErase(30, 160, 150, 70);
			label.addText(85, 180, FONTTYPE.SIMPLIFIED_CHINESE,
					ROTATION.ROTATION_0, FONTMUL.MUL_1, FONTMUL.MUL_1, "擦除!");
			label.addText(0, 970, FONTTYPE.SIMPLIFIED_CHINESE,
					ROTATION.ROTATION_0, FONTMUL.MUL_1, FONTMUL.MUL_1,
					"测试打印:反显区域!");
			label.addText(0, 990, FONTTYPE.SIMPLIFIED_CHINESE,
					ROTATION.ROTATION_0, FONTMUL.MUL_1, FONTMUL.MUL_1,
					"测试打印:反显区域!");
			label.addReverse(0, 970, 300, 50);
			label.addQRCode(10, 1030, EEC.LEVEL_M, 150, ROTATION.ROTATION_0,
					"Smarnet");
			label.addPrint(1);
			Vector<Byte> datas = label.getCommand();
			byte[] bdatas = new byte[datas.size()];
			for (int i = 0; i < datas.size(); i++) {
				bdatas[i] = datas.get(i);
			}
			if (serialport.isSelected()) {
				boolean s = sp.sendMessage(bdatas);
				ccc(s);
			} else if (netport.isSelected()) {
				boolean s = in.sendMessage(bdatas);
				ccc(s);
			} else if(driveport.isSelected()){
				boolean s = dr.sendMessage(bdatas);
				ccc(s);
			} else {
				boolean s=usb.sendMessage(bdatas,bdatas.length,Lusb.getSelectedIndex());
				ccc(s);
			} 
		}
	};

	private static ActionListener opendrive_ActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String name = (String) drives.getSelectedItem();
			boolean b = dr.opendrive(name);
			if (b)
			JOptionPane.showMessageDialog(f.getContentPane(), "驱动" + dr.getname() + "开启成功!",
					"系统信息", JOptionPane.INFORMATION_MESSAGE);
			else {
				JOptionPane.showMessageDialog(f.getContentPane(), "驱动" + dr.getname() + "开启失败!",
						"系统信息", JOptionPane.INFORMATION_MESSAGE);
			}

		}
	};
	public static void initui() {
		f = new JFrame("Printer"); // 创建JFrame容器
		// 窗体可见
		// 设置窗体大小
		f.setSize(860, 280);
		f.setLayout(new FlowLayout());// 设置浮动布局方式
		// 设置窗体显示位置
		f.setLocation(500, 280);
		f.addWindowListener(windowListener);
		Button btn1 = new Button("Oepn Serialport"); 
		Button btn2 = new Button("Send Message");
		Button btn3 = new Button("Close Serialport");
		Button initcpcl = new Button("Initialize");
		Button print = new Button("Print");
		Button text = new Button("Text");
		Button setmag = new Button("Setmag");
		Button barcode = new Button("Barcode");
		Button barcodetext = new Button("BarcodeText");
		Button QRcode = new Button("QRcode");
		Button box = new Button("Box");
		Button line = new Button("Line");
		Button inverse_line = new Button("Inverse-Line");
		Button e_graphics = new Button("E-GRAPHICS");
		Button c_graphics = new Button("C-GRAPHICS");
		Button justification = new Button("Justification");
		Button page_width = new Button("Page-Width");
		Button speed = new Button("Speed");
		Button beep = new Button("Beep");
		Button hortab = new Button("Esc Test Page");
		Button downloadbmp = new Button("Define nv Download Bitmap");
		Button printbmp = new Button("Print nv Download Bitmap");
		Button timestate = new Button("Printer State");
		Button Sound = new Button("Buzzer");
		Button escp = new Button("Print And Paper feed");
		Button lineoder = new Button("Set Spacing");
		Button roation = new Button("90Degrees And Horse");
		Button escbarcode = new Button("Barcode");
		Button escQRcode = new Button("Qrcode");
		Button tscrotation = new Button("Rotate And Horse");
		Button tscbar = new Button("Barcode And Rectangular");
		JLabel ip_label = new JLabel("Ip Address: ");
		ipinput.setText("192.168.36.254");
		JLabel port_label = new JLabel("port: ");
		portchoose = new JLabel("Communication port: ");
		JLabel txt = new JLabel("Text: ");
		JLabel cpcl = new JLabel("CPCL: ");
		JLabel esc = new JLabel("ESC: ");
		JLabel tsc = new JLabel("TSC: ");
		JLabel bmpadd = new JLabel("Picture path: ");
		JLabel bmpaddl = new JLabel("Save Picture Path: ");
		JLabel bmpsize = new JLabel("Picture Size: ");
		JLabel usblist = new JLabel("USB: ");
		portinput.setText("9100");
		netport = new JRadioButton("Net Port");
		serialport = new JRadioButton("Serialport", true);
		driveport = new JRadioButton("Drive");
		usbport = new JRadioButton("Usb");
		port_bg.add(netport);
		port_bg.add(serialport);
		port_bg.add(driveport);
		port_bg.add(usbport);
		Button internet = new Button("Net Connect");
		Button internet2 = new Button("Net Disconnect");
		Button printmode = new Button("Print Mode");
		Button tscprint = new Button("TSC Test Page");
		Button absoluteprint = new Button("Initialize Esc");
		Button choosebmp = new Button("Print Choose Bitmap");
		Button tsctext = new Button("Print Text");
		Button opendrive = new Button("Open Drive");
		String[] bauds = { "9600", "19200", "38400", "57600", "115200" };
		for (int i = 0; i < bauds.length; i++) {
			baud.addItem(bauds[i]);
		}
		String[] escprintmodestr = { "Blod", "Underline", "Double High", "Double Width", "9*24", "12*24" };
		for (int i = 0; i < escprintmodestr.length; i++) {
			escprintmode.addItem(escprintmodestr[i]);
		}
		/*int usbports=usb.findusb();
		for (int i = 0; i <usbports; i++) {
			Lusb.addItem("USB"+i);
		}*/
		// 查找所有的可用的打印服务
		PrintService[] printServices = dr.getPrintServices();
		for (int i = 0; i < printServices.length; i++) {
			drives.addItem(printServices[i].getName());
			// System.out.println(name);
		}
		Set<CommPortIdentifier> portList = new HashSet<CommPortIdentifier>();
		portList = sp.getPortList();
		for (CommPortIdentifier cp : portList) {
			jcombo.addItem(cp.getName());
		}
		btn1.addActionListener(connect_ActionListener);
		btn2.addActionListener(send_ActionListener);
		btn3.addActionListener(close_ActionListener);
		initcpcl.addActionListener(init_ActionListener);
		print.addActionListener(print_ActionListener);
		text.addActionListener(text_ActionListener);
		setmag.addActionListener(setmag_ActionListener);
		barcode.addActionListener(barcode_ActionListener);
		barcodetext.addActionListener(barcodetext_ActionListener);
		QRcode.addActionListener(QRcode_ActionListener);
		box.addActionListener(box_ActionListener);
		line.addActionListener(line_ActionListener);
		inverse_line.addActionListener(inverse_line_ActionListener);
		e_graphics.addActionListener(e_graphics_ActionListener);
		c_graphics.addActionListener(c_graphics_ActionListener);
		justification.addActionListener(justification_ActionListener);
		page_width.addActionListener(page_width_ActionListener);
		speed.addActionListener(speed_ActionListener);
		beep.addActionListener(beep_ActionListener);
		internet.addActionListener(internet_ActionListener);
		internet2.addActionListener(internet2_ActionListener);
		hortab.addActionListener(hortab_ActionListener);
		downloadbmp.addActionListener(downloadbmp_ActionListener);
		printbmp.addActionListener(printbmp_ActionListener);
		timestate.addActionListener(timestate_ActionListener);
		Sound.addActionListener(sound_ActionListener);
		printmode.addActionListener(printmode_ActionListener);
		absoluteprint.addActionListener(absoluteprint_ActionListener);
		tscprint.addActionListener(tscprint_ActionListener);
		choosebmp.addActionListener(choosebmp_ActionListener);
		escp.addActionListener(escp_ActionListener);
		lineoder.addActionListener(lineoder_ActionListener);
		roation.addActionListener(roation_ActionListener);
		escbarcode.addActionListener(escbarcode_ActionListener);
		escQRcode.addActionListener(escQRcode_ActionListener);
		tsctext.addActionListener(tsctext_ActionListener);
		tscrotation.addActionListener(tscrotation_ActionListener);
		tscbar.addActionListener(tscbar_ActionListener);
		opendrive.addActionListener(opendrive_ActionListener);

		f.add(btn1);
		f.add(btn3);
		f.add(jcombo);
		f.add(baud);
		f.add(ip_label);
		f.add(ipinput);
		f.add(port_label);
		f.add(portinput);
		f.add(internet);
		f.add(internet2);
		f.add(opendrive);
		f.add(drives);
		f.add(usblist);
		f.add(Lusb);
		f.add(portchoose);
		f.add(serialport);
		f.add(netport);
		f.add(driveport);
		f.add(usbport);
		f.add(txt);
		f.add(input);
		f.add(btn2);
		f.add(bmpadd);
		f.add(bmppath);
		f.add(bmpaddl);
		f.add(bmplpath);
		f.add(bmpsize);
		f.add(bmpsizeinput);
		f.add(cpcl);
		f.add(initcpcl);
		f.add(print);
		f.add(text);
		f.add(setmag);
		f.add(barcode);
		f.add(barcodetext);
		f.add(QRcode);
		f.add(box);
		f.add(line);
		f.add(inverse_line);
		f.add(e_graphics);
		f.add(c_graphics);
		f.add(justification);
		f.add(page_width);
		f.add(speed);
		f.add(beep);
		f.add(esc);
		f.add(hortab);
		f.add(downloadbmp);
		f.add(printbmp);
		f.add(timestate);
		f.add(Sound);
		f.add(printmode);
		f.add(escprintmode);
		f.add(absoluteprint);
		f.add(choosebmp);
		f.add(escp);
		f.add(lineoder);
		f.add(roation);
		f.add(escbarcode);
		f.add(escQRcode);
		f.add(tsc);
		f.add(tscprint);
		f.add(tsctext);
		f.add(tscrotation);
		f.add(tscbar);
		f.setVisible(true);

	}

	public static ActionListener connect_ActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String portname = (String) jcombo.getSelectedItem();
			Set<CommPortIdentifier> portList = new HashSet<CommPortIdentifier>();
			CommPortIdentifier cp = null;
			portList = sp.getPortList();
			for (CommPortIdentifier str : portList) {
				if (str.getName().equals(portname)) {
					cp = str;
				}
			}
			int b = Integer.valueOf((String) (baud.getSelectedItem()));
			boolean s = sp.openSerialPort(cp.getName(), cp, 3000, b);
			if (s)
				JOptionPane.showMessageDialog(f.getContentPane(),
						"打开串口成功,注意图片路径不能为空", "系统信息",
						JOptionPane.INFORMATION_MESSAGE);
			else {
				JOptionPane.showMessageDialog(f.getContentPane(), "打开串口失败",
						"系统信息", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	};

	public static ActionListener escp_ActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			EscCommand esc = new EscCommand();
			esc.addText("打印测试:换行");
			esc.addPrintAndLineFeed();
			esc.addText("打印测试:走纸30点");
			esc.addPrintAndFeedPaper((byte) 30);
			esc.addText("打印测试:走纸3行");
			esc.addPrintAndFeedLines((byte) 3);
			esc.addHorTab();
			esc.addText("打印测试:水平定位\n");
			esc.addSetAbsolutePrintPosition((byte) 30);
			esc.addText("打印测试:绝对位置");
			esc.addSetRelativePrintPositon((byte) 600);
			esc.addText("打印测试:相对位置");
			esc.addPrintAndLineFeed();
			esc.addSelectJustification(JUSTIFICATION.RIGHT);
			esc.addText("打印测试:右对齐\n");
			esc.addSelectJustification(JUSTIFICATION.CENTER);
			esc.addText("打印测试:居中\n");
			esc.addSelectJustification(JUSTIFICATION.LEFT);
			esc.addText("打印测试:左对齐\n");
			esc.addSetLeftMargin((byte) 30);
			esc.addSelectJustification(JUSTIFICATION.RIGHT);
			esc.addText("左边距30右对齐\n");
			esc.addSelectJustification(JUSTIFICATION.CENTER);
			esc.addText("左边距30居中\n");
			esc.addSelectJustification(JUSTIFICATION.LEFT);
			esc.addText("左边距30左对齐\n");
			Vector<Byte> datas = esc.getCommand();
			byte[] bdatas = new byte[datas.size()];
			for (int i = 0; i < datas.size(); i++) {
				bdatas[i] = datas.get(i);
			}
			if (serialport.isSelected()) {
				boolean s = sp.sendMessage(bdatas);
				ccc(s);
			} else if (netport.isSelected()) {
				boolean s = in.sendMessage(bdatas);
				ccc(s);
			} else if(driveport.isSelected()){
				boolean s = dr.sendMessage(bdatas);
				ccc(s);
			} else {
				boolean s=usb.sendMessage(bdatas,bdatas.length,Lusb.getSelectedIndex());
				ccc(s);
			} 
		}

	};

	public static void main(String[] args) {
		sp = new SerialPortDao();
		in = new Internet();
		dr = new Drive();
		usb=new UsbNative();
		initui();
		
		
//		int no=usb.findusb();
//		System.out.print("USB有"+no+"个!\n");
//		boolean b=usb.openusb(0);
//		if(b){
//		System.out.print("打开第一个usb成功!\n");
//		usb.sendMessage(bdatas,bdatas.length,0);
//		}else {
//			System.out.print("打开第一个usb失败!\n");
//		}
		
	}
	
	/*static {
		System.loadLibrary("Native");
	}*/
}
