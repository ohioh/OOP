class Calc {
    public static void main (String[] args) {
        boolean validOperator = true;
        double result = 0;
        double numberOne;
        double numberTwo;
        try {
            char operator = args[1].charAt(0);
            numberOne = Double.parseDouble(args[0]);
            numberTwo = Double.parseDouble(args[2]);
            switch (operator) {
                case '+':
                    result = numberOne + numberTwo;
                    break;
                case '-':
                    result = numberOne - numberTwo;
                    break;
                case '*':
                    result = numberOne * numberTwo;
                    break;
                case '/':
                    result = numberOne / numberTwo;
                    break;
                default:
                    System.out.println("Benutzen Sie bitte einen Operator!");
                    validOperator = false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Benutzen sie bitte Zahlen!");
            validOperator = false;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Sie müssen Parameter mit übergeben!");
            validOperator = false;
        } catch (Exception e) {
            System.out.println("Ein Fehler ist aufgetreten.");
            validOperator = false;
        }

        if(validOperator)
        {
            System.out.println("Das Resultat ist:" + result + "\n");
        }
        
        /*
        string[] operatoren = new string[(args.length/2)]
        for(int i = 1, j = 0; i <= args.length; i+=2)
        {
            operatoren[j] = args[i];
        }
        */
        
    }
    // mehrere parameter version:
    // die operatoren sind die ungeraden indizes in einem foreach loop auf args
    // die geraden indizes sind die nummern die verrechnet werden sollen
    // ist der erste char eines string args eine klammer muessen die rechenregeln geaendert werden
    // ist der letzte char ... selbe wie oben
    // anzahl der args gibt vor, wie viele operatoren vorkommen und somit wie viele operatoren miteinander verglichen werden muessen.
    // entsprechend der operatoren koennte man ne linq abfrage stellen und die einzelnen operatoren der prio nach abrufen??
    
}