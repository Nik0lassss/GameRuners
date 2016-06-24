package com.nicholaschirkevich.game.actions;

import com.badlogic.gdx.physics.box2d.World;
import com.nicholaschirkevich.game.model.boosters.*;
import com.nicholaschirkevich.game.model.boosters.Coin;
import com.nicholaschirkevich.game.model.boosters.Skull;
import com.nicholaschirkevich.game.util.RandomUtil;

import java.util.ArrayList;

/**
 * Created by Nikolas on 14.03.2016.
 */
public class Prize {
    private static float time;
    private static float timeToGeneratePrize = 12;

    public static void resetTime() {
        time = 0;
    }


    public static void updatePrize(World world, com.badlogic.gdx.graphics.Camera camera, ArrayList<com.nicholaschirkevich.game.model.boosters.Coin> coins, ArrayList<Skull> skulls, ArrayList<Ladle> ladles, ArrayList<Booster> boosters, ArrayList<Springboard> springboards, ArrayList<FlySpringboard> flySpringboards, ArrayList<Dirt> dirts, float dt, float posYLastCar, boolean isLeftLastCar) {
        time += dt;
        if (time > timeToGeneratePrize) {
           if (RandomUtil.getNoRandomBoolean()) {
                System.out.println("prize");
               int pos = 0;
               for (int i = 0; i < 4; i++) {
                   if(i==0)coins.add(new Coin(world, 90, (int) posYLastCar + 270 + pos, 10,isLeftLastCar));
                   else coins.add(new Coin(world, 90, (int) posYLastCar + 270 + pos, 10,coins.get(i-1).isLeft()));
                   pos += 30;

               }
               // springboards.add(new Springboard(world,90,(int)posYLastCar+270,10));
                //skulls.add(new Skull(world, 90, (int) posYLastCar + 270, 10, !isLeftLastCar));
               //ladles.add(new Ladle(world, 90,(int) posYLastCar + 290, 10,isLeftLastCar));
               //flySpringboards.add(new FlySpringboard(world, 90,(int) posYLastCar + 270, 10,isLeftLastCar));
                 //boosters.add(new Booster(world, 90,(int) posYLastCar + 650, 10,!isLeftLastCar));
                // dirts.add(new Dirt(world,90,(int)posYLastCar+270,10,isLeftLastCar));
            } else {
               // dirts.add(new Dirt(world,90,(int)posYLastCar+270,10,isLeftLastCar));
               //springboards.add(new Springboard(world,90,(int)posYLastCar+270,10));
               //skulls.add(new Skull(world, 90, (int) posYLastCar + 270, 10, !isLeftLastCar));
                 // ladles.add(new Ladle(world, 90,(int) posYLastCar + 270, 10,isLeftLastCar));
               //flySpringboards.add(new FlySpringboard(world, 90,(int) posYLastCar + 290, 10,isLeftLastCar));
               //boosters.add(new Booster(world, 90,(int) posYLastCar + 650, 10,!isLeftLastCar));
                switch (RandomUtil.getNoRand(1, 5)) {
                    case 1:
                        ladles.add(new Ladle(world, 90, (int) posYLastCar + 290, 10,isLeftLastCar));
                        break;
                    case 2:
                        boosters.add(new Booster(world, 90,(int) posYLastCar + 290, 10,isLeftLastCar));
                        break;
                    case 3:
                        skulls.add(new Skull(world, 90, (int) posYLastCar + 290, 10,isLeftLastCar));
                       break;
                    case 4:
                        dirts.add(new Dirt(world,90,(int)posYLastCar+290,10,isLeftLastCar));
                        break;
                    case 5:
                        flySpringboards.add(new FlySpringboard(world, 90,(int) posYLastCar + 270, 10,isLeftLastCar));
                        break;


               }
////
//
//                System.out.println("else prize");
          }
//

            time = 0;
        }
    }
}
