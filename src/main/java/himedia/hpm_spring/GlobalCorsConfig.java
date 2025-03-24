package himedia.hpm_spring;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class GlobalCorsConfig implements WebMvcConfigurer {

	  @Override
	   public void addCorsMappings(CorsRegistry registry) {
	       registry.addMapping("/**")
	               .allowedOrigins("http://localhost:5173")  // 프론트엔드 주소
	               .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
	               .allowedHeaders("*")
	               .maxAge(3600)
	               .allowCredentials(true);
	    }
}
