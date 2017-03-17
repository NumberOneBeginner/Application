package com.example.peter.mystaffapplication.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.peter.mystaffapplication.R;
import com.example.peter.mystaffapplication.adapter.PagerNewsAdapter;

import java.util.ArrayList;
import java.util.List;

public class NewsroomFragment extends Fragment {
    TabLayout tabLayout ;
    ViewPager vp;
    View view;
    List<Fragment> fragments;
    private String titles[] = {"News","Activities", "Evens"};
    private int[]imageView={R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_newsroom, container, false);

        return view;
    }
   private void listAddFragment(){
       fragments = new ArrayList<Fragment>();
       fragments.add(new NewsFragment());
       fragments.add(new ActivitiesFragment());
       fragments.add(new EvensFragment());
   }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listAddFragment();
        init();
    }

    private void init(){
       tabLayout = (TabLayout) getActivity().findViewById(R.id.tab_newsroom);
         vp = (ViewPager) getActivity().findViewById(R.id.vp_newsroom);
         vp.setAdapter(new PagerNewsAdapter(getFragmentManager(),fragments));
         tabLayout.setupWithViewPager(vp);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(getTabView(i));
            }
        }
    }
    public View getTabView(int position){
        //首先为子tab布置一个布局
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.tablayout_item,null);
        TextView tv = (TextView) v.findViewById(R.id.tv_tab);
        tv.setText(titles[position]);
        ImageView iv = (ImageView) v.findViewById(R.id.img_tab);
        iv.setImageResource(imageView[position]);
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


}
