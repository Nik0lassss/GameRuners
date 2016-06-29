package com.nicholaschirkevich.game.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.nicholaschirkevich.game.R;
import com.nicholaschirkevich.game.application.Application;
import com.nicholaschirkevich.game.entity.ImageProgressViewScale;
import com.nicholaschirkevich.game.util.ToastHelper;
import com.nicholaschirkevich.game.vkmodel.User;
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
    private ImageLoader imageLoader = Application.getInstance().getImageLoader();
    private Context context;



    public FriendDialogListAdapter(Context context, ArrayList<User> users) {
        this.users = users;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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


        if (layoutInflater == null)
            layoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = layoutInflater.inflate(R.layout.friend_invite_dialog_item_layout, null);

        if (imageLoader == null)
            imageLoader = Application.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.fragment_friends_item_friend_image);
        final User user = users.get(position);


//        ViewHolder holder;
//        if (convertView == null)
//            convertView = layoutInflater.inflate(R.layout.friend_invite_dialog_item_layout, null);
//            holder = new ViewHolder();
            TextView name = (TextView) convertView.findViewById(R.id.first_name_frined_invite_dialog_item_layout);
            //TextView quantityView = (TextView) convertView.findViewById(R.id.last_name_frined_invite_dialog_item_layout);
        thumbNail.setImageUrl(user.getPhoto_100(), imageLoader);
             //imageView = (ImageProgressViewScale) convertView.findViewById(R.id.fragment_friends_item_friend_image);
//            convertView.setTag(R.id.first_name_frined_invite_dialog_item_layout,unitView);
//            convertView.setTag(R.id.last_name_frined_invite_dialog_item_layout,quantityView);
//            convertView.setTag(R.id.fragment_friends_item_friend_image,imageView);
//        }
//            convertView.setTag(holder);
//        } else {
//            holder = (ViewHolder) convertView.getTag();
//        }
//         unitView= (TextView) convertView.getTag(R.id.first_name_frined_invite_dialog_item_layout);
//         quantityView =(TextView) convertView.getTag(R.id.last_name_frined_invite_dialog_item_layout);
//         imageView = (ImageProgressViewScale) convertView.getTag(R.id.fragment_friends_item_friend_image);
        name.setText(users.get(position).getName().toString());
        //quantityView.setText(String.valueOf(users.get(position).getID()));
        //imageView.setImageCircleUrl(users.get(position).getPhoto_100());



        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VKRequest vkSendInvite = new VKRequest("apps.sendRequest", VKParameters.from(VKApiConst.USER_ID,user.getID(),"text","Приглашаю по тестить","type","invite","name","sadasf","key","testkey","separate","false"));
                vkSendInvite.executeWithListener(new VKRequest.VKRequestListener() {
                    @Override
                    public void onComplete(VKResponse response) {

                        super.onComplete(response);
                        Toast.makeText(context,response.toString(),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void attemptFailed(VKRequest request, int attemptNumber, int totalAttempts) {
                        super.attemptFailed(request, attemptNumber, totalAttempts);
                        Toast.makeText(context, request.toString(), Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onError(VKError error) {
                        super.onError(error);
                        Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
        return convertView;
    }

    static class ViewHolder {
        TextView unitView;
        TextView quantityView;
        ImageProgressViewScale imageView;
    }
}
