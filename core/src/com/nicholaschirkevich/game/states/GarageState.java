package com.nicholaschirkevich.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.nicholaschirkevich.game.GameRuners;
import com.nicholaschirkevich.game.entity.CarsType;
import com.nicholaschirkevich.game.interfaces.ResumeButtonListener;
import com.nicholaschirkevich.game.interfaces.UpdateGarageTable;
import com.nicholaschirkevich.game.menu.BackButton;
import com.nicholaschirkevich.game.menu.StartGameGarageButton;
import com.nicholaschirkevich.game.util.Constants;
import com.nicholaschirkevich.game.util.GameManager;
import com.nicholaschirkevich.game.view_adapters.GarageAdapter;

import java.util.ArrayList;


/**
 * Created by Nikolas on 10.03.2016.
 */
public class GarageState extends State implements ResumeButtonListener, UpdateGarageTable {
    OrthographicCamera camera;

    Stage stage;
    Texture cnr_line, garageNameTexture;
    Image image, garageNameImage;
    ScrollPane scrollPane2;
    BackButton backButton;
    StartGameGarageButton startGameGarageButton;
//    ScrollPane scrollPane;

    //container is new
    Table table, container;

    Texture texture1, texture2, texture3, texture4, texture5, texture6;
    ImageButton button1, button2, button3, button4, button5, button6;


    public GarageState(GameStateManager gsm) {
        super(gsm);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        ArrayList<CarsType> carsTypes = GameManager.getCarsTypes();
        stage = new Stage(new StretchViewport(GameRuners.WIDTH / 2, GameRuners.HEIGHT / 2));
        cnr_line = new Texture("cnr_line.png");
        image = new Image(cnr_line);
        image.setBounds(0, GameRuners.HEIGHT / 2 - 80, GameRuners.WIDTH / 2, 80);


        garageNameTexture = new Texture("title_vehicles.png");
        garageNameImage = new Image(garageNameTexture);
        garageNameImage.setBounds(20, GameRuners.HEIGHT / 2 - 40, garageNameTexture.getWidth(), garageNameTexture.getHeight());

        //Add

//        container = new Table();
//
//        table = new Table();
//
//
//        stage.addActor(container);

        //container.setFillParent(true);

//        final ScrollPane scrollPane = new ScrollPane(table);
//        Skin uiSkin = new Skin(Gdx.files.internal("uiskin.json"));
//        Label label = new Label("test", uiSkin);
//        float myPadTop = Gdx.graphics.getHeight() / 2 - scrollPane.getHeight() / 2;
//        float myPadBottom = Gdx.graphics.getHeight() / 2 - scrollPane.getHeight() / 2;
//
//        scrollPane.setFlickScroll(true);
//
//        container.add(scrollPane).padTop(0).padBottom(0).padRight(10).padLeft(10);
//
//        table.pad(10).defaults().expandX().space(4);

        //

//        texture1 = new Texture(Gdx.files.internal("car_f1_1_1.png"));
//        texture2 = new Texture(Gdx.files.internal("car_f1_1_1.png"));
//        texture3 = new Texture(Gdx.files.internal("car_f1_1_1.png"));
//        texture4 = new Texture(Gdx.files.internal("car_f1_1_1.png"));
//        texture5 = new Texture(Gdx.files.internal("car_f1_1_1.png"));
//        texture6 = new Texture(Gdx.files.internal("car_f1_1_1.png"));
//
//        button1 = new ImageButton(new Image(texture1).getDrawable());
//        button2 = new ImageButton(new Image(texture2).getDrawable());
//        button3 = new ImageButton(new Image(texture3).getDrawable());
//        button4 = new ImageButton(new Image(texture4).getDrawable());
//        button5 = new ImageButton(new Image(texture5).getDrawable());
//        button6 = new ImageButton(new Image(texture6).getDrawable());

//        for (int i = 0; i < carsTypes.size(); i++) {
//            CarsType carsType = carsTypes.get(i);
//            for (int z = 0; z < carsType.getCars().size(); z++) {
//                Car car = carsType.getCars().get(z);
//               // CarGarageItem item = new CarGarageItem(car);
//                //item.addListener(new CarGarageItemClickListener(car.getID(),this));
//                table.add(new CarGarageItem(car)).row();
//            }
//        }


        //table.setFillParent(true);//Remove
        //table.defaults().width(Gdx.graphics.getWidth()/2.5f).height(Gdx.graphics.getHeight()/(8*Gd//x.graphics.getHeight()/Gdx.graphics.getWidth()));//Remove
//
//        table.add(button1).row();
//        table.add(button2).row();
//        table.add(button3).row();
//        table.add(button4).row();
//        table.add(button5).row();
//        table.add(button6).row();
//        table.add(new ResumeButton(200, 200, 200, 200, this));
//        table.add(new ResumeButton(200, 200, 200, 200, this));
//        table.add(new ResumeButton(200, 200, 200, 200, this));
//        table.add(label).row();
//        table.add(new CarGarageItem()).row();
//        table.add(new CarGarageItem()).row();
//        table.add(new CarGarageItem()).row();
//        table.add(new CarGarageItem()).row();
//        table.add(new CarGarageItem()).row();
//        table.add(new CarGarageItem()).row();
//        table.add(new CarGarageItem()).row();
//        table.add(new CarGarageItem()).row();
//        ScrollPane scrollPane2 = new ScrollPane(table);
//        scrollPane2.setBounds(0, 0, GameRuners.WIDTH / 2, GameRuners.HEIGHT / 2 - 70);
//        scrollPane2.setScrollingDisabled(true, false);
//        scrollPane2.setOverscroll(false, false);
//        scrollPane2.invalidate();
//        stage.addActor(scrollPane2);
//        stage.addActor(image);
//        stage.addActor(garageNameImage);
        //ArrayList<CarsType> carsTypes = GameManager.getCarsTypes();
        //table.add(button6);
        //scrollPane.setX(Gdx.graphics.getWidth()/2-scrollPane.getWidth()/2);//Remove
        //scrollPane.setY(Gdx.graphics.getHeight()/2-scrollPane.getHeight()/2);//Remove

//        FileHandle handle = Gdx.files.classpath("gear_shift.xml");
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder builder = null;
//        try {
//            builder = factory.newDocumentBuilder();
//        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
//        }
//        Document document = null;
//        try {
//
//            document = builder.parse(handle.file());
//        } catch (SAXException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Element root = document.getDocumentElement();
//
//        Element message = (Element) root.getElementsByTagName("car_gear").item(0);
//
//       // Element message = (Element) root.getChildNodes().getLength();
////        message.getElementsByTagName("car_gear").item(0);
//        //String textContent = message.getTextContent(); // тоже для упрощения
//        System.out.println( message.getTextContent());
//        XmlReader xmlReader = new XmlReader();
//        FileHandle file = Gdx.files.internal("gear_shift.xml");
//        XmlReader.Element root = null;
//        try {
//            root = xmlReader.parse(file);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        //String name = root.getAttribute("name");
//        XmlReader.Element tasksElement = root.getChildByName("car_gears");
//        int taskCount = tasksElement.getChildCount();
//        for(int i = 0;i<taskCount;i++)
//        {
//            XmlReader.Element element = tasksElement.getChild(i);
//            XmlReader.Element speedElement=  element.getChildByName("speeds");
//           for(int z=0;z<element.getInt("speed");z++)
//           {
//
//           }
//            XmlReader.Element timesElement=  element.getChildByName("times");
//        }

//        ArrayList<GearShift> gearShifts = XmlHelper.getShifts();
//        System.out.println(gearShifts.size());
//
//        button1.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                System.out.println("Test");
//            }
//        });
        setUpGarage();
        setUpBackButton();
        setUpStartButton();
        stage.addActor(image);
        stage.addActor(garageNameImage);

        Gdx.input.setInputProcessor(stage);
    }

    public void setUpBackButton() {

        float width = 43, height = 49;
        backButton = new BackButton(Constants.GARAGE_BTTN_X_VISIBLE, Constants.GARAGE_BTTN_Y - (height / 2), width, height,gsm);
        stage.addActor(backButton);
    }

    public void setUpStartButton()
    {
        float x = Constants.RESUME_BTTN_X_VISIBLE, y = Constants.RESUME_BTTN_Y_VISIBLE-190, width = 70, height = 55;
        startGameGarageButton = new StartGameGarageButton(x - (width / 2), y - (height / 2), width, height,gsm);
        stage.addActor(startGameGarageButton);
    }

    public void setUpGarage() {
        container = new Table();
        table = new Table();
        stage.addActor(container);
        ArrayList<CarsType> carsTypes = GameManager.getCarsTypes();
        GarageAdapter garageAdapter = new GarageAdapter(table, carsTypes);
        garageAdapter.loadTableData();
//        for (int i = 0; i < carsTypes.size(); i++) {
//            CarsType carsType = carsTypes.get(i);
//            for (int z = 0; z < carsType.getCars().size(); z++) {
//                Car car = carsType.getCars().get(z);
//                // CarGarageItem item = new CarGarageItem(car);
//                //item.addListener(new CarGarageItemClickListener(car.getID(),this));
//                table.add(new CarGarageItem(car, this)).row();
//            }
//        }
        scrollPane2 = new ScrollPane(table);
        scrollPane2.setBounds(0, 0, GameRuners.WIDTH / 2, GameRuners.HEIGHT / 2 - 70);
        scrollPane2.setScrollingDisabled(true, false);
        scrollPane2.setOverscroll(false, false);
        scrollPane2.invalidate();
        stage.addActor(scrollPane2);


    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
//        Gdx.gl.glClearColor(0.281f, 0.602f, 0.844f, 0);
        Gdx.gl.glClearColor(0.098f, 0.655f, 0.976f, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }


    @Override
    public void dispose() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

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
        setUpGarage();
//        table.remove();
//        stage.addActor(scrollPane2);
    }

    @Override
    public void setSelectedItme(int index) {

    }
}
