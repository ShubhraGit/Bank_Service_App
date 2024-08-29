package com.hs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.hs.model.EmailDetails;
import com.hs.model.Transaction;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String senderEmail;

	@Override
	public void sendEmailAlert(EmailDetails emailDetails) {
		// TODO Auto-generated method stub

		try {

			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setFrom(senderEmail);
			mailMessage.setTo(emailDetails.getRecipient());
			mailMessage.setText(emailDetails.getMessageBody());
			mailMessage.setSubject(emailDetails.getSubject());

			javaMailSender.send(mailMessage);
			System.out.println(" Mail Sent Successfully");

		} catch (MailException e) {
			throw new RuntimeException(e);
		}
	}
	
    @Override
    public void sendTransactionHistoryEmail(String recipientEmail, List<Transaction> transactionList) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(recipientEmail);
            helper.setSubject("Transaction History");
        	System.out.println("Transaction History");

            // Create an HTML table for the transaction history
            StringBuilder transactionHistoryHtml = new StringBuilder();
            transactionHistoryHtml.append("<html><head><style>");
            transactionHistoryHtml.append("body { font-family: Arial, sans-serif; }");
            transactionHistoryHtml.append("h2 { text-align: center; }");
            transactionHistoryHtml.append("table { border-collapse: collapse; width: 100%; border: 1px solid #ddd; }");
            transactionHistoryHtml.append("th, td { text-align: left; padding: 8px; }");
            transactionHistoryHtml.append("th { background-color: #f2f2f2; }");
            transactionHistoryHtml.append("tr:nth-child(even) { background-color: #f2f2f2; }");
            transactionHistoryHtml.append("</style></head><body>");
            transactionHistoryHtml.append("<h2>Transaction History</h2>");
            transactionHistoryHtml.append("<table>");
            transactionHistoryHtml.append("<tr><th>Date</th><th>Amount</th><th>Description</th><th>Trans Type</th></tr>");

            for (Transaction history : transactionList) {
                transactionHistoryHtml.append("<tr>");
                transactionHistoryHtml.append("<td>").append(history.getCreatedAt()).append("</td>");
                transactionHistoryHtml.append("<td>").append(history.getAmount()).append("</td>");
                transactionHistoryHtml.append("<td>").append(history.getStatus()).append("</td>");
                transactionHistoryHtml.append("<td>").append(history.getTransactionType()).append("</td>");
              
                transactionHistoryHtml.append("</tr>");
            }

            transactionHistoryHtml.append("</table></body></html>");

            // Set the HTML content as the email body
            helper.setText(transactionHistoryHtml.toString(), true);
            System.out.println("Sending Transaction History");
            javaMailSender.send(message);
            System.out.println("send Transaction History");
        } catch (MessagingException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }

}
