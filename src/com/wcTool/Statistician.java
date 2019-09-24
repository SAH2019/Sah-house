package com.wcTool;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.undo.*;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class Statistician extends JFrame{	
	private static final long serialVersionUID = -8542376708977843010L;
	public Statistician(String choose) {		
		JTextArea text =new JTextArea();       //创建文本区域组件，接受文本输入 
		Clipboard clipboard = getToolkit().getSystemClipboard();
		super.setTitle("WC-字符统计工具");
		super.setIconImage(new ImageIcon(".\\src\\timg.jpg").getImage());
		super.setSize(1000, 1000);
		JMenuBar bar = new JMenuBar();	
		super.setJMenuBar(bar);
		super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		super.add(text,BorderLayout.CENTER);
		super.setVisible(false);
		JScrollPane jsp=new JScrollPane(text);      
		jsp.setBounds(0, 0, 1100, 1100);
		super.add(jsp);
		
		
			FileDialog filedialog = new FileDialog(this, "打开",FileDialog.LOAD);           //文件打开对话�?
			filedialog.setVisible(true);
			String destion = filedialog.getDirectory()+filedialog.getFile();
			File newfile = new File(destion);
			BufferedReader fis=null;	
	  try {
				fis = new BufferedReader(new FileReader(newfile));              
				String buf=null;
				for(;null!=(buf=fis.readLine());) 
				{
					text.append(buf);
					text.append("\n");
				}			  
				} catch (Exception e1)
	  			{
				e1.printStackTrace(); 
				}finally {
				try {
					fis.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
	  
	  
		  
			  Scanner   sc=new Scanner(System.in);
		        System.out.println("请输入指令: -c,-w,-l,-s(-c统计字符总数:-w统计单词数；-l统计行数;-s显示文本窗口)");
		        while(true){
		        {
		        String order=sc.nextLine();
		        switch( order)  
		        {
					case "-w":{		
						int sum1=0;
						Pattern p2 = Pattern.compile("\\b[a-zA-z]+\\b");
						Matcher m2 = p2.matcher(text.getText());
						while(m2.find()) {
						sum1++;	
						};
							System.out.println("单词数为:"+sum1);
						}; 
						break;
					case "-l":           {
						int sum2=0;
						Pattern p3= Pattern.compile("\\n");
						Matcher m3 = p3.matcher(text.getText());
						while(m3.find()) {
							sum2++;
						};
						System.out.println("行数为："+sum2);
						};
						break;
					case "-c":
					     {
					    	 int sum3=0;
						Pattern p1 = Pattern.compile("[^\r|^\n]");
						Matcher m1= p1.matcher(text.getText());		
						while(m1.find()) {
							sum3++;
						}	;
						System.out.println("字符总数为："+sum3);
					     }; 	
					     break;
					case"-s":
						super.setVisible(true);
						break;
					default:
						System.out.println("指令输入错误!");
				}
			  }
		  }}
	public static void main(String[] args) {
		new Statistician(null);
	}
}