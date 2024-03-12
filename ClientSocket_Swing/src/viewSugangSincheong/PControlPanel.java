package viewSugangSincheong;

import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import viewSugangSincheong.PSugangsincheongPanel.ActionHandler;

public class PControlPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private JButton buttonRight;
	private JButton buttonLeft;
	public PControlPanel(String panelId, ActionHandler actionHandler) {
		LayoutManager layoutManager = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layoutManager);

		this.buttonRight = new JButton(">>");
		buttonRight.setBackground(Color.YELLOW);
		this.buttonRight.addActionListener(actionHandler);
		this.buttonRight.setActionCommand(panelId+this.buttonRight.getText());
		this.add(this.buttonRight);
		
		this.buttonLeft = new JButton("<<");
		buttonLeft.setBackground(Color.YELLOW);
		this.buttonLeft.addActionListener(actionHandler);
		this.buttonLeft.setActionCommand(panelId+this.buttonLeft.getText());
		this.add(this.buttonLeft);
	}
}
