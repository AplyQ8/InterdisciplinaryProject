package com.example.testservice.service;

import com.example.testservice.model.Answer;
import com.example.testservice.model.Question;
import com.example.testservice.model.Survey;
import com.example.testservice.model.User;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.List;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.FontSelector;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


// Сервис для создания теста в формате PDF
// использует технологию с открытым исходным кодом - openPDF
@Service
public class FileExporter {

    public static File fontFile = new File("tnr.ttf");

    public void setResponseHeader(HttpServletResponse response,String contentType,String ext,String prefix) throws Exception{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String timeStamp = dateFormat.format(new Date());
        String fileName = prefix+ timeStamp + ext;

        response.setContentType(contentType);

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename= " + fileName;
        response.setHeader(headerKey,headerValue);
    }

    public void exportToPDF(User user, Survey survey, HttpServletResponse response) throws Exception {
        setResponseHeader(response,"application/pdf",".pdf",survey.getTitle());

        Document document = new Document(PageSize.A4);
        document.addTitle(survey.getTitle());
        document.addHeader("Тест",survey.getTitle());


        PdfWriter.getInstance(document,response.getOutputStream());


        document.open();


        BaseFont unicode = BaseFont.createFont(fontFile.getAbsolutePath(),BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

        Font font = new Font(unicode);
        font.setSize(16);
        font.setColor(Color.BLACK);

        Font font2 = new Font(unicode);
        font2.setSize(14);
        font2.setColor(Color.BLACK);

        document.add(new Paragraph("Тест: " + survey.getTitle(),font));
        document.add(new Paragraph("Составитель: " + user.getUsername(),font));
        document.add(new Paragraph("\n\n"));
        for (Question question: survey.getQuestionList()) {
            document.add(new Paragraph(question.getQuestion(),font));
            List list = new List(List.ORDERED);
            for (Answer answer:question.getAnswers()){
                list.add(new ListItem(answer.getAnswer(),font2));
            }
            document.add(list);
        }

        document.close();
    }
}
