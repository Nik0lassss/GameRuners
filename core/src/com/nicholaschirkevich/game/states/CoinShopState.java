package com.nicholaschirkevich.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.nicholaschirkevich.game.GameRuners;
import com.nicholaschirkevich.game.admob.ActionResolver;
import com.nicholaschirkevich.game.entity.CarsType;
import com.nicholaschirkevich.game.entity.CoinShop;
import com.nicholaschirkevich.game.interfaces.ResumeButtonListener;
import com.nicholaschirkevich.game.interfaces.UpdateCoinCountInterface;
import com.nicholaschirkevich.game.interfaces.UpdateGarageTable;
import com.nicholaschirkevich.game.menu.BackButton;
import com.nicholaschirkevich.game.menu.MenuTest;
import com.nicholaschirkevich.game.menu.StartGameGarageButton;
import com.nicholaschirkevich.game.menu.items.CoinShopItem;
import com.nicholaschirkevich.game.util.AssetsManager;
import com.nicholaschirkevich.game.util.Constants;
import com.nicholaschirkevich.game.util.GameManager;
import com.nicholaschirkevich.game.view_adapters.CoinShopAdapter;
import com.nicholaschirkevich.game.view_adapters.GarageAdapter;

import java.util.ArrayList;


/**
 * Created by Nikolas on 10.03.2016.
 */
public class CoinShopState extends Group implements ResumeButtonListener, UpdateGarageTable ,UpdateCoinCountInterface {
   private OrthographicCamera camera;
    private Texture cnr_line, garageNameTexture, backgroung_texture, backButtonTextureDown, backButtonTextureUp;
    private Image image, garageNameImage, backgroung_image, backButtonImageDown, backButtonImageUp;
    private ScrollPane scrollPane2;
    private TextButton backBttn;
    private SequenceAction sequenceReturn;
    private Label labelCoinCount;
    private ImageButton imageButton, resumeImageButton;
    private Table table, container;
    private GameStateManager gsm;
    private ActionResolver actionResolver;
    private Actor parentView;


    private State parentState;

    private  UpdateCoinCountInterface updateCoinCountInterface;
    public CoinShopState(GameStateManager gsm, ActionResolver actionResolver,Actor parentView) {


        this.updateCoinCountInterface = updateCoinCountInterface;
        this.actionResolver = actionResolver;
        this.parentView = parentView;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        cnr_line = AssetsManager.getTextureRegion(Constants.CNR_LINE_ID).getTexture();
        backgroung_texture = AssetsManager.getTextureRegion(Constants.BACK_TILE_ID).getTexture();
        backgroung_image = new Image(backgroung_texture);
        backgroung_image.setBounds(0, 0, GameRuners.WIDTH / 2, GameRuners.HEIGHT / 2);
        addActor(backgroung_image);
        image = new Image(cnr_line);
        image.setBounds(0, GameRuners.HEIGHT / 2 - 70, GameRuners.WIDTH / 2, 80);
        sequenceReturn = new SequenceAction();
        this.gsm = gsm;
        garageNameTexture = AssetsManager.getTextureRegion(Constants.COIN_SHOP_NAME_ID).getTexture();
        garageNameImage = new Image(garageNameTexture);
        garageNameImage.setBounds(5, GameRuners.HEIGHT / 2 - 40, garageNameTexture.getWidth(), garageNameTexture.getHeight());


        setUpCoinShop();
        setUpBackButton();

//        setUpBackButton();
//        setUpStartButton();
        addActor(image);
        addActor(garageNameImage);
        setUpImageCoinCount();
        setUpCoinCountLabel();
        setUpResumeImageButton();


    }

    public void setUpResumeImageButton()
    {
        resumeImageButton = new ImageButton(new Image(AssetsManager.getTextureRegion(Constants.RESUME_BTTN_ID)).getDrawable(),new Image(AssetsManager.getTextureRegion(Constants.RESUME_BTTN_ID)).getDrawable());
        resumeImageButton.setBounds(GameRuners.WIDTH / 4-40,GameRuners.HEIGHT / 4-282,resumeImageButton.getWidth(),resumeImageButton.getHeight());
        resumeImageButton.addListener(new ClickListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                gsm.push(new GameState(gsm, false, true, actionResolver));
                return true;
            }
        });
        addActor(resumeImageButton);
    }


    public void setUpImageCoinCount() {
        imageButton = new ImageButton(new Image(AssetsManager.getTextureRegion(Constants.COIN_ICON_1_NAME_ID)).getDrawable());
        //imageButton.setBounds(labelCoinCount.getX() + 50, labelCoinCount.getY() - 2, imageButton.getWidth(), imageButton.getHeight());
        imageButton.setBounds(GameRuners.WIDTH / 2 - 25, GameRuners.HEIGHT / 2 - 30, imageButton.getWidth(), imageButton.getHeight());
        addActor(imageButton);
    }

    public State getParentState() {
        return parentState;
    }

    public void setParentState(State parentState) {
        this.parentState = parentState;
    }


    public void setUpCoinCountLabel() {
        labelCoinCount = new Label(String.valueOf(GameManager.getCoinCounter()), AssetsManager.getUiSkin());
        labelCoinCount.setFontScale(0.60f, 0.60f);
        labelCoinCount.setBounds(imageButton.getX() - labelCoinCount.getPrefWidth() - 5, imageButton.getY(), labelCoinCount.getWidth(), labelCoinCount.getHeight());
        //labelCoinCount.setBounds(imageButton.getX() - 45, imageButton.getY(), labelCoinCount.getWidth(), labelCoinCount.getHeight());
        addActor(labelCoinCount);
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
                       if(parentView!=null) {
                           ((UpdateCoinCountInterface)parentView).updateCoinCountView();
                           getStage().addActor(parentView);
                       }

                        else{
                           ((UpdateCoinCountInterface)parentState).updateCoinCountView();
                           remove();}
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


    public void setUpCoinShop() {
        container = new Table();
        table = new Table();

        addActor(container);
        ArrayList<CoinShopItem> coinShopItems = new ArrayList<CoinShopItem>();
        CoinShop coinShop = new CoinShop(1, 0.99f, 500, GameManager.getStrings().get(Constants.CM_COINS_PACK_FIRST_LINE), 0, Constants.COIN_SHOP_STANDART_ID);
        CoinShop coinShop2 = new CoinShop(2, 2.99f, 1950, GameManager.getStrings().get(Constants.CM_MEDIUM_COINS_PACK_FIRST_LINE) + "\n        " + GameManager.getStrings().get(Constants.CM_MEDIUM_COINS_PACK_SECOND_LINE), 30, Constants.COIN_SHOP_2_ID);
        CoinShop coinShop3 = new CoinShop(3, 4.99f, 3750, GameManager.getStrings().get(Constants.CM_BIG_COINS_PACK_FIRST_LINE) + "\n        " + GameManager.getStrings().get(Constants.CM_BIG_COINS_PACK_SECOND_LINE), 50, Constants.COIN_SHOP_3_ID);
        CoinShop coinShop4 = new CoinShop(4, 9.99f, 10000, GameManager.getStrings().get(Constants.CM_GREAT_COINS_PACK_FIRST_LINE)+"\n        "+GameManager.getStrings().get(Constants.CM_GREAT_COINS_PACK_SECOND_LINE), 100, Constants.COIN_SHOP_4_ID);
        coinShopItems.add(new CoinShopItem(coinShop, 1,actionResolver,this));
        CoinShopAdapter garageAdapter = new CoinShopAdapter(table, coinShopItems);
        table.add(new CoinShopItem(coinShop, 1,actionResolver,this)).row();
        table.add(new CoinShopItem(coinShop2, 1,actionResolver,this)).row();
        table.add(new CoinShopItem(coinShop3, 1,actionResolver,this)).row();
        table.add(new CoinShopItem(coinShop4, 1,actionResolver,this)).row();
        table.top();
        table.padTop(3f);
        garageAdapter.loadTableData();

        scrollPane2 = new ScrollPane(table);
        scrollPane2.setBounds(0, 0, GameRuners.WIDTH / 2, GameRuners.HEIGHT / 2 - 70);
        scrollPane2.setScrollingDisabled(true, false);
        scrollPane2.setOverscroll(false, false);
        scrollPane2.invalidate();
        addActor(scrollPane2);


    }


    @Override
    public void resumeButtonOnResume() {

    }

    @Override
    public void onSaveMe() {

    }


    @Override
    public void updateTable() {
        table.remove();
        setUpCoinShop();

    }

    @Override
    public void setSelectedItme(int index) {

    }

    @Override
    public void updateCoinCountView() {
        labelCoinCount.setText(String.valueOf(GameManager.getCoinCounter()));
        labelCoinCount.invalidate();

        labelCoinCount.setBounds(imageButton.getX() - labelCoinCount.getPrefWidth() -5, imageButton.getY(), labelCoinCount.getWidth(), labelCoinCount.getHeight());

//        labelCoinCount.notify();
    }
}
