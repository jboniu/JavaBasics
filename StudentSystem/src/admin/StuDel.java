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
    
	public StuDel() {
		super("ɾ��ѧ��");
		
		/******����Сͼ��********/
		ImageIcon icon = new ImageIcon("picture/��ƺŮ��.png");
		Image icon1=icon.getImage();
		this.setIconImage(icon1);
		

		
		setSize(500,480);
		this.setResizable(false);//��ֹ�û��޸Ĵ���
		this.setVisible(true);
		this.setLayout(new GridLayout(2,1));
//		this.setLayout(new BorderLayout());

		this.setLocationRelativeTo(null);//���ַ������趨һ�����ڵ����������һ�����ڵ�λ��
		
		Container c = getContentPane(); //��ȡJFrame���
		
		//���
		jp1=new JPanel();
		jp2=new JPanel();
		
		
		c.add(jp1);
		c.add(jp2);
		
		//���
		//
		lblId=new JLabel("��������ɾ��ѧ����ѧ���ţ�");
		jtfId=new JTextField(15);
		
		jp1.add(lblId);
		jp1.add(jtfId);
		
		//��ťɾ����ȡ��
		btnDel=new JButton("ɾ��");
		btnCancel=new JButton("ȡ��");
		
		jp2.add(btnDel);
		jp2.add(btnCancel);
		
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
		btnDel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					ps=ct.prepareStatement("delete from student where sid = ?");
					ps.setString(1, jtfId.getText());
					
				//ͨ��ps����sql���,ͬʱ��ȡ����ֵ��Ӱ��ļ�¼��
					int len=ps.executeUpdate();
					 if(len==1){

		                    JOptionPane.showMessageDialog(null,"ɾ��ѧ���ɹ�","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
		                }
		                else{
		                    JOptionPane.showMessageDialog(null,"ɾ��ѧ��ʧ�ܣ�����������ѧ���Ƿ�����","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
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
