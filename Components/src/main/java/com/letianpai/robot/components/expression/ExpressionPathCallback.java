package com.letianpai.robot.components.expression;

/**
 * Created by liujunbin
 */

public class ExpressionPathCallback {

    private ExpressionPathListener mExpressionPathListener;

    private static class ExpressionPathCallbackHolder {
        private static ExpressionPathCallback instance = new ExpressionPathCallback();
    }

    private ExpressionPathCallback() {
    }

    public static ExpressionPathCallback getInstance() {
        return ExpressionPathCallbackHolder.instance;
    }

    public interface ExpressionPathListener {
        void updateExpressionPath(String expressionPath);
        void expressionFileIsNoExit(String fileName);
    }

    public void setExpressionPathListener(ExpressionPathListener expressionPathListener) {
        this.mExpressionPathListener = expressionPathListener;
    }

    public void updateExpressionPath(String expressionPath) {
        if (mExpressionPathListener != null) {
            mExpressionPathListener.updateExpressionPath(expressionPath);
        }
    }
    public void expressionFileNoExit(String fileName) {
        if (mExpressionPathListener != null) {
            mExpressionPathListener.expressionFileIsNoExit(fileName);
        }
    }


}












