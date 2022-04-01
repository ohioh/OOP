import java.util.LinkedList;
import java.util.Stack;

// 1 + 2 + 3 + 4 + 5 * 7
// A + B * C / D - E
// ABCD/*E-+
// TODO: falls args kleiner 3 exception
// TODO: die exceptions ausm calc catchen
// TODO: refactorn, vereinfachen
// Thanks to Bjarne Schindler for sending me this link and helping me along the way
// https://eecs.wsu.edu/~nroy/courses/cpts122/labs/Infix2Postfix.pdf

public class ProCalc {
    public static void main(String[] infixArgs) {
        LinkedList<String> postfixArgs = infixToPostfix(infixArgs);
        System.out.println(calculatePostfix(postfixArgs));
    }


    private static LinkedList<String> infixToPostfix(String[] infix)
    {
        LinkedList<String> postfix = new LinkedList<String>();
        Stack<Operator> operatorStack = new Stack<Operator>();

        for(int i = 0; i < infix.length; i++)
        {
            if(isANumber(infix[i]))
            {
                //rule 2
                postfix.add(infix[i]);
            } 
            else if(isAnOperator(infix[i])) 
            { 
                Operator infixOperator = new Operator(infix[i]);

                if(isABracket(infixOperator))
                {
                    //rule 4 & 5
                    bracketMagic(operatorStack, infixOperator);
                } 
                else 
                {
                    //rule 3
                    operatorPrecedenceMagic(postfix, operatorStack, infixOperator);
                }
            } 
        }

        //rule 6
        cleanUpMagic(postfix, operatorStack);

        return postfix;
    }


    private static void cleanUpMagic(LinkedList<String> postfix, Stack<Operator> operatorStack) {
        while (!operatorStack.empty())
        {
            postfix.add(operatorStack.pop().getOperator());
        }
    }


    private static void operatorPrecedenceMagic(LinkedList<String> postfix, Stack<Operator> operatorStack, Operator infixOperator) {
        while(!operatorStack.isEmpty() && !operatorStack.peek().isLeftBracket())
        {
            if(operatorStack.peek().hasHigherPrecedence(infixOperator))
            {
                postfix.add(operatorStack.pop().getOperator());
            }
        }
        operatorStack.push(infixOperator);
    }


    private static void bracketMagic(Stack<Operator> operatorStack, Operator infixOperator) {
        operatorStack.push(infixOperator);

        if(infixOperator.isRightBracket())
        {
            while(!operatorStack.isEmpty() && !operatorStack.peek().isLeftBracket())
            {
                if(operatorStack.peek().isRightBracket())
                {
                    operatorStack.pop();
                } 
            }
        }
    }

    private static boolean isAnOperator(String potentialOperator) {
        switch (potentialOperator) {
            case "+": return true;
            case "-": return true;
            case "*": return true;
            case "/": return true;
            default: return false;
        }
    }

    private static boolean isABracket(Operator potentialBracket) {
        if(potentialBracket.isLeftBracket() || potentialBracket.isRightBracket()){
            return true;
        }
        return false;
    }

    private static boolean isANumber(String potentialNumber) {
        try{
            Double.parseDouble(potentialNumber);
            return true;
        } catch(Exception e){
            return false;
        }
    }

    private static double calculatePostfix(LinkedList<String> postfix){
        Stack<Double> postfixStack = new Stack<Double>();

        for(String token : postfix){
            if(isANumber(token)){
                postfixStack.push(Double.parseDouble(token));
            } else {
                double operand1 = postfixStack.pop();
                double operand2 = postfixStack.pop();
                double result = calculateExpression(token, operand1, operand2);
                postfixStack.push(result);
            }
        }
        return postfixStack.pop();
    }

    private static double calculateExpression(String operator, double operand1, double operand2){
        return switch (operator) {
            case "+" -> operand1 + operand2;
            case "-" -> operand1 - operand2;
            case "*" -> operand1 * operand2;
            case "/" -> operand1 / operand2;
            default -> 0.0;
        };
    }
}