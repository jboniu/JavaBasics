package teacher;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JToolBar;

import admin.TeaAdd;

public class Teacher extends JFrame{
	public int age;
	public String sid,sname,spassword,sex,sclass;
	public JToolBar toolBar;
	public JButton btnQueryMess,btnChangePwd,btnChangeGrade; //��ѯ��Ϣ���޸�����,�޸ĳɼ�
	public JLabel lblTitle,lblTip,lblType,lblWay;
	public JPanel jp1,jp2,jp3,jp4,jp5;
	public JComboBox<String> comboBox1,comboBox2;
	public JTable jtable;
	public Teacher(String tname,String tid) {
		super("��ƺŮ�Ӹ߼���ѧѧ������ϵͳ");
		
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
		
		this.setSize(img.getIconWidth(), img.getIconHeight());
//		this.setSize(500,800);
		this.setResizable(false);//��ֹ�û��޸Ĵ���
		this.setVisible(true);
		this.setLayout(new GridLayout(3,1));
//		this.setLayout(new BorderLayout());

		this.setLocationRelativeTo(null);//���ַ������趨һ�����ڵ����������һ�����ڵ�λ��
		
		Container c = getContentPane(); //��ȡJFrame���
		
		//���jp1,jp2,jp3,jtp1,jp4,jtp2,jp5;
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		
		
		jp1.setOpaque(false);
		jp2.setOpaque(false);
		jp3.setOpaque(false);
		
		
		c.add(jp1);
		c.add(jp2);
		c.add(jp3);
		
		
		
		//��ǩlblTitle,lblTip;
		lblTitle=new JLabel("��ƺŮ�Ӹ߼���ѧѧ������ϵͳ");
		lblTitle.setFont(new Font("����", Font.BOLD, 28));
        lblTitle.setForeground(Color.BLACK);
        
        lblTip=new JLabel("��ã�"+tname+"��ʦ��");
        lblTip.setFont(new Font("����", Font.BOLD, 18));
        lblTip.setForeground(Color.blue);
        
       
		jp1.add(lblTitle);
        jp2.add(lblTip);
        jp2.setLayout(new FlowLayout(FlowLayout.LEFT));
        
		
		
		//��ťbtnOK,btnCancel;
        
      //��ť
      		btnQueryMess=new JButton("��ѯ��Ϣ");
      		btnChangePwd=new JButton("�޸�����");
      		btnChangeGrade=new JButton("�޸ĳɼ�");
      		
      		btnQueryMess.setFont(new Font("����",Font.BOLD,20));
      		btnChangePwd.setFont(new Font("����",Font.BOLD,20));
      		btnChangeGrade.setFont(new Font("����",Font.BOLD,20));
		
		jp3.add(btnQueryMess);
		jp3.add(btnChangePwd);
		jp3.add(btnChangeGrade);
		
		//��������
		btnQueryMess.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new TqueryMess(tname,tid);
			}
		});
		
		btnChangePwd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new TchangePwd(tname,tid);
			}
		});
		btnChangeGrade.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new TchangeGrade(tname,tid);
			}
		});
		
	}
}
