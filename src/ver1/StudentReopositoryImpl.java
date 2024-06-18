package ver1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentReopositoryImpl implements StudentRepository {

	public static final String ADD_STUDENT = "  insert into students (name, age, email) values(? , ?, ?) ";
	public static final String VIEW_STUDENT = " select * from students ";
	public static final String DELETE_STUDENT = " delete from students ";
	public static final String EDIT_STUDENT = " update students set name = ? where name = ? ";

	@Override
	public int addStudent(String name, int age, String email) throws SQLException {

		int resultRowCount = 0;

		try (Connection conn = DBConnection.getconnection()) {
			PreparedStatement pstmt = conn.prepareStatement(ADD_STUDENT);

			pstmt.setString(1, name);
			pstmt.setInt(2, age);
			pstmt.setString(3, email);
			pstmt.executeUpdate();

		}

		return resultRowCount;
	}

	@Override
	public List<StudentDTO> viewStuent() throws SQLException {

		List<StudentDTO> list = new ArrayList<>();

		try (Connection conn = DBConnection.getconnection()) {
			PreparedStatement pstmt = conn.prepareStatement(VIEW_STUDENT);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String email = rs.getString("email");
				list.add(new StudentDTO(id, name, age, email));

			}
		}
		for (StudentDTO studentDTO : list) {
			System.out.println(studentDTO.getId());
			System.out.println(studentDTO.getName());
			System.out.println(studentDTO.getAge());
			System.out.println(studentDTO.getEmail());

		}

		return list;
	}

	@Override
	public StudentDTO deleteStudent() throws SQLException {

		try (Connection conn = DBConnection.getconnection()) {
			PreparedStatement pstmt = conn.prepareStatement(DELETE_STUDENT);
			pstmt.executeUpdate();
			System.out.println("데이터가 모두 삭제되었습니다");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public void updateStudent(String name) throws SQLException {

		try (Connection conn = DBConnection.getconnection()) {
			PreparedStatement pstmt = conn.prepareStatement(EDIT_STUDENT);
			pstmt.setString(3, name);

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
