package com.cqgk.clerk.delegate;

import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cqgk.clerk.R;
import com.cqgk.clerk.base.BusinessBaseActivity;
import com.cqgk.clerk.base.BusinessBaseFragment;
import com.cqgk.clerk.helper.ImageHelper;
import com.cqgk.clerk.utils.CheckUtils;
import com.cqgk.clerk.utils.DisplayUtil;


/**
 * 头部导航栏事件聚合
 *
 * @author duke
 */
public class TitleDelegate extends BasicDelegate {

    private View mTitleBarLayout;
    private TextView mTitleShow;
    private ImageView mLeftBtn;
    private TextView mLeftText;
    private ImageView mRightBtn;
    private TextView mRightText;

    private EditText search_et;

    /**
     * 右区域
     */
    private RelativeLayout right_layout;

    /**
     * 左边区域
     */
    private LinearLayout left_layout;
    /**
     * 搜索栏
     */
    private LinearLayout top_search_bar;

    private TabClickListenerInterface tabClickListenerInterface;


    public TitleDelegate(BusinessBaseActivity activity) {
        super(activity);
    }

    public TitleDelegate(BusinessBaseFragment fragment) {
        super(fragment);
    }

    public interface TabClickListenerInterface {
        public void tabclick(View v, boolean isLeft);
    }


    /**
     *
     */
    protected void init() {
        mTitleBarLayout = findViewById(R.id.comment_title_bar_layout);
        mLeftBtn = findViewById(R.id.left_btn);
        mLeftText = findViewById(R.id.left_text);
        mTitleShow = findViewById(R.id.title_show);
        mRightBtn = findViewById(R.id.right_btn);
        mRightText = findViewById(R.id.right_text);
        left_layout = findViewById(R.id.left_layout);
        right_layout = findViewById(R.id.right_layout);


        View.OnClickListener leftOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isActivity()) {
                    getActivity().finish();
                } else {
                    getFragment().finish();
                }
            }
        };
        mLeftBtn.setOnClickListener(leftOnClickListener);
        mLeftText.setOnClickListener(leftOnClickListener);



    }





    /**
     * tab点击事件
     *
     * @param tabEvent
     */
    public void setTabEvent(TabClickListenerInterface tabEvent) {
        this.tabClickListenerInterface = tabEvent;
    }

    /**
     * 显示搜索栏
     */
    public void showSearchBar(int left, int rihgt) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT, 1.0f);
        layoutParams.setMargins(left, DisplayUtil.dip2px(8), rihgt, DisplayUtil.dip2px(8));
        top_search_bar.setLayoutParams(layoutParams);
        top_search_bar.setVisibility(View.VISIBLE);
        mTitleShow.setVisibility(View.GONE);
    }

    public void setSearch_etNotrue() {
        search_et.setFocusable(false);
        search_et.setFocusableInTouchMode(false);
    }


    /**
     * 设置编辑框时间
     *
     * @param onEditorActionListener
     */
    public void setSearchAction(EditText.OnEditorActionListener onEditorActionListener) {
        search_et.setOnEditorActionListener(onEditorActionListener);
    }

    /**
     * @param clickListener
     */
    public void setTopSearchBarClick(View.OnClickListener clickListener) {
        search_et.setOnClickListener(clickListener);
    }


    public void setBackground(@ColorRes int colorId) {
        mTitleBarLayout.setBackgroundColor(getResources().getColor(colorId));
    }

    public void setBackgroundColor(int color) {
        mTitleBarLayout.setBackgroundColor(color);
    }

    public void setTitleTextColor(int color) {
        mTitleShow.setTextColor(color);
    }

    public void setTitle(int titleId) {
        setTitle(getString(titleId));
    }

    public void setTitle(String text) {
        if (CheckUtils.isAvailable(text)) {
            mTitleShow.setText(text);
        }
    }

    public void ShowRightView() {
        mRightBtn.setVisibility(View.VISIBLE);
        mRightText.setVisibility(View.VISIBLE);
    }

    public void hideLeftView() {
        left_layout.setVisibility(View.GONE);
    }

    public void hideLeftBtn() {
        mLeftBtn.setVisibility(View.INVISIBLE);
    }


    public void hideRightBtn() {
        mRightBtn.setVisibility(View.INVISIBLE);
//        mRightText.setVisibility(View.INVISIBLE);
    }

    /**
     * 隐藏右边区域
     */
    public void hideRightView() {
//        mRightBtn.setVisibility(View.INVISIBLE);
//        mRightText.setVisibility(View.INVISIBLE);
        right_layout.setVisibility(View.GONE);
    }

    public void setLeftDrawable(int rightIconId) {
        mLeftBtn.setVisibility(View.VISIBLE);
        mLeftText.setVisibility(View.GONE);
        mLeftBtn.setImageResource(rightIconId);
    }

    public void setLeftDrawable(Drawable rightIcon) {
        mLeftBtn.setVisibility(View.VISIBLE);
        mLeftText.setVisibility(View.GONE);
        mLeftBtn.setImageDrawable(rightIcon);
    }

    /**
     * 从网络获取图片
     *
     * @param url
     */
    public void setLeftDrawableUrl(String url) {
        mLeftBtn.setScaleType(ImageView.ScaleType.FIT_XY);
        mLeftBtn.setLayoutParams(new LinearLayout.LayoutParams(DisplayUtil.dip2px(40), DisplayUtil.dip2px(40)));
        ImageHelper.getInstance().displayZhan(mLeftBtn, url);
    }


    public void setLeftOnClick(View.OnClickListener onClickListener) {
        left_layout.setOnClickListener(onClickListener);
        mLeftBtn.setOnClickListener(onClickListener);
    }

    public void setLeftText(int titleId) {
        setLeftText(getString(titleId));
    }

    public void setLeftText(String text) {
        if (CheckUtils.isAvailable(text)) {
            mLeftBtn.setVisibility(View.GONE);
            mLeftText.setVisibility(View.VISIBLE);
            mLeftText.setText(text);
        }
    }

    public void setLeftBtnxtColor(int color) {
        mLeftText.setTextColor(color);
    }

    public void setLeftBackground(@DrawableRes int resId) {
        mLeftBtn.setBackgroundResource(resId);
    }

    public void setRightBackground(@DrawableRes int resId) {
        mRightBtn.setBackgroundResource(resId);
    }

    public void setRightOnClick(View.OnClickListener onClickListener) {
        mRightBtn.setOnClickListener(onClickListener);
        mRightText.setOnClickListener(onClickListener);
    }

    public void setRightText(int titleId) {
        setRightText(getString(titleId));
    }

    public void setRightText(String text) {
        if (CheckUtils.isAvailable(text)) {
            mRightBtn.setVisibility(View.GONE);
            mRightText.setVisibility(View.VISIBLE);
            mRightText.setText(text);
        }
    }

    public void setRightTextSize(int size) {
        mRightText.setTextSize(size);
    }

    public void setRightTextColor(int color) {
        mRightText.setTextColor(color);
    }

    public void setRightDrawable(int rightIconId) {
        mRightBtn.setVisibility(View.VISIBLE);
        mRightText.setVisibility(View.GONE);
        mRightBtn.setImageResource(rightIconId);
    }

    public void setRightDrawable(Drawable rightIcon) {
        mRightBtn.setVisibility(View.VISIBLE);
        mRightText.setVisibility(View.GONE);
        mRightBtn.setImageDrawable(rightIcon);
    }

    public void Hide() {
        mTitleBarLayout.setVisibility(View.GONE);
    }

    public void Show() {
        mTitleBarLayout.setVisibility(View.VISIBLE);
    }

    /**
     * 获取搜索框内容
     */
    public String getSearchString() {
        return search_et.getText().toString();
    }

    /**
     * @param str
     * @return
     */
    public void setSearchString(String str) {
        search_et.setText(str);
    }


}
