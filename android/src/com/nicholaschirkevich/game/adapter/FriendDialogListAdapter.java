package com.nicholaschirkevich.game.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nicholaschirkevich.game.R;
import com.nicholaschirkevich.game.vkmodel.User;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import java.util.ArrayList;

/**
 * Created by Nikolas on 08.06.2016.
 */
public class FriendDialogListAdapter extends BaseAdapter {

    private ArrayList<User> users;
    private LayoutInflater layoutInflater;

    public FriendDialogListAdapter(Context context, ArrayList<User> users) {
        this.users = users;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final User user;

        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.friend_invite_dialog_item_layout, null);
            holder = new ViewHolder();
            holder.unitView = (TextView) convertView.findViewById(R.id.first_name_frined_invite_dialog_item_layout);
            holder.quantityView = (TextView) convertView.findViewById(R.id.last_name_frined_invite_dialog_item_layout);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.unitView.setText(users.get(position).getName().toString());
        holder.quantityView.setText(String.valueOf(users.get(position).getID()));

        user = users.get(position);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VKRequest vkSendInvite = new VKRequest("apps.sendRequest", VKParameters.from(VKApiConst.USER_ID,user.getID(),"text","Text text","type","request","name","sadasf","key","testkey","separate","false"));
                vkSendInvite.executeWithListener(new VKRequest.VKRequestListener() {
                    @Override
                    public void onComplete(VKResponse response) {
                        super.onComplete(response);
                    }

                    @Override
                    public void attemptFailed(VKRequest request, int attemptNumber, int totalAttempts) {
                        super.attemptFailed(request, attemptNumber, totalAttempts);
                    }

                    @Override
                    public void onError(VKError error) {
                        super.onError(error);
                    }
                });

            }
        });
        return convertView;
    }

    static class ViewHolder {
        TextView unitView;
        TextView quantityView;
    }
}
