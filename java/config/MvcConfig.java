package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer{
	//핸들러맵핑 - 컨트롤러 찾는 역할 , 핸들로 맵핑이 동작할 수 있도록 한다.
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp().prefix("/WEB-INF/view/"); // ~form.jsp에서 뒤에 .jsp를 붙여지주지 않아도 된다. 왜? .jsp() 덕분에, 앞에 디렉토리도 안붙여줘도 된다. prefix() 덕분에
	}
}
