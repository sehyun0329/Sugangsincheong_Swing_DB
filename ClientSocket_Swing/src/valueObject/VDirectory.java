package valueObject;
//VDirectory.java

import java.util.Vector;

public class VDirectory {
 private String name;
 private Vector<VDirectory> colleges;
 private Vector<VDirectory> departments;

 public VDirectory(String name) {
     this.name = name;
     this.colleges = new Vector<>();
     this.departments = new Vector<>();
 }

 public String getName() {
     return name;
 }

 public Vector<VDirectory> getColleges() {
     return colleges;
 }

 public Vector<VDirectory> getDepartments() {
     return departments;
 }

 public void addCollege(VDirectory college) {
     colleges.add(college);
 }

 public void addDepartment(VDirectory department) {
     departments.add(department);
 }
}
