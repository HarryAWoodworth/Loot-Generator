package com.codeka.harry.lootgenerator;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private String[] rubbageLoot = {
            "Dirty Bandage",
            "Duct Tape",
            "Cloth",
            "Glass Bottle",
            "Meat",
            "Trash",
            "Trash"
    }; //1
    private String[] houseLoot = {
            "Water Bottle",
            "Alchohol",
            "Cloth",
            "Duct Tape",
            "Glass Bottle",
            "Meat",
            "Bandage",
            "Trash",
            "Ammunition"
    }; //2
    private String[] gunSafeLoot = {
            "Shotgun",
            "Shotgun",
            "Hunting Rifle",
            "Hunting Rifle",
            "Sub Machine Gun",
            "Pistol",
            "Pistol",
            "Pistol"

    }; //6
    private String[] medicalLoot = {
            "Sterilized Bandages",
            "Splint",
            "Adrenaline",
            "Morphine",
            "Water Bottle",
            "First-Aid Kit"
    }; //5
    private String[] supplyDropLoot = {
            "Pistol",
            "Energy Drink",
            "Energy Drink",
            "Water Bottle",
            "Water Bottle",
            "Water Bottle",
            "Rations",
            "Rations",
            "Rations",
            "First-Aid Kit",
            "First-Aid Kit",
            "First-Aid Kit"
    }; //3
    private String[] safeHouseLoot = {
            "Bandage",
            "Duct Tape",
            "Food",
            "Ammunition",
            "Ammunition",
            "Ammunition",
            "Ammunition",
            "Ammunition"
    }; //4
    private String[] superLoot = {
            "Doctor's Bag",
            "Chainsaw",
            "RIOT armor",
            "Lucky Charm",
            "Kevlar Outfit",
            "Rabbit's Foot",
            "Really Big Molotov"
    }; //8
    private String[] militaryLoot = {
            "Body Armor",
            "Reflex Sight",
            "Machine Gun",
            "Box of Rations",
            "Grenade",
            "Ammunition",
            "Ammunition",
            "Ammunition",
            "Ammunition"
    }; //7
    private String[][] loots = {rubbageLoot,houseLoot,supplyDropLoot,safeHouseLoot,medicalLoot,gunSafeLoot,militaryLoot,superLoot};

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

    /** Called when the user clicks the 'New Loot' button */
    public void generateLoot(View view) {
        TextView lootView = (TextView)findViewById(R.id.lootView);
        EditText editText1 = (EditText) findViewById(R.id.editText);
        EditText editText2 = (EditText) findViewById(R.id.editText2);

        int lootLevel = 0;
        String input1 = editText1.getText().toString();
        try {
            lootLevel = Integer.parseInt(input1);
        } catch (NumberFormatException e) {
            messageBox("You dun goofed", e.getMessage());
            return;
        }

        int luckLevel = 0;
        String input2 = editText2.getText().toString();
        try {
           luckLevel = Integer.parseInt(input2);
        } catch (NumberFormatException e) {
            messageBox("You dun goofed", e.getMessage());
            return;
        }

        if(lootLevelCheck(lootLevel) && luckLevelCheck(luckLevel))
        {
            Random rand = new Random();
            int luckRand = rand.nextInt(10)+1;
            if(luckLevel >= luckRand && !(lootLevel+1 >= loots.length-1)){lootLevel+=1;}
            String[] currentLoot = loots[lootLevel-1];
            int randNum = rand.nextInt(currentLoot.length);
            lootView.setText(currentLoot[randNum]);
        }
    }

    public boolean lootLevelCheck(int lootLevel){
        if(lootLevel < 1 || lootLevel > loots.length)
        {
            messageBox("You dun goofed", "Loot level not in range! Level must be in between 1 and " + loots.length +".");
            return false;
        }
        return true;
    }

    public boolean luckLevelCheck(int luckLevel){
        if(luckLevel < 1 || luckLevel > 10)
        {
            messageBox("You dun goofed", "Luck level not in range! Level must be between 1 and 10.");
            return false;
        }
        return true;
    }

    private void messageBox(String method, String message) {
        Log.d("EXCEPTION: " + method, message);

        AlertDialog.Builder messageBox = new AlertDialog.Builder(this);
        messageBox.setTitle(method);
        messageBox.setMessage(message);
        messageBox.setCancelable(false);
        messageBox.setNeutralButton("OK", null);
        messageBox.show();
    }
}
