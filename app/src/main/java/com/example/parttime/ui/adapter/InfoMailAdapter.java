package com.example.parttime.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.parttime.R;
import com.example.parttime.entity.node.InfoMail;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * Create adapter
 */
public class InfoMailAdapter extends BaseQuickAdapter<InfoMail, BaseViewHolder> {

    public InfoMailAdapter(List<InfoMail> list) {
        super(R.layout.item_info_mail, list);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, InfoMail item) {
        helper.setText(R.id.tv_content, item.getInfoContents());
        helper.setText(R.id.tv_id, item.getInfoId()+"");

    }

}
