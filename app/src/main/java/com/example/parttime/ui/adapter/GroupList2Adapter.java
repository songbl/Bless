package com.example.parttime.ui.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.parttime.R;
import com.example.parttime.entity.node.GroupEntity;
import com.example.parttime.utils.LogUtil;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * Create adapter
 */
public class GroupList2Adapter extends BaseQuickAdapter<GroupEntity, BaseViewHolder> {

    public static final int ITEM_0_PAYLOAD = 901;
    public GroupList2Adapter(List<GroupEntity> list) {
        super(R.layout.item_group_list, list);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, GroupEntity item) {
        LogUtil.e("GroupList2Adapter","GroupList2Adapter(GroupList2Adapter.java:19) "+item.getGroupName());
        helper.setText(R.id.tweetName, item.getGroupName());

    }

    /**
     * This method will only be executed when there is payload info
     *
     * 当有 payload info 时，只会执行此方法
     *
     * @param helper   A fully initialized helper.
     * @param item     The item that needs to be displayed.
     * @param payloads payload info.
     */
    @Override
    protected void convert(@NotNull BaseViewHolder helper, @NotNull GroupEntity item, @NotNull List<?> payloads) {
        for (Object p : payloads) {
            int payload = (int) p;
            if (payload == ITEM_0_PAYLOAD) {
                helper.setText(R.id.tweetName, item.getGroupName());
            }
        }
    }

}
