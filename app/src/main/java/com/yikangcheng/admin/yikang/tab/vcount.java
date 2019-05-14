package com.yikangcheng.admin.yikang.tab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;

public class vcount extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = View.inflate(getActivity(), R.layout.vcountlayout, null);
        TextView cte = (TextView) inflate.findViewById(R.id.oo);
        Bundle arguments = getArguments();
        String name = arguments.getString("name");
        Log.e("chen", "onCreateView: ------" + name);
        cte.setText("动态fragment："+name);
        return inflate;
    }
}
