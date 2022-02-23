package xyz.carjoy.question;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication
@ServletComponentScan
@EnableCaching
@EnableScheduling
@EnableTransactionManagement
public class QuestionApplication extends SpringBootServletInitializer{

	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(QuestionApplication.class);
    }

	/**
	 * 设置匹配*.action后缀请求
	 * @param dispatcherServlet
	 * @return
	 */
	@Bean
	public ServletRegistrationBean servletRegistrationBean(DispatcherServlet dispatcherServlet) {
		ServletRegistrationBean<DispatcherServlet> servletServletRegistrationBean = new ServletRegistrationBean<>(dispatcherServlet);
		servletServletRegistrationBean.addUrlMappings("*.action");
		return servletServletRegistrationBean;
	}

//	@KafkaListeners(value = {@KafkaListener(topics = {"topic01"})})
//	public void receive(ConsumerRecord<String, String> record){
//		System.out.println("接收到消息了"+record);
//	}

//	@KafkaListeners(value = {@KafkaListener(topics = {"topic02"})})
//	@SendTo("topicspringboot")
//	public String receive2(ConsumerRecord<String, String> record){
//		return record.value()+"springbootsend";
//	}
	
	public static void main(String[] args) {
		SpringApplication.run(QuestionApplication.class, args);
	}

}
