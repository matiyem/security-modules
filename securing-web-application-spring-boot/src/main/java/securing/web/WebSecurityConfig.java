package securing.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * created by Atiye Mousavi
 * Date: 8/28/2021
 * Time: 2:34 PM
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    //متدauthenticationManager یکauthenticationManager را برای استفاده دریافت میکند
    //متدauthenticationManagerBean برای کانفیگ کردنauthenticationManager است
    //متد configure(AuthenticationManagerBuilder auth) برای بدست آوردن AuthenticationManager. این ساتفاده میشود
    //متد configure(HttpSecurity http)برای override کردن  متدHttpSecurity. است
    //متد configure(WebSecurity web) برای override کردن webSecurity است
    //متد getApplicationContext برای گرفتن applicationContextاست
    //متد getHttp برای ساخت HttpSecurity و یا برگرداندن نمونه فعلی
    //متد init برای initialize کردن securityBuilder است
    //متدuserDetailsService برای امکان تغییر و دسترسی به UserDetailsService از userDetailsServiceBean () بدون تعامل با ApplicationContext.
    //متد  userDetailsServiceBeanاین روش را نادیده بگیرید تا UserDetailsService ایجاد شده از پیکربندی (AuthenticationManagerBuilder) به عنوان لوبیا نشان داده شود.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //اینجا داریم مشخص میکنیم کدوم url باید secure باشه و کدوم نباید
        http
                .authorizeRequests()
                .antMatchers("/", "/home").permitAll()//نیاز به secure نداره
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Override
    protected UserDetailsService userDetailsService() {
        //در این قسمت یوزر در مموری قرار میگیرد
        //یک پسورد در کنسول جنریت میشود
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }
}
