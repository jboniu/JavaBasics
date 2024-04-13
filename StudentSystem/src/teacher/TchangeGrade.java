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
	public JLabel lblTip,lblSid,lblCid,lblChange;//��ѯ��ʾ��ѧ����ʾ���޸���ʾ
	public JTextField jtfSid,jtfCid,jtfChange;
	public JTable tableGrade;
	public DefaultTableModel modelGra;
	public JScrollPane jspGra;
	public JPanel jp1,jp2,jp3,jp4,jp5,jp6;
	public String titleGra[]={"ѧ��","ѧ����","�γ̺�","�γ���","��ʦ��","ѧ���ɼ�"};
	public JButton btnOK,btnChange,btnCancel;
	
	    //�洢ѧ����Ϣ
		String stuId,stuName;
		
		//�洢�γ���Ϣ
		String couId,couName;
		//�洢�ɼ���Ϣ
		int  graGrade;
		
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
	public TchangeGrade(String tname,String tid) {
		super("�޸ĳɼ�");
		
		//�洢��ʦ��Ϣ
		String teaId=tid,teaName=tname;
		
		setSize(500,600);
		setVisible(true);
		
		/******����Сͼ��********/
		ImageIcon icon = new ImageIcon("picture/��ƺŮ��.png");
		Image icon1=icon.getImage();
		this.setIconImage(icon1);
		
		
		this.setResizable(false);//��ֹ�û��޸Ĵ���
		this.setVisible(true);
		this.setLayout(new GridLayout(6,1));

		this.setLocationRelativeTo(null);//���ַ������趨һ�����ڵ����������һ�����ڵ�λ��
		
		Container c = getContentPane(); //��ȡJFrame���
		
        //�������
		//���jp1,jp2,jp3,jp4
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
		
		//��ǩlblTip,lblSid,lblCid,lblChange;//��ѯ��ʾ��ѧ����ʾ���޸���ʾ
		lblTip=new JLabel("��ѯѧ�����γɼ���");
		lblSid=new JLabel("ѧ�ţ�");
		lblCid=new JLabel("�γ̺ţ�");
		lblChange=new JLabel("�޸�ѧ���ɼ���");
		
		//�ı���jtfSid,jtfCid,jtfChange;
		jtfSid=new JTextField(15);
		jtfCid=new JTextField(15);
		jtfChange=new JTextField(15);
		
		//���ģ��modelGra
		modelGra=new DefaultTableModel(titleGra,1);
		
		//JTable tableGrade
		tableGrade=new JTable(modelGra);
		
		//JScrollPane jspGra
		jspGra=new JScrollPane(tableGrade);
		
		//��ťbtnOK,btnCancel,btnQueryStu,btnQueryTea,btnQueryCou,btnQueryGrade;
		btnOK=new JButton("ȷ��");
		btnChange=new JButton("�޸�");
		btnCancel=new JButton("ȡ��");
		
		
		//�������
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
			btnOK.addActionListener(new ActionListener() {
						
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(e.getActionCommand()=="ȷ��") {
						try {
							
//							"ѧ��","ѧ����","�γ̺�","�γ���","��ʦ��","ѧ���ɼ�"
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
								JOptionPane.showMessageDialog(null, "û�д�ѧ����ѧ��Ϊ�գ�\n����������", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);          
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
			//�޸�
			btnChange.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(e.getActionCommand()=="�޸�") {
						try {
							ps=ct.prepareStatement("update grade set grade=? where sid=? and cid=?");
							ps.setString(1, jtfChange.getText());
							ps.setString(2, jtfSid.getText());
							ps.setString(3, jtfCid.getText());
							
							//ͨ��ps����sql���,ͬʱ��ȡ����ֵ��Ӱ��ļ�¼��
							int len=ps.executeUpdate();
							if(len==1){

			                    JOptionPane.showMessageDialog(null,"�޸�ѧ���ɼ��ɹ�","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
			                }
			                else{
			                    JOptionPane.showMessageDialog(null,"�޸�ѧ���ɼ�ʧ�ܣ�����������ѧ���Ƿ�����","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
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
