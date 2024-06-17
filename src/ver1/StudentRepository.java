package ver1;

import java.sql.SQLException;
import java.util.List;

public interface StudentRepository {
	
	int addStudent(String name, int age, String email) throws SQLException;
	
	List<StudentDTO> viewStuent() throws SQLException;
	
	StudentDTO deleteStudent() throws SQLException;
	
	
	
	

}
