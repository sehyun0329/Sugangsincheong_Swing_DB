package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import global.Constants;
import global.Locale;
import service.SLogin;
import valueObject.VAccount;

public class PLoginDialog extends JDialog
{
	private static final long serialVersionUID = 1L;

	private JTextField tfId;
	private JPasswordField tfPassword;

	private SLogin sLogin;

	//public PLoginDialog(ActionHandler actionHandler)
	public PLoginDialog()
	{

		this.setTitle("수강신청 로그인");

		this.setSize(Constants.CLoginDialog.WIDTH, Constants.CLoginDialog.HEIGHT);
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(size.width / 2 - this.getWidth() / 2, Constants.CLoginDialog.Y);
		this.setModal(true);
		
		ImageIcon image=new ImageIcon("image/mju.jpg");
		JLabel img1=new JLabel(image);
        img1.setHorizontalAlignment(JLabel.CENTER);
		this.add(img1);

		LayoutManager layoutManager = new FlowLayout();
		this.setLayout(layoutManager);

		JLabel lbId = new JLabel(Locale.LLoginPanel.ID_LABEL);
		this.add(lbId);
		lbId.setFont(new Font("맑은 고딕", Font.BOLD, 12)); //폰트

		this.tfId = new JTextField();
		this.tfId.setColumns(10);
		this.add(tfId);

		JLabel lbPassword = new JLabel(Locale.LLoginPanel.PASSWORD_LABEL);
		lbPassword.setFont(new Font("맑은 고딕", Font.BOLD, 12)); //폰트
		this.add(lbPassword);

		this.tfPassword = new JPasswordField();
		this.tfPassword.setColumns(10);
		this.add(tfPassword);

		JButton btLogin = new JButton(Locale.OK_LABEL);
		this.getRootPane().setDefaultButton(btLogin);
	    btLogin.setBackground(Color.YELLOW);
	    btLogin.setFont(new Font("맑은 고딕", Font.BOLD, 12)); //폰트
		this.add(btLogin, BorderLayout.SOUTH);
		btLogin.addActionListener(new ActionHandler());
		
	    JButton btLogin2 = new JButton("취소");
	    
	        setSize(600, 680);
		    btLogin2.setBackground(Color.RED);
		    btLogin2.setFont(new Font("맑은 고딕", Font.BOLD, 12)); //폰트
			this.add(btLogin2, BorderLayout.EAST);
			btLogin2.addActionListener(new ActionHandler());
			
//	        btLogin2.addActionListener(new ActionListener() {
//	            @Override
//	            public void actionPerformed(ActionEvent e) {
//	                System.exit(0);

		this.sLogin = new SLogin();
	}

	@SuppressWarnings("unused")
	private ImageIcon imageSetsize(ImageIcon image, int i, int j) {
		return null;
	}

	public VAccount login()
	{
		String id = this.tfId.getText();
		char[] password = this.tfPassword.getPassword();
		return this.sLogin.login(id);
	}

	/* 클래스 PLoginDialog 의 btLogin 버튼을 누루면
	클래스 ActionHandler 을 생성해서 아이디와 암호를 처리함

	클래스 PLoginDialog 의 btLogin 버튼을 사용하고
	아이디(tfId)와 암호(tfPassword)를 받아서 사용해야 하기 때문에
	클래스 PLoginDialog에 클래스 ActionHandler를 넣어 사용해줌.*/
	
	class ActionHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			// 사용자 ID 유효성 검사
			// 사용자가 값 입력시 공백 무시하기 위하여 trim을 사용해줌
			String userId = tfId.getText().trim();
			if (userId.isEmpty())
			{
				JOptionPane.showMessageDialog(null, "아이디를 입력하세요.");
				return;
			}

			// 사용자 비밀번호 유효성 검사
			// JPasswordField.getPassword() 사용 -> char[] 배열을 반환
			// JOptionPane은 parentComponent,message,title,messageType 순 사용
			char[] secretPassword = tfPassword.getPassword();
			if (secretPassword.length == 0)
			{
				JOptionPane.showMessageDialog(null, "비밀번호를 입력하세요.");
				return;
			}

			// 로그인 처리
			VAccount account = sLogin.login(userId);
			//JPasswordField.getText() 메서드는 보안상의 이유로 deprecated (비권장) 선언된 메서드이기에 사용 X
			// JPasswordField.getPassword() 사용을 권장하고 있고 char[] 배열을 반환
			// 문자열을 한 글자씩 쪼개서 char형의 배열에 작업을 투입하여 주는 방식
			
			if (account != null && Arrays.equals(secretPassword, account.getPassword().toCharArray()))
			{
				JOptionPane.showMessageDialog(null, "로그인에 성공하였습니다.");
				JOptionPane.showMessageDialog(null, account.getName() + "님 환영합니다.");
				dispose(); // dispose() 메소드는 프로그램의 종료가 아닌 현재의 frame을 종료시킴 / 프레임만 닫히고 다른 프레임은 열려있는 상태
				PMainFrame mainFrame = new PMainFrame(account);
				mainFrame.setVisible(true);

			} else
			{
				JOptionPane.showMessageDialog(null, "로그인에 실패하였습니다.");
			}
			
		}
		
	}
	}