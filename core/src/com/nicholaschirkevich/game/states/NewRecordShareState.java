package com.nicholaschirkevich.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.IdentityMap;
import com.badlogic.gdx.utils.ScreenUtils;
import com.nicholaschirkevich.game.admob.ActionResolver;
import com.nicholaschirkevich.game.menu.NewRecordScreenshotTotal;
import com.nicholaschirkevich.game.menu.RecordRectangle;
import com.nicholaschirkevich.game.util.AssetsManager;
import com.nicholaschirkevich.game.util.Constants;
import com.nicholaschirkevich.game.util.GameManager;

import java.util.ArrayList;

/**
 * Created by Колян on 14.09.2016.
 */
public class NewRecordShareState extends Group {
    private GameStateManager gsm;
    private ActionResolver actionResolver;
    private byte[] pixels;
    private Pixmap pixmap;


    public NewRecordShareState(GameStateManager gsm, ActionResolver actionResolver, byte[] pixels, int width, int height) {
        this.gsm = gsm;
        this.actionResolver = actionResolver;
        this.pixels = pixels;


        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        BufferUtils.copy(pixels, 0, pixmap.getPixels(), pixels.length);
        Texture texture = new Texture(pixmap);
        //PixmapIO.writePNG(Gdx.files.external("mypixmap.png"), pixmap);
        pixmap.dispose();

        Image image = new Image(texture);

        image.setPosition(40, 175);
        image.setWidth(240);
        image.setHeight(280);
        addActor(new NewRecordScreenshotTotal());
        addActor(image);
        setUpBackButton();
        setUpConuses();
        setUpSrLogo();
    }

    public void setUpBackButton() {
        Texture upTexture, downTexture;
        upTexture = AssetsManager.getTextureRegion(Constants.BUTTON_BACK_ID).getTexture();
        downTexture = AssetsManager.getTextureRegion(Constants.BUTTON_BACK_PRSSD_ID).getTexture();
        upTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        downTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        ImageButton back_button = new ImageButton((new Image(upTexture)).getDrawable(),((new Image(downTexture)).getDrawable()));
        back_button.setPosition(1, 465);
        addActor(back_button);
    }

    public void setUpConuses() {
        int posX = 90, posY = 180;
        ArrayList<Image> conuses = new ArrayList<Image>();
        for (int i = 0; i < 4; i++) {
            Image conus = new Image(AssetsManager.getTextureRegion(Constants.CONE_ID));
            conus.setPosition(posX,posY);
            posX+=45;
            posY+=3;
            addActor(conus);
        }

    }


    public void setUpSrLogo()
    {
        Image image = new Image(AssetsManager.getTextureRegion(Constants.MAIN_MENU_LOGO_ID).getTexture());
        image.setPosition(80,95);
        image.setScale(0.6f,0.6f);
        addActor(image);
    }
}
