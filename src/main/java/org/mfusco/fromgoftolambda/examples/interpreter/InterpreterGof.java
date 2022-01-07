package org.mfusco.fromgoftolambda.examples.interpreter;

import java.util.Stack;

public class InterpreterGof {

    interface Expression {
        int interpret();
    }

    public static class Add implements Expression{

        private final Expression leftExpression;
        private final Expression rightExpression;

        public Add(Expression leftExpression,Expression rightExpression ){
            this.leftExpression = leftExpression;
            this.rightExpression = rightExpression;
        }

        @Override
        public int interpret() {
            return leftExpression.interpret() + rightExpression.interpret();
        }
    }

    public static class Subtract implements Expression{

        private final Expression leftExpression;
        private final Expression rightExpression;

        public Subtract(Expression leftExpression,Expression rightExpression ){
            this.leftExpression = leftExpression;
            this.rightExpression = rightExpression;
        }

        @Override
        public int interpret() {
            return leftExpression.interpret() - rightExpression.interpret();
        }
    }

    public static class Product implements Expression{

        private final Expression leftExpression;
        private final Expression rightExpression;

        public Product(Expression leftExpression,Expression rightExpression ){
            this.leftExpression = leftExpression;
            this.rightExpression = rightExpression;
        }

        @Override
        public int interpret() {
            return leftExpression.interpret() * rightExpression.interpret();
        }
    }

    public static class Number implements Expression {
        private final int n;

        public Number(int n){
            this.n = n;
        }

        @Override
        public int interpret() {
            return n;
        }
    }

    public static boolean isOperator(String s) {
        if (s.equals("+") || s.equals("-") || s.equals("*"))
            return true;
        else
            return false;
    }

    public static Expression getOperator(String s, Expression left, Expression right) {
        switch (s) {
            case "+":
                return new Add(left, right);
            case "-":
                return new Subtract(left, right);
            case "*":
                return new Product(left, right);
        }
        return null;
    }

    public static int evaluate(String expression) {
        Stack<Expression> stack = new Stack<>();
        for (String s : expression.split(" ")) {
            if (isOperator(s)) {
                Expression right = stack.pop();
                Expression left = stack.pop();
                stack.push(getOperator(s, left, right));
            } else {
                Expression i = new Number(Integer.parseInt(s));
                stack.push(i);
            }
        }
        return stack.pop().interpret();
    }

    public static void main( String[] args ) {
        String expression = "7 3 - 2 1 + *";
        System.out.println( evaluate( expression ) );
    }
}
