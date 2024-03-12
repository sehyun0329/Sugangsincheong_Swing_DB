package viewSugangSincheong;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import valueObject.VLecture;

public class PSugangsincheongPanel extends JPanel {
    private PDirectoryPanel directoryPanel;
    private PLectureTable miridamgiPanel;
    private PLectureTable sincheongPanel;
    private PControlPanel controlPanel1;
    private PControlPanel controlPanel2;

    public PSugangsincheongPanel() {
        ActionHandler actionHandler = new ActionHandler();

        LayoutManager layoutManager = new BoxLayout(this, BoxLayout.X_AXIS);
        this.setLayout(layoutManager);
        
        directoryPanel = new PDirectoryPanel();
        this.add(directoryPanel);
        
        this.controlPanel1 = new PControlPanel("1", actionHandler);
		this.add(this.controlPanel1);

        miridamgiPanel = new PLectureTable();
        this.add(new JScrollPane(miridamgiPanel));
        
        this.controlPanel2 = new PControlPanel("2", actionHandler);
		this.add(this.controlPanel2);

        sincheongPanel = new PLectureTable();
        this.add(new JScrollPane(sincheongPanel));
        
        JButton saveToDatabaseButton = new JButton("저장");
        saveToDatabaseButton.addActionListener(actionHandler);
        this.add(saveToDatabaseButton);

    }
    
    private void saveToDatabase() {
        Vector<VLecture> vLectures = sincheongPanel.getSelectedLectures();
        DatabaseManager.saveLectures(vLectures);
        System.out.println("saveToDatabase");
    }


    private void moveFromLectureToMiridamgi() {
        Vector<VLecture> vLectures = directoryPanel.getSelectedLectures();
        miridamgiPanel.addLectures(vLectures);
        System.out.println("moveFromLectureToMiridamgi");
    }

    private void moveFromMiridamgiToLecture() {
        Vector<VLecture> vLectures = miridamgiPanel.getSelectedLectures();
        miridamgiPanel.removeSelectedLectures();
        System.out.println("moveFromMiridamgiToLecture");
    }

    private void moveFromMiridamgiToSincheong() {
        Vector<VLecture> vLectures = miridamgiPanel.getSelectedLectures();
        sincheongPanel.setLectures(vLectures);
        System.out.println("moveFromMiridamgiToSincheong");
    }

    private void moveFromSincheongToMiridamgi() {
        Vector<VLecture> vLectures = sincheongPanel.getSelectedLectures();
        miridamgiPanel.addLectures(vLectures);
        sincheongPanel.removeSelectedLectures();
        System.out.println("moveFromSincheongToMiridamgi");
    }

    public class ActionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getActionCommand().compareTo("1>>") == 0) {
                moveFromLectureToMiridamgi();
            } else if (event.getActionCommand().compareTo("1<<") == 0) {
                moveFromMiridamgiToLecture();
            } else if (event.getActionCommand().compareTo("2>>") == 0) {
                moveFromMiridamgiToSincheong();
            } else if (event.getActionCommand().compareTo("2<<") == 0) {
                moveFromSincheongToMiridamgi();
            } else if (event.getActionCommand().compareTo("저장") == 0) {
                saveToDatabase();
            }
        }
    }
}
