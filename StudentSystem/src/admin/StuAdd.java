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
	
	// ���ݿ�����
    static String driverName = "com.mysql.cj.jdbc.Driver";
    static String dbURL = "jdbc:mysql://localhost:3306/javaclassdesign?serverTimezone=Asia/Shanghai&useSSL=false&useUnicode=true&characterEncoding=utf-8";

    // ���ݿ��û���������
    static String userName="root";
    static String userPwd="password";

    // ���ݿ����ӡ�sql��䡢������ȶ���
    static Connection ct = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
	public StuAdd() {
		super("����ѧ��");
		/******����Сͼ��********/
		ImageIcon icon = new ImageIcon("picture/��ƺŮ��.png");
		Image icon1=icon.getImage();
		this.setIconImage(icon1);
		
		
		
		this.setSize(400,600);
		this.setResizable(false);//��ֹ�û��޸Ĵ���
		this.setVisible(true);
		this.setLayout(new GridLayout(7,1));
//		this.setLayout(new BorderLayout());

		this.setLocationRelativeTo(null);//���ַ������趨һ�����ڵ����������һ�����ڵ�λ��
		
		Container c = getContentPane(); //��ȡJFrame���
		
		
		 //�������
		//���jp1,jp2,jp3,jp4,jp5,jp6,jp7
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
		
		//��ǩlblSid,lblName,lblPwd,lblSex,lblClass,lblAge;
		lblSid=new JLabel("ѧ�ţ�");
		lblName=new JLabel("������");
		lblPwd=new JLabel("���룺");
		lblSex=new JLabel("�Ա�");
		lblClass=new JLabel("�༶��");
		lblAge=new JLabel("���䣺");
		
		//�ı���jtfSid,jtfName,jtfPwd,jtfSex,jtfClass,jtfAge;
		jtfSid=new JTextField(15);
		jtfName=new JTextField(15);
		jtfPwd=new JTextField(15);
		jtfSex=new JTextField(15);
		jtfClass=new JTextField(15);
		jtfAge=new JTextField(15);
		
		//�������
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
		
		//��ť��ӡ�ȡ��
		btnAdd=new JButton("���");
		btnCancel=new JButton("ȡ��");
		
		jp7.add(btnAdd);
		jp7.add(btnCancel);
		
		// �������ݿ������
	    try {
	        Class.forName(driverName);
	        ct=DriverManager.getConnection(dbURL, userName, userPwd);
	    } catch(ClassNotFoundException e1) {   
	        //���ݿ��������쳣����
	        System.out.println("Sorry,can`t find the Driver!");   
	        e1.printStackTrace();   
	        } catch(SQLException e2) {
	        //���ݿ�����ʧ���쳣����
	        e2.printStackTrace();  
	        }catch (Exception e) {
	        // TODO: handle exception
	        e.printStackTrace();
	    }finally{
	        System.out.println("���ݿ����ݳɹ���ȡ����");  
	        
	    }
		
		//��������
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
				//ͨ��ps����sql���,ͬʱ��ȡ����ֵ��Ӱ��ļ�¼��
					int len=ps.executeUpdate();
					 if(len==1){

		                    JOptionPane.showMessageDialog(null,"���ѧ���ɹ�","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
		                }
		                else{
		                    JOptionPane.showMessageDialog(null,"���ѧ��ʧ�ܣ�ѧ���Ѵ��ڲ����ظ�","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
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
