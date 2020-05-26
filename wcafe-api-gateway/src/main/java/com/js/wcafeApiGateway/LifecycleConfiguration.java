package com.js.wcafeApiGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Processing according to the lift cycle of the application.
 *
 * @version 1.0.0
 * @see InitializingBean
 * @see DisposableBean
 * @since 8.0
 */
@Configuration
@SuppressWarnings("all")
public class LifecycleConfiguration implements InitializingBean, DisposableBean {

	private static final Logger log = LoggerFactory.getLogger(LifecycleConfiguration.class);

	@Value("${spring.profiles.active:NULL}")
    private String activeProfiles;
    
    @Value("${spring.datasource.url:NULL}")
    private String dataSourceUrl;
    
    @Value("${client-url}")
    private String clientUrl;

	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("[ START ] Spring Boot 시작 시 처리할 내용 추가");
		if (log.isInfoEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("+--------------------------------+---------------------------------------").append("\n");
			sb.append("| Spring Active Profiles         | ").append(activeProfiles).append("\n");
            sb.append("| Spring Data Source Url         | ").append(dataSourceUrl).append("\n");
            sb.append("| Spring user Api url            | ").append(clientUrl).append("\n");
            sb.append("+--------------------------------+---------------------------------------").append("\n");
            
            log.info("YAML Info...\n{}", sb.toString());
		}
	}

	@Override
	public void destroy() throws Exception {
		log.info("[ STOP  ] Spring Boot 종료 시 처리할 내용 추가");
	}

}
