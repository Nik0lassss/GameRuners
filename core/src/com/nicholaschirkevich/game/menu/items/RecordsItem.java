package com.nicholaschirkevich.game.menu.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.nicholaschirkevich.game.GameRuners;
import com.nicholaschirkevich.game.admob.ActionResolver;
import com.nicholaschirkevich.game.entity.LeaderboardEntity;
import com.nicholaschirkevich.game.entity.VkUser;
import com.nicholaschirkevich.game.listeners.OnGetLidearBoards;
import com.nicholaschirkevich.game.menu.RecordRectangle;
import com.nicholaschirkevich.game.util.AssetsManager;
import com.nicholaschirkevich.game.util.Constants;
import com.nicholaschirkevich.game.util.GameManager;

import java.util.ArrayList;

/**
 * Created by Nikolas on 10.03.2016.
 */
public class RecordsItem extends Group implements OnGetLidearBoards {

    private Integer index;
    private int addHeight = 0;
    private VkUser vkUser;
    private ActionResolver actionResolver;
    private Label highscore;

    private Group groupView;

    public RecordsItem(VkUser vkUser, Integer index, ActionResolver actionResolver) {
        this.actionResolver = actionResolver;
        this.index = index;
        this.vkUser = vkUser;
        setUpBackground(index);
        setUpNumberLabel();
        setUpNameLabel(false);
        setUpHighscoreLabel();
        actionResolver.getByteImage(this, vkUser.getImage_src(), index);
        setBounds(0, 0, 220, 60 + addHeight);
        groupView = this;

    }

    public RecordsItem(VkUser vkUser, Integer index, ActionResolver actionResolver, boolean isMy) {
        this.actionResolver = actionResolver;
        this.index = index;
        this.vkUser = vkUser;
        setUpBackground(index);
        setUpNumberLabel();
        setUpNameLabel(true);
        setUpHighscoreLabel();
        actionResolver.getByteImage(this, vkUser.getImage_src(), index);
        setBounds(0, 0, 220, 60 + addHeight);
        groupView = this;


    }

    public void setUpBackground(int index) {

        if (index % 2 == 0) {

        } else {
            Image imageBakcground = new Image(AssetsManager.getTextureRegion(Constants.SLOT_BACKGROUND_RECORD_ID));
            imageBakcground.setBounds(0, 0, GameRuners.WIDTH / 2 - 22, 60);
            addActor(imageBakcground);
        }
    }

    public void setUpNumberLabel() {
        Label numberLabel = new Label(String.valueOf(index + 1), AssetsManager.getUiSkin());
        numberLabel.setPosition(getX() + 15, getY() + 30, Align.left);
        numberLabel.setFontScale(0.5f, 0.5f);
        addActor(numberLabel);
    }

    public void setUpNameLabel(boolean isMy) {
        Label numberLabel = new Label("", AssetsManager.getUiSkin());
        if (isMy) {
            numberLabel.setText(GameManager.getStrings().get(Constants.LEADERBOARD_ME_TEXT));
            numberLabel.setColor(Color.ORANGE);
        }
        else
           numberLabel.setText(vkUser.getUser_first_name() + "\n" + vkUser.getUser_last_name());
        numberLabel.setPosition(getX() + 100, getY() + 30, Align.left);
        numberLabel.setFontScale(0.4f, 0.4f);
        addActor(numberLabel);
    }

    public void setUpHighscoreLabel() {
        highscore = new Label(vkUser.getHighscore(), AssetsManager.getUiSkin());
        highscore.setPosition(getX() + 220, getY() + 30, Align.left);
        highscore.setFontScale(0.5f, 0.5f);
        addActor(highscore);
    }


    @Override
    public void onGetLidearboardsData(ArrayList<LeaderboardEntity> leaderboardEntities) {

    }

    @Override
    public void onGetVkImageLidearboardsData(ArrayList<VkUser> leaderboardEntities) {

    }

    @Override
    public void onGetImage(byte[] imageByte) {
        try {
            final Pixmap pmap = new Pixmap(imageByte, 0, imageByte.length);
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    Texture tex = new Texture(pmap);
                    Image image = new Image(tex);
                    image.setBounds(50, 10, 40, 40);
                    Image backImage = new Image(AssetsManager.getTextureRegion(Constants.BACK_BLACK_ID));
                    backImage.setBounds(48, 8, 44, 44);

                    groupView.addActor(backImage);
                    groupView.addActor(image);

                }
            });
        } catch (Exception e) {
            Gdx.app.log("KS", e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public void onGetHighscoresFriends(ArrayList<VkUser> arrayList) {

    }
}
