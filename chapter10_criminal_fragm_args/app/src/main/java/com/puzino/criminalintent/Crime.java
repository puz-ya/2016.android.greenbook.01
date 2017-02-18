package com.puzino.criminalintent;

import java.util.Date;
import java.util.UUID;

/**
 * Created on 10.02.2017
 * @author Puzino Yury.
 */

public class Crime {

    private UUID mId;
    private String mTitle;

    /** date of the crime */
    private Date mDate;
    /** is solved crime? */
    private boolean mSolved;

    public Crime(){
        mId = UUID.randomUUID();
        mDate = new Date(); //set current millisec
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }
}
