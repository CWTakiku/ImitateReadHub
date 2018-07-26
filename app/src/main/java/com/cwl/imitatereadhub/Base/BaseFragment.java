package com.cwl.imitatereadhub.Base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cwl.imitatereadhub.R;
import com.cwl.imitatereadhub.utils.UtilsCode;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment<T extends BaseContract.BasePresenter> extends RxFragment implements BaseContract.BaseView{

    @Nullable
    @Inject
    protected T mPresenter;
    private Unbinder unbinder;
    private View mRootView;
    private KProgressHUD mKProgressHUD;

    protected abstract int getLayoutId();

    protected abstract void initView(View view);

    protected abstract void initData();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        attachView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mRootView==null){
         mRootView=inflater.inflate(getLayoutId(),container,false);
        }
        unbinder= ButterKnife.bind(this,mRootView);
        initView(mRootView);
        return mRootView;

    }

    @Override
    public void onResume() {
        super.onResume();
        if (!UtilsCode.isConnect()){
            showNoNet();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        detachView();
    }

    @Override
    public void showLoading() {
   mKProgressHUD=KProgressHUD.create(getActivity());
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
        Toast.makeText(getActivity(), R.string.request_api_failed, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNoNet() {
        Toast.makeText(getActivity(), R.string.check_net, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRetry() {
        Toast.makeText(getActivity(), R.string.retry, Toast.LENGTH_SHORT).show();
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.bindToLifecycle();
    }
    /**
     * 贴上view
     */
    private void attachView() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    /**
     * 分离view
     */
    private void detachView() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
