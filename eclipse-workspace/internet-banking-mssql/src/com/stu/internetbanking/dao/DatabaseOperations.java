package com.stu.internetbanking.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.stu.internetbanking.dto.Bank;
import com.stu.internetbanking.dto.BankPassbook;
import com.stu.internetbanking.dto.Bank_transfer;

public class DatabaseOperations {
	
	final public static String DRIVER_CLASS="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	final public static String DB_URL="jdbc:sqlserver://localhost:1433;encrypt=true;databaseName=Krishna_Testing;"
			+ "user=sa;password=15848;trustServerCertificate=true";
	public static int account_number=23000;
	public static int sno=0;
	public static int id=0;
	
	public int accountNumberGenerate() {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		
		try {
			Class.forName(DRIVER_CLASS);
			con=DriverManager.getConnection(DB_URL);
			String query="select isnull(max(ac_number),0) from bank";
			st=con.createStatement();
			rs=st.executeQuery(query);
			
			if(rs.next()) {
				account_number=rs.getInt(1);
				return account_number;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(st!=null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(rs!=null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		return account_number;
		
	}
	
	public boolean insertaccountdetails(Bank bank) {
		Connection con=null;
		PreparedStatement ps=null;
		
		try {
			Class.forName(DRIVER_CLASS);
			con=DriverManager.getConnection(DB_URL);
			
			String query="insert into bank values(?,?,?,?,?,?,?,?,?) ";
			
			DateFormat dt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			int temp_accno=accountNumberGenerate()+1;
			bank.setAc_number(temp_accno);
			bank.setCreated_date(dt.format(date));
			
			ps=con.prepareStatement(query);
			
			ps.setInt(1,bank.getAc_number());
			ps.setString(2, bank.getUsername());
			ps.setString(3, bank.getPassword());
			ps.setDouble(4, bank.getAmount());
			ps.setString(5, bank.getEmail());
			ps.setLong(6, bank.getPhone());
			ps.setString(7, bank.getCreated_date());
			ps.setString(8, bank.getUpdated_date());
			ps.setInt(9, bank.getOtp());
			
			int rowAffected=ps.executeUpdate();
			
			return rowAffected!=0;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		return false;
	}

	public Bank validateLogin(int ac_no, String pass) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			Class.forName(DRIVER_CLASS);
			con=DriverManager.getConnection(DB_URL);
			
			String query="select *from bank where ac_number=? and password=?";
			
			ps=con.prepareStatement(query);
			ps.setInt(1, ac_no);
			ps.setString(2, pass);
			
			rs=ps.executeQuery();
			if(rs.next()) {
				Bank b=new Bank();
				int ac_number =rs.getInt(1);
				String username=rs.getString(2);
				String password=rs.getString(3);
				double amount=rs.getDouble(4);
				String email=rs.getString(5);
				long phone=rs.getLong(6);
				String created_date=rs.getString(7);
				String updated_date=rs.getString(8);
				
				b.setAc_number(ac_number);
				b.setUsername(username);
				b.setPassword(password);
				b.setAmount(amount);
				b.setEmail(email);
				b.setPhone(phone);
				b.setCreated_date(created_date);
				b.setUpdated_date(updated_date);
				return b;
				
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(rs!=null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}		
		return null;
	}

	public boolean deposit(int ac_number, double amount, double d_amount) {
		Connection con=null;
		PreparedStatement ps=null;
		try {
			Class.forName(DRIVER_CLASS);
			con=DriverManager.getConnection(DB_URL);
			double final_amount=amount+d_amount;
			String query="update bank set amount=? where ac_number=?";
			ps=con.prepareStatement(query);
			ps.setDouble(1, final_amount);
			ps.setInt(2, ac_number);
			
			int rowsAffected=ps.executeUpdate();
			
			return rowsAffected!=0;
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return false;
	}

	public boolean withdraw(int ac_number, double amount, double w_amount) {
		Connection con=null;
		PreparedStatement ps=null;
		try {
			Class.forName(DRIVER_CLASS);
			con=DriverManager.getConnection(DB_URL);
			double final_amount=amount-w_amount;
			String query="update bank set amount=? where ac_number=?";
			ps=con.prepareStatement(query);
			ps.setDouble(1, final_amount);
			ps.setInt(2, ac_number);
			
			int rowsAffected=ps.executeUpdate();
			
			return rowsAffected!=0;
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return false;
	}
	
	public int snoGenerate() {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		
		try {
			Class.forName(DRIVER_CLASS);
			con=DriverManager.getConnection(DB_URL);
			String query="select isnull(max(sno),0) from bank_passbook";
			st=con.createStatement();
			rs=st.executeQuery(query);
			
			if(rs.next()) {
				sno=rs.getInt(1);
				return sno;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(st!=null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(rs!=null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		return sno;
		
	}
		
	public boolean transactionHistory(BankPassbook obj) {
		Connection con=null;
		PreparedStatement ps=null;
		
		try {
			Class.forName(DRIVER_CLASS);
			con=DriverManager.getConnection(DB_URL);
			
			String query="insert into bank_passbook values (?,?,?,?,?,?) ";
			
			DateFormat dt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			
			int temp_sno=snoGenerate()+1;
			obj.setSno(temp_sno);
			obj.setT_date(dt.format(date));
			
			ps=con.prepareStatement(query);
			ps.setInt(1, temp_sno);
			ps.setInt(2, obj.getAc_number());
			ps.setString(3, obj.getT_date());
			ps.setString(4, obj.getType());
			ps.setDouble(5, obj.getAmount());
			ps.setDouble(6, obj.getAvail_bal());
			
			int rowAffected=ps.executeUpdate();
			
			return rowAffected!=0;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return false;
	}

	public ArrayList<BankPassbook> getPassbook(int ac_number) {
		
		ArrayList<BankPassbook> getpassbook=new ArrayList<>();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs =null;
		try {
			Class.forName(DRIVER_CLASS);
			con=DriverManager.getConnection(DB_URL);
			String query="select *from bank_passbook where ac_number=? ";
			ps=con.prepareStatement(query);
			ps.setInt(1, ac_number);
			rs=ps.executeQuery();
			while(rs.next()) {
				BankPassbook b=new BankPassbook();
				int sno=rs.getInt(1);
				int ac_no=rs.getInt(2);
				String t_date=rs.getString(3);
				String type=rs.getString(4);
				double amount=rs.getDouble(5);
				double avail_bal=rs.getDouble(6);
				b.setSno(sno);
				b.setAc_number(ac_no);
				b.setT_date(t_date);
				b.setType(type);
				b.setAmount(amount);
				b.setAvail_bal(avail_bal);
				
				getpassbook.add(b);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		}
		
		return getpassbook;
	}

	public boolean closeAccount(int ac_number) {
		Connection con=null;
		PreparedStatement ps=null;
		try {
			Class.forName(DRIVER_CLASS);
			con=DriverManager.getConnection(DB_URL);
			String query="delete from bank where ac_number=?";
			ps=con.prepareStatement(query);
			ps.setInt(1, ac_number);
			int rowAffected=ps.executeUpdate();
			
			return rowAffected!=0;
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return false;
	}

	public int idGenerate() {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		
		try {
			Class.forName(DRIVER_CLASS);
			con=DriverManager.getConnection(DB_URL);
			String query="select isnull(max(id),0) from payment_sent";
			st=con.createStatement();
			rs=st.executeQuery(query);
			
			if(rs.next()) {
				id=rs.getInt(1);
				return id;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(st!=null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(rs!=null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		return id;
		
	}
	public void tranferentry(Bank_transfer sent) {
		Connection con=null;
		PreparedStatement ps=null;
		try {
			Class.forName(DRIVER_CLASS);
			con=DriverManager.getConnection(DB_URL);
			String query="insert into payment_sent values(?,?,?,?,?,?,?)";
			
			DateFormat dt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
	
			sent.setId(idGenerate()+1);
			sent.setSent_date(dt.format(date));
			
			ps=con.prepareStatement(query);
			ps.setInt(1,sent.getId());
			ps.setInt(2, sent.getAc_number());
			ps.setInt(3, sent.getTransfer_acno());
			ps.setString(4, sent.getIfsc());
			ps.setString(5, sent.getSent_date());
			ps.setDouble(6, sent.getAmount());
			ps.setString(7, sent.getStatus());
			int rowAffected=ps.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	public boolean updateAccountDetails(Bank login) {
		Connection con=null;
		PreparedStatement ps=null;
		try {
			Class.forName(DRIVER_CLASS);
			con=DriverManager.getConnection(DB_URL);
			
			DateFormat dt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
	
			login.setUpdated_date(dt.format(date));
			String query="update bank set username=?,email=?,phone=?,updated_date=? where ac_number=?";
			ps=con.prepareStatement(query);
			ps.setString(1, login.getUsername());
			ps.setString(2, login.getEmail());
			ps.setLong(3, login.getPhone());
			ps.setString(4, login.getUpdated_date());
			ps.setInt(5, login.getAc_number());
			
			int rowsAffected=ps.executeUpdate();
			
			return rowsAffected!=0;
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	public void forgotpassword(String email, int acc_no) {
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs = null;
		try {
			Class.forName(DRIVER_CLASS);
			con=DriverManager.getConnection(DB_URL);
			cs=con.prepareCall("{call send_test_email(?,?)}");
			cs.setString(1,email);
			cs.setInt(2, acc_no);
			cs.execute();
			System.out.println("Email sent successfully...");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(cs!=null) {
				try {
					cs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public Bank getAccountDetails(int ac_no) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			Class.forName(DRIVER_CLASS);
			con=DriverManager.getConnection(DB_URL);
			
			String query="select *from bank where ac_number=?";
			
			ps=con.prepareStatement(query);
			ps.setInt(1, ac_no);
			
			rs=ps.executeQuery();
			if(rs.next()) {
				Bank b=new Bank();
				int ac_number =rs.getInt(1);
				String username=rs.getString(2);
				String password=rs.getString(3);
				double amount=rs.getDouble(4);
				String email=rs.getString(5);
				long phone=rs.getLong(6);
				String created_date=rs.getString(7);
				String updated_date=rs.getString(8);
				int otp=rs.getInt(9);
				
				b.setAc_number(ac_number);
				b.setUsername(username);
				b.setPassword(password);
				b.setAmount(amount);
				b.setEmail(email);
				b.setPhone(phone);
				b.setCreated_date(created_date);
				b.setUpdated_date(updated_date);
				b.setOtp(otp);
				return b;
				
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(rs!=null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}		
		return null;
	}

	public boolean updatepassword(int acc_no, String u_password) {
		Connection con=null;
		PreparedStatement ps=null;
		try {
			Class.forName(DRIVER_CLASS);
			con=DriverManager.getConnection(DB_URL);
			DateFormat dt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			
			String query="update bank set password=?, updated_date=? where ac_number=?";
			ps=con.prepareStatement(query);
			ps.setString(1, u_password);
			ps.setString(2, dt.format(date));
			ps.setInt(3, acc_no);
			int rowsAffected=ps.executeUpdate();
			
			return rowsAffected!=0;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}		
		return false;
	}
}
