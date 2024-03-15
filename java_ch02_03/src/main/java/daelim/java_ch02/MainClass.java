package daelim.java_ch02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
    public static void main(String[] args) {

/*
        //DI (Dependency Injection) : 의존성 주입 방식
        MyCalculator calculator = new MyCalculator();

        calculator.calculator(10, 5, new CalAdd());
        calculator.calculator(10, 5, new CalSub());
        calculator.calculator(10, 5, new CalMul());
        calculator.calculator(10, 5, new CalDiv());

        //Ioc(Inversion of Control) : 제어의 역전 방식
        new CalAssembler();
*/

        // 계산기 프로그램을 스프링으로 구현
        //xml 사용
        /*
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationContext.xml");
        CalAssembler calAssembler = ctx.getBean("calAssembler", CalAssembler.class);
        calAssembler.assemble();
         */

        //Annotation 사용
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
        CalAssembler calAssembler = ctx.getBean("calAssembler", CalAssembler.class);
        calAssembler.assemble();

        ctx.close();
    }
}
