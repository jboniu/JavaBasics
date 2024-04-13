package admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

public class TeaUpd extends JFrame{
	public JLabel lblTid,lblTip,lblUpd;//教师号，属性提示，修改的值
	public JTextField jtfTid,jtfUpd;
	public JPanel jp1,jp2,jp3,jp4;
	public JButton btnUdp,btnCancel;
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
	
	public TeaUpd() {
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
		//面板jp1,jp2,jp3,jp4;
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		jp4=new JPanel();
		
		c.add(jp1);
		c.add(jp2);
		c.add(jp3);
		c.add(jp4);
		
		jp1.setOpaque(false);
		jp2.setOpaque(false);
		jp3.setOpaque(false);
		jp4.setOpaque(false);
		
		//标签lblTid,lblTip,lblUpd;//学号，属性提示，修改的值
		lblTid=new JLabel("修改教师的学号：");
		lblTip=new JLabel("修改教师的属性：");
		lblUpd=new JLabel("修改后的值：");
		
		//文本框jtfTid,jtfTip,jtfUpd;
		jtfTid=new JTextField(15);
		comboBox=new JComboBox<String>();
		comboBox.addItem("姓名");
		comboBox.addItem("密码");
		comboBox.addItem("年龄");
		comboBox.addItem("性别");
		jtfUpd=new JTextField(15);
		
		jp1.add(lblTid);
		jp1.add(jtfTid);
		
		jp2.add(lblTip);
		jp2.add(comboBox);
		
		jp3.add(lblUpd);
		jp3.add(jtfUpd);
		
		//按钮btnUdp,btnCancel;
		btnUdp=new JButton("修改");
		btnCancel=new JButton("取消");
		
		jp4.add(btnUdp);
		jp4.add(btnCancel);
		
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
		btnUdp.addActionListener(new ActionListener() {
							
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//选中姓名
				if(comboBox.getSelectedItem().toString()=="姓名") {
					try {
						ps=ct.prepareStatement("update teacher set tname=? where tid=? ");
						ps.setString(1, jtfUpd.getText());
						ps.setString(2, jtfTid.getText());
						
						//通过ps发送sql语句,同时获取返回值，影响的记录数
						int len=ps.executeUpdate();
						if(len==1){

		                    JOptionPane.showMessageDialog(null,"修改教师姓名成功","提示信息",JOptionPane.WARNING_MESSAGE);
		                }
		                else{
		                    JOptionPane.showMessageDialog(null,"修改教师姓名失败，请检查所输入学号是否有误","提示信息",JOptionPane.WARNING_MESSAGE);
		                }
					}catch(SQLException e1) {
						e1.printStackTrace();
					}
				}
				//选中密码
				else if(comboBox.getSelectedItem().toString()=="密码"){
					try {
						ps=ct.prepareStatement("update teacher set tpassword=? where tid=? ");
						ps.setString(1, jtfUpd.getText());
						ps.setString(2, jtfTid.getText());
						
						//通过ps发送sql语句,同时获取返回值，影响的记录数
						int len=ps.executeUpdate();
						if(len==1){

		                    JOptionPane.showMessageDialog(null,"修改教师密码成功","提示信息",JOptionPane.WARNING_MESSAGE);
		                }
		                else{
		                    JOptionPane.showMessageDialog(null,"修改教师密码失败，请检查所输入学号是否有误","提示信息",JOptionPane.WARNING_MESSAGE);
		                }
					}catch(SQLException e1) {
						e1.printStackTrace();
					}
				}
				//选中年龄
				else if(comboBox.getSelectedItem().toString()=="年龄"){
					try {
						ps=ct.prepareStatement("update teacher set age=? where tid=? ");
						ps.setString(1, jtfUpd.getText());
						ps.setString(2, jtfTid.getText());
						
						//通过ps发送sql语句,同时获取返回值，影响的记录数
						int len=ps.executeUpdate();
						if(len==1){

		                    JOptionPane.showMessageDialog(null,"修改教师年龄成功","提示信息",JOptionPane.WARNING_MESSAGE);
		                }
		                else{
		                    JOptionPane.showMessageDialog(null,"修改教师年龄失败，请检查所输入学号是否有误","提示信息",JOptionPane.WARNING_MESSAGE);
		                }
					}catch(SQLException e1) {
						e1.printStackTrace();
					}
					
				}
				
				//选中性别
				else if(comboBox.getSelectedItem().toString()=="性别"){
					try {
						ps=ct.prepareStatement("update teacher set sex=? where tid=? ");
						ps.setString(1, jtfUpd.getText());
						ps.setString(2, jtfTid.getText());
						
						//通过ps发送sql语句,同时获取返回值，影响的记录数
						int len=ps.executeUpdate();
						if(len==1){

		                    JOptionPane.showMessageDialog(null,"修改教师性别成功","提示信息",JOptionPane.WARNING_MESSAGE);
		                }
		                else{
		                    JOptionPane.showMessageDialog(null,"修改教师性别失败，请检查所输入学号是否有误","提示信息",JOptionPane.WARNING_MESSAGE);
		                }
					}catch(SQLException e1) {
						e1.printStackTrace();
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
