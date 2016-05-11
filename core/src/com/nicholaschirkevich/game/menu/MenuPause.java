package com.nicholaschirkevich.game.menu;

/**
 * Created by Nikolas on 20.04.2016.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.nicholaschirkevich.game.GameRuners;
import com.nicholaschirkevich.game.interfaces.ResumeFromPause;
import com.nicholaschirkevich.game.states.GameState;
import com.nicholaschirkevich.game.states.GameStateManager;
import com.nicholaschirkevich.game.util.Constants;

/**
 * Created by Nikolas on 10.03.2016.
 */
public class MenuPause extends Group {

    Skin uiSkin = new Skin(Gdx.files.internal("uiskin_digit.json"));
    TextButton resumeFromPause, exitButton;

    Texture slot_vehicle;
    Label playLabel, exitLabel;
    Image background;
    Image resumeButtonUpImage, resumeButtonDownImage, exitButtonImageUp, exitButtonImageDown;
    Texture resumeButtonUpTexture, resumeButtonDownTexture, exitButtonTextureUp, exiteButtonTextureDown;
    TextureRegion saveMeImageTextureRegion;
    SequenceAction resumeSequence, exitSequence;
    float time = 0;
    float x = Constants.SAVE_ME_BAR_X_VISIBLE, y = Constants.SAVE_ME_BAR_Y_VISIBLE;
    private GameStateManager gsm;
    ResumeFromPause resumeFromPauseInterface;


    public MenuPause(ResumeFromPause resumeFromPause, GameStateManager gsm) {

        resumeButtonUpTexture = new Texture("bttn_resume.png");
        resumeButtonDownTexture = new Texture("bttn_resume_prssd.png");
        exitButtonTextureUp = new Texture("bttn_exit.png");
        exiteButtonTextureDown = new Texture("bttn_exit_prssd.png");

        exitButtonImageDown = new Image(exiteButtonTextureDown);
        exitButtonImageUp = new Image(exitButtonTextureUp);


        resumeButtonUpImage = new Image(resumeButtonUpTexture);
        resumeButtonDownImage = new Image(resumeButtonDownTexture);
        resumeSequence = new SequenceAction();
        exitSequence = new SequenceAction();
        setUpBackgroung(false);
        setUpResumeButton();
        setUpExitButton();
        this.resumeFromPauseInterface = resumeFromPause;

        this.gsm = gsm;
        setBounds(0, 0, GameRuners.WIDTH / 2, GameRuners.HEIGHT / 2);
    }

    public void setUpResumeButton() {
        float x = Constants.RESUME_PAUSE_BTTN_X_VISIBLE, y = Constants.RESUME_PAUSE_BTTN_Y_VISIBLE;
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.down = resumeButtonDownImage.getDrawable();
        textButtonStyle.up = resumeButtonUpImage.getDrawable();
        textButtonStyle.font = uiSkin.getFont("default-font");

        playLabel = new Label("Play", uiSkin);


        resumeFromPause = new TextButton("", textButtonStyle);
        resumeFromPause.addListener(new ClickListener() {

                                        @Override
                                        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                                            resumeSequence.addAction(Actions.delay(0.3f));
                                            resumeSequence.addAction(new Action() {
                                                @Override
                                                public boolean act(float delta) {
                                                    resumeFromPauseInterface.resumeFromPause();
                                                    return true;
                                                }
                                            });
                                            resumeSequence.addAction(Actions.removeActor());
                                            addAction(resumeSequence);

                                            return true;
                                        }
                                    }
        );
        resumeFromPause.getLabel().setFontScale(0.4f, 0.4f);
        resumeFromPause.setBounds(x - resumeFromPause.getWidth() / 2, y - resumeFromPause.getHeight() / 2, resumeFromPause.getWidth(), resumeFromPause.getHeight());
        playLabel.setX(resumeFromPause.getX() + 15);
        playLabel.setY(resumeFromPause.getY() - 25);
        playLabel.setFontScale(0.5f, 0.5f);
        addActor(resumeFromPause);
        addActor(playLabel);
    }

    public void setUpExitButton() {
        float x = Constants.EXIT_PAUSE_BTTN_X_VISIBLE, y = Constants.EXIT_PAUSE_BTTN_Y_VISIBLE;
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.down = exitButtonImageDown.getDrawable();
        textButtonStyle.up = exitButtonImageUp.getDrawable();
        textButtonStyle.font = uiSkin.getFont("default-font");
        exitLabel = new Label("Exit", uiSkin);

        exitButton = new TextButton("", textButtonStyle);
        exitButton.addListener(new ClickListener() {

                                   @Override
                                   public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                                       exitSequence.addAction(Actions.delay(0.3f));
                                       exitSequence.addAction(new Action() {
                                           @Override
                                           public boolean act(float delta) {
                                               gsm.set(new GameState(gsm, false, false));
                                               return true;
                                           }
                                       });
                                       exitSequence.addAction(Actions.removeActor());
                                       addAction(exitSequence);

                                       return true;
                                   }
                               }
        );
        exitButton.getLabel().setFontScale(0.4f, 0.4f);
        exitButton.setBounds(x - exitButton.getWidth() / 2, y - exitButton.getHeight() / 2, exitButton.getWidth(), exitButton.getHeight());

        exitLabel.setX(exitButton.getX()+15);
        exitLabel.setY(exitButton.getY()-25);
        exitLabel.setFontScale(0.5f,0.5f);
        addActor(exitButton);
        addActor(exitLabel);
    }


    @Override
    public void act(float delta) {

        super.act(delta);


    }

    private void setUpBackgroung(boolean selected) {
        if (selected) {
            slot_vehicle = new Texture("pause_background_tile.png");
        } else slot_vehicle = new Texture("pause_background_tile.png");
        background = new Image(slot_vehicle);
        background.setBounds(0, -20, GameRuners.WIDTH / 2, GameRuners.HEIGHT / 2 + 50);
        background.addListener(new ClickListener() {

                                   @Override
                                   public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                                       remove();
                                       return true;
                                   }
                               }
        );
        addActor(background);

    }

}
