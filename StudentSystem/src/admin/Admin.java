package admin;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
//import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JToolBar;

//import javax.swing.*;

public class Admin extends JFrame{
	public int age;
	public String sid,sname,spassword,sex,sclass;
	public JToolBar toolBar;
	public JButton btnOK,btnCancel; //ȷ�ϣ�ȡ��
	public JLabel lblTitle,lblTip,lblType,lblWay;
	public JPanel jp1,jp2,jp3,jp4,jp5;
	public JComboBox<String> comboBox1,comboBox2;
	public JTable jtable;
	
	
	
	public Admin(String aname) {
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
		this.setLayout(new GridLayout(5,1));
//		this.setLayout(new BorderLayout());

		this.setLocationRelativeTo(null);//���ַ������趨һ�����ڵ����������һ�����ڵ�λ��
		
		Container c = getContentPane(); //��ȡJFrame���
		
		//���jp1,jp2,jp3,jtp1,jp4,jtp2,jp5;
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		jp4=new JPanel();
		jp5=new JPanel();
		
		jp1.setOpaque(false);
		jp2.setOpaque(false);
		jp3.setOpaque(false);
		jp4.setOpaque(false);
		jp5.setOpaque(false);
		
		c.add(jp1);
		c.add(jp2);
		c.add(jp3);
		c.add(jp4);
		c.add(jp5);
		
		
		//��ǩlblTitle,lblTip,lblType,lblWay;
		lblTitle=new JLabel("��ƺŮ�Ӹ߼���ѧѧ������ϵͳ");
		lblTitle.setFont(new Font("����", Font.BOLD, 28));
        lblTitle.setForeground(Color.BLACK);
        
        lblTip=new JLabel("��ã�"+aname+"����Ա��");
        lblTip.setFont(new Font("����", Font.BOLD, 18));
        lblTip.setForeground(Color.blue);
        lblType=new JLabel("�������ͣ�");
        lblType.setFont(new Font("����", Font.BOLD, 18));
        lblWay=new JLabel("���·�ʽ��");
        lblWay.setFont(new Font("����", Font.BOLD, 18));
        
//        jp1.add(lblTitle);
//        jp2.add(lblTip);
//        jp2.setLayout(new FlowLayout(FlowLayout.LEFT));
//        lblTip.setFont(new Font("����", Font.BOLD, 18));
//        lblTip.setForeground(Color.blue);
//        jp3.add(lblType);
//        jp4.add(lblWay);
        
        //�����б�comboBox1,comboxBox2
        comboBox1 = new JComboBox<String>();//����һ�������б��
//		comboBox.setBounds(340, 50, 220, 35);//��������
		comboBox1.addItem("��ʦ");
		comboBox1.addItem("ѧ��");
		comboBox1.addItem("ȫ��");
		comboBox1.setFont(new Font("����", Font.PLAIN, 16));
		comboBox1.setEditable(false);
		
		comboBox2 = new JComboBox<String>();//����һ�������б��
		comboBox2.addItem("����");
		comboBox2.addItem("ɾ��");
		comboBox2.addItem("�޸�");
		comboBox2.addItem("��ѯ");
		comboBox2.setFont(new Font("����", Font.PLAIN, 16));
		comboBox2.setEditable(false);
		
		
		
		
		jp1.add(lblTitle);
        jp2.add(lblTip);
        jp2.setLayout(new FlowLayout(FlowLayout.LEFT));
        jp3.add(lblType);
		jp3.add(comboBox1);
		jp4.add(lblWay);
		jp4.add(comboBox2);
		
		
		//��ťbtnOK,btnCancel;
		btnOK=new JButton("ȷ��");
		btnCancel=new JButton("ȡ��");
		
		jp5.add(btnOK);
		jp5.add(btnCancel);
		
		//��������
		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getActionCommand()=="ȷ��") {
					//ѡ�й���Ա
					if(comboBox1.getSelectedItem().toString()=="��ʦ"&&comboBox2.getSelectedItem().toString()=="����") {
						new TeaAdd();
					}
					else if(comboBox1.getSelectedItem().toString()=="��ʦ"&&comboBox2.getSelectedItem().toString()=="ɾ��"){
						new TeaDel();
					}
					else if(comboBox1.getSelectedItem().toString()=="��ʦ"&&comboBox2.getSelectedItem().toString()=="�޸�"){
						new TeaUpd();
					}
					else if(comboBox1.getSelectedItem().toString()=="��ʦ"&&comboBox2.getSelectedItem().toString()=="��ѯ"){
						JOptionPane.showMessageDialog(null, "��ѡ��ȫ�������в�ѯ", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
					}
					
					//ѡ��ѧ��
					else if(comboBox1.getSelectedItem().toString()=="ѧ��"&&comboBox2.getSelectedItem().toString()=="����") {
						new StuAdd();
					}
					else if(comboBox1.getSelectedItem().toString()=="ѧ��"&&comboBox2.getSelectedItem().toString()=="ɾ��"){
						new StuDel();
					}
					else if(comboBox1.getSelectedItem().toString()=="ѧ��"&&comboBox2.getSelectedItem().toString()=="�޸�"){
						new StuUpd();
					}
					else if(comboBox1.getSelectedItem().toString()=="ѧ��"&&comboBox2.getSelectedItem().toString()=="��ѯ"){
						JOptionPane.showMessageDialog(null, "��ѡ��ȫ�������в�ѯ", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
					}
					//ѡ��ȫ��
					else if(comboBox1.getSelectedItem().toString()=="ȫ��"&&comboBox2.getSelectedItem().toString()=="��ѯ"){
						new AllQue();
					}
					else if(comboBox1.getSelectedItem().toString()=="ȫ��"&&comboBox2.getSelectedItem().toString()=="����"){
						JOptionPane.showMessageDialog(null, "�ݲ�֧������ȫ��", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
					}
					else if(comboBox1.getSelectedItem().toString()=="ȫ��"&&comboBox2.getSelectedItem().toString()=="�޸�"){
						JOptionPane.showMessageDialog(null, "�ݲ�֧���޸�ȫ��", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
					}
					else if(comboBox1.getSelectedItem().toString()=="ȫ��"&&comboBox2.getSelectedItem().toString()=="ɾ��"){
						JOptionPane.showMessageDialog(null, "�ݲ�֧��ɾ��ȫ��", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
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
