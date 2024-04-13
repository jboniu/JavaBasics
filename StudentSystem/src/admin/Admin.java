package admin;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
//import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JToolBar;

//import javax.swing.*;

public class Admin extends JFrame{
	public int age;
	public String sid,sname,spassword,sex,sclass;
	public JToolBar toolBar;
	public JButton btnOK,btnCancel; //确认，取消
	public JLabel lblTitle,lblTip,lblType,lblWay;
	public JPanel jp1,jp2,jp3,jp4,jp5;
	public JComboBox<String> comboBox1,comboBox2;
	public JTable jtable;
	
	
	
	public Admin(String aname) {
		super("华坪女子高级中学学生管理系统");
		
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
//		this.setSize(500,800);
		this.setResizable(false);//禁止用户修改窗体
		this.setVisible(true);
		this.setLayout(new GridLayout(5,1));
//		this.setLayout(new BorderLayout());

		this.setLocationRelativeTo(null);//这种方法是设定一个窗口的相对于另外一个窗口的位置
		
		Container c = getContentPane(); //获取JFrame面板
		
		//面板jp1,jp2,jp3,jtp1,jp4,jtp2,jp5;
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		jp4=new JPanel();
		jp5=new JPanel();
		
		jp1.setOpaque(false);
		jp2.setOpaque(false);
		jp3.setOpaque(false);
		jp4.setOpaque(false);
		jp5.setOpaque(false);
		
		c.add(jp1);
		c.add(jp2);
		c.add(jp3);
		c.add(jp4);
		c.add(jp5);
		
		
		//标签lblTitle,lblTip,lblType,lblWay;
		lblTitle=new JLabel("华坪女子高级中学学生管理系统");
		lblTitle.setFont(new Font("宋体", Font.BOLD, 28));
        lblTitle.setForeground(Color.BLACK);
        
        lblTip=new JLabel("你好，"+aname+"管理员！");
        lblTip.setFont(new Font("宋体", Font.BOLD, 18));
        lblTip.setForeground(Color.blue);
        lblType=new JLabel("更新类型：");
        lblType.setFont(new Font("宋体", Font.BOLD, 18));
        lblWay=new JLabel("更新方式：");
        lblWay.setFont(new Font("宋体", Font.BOLD, 18));
        
//        jp1.add(lblTitle);
//        jp2.add(lblTip);
//        jp2.setLayout(new FlowLayout(FlowLayout.LEFT));
//        lblTip.setFont(new Font("宋体", Font.BOLD, 18));
//        lblTip.setForeground(Color.blue);
//        jp3.add(lblType);
//        jp4.add(lblWay);
        
        //下拉列表comboBox1,comboxBox2
        comboBox1 = new JComboBox<String>();//创建一个下拉列表框
//		comboBox.setBounds(340, 50, 220, 35);//设置坐标
		comboBox1.addItem("教师");
		comboBox1.addItem("学生");
		comboBox1.addItem("全部");
		comboBox1.setFont(new Font("宋体", Font.PLAIN, 16));
		comboBox1.setEditable(false);
		
		comboBox2 = new JComboBox<String>();//创建一个下拉列表框
		comboBox2.addItem("增加");
		comboBox2.addItem("删除");
		comboBox2.addItem("修改");
		comboBox2.addItem("查询");
		comboBox2.setFont(new Font("宋体", Font.PLAIN, 16));
		comboBox2.setEditable(false);
		
		
		
		
		jp1.add(lblTitle);
        jp2.add(lblTip);
        jp2.setLayout(new FlowLayout(FlowLayout.LEFT));
        jp3.add(lblType);
		jp3.add(comboBox1);
		jp4.add(lblWay);
		jp4.add(comboBox2);
		
		
		//按钮btnOK,btnCancel;
		btnOK=new JButton("确认");
		btnCancel=new JButton("取消");
		
		jp5.add(btnOK);
		jp5.add(btnCancel);
		
		//监听函数
		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getActionCommand()=="确认") {
					//选中管理员
					if(comboBox1.getSelectedItem().toString()=="教师"&&comboBox2.getSelectedItem().toString()=="增加") {
						new TeaAdd();
					}
					else if(comboBox1.getSelectedItem().toString()=="教师"&&comboBox2.getSelectedItem().toString()=="删除"){
						new TeaDel();
					}
					else if(comboBox1.getSelectedItem().toString()=="教师"&&comboBox2.getSelectedItem().toString()=="修改"){
						new TeaUpd();
					}
					else if(comboBox1.getSelectedItem().toString()=="教师"&&comboBox2.getSelectedItem().toString()=="查询"){
						JOptionPane.showMessageDialog(null, "请选择全部来进行查询", "提示消息", JOptionPane.WARNING_MESSAGE);
					}
					
					//选中学生
					else if(comboBox1.getSelectedItem().toString()=="学生"&&comboBox2.getSelectedItem().toString()=="增加") {
						new StuAdd();
					}
					else if(comboBox1.getSelectedItem().toString()=="学生"&&comboBox2.getSelectedItem().toString()=="删除"){
						new StuDel();
					}
					else if(comboBox1.getSelectedItem().toString()=="学生"&&comboBox2.getSelectedItem().toString()=="修改"){
						new StuUpd();
					}
					else if(comboBox1.getSelectedItem().toString()=="学生"&&comboBox2.getSelectedItem().toString()=="查询"){
						JOptionPane.showMessageDialog(null, "请选择全部来进行查询", "提示消息", JOptionPane.WARNING_MESSAGE);
					}
					//选中全部
					else if(comboBox1.getSelectedItem().toString()=="全部"&&comboBox2.getSelectedItem().toString()=="查询"){
						new AllQue();
					}
					else if(comboBox1.getSelectedItem().toString()=="全部"&&comboBox2.getSelectedItem().toString()=="增加"){
						JOptionPane.showMessageDialog(null, "暂不支持增加全部", "提示消息", JOptionPane.WARNING_MESSAGE);
					}
					else if(comboBox1.getSelectedItem().toString()=="全部"&&comboBox2.getSelectedItem().toString()=="修改"){
						JOptionPane.showMessageDialog(null, "暂不支持修改全部", "提示消息", JOptionPane.WARNING_MESSAGE);
					}
					else if(comboBox1.getSelectedItem().toString()=="全部"&&comboBox2.getSelectedItem().toString()=="删除"){
						JOptionPane.showMessageDialog(null, "暂不支持删除全部", "提示消息", JOptionPane.WARNING_MESSAGE);
					}
						
				}
					
			
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		
	}

	
}
