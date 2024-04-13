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
	
	
	// �趨�û���������
    String user_name;
    String password;
    
    //����Ա�����͹���Ա��
    String adm_name;
    String aid;

    // ѧ��������ѧ��
    String stu_name;
    String sid;
    String stu_password;

    // ��ʦ�����ͽ̹���
    String tea_name;
    String tid;
    String tea_password;


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
	
 
    public static void main(String[] args) {
		new Login();

	}
    
	public Login(){
		super("��¼");
		
		 /******����Сͼ��********/
		ImageIcon icon = new ImageIcon("picture/��ƺŮ��.png");
		Image icon1=icon.getImage();
		this.setIconImage(icon1);
		
		/*************����ͼƬ************/
		((JPanel)this.getContentPane()).setOpaque(false);
		ImageIcon img = new ImageIcon("picture/��ƺŮУ1.jpg"); //���ͼƬ
		JLabel background = new JLabel(img);
		this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
		background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		
	
		
		Container c = getContentPane(); //��ȡJFrame���
		
		jpn = new JPanel(); //��������JPanel
		jpc = new JPanel(); //��������JPanel
		jpc.setLayout(null);
		
		c.add(jpn,BorderLayout.NORTH);
		c.add(jpc,BorderLayout.CENTER);
		
		jpn.setOpaque(false); //��JPanel����Ϊ͸��
		jpc.setOpaque(false); 

		
		/************��************/
		lblTitle=new JLabel("��ƺŮ�Ӹ߼���ѧѧ������ϵͳ");
//		lblTitle.setBounds(80,50,500,100);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);//center label text
        Font fontTitle = new Font("�����п�", Font.BOLD, 40);
        lblTitle.setFont(fontTitle);
        lblTitle.setForeground(Color.white);
		jpn.add(lblTitle);
	


		/************��************/
		lblType=new JLabel("��  �ͣ�");
		lblType.setBounds(230, 50, 200, 35);
		lblType.setFont(new Font("����", Font.BOLD, 22));
		lblType.setForeground(new Color(176,171,178));
		comboBox = new JComboBox<String>();//����һ�������б��
		comboBox.setBounds(340, 50, 220, 35);//��������
		comboBox.addItem("����Ա");
		comboBox.addItem("��ʦ");
		comboBox.addItem("ѧ��");
		comboBox.setFont(new Font("����", Font.PLAIN, 22));
		comboBox.setEditable(false);
		
		jpc.add(lblType);
		jpc.add(comboBox);
		
		
		
		
		
		lblName=new JLabel("�û���:");
		lblName.setBounds(230, 100, 200, 35);
		lblName.setFont(new Font("����", Font.BOLD, 22));
		lblName.setForeground(new Color(176,171,198));
		
		lblPassword=new JLabel("��  ��:");
		lblPassword.setBounds(230, 150, 200, 35);
		lblPassword.setFont(new Font("����", Font.BOLD, 22));
		lblPassword.setForeground(new Color(176,171,198));
		
		jtfName=new JTextField(15);
		jtfName.setBounds(340, 100, 220, 35);
		jtfName.setFont(new Font("����", Font.PLAIN, 22));
		
		jtfpassword=new JPasswordField(15);
		jtfpassword.setBounds(340, 150, 220, 35);
	
		jpc.add(lblName);
		jpc.add(jtfName);
		jpc.add(lblPassword);
		jpc.add(jtfpassword);
		
		
		btnLogin=new JButton("��¼");
		btnLogin.setBounds(255, 260, 120, 35);
		btnCancel=new JButton("ȡ��");
		btnCancel.setBounds(420, 260, 120, 35);
		
		
		
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
		
		/****************��¼***************/
		btnLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getActionCommand()=="��¼") {
					//ѡ�й���Ա
					if(comboBox.getSelectedItem().toString()=="����Ա") {
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
								JOptionPane.showMessageDialog(null, "û�д��û����û���Ϊ�գ�\n����������", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);          
							}
						}catch(SQLException e1) {
							e1.printStackTrace();
						}
					}
					//ѡ��ѧ��
					else if(comboBox.getSelectedItem().toString()=="ѧ��"){
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
								JOptionPane.showMessageDialog(null, "û�д��û����û���Ϊ�գ�\n����������", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);          
							}
						}catch(SQLException e1) {
							e1.printStackTrace();
						}
					}
					//ѡ�н�ʦ
					else if(comboBox.getSelectedItem().toString()=="��ʦ"){
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
								JOptionPane.showMessageDialog(null, "û�д��û����û���Ϊ�գ�\n����������", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);          
							}
						}catch(SQLException e1) {
							e1.printStackTrace();
						}
					}
					
				}
			}
		});
		
		/************ȡ��*******************/
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

		this.setResizable(false);//��ֹ�û��޸Ĵ���
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);//���ַ������趨һ�����ڵ����������һ�����ڵ�λ��
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	

	
	//�жϵ�¼
	public void admin_login() {
		if(user_name.equals(jtfName.getText())&&password.equals(String.valueOf(jtfpassword.getPassword()))) {
			JOptionPane.showMessageDialog(null, "��¼�ɹ�", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
			new Admin(adm_name);
		}
		else if(jtfName.getText().isEmpty() && String.valueOf(jtfpassword.getPassword()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "�������û��������룡", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
        }
		else if(jtfName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "�������û�����", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
        }
		else if(String.valueOf(jtfpassword.getPassword()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "���������룡", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
        }
		else {
            JOptionPane.showMessageDialog(null, "�û������������\n����������", "��ʾ��Ϣ", JOptionPane.ERROR_MESSAGE);
        }
	}
	public void stu_login() {
		if(user_name.equals(jtfName.getText())&&password.equals(String.valueOf(jtfpassword.getPassword()))) {
			JOptionPane.showMessageDialog(null, "��¼�ɹ�", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
			new Student(stu_name,sid,stu_password);
		}
		else if(jtfName.getText().isEmpty() && String.valueOf(jtfpassword.getPassword()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "�������û��������룡", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
        }
		else if(jtfName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "�������û�����", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
        }
		else if(String.valueOf(jtfpassword.getPassword()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "���������룡", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
        }
		else {
            JOptionPane.showMessageDialog(null, "�û������������\n����������", "��ʾ��Ϣ", JOptionPane.ERROR_MESSAGE);
        }
	}
	public void tea_login() {
		if(user_name.equals(jtfName.getText())&&password.equals(String.valueOf(jtfpassword.getPassword()))) {
			JOptionPane.showMessageDialog(null, "��¼�ɹ�", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
			 new Teacher(tea_name,tid);
		}
		else if(jtfName.getText().isEmpty() && String.valueOf(jtfpassword.getPassword()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "�������û��������룡", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
        }
		else if(jtfName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "�������û�����", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
        }
		else if(String.valueOf(jtfpassword.getPassword()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "���������룡", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
        }
		else {
            JOptionPane.showMessageDialog(null, "�û������������\n����������", "��ʾ��Ϣ", JOptionPane.ERROR_MESSAGE);
        }
	}

	
}
