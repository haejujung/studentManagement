package ver2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ver2.model.StudentDTO;

// 기능 설계는 인터페이스를 먼저 자것ㅇ하고 구현 클래스를 만드는 것이 좋다
public class StudentDAO {

	// 학생 정보 추가 기능 만들기
	public void addStudent(ver2.model.StudentDTO dto) throws SQLException {
		String query = " insert into students (name, age, email) values(?, ?, ?) ";
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getName());
			pstmt.setInt(2, dto.getAge());
			pstmt.setString(3, dto.getEmail());
			pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	// 학생에 이름 또는 (id)로 조회하는 기능 만들기
	public ver2.model.StudentDTO getStudentById(int id) throws SQLException {

		String query = " select * from students where id = ?";
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);

			try (ResultSet rs = pstmt.executeQuery()) {

				if (rs.next()) {
					return new ver2.model.StudentDTO(rs.getInt("id"), rs.getString("name"), rs.getInt("age"),
							rs.getString("email"));
				}
			}

		}

		return null;
	}

	// 학생 전체 조회 기능
    public List<StudentDTO> getAllStudents() throws SQLException{
        // tip - 리스트라면 무조건 리스트를 생성하고 코드 작성하기
        List<StudentDTO> list = new ArrayList<>();
        String query =" SELECT * FROM students ";
        try(Connection conn = DBConnectionManager.getInstance().getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                // builder 할때는 마지막에 build를 꼭 호출
                StudentDTO dto = new StudentDTO().builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .age(rs.getInt("age"))
                .email(rs.getString("email"))
                .build();

                list.add(dto);
            }
        }
        return list;
    }
	public void updateStudent(String name, StudentDTO dto) throws SQLException {
		String query = " update students set name = ?, age = ?, email = ? where name = ? ";
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getName());
			pstmt.setInt(2, dto.getAge());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, name); // 조건 값 세팅
			pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void deleteStudent(int id) throws SQLException {
		String query = " delete from students where id = ? ";
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
