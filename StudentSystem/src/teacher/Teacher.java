package teacher;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JToolBar;

import admin.TeaAdd;

public class Teacher extends JFrame{
	public int age;
	public String sid,sname,spassword,sex,sclass;
	public JToolBar toolBar;
	public JButton btnQueryMess,btnChangePwd,btnChangeGrade; //查询信息，修改密码,修改成绩
	public JLabel lblTitle,lblTip,lblType,lblWay;
	public JPanel jp1,jp2,jp3,jp4,jp5;
	public JComboBox<String> comboBox1,comboBox2;
	public JTable jtable;
	public Teacher(String tname,String tid) {
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
		this.setLayout(new GridLayout(3,1));
//		this.setLayout(new BorderLayout());

		this.setLocationRelativeTo(null);//这种方法是设定一个窗口的相对于另外一个窗口的位置
		
		Container c = getContentPane(); //获取JFrame面板
		
		//面板jp1,jp2,jp3,jtp1,jp4,jtp2,jp5;
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		
		
		jp1.setOpaque(false);
		jp2.setOpaque(false);
		jp3.setOpaque(false);
		
		
		c.add(jp1);
		c.add(jp2);
		c.add(jp3);
		
		
		
		//标签lblTitle,lblTip;
		lblTitle=new JLabel("华坪女子高级中学学生管理系统");
		lblTitle.setFont(new Font("宋体", Font.BOLD, 28));
        lblTitle.setForeground(Color.BLACK);
        
        lblTip=new JLabel("你好，"+tname+"老师！");
        lblTip.setFont(new Font("宋体", Font.BOLD, 18));
        lblTip.setForeground(Color.blue);
        
       
		jp1.add(lblTitle);
        jp2.add(lblTip);
        jp2.setLayout(new FlowLayout(FlowLayout.LEFT));
        
		
		
		//按钮btnOK,btnCancel;
        
      //按钮
      		btnQueryMess=new JButton("查询信息");
      		btnChangePwd=new JButton("修改密码");
      		btnChangeGrade=new JButton("修改成绩");
      		
      		btnQueryMess.setFont(new Font("宋体",Font.BOLD,20));
      		btnChangePwd.setFont(new Font("宋体",Font.BOLD,20));
      		btnChangeGrade.setFont(new Font("宋体",Font.BOLD,20));
		
		jp3.add(btnQueryMess);
		jp3.add(btnChangePwd);
		jp3.add(btnChangeGrade);
		
		//监听函数
		btnQueryMess.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new TqueryMess(tname,tid);
			}
		});
		
		btnChangePwd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new TchangePwd(tname,tid);
			}
		});
		btnChangeGrade.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new TchangeGrade(tname,tid);
			}
		});
		
	}
}
