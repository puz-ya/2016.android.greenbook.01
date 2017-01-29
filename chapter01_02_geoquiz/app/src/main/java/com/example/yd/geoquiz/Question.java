package com.example.yd.geoquiz;

/**
 * Created by YD on 16.11.2016.
 */

public class Question {

    private int mTextResId; //queston text
    private boolean mAnswerTrue;    //answer is right or wrong

    public Question(int textResId, boolean answerTrue){
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }
}
