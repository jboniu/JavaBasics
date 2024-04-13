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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class TeaAdd extends JFrame{
	public JButton btnAdd,btnCancel;
	public JPanel jp1,jp2,jp3,jp4,jp5,jp6;
	public JLabel lblTid,lblName,lblPwd,lblAge,lblSex;
	public JTextField jtfTid,jtfName,jtfPwd,jtfAge,jtfSex;
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
	
	public TeaAdd() {
		super("增加教师");
		/******设置小图标********/
		ImageIcon icon = new ImageIcon("picture/华坪女高.png");
		Image icon1=icon.getImage();
		this.setIconImage(icon1);
		
		
		
		this.setSize(400,600);
		this.setResizable(false);//禁止用户修改窗体
		this.setVisible(true);
		this.setLayout(new GridLayout(6,1));
//		this.setLayout(new BorderLayout());

		this.setLocationRelativeTo(null);//这种方法是设定一个窗口的相对于另外一个窗口的位置
		
		Container c = getContentPane(); //获取JFrame面板
		
		
		 //创建组件
		//面板jp1,jp2,jp3,jp4,jp5,jp6
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		jp4=new JPanel();
		jp5=new JPanel();
		jp6=new JPanel();
		
		c.add(jp1);
		c.add(jp2);
		c.add(jp3);
		c.add(jp4);
		c.add(jp5);
		c.add(jp6);
		
		//标签lblTid,lblName,lblPwd,lblAge,lblSex;
		lblTid=new JLabel("教师号：");
		lblName=new JLabel("姓名：");
		lblPwd=new JLabel("密码：");
		lblAge=new JLabel("年龄：");
		lblSex=new JLabel("性别：");
		
		//文本框jtfSid,jtfName,jtfPwd,jtfSex,jtfClass,jtfAge;
		jtfTid=new JTextField(15);
		jtfName=new JTextField(15);
		jtfPwd=new JTextField(15);
		jtfAge=new JTextField(15);
		jtfSex=new JTextField(15);
		
		//加入面板
		jp1.add(lblTid);
		jp1.add(jtfTid);
		jp2.add(lblName);
		jp2.add(jtfName);
		jp3.add(lblPwd);
		jp3.add(jtfPwd);
		jp4.add(lblAge);
		jp4.add(jtfAge);
		jp5.add(lblSex);
		jp5.add(jtfSex);
		
		//按钮添加、取消
		btnAdd=new JButton("添加");
		btnCancel=new JButton("取消");
		
		jp6.add(btnAdd);
		jp6.add(btnCancel);
		
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
						ps=ct.prepareStatement("insert into teacher(tid,tname,tpassword,age,sex) values(?,?,?,?,?)");
						ps.setString(1, jtfTid.getText());
						ps.setString(2, jtfName.getText());
						ps.setString(3, jtfPwd.getText());
						ps.setString(4, jtfAge.getText());
						ps.setString(5, jtfSex.getText());
						
					//通过ps发送sql语句,同时获取返回值，影响的记录数
						int len=ps.executeUpdate();
						 if(len==1){

			                    JOptionPane.showMessageDialog(null,"添加教师成功","提示信息",JOptionPane.WARNING_MESSAGE);
			                }
			                else{
			                    JOptionPane.showMessageDialog(null,"添加教师失败，教师号已存在不可重复","提示信息",JOptionPane.WARNING_MESSAGE);
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
