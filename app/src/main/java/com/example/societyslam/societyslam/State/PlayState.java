package com.example.societyslam.societyslam.State;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.societyslam.societyslam.Game.Assets;
import com.example.societyslam.societyslam.GameObjects.Card;
import com.example.societyslam.societyslam.GameObjects.Deck;
import com.example.societyslam.societyslam.GameObjects.EnergyCard;
import com.example.societyslam.societyslam.GameObjects.Level;
import com.example.societyslam.societyslam.GameObjects.SocietyCard;
import com.example.societyslam.societyslam.GameObjects.Type;
import com.example.societyslam.societyslam.R;
import com.example.societyslam.societyslam.State.State;
import com.example.societyslam.societyslam.Util.Painter;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Aoife Brown on 21/11/2016.
 */

public class PlayState extends State implements View.OnClickListener {



    private ImageButton drawButton;
    private ImageView cardImage;
    private SocietyCard computerSociety = new SocietyCard("Computer Society", 0, 0, 3, 2, Assets.computerSociety, 100, "Virus Strike", 1, 30, Type.electric, Type.water, null, 2, Level.Basic);
    private SocietyCard boxingSociety = new SocietyCard("Boxing Society", 0, 0, 3, 2, Assets.boxingSociety, 120, "Force Punce", 1, 40, Type.fighting, Type.electric, Type.water, 2, Level.Basic);
    private SocietyCard rowingSociety = new SocietyCard("Rowing Society", 0, 0, 3, 2, Assets.rowingSociety, 100, "Paddle Pound", 1, 20, Type.water, Type.electric, Type.fighting, 1, Level.Basic);
    private EnergyCard waterEnergy = new EnergyCard("Water", 0, 0, 3, 2, Assets.waterEnergy, Type.water);
    private EnergyCard electricEnergy = new EnergyCard("Electric", 0, 0, 3, 2, Assets.electricEnergy, Type.electric);
    private EnergyCard earthEnergy = new EnergyCard("Earth", 0, 0, 3, 2,Assets.earthEnergy, Type.earth);

    private ArrayList<Card> deckOfCards = new ArrayList<Card>();
    Deck myDeck = new Deck(deckOfCards);
    @Override
    public void init(){



    }
    @Override
    public void update(float delta){
    }
    @Override
    public void render(Painter g){
        g.drawImage(Assets.ssb, 0, 0);
    }
    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY){
        return false;
    }

    public void onClick(View v){ drawCard(); }

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawButton = (ImageButton) findViewById(R.id.drawButton);
        cardImage = (ImageView) findViewById(R.id.cardImage);
        drawButton.setOnClickListener(this);
        deckOfCards.add(computerSociety);
        deckOfCards.add(boxingSociety);
        deckOfCards.add(rowingSociety);
        deckOfCards.add(waterEnergy);
        deckOfCards.add(electricEnergy);
        deckOfCards.add(earthEnergy);
    }

    private void drawCard(){
        myDeck.randomCard();
    }
}

