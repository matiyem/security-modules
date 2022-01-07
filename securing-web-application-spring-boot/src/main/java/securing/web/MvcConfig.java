package securing.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * created by Atiye Mousavi
 * Date: 8/28/2021
 * Time: 2:01 PM
 */
//برای اینکه فرم های html ما نمایش داده شود ما نیاز داریم mvc را فعال کنیم.که از طریق پیاده سازی اینترفیس WebMvcConfigurer  انجام میشود
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
    }
}
