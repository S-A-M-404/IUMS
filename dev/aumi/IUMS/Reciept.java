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
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

public class Reciept {

	@SuppressWarnings("deprecation")
	public Reciept(String id1, String name1, int sem1, String section1) {
		// TODO Auto-generated constructor stub
		
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "iums";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "";
		Connection conn = null;
		Statement stmt = null;

		int totalFine = 0;

		try {
			String dest = "E:\\programs\\java\\IUMS\\PDF" + id1 + " Money Reciept.pdf";
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

			Text text2 = new Text("Money Reciept");
			text2.setFont(font);
			text2.setFontSize(14);
			Paragraph p2 = new Paragraph(text2);
			p2.setTextAlignment(TextAlignment.CENTER);
			doc.add(p2);

			Text text3 = new Text("Student Name : "+name1);
			Paragraph p3 = new Paragraph(text3).setFont(PdfFontFactory.createFont(FontConstants.HELVETICA_OBLIQUE));
			doc.add(p3);

			Text text4 = new Text("ID :            " + id1 + "                       " 
					+ "Section :              " +section1 + "                                       " + "Semester : " + sem1);
			Paragraph p4 = new Paragraph(text4).setFont(PdfFontFactory.createFont(FontConstants.HELVETICA_OBLIQUE));
			doc.add(p4);

			doc.add(new Paragraph(""));
			doc.add(new Paragraph(""));
			doc.add(new Paragraph(""));
			doc.add(new Paragraph(""));

			

			float[] pointColumnWidths = { 40F, 100F, 300F, 80F };
			Table table = new Table(pointColumnWidths);

			Cell c1 = new Cell();
			c1.add("NO");
			c1.setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER);
			table.addCell(c1);

			Cell c2 = new Cell();
			c2.add("Course No");
			c2.setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER);
			table.addCell(c2);

			Cell c3 = new Cell();
			c3.add("Course Name");
			c3.setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER);
			table.addCell(c3);

			Cell c4 = new Cell();
			c4.add("Fine Amount");
			c4.setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER);
			table.addCell(c4);

			try {
				Class.forName(driver).newInstance();
				conn = DriverManager.getConnection(url + dbName, userName, password);
				System.out.println("Connected to the database....." + dbName);

				stmt = conn.createStatement();
				String query = "SELECT * FROM punishment_table inner join cse_lab on punishment_table.Course_no = cse_lab.Course_No";
				ResultSet rs = stmt.executeQuery(query);

				int i = 1;
				while (rs.next()) {
					if (rs.getString("ID").equals(id1)) {
						table.addCell(new Cell().add(Integer.toString(i)).setTextAlignment(TextAlignment.CENTER)
								.setBorder(Border.NO_BORDER));
						table.addCell(new Cell().add(rs.getString("Course_no")).setTextAlignment(TextAlignment.CENTER)
								.setBorder(Border.NO_BORDER));
						table.addCell(new Cell().add(rs.getString("Course_title"))
								.setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
						table.addCell(new Cell().add(Integer.toString(rs.getInt("fine_amount")))
								.setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
						totalFine = totalFine + rs.getInt("fine_amount");
						i++;
					}
				}
				System.out.println(totalFine);

				// doc.add(list);
				doc.add(table);
				Text blank = new Text("___________________________________________________________________________");
				Paragraph blankP = new Paragraph(blank);
				doc.add(blankP);
				
				
				doc.add(new Paragraph("Fine Amount                                                                                                 "
						+ "                   =  " + Integer.toString(totalFine)));
				
				rs.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

			Text text5 = new Text("_________________________");
			Paragraph p5 = new Paragraph(text5);
			p5.setFixedPosition(400, 60, 200);
			doc.add(p5);

			Text text6 = new Text("Authorized Officer");
			Paragraph p6 = new Paragraph(text6);
			p6.setFixedPosition(435, 40, 200);
			doc.add(p6);

			Text footer = new Text("Powered By SAM404");
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
