package student;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class queryGrade extends JFrame{
	public int chinese,math,english,physics,chemistry,biology,history,geography,politics;
	public String age,stuId,stuName,spassword,sex,sclass;
	public JButton btnQueryGrade,btnQueryMess,btnBack,btnExit; 
	public JLabel lblName,lblName1,lblSid,lblSid1;
	public JPanel jp1,jp2,jp3,jtp1,jp4,jtp2,jp5;
	public JTable jtable1,jtable2;//基本信息，成绩
	

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
	
    
 


	
	public queryGrade(String sname,String sid) {
		super("查询信息");
		setSize(500,500);
		
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
		
//		this.setSize(img.getIconWidth(), img.getIconHeight());
		this.setResizable(false);//禁止用户修改窗体
		this.setVisible(true);
		this.setLayout(new GridLayout(8,1));
//		this.setLayout(new BorderLayout());

		this.setLocationRelativeTo(null);//这种方法是设定一个窗口的相对于另外一个窗口的位置
		
		Container c = getContentPane(); //获取JFrame面板
		
        //创建组件
		//面板jp1,jp2,jp3,jtp1,jp4,jtp2,jp5
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		jtp1=new JPanel();
		jp4=new JPanel();
		jtp2=new JPanel();
		jp5=new JPanel();
		
		c.add(jp1);
		c.add(jp2);
		c.add(jp3);
		c.add(jtp1);
		c.add(jp4);
		c.add(jtp2);
		c.add(jp5);
		
		//标签lblName,lblName1,lblSid,lblSid1
		lblName=new JLabel("姓名：");
		lblName1=new JLabel(sname);
		lblSid=new JLabel("学号：");
		lblSid1=new JLabel(sid);
		
		jp1.add(lblName);
		jp1.add(lblName1);
		jp2.add(lblSid);
		jp2.add(lblSid1);
		
		jp1.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp2.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		//查询按钮btnQueryGrade,btnQueryMess
		btnQueryMess=new JButton("基本查询");
		btnQueryGrade=new JButton("成绩查询");
		
		jp3.add(btnQueryMess);
		jp4.add(btnQueryGrade);
		
		
		
		//表格
		
		String[] colnames1 = {"学号","姓名","密码","性别","班级","年龄"};
		DefaultTableModel model1 = new DefaultTableModel(colnames1,1);
		
		jtable1=new JTable(model1);
//		jtable1.addRowSelectionInterval(array1);
		jtp1.add(new JScrollPane(jtable1));
		
		String[] colnames2 = {"语文","数学","英语","物理","化学","生物","历史","地理","政治"};
		DefaultTableModel model2 = new DefaultTableModel(colnames2,1);

		jtable2=new JTable(model2);
		jtp2.add(new JScrollPane(jtable2));
		
		// 进行数据库的连接
		try {
	        Class.forName(driverName);
	        ct=DriverManager.getConnection(dbURL, userName, userPwd);
	    } catch(ClassNotFoundException e1) {   
	        //数据库驱动类异常处理
	        System.out.println("Sorry,can`t find the Driver!");   
	        e1.printStackTrace();   
	        } catch(SQLException e1) {
	        //数据库连接失败异常处理
	        e1.printStackTrace();  
	        }catch (Exception e1) {
	        // TODO: handle exception
	        e1.printStackTrace();
	    }finally{
	        System.out.println("数据库数据成功获取！！"); 
	     
	    }
				
				
				//监听函数
				btnQueryMess.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
						
						if(e.getActionCommand()=="基本查询") {
							try {
								ps=ct.prepareStatement("select * from student where  sname=? and sid=?");
								ps.setString(1, lblName1.getText());
								ps.setString(2, lblSid1.getText());
								
								rs=ps.executeQuery();
								if(rs.next()) {
									stuId=rs.getString(1);
									stuName=rs.getString(2);
									spassword=rs.getString(3);
									sex=rs.getString(4);
									sclass=rs.getString(5);
									age=rs.getString(6);
									
								}
								else {
									JOptionPane.showMessageDialog(null, "没有此用户或用户名为空！\n请重新输入", "提示消息", JOptionPane.WARNING_MESSAGE);          
								}
							}catch(SQLException e1) {
								e1.printStackTrace();
							}
							jtable1.setValueAt(stuId, 0, 0);
							jtable1.setValueAt(stuName, 0, 1);
							jtable1.setValueAt(spassword, 0, 2);
							jtable1.setValueAt(sex, 0, 3);
							jtable1.setValueAt(sclass, 0, 4);
							jtable1.setValueAt(age, 0, 5);
						}
					}
				});
				
				
				
				
				btnQueryGrade.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
						
						if(e.getActionCommand()=="成绩查询") {
							try {
								ps=ct.prepareStatement("select grade.grade from grade,student,course where student.sid=grade.sid and course.cid=grade.cid and student.sname=? and student.sid=?");
								ps.setString(1, lblName1.getText());
								ps.setString(2, lblSid1.getText());
								
								rs=ps.executeQuery();
								
								Integer[] grade;
								grade=new Integer[100];
								int i=0;
								while(rs.next()) {
									grade[i]=rs.getInt(1);
									i++;
								}
								chinese=grade[0];
								math=grade[1];
								english=grade[2];
								physics=grade[3];
								chemistry=grade[4];
								biology=grade[5];
								history=grade[6];
								geography=grade[7];
								politics=grade[8];
							}catch(SQLException e1) {
								e1.printStackTrace();
							}
							jtable2.setValueAt(chinese, 0, 0);
							jtable2.setValueAt(math, 0, 1);
							jtable2.setValueAt(english, 0, 2);
							jtable2.setValueAt(physics, 0, 3);
							jtable2.setValueAt(chemistry, 0, 4);
							jtable2.setValueAt(biology, 0, 5);
							jtable2.setValueAt(history, 0, 6);
							jtable2.setValueAt(geography, 0, 7);
							jtable2.setValueAt(politics, 0, 8);
						}
					}
				});
		
		//返回、退出
		btnBack=new JButton("返回");
		btnExit=new JButton("退出");
		
		jp5.add(btnBack);
		jp5.add(btnExit);
		
		//监听函数
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		
		btnExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});		
		
		jp1.setOpaque(false);
		jp2.setOpaque(false);
		jp3.setOpaque(false);
		jtp1.setOpaque(false);
		jp4.setOpaque(false);
		jtp2.setOpaque(false);
		jp5.setOpaque(false);
		
		
		
	}
	
	
}
