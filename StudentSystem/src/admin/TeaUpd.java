package admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

public class TeaUpd extends JFrame{
	public JLabel lblTid,lblTip,lblUpd;//��ʦ�ţ�������ʾ���޸ĵ�ֵ
	public JTextField jtfTid,jtfUpd;
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
	
	public TeaUpd() {
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
		
		//��ǩlblTid,lblTip,lblUpd;//ѧ�ţ�������ʾ���޸ĵ�ֵ
		lblTid=new JLabel("�޸Ľ�ʦ��ѧ�ţ�");
		lblTip=new JLabel("�޸Ľ�ʦ�����ԣ�");
		lblUpd=new JLabel("�޸ĺ��ֵ��");
		
		//�ı���jtfTid,jtfTip,jtfUpd;
		jtfTid=new JTextField(15);
		comboBox=new JComboBox<String>();
		comboBox.addItem("����");
		comboBox.addItem("����");
		comboBox.addItem("����");
		comboBox.addItem("�Ա�");
		jtfUpd=new JTextField(15);
		
		jp1.add(lblTid);
		jp1.add(jtfTid);
		
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
						ps=ct.prepareStatement("update teacher set tname=? where tid=? ");
						ps.setString(1, jtfUpd.getText());
						ps.setString(2, jtfTid.getText());
						
						//ͨ��ps����sql���,ͬʱ��ȡ����ֵ��Ӱ��ļ�¼��
						int len=ps.executeUpdate();
						if(len==1){

		                    JOptionPane.showMessageDialog(null,"�޸Ľ�ʦ�����ɹ�","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
		                }
		                else{
		                    JOptionPane.showMessageDialog(null,"�޸Ľ�ʦ����ʧ�ܣ�����������ѧ���Ƿ�����","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
		                }
					}catch(SQLException e1) {
						e1.printStackTrace();
					}
				}
				//ѡ������
				else if(comboBox.getSelectedItem().toString()=="����"){
					try {
						ps=ct.prepareStatement("update teacher set tpassword=? where tid=? ");
						ps.setString(1, jtfUpd.getText());
						ps.setString(2, jtfTid.getText());
						
						//ͨ��ps����sql���,ͬʱ��ȡ����ֵ��Ӱ��ļ�¼��
						int len=ps.executeUpdate();
						if(len==1){

		                    JOptionPane.showMessageDialog(null,"�޸Ľ�ʦ����ɹ�","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
		                }
		                else{
		                    JOptionPane.showMessageDialog(null,"�޸Ľ�ʦ����ʧ�ܣ�����������ѧ���Ƿ�����","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
		                }
					}catch(SQLException e1) {
						e1.printStackTrace();
					}
				}
				//ѡ������
				else if(comboBox.getSelectedItem().toString()=="����"){
					try {
						ps=ct.prepareStatement("update teacher set age=? where tid=? ");
						ps.setString(1, jtfUpd.getText());
						ps.setString(2, jtfTid.getText());
						
						//ͨ��ps����sql���,ͬʱ��ȡ����ֵ��Ӱ��ļ�¼��
						int len=ps.executeUpdate();
						if(len==1){

		                    JOptionPane.showMessageDialog(null,"�޸Ľ�ʦ����ɹ�","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
		                }
		                else{
		                    JOptionPane.showMessageDialog(null,"�޸Ľ�ʦ����ʧ�ܣ�����������ѧ���Ƿ�����","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
		                }
					}catch(SQLException e1) {
						e1.printStackTrace();
					}
					
				}
				
				//ѡ���Ա�
				else if(comboBox.getSelectedItem().toString()=="�Ա�"){
					try {
						ps=ct.prepareStatement("update teacher set sex=? where tid=? ");
						ps.setString(1, jtfUpd.getText());
						ps.setString(2, jtfTid.getText());
						
						//ͨ��ps����sql���,ͬʱ��ȡ����ֵ��Ӱ��ļ�¼��
						int len=ps.executeUpdate();
						if(len==1){

		                    JOptionPane.showMessageDialog(null,"�޸Ľ�ʦ�Ա�ɹ�","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
		                }
		                else{
		                    JOptionPane.showMessageDialog(null,"�޸Ľ�ʦ�Ա�ʧ�ܣ�����������ѧ���Ƿ�����","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
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
