package com.example.parttime.ui.adapter.tree.provider;

import android.view.View;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.parttime.R;
import com.example.parttime.entity.node.tree.ThirdNode;
import com.example.parttime.ui.adapter.tree.NodeTreeAdapter;
import com.example.parttime.utils.LogUtil;

import org.jetbrains.annotations.NotNull;

public class ThirdProvider extends BaseNodeProvider {

    @Override
    public int getItemViewType() {
        return 3;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_node_third;
    }

    @Override
    public void convert(@NotNull BaseViewHolder helper, @NotNull BaseNode data) {
        ThirdNode entity = (ThirdNode) data;
        helper.setText(R.id.title, entity.getTitle());
    }

    @Override
    public void onClick(@NotNull BaseViewHolder helper, @NotNull View view, BaseNode data, int position) {
        // 这里使用payload进行增量刷新（避免整个item刷新导致的闪烁，不自然）

        LogUtil.e("FirstProvider","onClick(FirstProvider.java:80)"+"第三级节点点击"+((ThirdNode)data).getTitle()+"联系人Id"+((ThirdNode)data).getContactId());
    }
}
