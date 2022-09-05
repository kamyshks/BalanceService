package kamyshks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.metrics.export.prometheus.EnablePrometheusMetrics;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableJpaRepositories
@RestController
@EnableCaching
@EnableTransactionManagement
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
