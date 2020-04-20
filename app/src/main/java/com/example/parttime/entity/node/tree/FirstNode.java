package com.example.parttime.entity.node.tree;

import com.chad.library.adapter.base.entity.node.BaseExpandNode;
import com.chad.library.adapter.base.entity.node.BaseNode;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FirstNode extends BaseExpandNode {

    private List<BaseNode> childNode;
    private String title;
    private int groupId;




    public FirstNode(List<BaseNode> childNode, String title,int groupId) {
        this.childNode = childNode;
        this.title = title;
        this.groupId = groupId ;

        setExpanded(false);
    }

    public String getTitle() {
        return title;
    }
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Nullable
    @Override
    public List<BaseNode> getChildNode() {
        return childNode;
    }
}
