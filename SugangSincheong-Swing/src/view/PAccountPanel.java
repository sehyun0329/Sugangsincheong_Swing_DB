package view;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import global.Constants;
import global.Locale;
import valueObject.VAccount;

public class PAccountPanel extends JPanel {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	
	public PAccountPanel(VAccount vAccount) {
		JButton myPage = new JButton("마이페이지");
		this.add(myPage, "North");
		myPage.setFont(new Font("맑은 고딕", Font.BOLD, 12)); //폰트
		myPage.setBackground(Color.GREEN);
		
		myPage.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				new goMypage();
			}
		});
		
		JButton memo = new JButton("메모장");
		memo.setFont(new Font("맑은 고딕", Font.BOLD, 12)); //폰트
		memo.setBackground(Color.orange);
		this.add(memo, "South");
		
		memo.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				new goMemo();
			}
		});
		
			
		
//		this.setFocusable(false);

		
		JLabel lName = new JLabel(vAccount.getName());
		this.add(lName);
		
		JLabel lGreeting = new JLabel(Locale.INSA_POSTFIX);
		this.add(lGreeting);
		
		JLabel lLogin = new JLabel(Locale.LOGIN_TIME_PREFIX);
		this.add(lLogin);
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Locale.TIME_FORMAT);
		JLabel lTime = new JLabel(simpleDateFormat.format(new Date()));
		this.add(lTime);
		
		JLabel lDescription = new JLabel(Locale.IPNIDA);
		this.add(lDescription);
		
		
			  }
			 
			}