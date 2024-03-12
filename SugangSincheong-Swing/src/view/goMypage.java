package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import valueObject.VAccount;

public class goMypage extends JFrame {

	goMypage(){
    super("마이페이지"); //타이틀
    JPanel jPanel = new JPanel();
    JFrame jFrame = new JFrame();

    jPanel.setBackground(Color.WHITE);

    setSize(400, 400);

    add(jPanel);
    
	ImageIcon image=new ImageIcon("image/MyPicture.jpg");
	Image img = image.getImage();
	Image changeImg = img.getScaledInstance(250,200,Image.SCALE_SMOOTH);
    ImageIcon changeIcon = new ImageIcon (changeImg);
	JLabel label = new JLabel (changeIcon);
	
	JLabel label1 = new JLabel("이름: 김세현 학번: 60202184 나이:23 ",JLabel.CENTER);
	JLabel label2 = new JLabel("학과: 응용소프트웨어학과 ",JLabel.CENTER);


	this.add(label1);
	this.add(label2);

	setLayout(new FlowLayout());
	this.add(label);

    Dimension frameSize = getSize();
    Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
    setLocation((windowSize.width - frameSize.width) / 2,
            (windowSize.height - frameSize.height) / 2); //화면 중앙에 띄우기
//    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setVisible(true);
}}
