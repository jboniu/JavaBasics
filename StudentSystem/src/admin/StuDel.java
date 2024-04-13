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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StuDel extends JFrame{
	public JLabel lblId;
	public JTextField jtfId;
	public JButton btnDel,btnCancel;
	public JPanel jp1,jp2;
	
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
    
	public StuDel() {
		super("删除学生");
		
		/******设置小图标********/
		ImageIcon icon = new ImageIcon("picture/华坪女高.png");
		Image icon1=icon.getImage();
		this.setIconImage(icon1);
		

		
		setSize(500,480);
		this.setResizable(false);//禁止用户修改窗体
		this.setVisible(true);
		this.setLayout(new GridLayout(2,1));
//		this.setLayout(new BorderLayout());

		this.setLocationRelativeTo(null);//这种方法是设定一个窗口的相对于另外一个窗口的位置
		
		Container c = getContentPane(); //获取JFrame面板
		
		//面板
		jp1=new JPanel();
		jp2=new JPanel();
		
		
		c.add(jp1);
		c.add(jp2);
		
		//组件
		//
		lblId=new JLabel("请输入想删除学生的学生号：");
		jtfId=new JTextField(15);
		
		jp1.add(lblId);
		jp1.add(jtfId);
		
		//按钮删除、取消
		btnDel=new JButton("删除");
		btnCancel=new JButton("取消");
		
		jp2.add(btnDel);
		jp2.add(btnCancel);
		
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
		btnDel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					ps=ct.prepareStatement("delete from student where sid = ?");
					ps.setString(1, jtfId.getText());
					
				//通过ps发送sql语句,同时获取返回值，影响的记录数
					int len=ps.executeUpdate();
					 if(len==1){

		                    JOptionPane.showMessageDialog(null,"删除学生成功","提示信息",JOptionPane.WARNING_MESSAGE);
		                }
		                else{
		                    JOptionPane.showMessageDialog(null,"删除学生失败，请检查所输入学号是否有误","提示信息",JOptionPane.WARNING_MESSAGE);
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
