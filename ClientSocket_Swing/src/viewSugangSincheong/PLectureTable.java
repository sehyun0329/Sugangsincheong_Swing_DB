package viewSugangSincheong;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import service.SLecture;
import valueObject.VDirectory;
import valueObject.VLecture;

public class PLectureTable extends JTable {
    private static final long serialVersionUID = 1L;

    private SLecture sLecture;
    private Vector<VLecture> vLectures;

    private DefaultTableModel tableModel;

    private Vector<VDirectory> selectedLectures;

    private int[] selectedLectureIndexes;

    public PLectureTable() {
        Vector<String> header = new Vector<String>();
        header.add("강좌번호");
        header.add("강좌명");
        header.add("교수명");
        header.add("학점");
        header.add("시간");
        this.tableModel = new DefaultTableModel(header, 0);
        this.setModel(this.tableModel);
    }

    public Vector<VLecture> getSelectedLectures() {
        DefaultTableModel model = (DefaultTableModel) this.getModel();
        selectedLectures = new Vector<VDirectory>();
        selectedLectureIndexes = this.getSelectedRows();

        Vector<VLecture> selectedLectures = new Vector<VLecture>();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("directory/Basket.txt", true))) {
            for (int i = 0; i < selectedLectureIndexes.length; i++) {
                VLecture vLecture = new VLecture();
                vLecture.setId(model.getValueAt(selectedLectureIndexes[i], 0).toString());
                vLecture.setName(model.getValueAt(selectedLectureIndexes[i], 1).toString());
                vLecture.setProfessor(model.getValueAt(selectedLectureIndexes[i], 2).toString());
                vLecture.setCredit(model.getValueAt(selectedLectureIndexes[i], 3).toString());
                vLecture.setTime(model.getValueAt(selectedLectureIndexes[i], 4).toString());

                String lectureInfo = vLecture.getId() + ", " + vLecture.getName() + ", " +
                        vLecture.getProfessor() + ", " + vLecture.getCredit() + ", " + vLecture.getTime();

                writer.write(lectureInfo);
                writer.newLine();

                selectedLectures.add(vLecture);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return selectedLectures;
    }

    public void setData(String fileName) {
        this.sLecture = new SLecture();
        this.vLectures = sLecture.getLectures(fileName);

        this.tableModel.setNumRows(0);
        for (VLecture vLecture : vLectures) {
            Vector<String> row = new Vector<String>();
            row.add(vLecture.getId());
            row.add(vLecture.getName());
            row.add(vLecture.getProfessor());
            row.add(vLecture.getCredit());
            row.add(vLecture.getTime());

            this.tableModel.addRow(row);
        }
        this.setRowSelectionInterval(0, 0);
    }

    public void add(Vector<String> row) {
        // TODO Auto-generated method stub
    }

    public void addLectures(Vector<VLecture> lectures) {
        for (VLecture lecture : lectures) {
            Vector<String> row = new Vector<String>();
            row.add(lecture.getId());
            row.add(lecture.getName());
            row.add(lecture.getProfessor());
            row.add(lecture.getCredit());
            row.add(lecture.getTime());

            this.tableModel.addRow(row);
        }
    }

    public VLecture getLectureAt(int row) {
        String id = (String) this.tableModel.getValueAt(row, 0);
        String name = (String) this.tableModel.getValueAt(row, 1);
        String professor = (String) this.tableModel.getValueAt(row, 2);
        String credit = (String) this.tableModel.getValueAt(row, 3);
        String time = (String) this.tableModel.getValueAt(row, 4);

        VLecture vLecture = new VLecture();
        vLecture.setId(id);
        vLecture.setName(name);
        vLecture.setProfessor(professor);
        vLecture.setCredit(credit);
        vLecture.setTime(time);

        return vLecture;
    }
    
    public void setLectures(Vector<VLecture> lectures) {
        for (VLecture lecture : lectures) {
            Vector<String> row = new Vector<String>();
            row.add(lecture.getId());
            row.add(lecture.getName());
            row.add(lecture.getProfessor());
            row.add(lecture.getCredit());
            row.add(lecture.getTime());

            this.tableModel.addRow(row);
        }
    }

    public void removeSelectedLectures() {
        int[] selectedRows = this.getSelectedRows();
        for (int i = selectedRows.length - 1; i >= 0; i--) {
            this.tableModel.removeRow(selectedRows[i]);
        }
    }
    
    public void removeLectures(Vector<VLecture> lectures) {
        for (VLecture lecture : lectures) {
            int row = getRowByLecture(lecture);
            if (row >= 0) {
                this.tableModel.removeRow(row);
            }
        }
    }

    private int getRowByLecture(VLecture lecture) {
        for (int i = 0; i < this.tableModel.getRowCount(); i++) {
            String id = (String) this.tableModel.getValueAt(i, 0);
            if (id.equals(lecture.getId())) {
                return i;
            }
        }
        return -1;
    }

	public void clearLectures() {
        DefaultTableModel model = (DefaultTableModel) this.getModel();
        model.setNumRows(0); // 현재 데이터를 모두 삭제하여 강좌 목록 초기화
    }
}
