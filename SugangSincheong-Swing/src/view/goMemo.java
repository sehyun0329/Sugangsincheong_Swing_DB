package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class goMemo extends JFrame {
	goMemo () {
        setTitle("메모장");
	    JFrame jFrame = new JFrame("메모장");

	    setSize(400, 400);
		JTextField tf = new JTextField(3000);
	    this.add(tf);
		
	    Dimension frameSize = getSize();
	    Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
	    setLocation((windowSize.width - frameSize.width) / 2,
	            (windowSize.height - frameSize.height) / 2); //화면 중앙에 띄우기
	    setVisible(true);
	    
	}
}
