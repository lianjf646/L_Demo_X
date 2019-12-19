package com.phph.diarydemo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.phph.diarydemo.R;

/**
 * Created by v on 2019/12/18.
 */
public class BookingFragment extends Fragment {

    private TextView tv_num;
    private int num;

    public static BookingFragment newInstance(int num) {
        Bundle args = new Bundle();
        args.putInt("num", num);
        BookingFragment fragment = new BookingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_booking, container, false);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_num = view.findViewById(R.id.tv_num);

    }


    @Override
    public void onResume() {
        super.onResume();
        Bundle bundle = getArguments();
        if (bundle != null) {
            num = bundle.getInt("num");
            tv_num.setText(String.valueOf(num));
        }

    }
}
