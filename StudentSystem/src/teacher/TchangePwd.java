package teacher;

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
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;

public class TchangePwd extends JFrame{
	public JLabel lblOldPwd,lblNewPwd,lblPwd;
	public JPanel jp1,jp2,jp3,jp4;
	public JTextField oldPwdField;
	public JPasswordField newPwdField,okPwdField;
	public JButton btnChange,btnCancel; 
	public JTable jtable1;
	public String teaId,teaName,teaPwd,teaSex;
	public int teaAge;
	
	String tid1; // 存储教师号
    String old_password; // 存储旧密码
    
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
	public TchangePwd(String tname,String tid) {
		super("修改密码");
		tid1=tid;
		setSize(500,480);
		setVisible(true);
		
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
		this.setLayout(new GridLayout(4,1));

		this.setLocationRelativeTo(null);//这种方法是设定一个窗口的相对于另外一个窗口的位置
		
		Container c = getContentPane(); //获取JFrame面板
		
        //创建组件
		//面板jp1,jp2,jp3,jp4
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		jp4=new JPanel();
		
		c.add(jp1);
		c.add(jp2);
		c.add(jp3);
		c.add(jp4);
		
		//标签lblOldPwd,lblNewPwd,lblPwd;
		lblOldPwd=new JLabel("原密码：");
		lblNewPwd=new JLabel("新密码：");
		lblPwd=new JLabel("确认密码：");
		
		jp1.add(lblOldPwd);
		jp2.add(lblNewPwd);
		jp3.add(lblPwd);
		
		
		//文本框、密码框
		oldPwdField=new JTextField(15);
		newPwdField=new JPasswordField(15);
		okPwdField=new JPasswordField(15);
		
		jp1.add(oldPwdField);
		jp2.add(newPwdField);
		jp3.add(okPwdField);
		
//		jp1.setLayout(new FlowLayout(FlowLayout.LEFT));
//		jp2.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		//按钮修改、取消 btnChange,btnCancel
		btnChange=new JButton("修改");
		btnCancel=new JButton("取消");
		
		jp4.add(btnChange);
		jp4.add(btnCancel);
		
		try {
            Class.forName(driverName);
            ct = DriverManager.getConnection(dbURL, userName, userPwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		//监听函数
		btnChange.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getActionCommand() == "修改"){
		            try {
		                ps = ct.prepareStatement("select * from teacher where tid=? ");
		                ps.setString(1, tid);

		                rs=ps.executeQuery();
		                //取出对应教师信息
		                while(rs.next()){
		                    old_password = rs.getString(3);      //将教师的旧密码取出，用于判断原密码是否正确
		                }
		            } catch (SQLException e1) {
		                e1.printStackTrace();
		            }
		            change_password();
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
		
		//
		
		
		
		
		jp1.setOpaque(false);
		jp2.setOpaque(false);
		jp3.setOpaque(false);
		jp4.setOpaque(false);
		
		
		
		
		}
		
	
	
	public void change_password() {
		String new_PwdField=String.valueOf(newPwdField.getPassword());
		String ok_PwdField=String.valueOf(okPwdField.getPassword());
		
		if(old_password.equals(oldPwdField.getText()) && new_PwdField.equals(ok_PwdField) && !new_PwdField.isEmpty() && !ok_PwdField.isEmpty()){
            try{
                String sql="update teacher set tpassword=? where tid=? ";
                ps = ct.prepareStatement(sql);
                ps.setString(1, new_PwdField);
                ps.setString(2, tid1);
                ps.executeUpdate();//执行sql语句
                ct.close();
            }catch (SQLException e1) {
                e1.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "修改成功", "提示信息", JOptionPane.WARNING_MESSAGE);
            dispose();
        }else if(oldPwdField.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "请输入原密码！", "提示信息", JOptionPane.WARNING_MESSAGE);
        }else if(!old_password.equals(oldPwdField.getText()))
        {
            JOptionPane.showMessageDialog(null, "原密码错误\n请重新输入！", "提示信息", JOptionPane.ERROR_MESSAGE);
        }else if(new_PwdField.isEmpty() || ok_PwdField.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "新密码或者确认密码为空\n请重新输入！", "提示信息", JOptionPane.WARNING_MESSAGE);
        }else if(!new_PwdField.equals(ok_PwdField))
        {
            JOptionPane.showMessageDialog(null, "确认密码与新密码不符\n请重新输入！", "提示信息", JOptionPane.ERROR_MESSAGE);
        }
		
	}
}