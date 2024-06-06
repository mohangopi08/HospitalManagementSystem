package Hospital.ManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Patient {
	private Scanner scanner;
	private Connection connection;
	public Patient(Scanner scanner ,Connection connection) {
		this.connection=connection;
		this.scanner=scanner;
	}
	public void addPatient() {
		System.out.print("Enter the Patient name:  ");
		String name = scanner.next();
		System.out.print("Enter the Patient age:  ");
		int age =scanner.nextInt();
		System.out.print("Enter the patient Gender:  ");
		String gender = scanner.next();
		
		try {
			String value ="insert into patient(p_name,p_age,p_gender) values(?,?,?)" ;
			PreparedStatement ps =connection.prepareStatement(value);
			ps.setString(1,name);
			ps.setInt(2, age);
			ps.setString(3, gender);
			int ar = ps.executeUpdate();
			if(ar>0) {
				System.out.println("Patient details are added successfully!!");
			}
			else {
				System.out.println("Fail to add patient");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void viewPatient() {
		try {
			String value = "select * from patient";
			PreparedStatement ps = connection.prepareStatement(value);
			ResultSet rs = ps.executeQuery();
			System.out.println("The patients are:");
			while(rs.next()) {
				int id = rs.getInt("p_id");
				String name = rs.getString("p_name");
				int age = rs.getInt("p_age");
				String gender =rs.getString("p_gender");
				System.out.println("The patient id"+ " " +id);
				System.out.println("The patient name is"+ " " +name);
				System.out.println("The patient age is" +" " +age);
				System.out.println("The patient gender is" +" "+ gender);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public boolean getPatientById(int id) {
		String value ="select * from patient where p_id=?";
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
	
	public void deletePatient() {
		String value ="delete from patient where p_id=?";
		try {
			PreparedStatement ps = connection.prepareStatement(value);
		     System.out.println("Enter the patient ID to Discharge");
		      int id = scanner.nextInt();
		      ps.setInt(1, id);
		    int rs = ps.executeUpdate();
		      System.out.println("Discharge patient ID is"+ " "+rs);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
		

	}
