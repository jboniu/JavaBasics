package teacher;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TqueryMess extends JFrame{
	public JLabel lblName,lblName1,lblTid,lblTid1;
	public JPanel jp1,jp2,jp3,jp4;
	public JButton btnQueryMess;
	public JTable jtable1;
	public String teaId,teaName,teaPwd,teaSex;
	public int teaAge;
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
    
    
	public TqueryMess(String tname,String tid) {
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
		this.setLayout(new GridLayout(6,1));
//		this.setLayout(new BorderLayout());

		this.setLocationRelativeTo(null);//���ַ������趨һ�����ڵ����������һ�����ڵ�λ��
		
		Container c = getContentPane(); //��ȡJFrame���
		
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
		
		//��ǩlblName,lblName1,lblSid,lblSid1
		lblName=new JLabel("������");
		lblName1=new JLabel(tname);
		lblTid=new JLabel("��ʦ�ţ�");
		lblTid1=new JLabel(tid);
		
		jp1.add(lblName);
		jp1.add(lblName1);
		jp2.add(lblTid);
		jp2.add(lblTid1);
		
		jp1.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp2.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		//���
		
		String[] colnames1 = {"��ʦ��","����","����","����","�Ա�"};
		DefaultTableModel model1 = new DefaultTableModel(colnames1,1);		
		jtable1=new JTable(model1);
//				jtable1.addRowSelectionInterval(array1);
		jp3.add(new JScrollPane(jtable1));
		
		//��ť
		btnQueryMess=new JButton("��ѯ");
		jp4.add(btnQueryMess);
		
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
				
				
				if(e.getActionCommand()=="��ѯ") {
					try {
						ps=ct.prepareStatement("select * from teacher where  tname=? and tid=?");
						ps.setString(1, lblName1.getText());
						ps.setString(2, lblTid1.getText());
						
						rs=ps.executeQuery();
						if(rs.next()) {
							teaId=rs.getString(1);
							teaName=rs.getString(2);
							teaPwd=rs.getString(3);
							teaAge=rs.getInt(4);
							teaSex=rs.getString(5);
							
							
						}
						else {
							JOptionPane.showMessageDialog(null, "û�д��û����û���Ϊ�գ�\n����������", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);          
						}
					}catch(SQLException e1) {
						e1.printStackTrace();
					}
					jtable1.setValueAt(teaId, 0, 0);
					jtable1.setValueAt(teaName, 0, 1);
					jtable1.setValueAt(teaPwd, 0, 2);
					jtable1.setValueAt(teaAge, 0, 3);
					jtable1.setValueAt(teaSex, 0, 4);
				}
			}
		});
	}
}
