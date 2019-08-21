package com.timo.dream.ui.fragment;

import android.view.View;

import com.timo.base.BaseTools;
import com.timo.base.base.base_fragment.BaseFragment;
import com.timo.base.view.TitleBar;
import com.timo.dream.R;

/**
 * Created by 蔡永汪 on 2019/8/15.
 */

public class Home0Fragment extends BaseFragment {
    private TitleBar title_home;

    @Override
    protected int getContentResId() {
        return R.layout.fragment_dream;
    }

    @Override
    protected void initEvent(View view) {
        title_home = view.findViewById(R.id.title_home);
        BaseTools.setTitleBar(title_home, "观众网");
    }
}
