package Hospital.ManagementSystem;
import java.sql.*;
import java.util.*;
public class Hospital {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
     try {
		Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "Mohan");
         Scanner sc = new Scanner(System.in);
		 Patient patient = new Patient(sc, con);
		 Doctor doctor = new Doctor(con);
		 while(true) {
			 System.out.println("HOSPITAL MANAGEMENT SYSTEM");
			 System.out.println("1. Add Patient");
			 System.out.println("2. View Patient");
			 System.out.println("3. View Doctors");
			 System.out.println("4.Book Appiontment");
			 System.out.println("5.Discharge Patient");
			 System.out.println("6.Exit");
			 System.out.println("Enter your choice:");
			 int choice = sc.nextInt();
			 switch(choice) {
			 case 1:
				 patient.addPatient();
				 System.out.println();
				 break;
			 case 2:
				 patient.viewPatient();
				 System.out.println();
				 break;
			 case 3:
				 doctor.viewDoctor();
				 System.out.println();
				 break;
			 case 4:
				 bookAppointment(patient, con, sc, doctor);
				 break;
			 case 5:
				 patient.deletePatient();
				 System.out.println();
				 break;
			 case 6:
				 System.out.println("THANK YOU!FOR USING HOSPITAL MANAGEMENT SYSTEM!!!");
				 return;
				 default:
					 System.out.println("Enter the valid Choice!!!");
				}
		 }
		 
	} catch (Exception e) {
		// TODO: handle exception
	}
	}
public static void bookAppointment(Patient patient,Connection connection ,Scanner scanner,Doctor doctor) {
	System.out.println("Enter the patient id: ");
	int pi= scanner.nextInt();
	System.out.println("Enter the Doctor id: ");
	int di =scanner.nextInt();
	System.out.println("Enter the Appointment date(YYYY-MM-DD):");
	String date = scanner.next();
	if(patient.getPatientById(pi)&& (doctor.getDoctorById(di))){
		if(checkDoctorAvailability(di,date,connection)) {
			String appointment ="Insert into appointment(patient_id ,doctor_id,appointment_date) values(?,?,?)";
			try {
				PreparedStatement ps = connection.prepareStatement(appointment);
				ps.setInt(1, pi);
				ps.setInt(2, di);
				ps.setString(3, date);
		        int rs=ps.executeUpdate();
		        if(rs>0){
		        	System.out.println("Appointment Booked");
		        }
		        else {
		        	System.out.println("Fail to book Appointment");
		        }
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		else {
			System.out.println("Doctor not available on this date");
		}
	}
	else {
		System.out.println("Either Doctor or Patient doesn't exist!!");
	}
}
public static  boolean checkDoctorAvailability(int di,String date,Connection connection) {
   String query ="Select count(*) from appointment where doctor_id =? and appointment_date=?";
   try {
	PreparedStatement ps = connection.prepareStatement(query);
	ps.setInt(1, di);
	ps.setString(2, date);
	ResultSet rs = ps.executeQuery();
	if(rs.next()) {
		int count = rs.getInt(1);
		if(count ==0) {
			return true;
		}
		else {
			return false;
		}
	}
} catch (Exception e) {
	// TODO: handle exception
}
   return false;
}

}
