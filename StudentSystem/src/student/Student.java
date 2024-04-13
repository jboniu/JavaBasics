package student;

//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Container;
//import java.awt.Font;
//import java.awt.Graphics;
//import java.awt.Image;
//
//
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JToolBar;
//import javax.swing.SwingConstants;

import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import login.Login;

public class Student extends JFrame{
	public int age;
	public String sid,sname,spassword,sex,sclass;
	public JToolBar toolBar;
	public JButton btnQueryGrade,btnChangePwd; //��ѯ�ɼ����޸�����
	public JLabel lblTitle,lblTip1,lblTip2,lblTip3;
	public JPanel jpn,jpn1,jpc;//������
	public JTable jtable;
	public Student(String sname,String sid,String spassword) {
		super("��ƺŮ�Ӹ߼���ѧѧ������ϵͳ");
//		this.setSize(810, 570);

		
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
		this.setResizable(false);//��ֹ�û��޸Ĵ���
		this.setVisible(true);
//		this.setLayout(new GridLayout(6,1));
		this.setLayout(new BorderLayout());

		this.setLocationRelativeTo(null);//���ַ������趨һ�����ڵ����������һ�����ڵ�λ��
		
		Container c = getContentPane(); //��ȡJFrame���
		
		jpn = new JPanel(); //��������JPanel
		
		jpc = new JPanel(); //��������JPanel
		jpc.setLayout(null);
		
		c.add(jpn,BorderLayout.NORTH);
		c.add(jpc,BorderLayout.CENTER);

		
		jpn.setOpaque(false); //��JPanel����Ϊ͸��
		jpc.setOpaque(false);
		
		/**********��**********/
		lblTitle=new JLabel("��ƺŮ�Ӹ߼���ѧѧ������ϵͳ");
		Font fontTitle = new Font("����", Font.BOLD, 28);
        lblTitle.setFont(fontTitle);
        lblTitle.setForeground(Color.BLACK);
       
		jpn.add(lblTitle);

		
		
		
		
		/***********��**************/
		lblTip1=new JLabel("��ã�"+sname+"ͬѧ��");
//		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);//center label text
        lblTip1.setFont(new Font("����", Font.BOLD, 18));
        lblTip1.setForeground(Color.blue);

        lblTip1.setBounds(0,0,250,40);

        jpc.add(lblTip1);

        
		//��ť
		btnQueryGrade=new JButton("��ѯ��Ϣ");
		btnChangePwd=new JButton("�޸�����");
		
		btnQueryGrade.setFont(new Font("����",Font.BOLD,20));
		btnChangePwd.setFont(new Font("����",Font.BOLD,20));
		
		btnQueryGrade.setBounds(170,80,130,40);
		btnChangePwd.setBounds(380,80,130,40);
		
		jpc.add(btnQueryGrade);
		jpc.add(btnChangePwd);
		
		btnQueryGrade.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				new queryGrade(sname,sid);
			}
		});
		
		btnChangePwd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new changePwd(sname,sid);
				
			}
		});
		
		
	}
	
//	public static void main(String[] args) {
//		Student s = new Student("�ž�","100");
//
//		s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//	}
	
	

	
}
