    public class Operator implements Comparable<Operator> {
        private String operatorSign;
        private int precedence = 0;
        private boolean isLeftBracket = false;
        private boolean isRightBracket = false;

        public Operator(String operatorSign) {
            this.operatorSign = operatorSign;
            this.precedence = getPrecedence(operatorSign);
            if(this.precedence == 1) {
                this.isLeftBracket = true;
            }
            if(this.precedence == 0) {
                this.isRightBracket = true;
            }
        }

        public String getOperator(){
            return operatorSign;
        }
        public int getPrecedence(){
            return precedence;
        }
        public boolean isLeftBracket(){
            return isLeftBracket;
        }
        public boolean isRightBracket(){
            return isRightBracket;
        }
        private int getPrecedence(String operatorSign)
        {
            switch (operatorSign) {
                case "+" : 
                case "-" : return 0;
                case "*" :
                case "/" : return 1;
                case "[" : 
                case "]" : return 2;
                default: return -1;
            }
        }

        public boolean hasHigherPrecedence(Operator otheOperator){
            if(precedence > otheOperator.precedence){
                return true;
            }
            return false;
        }

        @Override 
        public int compareTo(Operator otherOperator) {
            if(otherOperator.precedence < precedence)
            {
                return -1;
            } else if(otherOperator.precedence > precedence) {
                return 1;
            } else {
                return 0;
            }
        }
    }
