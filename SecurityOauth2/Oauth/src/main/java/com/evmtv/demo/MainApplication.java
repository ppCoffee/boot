package com.evmtv.demo;




import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;




@SpringBootApplication()
//@ComponentScan("com.evmtv.boot")
public class MainApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);
		
		
//		ConfigEntity entity1 = run.getBean("ConfigEntity", ConfigEntity.class);
//		ConfigEntity entity2 = run.getBean("ConfigEntity", ConfigEntity.class);
//		
//		
//		String str = "";
//		System.out.println(StringUtils.hasText(str));
//		str = null;
//		System.out.println(StringUtils.hasText(str));
//		
//		
//		System.out.println(entity1 == entity2);
//		System.out.println(entity1);

	}
	
}
