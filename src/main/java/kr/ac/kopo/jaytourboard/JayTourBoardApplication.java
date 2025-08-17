package kr.ac.kopo.jaytourboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


//@EnableJpaAuditing 어노테이션은 Spring Data JPA에서 제공하는 Auditing 기능을 활성화하기 위한 설정입니다.
//이 기능을 활성화하면, 엔티티가 생성되거나 수정될 때 자동으로 특정 필드(예: 생성 시간, 수정 시간 등)를 관리할 수 있습니다.
@SpringBootApplication
@EnableJpaAuditing
public class JayTourBoardApplication {

    public static void main(String[] args) {

        SpringApplication.run(JayTourBoardApplication.class, args);
    }

}
