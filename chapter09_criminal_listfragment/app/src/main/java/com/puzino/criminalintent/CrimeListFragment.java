package com.puzino.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created on 18.02.2017
 *
 * @author Puzino Yury.
 */

public class CrimeListFragment extends Fragment {

    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mCrimeAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_crime_list, viewGroup, false);

        mCrimeRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        //after creation we must set LayoutManager
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //set new data to Adapter and update RecyclerView
        updateUI();

        return view;
    }

    /** Simple Holder class, will be extended */
    private class CrimeHolder extends RecyclerView.ViewHolder{

        /** links to Views - text of the crime */
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mSolvedCheckBox;

        /** crime model */
        private Crime mCrime;

        public CrimeHolder(View itemView){
            super(itemView);

            //to reduce calls of findViewById() in onBindViewHolder
            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_crime_title_tview);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_crime_date_tview);
            mSolvedCheckBox = (CheckBox) itemView.findViewById(R.id.list_item_crime_solved_checkbox);
        }

        /** set data to Views */
        public void bindCrime(Crime crime){
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDate().toString());
            mSolvedCheckBox.setChecked(mCrime.isSolved());
        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>{

        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> crimes){
            mCrimes = crimes;
        }

        /** must implement getItemCount */
        @Override
        public int getItemCount(){
            return mCrimes.size();
        }

        /** must implement onCreateViewHolder
         * set view of element
         * create new Holder with that view
         * */
        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_crime, viewGroup, false);
            return new CrimeHolder(view);
        }

        /** must implement onBindViewHolder */
        @Override
        public void onBindViewHolder(CrimeHolder holder, int position){
            Crime crime = mCrimes.get(position);
            //binding by our method
            holder.bindCrime(crime);
        }
    }

    /** get data, set new Adapter, insert in RecyclerView */
    private void updateUI(){
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();

        mCrimeAdapter = new CrimeAdapter(crimes);
        mCrimeRecyclerView.setAdapter(mCrimeAdapter);
    }
}
