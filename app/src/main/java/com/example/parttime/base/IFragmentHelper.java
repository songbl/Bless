package com.example.parttime.base;


import androidx.fragment.app.Fragment;


public interface IFragmentHelper {
    /**
     * 加载一个新的fragment，replace方式，加载第一个fragment时使用
     * @param fragment
     */
    public void loadNewFragment(Fragment fragment);

    /**
     * 添加一个新的fragment并显示，add、show方式，加载非第一个fragment时使用
     * @param currFragment
     * @param nextFragment
     */
    public void addNewFragment(Fragment currFragment, Fragment nextFragment);

    /**
     * 结束当前fragment，回到上一个fragment界面，若此fragment为第一个fragment时，结束当前activity
     */
    public void finshCurrFragment();

    /**
     * 跳转到fragment stack中某个fragment界面，并移除目标fragment之上的所有fragment
     * @param tag
     */

    public void navigateToPreFragment(String tag);

    /**
     * 添加一个新的fragment并显示，add、show方式，加载非第一个fragment时使用
     * @param currFragmentTag
     * @param nextFragment
     */
    public void addNewFragment(String currFragmentTag, Fragment nextFragment);

}
