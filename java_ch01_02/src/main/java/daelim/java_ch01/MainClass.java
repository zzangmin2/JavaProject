package daelim.java_ch01;

public class MainClass {
    public static void main(String[] args) {


        //DI (Dependency Injection) : 의존성 주입 방식
        MyCalculator calculator = new MyCalculator();

        calculator.calculator(10, 5, new CalAdd());
        calculator.calculator(10, 5, new CalSub());
        calculator.calculator(10, 5, new CalMul());
        calculator.calculator(10, 5, new CalDiv());

        //Ioc(Inversion of Control) : 제어의 역전 방식

        new CalAssembler();

    }
}
