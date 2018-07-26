package com.cwl.imitatereadhub;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.cwl.imitatereadhub.Base.BaseActivity;
import com.cwl.imitatereadhub.ui.mine.MineFragment;
import com.cwl.imitatereadhub.ui.news.NewsFragment;
import com.cwl.imitatereadhub.ui.topic.TopicFragment;
import com.cwl.imitatereadhub.ui.wight.ScrollViewPager;
import com.cwl.imitatereadhub.ui.wight.TitleBarLayout;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.title)
    TitleBarLayout titleBar;
    @BindView(R.id.view_pager)
    ScrollViewPager viewPager;
    @BindView(R.id.rg_tab)
    RadioGroup rgtab;
    @BindView(R.id.rb_tab_topic)
    RadioButton rbTabTopic;
    @BindView(R.id.rb_tab_news)
    RadioButton rbTabNews;
    @BindView(R.id.rb_tab_mine)
    RadioButton rbTabMine;

    private long exitTime=0;
    private ViewPagerAdapter mPagerAdapter;
    private ArrayList<Fragment> mFragments=new ArrayList<>();
    private ArrayList<String> mTitleStrs=new ArrayList<>();
    private TopicFragment mTopicFragment;
    private NewsFragment mNewsFragment;
    private MineFragment mMineFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
       initFragment();
      //getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
       //BarUtil.setStatusBarVisibility(this, true);
      //BarUtil.setStatusBarColor(this, getResources().getColor(R.color.c1), 1); //状态栏
       titleBar.setTitleBarBgColor(getResources().getColor(R.color.c1)); //背景颜色
       titleBar.setTitleColor(getResources().getColor(R.color.b7));    //文字颜色
    }

    private void initFragment() {
        mTopicFragment=new TopicFragment();
        mNewsFragment=new NewsFragment();
        mMineFragment=new MineFragment();
        mFragments.add(mTopicFragment);
        mFragments.add(mNewsFragment);
        mFragments.add(mMineFragment);
    mTitleStrs.add(getString(R.string.tab_hot));
     mTitleStrs.add(getString(R.string.tab_info));
     mTitleStrs.add(getString(R.string.tab_mine));


    }

    @Override
    protected void initData() {
        mPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mPagerAdapter);
        viewPager.setCurrentItem(0);//默认显示第一页
        rbTabTopic.setChecked(true);
        titleBar.setTitle(mTitleStrs.get(0));
        initListener();

    }

    private void initListener() {
    rgtab.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            int pos=0;
            switch (checkedId){
                case R.id.rb_tab_topic:
                    pos = 0;
                    break;
                case R.id.rb_tab_news:
                    pos = 1;
                    break;
                case R.id.rb_tab_mine:
                    pos = 2;
                    break;
                default:
                    break;
            }
            viewPager.setCurrentItem(pos);
            titleBar.setTitle(mTitleStrs.get(pos));
        }
    });
    viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            titleBar.setTitle(mTitleStrs.get(position));
            switch (position) {
                case 0:
                    rbTabTopic.setChecked(true);
                    break;
                case 1:
                    rbTabNews.setChecked(true);
                    break;
                case 2:
                    rbTabMine.setChecked(true);
                    break;
                default:
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    });
    }

    @Override
    public void onBackPressed() {

          if (System.currentTimeMillis()-exitTime>2000){
              Toast.makeText(this, R.string.exit_program, Toast.LENGTH_SHORT).show();
              exitTime=System.currentTimeMillis();
              }else {
              finish();
              System.exit(0);
          }
    }
    public class ViewPagerAdapter extends FragmentPagerAdapter{

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

    }
}
