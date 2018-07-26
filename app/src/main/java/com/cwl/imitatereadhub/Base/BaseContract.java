package com.cwl.imitatereadhub.Base;

import com.trello.rxlifecycle2.LifecycleTransformer;

public interface BaseContract  {

    interface BasePresenter<T extends BaseContract.BaseView>{
        void attachView(T view);
        void detachView();

    }
    interface BaseView{
        /**
         * 显示加载中
         */
        void showLoading();

        /**
         * 隐藏加载中
         */
        void hideLoading();
      /**
       * 显示请求成功
       */
        void showSuccess();

        /**
         * 显示请求失败
         */
        void showFailed();

        /**
         * 显示无网络
         */
        void showNoNet();

        /**
         * 重试
         */
        void onRetry();

        <T> LifecycleTransformer<T> bindToLife();//绑定生命周期
    }

}
