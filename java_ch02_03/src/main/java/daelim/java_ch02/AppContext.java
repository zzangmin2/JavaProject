package daelim.java_ch02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {

    @Bean
    public CalAdd calAdd(){
        return new CalAdd();
    }
    @Bean
    public CalSub calSub(){
        return new CalSub();
    }

    @Bean
    public CalMul calMul(){
        return new CalMul();
    }

    @Bean
    public CalDiv calDiv(){
        return new CalDiv();
    }

    @Bean
    public MyCalculator myCalculator(){
        return new MyCalculator();
    }

    @Bean
    public CalAssembler calAssembler(){
        return new CalAssembler(myCalculator(), calAdd(), calSub(), calMul(), calDiv());
    }
}
