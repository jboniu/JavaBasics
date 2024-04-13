package admin;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class AllQue extends JFrame{
	public JButton btnCancel,btnQueryStu,btnQueryTea,btnQueryCou,btnQueryGrade;
	public JLabel lblSid,lblTid,lblCid,lblSid1,lblCid1;
	public JTextField jtfSid,jtfTid,jtfCid,jtfSid1,jtfCid1;
	public JPanel jp1,jp2,jp3,jp4,jp5,jp6,jp7,jp8,jp9,jp10,jp11,jp12;
	public JTable tableStu,tableTea,tableCou,tableGra;
	public DefaultTableModel modelStu,modelTea,modelCou,modelGra;
	public JScrollPane jspStu,jspTea,jspCou,jspGra;//滚动杠对象
	public String titleStu[]={"学号","姓名","密码","性别","班级","年龄"};
	public String titleTea[]={"教师号","姓名","密码","年龄","性别"};
	public String titleCou[]={"课程号","课程名","教师号","学习人数"};
	public String titleGra[]={"学号","课程号","成绩"};
	
	
	
	//存储学生信息
	String stuId,stuName,spassword,ssex,sclass;
	int sage;
	//存储教师信息
	String teaId,teaName,tpassword,tsex;
	int tage;
	//存储课程信息
	String couId,couName,couTid;
	int couCredit;
	//存储成绩信息
	String graSid,graCid;
	int graGrade;
	
	
	// 数据库驱动
    static String driverName = "com.mysql.cj.jdbc.Driver";
    static String dbURL = "jdbc:mysql://localhost:3306/javaclassdesign?serverTimezone=Asia/Shanghai&useSSL=false&useUnicode=true&characterEncoding=utf-8";

    // 数据库用户名和密码
    static String userName="root";
    static String userPwd="password";

    // 数据库连接、sql语句、结果集等对象
    static Connection ct = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
	
	public AllQue() {
		super("查询信息");
		setSize(500,600);
		setVisible(true);
		
		/******设置小图标********/
		ImageIcon icon = new ImageIcon("picture/华坪女高.png");
		Image icon1=icon.getImage();
		this.setIconImage(icon1);
		
		
		this.setResizable(false);//禁止用户修改窗体
		this.setVisible(true);
		this.setLayout(new GridLayout(12,1));

		this.setLocationRelativeTo(null);//这种方法是设定一个窗口的相对于另外一个窗口的位置
		
		Container c = getContentPane(); //获取JFrame面板
		
        //创建组件
		//面板jp1,jp2,jp3,jp4,jp5,jp6,jp7,jp8;
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		jp4=new JPanel();
		jp5=new JPanel();
		jp6=new JPanel();
		jp7=new JPanel();
		jp8=new JPanel();
		jp9=new JPanel();
		jp10=new JPanel();
		jp11=new JPanel();
		jp12=new JPanel();
		
		c.add(jp1);
		c.add(jp2);
		c.add(jp3);
		c.add(jp4);
		c.add(jp5);
		c.add(jp6);
		c.add(jp7);
		c.add(jp8);
		c.add(jp9);
		c.add(jp10);
		c.add(jp11);
		c.add(jp12);
		
		jp1.setOpaque(false);
		jp2.setOpaque(false);
		jp3.setOpaque(false);
		jp4.setOpaque(false);
		jp5.setOpaque(false);
		jp6.setOpaque(false);
		jp7.setOpaque(false);
		jp8.setOpaque(false);
		jp9.setOpaque(false);
		jp10.setOpaque(false);
		jp11.setOpaque(false);
		jp12.setOpaque(false);
		
		//标签lblSid,lblTid,lblCid,lblSid1,lblCid1;
		lblSid=new JLabel("学号：");
		lblTid=new JLabel("教师号：");
		lblCid=new JLabel("课程号：");
		lblSid1=new JLabel("学号：");
		lblCid1=new JLabel("课程号：");
		
		
		//文本框jtfSid,jtfTid,jtfCid,jtfSid1;
		jtfSid=new JTextField(15);
		jtfTid=new JTextField(15);
		jtfCid=new JTextField(15);
		jtfSid1=new JTextField(15);
		jtfCid1=new JTextField(15);
		//表格模型modelStu,modelTea,modelCou,modelGra;
		modelStu=new DefaultTableModel(titleStu,1);
		modelTea=new DefaultTableModel(titleTea,1);
		modelCou=new DefaultTableModel(titleCou,1);
		modelGra=new DefaultTableModel(titleGra,1);
		//JTable tableStu,tableTea,tableCou,tableGra;
		tableStu=new JTable(modelStu);
		tableTea=new JTable(modelTea);
		tableCou=new JTable(modelCou);
		tableGra=new JTable(modelGra);
		//JScrollPane jspStu,jspTea,jspCou,jspGra;//滚动杠对象
		jspStu=new JScrollPane(tableStu);
		jspTea=new JScrollPane(tableTea);
		jspCou=new JScrollPane(tableCou);
		jspGra=new JScrollPane(tableGra);
		
		//按钮btnCancel,btnQueryStu,btnQueryTea,btnQueryCou,btnQueryGrade;
		btnQueryStu=new JButton("查询学生");
		btnQueryTea=new JButton("查询教师");
		btnQueryCou=new JButton("查询课程");
		btnQueryGrade=new JButton("查询成绩");
		
		//加入面板
		jp1.add(lblSid);
		jp1.add(jtfSid);
				
		jp2.add(jspStu);
		jp3.add(btnQueryStu);
				
		jp4.add(lblTid);
		jp4.add(jtfTid);
				
		jp5.add(jspTea);
		jp6.add(btnQueryTea);
		
		jp7.add(lblCid);
		jp7.add(jtfCid);
				
		jp8.add(jspCou);
		jp9.add(btnQueryCou);
				
		jp10.add(lblSid1);
		jp10.add(jtfSid1);
		jp10.add(lblCid1);
		jp10.add(jtfCid1);
				
		jp11.add(jspGra);
		jp12.add(btnQueryGrade);
		
		// 进行数据库的连接
	   try {
	        Class.forName(driverName);
	        ct=DriverManager.getConnection(dbURL, userName, userPwd);
	    } catch(ClassNotFoundException e1) {   
	        //数据库驱动类异常处理
	        System.out.println("Sorry,can`t find the Driver!");   
	        e1.printStackTrace();   
	        } catch(SQLException e2) {
	        //数据库连接失败异常处理
	        e2.printStackTrace();  
	        }catch (Exception e) {
	        // TODO: handle exception
	        e.printStackTrace();
	    }finally{
	        System.out.println("数据库数据成功获取！！");  
	        
	    }
		
		//监听函数
		btnQueryStu.addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getActionCommand()=="查询学生") {
					try {
						ps=ct.prepareStatement("select * from student where sid=?");
						ps.setString(1, jtfSid.getText());
						
						rs=ps.executeQuery();
						if(rs.next()) {
							stuId=rs.getString(1);
							stuName=rs.getString(2);
							spassword=rs.getString(3);
							ssex=rs.getString(4);
							sclass=rs.getString(5);
							sage=rs.getInt(6);
							
						}
						else {
							JOptionPane.showMessageDialog(null, "没有此学生或学号为空！\n请重新输入", "提示消息", JOptionPane.WARNING_MESSAGE);          
						}
					}catch(SQLException e1) {
						e1.printStackTrace();
					}
					tableStu.setValueAt(stuId, 0, 0);
					tableStu.setValueAt(stuName, 0, 1);
					tableStu.setValueAt(spassword, 0, 2);
					tableStu.setValueAt(ssex, 0, 3);
					tableStu.setValueAt(sclass, 0, 4);
					tableStu.setValueAt(sage, 0, 5);
				}
				}
		});
				
		btnQueryTea.addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getActionCommand()=="查询教师") {
					try {
						ps=ct.prepareStatement("select * from teacher where tid=?");
						ps.setString(1, jtfTid.getText());
						
						rs=ps.executeQuery();
						if(rs.next()) {
							teaId=rs.getString(1);
							teaName=rs.getString(2);
							tpassword=rs.getString(3);
							tage=rs.getInt(4);
							tsex=rs.getString(5);
							
						}
						else {
							JOptionPane.showMessageDialog(null, "没有此教师或教师号为空！\n请重新输入", "提示消息", JOptionPane.WARNING_MESSAGE);          
						}
					}catch(SQLException e1) {
						e1.printStackTrace();
					}
					tableTea.setValueAt(teaId, 0, 0);
					tableTea.setValueAt(teaName, 0, 1);
					tableTea.setValueAt(tpassword, 0, 2);
					tableTea.setValueAt(tage, 0, 3);
					tableTea.setValueAt(tsex, 0, 4);
				}
				}
		});		
		
		btnQueryCou.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getActionCommand()=="查询课程") {
					try {
						ps=ct.prepareStatement("select * from course where cid=?");
						ps.setString(1, jtfCid.getText());
						
						rs=ps.executeQuery();
						if(rs.next()) {
							couId=rs.getString(1);
							couName=rs.getString(2);
							couTid=rs.getString(3);
							couCredit=rs.getInt(4);
							
							
							
						}
						else {
							JOptionPane.showMessageDialog(null, "没有此课程或课程号为空！\n请重新输入", "提示消息", JOptionPane.WARNING_MESSAGE);          
						}
					}catch(SQLException e1) {
						e1.printStackTrace();
					}
					tableCou.setValueAt(couId, 0, 0);
					tableCou.setValueAt(couName, 0, 1);
					tableCou.setValueAt(couTid, 0, 2);
					tableCou.setValueAt(couCredit, 0, 3);
				}
			}
		});		
		
		btnQueryGrade.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(e.getActionCommand()=="查询成绩") {
					try {
						ps=ct.prepareStatement("select * from grade where sid=? and cid=?");
						ps.setString(1, jtfSid1.getText());
						ps.setString(2, jtfCid1.getText());
						
						rs=ps.executeQuery();
						if(rs.next()) {
							graSid=rs.getString(1);
							graCid=rs.getString(2);
							graGrade=rs.getInt(3);
						}
						else {
							JOptionPane.showMessageDialog(null, "没有此成绩信息！\n请重新输入", "提示消息", JOptionPane.WARNING_MESSAGE);          
						}
					}catch(SQLException e1) {
						e1.printStackTrace();
					}
					tableGra.setValueAt(graSid, 0, 0);
					tableGra.setValueAt(graCid, 0, 1);
					tableGra.setValueAt(graGrade, 0, 2);
				}	
			}
		});		
		
		
		
	}
	
}
