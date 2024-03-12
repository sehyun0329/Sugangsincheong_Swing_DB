package viewSugangSincheong;

import java.awt.BorderLayout;
import java.io.File;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import valueObject.VLecture;

public class PDirectoryPanel extends JPanel {
    private JTree directoryTree;
    private DefaultMutableTreeNode root;
    private PLectureTable lectureTablePanel;

    public PDirectoryPanel() {
        this.setLayout(new BorderLayout());

        // 초기 루트 노드 생성
        root = new DefaultMutableTreeNode("Root");

        // 디렉토리 정보 읽어오기
        File rootDirectory1 = new File("src/resources/directory/용인");
        File rootDirectory2 = new File("src/resources/directory/서울");
        if (rootDirectory1.exists() && rootDirectory1.isDirectory()) {
            populateTree(root, rootDirectory1);
        }
        if (rootDirectory2.exists() && rootDirectory2.isDirectory()) {
            populateTree(root, rootDirectory2);
        }

        // JTree 생성 및 설정
        directoryTree = new JTree(root);
        directoryTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        directoryTree.addTreeSelectionListener(new TreeSelectionHandler());

        JScrollPane treeScrollPane = new JScrollPane(directoryTree);

        // 강좌 정보를 표시하는 패널 생성
        lectureTablePanel = new PLectureTable();
        JScrollPane lectureTableScrollPane = new JScrollPane(lectureTablePanel);

        // SplitPane으로 JTree와 강좌 정보를 표시하는 패널을 나눔
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treeScrollPane, lectureTableScrollPane);
        splitPane.setDividerLocation(200); // JTree와 강좌 정보 표시 패널의 초기 크기 조절

        this.add(splitPane, BorderLayout.CENTER);
    }

    private void populateTree(DefaultMutableTreeNode parentNode, File directory) {
        DefaultMutableTreeNode dirNode = new DefaultMutableTreeNode(directory.getName());
        parentNode.add(dirNode);

        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    populateTree(dirNode, file);
                } else {
                    dirNode.add(new DefaultMutableTreeNode(new FileNode(file)));
                }
            }
        }
    }

    private class TreeSelectionHandler implements TreeSelectionListener {
        @Override
        public void valueChanged(TreeSelectionEvent event) {
            TreePath path = event.getPath();
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();

            Object userObject = selectedNode.getUserObject();
            if (userObject instanceof FileNode) {
                FileNode fileNode = (FileNode) userObject;
                String fileContents = fileNode.readFileContents();

                // 파일 내용을 파싱하여 Vector<VLecture>로 변환
                Vector<VLecture> lectures = parseFileContents(fileContents);

                // 기존 강좌 목록을 초기화하고 변환된 강좌 정보를 추가
                lectureTablePanel.clearLectures(); // 기존 목록 초기화
                lectureTablePanel.setLectures(lectures);
            }
        }

        public Vector<VLecture> getSelectedLectures() {
            // 현재 선택된 강좌들을 반환하는 코드 작성
            Vector<VLecture> selectedLectures = new Vector<>();

            TreePath[] paths = directoryTree.getSelectionPaths();
            if (paths != null) {
                for (TreePath path : paths) {
                    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
                    Object userObject = selectedNode.getUserObject();
                    if (userObject instanceof FileNode) {
                        FileNode fileNode = (FileNode) userObject;
                        String fileContents = fileNode.readFileContents();
                        Vector<VLecture> lectures = parseFileContents(fileContents);
                        selectedLectures.addAll(lectures);
                    }
                }
            }

            return selectedLectures;
        }

        public Vector<VLecture> parseFileContents(String fileContents) {
            // 파일 내용을 파싱하여 Vector<VLecture>로 반환하는 코드 작성
            Vector<VLecture> lectures = new Vector<>();
            String[] lines = fileContents.split("\n");
            for (String line : lines) {
                String[] parts = line.split(" ");
                if (parts.length == 5) {
                    VLecture lecture = new VLecture();
                    lecture.setId(parts[0]);
                    lecture.setName(parts[1]);
                    lecture.setProfessor(parts[2]);
                    lecture.setCredit(parts[3]);
                    lecture.setTime(parts[4]);
                    lectures.add(lecture);
                }
            }

            return lectures;
        }
        
        // TreeSelectionHandler 내에 clearLectures 메서드 추가
        private void clearLectures() {
            lectureTablePanel.clearLectures();
        }
    }

    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Directory Panel Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        PDirectoryPanel directoryPanel = new PDirectoryPanel();
        frame.getContentPane().add(directoryPanel);

        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public Vector<VLecture> getSelectedLectures() {
		Vector<VLecture> selectedLectures = new Vector<>();
		if (this.lectureTablePanel != null) {
			int[] selectedRows = this.lectureTablePanel.getSelectedRows();
			for (int selectedRow : selectedRows) {
				selectedLectures.add(this.lectureTablePanel.getLectureAt(selectedRow));
			}
		}
		return selectedLectures;
	}
}
