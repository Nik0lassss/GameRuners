package com.nicholaschirkevich.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.SnapshotArray;
import com.nicholaschirkevich.game.GameRuners;
import com.nicholaschirkevich.game.admob.ActionResolver;
import com.nicholaschirkevich.game.entity.LeaderboardEntity;
import com.nicholaschirkevich.game.entity.VkUser;
import com.nicholaschirkevich.game.interfaces.ResumeButtonListener;
import com.nicholaschirkevich.game.listeners.OnGetLidearBoards;
import com.nicholaschirkevich.game.menu.RecordRectangle;
import com.nicholaschirkevich.game.util.AssetsManager;
import com.nicholaschirkevich.game.util.Constants;
import com.nicholaschirkevich.game.view_adapters.RecordsLeaderBoardAdapter;

import java.util.ArrayList;


/**
 * Created by Nikolas on 10.03.2016.
 */
public class TestTable extends Group implements ResumeButtonListener, OnGetLidearBoards {
    private OrthographicCamera camera;
    private Texture cnr_line, garageNameTexture, backgroung_texture, backButtonTextureDown, backButtonTextureUp;
    private Image image, garageNameImage, backgroung_image, backButtonImageDown, backButtonImageUp;
    private ScrollPane scrollPane2;
    private ScrollPane scrollPaneRight;
    private TextButton backBttn;
    private SequenceAction sequenceReturn;
    private Label labelCoinCount;
    private ImageButton imageButton, resumeImageButton;
    private Table tableRight, container, tableLeft;
    private GameStateManager gsm;
    private ActionResolver actionResolver;
    private Group parentView;
    private ArrayList<LeaderboardEntity> leaderboardEntities = new ArrayList<LeaderboardEntity>();
    private ArrayList<VkUser> vkUsers = new ArrayList<VkUser>();
    private float scrollY = 0;

    public TestTable(GameStateManager gsm, ActionResolver actionResolver, Group parentView) {

        this.actionResolver = actionResolver;
        this.parentView = parentView;
        setInvisibleParent(parentView);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        cnr_line = AssetsManager.getTextureRegion(Constants.CNR_LINE_ID).getTexture();
        backgroung_texture = AssetsManager.getTextureRegion(Constants.RECORN_TAB_BUTTON_SELECTED_ID).getTexture();
        backgroung_image = new Image(backgroung_texture);
        backgroung_image.setBounds(0, 0, GameRuners.WIDTH / 2, GameRuners.HEIGHT / 2);
        //addActor(backgroung_image);
        image = new Image(cnr_line);
        image.setBounds(0, GameRuners.HEIGHT / 2 - 70, GameRuners.WIDTH / 2, 80);
        sequenceReturn = new SequenceAction();
        this.gsm = gsm;
        garageNameTexture = AssetsManager.getTextureRegion(Constants.COIN_SHOP_NAME_ID).getTexture();
        garageNameImage = new Image(garageNameTexture);
        garageNameImage.setBounds(5, GameRuners.HEIGHT / 2 - 40, garageNameTexture.getWidth(), garageNameTexture.getHeight());


        actionResolver.getLidearBoards(this);
        addRectagleScroll();

        setUpBackButton();
        setUpTabImageButton();

//        setUpBackButton();
//        setUpStartButton();
        addActor(image);
        addActor(garageNameImage);

        //setUpImageCoinCount();
        //setUpCoinCountLabel();
        //setUpResumeImageButton();

    }


    public void addRectagleScroll() {
        addActor(new RecordRectangle(10, 72, GameRuners.WIDTH / 2 - 20, GameRuners.HEIGHT / 2 - 218));
    }

    public void setInvisibleParent(Group group) {
        SnapshotArray<Actor> actors = group.getChildren();
        for (Actor actor : actors) {
            actor.setVisible(false);
        }
    }

    public void setVisibleParent(Group group) {
        SnapshotArray<Actor> actors = group.getChildren();
        for (Actor actor : actors) {
            actor.setVisible(true);
        }
    }

    public void setUpTabImageButton() {

        final float ySelect = GameRuners.HEIGHT / 2 - 150;
        final float yUnSelect = ySelect + 5;
        final TextButton.TextButtonStyle leftTextButtonStyle = new TextButton.TextButtonStyle();
        leftTextButtonStyle.down = new Image(AssetsManager.getTextureRegion(Constants.RECORN_TAB_BUTTON_SELECTED_ID)).getDrawable();
        leftTextButtonStyle.up = new Image(AssetsManager.getTextureRegion(Constants.RECORN_TAB_BUTTON_SELECTED_ID)).getDrawable();
        leftTextButtonStyle.font = AssetsManager.getUiSkin().getFont("default-font");
        final TextButton leftButtonFriends = new TextButton("Друзья", leftTextButtonStyle);
        leftButtonFriends.getLabel().setFontScale(0.5f, 0.5f);
        leftButtonFriends.setBounds(11, ySelect, GameRuners.WIDTH / 4 - 12, 62);


        final TextButton.TextButtonStyle rightTextButtonStyle = new TextButton.TextButtonStyle();
        rightTextButtonStyle.down = new Image(AssetsManager.getTextureRegion(Constants.RECORN_TAB_BUTTON_UNSELECTED_ID)).getDrawable();
        rightTextButtonStyle.up = new Image(AssetsManager.getTextureRegion(Constants.RECORN_TAB_BUTTON_UNSELECTED_ID)).getDrawable();
        rightTextButtonStyle.font = AssetsManager.getUiSkin().getFont("default-font");
        final TextButton rightButtonFriends = new TextButton("Все", rightTextButtonStyle);
        rightButtonFriends.getLabel().setFontScale(0.5f, 0.5f);
        rightButtonFriends.setBounds(GameRuners.WIDTH / 4 + 1, yUnSelect, GameRuners.WIDTH / 4 - 12, 57);
        leftButtonFriends.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                leftTextButtonStyle.down = new Image(AssetsManager.getTextureRegion(Constants.RECORN_TAB_BUTTON_SELECTED_ID)).getDrawable();
                leftTextButtonStyle.up = new Image(AssetsManager.getTextureRegion(Constants.RECORN_TAB_BUTTON_SELECTED_ID)).getDrawable();
                leftButtonFriends.setBounds(11, ySelect, GameRuners.WIDTH / 4 - 12, 62);
                rightTextButtonStyle.down = new Image(AssetsManager.getTextureRegion(Constants.RECORN_TAB_BUTTON_UNSELECTED_ID)).getDrawable();
                rightTextButtonStyle.up = new Image(AssetsManager.getTextureRegion(Constants.RECORN_TAB_BUTTON_UNSELECTED_ID)).getDrawable();
                rightButtonFriends.setBounds(GameRuners.WIDTH / 4 + 1, yUnSelect, GameRuners.WIDTH / 4 - 12, 57);

                return super.touchDown(event, x, y, pointer, button);
            }
        });

        rightButtonFriends.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                setUpCoinShop2();
                rightTextButtonStyle.down = new Image(AssetsManager.getTextureRegion(Constants.RECORN_TAB_BUTTON_SELECTED_ID)).getDrawable();
                rightTextButtonStyle.up = new Image(AssetsManager.getTextureRegion(Constants.RECORN_TAB_BUTTON_SELECTED_ID)).getDrawable();
                rightButtonFriends.setBounds(GameRuners.WIDTH / 4 + 1, ySelect, GameRuners.WIDTH / 4 - 12, 62);
                leftTextButtonStyle.down = new Image(AssetsManager.getTextureRegion(Constants.RECORN_TAB_BUTTON_UNSELECTED_ID)).getDrawable();
                leftTextButtonStyle.up = new Image(AssetsManager.getTextureRegion(Constants.RECORN_TAB_BUTTON_UNSELECTED_ID)).getDrawable();
                leftButtonFriends.setBounds(11, yUnSelect, GameRuners.WIDTH / 4 - 12, 57);

                return super.touchDown(event, x, y, pointer, button);
            }
        });
        addActor(new RecordRectangle(10, GameRuners.HEIGHT / 2 - 146, GameRuners.WIDTH / 4 - 10, 59));

        addActor(new RecordRectangle(GameRuners.WIDTH / 4, GameRuners.HEIGHT / 2 - 146, GameRuners.WIDTH / 4 - 10, 59));


        addActor(rightButtonFriends);
        addActor(leftButtonFriends);
    }


    private void setUpBackButton() {
        backButtonTextureDown = AssetsManager.getTextureRegion(Constants.BACK_BUTTON_PRESSED_ID).getTexture();
        backButtonTextureUp = AssetsManager.getTextureRegion(Constants.BACK_BUTTON_ID).getTexture();

        backButtonImageDown = new Image(backButtonTextureDown);
        backButtonImageUp = new Image(backButtonTextureUp);


        float x = Constants.BACK_BTTN_X_VISIBLE, y = Constants.BACK_BTTN_Y_VISIBLE - 35, width = 70, height = 55;
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.down = backButtonImageDown.getDrawable();
        textButtonStyle.up = backButtonImageUp.getDrawable();
        textButtonStyle.font = AssetsManager.getUiSkin().getFont("default-font");

        backBttn = new TextButton("", textButtonStyle);
        backBttn.getLabel().setFontScale(0.4f, 0.4f);
        backBttn.getLabelCell().padLeft(25f);


        backBttn.setBounds(x - backBttn.getWidth() / 2, y - backBttn.getHeight() / 2, backBttn.getWidth(), backBttn.getHeight());

        backBttn.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                sequenceReturn.addAction(Actions.delay(0.1f));
                sequenceReturn.addAction(new Action() {
                    @Override
                    public boolean act(float delta) {
                        setVisibleParent(parentView);
                        getStage().addActor(parentView);

                        //getStage().addActor(new MenuTest(listenerResume, gsm, actionResolver));
                        return true;
                    }
                });
                sequenceReturn.addAction(Actions.removeActor());


                addAction(sequenceReturn);

                return true;
            }
        });

        addActor(backBttn);

    }


    public void setUpCoinShop2() {
        container = new Table();
        tableRight = new Table();


        //RecordsLeaderBoardAdapter recordAdapter = new RecordsLeaderBoardAdapter(tableRight, vkUsers,actionResolver);

        tableRight.add(new Image(AssetsManager.getTextureRegion(Constants.BTTN_GET_BONUS_PRESSED_ID).getTexture()));
        tableRight.add(new Image(AssetsManager.getTextureRegion(Constants.BTTN_GET_BONUS_PRESSED_ID).getTexture()));
        tableRight.add(new Image(AssetsManager.getTextureRegion(Constants.BTTN_GET_BONUS_PRESSED_ID).getTexture())).row();
        tableRight.add(new Image(AssetsManager.getTextureRegion(Constants.BTTN_GET_BONUS_PRESSED_ID).getTexture())).row();
        tableRight.add(new Image(AssetsManager.getTextureRegion(Constants.BTTN_GET_BONUS_PRESSED_ID).getTexture())).row();
        tableRight.add(new Image(AssetsManager.getTextureRegion(Constants.BTTN_GET_BONUS_PRESSED_ID).getTexture())).row();
        tableRight.add(new Image(AssetsManager.getTextureRegion(Constants.BTTN_GET_BONUS_PRESSED_ID).getTexture())).row();
        tableRight.add(new Image(AssetsManager.getTextureRegion(Constants.BTTN_GET_BONUS_PRESSED_ID).getTexture())).row();
        tableRight.add(new Image(AssetsManager.getTextureRegion(Constants.BTTN_GET_BONUS_PRESSED_ID).getTexture())).row();
        tableRight.add(new Image(AssetsManager.getTextureRegion(Constants.BTTN_GET_BONUS_PRESSED_ID).getTexture())).row();
        tableRight.add(new Image(AssetsManager.getTextureRegion(Constants.BTTN_GET_BONUS_PRESSED_ID).getTexture())).row();
        tableRight.add(new Image(AssetsManager.getTextureRegion(Constants.BTTN_GET_BONUS_PRESSED_ID).getTexture())).row();
        tableRight.add(new Image(AssetsManager.getTextureRegion(Constants.BTTN_GET_BONUS_PRESSED_ID).getTexture())).row();
        tableRight.add(new Image(AssetsManager.getTextureRegion(Constants.BTTN_GET_BONUS_PRESSED_ID).getTexture())).row();
        tableRight.add(new Image(AssetsManager.getTextureRegion(Constants.BTTN_GET_BONUS_PRESSED_ID).getTexture())).row();
        tableRight.top().left();
        tableRight.padTop(3f);

        ArrayList<Actor> actors = new ArrayList<Actor>();
        actors.add(new Image(AssetsManager.getTextureRegion(Constants.BTTN_GET_BONUS_PRESSED_ID).getTexture()));
        actors.add(new Image(AssetsManager.getTextureRegion(Constants.BTTN_GET_BONUS_PRESSED_ID).getTexture()));
        actors.add(new Image(AssetsManager.getTextureRegion(Constants.BTTN_GET_BONUS_PRESSED_ID).getTexture()));
        actors.add(new Image(AssetsManager.getTextureRegion(Constants.BTTN_GET_BONUS_PRESSED_ID).getTexture()));
        actors.add(new Image(AssetsManager.getTextureRegion(Constants.BTTN_GET_BONUS_PRESSED_ID).getTexture()));
        actors.add(new Image(AssetsManager.getTextureRegion(Constants.BTTN_GET_BONUS_PRESSED_ID).getTexture()));
        //recordAdapter.loadTableData();
        List.ListStyle listStyle = new List.ListStyle();
        listStyle.font =AssetsManager.getUiSkin().getFont("default-font");
        listStyle.background = new Image( AssetsManager.getTextureRegion(Constants.RECORN_TAB_BUTTON_SELECTED_ID)).getDrawable();
        listStyle.selection =  new Image( AssetsManager.getTextureRegion(Constants.RECORN_TAB_BUTTON_SELECTED_ID)).getDrawable();
        listStyle.fontColorSelected = Color.BLUE;
        listStyle.fontColorUnselected= Color.BROWN;
        List listView = new List(listStyle);

        listView.setItems(actors);
        listView.setBounds(20,20,170,500);

        scrollPane2 = new ScrollPane(tableRight);
        scrollPane2.setBounds(11, 73, GameRuners.WIDTH / 2 - 22, GameRuners.HEIGHT / 2 - 220);
        //scrollPane2.setScrollingDisabled(false, true);
        scrollPane2.setOverscroll(false, false);
        scrollPane2.setForceScroll(true,true);
        scrollPane2.setForceScroll(true, true);


        scrollPane2.updateVisualScroll();
        scrollPane2.invalidate();

//        ScrollPane scrollPane3 = new ScrollPane(container);
//        scrollPane3.setBounds(11, 73, GameRuners.WIDTH / 2 - 22, GameRuners.HEIGHT / 2 - 220);
//        scrollPane3.setScrollingDisabled(true, false);
//        scrollPane3.setOverscroll(false, false);
//        scrollPane3.invalidate();
        backgroung_image.setBounds(11, 73, GameRuners.WIDTH / 2 - 22, GameRuners.HEIGHT / 2 - 220);
//        addActor(backgroung_image);
//        addActor(scrollPane2);
        addActor(listView);

    }


    @Override
    public void resumeButtonOnResume() {

    }

    @Override
    public void onSaveMe() {

    }


    @Override
    public void onGetLidearboardsData(ArrayList<LeaderboardEntity> leaderboard) {
        leaderboardEntities = leaderboard;

        actionResolver.getVkImageLidearBoards(this, leaderboardEntities);

    }

    @Override
    public void onGetVkImageLidearboardsData(ArrayList<VkUser> vkUserArrayList) {
        vkUsers = vkUserArrayList;
        for (VkUser vkUser : vkUserArrayList) {
            for (LeaderboardEntity leaderboard : leaderboardEntities) {
                if (vkUser.getId().equals(leaderboard.getVk_id())) {
                    vkUser.setHighscore(leaderboard.getHighscore());
                }
            }
        }

        setUpCoinShop2();

    }

    @Override
    public void onGetImage(byte[] imageByte) {

    }

    @Override
    public void onGetHighscoresFriends(ArrayList<VkUser> arrayList) {

    }

    @Override
    public void onGetFacebookHighscoresFriends(ArrayList<VkUser> arrayList) {

    }

    @Override
    public void onGetLeaderboardErrore(String errore) {

    }

    @Override
    public void onGetVkLeaderboardErrore(String errore) {

    }


    @Override
    public void act(float delta) {
        super.act(delta);

        if (scrollPane2 != null){

            //if(scrollY<scrollPane2.getWidth()/2)scrollY++;
        scrollPane2.setScrollX(500);
        scrollPane2.invalidate();}
    }
}
