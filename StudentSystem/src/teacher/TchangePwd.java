package teacher;

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
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;

public class TchangePwd extends JFrame{
	public JLabel lblOldPwd,lblNewPwd,lblPwd;
	public JPanel jp1,jp2,jp3,jp4;
	public JTextField oldPwdField;
	public JPasswordField newPwdField,okPwdField;
	public JButton btnChange,btnCancel; 
	public JTable jtable1;
	public String teaId,teaName,teaPwd,teaSex;
	public int teaAge;
	
	String tid1; // �洢��ʦ��
    String old_password; // �洢������
    
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
	public TchangePwd(String tname,String tid) {
		super("�޸�����");
		tid1=tid;
		setSize(500,480);
		setVisible(true);
		
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
		this.setLayout(new GridLayout(4,1));

		this.setLocationRelativeTo(null);//���ַ������趨һ�����ڵ����������һ�����ڵ�λ��
		
		Container c = getContentPane(); //��ȡJFrame���
		
        //�������
		//���jp1,jp2,jp3,jp4
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		jp4=new JPanel();
		
		c.add(jp1);
		c.add(jp2);
		c.add(jp3);
		c.add(jp4);
		
		//��ǩlblOldPwd,lblNewPwd,lblPwd;
		lblOldPwd=new JLabel("ԭ���룺");
		lblNewPwd=new JLabel("�����룺");
		lblPwd=new JLabel("ȷ�����룺");
		
		jp1.add(lblOldPwd);
		jp2.add(lblNewPwd);
		jp3.add(lblPwd);
		
		
		//�ı��������
		oldPwdField=new JTextField(15);
		newPwdField=new JPasswordField(15);
		okPwdField=new JPasswordField(15);
		
		jp1.add(oldPwdField);
		jp2.add(newPwdField);
		jp3.add(okPwdField);
		
//		jp1.setLayout(new FlowLayout(FlowLayout.LEFT));
//		jp2.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		//��ť�޸ġ�ȡ�� btnChange,btnCancel
		btnChange=new JButton("�޸�");
		btnCancel=new JButton("ȡ��");
		
		jp4.add(btnChange);
		jp4.add(btnCancel);
		
		try {
            Class.forName(driverName);
            ct = DriverManager.getConnection(dbURL, userName, userPwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		//��������
		btnChange.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getActionCommand() == "�޸�"){
		            try {
		                ps = ct.prepareStatement("select * from teacher where tid=? ");
		                ps.setString(1, tid);

		                rs=ps.executeQuery();
		                //ȡ����Ӧ��ʦ��Ϣ
		                while(rs.next()){
		                    old_password = rs.getString(3);      //����ʦ�ľ�����ȡ���������ж�ԭ�����Ƿ���ȷ
		                }
		            } catch (SQLException e1) {
		                e1.printStackTrace();
		            }
		            change_password();
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
		
		//
		
		
		
		
		jp1.setOpaque(false);
		jp2.setOpaque(false);
		jp3.setOpaque(false);
		jp4.setOpaque(false);
		
		
		
		
		}
		
	
	
	public void change_password() {
		String new_PwdField=String.valueOf(newPwdField.getPassword());
		String ok_PwdField=String.valueOf(okPwdField.getPassword());
		
		if(old_password.equals(oldPwdField.getText()) && new_PwdField.equals(ok_PwdField) && !new_PwdField.isEmpty() && !ok_PwdField.isEmpty()){
            try{
                String sql="update teacher set tpassword=? where tid=? ";
                ps = ct.prepareStatement(sql);
                ps.setString(1, new_PwdField);
                ps.setString(2, tid1);
                ps.executeUpdate();//ִ��sql���
                ct.close();
            }catch (SQLException e1) {
                e1.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "�޸ĳɹ�", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
            dispose();
        }else if(oldPwdField.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "������ԭ���룡", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
        }else if(!old_password.equals(oldPwdField.getText()))
        {
            JOptionPane.showMessageDialog(null, "ԭ�������\n���������룡", "��ʾ��Ϣ", JOptionPane.ERROR_MESSAGE);
        }else if(new_PwdField.isEmpty() || ok_PwdField.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "���������ȷ������Ϊ��\n���������룡", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
        }else if(!new_PwdField.equals(ok_PwdField))
        {
            JOptionPane.showMessageDialog(null, "ȷ�������������벻��\n���������룡", "��ʾ��Ϣ", JOptionPane.ERROR_MESSAGE);
        }
		
	}
}