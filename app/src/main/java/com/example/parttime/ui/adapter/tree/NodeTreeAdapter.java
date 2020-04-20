package com.example.parttime.ui.adapter.tree;

import com.chad.library.adapter.base.BaseNodeAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.example.parttime.entity.node.tree.FirstNode;
import com.example.parttime.entity.node.tree.SecondNode;
import com.example.parttime.entity.node.tree.ThirdNode;
import com.example.parttime.ui.adapter.tree.provider.FirstProvider;
import com.example.parttime.ui.adapter.tree.provider.SecondProvider;
import com.example.parttime.ui.adapter.tree.provider.ThirdProvider;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NodeTreeAdapter extends BaseNodeAdapter {

    public NodeTreeAdapter() {
        super();
        addNodeProvider(new FirstProvider());
        addNodeProvider(new SecondProvider());
        addNodeProvider(new ThirdProvider());
    }

    @Override
    protected int getItemType(@NotNull List<? extends BaseNode> data, int position) {
        BaseNode node = data.get(position);
        if (node instanceof FirstNode) {
            return 1;
        } else if (node instanceof SecondNode) {
            return 2;
        } else if (node instanceof ThirdNode) {
            return 3;
        }
        return -1;
    }

    public static final int EXPAND_COLLAPSE_PAYLOAD = 110;
}
