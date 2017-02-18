package com.puzino.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created on 18.02.2017
 *
 * @author Puzino Yury.
 */

public class CrimeLab {
    private static CrimeLab mCrimeLab;
    private List<Crime> mCrimes;

    public static CrimeLab get(Context context){
        if(mCrimeLab == null){
            mCrimeLab = new CrimeLab(context);
        }
        return mCrimeLab;
    }

    /** closed constructor */
    private CrimeLab(Context context){
        mCrimes = new ArrayList<>();

        //**
         for(int i=0; i<100; i++){
             Crime crime = new Crime();
             crime.setTitle("# = " + i);
             crime.setSolved(i % 2 == 0);   //each second is solved
             mCrimes.add(crime);
         }
         //*/
    }

    /** get all crimes */
    public List<Crime> getCrimes(){
        return mCrimes;
    }

    /** get one Crime from list */
    public Crime getCrime(UUID id){
        for(Crime crime : mCrimes){
            if(crime.getId().equals(id)){
                return crime;
            }
        }
        return null;
    }
}
