package daelim.java_ch01;


// Ioc
// 프로그램에 실행에 필요한 객체를 생성하는 클래스
// 기존에 main에서 객체 생성 역할을 수행
// ioc컨테이너에서 사용되는 객체 -> bean
// ico 컨테이너는 bean을 생성하고 필요한 곳에 주입하는 공간
// 싱글톤 형태로 bean이 만들어짐


//Ioc 컨테이너
public class CalAssembler {
    MyCalculator calculator;
    CalAdd calAdd;
    CalSub calSub;
    CalMul calMul;
    CalDiv calDiv;

    public CalAssembler(){
        calculator = new MyCalculator();
        calAdd = new CalAdd();
        calSub = new CalSub();
        calMul = new CalMul();
        calDiv = new CalDiv();
    }

    public void assemble(){
        calculator.calculator(10, 5, calAdd);
        calculator.calculator(10, 5, calSub);
        calculator.calculator(10, 5, calMul);
        calculator.calculator(10, 5, calDiv);

    }

}
