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
	
	public TeaAdd() {
		super("���ӽ�ʦ");
		/******����Сͼ��********/
		ImageIcon icon = new ImageIcon("picture/��ƺŮ��.png");
		Image icon1=icon.getImage();
		this.setIconImage(icon1);
		
		
		
		this.setSize(400,600);
		this.setResizable(false);//��ֹ�û��޸Ĵ���
		this.setVisible(true);
		this.setLayout(new GridLayout(6,1));
//		this.setLayout(new BorderLayout());

		this.setLocationRelativeTo(null);//���ַ������趨һ�����ڵ����������һ�����ڵ�λ��
		
		Container c = getContentPane(); //��ȡJFrame���
		
		
		 //�������
		//���jp1,jp2,jp3,jp4,jp5,jp6
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
		
		//��ǩlblTid,lblName,lblPwd,lblAge,lblSex;
		lblTid=new JLabel("��ʦ�ţ�");
		lblName=new JLabel("������");
		lblPwd=new JLabel("���룺");
		lblAge=new JLabel("���䣺");
		lblSex=new JLabel("�Ա�");
		
		//�ı���jtfSid,jtfName,jtfPwd,jtfSex,jtfClass,jtfAge;
		jtfTid=new JTextField(15);
		jtfName=new JTextField(15);
		jtfPwd=new JTextField(15);
		jtfAge=new JTextField(15);
		jtfSex=new JTextField(15);
		
		//�������
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
		
		//��ť��ӡ�ȡ��
		btnAdd=new JButton("���");
		btnCancel=new JButton("ȡ��");
		
		jp6.add(btnAdd);
		jp6.add(btnCancel);
		
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
						ps=ct.prepareStatement("insert into teacher(tid,tname,tpassword,age,sex) values(?,?,?,?,?)");
						ps.setString(1, jtfTid.getText());
						ps.setString(2, jtfName.getText());
						ps.setString(3, jtfPwd.getText());
						ps.setString(4, jtfAge.getText());
						ps.setString(5, jtfSex.getText());
						
					//ͨ��ps����sql���,ͬʱ��ȡ����ֵ��Ӱ��ļ�¼��
						int len=ps.executeUpdate();
						 if(len==1){

			                    JOptionPane.showMessageDialog(null,"��ӽ�ʦ�ɹ�","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
			                }
			                else{
			                    JOptionPane.showMessageDialog(null,"��ӽ�ʦʧ�ܣ���ʦ���Ѵ��ڲ����ظ�","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
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
