package com.cwl.imitatereadhub.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.cwl.imitatereadhub.R;
import com.cwl.imitatereadhub.utils.UtilsCode;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<T extends  BaseContract.BasePresenter> extends RxAppCompatActivity implements BaseContract.BaseView {

    @Nullable
    @Inject
    protected T mPresenter;
    private Unbinder unbinder;
     private KProgressHUD mKProgressHUD;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutId=getLayoutId();
        setContentView(layoutId);
        unbinder= ButterKnife.bind(this);//绑定控件
        attachView();
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind(); //解绑
        detachView();

    }

    /**
     * 贴上View
     */
    private void attachView() {
        if (mPresenter!=null){
            mPresenter.attachView(this);
        }
    }

    /**
     * 分离View
     */
    private void detachView() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!UtilsCode.isConnect()){
            showNoNet();
        }
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();


    @Override
    public void showLoading() {
        mKProgressHUD = KProgressHUD.create(this);
        mKProgressHUD.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();
    }

    @Override
    public void hideLoading() {
           if (mKProgressHUD!=null){
               mKProgressHUD.dismiss();
           }
    }

    @Override
    public void showSuccess() {

    }

    @Override
    public void showFailed() {
        Toast.makeText(this, R.string.request_api_failed, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNoNet() {
        Toast.makeText(this, R.string.check_net, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRetry() {

    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.bindToLifecycle();
    }
}
