package com.example.parttime.base;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.example.parttime.AppApplication;
import com.example.parttime.R;
import com.example.parttime.ui.activity.LoginActivity;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.impl.LoadingPopupView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


public class BaseActivity extends AppCompatActivity implements IFragmentHelper {
    protected String TAG = this.getClass().getSimpleName();
    public LoadingPopupView loadingPopup ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        log(TAG + "------" + "onCreate");
        loadingPopup = (LoadingPopupView) new XPopup.Builder(BaseActivity.this)
                .asLoading("正在加载中");
    }

    @Override
    protected void onStop() {
        super.onStop();
        log(TAG + "------" + "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        log(TAG + "------" + "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        log(TAG + "------" + "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        log(TAG + "------" + "onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        log(TAG + "------" + "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        log(TAG + "------" + "onRestart");
    }


    /**
     * 跳转到另一个activity
     * @param clazz
     */
    protected void gotoActivity(Class clazz){
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    /**
     * 跳转到另一个activity，附带动画效果
     * @param clazz
     */
    protected void gotoActivityWithAnim(Class clazz){
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
        overridePendingTransition(R.anim.activity_or_fragment_enter, R.anim.activity_or_fragment_exit);
    }
    /**
     * 跳转到另一个activity，附带动画效果
     * @param clazz
     */
    protected void gotoActivityWithAnim(String string, Class clazz){
        Intent intent = new Intent(this, clazz);
        intent.putExtra("key",string);
        startActivity(intent);
        overridePendingTransition(R.anim.activity_or_fragment_enter, R.anim.activity_or_fragment_exit);
    }
    /**
     * 跳转到另一个activity，附带动画效果
     */
    protected void gotoActivityWithAnim(Intent intent){
        startActivity(intent);
        overridePendingTransition(R.anim.activity_or_fragment_enter, R.anim.activity_or_fragment_exit);
    }
    /**
     * 跳转到另一个activity，附带动画效果
     */
    protected void gotoActivitiesWithAnim(Intent[] intents){
        startActivities(intents);
        overridePendingTransition(R.anim.activity_or_fragment_enter, R.anim.activity_or_fragment_exit);
    }
    /**
     * 跳转到另一个activity，附带动画效果
     */
    protected void startActivityForResultWithAnim(Intent intent, int requestCode){
        startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.activity_or_fragment_enter, R.anim.activity_or_fragment_exit);
    } /**
     * 跳转到另一个activity，附带动画效果
     */
    protected void startActivityForResultWithAnim(Intent intent, int requestCode, Bundle bundle){
        startActivityForResult(intent, requestCode,bundle);
        overridePendingTransition(R.anim.activity_or_fragment_enter, R.anim.activity_or_fragment_exit);
    }

    /**
     * 结束，附带动画效果
     */
    protected void finishWithAnim(){
        finish();
        if (animOnFinish()){
            overridePendingTransition(R.anim.activity_or_fragment_pop_enter, R.anim.activity_or_fragment_pop_exit);
        }
    }

    @Override
    public void loadNewFragment(Fragment fragment) {
        String tag = fragment.getClass().getSimpleName();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contentFrame, fragment, tag)
                .commit();
    }

    @Override
    public void addNewFragment(Fragment currFragment, Fragment nextFragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.activity_or_fragment_enter, R.anim.activity_or_fragment_exit, R.anim.activity_or_fragment_pop_enter, R.anim.activity_or_fragment_pop_exit)
                .add(R.id.contentFrame, nextFragment, nextFragment.getClass().getSimpleName())
                .hide(currFragment)
                .addToBackStack(currFragment.getClass().getSimpleName())
                .commit();
    }

    @Override
    public void finshCurrFragment() {
        FragmentManager fm = getSupportFragmentManager();
        boolean popSuccessful = fm.popBackStackImmediate();
        if (!popSuccessful){//如果没弹出成功，说明当前fragment是第一个fragment，且是loadNewFragment进来的
            finishWithAnim();
        }
    }

    @Override
    public void navigateToPreFragment(String tag) {
        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStackImmediate(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    @Override
    public void addNewFragment(String currFragmentTag, Fragment nextFragment) {
        Fragment currFragment = getSupportFragmentManager().findFragmentByTag(currFragmentTag);
        addNewFragment(currFragment, nextFragment);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){//监听返回键，返回时加入动画
            finshCurrFragment();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 结束activity时，是否需要动画。闪屏页和主页不需要
     * @return
     */
    public boolean animOnFinish(){
        return true;
    }


    /**
     * 打印信息
     * @param msg
     */
    public void log(String msg){
        if (canLogMsg()){
//            LogUtil.i(msg);
        }
    }

    /**
     * 控制activity的全局信息，是否能打印
     * @return
     */
    public boolean canLogMsg(){
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
}
