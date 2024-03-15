package daelim.java_ch02;

public class CalAssembler {
    MyCalculator calculator;
    CalAdd calAdd;
    CalSub calSub;
    CalMul calMul;
    CalDiv calDiv;


    public CalAssembler(MyCalculator calculator, CalAdd calAdd, CalSub calSub, CalMul calMul, CalDiv calDiv) {
        this.calculator = calculator;
        this.calAdd = calAdd;
        this.calSub = calSub;
        this.calMul = calMul;
        this.calDiv = calDiv;
    }


    public void assemble(){
        calculator.calculator(10, 5, calAdd);
        calculator.calculator(10, 5, calSub);
        calculator.calculator(10, 5, calMul);
        calculator.calculator(10, 5, calDiv);

    }

}
