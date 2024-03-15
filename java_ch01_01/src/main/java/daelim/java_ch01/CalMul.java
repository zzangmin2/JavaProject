package daelim.java_ch01;

public class CalMul implements ICalculator{

    //ctrl+i
    @Override
    public int doOperation(int firstNum, int secondNum) {
        return firstNum * secondNum;
    }
}
