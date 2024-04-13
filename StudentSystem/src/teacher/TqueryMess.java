package teacher;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TqueryMess extends JFrame{
	public JLabel lblName,lblName1,lblTid,lblTid1;
	public JPanel jp1,jp2,jp3,jp4;
	public JButton btnQueryMess;
	public JTable jtable1;
	public String teaId,teaName,teaPwd,teaSex;
	public int teaAge;
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
    
    
	public TqueryMess(String tname,String tid) {
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
		this.setLayout(new GridLayout(6,1));
//		this.setLayout(new BorderLayout());

		this.setLocationRelativeTo(null);//这种方法是设定一个窗口的相对于另外一个窗口的位置
		
		Container c = getContentPane(); //获取JFrame面板
		
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
		
		//标签lblName,lblName1,lblSid,lblSid1
		lblName=new JLabel("姓名：");
		lblName1=new JLabel(tname);
		lblTid=new JLabel("教师号：");
		lblTid1=new JLabel(tid);
		
		jp1.add(lblName);
		jp1.add(lblName1);
		jp2.add(lblTid);
		jp2.add(lblTid1);
		
		jp1.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp2.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		//表格
		
		String[] colnames1 = {"教师号","姓名","密码","年龄","性别"};
		DefaultTableModel model1 = new DefaultTableModel(colnames1,1);		
		jtable1=new JTable(model1);
//				jtable1.addRowSelectionInterval(array1);
		jp3.add(new JScrollPane(jtable1));
		
		//按钮
		btnQueryMess=new JButton("查询");
		jp4.add(btnQueryMess);
		
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
				
				
				if(e.getActionCommand()=="查询") {
					try {
						ps=ct.prepareStatement("select * from teacher where  tname=? and tid=?");
						ps.setString(1, lblName1.getText());
						ps.setString(2, lblTid1.getText());
						
						rs=ps.executeQuery();
						if(rs.next()) {
							teaId=rs.getString(1);
							teaName=rs.getString(2);
							teaPwd=rs.getString(3);
							teaAge=rs.getInt(4);
							teaSex=rs.getString(5);
							
							
						}
						else {
							JOptionPane.showMessageDialog(null, "没有此用户或用户名为空！\n请重新输入", "提示消息", JOptionPane.WARNING_MESSAGE);          
						}
					}catch(SQLException e1) {
						e1.printStackTrace();
					}
					jtable1.setValueAt(teaId, 0, 0);
					jtable1.setValueAt(teaName, 0, 1);
					jtable1.setValueAt(teaPwd, 0, 2);
					jtable1.setValueAt(teaAge, 0, 3);
					jtable1.setValueAt(teaSex, 0, 4);
				}
			}
		});
	}
}
