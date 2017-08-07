package com.f_candy_d.pinoko.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.f_candy_d.pinoko.R;
import com.f_candy_d.pinoko.controller.CardAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CardListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class CardListFragment extends Fragment {

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnFragmentInteractionListener {
        CardAdapter getAdapter(final int fragmentId);
        RecyclerView.LayoutManager getLayoutManager(final int fragmentId);
    }

    private static final String ARG_FRAGMENT_ID = "fragmentId";

    private OnFragmentInteractionListener mListener;
    private int mFragmentId;

    public static CardListFragment newInstance(final int fragmentId) {
        CardListFragment fragment = new CardListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_FRAGMENT_ID, fragmentId);
        fragment.setArguments(bundle);

        return fragment;
    }

    public CardListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mFragmentId = getArguments().getInt(ARG_FRAGMENT_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.fragment_card_list, container, false);
        initUI(view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    private void initUI(@NonNull final View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.card_list_fcl);
        recyclerView.setLayoutManager(mListener.getLayoutManager(mFragmentId));
        recyclerView.setAdapter(mListener.getAdapter(mFragmentId));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void refresh() {

    }

    public int getFragmentId() {
        return mFragmentId;
    }

    public void setFragmentId(int fragmentId) {
        mFragmentId = fragmentId;
    }
}
