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
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class AllQue extends JFrame{
	public JButton btnCancel,btnQueryStu,btnQueryTea,btnQueryCou,btnQueryGrade;
	public JLabel lblSid,lblTid,lblCid,lblSid1,lblCid1;
	public JTextField jtfSid,jtfTid,jtfCid,jtfSid1,jtfCid1;
	public JPanel jp1,jp2,jp3,jp4,jp5,jp6,jp7,jp8,jp9,jp10,jp11,jp12;
	public JTable tableStu,tableTea,tableCou,tableGra;
	public DefaultTableModel modelStu,modelTea,modelCou,modelGra;
	public JScrollPane jspStu,jspTea,jspCou,jspGra;//�����ܶ���
	public String titleStu[]={"ѧ��","����","����","�Ա�","�༶","����"};
	public String titleTea[]={"��ʦ��","����","����","����","�Ա�"};
	public String titleCou[]={"�γ̺�","�γ���","��ʦ��","ѧϰ����"};
	public String titleGra[]={"ѧ��","�γ̺�","�ɼ�"};
	
	
	
	//�洢ѧ����Ϣ
	String stuId,stuName,spassword,ssex,sclass;
	int sage;
	//�洢��ʦ��Ϣ
	String teaId,teaName,tpassword,tsex;
	int tage;
	//�洢�γ���Ϣ
	String couId,couName,couTid;
	int couCredit;
	//�洢�ɼ���Ϣ
	String graSid,graCid;
	int graGrade;
	
	
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
	
	public AllQue() {
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
		//���jp1,jp2,jp3,jp4,jp5,jp6,jp7,jp8;
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		jp4=new JPanel();
		jp5=new JPanel();
		jp6=new JPanel();
		jp7=new JPanel();
		jp8=new JPanel();
		jp9=new JPanel();
		jp10=new JPanel();
		jp11=new JPanel();
		jp12=new JPanel();
		
		c.add(jp1);
		c.add(jp2);
		c.add(jp3);
		c.add(jp4);
		c.add(jp5);
		c.add(jp6);
		c.add(jp7);
		c.add(jp8);
		c.add(jp9);
		c.add(jp10);
		c.add(jp11);
		c.add(jp12);
		
		jp1.setOpaque(false);
		jp2.setOpaque(false);
		jp3.setOpaque(false);
		jp4.setOpaque(false);
		jp5.setOpaque(false);
		jp6.setOpaque(false);
		jp7.setOpaque(false);
		jp8.setOpaque(false);
		jp9.setOpaque(false);
		jp10.setOpaque(false);
		jp11.setOpaque(false);
		jp12.setOpaque(false);
		
		//��ǩlblSid,lblTid,lblCid,lblSid1,lblCid1;
		lblSid=new JLabel("ѧ�ţ�");
		lblTid=new JLabel("��ʦ�ţ�");
		lblCid=new JLabel("�γ̺ţ�");
		lblSid1=new JLabel("ѧ�ţ�");
		lblCid1=new JLabel("�γ̺ţ�");
		
		
		//�ı���jtfSid,jtfTid,jtfCid,jtfSid1;
		jtfSid=new JTextField(15);
		jtfTid=new JTextField(15);
		jtfCid=new JTextField(15);
		jtfSid1=new JTextField(15);
		jtfCid1=new JTextField(15);
		//���ģ��modelStu,modelTea,modelCou,modelGra;
		modelStu=new DefaultTableModel(titleStu,1);
		modelTea=new DefaultTableModel(titleTea,1);
		modelCou=new DefaultTableModel(titleCou,1);
		modelGra=new DefaultTableModel(titleGra,1);
		//JTable tableStu,tableTea,tableCou,tableGra;
		tableStu=new JTable(modelStu);
		tableTea=new JTable(modelTea);
		tableCou=new JTable(modelCou);
		tableGra=new JTable(modelGra);
		//JScrollPane jspStu,jspTea,jspCou,jspGra;//�����ܶ���
		jspStu=new JScrollPane(tableStu);
		jspTea=new JScrollPane(tableTea);
		jspCou=new JScrollPane(tableCou);
		jspGra=new JScrollPane(tableGra);
		
		//��ťbtnCancel,btnQueryStu,btnQueryTea,btnQueryCou,btnQueryGrade;
		btnQueryStu=new JButton("��ѯѧ��");
		btnQueryTea=new JButton("��ѯ��ʦ");
		btnQueryCou=new JButton("��ѯ�γ�");
		btnQueryGrade=new JButton("��ѯ�ɼ�");
		
		//�������
		jp1.add(lblSid);
		jp1.add(jtfSid);
				
		jp2.add(jspStu);
		jp3.add(btnQueryStu);
				
		jp4.add(lblTid);
		jp4.add(jtfTid);
				
		jp5.add(jspTea);
		jp6.add(btnQueryTea);
		
		jp7.add(lblCid);
		jp7.add(jtfCid);
				
		jp8.add(jspCou);
		jp9.add(btnQueryCou);
				
		jp10.add(lblSid1);
		jp10.add(jtfSid1);
		jp10.add(lblCid1);
		jp10.add(jtfCid1);
				
		jp11.add(jspGra);
		jp12.add(btnQueryGrade);
		
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
		btnQueryStu.addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getActionCommand()=="��ѯѧ��") {
					try {
						ps=ct.prepareStatement("select * from student where sid=?");
						ps.setString(1, jtfSid.getText());
						
						rs=ps.executeQuery();
						if(rs.next()) {
							stuId=rs.getString(1);
							stuName=rs.getString(2);
							spassword=rs.getString(3);
							ssex=rs.getString(4);
							sclass=rs.getString(5);
							sage=rs.getInt(6);
							
						}
						else {
							JOptionPane.showMessageDialog(null, "û�д�ѧ����ѧ��Ϊ�գ�\n����������", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);          
						}
					}catch(SQLException e1) {
						e1.printStackTrace();
					}
					tableStu.setValueAt(stuId, 0, 0);
					tableStu.setValueAt(stuName, 0, 1);
					tableStu.setValueAt(spassword, 0, 2);
					tableStu.setValueAt(ssex, 0, 3);
					tableStu.setValueAt(sclass, 0, 4);
					tableStu.setValueAt(sage, 0, 5);
				}
				}
		});
				
		btnQueryTea.addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getActionCommand()=="��ѯ��ʦ") {
					try {
						ps=ct.prepareStatement("select * from teacher where tid=?");
						ps.setString(1, jtfTid.getText());
						
						rs=ps.executeQuery();
						if(rs.next()) {
							teaId=rs.getString(1);
							teaName=rs.getString(2);
							tpassword=rs.getString(3);
							tage=rs.getInt(4);
							tsex=rs.getString(5);
							
						}
						else {
							JOptionPane.showMessageDialog(null, "û�д˽�ʦ���ʦ��Ϊ�գ�\n����������", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);          
						}
					}catch(SQLException e1) {
						e1.printStackTrace();
					}
					tableTea.setValueAt(teaId, 0, 0);
					tableTea.setValueAt(teaName, 0, 1);
					tableTea.setValueAt(tpassword, 0, 2);
					tableTea.setValueAt(tage, 0, 3);
					tableTea.setValueAt(tsex, 0, 4);
				}
				}
		});		
		
		btnQueryCou.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getActionCommand()=="��ѯ�γ�") {
					try {
						ps=ct.prepareStatement("select * from course where cid=?");
						ps.setString(1, jtfCid.getText());
						
						rs=ps.executeQuery();
						if(rs.next()) {
							couId=rs.getString(1);
							couName=rs.getString(2);
							couTid=rs.getString(3);
							couCredit=rs.getInt(4);
							
							
							
						}
						else {
							JOptionPane.showMessageDialog(null, "û�д˿γ̻�γ̺�Ϊ�գ�\n����������", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);          
						}
					}catch(SQLException e1) {
						e1.printStackTrace();
					}
					tableCou.setValueAt(couId, 0, 0);
					tableCou.setValueAt(couName, 0, 1);
					tableCou.setValueAt(couTid, 0, 2);
					tableCou.setValueAt(couCredit, 0, 3);
				}
			}
		});		
		
		btnQueryGrade.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(e.getActionCommand()=="��ѯ�ɼ�") {
					try {
						ps=ct.prepareStatement("select * from grade where sid=? and cid=?");
						ps.setString(1, jtfSid1.getText());
						ps.setString(2, jtfCid1.getText());
						
						rs=ps.executeQuery();
						if(rs.next()) {
							graSid=rs.getString(1);
							graCid=rs.getString(2);
							graGrade=rs.getInt(3);
						}
						else {
							JOptionPane.showMessageDialog(null, "û�д˳ɼ���Ϣ��\n����������", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);          
						}
					}catch(SQLException e1) {
						e1.printStackTrace();
					}
					tableGra.setValueAt(graSid, 0, 0);
					tableGra.setValueAt(graCid, 0, 1);
					tableGra.setValueAt(graGrade, 0, 2);
				}	
			}
		});		
		
		
		
	}
	
}
