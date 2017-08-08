package com.f_candy_d.pinoko.utils;

import android.text.TextUtils;

import java.util.ArrayList;

/**
 * Created by daichi on 17/08/03.
 */

public class SQLWhere {

    /**
     * The super class of expression classes
     */
    abstract public static class Expr {
        protected static final String SPACE = " ";
        public Expr() {}
    }

    /**
     * Logical expression class
     */
    public static class LogicExpr extends Expr {

        public enum LogicOp {
            AND,
            OR
        }

        public Expr left = null;
        public Expr right = null;
        public LogicOp operator = null;
        private boolean isInBrancketLeft = false;
        private boolean isInBrancketRight = false;

        public LogicExpr() {}

        public LogicExpr(final LogicExpr l, final LogicOp logicOp, final LogicExpr r) {
            this.left = l;
            this.operator = logicOp;
            this.right = r;
        }

        public LogicExpr l(final Expr l) {
            return l(l, false);
        }

        public LogicExpr l(final Expr l, final boolean isInBrancket) {
            this.left = l;
            this.isInBrancketLeft = isInBrancket;
            return this;
        }

        public LogicExpr r(final Expr r) {
            return r(r, false);
        }

        public LogicExpr r(final Expr r, final boolean isInBrancket) {
            this.right = r;
            this.isInBrancketRight = isInBrancket;
            return this;
        }

        public LogicExpr and() {
            this.operator = LogicOp.AND;
            return this;
        }

        public LogicExpr or() {
            this.operator = LogicOp.OR;
            return this;
        }

        public void setIsInBrancketLeft(final boolean yesOrNot) {
            this.isInBrancketLeft = yesOrNot;
        }

        public boolean isInBrancketLeft() {
            return isInBrancketLeft;
        }

        public void setIsInBrancketRight(final boolean yesOrNot) {
            this.isInBrancketRight = yesOrNot;
        }

        public boolean isInBrancketRight() {
            return isInBrancketRight;
        }

            @Override
        public String toString() {
            if (this.left != null && this.right != null) {
                if (this.operator != null) {
                    String lef = (isInBrancketLeft)
                            ? "(" + this.left.toString() + ")"
                            : this.left.toString();

                    String rig = (isInBrancketRight)
                            ? "(" + this.right.toString() + ")"
                            : this.right.toString();

                    return lef + SPACE + this.operator.toString() + SPACE + rig;
                }

                throw new IllegalStateException("Give an operator!");
            }

            // If ether l or r is null,
            // return one which is NOT null.
            return (this.left != null)
                    ? left.toString()
                    : this.right.toString();
        }
    }

    /**
     * Conditional expression class
     */
    public static class CondExpr extends Expr {

        public enum CondOp {
            LT("<"),       // a <  b (a is Less Than b)
            LTE("<="),     // a <= b (a is Less Than or Equal to b)
            GT(">"),       // a >  b (a is Grater Than b)
            GTE(">="),     // a >= b (a is Grater Than or Equal to b)
            EQ("="),       // a == b (a is EQual to b)
            NEQ("!=");     // a != b (a is Not EQual to b)

            private String mString;

            CondOp(final String string) {
                mString = string;
            }

            @Override
            public String toString() {
                return mString;
            }
        }

        private String left = null;
        private String right = null;
        private CondOp operator = null;

        public CondExpr() {}

        public CondExpr(final String l, final CondOp condOp, final String r) {
            this.left = l;
            this.operator = condOp;
            this.right = r;
        }

        public CondExpr(final String l, final CondOp condOp, final int r) {
            this(l, condOp, String.valueOf(r));
        }

        public CondExpr(final String l, final CondOp condOp, final long r) {
            this(l, condOp, String.valueOf(r));
        }

        public CondExpr l(final String arg) {
            this.left = arg;
            return this;
        }

        public CondExpr r(final String arg) {
            this.right = arg;
            return this;
        }

        public CondExpr r(final int arg) {
            return r(String.valueOf(arg));
        }

        public CondExpr r(final long arg) {
            return r(String.valueOf(arg));
        }

        public CondExpr lessThan() {
            this.operator = CondOp.LT;
            return this;
        }

        public CondExpr lessThanOrEqualTo() {
            this.operator = CondOp.LTE;
            return this;
        }

        public CondExpr graterThan() {
            this.operator = CondOp.GT;
            return this;
        }

        public CondExpr graterThanOrEqualTo() {
            this.operator = CondOp.GTE;
            return this;
        }

        public CondExpr equalTo() {
            this.operator = CondOp.EQ;
            return this;
        }

        public CondExpr notEqualTo() {
            this.operator = CondOp.NEQ;
            return this;
        }

        @Override
        public String toString() {
            if (this.left != null && this.right != null && this.operator != null) {
                return this.left + SPACE + this.operator.toString() + SPACE + this.right;
            }
            return null;
        }
    }

    /**
     * Other special expression class
     */
    public static class SpecExpr extends Expr {

        public enum SpecOp {
            BETWEEN,
            NOT_BETWEEN,
            LIKE,
            IN,
            IS_NULL,
            IS_NOT_NULL;

            public String toString(final ArrayList<String> args) {
                switch (this) {
                    case BETWEEN: {
                        if (2 <= args.size()) {
                            return "BETWEEN " + args.get(0) + " AND " + args.get(1);
                        }
                        throw new IllegalArgumentException("Too few arguments");
                    }

                    case NOT_BETWEEN: {
                        if (2 <= args.size()) {
                            return "NOT BETWEEN " + args.get(0) + " AND " + args.get(1);
                        }
                        throw new IllegalArgumentException("Too few arguments");
                    }

                    case LIKE: {
                        if (1 <= args.size()) {
                            return "LIKE " + args.get(0);
                        }
                        throw new IllegalArgumentException("Too few arguments");
                    }

                    case IN: {
                        if (1 <= args.size()) {
                            return "IN(" + TextUtils.join(",", args) + ")";
                        }
                        throw new IllegalArgumentException("Too few arguments");
                    }

                    case IS_NULL: {
                        return "IS NULL";
                    }

                    case IS_NOT_NULL: {
                        return "IS NOT NULL";
                    }

                    default:
                        throw new IllegalArgumentException();
                }
            }
        }

        private SpecOp operator = null;
        private ArrayList<String> args;
        public String operand = null;

        public SpecExpr() {
            // (NOT)BETWEEN requires 2 arguments, LIKE requires 1 argument,
            // IN requires some arguments more than 0, and IS(NOT)NULL requires no argument, so...
            this.args = new ArrayList<>(2);
        }

        public void between(final String operand, final long min, final long max) {
            this.operator = SpecOp.BETWEEN;
            this.operand = operand;
            this.args.clear();
            this.args.add(String.valueOf(min));
            this.args.add(String.valueOf(max));
        }

        public void notBetween(final String operand, final long min, final long max) {
            this.operator = SpecOp.NOT_BETWEEN;
            this.operand = operand;
            this.args.clear();
            this.args.add(String.valueOf(min));
            this.args.add(String.valueOf(max));
        }

        public void like(final String operand, final String regex) {
            this.operator = SpecOp.LIKE;
            this.operand = operand;
            this.args.clear();
            this.args.add("'" + regex + "'");
        }

        public void in(final String operand, final String[] vals) {
            this.operator = SpecOp.IN;
            this.operand = operand;
            this.args.clear();
            for (String val : vals) {
                this.args.add("'" + val + "'");
            }
        }

        public void in(final String operand, final int[] vals) {
            this.operator = SpecOp.IN;
            this.operand = operand;
            this.args.clear();
            for (int val : vals) {
                this.args.add(String.valueOf(val));
            }
        }

        public void in(final String operand, final long[] vals) {
            this.operator = SpecOp.IN;
            this.operand = operand;
            this.args.clear();
            for (long val : vals) {
                this.args.add(String.valueOf(val));
            }
        }

        public void isNull(final String operand) {
            this.operator = SpecOp.IS_NULL;
            this.operand = operand;
            this.args.clear();
        }

        public void isNotNull(final String operand) {
            this.operator = SpecOp.IS_NOT_NULL;
            this.operand = operand;
            this.args.clear();
        }

        @Override
        public String toString() {
            if (this.operand != null && this.operator != null) {
                return this.operand + SPACE + this.operator.toString(this.args);
            }
            return null;
        }
    }

    private Expr mExpression;

    public SQLWhere() {
        this(null);
    }

    public SQLWhere(final Expr expression) {
        mExpression = expression;
    }

    public void setExpression(Expr expression) {
        mExpression = expression;
    }

    @Override
    public String toString() {
        if (mExpression != null) {
            return mExpression.toString();
        } else {
            return null;
        }
    }
}
