package fc.email;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public interface MailServiceInter {
	 MimeMessage creatMessage(String to) throws MessagingException, UnsupportedEncodingException;

	    // 랜덤 인증코드 생성
	    String createKey();
	    
	    // 메일 발송
	    String sendSimpleMessage(String to) throws Exception;
}
