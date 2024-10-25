package com.letianpai.robot.components.expression

/**
 * Created by liujunbin
 */
class ExpressionPathCallback private constructor() {
    private var mExpressionPathListener: ExpressionPathListener? = null

    private object ExpressionPathCallbackHolder {
        val instance: ExpressionPathCallback = ExpressionPathCallback()
    }

    interface ExpressionPathListener {
        fun updateExpressionPath(expressionPath: String?)
        fun expressionFileIsNoExit(fileName: String?)
    }

    fun setExpressionPathListener(expressionPathListener: ExpressionPathListener?) {
        this.mExpressionPathListener = expressionPathListener
    }

    fun updateExpressionPath(expressionPath: String?) {
        if (mExpressionPathListener != null) {
            mExpressionPathListener!!.updateExpressionPath(expressionPath)
        }
    }

    fun expressionFileNoExit(fileName: String?) {
        if (mExpressionPathListener != null) {
            mExpressionPathListener!!.expressionFileIsNoExit(fileName)
        }
    }


    companion object {
        val instance: ExpressionPathCallback
            get() = ExpressionPathCallbackHolder.instance
    }
}












