package com.example.diceout;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //Fields to hold the roll and result text
    TextView rollResult;
    TextView scoreText;

    //Field to hold the roll button
    Button rollButton;

    //Field to hold the score
    int score;

    //Need to create an instance of the Random class for generating the numbers use ALT+ENTER shortcut to import
    Random rand;

    //Need to create dice to hold the values
    int die1;
    int die2;
    int die3;

    //ArrayList to hold all the dice values
    ArrayList<Integer> dice;

    //Arraylist to hold all 3 dice images
    ArrayList<ImageView> diceImageViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //Set initial score to 0
        score = 0;
        //Toast.makeText(this, "The score is "+ score, Toast.LENGTH_SHORT).show();

        //link the java to the XML for widgets
        rollResult = (TextView) findViewById(R.id.rollResult);
        rollButton = (Button) findViewById(R.id.rollButton);
        scoreText = (TextView) findViewById(R.id.scoreText);
        //initialize the random number generator
        rand = new Random();

        //Create ArrayList Container for the Dice values
        dice = new ArrayList<Integer>();

        ImageView die1Image = (ImageView)findViewById(R.id.die1Image);
        ImageView die2Image = (ImageView)findViewById(R.id.die2Image);
        ImageView die3Image = (ImageView)findViewById(R.id.die3Image);

        diceImageViews = new ArrayList<ImageView>();
        diceImageViews.add(die1Image);
        diceImageViews.add(die2Image);
        diceImageViews.add(die3Image);

        //For static images you can use drawables, but for dynamic changeing ones you should use assets.
        //It is common to run out of memory if using drawables, so we will use drawables here
        //Need to manually create an assets folder
    }

    public void rollDice(View v){

        rollResult.setText("Clicked!");

        //Roll dice
        die1 = rand.nextInt(6) + 1;
        die2 = rand.nextInt(6) + 1;
        die3 = rand.nextInt(6) + 1;

        //Set the dice values into an arraylist
        dice.clear();
        dice.add(die1);
        dice.add(die2);
        dice.add(die3);

        for(int dieOfSet = 0; dieOfSet < 3; dieOfSet++){
            //create string for file name
            String imageName = "die_" + dice.get(dieOfSet) + ".png";

            //get asset based on filename, but need a try catch for possible input errors
            try{
                //gets assets from the assets folder
                InputStream stream = getAssets().open(imageName);
                Drawable d = Drawable.createFromStream(stream,null);
                diceImageViews.get(dieOfSet).setImageDrawable(d);

            }catch (IOException e){
                e.printStackTrace();
            }
        }

        //Build string for what the user rolled
        String msg = "You rolled a " + die1 + ", a " + die2 +", a " + die3;

        //Update the app to display the roll message
        rollResult.setText(msg);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
