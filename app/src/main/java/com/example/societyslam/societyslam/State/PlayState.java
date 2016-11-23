package com.example.societyslam.societyslam.State;

import android.view.MotionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import android.os.Bundle;


import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.societyslam.societyslam.GameObjects.Card;
import com.example.societyslam.societyslam.GameObjects.Deck;
import com.example.societyslam.societyslam.GameObjects.EnergyCard;
import com.example.societyslam.societyslam.GameObjects.Level;
import com.example.societyslam.societyslam.GameObjects.SocietyCard;
import com.example.societyslam.societyslam.R;
import com.example.societyslam.societyslam.GameObjects.Type;
import com.example.societyslam.societyslam.Util.Painter;

public class PlayState extends State implements OnClickListener {

    private ImageButton drawButton;
    private ImageView cardImage;

    SocietyCard computerSociety = new SocietyCard("Computer Society", 0, 0, 3, 2, 100, R.drawable.computersociety, "Virus Strike", 1, 30, Type.electric, Type.water, null, 2, Level.Basic);
    SocietyCard boxingSociety = new SocietyCard("Boxing Society", 0, 0, 3, 2, 120, R.drawable.boxingsociety, "Force Punch", 1, 40, Type.fighting, Type.electric, Type.water, 2, Level.Basic);
    SocietyCard rowingSociety = new SocietyCard("Rowing Society", 0, 0, 3, 2, 100, R.drawable.rowingsociety, "Paddle Pound", 1, 20, Type.water, Type.electric, Type.fighting, 1, Level.Basic);


    EnergyCard waterEnergy = new EnergyCard("Water", 0, 0 ,3, 2, R.drawable.waterenergy, Type.water);
    EnergyCard electricEnergy = new EnergyCard("Electric", 0, 0 ,3, 2, R.drawable.electricenergy, Type.electric);
    EnergyCard earthEnergy = new EnergyCard("Earth", 0, 0 ,3, 2, R.drawable.earthenergy, Type.earth);

    ArrayList<Card> deckOfCards = new ArrayList<>(Arrays.asList(computerSociety, boxingSociety, rowingSociety, waterEnergy, electricEnergy, earthEnergy));

    Deck myDeck = new Deck(deckOfCards);


    @Override
    public void onClick(View v) {
        drawCard();

    }

    @Override
    public void init() {

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Painter g) {

    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        return false;
    }

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        drawButton = (ImageButton) findViewById((R.id.drawButton));
        cardImage = (ImageView)  findViewById((R.id.cardImage));

        drawButton.setOnClickListener(this);

    }

    private void drawCard(){
        myDeck.randomCard();
    }
}