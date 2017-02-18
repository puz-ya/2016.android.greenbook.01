package com.puzino.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

        /** link to one View - text of the crime */
        public TextView mTitleTextView;

        public CrimeHolder(View itemView){
            super(itemView);
            mTitleTextView = (TextView) itemView;
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
            View view = layoutInflater.inflate(android.R.layout.simple_list_item_1, viewGroup, false);
            return new CrimeHolder(view);
        }

        /** must implement onBindViewHolder */
        @Override
        public void onBindViewHolder(CrimeHolder holder, int position){
            Crime crime = mCrimes.get(position);
            holder.mTitleTextView.setText(crime.getTitle());
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
