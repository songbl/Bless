package com.example.parttime.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.parttime.R;
import com.example.parttime.entity.node.ContactsEntity;
import com.example.parttime.entity.node.GroupEntity;
import com.example.parttime.utils.LogUtil;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * Create adapter
 */
public class ContactsList2Adapter extends BaseQuickAdapter<ContactsEntity, BaseViewHolder> {

    public static final int ITEM_0_PAYLOAD = 901;
    public ContactsList2Adapter(List<ContactsEntity> list) {
        super(R.layout.item_contacts_list, list);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ContactsEntity item) {
        helper.setText(R.id.tv_name, item.getContactsName());
        helper.setText(R.id.tv_mobile, item.getContactsMobile());

    }
//
//    /**
//     * This method will only be executed when there is payload info
//     *
//     * 当有 payload info 时，只会执行此方法
//     *
//     * @param helper   A fully initialized helper.
//     * @param item     The item that needs to be displayed.
//     * @param payloads payload info.
//     */
//    @Override
//    protected void convert(@NotNull BaseViewHolder helper, @NotNull ContactsEntity item, @NotNull List<?> payloads) {
//        for (Object p : payloads) {
//            int payload = (int) p;
//            if (payload == ITEM_0_PAYLOAD) {
//                helper.setText(R.id.tweetName, item.getGroupName());
//            }
//        }
//    }

}
