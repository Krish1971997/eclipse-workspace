package com.qsp.studentapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.qsp.studentapp.dto.Student;

public class StudentDatabaseOperations {

	private static final String DRIVERCLASSS = "com.mysql.cj.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/java?user=root&password=root";

	public boolean insertStudent(Student stu) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			Class.forName(DRIVERCLASSS);
			String dburl = DBURL;
			con = DriverManager.getConnection(dburl);

			String query = "insert into student_1 values(?,?,?,?,?)";
			ps = con.prepareStatement(query);

			ps.setInt(1, stu.getId());
			ps.setString(2, stu.getName());
			ps.setDouble(3, stu.getMarks());
			ps.setString(4, stu.getEmailId());
			ps.setString(5, stu.getPassword());
			int rowAffected = ps.executeUpdate();

			if (rowAffected != 0)
				return true;
			else
				return false;

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	// Login Student
	// @SuppressWarnings("finally")
	public Student loginValidate(String email, String pswd) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVERCLASSS);
			con = DriverManager.getConnection(DBURL);

			String query = "select *from student_1 where email=? and password=?";
			ps = con.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, pswd);
			rs = ps.executeQuery();
			if (rs.next()) {
				Student s = new Student();
				int id = rs.getInt(1);
				String name = rs.getString(2);
				double marks = rs.getDouble(3);
				String mail = rs.getString(4);
				String password = rs.getString(5);

				s.setId(id);
				s.setName(name);
				s.setMarks(marks);
				s.setEmailId(mail);
				s.setPassword(password);

				return s;
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public ArrayList<Student> getAllStudents() {
		ArrayList<Student> allStudentsList = new ArrayList<>();

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVERCLASSS);
			con = DriverManager.getConnection(DBURL);

			String query = "select *from student_1";

			st = con.createStatement();
			rs = st.executeQuery(query);

			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				double marsk = rs.getDouble(3);
				String email = rs.getString(4);
				String password = rs.getString(5);

				Student s = new Student();
				// Set
				s.setId(id);
				s.setName(name);
				s.setMarks(marsk);
				s.setEmailId(email);
				s.setPassword(password);

				allStudentsList.add(s);

			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (st != null)
					st.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return allStudentsList;

	}

	public Student searchStudent(int sid) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVERCLASSS);
			con = DriverManager.getConnection(DBURL);

			String query = "select *from student_1 where sid=? ";
			ps = con.prepareStatement(query);
			ps.setInt(1, sid);
			rs = ps.executeQuery();
			if (rs.next()) {
				Student s = new Student();
				int id = rs.getInt(1);
				String name = rs.getString(2);
				double marks = rs.getDouble(3);
				String mail = rs.getString(4);
				String password = rs.getString(5);

				s.setId(id);
				s.setName(name);
				s.setMarks(marks);
				s.setEmailId(mail);
				s.setPassword(password);

				return s;
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public ArrayList<Student> getDisplayBasedonMarks(int lower, int higher) {
		ArrayList<Student> displayBasedonMarks = new ArrayList<>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVERCLASSS);
			con = DriverManager.getConnection(DBURL);

			String query = "select *from student where marks between ? and ?";

			ps = con.prepareStatement(query);
			ps.setInt(1, lower);
			ps.setInt(2, higher);
			rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				double marsk = rs.getDouble(3);
				String email = rs.getString(4);
				String password = rs.getString(5);

				Student s = new Student();
				// Set
				s.setId(id);
				s.setName(name);
				s.setMarks(marsk);
				s.setEmailId(email);
				s.setPassword(password);

				displayBasedonMarks.add(s);

			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return displayBasedonMarks;
	}

	public boolean updateStudentdetails(Student stu) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			Class.forName(DRIVERCLASSS);
			con = DriverManager.getConnection(DBURL);

			String query = "update student_1 set name=?, marks=?,email=? where sid=?";

			ps = con.prepareStatement(query);
			ps.setString(1, stu.getName());
			ps.setDouble(2, stu.getMarks());
			ps.setString(3, stu.getEmailId());
			ps.setInt(4, stu.getId());

			int rowsAffected=ps.executeUpdate();
			return rowsAffected!=0;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

		public boolean deleteStudent(int deleteId) {
				Connection con = null;
				PreparedStatement ps = null;
			try {
				 			Class.forName(DRIVERCLASSS);
			con = DriverManager.getConnection(DBURL);
			String query = "delete from student_1 where sid=?";
			 
			ps = con.prepareStatement(query);
			ps.setInt(1, deleteId);
			int rowsAffected=ps.executeUpdate();
			return rowsAffected!=0;
			 }	catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			
			 finally {
					try {
						if (con != null)
							con.close();
						if (ps != null)
							ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			return false;
			}

		public boolean updatePassword(String newPassword, String email_id) {
			Connection con = null;
			PreparedStatement ps = null;
		try {
			 			Class.forName(DRIVERCLASSS);
		con = DriverManager.getConnection(DBURL);
		String query = "update student_1 set password=? where email=?";
		 
		ps = con.prepareStatement(query);
		ps.setString(1, newPassword);
		ps.setString(2, email_id);
		int rowsAffected=ps.executeUpdate();
		return rowsAffected!=0;
		 }	catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		 finally {
				try {
					if (con != null)
						con.close();
					if (ps != null)
						ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return false;
		}
			
}
