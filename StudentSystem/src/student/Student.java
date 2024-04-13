package student;

//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Container;
//import java.awt.Font;
//import java.awt.Graphics;
//import java.awt.Image;
//
//
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JToolBar;
//import javax.swing.SwingConstants;

import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import login.Login;

public class Student extends JFrame{
	public int age;
	public String sid,sname,spassword,sex,sclass;
	public JToolBar toolBar;
	public JButton btnQueryGrade,btnChangePwd; //查询成绩，修改密码
	public JLabel lblTitle,lblTip1,lblTip2,lblTip3;
	public JPanel jpn,jpn1,jpc;//北、中
	public JTable jtable;
	public Student(String sname,String sid,String spassword) {
		super("华坪女子高级中学学生管理系统");
//		this.setSize(810, 570);

		
		/******设置小图标********/
		ImageIcon icon = new ImageIcon("picture/华坪女高.png");
		Image icon1=icon.getImage();
		this.setIconImage(icon1);
		
		/*************背景图片************/
		((JPanel)this.getContentPane()).setOpaque(false);
		ImageIcon img = new ImageIcon("picture/主页背景1.png"); //添加图片
		JLabel background = new JLabel(img);
		this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
		background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		
		this.setSize(img.getIconWidth(), img.getIconHeight());
		this.setResizable(false);//禁止用户修改窗体
		this.setVisible(true);
//		this.setLayout(new GridLayout(6,1));
		this.setLayout(new BorderLayout());

		this.setLocationRelativeTo(null);//这种方法是设定一个窗口的相对于另外一个窗口的位置
		
		Container c = getContentPane(); //获取JFrame面板
		
		jpn = new JPanel(); //创建个北JPanel
		
		jpc = new JPanel(); //创建个中JPanel
		jpc.setLayout(null);
		
		c.add(jpn,BorderLayout.NORTH);
		c.add(jpc,BorderLayout.CENTER);

		
		jpn.setOpaque(false); //把JPanel设置为透明
		jpc.setOpaque(false);
		
		/**********北**********/
		lblTitle=new JLabel("华坪女子高级中学学生管理系统");
		Font fontTitle = new Font("宋体", Font.BOLD, 28);
        lblTitle.setFont(fontTitle);
        lblTitle.setForeground(Color.BLACK);
       
		jpn.add(lblTitle);

		
		
		
		
		/***********中**************/
		lblTip1=new JLabel("你好，"+sname+"同学！");
//		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);//center label text
        lblTip1.setFont(new Font("宋体", Font.BOLD, 18));
        lblTip1.setForeground(Color.blue);

        lblTip1.setBounds(0,0,250,40);

        jpc.add(lblTip1);

        
		//按钮
		btnQueryGrade=new JButton("查询信息");
		btnChangePwd=new JButton("修改密码");
		
		btnQueryGrade.setFont(new Font("宋体",Font.BOLD,20));
		btnChangePwd.setFont(new Font("宋体",Font.BOLD,20));
		
		btnQueryGrade.setBounds(170,80,130,40);
		btnChangePwd.setBounds(380,80,130,40);
		
		jpc.add(btnQueryGrade);
		jpc.add(btnChangePwd);
		
		btnQueryGrade.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				new queryGrade(sname,sid);
			}
		});
		
		btnChangePwd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new changePwd(sname,sid);
				
			}
		});
		
		
	}
	
//	public static void main(String[] args) {
//		Student s = new Student("张静","100");
//
//		s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//	}
	
	

	
}
