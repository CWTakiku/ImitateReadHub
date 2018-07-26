package com.cwl.imitatereadhub.ui.mine;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cwl.imitatereadhub.R;

public class MineFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
      View rootView=inflater.inflate(R.layout.fragment_mine,container,false);
      return rootView;
    }
}
