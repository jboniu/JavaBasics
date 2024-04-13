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

import javax.swing.*;

public class StuAdd extends JFrame{
	public JButton btnAdd,btnCancel;
	public JPanel jp1,jp2,jp3,jp4,jp5,jp6,jp7;
	public JLabel lblSid,lblName,lblPwd,lblSex,lblClass,lblAge;
	public JTextField jtfSid,jtfName,jtfPwd,jtfSex,jtfClass,jtfAge;
	public JPasswordField jtfpassword;
	public JComboBox<String> comboBox;
	
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
    
	public StuAdd() {
		super("增加学生");
		/******设置小图标********/
		ImageIcon icon = new ImageIcon("picture/华坪女高.png");
		Image icon1=icon.getImage();
		this.setIconImage(icon1);
		
		
		
		this.setSize(400,600);
		this.setResizable(false);//禁止用户修改窗体
		this.setVisible(true);
		this.setLayout(new GridLayout(7,1));
//		this.setLayout(new BorderLayout());

		this.setLocationRelativeTo(null);//这种方法是设定一个窗口的相对于另外一个窗口的位置
		
		Container c = getContentPane(); //获取JFrame面板
		
		
		 //创建组件
		//面板jp1,jp2,jp3,jp4,jp5,jp6,jp7
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		jp4=new JPanel();
		jp5=new JPanel();
		jp6=new JPanel();
		jp7=new JPanel();
		
		c.add(jp1);
		c.add(jp2);
		c.add(jp3);
		c.add(jp4);
		c.add(jp5);
		c.add(jp6);
		c.add(jp7);
		
		//标签lblSid,lblName,lblPwd,lblSex,lblClass,lblAge;
		lblSid=new JLabel("学号：");
		lblName=new JLabel("姓名：");
		lblPwd=new JLabel("密码：");
		lblSex=new JLabel("性别：");
		lblClass=new JLabel("班级：");
		lblAge=new JLabel("年龄：");
		
		//文本框jtfSid,jtfName,jtfPwd,jtfSex,jtfClass,jtfAge;
		jtfSid=new JTextField(15);
		jtfName=new JTextField(15);
		jtfPwd=new JTextField(15);
		jtfSex=new JTextField(15);
		jtfClass=new JTextField(15);
		jtfAge=new JTextField(15);
		
		//加入面板
		jp1.add(lblSid);
		jp1.add(jtfSid);
		jp2.add(lblName);
		jp2.add(jtfName);
		jp3.add(lblPwd);
		jp3.add(jtfPwd);
		jp4.add(lblSex);
		jp4.add(jtfSex);
		jp5.add(lblClass);
		jp5.add(jtfClass);
		jp6.add(lblAge);
		jp6.add(jtfAge);
		
		//按钮添加、取消
		btnAdd=new JButton("添加");
		btnCancel=new JButton("取消");
		
		jp7.add(btnAdd);
		jp7.add(btnCancel);
		
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
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					
					ps=ct.prepareStatement("insert into teacher(sid,sname,spassword,sex,sclass,age) values(?,?,?,?,?,?)");
					ps.setString(1, jtfSid.getText());
					ps.setString(2, jtfName.getText());
					ps.setString(3, jtfPwd.getText());
					ps.setString(4, jtfSex.getText());
					ps.setString(5, jtfClass.getText());
					ps.setString(6, jtfAge.getText());
				//通过ps发送sql语句,同时获取返回值，影响的记录数
					int len=ps.executeUpdate();
					 if(len==1){

		                    JOptionPane.showMessageDialog(null,"添加学生成功","提示信息",JOptionPane.WARNING_MESSAGE);
		                }
		                else{
		                    JOptionPane.showMessageDialog(null,"添加学生失败，学号已存在不可重复","提示信息",JOptionPane.WARNING_MESSAGE);
		                }
				}catch(SQLException e1) {
					e1.printStackTrace();
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
