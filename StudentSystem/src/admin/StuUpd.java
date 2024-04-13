package admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

public class StuUpd extends JFrame{
	public JLabel lblSid,lblTip,lblUpd;//ѧ�ţ�������ʾ���޸ĵ�ֵ
	public JTextField jtfSid,jtfUpd;
	public JPanel jp1,jp2,jp3,jp4;
	public JButton btnUdp,btnCancel;
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
	
	public StuUpd() {
		super("��ѯ��Ϣ");
		setSize(500,600);
		setVisible(true);
		
		/******����Сͼ��********/
		ImageIcon icon = new ImageIcon("picture/��ƺŮ��.png");
		Image icon1=icon.getImage();
		this.setIconImage(icon1);
		
		
		this.setResizable(false);//��ֹ�û��޸Ĵ���
		this.setVisible(true);
		this.setLayout(new GridLayout(12,1));

		this.setLocationRelativeTo(null);//���ַ������趨һ�����ڵ����������һ�����ڵ�λ��
		
		Container c = getContentPane(); //��ȡJFrame���
		
        //�������
		//���jp1,jp2,jp3,jp4;
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
		
		//��ǩlblSid,lblTip,lblUpd;//ѧ�ţ�������ʾ���޸ĵ�ֵ
		lblSid=new JLabel("�޸�ѧ����ѧ�ţ�");
		lblTip=new JLabel("�޸�ѧ�������ԣ�");
		lblUpd=new JLabel("�޸ĺ��ֵ��");
		
		//�ı���jtfSid,jtfTip,jtfUpd;
		jtfSid=new JTextField(15);
		comboBox=new JComboBox<String>();
		comboBox.addItem("����");
		comboBox.addItem("����");
		comboBox.addItem("�Ա�");
		comboBox.addItem("�༶");
		comboBox.addItem("����");
		jtfUpd=new JTextField(15);
		
		jp1.add(lblSid);
		jp1.add(jtfSid);
		
		jp2.add(lblTip);
		jp2.add(comboBox);
		
		jp3.add(lblUpd);
		jp3.add(jtfUpd);
		
		//��ťbtnUdp,btnCancel;
		btnUdp=new JButton("�޸�");
		btnCancel=new JButton("ȡ��");
		
		jp4.add(btnUdp);
		jp4.add(btnCancel);
		
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
		btnUdp.addActionListener(new ActionListener() {
							
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				//ѡ������
				if(comboBox.getSelectedItem().toString()=="����") {
					try {
						ps=ct.prepareStatement("update Student set sname=? where sid=? ");
						ps.setString(1, jtfUpd.getText());
						ps.setString(2, jtfSid.getText());
						
						//ͨ��ps����sql���,ͬʱ��ȡ����ֵ��Ӱ��ļ�¼��
						int len=ps.executeUpdate();
						if(len==1){

		                    JOptionPane.showMessageDialog(null,"�޸�ѧ�������ɹ�","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
		                }
		                else{
		                    JOptionPane.showMessageDialog(null,"�޸�ѧ������ʧ�ܣ�����������ѧ���Ƿ�����","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
		                }
					}catch(SQLException e1) {
						e1.printStackTrace();
					}
				}
				//ѡ������
				else if(comboBox.getSelectedItem().toString()=="����"){
					try {
						ps=ct.prepareStatement("update Student set spassword=? where sid=? ");
						ps.setString(1, jtfUpd.getText());
						ps.setString(2, jtfSid.getText());
						
						//ͨ��ps����sql���,ͬʱ��ȡ����ֵ��Ӱ��ļ�¼��
						int len=ps.executeUpdate();
						if(len==1){

		                    JOptionPane.showMessageDialog(null,"�޸�ѧ������ɹ�","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
		                }
		                else{
		                    JOptionPane.showMessageDialog(null,"�޸�ѧ������ʧ�ܣ�����������ѧ���Ƿ�����","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
		                }
					}catch(SQLException e1) {
						e1.printStackTrace();
					}
				}
				//ѡ���Ա�
				else if(comboBox.getSelectedItem().toString()=="�Ա�"){
					try {
						ps=ct.prepareStatement("update Student set sex=? where sid=? ");
						ps.setString(1, jtfUpd.getText());
						ps.setString(2, jtfSid.getText());
						
						//ͨ��ps����sql���,ͬʱ��ȡ����ֵ��Ӱ��ļ�¼��
						int len=ps.executeUpdate();
						if(len==1){

		                    JOptionPane.showMessageDialog(null,"�޸�ѧ���Ա�ɹ�","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
		                }
		                else{
		                    JOptionPane.showMessageDialog(null,"�޸�ѧ���Ա�ʧ�ܣ�����������ѧ���Ƿ�����","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
		                }
					}catch(SQLException e1) {
						e1.printStackTrace();
					}
					
				}
				//ѡ�а༶
				else if(comboBox.getSelectedItem().toString()=="�༶"){
					try {
						ps=ct.prepareStatement("update Student set sclass=? where sid=? ");
						ps.setString(1, jtfUpd.getText());
						ps.setString(2, jtfSid.getText());
						
						//ͨ��ps����sql���,ͬʱ��ȡ����ֵ��Ӱ��ļ�¼��
						int len=ps.executeUpdate();
						if(len==1){

		                    JOptionPane.showMessageDialog(null,"�޸�ѧ���༶�ɹ�","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
		                }
		                else{
		                    JOptionPane.showMessageDialog(null,"�޸�ѧ���༶ʧ�ܣ�����������ѧ���Ƿ�����","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
		                }
					}catch(SQLException e1) {
						e1.printStackTrace();
					}
				}
				//ѡ������
				else if(comboBox.getSelectedItem().toString()=="����"){
					try {
						ps=ct.prepareStatement("update Student set age=? where sid=? ");
						ps.setString(1, jtfUpd.getText());
						ps.setString(2, jtfSid.getText());
						
						//ͨ��ps����sql���,ͬʱ��ȡ����ֵ��Ӱ��ļ�¼��
						int len=ps.executeUpdate();
						if(len==1){

		                    JOptionPane.showMessageDialog(null,"�޸�ѧ������ɹ�","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
		                }
		                else{
		                    JOptionPane.showMessageDialog(null,"�޸�ѧ������ʧ�ܣ�����������ѧ���Ƿ�����","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
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
				
								dispose();
				}
		});		
	}
}
