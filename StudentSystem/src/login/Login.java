package login;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.*;


import javax.swing.*;


import admin.Admin;
import teacher.Teacher;
import student.Student;

public class Login extends JFrame{
	
	public JButton btnLogin,btnCancel;
	public JPanel jpn,jpc,jps;
	public JLabel lblTitle,lblType,lblName,lblPassword;
	public JTextField jtfName;
	public JPasswordField jtfpassword;
	public JComboBox<String> comboBox;
	
	
	// 设定用户名和密码
    String user_name;
    String password;
    
    //管理员姓名和管理员号
    String adm_name;
    String aid;

    // 学生姓名和学号
    String stu_name;
    String sid;
    String stu_password;

    // 老师姓名和教工号
    String tea_name;
    String tid;
    String tea_password;


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
	
 
    public static void main(String[] args) {
		new Login();

	}
    
	public Login(){
		super("登录");
		
		 /******设置小图标********/
		ImageIcon icon = new ImageIcon("picture/华坪女高.png");
		Image icon1=icon.getImage();
		this.setIconImage(icon1);
		
		/*************背景图片************/
		((JPanel)this.getContentPane()).setOpaque(false);
		ImageIcon img = new ImageIcon("picture/华坪女校1.jpg"); //添加图片
		JLabel background = new JLabel(img);
		this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
		background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		
	
		
		Container c = getContentPane(); //获取JFrame面板
		
		jpn = new JPanel(); //创建个北JPanel
		jpc = new JPanel(); //创建个中JPanel
		jpc.setLayout(null);
		
		c.add(jpn,BorderLayout.NORTH);
		c.add(jpc,BorderLayout.CENTER);
		
		jpn.setOpaque(false); //把JPanel设置为透明
		jpc.setOpaque(false); 

		
		/************北************/
		lblTitle=new JLabel("华坪女子高级中学学生管理系统");
//		lblTitle.setBounds(80,50,500,100);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);//center label text
        Font fontTitle = new Font("华文行楷", Font.BOLD, 40);
        lblTitle.setFont(fontTitle);
        lblTitle.setForeground(Color.white);
		jpn.add(lblTitle);
	


		/************中************/
		lblType=new JLabel("类  型：");
		lblType.setBounds(230, 50, 200, 35);
		lblType.setFont(new Font("宋体", Font.BOLD, 22));
		lblType.setForeground(new Color(176,171,178));
		comboBox = new JComboBox<String>();//创建一个下拉列表框
		comboBox.setBounds(340, 50, 220, 35);//设置坐标
		comboBox.addItem("管理员");
		comboBox.addItem("教师");
		comboBox.addItem("学生");
		comboBox.setFont(new Font("宋体", Font.PLAIN, 22));
		comboBox.setEditable(false);
		
		jpc.add(lblType);
		jpc.add(comboBox);
		
		
		
		
		
		lblName=new JLabel("用户名:");
		lblName.setBounds(230, 100, 200, 35);
		lblName.setFont(new Font("宋体", Font.BOLD, 22));
		lblName.setForeground(new Color(176,171,198));
		
		lblPassword=new JLabel("密  码:");
		lblPassword.setBounds(230, 150, 200, 35);
		lblPassword.setFont(new Font("宋体", Font.BOLD, 22));
		lblPassword.setForeground(new Color(176,171,198));
		
		jtfName=new JTextField(15);
		jtfName.setBounds(340, 100, 220, 35);
		jtfName.setFont(new Font("宋体", Font.PLAIN, 22));
		
		jtfpassword=new JPasswordField(15);
		jtfpassword.setBounds(340, 150, 220, 35);
	
		jpc.add(lblName);
		jpc.add(jtfName);
		jpc.add(lblPassword);
		jpc.add(jtfpassword);
		
		
		btnLogin=new JButton("登录");
		btnLogin.setBounds(255, 260, 120, 35);
		btnCancel=new JButton("取消");
		btnCancel.setBounds(420, 260, 120, 35);
		
		
		
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
		
		/****************登录***************/
		btnLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getActionCommand()=="登录") {
					//选中管理员
					if(comboBox.getSelectedItem().toString()=="管理员") {
						try {
							ps=ct.prepareStatement("select * from administrator where  aname=?");
							ps.setString(1, jtfName.getText());
							
							rs=ps.executeQuery();
							if(rs.next()) {
								aid=rs.getString(1);
								user_name=rs.getString(2);
								password=rs.getString(3);
								adm_name=rs.getString(2);
								admin_login();
							}
							else {
								JOptionPane.showMessageDialog(null, "没有此用户或用户名为空！\n请重新输入", "提示消息", JOptionPane.WARNING_MESSAGE);          
							}
						}catch(SQLException e1) {
							e1.printStackTrace();
						}
					}
					//选中学生
					else if(comboBox.getSelectedItem().toString()=="学生"){
						try {
							ps=ct.prepareStatement("select * from student where  sname=? and spassword=?");
							ps.setString(1, jtfName.getText());
							ps.setString(2,String.valueOf(jtfpassword.getPassword()));
							rs=ps.executeQuery();
							if(rs.next()) {
								sid=rs.getString(1);
								user_name=rs.getString(2);
								stu_name=rs.getString(2);
								password=rs.getString(3);
								stu_password=rs.getString(3);
								
								stu_login();
							}
							else {
								JOptionPane.showMessageDialog(null, "没有此用户或用户名为空！\n请重新输入", "提示消息", JOptionPane.WARNING_MESSAGE);          
							}
						}catch(SQLException e1) {
							e1.printStackTrace();
						}
					}
					//选中教师
					else if(comboBox.getSelectedItem().toString()=="教师"){
						try {
							ps=ct.prepareStatement("select * from teacher where  tname=? and tpassword=?");
							ps.setString(1, jtfName.getText());
							ps.setString(2,String.valueOf(jtfpassword.getPassword()));
							rs=ps.executeQuery();
							if(rs.next()) {
								tid=rs.getString(1);
								user_name=rs.getString(2);
								tea_name=rs.getString(2);
								password=rs.getString(3);
								tea_password=rs.getString(3);
								
								tea_login();
							}
							else {
								JOptionPane.showMessageDialog(null, "没有此用户或用户名为空！\n请重新输入", "提示消息", JOptionPane.WARNING_MESSAGE);          
							}
						}catch(SQLException e1) {
							e1.printStackTrace();
						}
					}
					
				}
			}
		});
		
		/************取消*******************/
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
		
		
		jpc.add(btnLogin);
		jpc.add(btnCancel);
		
		
		
		


		this.setSize(img.getIconWidth(), img.getIconHeight());

		this.setResizable(false);//禁止用户修改窗体
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);//这种方法是设定一个窗口的相对于另外一个窗口的位置
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	

	
	//判断登录
	public void admin_login() {
		if(user_name.equals(jtfName.getText())&&password.equals(String.valueOf(jtfpassword.getPassword()))) {
			JOptionPane.showMessageDialog(null, "登录成功", "提示信息", JOptionPane.WARNING_MESSAGE);
			new Admin(adm_name);
		}
		else if(jtfName.getText().isEmpty() && String.valueOf(jtfpassword.getPassword()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "请输入用户名和密码！", "提示信息", JOptionPane.WARNING_MESSAGE);
        }
		else if(jtfName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "请输入用户名！", "提示信息", JOptionPane.WARNING_MESSAGE);
        }
		else if(String.valueOf(jtfpassword.getPassword()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "请输入密码！", "提示信息", JOptionPane.WARNING_MESSAGE);
        }
		else {
            JOptionPane.showMessageDialog(null, "用户名或密码错误！\n请重新输入", "提示信息", JOptionPane.ERROR_MESSAGE);
        }
	}
	public void stu_login() {
		if(user_name.equals(jtfName.getText())&&password.equals(String.valueOf(jtfpassword.getPassword()))) {
			JOptionPane.showMessageDialog(null, "登录成功", "提示信息", JOptionPane.WARNING_MESSAGE);
			new Student(stu_name,sid,stu_password);
		}
		else if(jtfName.getText().isEmpty() && String.valueOf(jtfpassword.getPassword()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "请输入用户名和密码！", "提示信息", JOptionPane.WARNING_MESSAGE);
        }
		else if(jtfName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "请输入用户名！", "提示信息", JOptionPane.WARNING_MESSAGE);
        }
		else if(String.valueOf(jtfpassword.getPassword()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "请输入密码！", "提示信息", JOptionPane.WARNING_MESSAGE);
        }
		else {
            JOptionPane.showMessageDialog(null, "用户名或密码错误！\n请重新输入", "提示信息", JOptionPane.ERROR_MESSAGE);
        }
	}
	public void tea_login() {
		if(user_name.equals(jtfName.getText())&&password.equals(String.valueOf(jtfpassword.getPassword()))) {
			JOptionPane.showMessageDialog(null, "登录成功", "提示信息", JOptionPane.WARNING_MESSAGE);
			 new Teacher(tea_name,tid);
		}
		else if(jtfName.getText().isEmpty() && String.valueOf(jtfpassword.getPassword()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "请输入用户名和密码！", "提示信息", JOptionPane.WARNING_MESSAGE);
        }
		else if(jtfName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "请输入用户名！", "提示信息", JOptionPane.WARNING_MESSAGE);
        }
		else if(String.valueOf(jtfpassword.getPassword()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "请输入密码！", "提示信息", JOptionPane.WARNING_MESSAGE);
        }
		else {
            JOptionPane.showMessageDialog(null, "用户名或密码错误！\n请重新输入", "提示信息", JOptionPane.ERROR_MESSAGE);
        }
	}

	
}
