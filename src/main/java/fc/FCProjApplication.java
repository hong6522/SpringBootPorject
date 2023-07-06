package fc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("fc.db")
public class FCProjApplication {

	public static void main(String[] args) {
		SpringApplication.run(FCProjApplication.class, args);
	}

}
