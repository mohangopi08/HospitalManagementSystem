package Hospital.ManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Doctor {
	private Connection connection;
	
     public Doctor(Connection connection) {
		this.connection=connection;

	}
	
	
	public void viewDoctor() {
		try {
			String value = "select * from doctor";
			PreparedStatement ps = connection.prepareStatement(value);
			ResultSet rs = ps.executeQuery();
			System.out.println("The Doctor are:");
			while(rs.next()) {
				int id = rs.getInt("d_id");
				String name = rs.getString("d_name");
				String special = rs.getString("specialization");
				System.out.println("The Doctor id is"+ " "+id);
				System.out.println("The Doctor name is"+ " "+name);
				System.out.println("The Doctor Speciallization is"+ " "+ special);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public boolean getDoctorById(int id) {
		String value ="select * from doctor where d_id=?";
		try {
			PreparedStatement ps = connection.prepareStatement(value);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
			else {
				return false;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
		
}
