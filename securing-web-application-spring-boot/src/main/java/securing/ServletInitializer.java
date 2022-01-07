package securing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ServletInitializer extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ServletInitializer.class, args);
    }

    //متد configure برای config کردن برنامه است
    //متد createSpringApplicationBuilder یک springApplicationBuilder را بر میگرداند که برای configure و ایجاد springApplication استفاده میشود
    //متد deregisterJdbcDrivers درایورهای jdbc را register میکند
    //متد run برای fully configure است
    //متد setRegisterErrorPageFilter برای ریجستر کردن error filter است
    @Override

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ServletInitializer.class);
    }

}
