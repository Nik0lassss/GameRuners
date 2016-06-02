package com.nicholaschirkevich.game.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.XmlReader;
import com.nicholaschirkevich.game.entity.BrakeLines;
import com.nicholaschirkevich.game.entity.BrokenTextures;
import com.nicholaschirkevich.game.entity.Car;
import com.nicholaschirkevich.game.entity.CarsType;
import com.nicholaschirkevich.game.entity.GearShift;
import com.nicholaschirkevich.game.entity.LeftRocketPosition;
import com.nicholaschirkevich.game.entity.NormalTextures;
import com.nicholaschirkevich.game.entity.RightRocketPosition;
import com.nicholaschirkevich.game.util.Constants;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Nikolas on 10.03.2016.
 */
public class XmlHelper {
    public static ArrayList<GearShift> getShifts() {
        ArrayList<GearShift> gearShifts = new ArrayList<GearShift>();
        XmlReader.Element root = getRoot(Constants.GEAR_SHIFT_FILE_PATH);
        XmlReader.Element carsElement = root.getChildByName(Constants.GEAR_SHIFT_FILE_CAR_GEARS);
        for (int i = 0; i < carsElement.getChildCount(); i++) {
            GearShift gearShift = new GearShift();
            XmlReader.Element speedsElement = carsElement.getChild(i).getChildByName(Constants.GEAR_SHIFT_FILE_SPEED_KEY);
            XmlReader.Element timesElement = carsElement.getChild(i).getChildByName(Constants.GEAR_SHIFT_FILE_TIME_KEY);
            for (int z = 0; z < speedsElement.getChildCount(); z++) {
                //System.out.println(speedsElement.getChild(z).getText());
                gearShift.getSpeeds().add(Integer.valueOf(speedsElement.getChild(z).getText()));
            }
            for (int n = 0; n < timesElement.getChildCount(); n++) {
                gearShift.getTimes().add(Integer.valueOf(timesElement.getChild(n).getText()));
            }
            gearShifts.add(gearShift);
        }
        return gearShifts;
    }

    public static HashMap<String, String> getStringsRus() {
        HashMap<String, String> strings = new HashMap<String, String>();
        XmlReader.Element root = getRoot(Constants.STRINGS_FILE_PATH);
        //XmlReader.Element stringElement = root.getChildByName(Constants.STRINGS_KEY);
        for (int i = 0; i < root.getChildCount(); i++) {
            XmlReader.Element element = root.getChild(i);
           XmlReader.Element child = element.getChildByName(Constants.Localization_ID);
           XmlReader.Element valueRu = element.getChildByName(Constants.Localization_RU);
            strings.put(child.getText(), valueRu.toString().replace("<ru>\n\t","  ").replace("\n</ru>","").replace("\\n","\n"));
        }
        return strings;
    }

    public static HashMap<String, String> getStringsEn() {
        HashMap<String, String> strings = new HashMap<String, String>();
        XmlReader.Element root = getRoot(Constants.STRINGS_FILE_PATH);
        //XmlReader.Element stringElement = root.getChildByName(Constants.STRINGS_KEY);
        for (int i = 0; i < root.getChildCount(); i++) {
            XmlReader.Element element = root.getChild(i);
            XmlReader.Element child = element.getChildByName(Constants.Localization_ID);
            XmlReader.Element valueEn = element.getChildByName(Constants.Localization_EN);
            strings.put(child.getText(),valueEn.toString().replace("<en>\n\t","  ").replace("\n</en>","").replace("\\n","\n"));
        }
        return strings;
    }

    private static XmlReader.Element getRoot(String fileName) {
        XmlReader xmlReader = new XmlReader();
        FileHandle file = Gdx.files.internal(fileName);
        XmlReader.Element root = null;
        try {
            root = xmlReader.parse(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }

    public static ArrayList<CarsType> getCars() {
        ArrayList<CarsType> carsTypes = new ArrayList<CarsType>();
        XmlReader.Element root = getRoot(Constants.CARS_TYPE_FILE_PATH);
        for (int i = 0; i < root.getChildCount(); i++) {
            CarsType carsType = new CarsType();
            XmlReader.Element carsTypeXml = root.getChild(i);
            carsType.setName(carsTypeXml.getChildByName(Constants.CARS_TYPE_NAME).getText());
            carsType.setTitleImage(carsTypeXml.getChildByName(Constants.CARS_TYPE_TITLE_IMAGE).getText());
            carsType.setTitleImageName(carsTypeXml.getChildByName(Constants.CARS_TYPE_TITLE_IMAGE_NAME).getText());
            XmlReader.Element carsArrayXml = carsTypeXml.getChildByName(Constants.CARS_TYPE_CARS);
            ArrayList<Car> carArrayList = new ArrayList<Car>();
            for (int z = 0; z < carsArrayXml.getChildCount(); z++) {
                XmlReader.Element carXml = carsArrayXml.getChild(z);
                Car car = new Car();
                car.setName(carXml.getChildByName(Constants.CARS_TYPE_CAR_NAME).getText());
                car.setCarNameText(carXml.getChildByName(Constants.CARS_TYPE_CAR_NAME_TEXT).getText());
                car.setMapType(carXml.getChildByName(Constants.CARS_TYPE_CAR_MAP_TYPE).getText());
                car.setSpeed(carXml.getChildByName(Constants.CARS_TYPE_CAR_SPEED).getText());
                car.setWeight(carXml.getChildByName(Constants.CARS_TYPE_CAR_WEIGHT).getText());
                car.setCurveType(carXml.getChildByName(Constants.CARS_TYPE_CAR_CURVE_TYPE).getText());
                car.setBrakeDuration(carXml.getChildByName(Constants.CARS_TYPE_CAR_BRAKE_DURATION).getText());
                car.setRareStatus(carXml.getChildByName(Constants.CARS_TYPE_CAR_RARE_STATUS).getText());
                car.setPosition(carXml.getChildByName(Constants.CARS_TYPE_CAR_POSITION).getText());
                car.setIsForRealMoney(carXml.getChildByName(Constants.CARS_TYPE_CAR_IS_FOR_REAL_MONEY).getText());
                car.setPossability(carXml.getChildByName(Constants.CARS_TYPE_CAR_POSSABILITY).getText());

                XmlReader.Element brakeLinesXml = carXml.getChildByName(Constants.CARS_TYPE_CAR_BRAKE_LINES);
                BrakeLines brakeLines = new BrakeLines();
                brakeLines.setLeftLineStart(brakeLinesXml.getChildByName(Constants.CARS_TYPE_CAR_BRAKE_LINES_LEFT_LINE_START).getText());
                brakeLines.setRightLineStart(brakeLinesXml.getChildByName(Constants.CARS_TYPE_CAR_BRAKE_LINES_RIGHT_LINE_START).getText());
                brakeLines.setWeightOfLine(brakeLinesXml.getChildByName(Constants.CARS_TYPE_CAR_BRAKE_LINES_WEIGHT_OF_LINE).getText());

                car.setBrakeLines(brakeLines);

                XmlReader.Element leftRocketPositionXml = carXml.getChildByName(Constants.CARS_TYPE_CAR_BRAKE_LEFT_ROCKET_POSTITION);
                LeftRocketPosition leftRocketPosition = new LeftRocketPosition();
                leftRocketPosition.setX(leftRocketPositionXml.getChildByName(Constants.CARS_X).getText());
                leftRocketPosition.setY(leftRocketPositionXml.getChildByName(Constants.CARS_X).getText());
                car.setLeftRocketPosition(leftRocketPosition);

                XmlReader.Element rightRocketPositionXml = carXml.getChildByName(Constants.CARS_TYPE_CAR_BRAKE_RIGHT_ROCKET_POSTITION);
                RightRocketPosition rightRocketPosition = new RightRocketPosition();
                rightRocketPosition.setX(rightRocketPositionXml.getChildByName(Constants.CARS_X).getText());
                rightRocketPosition.setY(rightRocketPositionXml.getChildByName(Constants.CARS_Y).getText());
                car.setRightRocketPosition(rightRocketPosition);

                car.setID(carXml.getChildByName(Constants.CARS_TYPE_CAR_ID).getText());
                car.setStatus(carXml.getChildByName(Constants.CARS_TYPE_CAR_STATUS).getText());
                car.setPrice(carXml.getChildByName(Constants.CARS_TYPE_CAR_PRICE).getText());

                XmlReader.Element normalTextureXml = carXml.getChildByName(Constants.CARS_TYPE_CAR_NORMAL_TEXTURES);
                NormalTextures normalTextures = new NormalTextures();
                for (int x = 0; x < normalTextureXml.getChildCount(); x++) {
                    normalTextures.add(normalTextureXml.getChild(x).getText());
                }
                car.setNormalTextures(normalTextures);

                XmlReader.Element brokenTexturesXml = carXml.getChildByName(Constants.CARS_TYPE_CAR_BROKEN_TEXTURES);
                BrokenTextures brokenTextures = new BrokenTextures();
                if (brokenTexturesXml != null)
                    for (int n = 0; n < brokenTexturesXml.getChildCount(); n++) {
                        brokenTextures.add(brokenTexturesXml.getChild(n).getText());
                    }
                car.setBrokenTextures(brokenTextures);

                car.setCarNameImage(carXml.getChildByName(Constants.CARS_TYPE_CAR_NAME_IMEGE).getText());
                carArrayList.add(car);
            }
            carsType.setCars(carArrayList);
            carsTypes.add(carsType);

        }

        return carsTypes;
    }
}
