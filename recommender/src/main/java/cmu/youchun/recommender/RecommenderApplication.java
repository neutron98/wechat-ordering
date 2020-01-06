package cmu.youchun.recommender;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(scanBasePackages = {"cmu.youchun.recommender"})
@MapperScan("cmu.youchun.recommender.dao")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class RecommenderApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecommenderApplication.class, args);
	}

}
