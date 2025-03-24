package himedia.hpm_spring;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class GlobalCorsConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// CORS 정책 설정
		registry.addMapping("/api/**")
				// /api로 시작되는 모든 요청에 CORS 정책 부여
				.allowedOrigins("http://localhost:5173")
				.allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
				.allowedHeaders("*") // 모든 헤더 허용
				.maxAge(3600) // CORS 캐싱 허용 시간 (초단위)
				.allowCredentials(true);
	}
}
