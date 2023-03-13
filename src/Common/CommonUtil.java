package Common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Scanner;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


public class CommonUtil {
    public static Scanner scan = new Scanner(System.in);
    public static String filePath = "C:\\Java\\healthcare_billing_system\\healthcare_billing_system\\bills\\";
    
    //convert text to pdf
    public static boolean convertTextToPDF(File file) throws Exception {

        BufferedReader br = null;
    
        try {
    
            Document pdfDoc = new Document(PageSize.A4);
            String output_file = file.getName().replace(".txt", ".pdf");
            output_file = filePath+output_file;
            PdfWriter.getInstance(pdfDoc, new FileOutputStream(output_file)).setPdfVersion(PdfWriter.VERSION_1_7);
    
            pdfDoc.open();
    
            Font myfont = new Font();
            Font fontTitle = new Font();

            fontTitle.setStyle(Font.BOLD);
            fontTitle.setColor(BaseColor.BLUE);
            fontTitle.setSize(16);

            myfont.setStyle(Font.NORMAL);
            myfont.setSize(11);
            //myfont.setColor(BaseColor.GRAY);
    
            pdfDoc.add(new Paragraph("\n"));
            Paragraph hospitalName = new Paragraph("RIA CARES" + "\n", fontTitle);
            hospitalName.setAlignment(Element.ALIGN_CENTER);
                    pdfDoc.add(hospitalName);
    
            if (file.exists()) {
    
                br = new BufferedReader(new FileReader(file));
                String strLine;
                int lineCount = 1;
                while ((strLine = br.readLine()) != null) {
                    if(lineCount==6 || lineCount==12)
                    {
                        myfont.setStyle(Font.BOLD);
                    }
                    else
                    {
                        myfont.setStyle(Font.NORMAL);;
                    }
                    Paragraph para = new Paragraph(strLine + "\n", myfont);
                    para.setAlignment(Element.ALIGN_CENTER);
                    pdfDoc.add(para);
                    lineCount++;
                }

                myfont.setColor(BaseColor.BLUE);
                myfont.setStyle(Font.ITALIC);
                myfont.setSize(9);
                Paragraph tagLine = new Paragraph("Stay Healthy, Stay Safe" + "\n", myfont);
                tagLine.setAlignment(Element.ALIGN_CENTER);
                pdfDoc.add(tagLine);

            } else {
                System.out.println("no such file exists!");
                return false;
            }
            pdfDoc.close();
        }
    
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) 
                br.close();
        }
    
        return true;
    }
}


