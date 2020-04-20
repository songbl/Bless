package com.example.parttime.base;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.parttime.R;
import com.example.parttime.utils.LogUtil;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class BaseFragment extends Fragment {
    private static final String HIDDEN_TAG = "hidden_tag";

    protected String TAG = this.getClass().getSimpleName();
    protected IFragmentHelper mHelper;



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IFragmentHelper) {
            mHelper = (IFragmentHelper) context;
            Log.e("songbl","mHelp"+mHelper.toString());
        }
        log(TAG + "------" + "onAttach");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mHelper = null;
        log(TAG + "------" + "onDetach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {//解决activity重建时，导致fragment重叠显示
            boolean isHidden = savedInstanceState.getBoolean(HIDDEN_TAG);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }
        log(TAG + "------" + "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        log(TAG + "------" + "onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        log(TAG + "------" + "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        log(TAG + "------" + "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        log(TAG + "------" + "onPause");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        log(TAG + "------" + "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        log(TAG + "------" + "onDestroy");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        LogUtil.e("BaseFragment","onHiddenChanged(BaseFragment.java:102)"+hidden);
        log(TAG + "------" + "onHiddenChanged----" + hidden);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(HIDDEN_TAG, isHidden());
        super.onSaveInstanceState(outState);
        log(TAG + "------" + "onSaveInstanceState");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        log(TAG + "------" + "onConfigurationChanged");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        log(TAG + "------" + "setUserVisibleHint----" + isVisibleToUser);
    }

    /**
     * 跳转到另一个activity
     *
     * @param clazz
     */
    protected void goToActivity(Class clazz) {
        Intent intent = new Intent(getContext(), clazz);
        startActivity(intent);
    }

    /**
     * 跳转到另一个activity，附带动画效果
     *
     * @param clazz
     */
    protected void gotoActivityWithAnim(Class clazz) {
        Intent intent = new Intent(getContext(), clazz);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.activity_or_fragment_enter, R.anim.activity_or_fragment_exit);
    }

    /**
     * 跳转到另一个activity，附带动画效果
     *
     * @param intent
     */
    protected void gotoActivityWithAnim(Intent intent) {
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.activity_or_fragment_enter, R.anim.activity_or_fragment_exit);
    }

    /**
     * 跳转到另一个activity，附带动画效果
     *
     * @param intent
     * @param requestCode
     */
    protected void startActivityForResultWithAnim(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
        getActivity().overridePendingTransition(R.anim.activity_or_fragment_enter, R.anim.activity_or_fragment_exit);
    }

    /**
     * 关闭父activity
     */
    protected void closeParentActivity() {
        FragmentActivity parentAct = getActivity();
        parentAct.finish();
    }

    /**
     * 关闭父activity，附带动画效果
     */
    protected void closeParentActivityWithAnim() {
        FragmentActivity parentAct = getActivity();
        parentAct.finish();
        parentAct.overridePendingTransition(R.anim.activity_or_fragment_pop_enter, R.anim.activity_or_fragment_pop_exit);
    }


    public void setIFragmentHelper(IFragmentHelper mHelper) {
        this.mHelper = mHelper;
    }




    public void finshCurrFragment() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        boolean popSuccessful = fm.popBackStackImmediate();
        if (!popSuccessful) {//如果没弹出成功，说明当前fragment是第一个fragment，且是loadNewFragment进来的
            finishWithAnim();
        }
    }

    protected void finishWithAnim() {
        getActivity().finish();
    }



    /**
     * 控制fragment的全局信息，是否能打印
     *
     * @return
     */
    public boolean canLogMsg() {
        return true;
    }


    private View noView = null;
    private View errorView = null;



    /**
     * 隐藏不数据布局
     */
    public void hideNoDataView() {
        if (noView != null) {
            noView.setVisibility(View.INVISIBLE);
        }
        if (errorView != null) {
            errorView.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 打印信息
     *
     * @param msg
     */
    public void log(String msg) {
        if (canLogMsg()) {
            Log.i(TAG,msg);
        }
    }
}
