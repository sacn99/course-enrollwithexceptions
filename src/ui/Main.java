package ui;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import customExceptions.QuotaEnrollExceedException;
import model.Course;

public class Main {
	public static void main(String[] args) {
		Course myCourse;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Welcome to course management");
		
		System.out.print("Please enter the total grades amount along the semester: ");
		int totalGradesAmount = Integer.parseInt(sc.nextLine()); 
		
		System.out.print("Please enter the course enrollment capacity: ");
		int quota = Integer.parseInt(sc.nextLine());
		
		System.out.print("Please enter the minimum grade: ");
		double min = Double.parseDouble(sc.nextLine());
		
		System.out.print("Please enter the maximum grade: ");
		double max = Double.parseDouble(sc.nextLine());
		
		myCourse = new Course(totalGradesAmount, max, min, quota);
		
		int option;
		do {
			System.out.println("Menu");
			System.out.println("1. Enroll a student");
			System.out.println("2. Unenroll a student");
			System.out.println("3. Set grade to a student");
			System.out.println("4. Advace a week");
			System.out.println("5. Exit the program");
			System.out.print("Please choose an option: ");
			
			option = Integer.parseInt(sc.nextLine());
			
			String id;
			switch(option) {
				case 1:
					System.out.print("Please enter the new student id: ");
					id = sc.nextLine();
					try {
						myCourse.enroll(id);
						System.out.println("Student has been enrolled");
					} catch (QuotaEnrollExceedException e) {
						System.out.println("It was no possible to enroll the student with id "+id);
						System.out.println(e.getMessage());
					}
				break;
				case 2:
					System.out.println("Please enter the student id to unenroll: ");
					id = sc.nextLine();
					myCourse.cancelEnrollment(id); //does this method throw an exception? why compile with no problem?
					System.out.println("Student has been unenrolled");
				break;
				case 3:
					System.out.print("Please enter the student id to grade: ");
					id = sc.nextLine();
					System.out.print("Please enter the student grade: ");
					double g = Double.parseDouble(sc.nextLine());
					System.out.print("Please enter the grade number in the semester: ");
					int gradeNumber = Integer.parseInt(sc.nextLine());
					
					myCourse.setStudentGrade(id, gradeNumber, g);
					System.out.println("Student has been graded");
				break;
				case 4:
					try {
					System.out.print("The current week has advanced");
					myCourse.advanceWeek();
					System.out.println("The current week is: "+myCourse.getCurrentWeek());
					}
					catch(Exception e) {
						System.out.print("Write only number asshole");
					}
				break;
				case 5:
					System.out.print("Thank you. Bye!");
				break;
			}
			
		}while(option!=5);
		
		sc.close();
	}
}
