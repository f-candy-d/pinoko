package com.f_candy_d.pinoko.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.f_candy_d.pinoko.R;
import com.f_candy_d.pinoko.model.Course;
import com.f_candy_d.pinoko.model.MergeableTimeBlock;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditTimeBlockFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditTimeBlockFragment extends Fragment {

    private static final String ARG_CONTENT = "content";

    private MergeableTimeBlock mContent;

    public EditTimeBlockFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment EditTimeBlockFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditTimeBlockFragment newInstance(final MergeableTimeBlock timeBlock) {
        EditTimeBlockFragment fragment = new EditTimeBlockFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_CONTENT, timeBlock);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mContent = getArguments().getParcelable(ARG_CONTENT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_course_time_block, container, false);
    }

}
