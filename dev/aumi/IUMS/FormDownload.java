package dev.aumi.IUMS;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

public class FormDownload {

	@SuppressWarnings("deprecation")
	public FormDownload(String id1, String name1, int sem1, String section1) {

		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "iums";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "";
		Connection conn = null;
		Statement stmt = null;

		String[][] arr = new String[100][6];
		int row = 0;

		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName, userName, password);
			System.out.println("Connected to the database....." + dbName);

			stmt = conn.createStatement();
			String query1 = "SELECT * from cse_lab_faculty_map inner join cse_lab on cse_lab_faculty_map.Course_No = cse_lab.Course_No"
					+ " inner join cse_teachers on cse_lab_faculty_map.Teacher_ID = cse_teachers.Teacher_ID";
			ResultSet rs1 = stmt.executeQuery(query1);

			while (rs1.next()) {

				String[] breakCourseNo = rs1.getString("Course_no").split(" ");
				String section = breakCourseNo[2];

				int semester = Integer.parseInt(breakCourseNo[1]) / 100;

				if (section.equals(section1) && semester <= sem1) {

					arr[row][0] = Integer.toString(1 + row);
					arr[row][1] = rs1.getString("Course_title");
					System.out.println(arr[row][1]);
					arr[row][2] = rs1.getString("Teacher_name");
					System.out.println(arr[row][2]);
					arr[row][3] = rs1.getString("Room_no");
					System.out.println(arr[row][3]);
					arr[row][4] = "None";
					System.out.println(arr[row][4]);
					arr[row][5] = rs1.getString("Teacher_name");
					System.out.println(arr[row][5]);
					row++;

				}

			}

			rs1.close();
			stmt = conn.createStatement();
			String query2 = "SELECT * from cse_lab_faculty_map inner join cse_lab on cse_lab_faculty_map.Course_No = cse_lab.Course_No"
					+ " inner join eee_teachers on cse_lab_faculty_map.Teacher_ID = eee_teachers.Teacher_ID";
			ResultSet rs2 = stmt.executeQuery(query2);

			while (rs2.next()) {

				String[] breakCourseNo = rs2.getString("Course_no").split(" ");
				String section = breakCourseNo[2];

				int semester = Integer.parseInt(breakCourseNo[1]) / 100;

				if (section.equals(section1) && semester <= sem1) {

					arr[row][0] = Integer.toString(1 + row);
					arr[row][1] = rs2.getString("Course_title");
					System.out.println(arr[row][1]);
					arr[row][2] = rs2.getString("Teacher_name");
					System.out.println(arr[row][2]);
					arr[row][3] = rs2.getString("Room_no");
					System.out.println(arr[row][3]);
					arr[row][4] = "None";
					System.out.println(arr[row][4]);
					arr[row][5] = rs2.getString("Teacher_name");
					System.out.println(arr[row][5]);
					row++;

				}

			}

			rs2.close();

			stmt = conn.createStatement();
			String query3 = "SELECT * from cse_lab_faculty_map inner join cse_lab on cse_lab_faculty_map.Course_No = cse_lab.Course_No"
					+ " inner join me_teachers on cse_lab_faculty_map.Teacher_ID = me_teachers.Teacher_ID";
			ResultSet rs3 = stmt.executeQuery(query3);

			while (rs3.next()) {

				String[] breakCourseNo = rs3.getString("Course_no").split(" ");
				String section = breakCourseNo[2];

				int semester = Integer.parseInt(breakCourseNo[1]) / 100;

				if (section.equals(section1) && semester <= sem1) {

					arr[row][0] = Integer.toString(1 + row);
					arr[row][1] = rs3.getString("Course_title");
					System.out.println(arr[row][1]);
					arr[row][2] = rs3.getString("Teacher_name");
					System.out.println(arr[row][2]);
					arr[row][3] = rs3.getString("Room_no");
					System.out.println(arr[row][3]);
					arr[row][4] = "None";
					System.out.println(arr[row][4]);
					arr[row][5] = rs3.getString("Teacher_name");
					System.out.println(arr[row][5]);
					row++;

				}

			}

			rs3.close();

			stmt = conn.createStatement();
			String query4 = "SELECT * from cse_lab_faculty_map inner join cse_lab on cse_lab_faculty_map.Course_No = cse_lab.Course_No"
					+ " inner join arts_science_teachers on cse_lab_faculty_map.Teacher_ID = arts_science_teachers.Teacher_ID";
			ResultSet rs4 = stmt.executeQuery(query4);

			while (rs4.next()) {

				String[] breakCourseNo = rs4.getString("Course_no").split(" ");
				String section = breakCourseNo[2];

				int semester = Integer.parseInt(breakCourseNo[1]) / 100;

				if (section.equals(section1) && semester <= sem1) {

					arr[row][0] = Integer.toString(1 + row);
					arr[row][1] = rs4.getString("Course_title");
					System.out.println(arr[row][1]);
					arr[row][2] = rs4.getString("Teacher_name");
					System.out.println(arr[row][2]);
					arr[row][3] = rs4.getString("Room_no");
					System.out.println(arr[row][3]);
					arr[row][4] = "None";
					System.out.println(arr[row][4]);
					arr[row][5] = rs4.getString("Teacher_name");
					System.out.println(arr[row][5]);
					row++;

				}

			}

			rs4.close();

			stmt = conn.createStatement();
			String query5 = "SELECT * FROM punishment_table inner join cse_lab on punishment_table.Course_no = cse_lab.Course_No";
					/*"Select * FROM punishment_table inner join cse_students on punishment_table.ID = cse_students.ID "
					+ "inner join  cse_lab on punishment_table.Course_No = cse_lab.Course_No";*/
			ResultSet rs5 = stmt.executeQuery(query5);
			int row1 = 0;
            while (rs5.next()) {

                if (rs5.getString("ID").equals(id1)) {
                    while (true){
                        
                        if(arr[row1][1].equals(rs5.getString("Course_title"))) {
                            arr[row1][4] = rs5.getString("remarks");
                            System.out.println();
                            arr[row1][5] = "";
                            break;
                        } else row1++;


                    }

                }

            }

			rs5.close();

		} catch (Exception e) {
			e.printStackTrace();

		}

		try {
			String dest = "E:\\programs\\java\\IUMS\\PDF" + id1 + " Clearance Form.pdf";
			PdfWriter writer = new PdfWriter(dest);

			PdfDocument pdf = new PdfDocument(writer);

			pdf.addNewPage();
			Document doc = new Document(pdf);
			String img1 = "E:\\programs\\java\\IUMS\\icon\\AUST.jpg";
			ImageData data1 = ImageDataFactory.create(img1);
			Image image1 = new Image(data1);

			image1.setFixedPosition(20, 750);
			image1.scaleToFit(50, 100);
			doc.add(image1);

			String img2 = "E:\\programs\\java\\IUMS\\icon\\logo.png";
			ImageData data2 = ImageDataFactory.create(img2);
			Image image2 = new Image(data2);

			image2.setFixedPosition(520, 750);
			image2.scaleToFit(75, 200);
			doc.add(image2);

			Text t = new Text("");
			Paragraph tp = new Paragraph(t);
			doc.add(tp);
			Text text1 = new Text("Ahsanullah University of Science and Technology");
			PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
			text1.setFont(font);
			text1.setFontSize(18);
			Paragraph p1 = new Paragraph(text1);
			p1.setTextAlignment(TextAlignment.CENTER);
			doc.add(p1);
			String sID1 = id1;

			if (sID1.charAt(7) == '4') {
				Text dep = new Text("Department of Computer Science and Engineering (CSE)");
				dep.setFont(font);
				dep.setFontSize(14);
				Paragraph p = new Paragraph(dep);
				p.setTextAlignment(TextAlignment.CENTER);
				doc.add(p);
			}
			if (sID1.charAt(7) == '5') {
				Text dep = new Text("Department of Elctrical and Electronic Engineering (EEE)");
				dep.setFont(font);
				dep.setFontSize(14);
				Paragraph p = new Paragraph(dep);
				p.setTextAlignment(TextAlignment.CENTER);
				doc.add(p);
			}
			Text text2 = new Text("Clearance Form");
			text2.setFont(font);
			text2.setFontSize(14);
			Paragraph p2 = new Paragraph(text2);
			p2.setTextAlignment(TextAlignment.CENTER);
			doc.add(p2);

			Text text3 = new Text("Student Name : " + name1);
			Paragraph p3 = new Paragraph(text3).setFont(PdfFontFactory.createFont(FontConstants.HELVETICA_OBLIQUE));
			// p3.setTextRenderingMode(Text)
			doc.add(p3);

			Text text4 = new Text("ID :                    " + id1 + "                       "
					+ "Section :                                              " +"  "+ section1 + "Semester : "+"  "+sem1);
			Paragraph p4 = new Paragraph(text4).setFont(PdfFontFactory.createFont(FontConstants.HELVETICA_OBLIQUE));
			doc.add(p4);

			Text blank = new Text("");
			Paragraph blankP = new Paragraph(blank);
			doc.add(blankP);

			float[] pointColumnWidths = { 35F, 220F, 100F, 50F, 55F, 60F };
			Table table = new Table(pointColumnWidths);

			// Adding cells to the table
			Cell c1 = new Cell();
			c1.add("NO");
			c1.setTextAlignment(TextAlignment.CENTER);
			table.addCell(c1);

			Cell c2 = new Cell();
			c2.add("Course Name");
			c2.setTextAlignment(TextAlignment.CENTER);
			table.addCell(c2);

			Cell c3 = new Cell();
			c3.add("Teacher Name");
			c3.setTextAlignment(TextAlignment.CENTER);
			table.addCell(c3);

			Cell c4 = new Cell();
			c4.add("Room NO");
			c4.setTextAlignment(TextAlignment.CENTER);
			table.addCell(c4);

			Cell c5 = new Cell();
			c5.add("Remarks");
			c5.setTextAlignment(TextAlignment.CENTER);
			table.addCell(c5);

			Cell c6 = new Cell();
			c6.add("Signature");
			c6.setTextAlignment(TextAlignment.CENTER);
			table.addCell(c6);

			int k = 0;
			while (k < row) {
				table.addCell(new Cell().add(String.valueOf(k + 1)));
				table.addCell(arr[k][1]);
				table.addCell(arr[k][2]);
				table.addCell(arr[k][3]);
				table.addCell(arr[k][4]);
				table.addCell(arr[k][5]);
				k++;
			}

			doc.add(table);

			Text text5 = new Text("_________________________");
			Paragraph p5 = new Paragraph(text5);
			p5.setFixedPosition(400, 60, 200);
			doc.add(p5);

			Text text6 = new Text("Head Of The Depertment");
			Paragraph p6 = new Paragraph(text6);
			p6.setFixedPosition(415, 40, 200);
			doc.add(p6);

			Text footer = new Text("Develpoed By SAM404");
			Paragraph f = new Paragraph(footer).setFont(PdfFontFactory.createFont(FontConstants.HELVETICA))
					.setFontSize(8);
			doc.showTextAligned(f, 595 / 2, 10, pdf.getNumberOfPages(), TextAlignment.CENTER, VerticalAlignment.BOTTOM,
					0);

			doc.close();
		} catch (

		FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
