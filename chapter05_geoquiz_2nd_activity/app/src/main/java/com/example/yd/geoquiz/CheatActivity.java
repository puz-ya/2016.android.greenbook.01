package com.example.yd.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    public static final String EXTRA_ANSWER = "answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN = "answer_shown";
    private boolean mAnswerIsTrue;
    private boolean mWasAnswerShown;

    public static final String ANSWER_INDEX = "answer_was_shown";

    private TextView mAnswerTextView;
    private Button mShowAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER, false);

        mAnswerTextView = (TextView) findViewById(R.id.answerTextView);

        mShowAnswer = (Button) findViewById(R.id.showAnswerButton);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAnswerIsTrue){
                    mAnswerTextView.setText(R.string.true_button);
                }else{
                    mAnswerTextView.setText(R.string.false_button);
                }
                mWasAnswerShown = true;
                setAnswerShowResult(mWasAnswerShown);
            }
        });

        if(savedInstanceState != null){
            mWasAnswerShown = savedInstanceState.getBoolean(ANSWER_INDEX,false);
            setAnswerShowResult(mWasAnswerShown);
        }
    }

    /** intent, which we send from QuizActivity to show answer
     * @param context context of QuizActivity
     * @param bAnswer is answer true or false
     * */
    public static Intent cheatingIntent(Context context, boolean bAnswer){
        Intent intent = new Intent(context, CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER, bAnswer);
        return intent;
    }

    /** set Result back to main QuizActivity with info about cheating
     * @param isAnswerShown set to true by default */
    private void setAnswerShowResult(boolean isAnswerShown){
        Intent intent = new Intent();
        intent.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, intent);
    }

    /** no hacking threw rotation */
    //saving our index from death
    @Override
    protected void onSaveInstanceState(Bundle bundle){
        super.onSaveInstanceState(bundle);

        bundle.putBoolean(ANSWER_INDEX, mWasAnswerShown);
    }
}
