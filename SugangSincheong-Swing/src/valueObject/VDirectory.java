package valueObject;
import java.util.Scanner;

public class VDirectory {
   private String id;
   private String name;
   private String fileName;
   
   public String getId( ) {
      return id;
      }
   public String getName() {
      return name;
   }
   public String getFileName() {
      return fileName;
   }
   public void read(Scanner scanner) {
      this.id=scanner.next();
      this.name= scanner.next();
      this.fileName=scanner.next();
   }
public void setName(String string) {
	// TODO Auto-generated method stub
	
}
public void setProfessor(String string) {
	// TODO Auto-generated method stub
	
}
public void setId(String string) {
	// TODO Auto-generated method stub
	
}
public void setCredit(String string) {
	// TODO Auto-generated method stub
	
}
public void setLecture(VLecture vLecture) {
	// TODO Auto-generated method stub
	
}
}