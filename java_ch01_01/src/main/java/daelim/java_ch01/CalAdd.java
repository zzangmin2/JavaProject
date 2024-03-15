package daelim.java_ch01;

public class CalAdd implements ICalculator{

    @Override
    public int doOperation(int firstNum, int secondNum){
        return firstNum + secondNum;
    }
}
