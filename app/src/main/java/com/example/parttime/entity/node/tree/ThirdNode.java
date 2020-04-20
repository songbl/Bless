package com.example.parttime.entity.node.tree;

import com.chad.library.adapter.base.entity.node.BaseNode;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ThirdNode extends BaseNode {
    private String title;

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    private int contactId;

    public ThirdNode(String title,int contactId) {
        this.title = title;
        this.contactId = contactId ;
    }

    public String getTitle() {
        return title;
    }

    @Nullable
    @Override
    public List<BaseNode> getChildNode() {
        return null;
    }
}
