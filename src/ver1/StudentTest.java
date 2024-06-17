package ver1;

import java.sql.Connection;
import java.util.Scanner;

public class StudentTest {

	public static void main(String[] args) {

		StudentReopositoryImpl impl = new StudentReopositoryImpl();
		try (Connection conn = DBConnection.getconnection(); Scanner scanner = new Scanner(System.in)) {

			while (true) {
				System.out.println();
				System.out.println("----------------------------------------");
				System.out.println("1. 학생 추가 ");
				System.out.println("2. 학생 전체 조회");
				System.out.println("3. 학생 전체 삭제");
				System.out.println("4. 학생 정보 수정");
				System.out.print("옵션을 선택 하세요: ");

				int choice = scanner.nextInt();

				if (choice == 1) {
					scanner.nextLine();
					System.out.println("추가할 학생 이름을 입력하세요");
					String name = scanner.nextLine();
					System.out.println("추가할 학생 skdl을 입력하세요");
					int age = scanner.nextInt();
					scanner.nextLine();
					System.out.println("추가할 학생 dlapdlf을 입력하세요");
					String email = scanner.nextLine();
					impl.addStudent(name, age, email);
				} else if (choice == 2) {
					impl.viewStuent();
					
				} else if (choice == 3) {
					impl.deleteStudent();
				} else if (choice == 4) {
					System.out.println("프로그램을 종료 합니다");
					break;
				} else {
					System.out.println("잘못된 선택입니다. 다시 시도하세요.");
				}
			}

		} catch (Exception e) {

		}

	}

}