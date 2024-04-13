package teacher;

import java.awt.Container;
import java.awt.FlowLayout;
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
import javax.swing.table.DefaultTableModel;

//import org.graalvm.compiler.nodes.java.NewArrayNode;

public class TchangeGrade extends JFrame {
//	private static final long serialVersionUID = 1L;
	public JLabel lblTip,lblSid,lblCid,lblChange;//查询提示，学号提示，修改提示
	public JTextField jtfSid,jtfCid,jtfChange;
	public JTable tableGrade;
	public DefaultTableModel modelGra;
	public JScrollPane jspGra;
	public JPanel jp1,jp2,jp3,jp4,jp5,jp6;
	public String titleGra[]={"学号","学生名","课程号","课程名","教师名","学生成绩"};
	public JButton btnOK,btnChange,btnCancel;
	
	    //存储学生信息
		String stuId,stuName;
		
		//存储课程信息
		String couId,couName;
		//存储成绩信息
		int  graGrade;
		
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
	public TchangeGrade(String tname,String tid) {
		super("修改成绩");
		
		//存储教师信息
		String teaId=tid,teaName=tname;
		
		setSize(500,600);
		setVisible(true);
		
		/******设置小图标********/
		ImageIcon icon = new ImageIcon("picture/华坪女高.png");
		Image icon1=icon.getImage();
		this.setIconImage(icon1);
		
		
		this.setResizable(false);//禁止用户修改窗体
		this.setVisible(true);
		this.setLayout(new GridLayout(6,1));

		this.setLocationRelativeTo(null);//这种方法是设定一个窗口的相对于另外一个窗口的位置
		
		Container c = getContentPane(); //获取JFrame面板
		
        //创建组件
		//面板jp1,jp2,jp3,jp4
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
		
		
		jp1.setOpaque(false);
		jp2.setOpaque(false);
		jp3.setOpaque(false);
		jp4.setOpaque(false);
		jp5.setOpaque(false);
		jp6.setOpaque(false);
		
		//标签lblTip,lblSid,lblCid,lblChange;//查询提示，学号提示，修改提示
		lblTip=new JLabel("查询学生本课成绩：");
		lblSid=new JLabel("学号：");
		lblCid=new JLabel("课程号：");
		lblChange=new JLabel("修改学生成绩：");
		
		//文本框jtfSid,jtfCid,jtfChange;
		jtfSid=new JTextField(15);
		jtfCid=new JTextField(15);
		jtfChange=new JTextField(15);
		
		//表格模型modelGra
		modelGra=new DefaultTableModel(titleGra,1);
		
		//JTable tableGrade
		tableGrade=new JTable(modelGra);
		
		//JScrollPane jspGra
		jspGra=new JScrollPane(tableGrade);
		
		//按钮btnOK,btnCancel,btnQueryStu,btnQueryTea,btnQueryCou,btnQueryGrade;
		btnOK=new JButton("确认");
		btnChange=new JButton("修改");
		btnCancel=new JButton("取消");
		
		
		//加入面板
		jp1.add(lblTip);
		jp1.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp2.add(lblSid);
		jp2.add(jtfSid);
		jp2.add(lblCid);
		jp2.add(jtfCid);
		jp2.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		jp3.add(btnOK);
		
		jp4.add(jspGra);
		
		jp5.add(lblChange);
		jp5.add(jtfChange);
		
		jp6.add(btnChange);
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
			btnOK.addActionListener(new ActionListener() {
						
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(e.getActionCommand()=="确认") {
						try {
							
//							"学号","学生名","课程号","课程名","教师名","学生成绩"
							ps=ct.prepareStatement("select student.sname,course.cname,grade.grade from student,course,grade where student.sid=? and course.tid=? and course.cid=? and grade.cid=course.cid and grade.sid=student.sid");
							ps.setString(1, jtfSid.getText());
							ps.setString(2, teaId);
							ps.setString(3, jtfCid.getText());
							
							rs=ps.executeQuery();
							if(rs.next()) {
								stuName=rs.getString(1);
								couName=rs.getString(2);
								graGrade=rs.getInt(3);
							}
							else {
								JOptionPane.showMessageDialog(null, "没有此学生或学号为空！\n请重新输入", "提示消息", JOptionPane.WARNING_MESSAGE);          
							}
						}catch(SQLException e1) {
							e1.printStackTrace();
						}
						tableGrade.setValueAt(jtfSid.getText(), 0, 0);
						tableGrade.setValueAt(stuName, 0, 1);
						tableGrade.setValueAt(jtfCid.getText(), 0, 2);
						tableGrade.setValueAt(couName, 0, 3);
						tableGrade.setValueAt(teaName, 0, 4);
						tableGrade.setValueAt(graGrade, 0, 5);
					}
					}
			});
			//修改
			btnChange.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(e.getActionCommand()=="修改") {
						try {
							ps=ct.prepareStatement("update grade set grade=? where sid=? and cid=?");
							ps.setString(1, jtfChange.getText());
							ps.setString(2, jtfSid.getText());
							ps.setString(3, jtfCid.getText());
							
							//通过ps发送sql语句,同时获取返回值，影响的记录数
							int len=ps.executeUpdate();
							if(len==1){

			                    JOptionPane.showMessageDialog(null,"修改学生成绩成功","提示信息",JOptionPane.WARNING_MESSAGE);
			                }
			                else{
			                    JOptionPane.showMessageDialog(null,"修改学生成绩失败，请检查所输入学号是否有误","提示信息",JOptionPane.WARNING_MESSAGE);
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
