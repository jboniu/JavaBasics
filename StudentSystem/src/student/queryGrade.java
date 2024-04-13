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
	public JTable jtable1,jtable2;//������Ϣ���ɼ�
	

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
	
    
 


	
	public queryGrade(String sname,String sid) {
		super("��ѯ��Ϣ");
		setSize(500,500);
		
		/******����Сͼ��********/
		ImageIcon icon = new ImageIcon("picture/��ƺŮ��.png");
		Image icon1=icon.getImage();
		this.setIconImage(icon1);
		
		/*************����ͼƬ************/
		((JPanel)this.getContentPane()).setOpaque(false);
		ImageIcon img = new ImageIcon("picture/��ҳ����1.png"); //���ͼƬ
		JLabel background = new JLabel(img);
		this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
		background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		
//		this.setSize(img.getIconWidth(), img.getIconHeight());
		this.setResizable(false);//��ֹ�û��޸Ĵ���
		this.setVisible(true);
		this.setLayout(new GridLayout(8,1));
//		this.setLayout(new BorderLayout());

		this.setLocationRelativeTo(null);//���ַ������趨һ�����ڵ����������һ�����ڵ�λ��
		
		Container c = getContentPane(); //��ȡJFrame���
		
        //�������
		//���jp1,jp2,jp3,jtp1,jp4,jtp2,jp5
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
		
		//��ǩlblName,lblName1,lblSid,lblSid1
		lblName=new JLabel("������");
		lblName1=new JLabel(sname);
		lblSid=new JLabel("ѧ�ţ�");
		lblSid1=new JLabel(sid);
		
		jp1.add(lblName);
		jp1.add(lblName1);
		jp2.add(lblSid);
		jp2.add(lblSid1);
		
		jp1.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp2.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		//��ѯ��ťbtnQueryGrade,btnQueryMess
		btnQueryMess=new JButton("������ѯ");
		btnQueryGrade=new JButton("�ɼ���ѯ");
		
		jp3.add(btnQueryMess);
		jp4.add(btnQueryGrade);
		
		
		
		//���
		
		String[] colnames1 = {"ѧ��","����","����","�Ա�","�༶","����"};
		DefaultTableModel model1 = new DefaultTableModel(colnames1,1);
		
		jtable1=new JTable(model1);
//		jtable1.addRowSelectionInterval(array1);
		jtp1.add(new JScrollPane(jtable1));
		
		String[] colnames2 = {"����","��ѧ","Ӣ��","����","��ѧ","����","��ʷ","����","����"};
		DefaultTableModel model2 = new DefaultTableModel(colnames2,1);

		jtable2=new JTable(model2);
		jtp2.add(new JScrollPane(jtable2));
		
		// �������ݿ������
		try {
	        Class.forName(driverName);
	        ct=DriverManager.getConnection(dbURL, userName, userPwd);
	    } catch(ClassNotFoundException e1) {   
	        //���ݿ��������쳣����
	        System.out.println("Sorry,can`t find the Driver!");   
	        e1.printStackTrace();   
	        } catch(SQLException e1) {
	        //���ݿ�����ʧ���쳣����
	        e1.printStackTrace();  
	        }catch (Exception e1) {
	        // TODO: handle exception
	        e1.printStackTrace();
	    }finally{
	        System.out.println("���ݿ����ݳɹ���ȡ����"); 
	     
	    }
				
				
				//��������
				btnQueryMess.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
						
						if(e.getActionCommand()=="������ѯ") {
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
									JOptionPane.showMessageDialog(null, "û�д��û����û���Ϊ�գ�\n����������", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);          
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
						
						
						if(e.getActionCommand()=="�ɼ���ѯ") {
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
		
		//���ء��˳�
		btnBack=new JButton("����");
		btnExit=new JButton("�˳�");
		
		jp5.add(btnBack);
		jp5.add(btnExit);
		
		//��������
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
