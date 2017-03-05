package com.puzino.criminalintent;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.text.DateFormat;
import java.util.Locale;
import java.util.UUID;

/**
 * Created on 10.02.2017
 * @author Puzino Yury.
 */

/**
 * A simple {@link Fragment} subclass.
 */
public class CrimeFragment extends Fragment {

    /** data for crime */
    private Crime mCrime;
    /** editText to insert crime title */
    private EditText mTitleField;
    /** set Date button */
    private Button mDateButton;
    /** checkbox if solved */
    private CheckBox mSolvedCheckBox;

    private static final String ARG_CRIME_ID = "crime_id";

    public CrimeFragment() {
        // Required empty public constructor
    }

    /** create bundle for fragment and save ID there */
    public static CrimeFragment newInstance(UUID crimeId){
        Bundle bundleArgs = new Bundle();
        bundleArgs.putSerializable(ARG_CRIME_ID, crimeId);

        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(bundleArgs);
        return fragment;
    }

    /** we create data, but no filling of Fragment content */
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_crime, container, false);

        mTitleField = (EditText) view.findViewById(R.id.crime_title);
        mTitleField.setText(mCrime.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //nothing to do here
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //nothing to do here
            }
        });

        //set new date and block button
        mDateButton = (Button) view.findViewById(R.id.crime_date);
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault());
        String newDateFormat = df.format(mCrime.getDate());
        mDateButton.setText(newDateFormat);
        mDateButton.setEnabled(false);

        //get link, set checkbox listener
        mSolvedCheckBox = (CheckBox) view.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setChecked(mCrime.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setSolved(!mCrime.isSolved());
            }
        });

        return view;
    }

    public void returnResult(){
        getActivity().setResult(Activity.RESULT_OK, null);
    }

}
