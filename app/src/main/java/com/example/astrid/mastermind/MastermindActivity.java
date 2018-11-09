package com.example.astrid.mastermind;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

// Main class
public class MastermindActivity extends AppCompatActivity {
    Button nouveau;
    Button verifier;
    Button case1;
    Button case2;
    Button case3;
    Button case4;
    Button rb1;
    Button rb2;
    Button rb3;
    Button rb4;
    TextView nbt;
    TextView txtCombinaisons;
    ListView listLines;
    ArrayList<Line> lines;
    LineAdapter lineAdapter;
    int color1, color2, color3, color4;
    Drawable[]drawables;
    int[] nb;
    int [] nbcherche;
    String[] nbdonne;
    int nbtentative,nbjustebon, nbjustemauvais;
    int nombre0, nombre1, nombre2, nombre3;
    Random rnd = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mastermind);

        nouveau = findViewById(R.id.nouveau);
        verifier = findViewById(R.id.verifier);
        txtCombinaisons = findViewById(R.id.txtCombinaisons);
        case1 = findViewById(R.id.case1);
        case2 = findViewById(R.id.case2);
        case3 = findViewById(R.id.case3);
        case4 = findViewById(R.id.case4);
        nbt = findViewById(R.id.nbt);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);

        listLines = findViewById(R.id.listeCombinaisons);
        lines=new ArrayList<>();
        lineAdapter = new LineAdapter(getApplicationContext(), lines);
        listLines.setAdapter(lineAdapter);
        drawables = new Drawable[8];

        drawables[0] = getResources().getDrawable(R.drawable.yellowroundedbutton);
        drawables[1] = getResources().getDrawable(R.drawable.cyanroundedbutton);
        drawables[2] = getResources().getDrawable(R.drawable.orangeroundedbutton);
        drawables[3] = getResources().getDrawable(R.drawable.greenroundedbutton);
        drawables[4] = getResources().getDrawable(R.drawable.redroundedbutton);
        drawables[5] = getResources().getDrawable(R.drawable.blueroundedbutton);
        drawables[6] = getResources().getDrawable(R.drawable.pinkroundedbutton);
        drawables[7] = getResources().getDrawable(R.drawable.purpleroundedbutton);

        nouveau.setOnClickListener(nouveauListener);
        verifier.setOnClickListener(verifierListener);
        case1.setOnClickListener(case1Listener);
        case2.setOnClickListener(case2Listener);
        case3.setOnClickListener(case3Listener);
        case4.setOnClickListener(case4Listener);

        nbcherche = new int[4];
        nb = new int[4];
        nbdonne = new String[4];

        init();
    }

    // Method for initializing and resetting
    public void init(){
        nbtentative = 0;
        nbjustebon = 0;
        nbjustemauvais = 0;
        color1 = 0;
        color2 = 0;
        color3 = 0;
        color4 = 0;
        rb1.setBackground(getResources().getDrawable(R.drawable.darkgreyroundedbutton));
        rb2.setBackground(getResources().getDrawable(R.drawable.darkgreyroundedbutton));
        rb3.setBackground(getResources().getDrawable(R.drawable.darkgreyroundedbutton));
        rb4.setBackground(getResources().getDrawable(R.drawable.darkgreyroundedbutton));
        case1.setBackground(getResources().getDrawable(R.drawable.greyroundedbutton));
        case2.setBackground(getResources().getDrawable(R.drawable.greyroundedbutton));
        case3.setBackground(getResources().getDrawable(R.drawable.greyroundedbutton));
        case4.setBackground(getResources().getDrawable(R.drawable.greyroundedbutton));

        nbt.setText(String.valueOf(nbtentative));
        lines.clear();
        lineAdapter.notifyDataSetChanged();
        txtCombinaisons.setVisibility(View.INVISIBLE);

        for (int i = 0; i < 4; i++)
        {
            nbcherche[i] = rnd.nextInt(8)+1;
        }
    }

    private View.OnClickListener nouveauListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            init();
        }
    };

    private View.OnClickListener verifierListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            verification();
        }
    };

    private View.OnClickListener case1Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            color1=changeCouleur(case1,color1,0);
        }
    };

    private View.OnClickListener case2Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            color2=changeCouleur(case2,color2,1);
        }
    };

    private View.OnClickListener case3Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            color3=changeCouleur(case3,color3,2);
        }
    };

    private View.OnClickListener case4Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            color4=changeCouleur(case4,color4,3);
        }
    };

    // Method for changing the color of the buttons
    public int changeCouleur(Button txt, int color, int nombre)
    {
        color++;
        if (color == 9)
        {
            color = 1;
        }
        for(int i = 1; i<=8; i++){
            if(color==i) {
                txt.setBackground(drawables[i - 1]);
                nb[nombre] = i;
            }
        }
        return color;
    }

    // Method for generating a popup
    public void generationPopUp(String titre, String contenu){
        AlertDialog alertDialog = new AlertDialog.Builder(MastermindActivity.this).create();
        alertDialog.setTitle(titre);
        alertDialog.setMessage(contenu);
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,"OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertDialog.show();
    }

    // Method for generating a popup with reset
    public void generationPopUpReinit(String titre, String contenu){
        AlertDialog alertDialog = new AlertDialog.Builder(MastermindActivity.this).create();
        alertDialog.setTitle(titre);
        alertDialog.setMessage(contenu);
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,"OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                init();
            }
        });
        alertDialog.show();
    }

    // Method to check if the combination is the right one
    public void verification(){
        if (color1 != 0 && color2 != 0 && color3 != 0 && color4 != 0)
        {
            nbjustebon = 0;
            nbjustemauvais = 0;
            nombre0 = 0;
            nombre1 = 0;
            nombre2 = 0;
            nombre3 = 0;
            rb1.setBackground(getResources().getDrawable(R.drawable.darkgreyroundedbutton));
            rb2.setBackground(getResources().getDrawable(R.drawable.darkgreyroundedbutton));
            rb3.setBackground(getResources().getDrawable(R.drawable.darkgreyroundedbutton));
            rb4.setBackground(getResources().getDrawable(R.drawable.darkgreyroundedbutton));

            nbtentative += 1;
            nbt.setText(String.valueOf(nbtentative));

            for (int i = 0; i < 4; i++)
            {
                if (nbcherche[i] == nb[i])
                {
                    nbjustebon += 1;
                    if(rb1.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.darkgreyroundedbutton).getConstantState())){
                        rb1.setBackground(getResources().getDrawable(R.drawable.redroundedbutton));
                    }else if(rb2.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.darkgreyroundedbutton).getConstantState())) {
                        rb2.setBackground(getResources().getDrawable(R.drawable.redroundedbutton));
                    }else if(rb3.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.darkgreyroundedbutton).getConstantState())) {
                        rb3.setBackground(getResources().getDrawable(R.drawable.redroundedbutton));
                    }else{
                        rb4.setBackground(getResources().getDrawable(R.drawable.redroundedbutton));
                    }
                }
            }
            if (nbcherche[0] != nb[0])
            {
                if (nb[0] == nbcherche[1] && nb[1] != nbcherche[1] && nombre1 == 0)
                {
                    nbjustemauvais += 1;
                    nombre1 = 1;
                }
                else if (nb[0] == nbcherche[2] && nb[2] != nbcherche[2] && nombre2 == 0)
                {
                    nbjustemauvais += 1;
                    nombre2 = 1;
                }
                else if (nb[0] == nbcherche[3] && nb[3] != nbcherche[3] && nombre3 == 0)
                {
                    nbjustemauvais += 1;
                    nombre3 = 1;
                }
            }
            if (nbcherche[1] != nb[1])
            {
                if (nb[1] == nbcherche[0] && nb[0] != nbcherche[0] && nombre0 == 0)
                {
                    nbjustemauvais += 1;
                    nombre0 = 1;

                }
                else if (nb[1] == nbcherche[2] && nb[2] != nbcherche[2] && nombre2 == 0)
                {
                    nbjustemauvais += 1;
                    nombre2 = 1;
                }
                else if (nb[1] == nbcherche[3] && nb[3] != nbcherche[3] && nombre3 == 0)
                {
                    nbjustemauvais += 1;
                    nombre3 = 1;
                }
            }
            if (nbcherche[2] != nb[2])
            {
                if (nb[2] == nbcherche[0] && nb[0] != nbcherche[0] && nombre0 == 0)
                {
                    nbjustemauvais += 1;
                    nombre0 = 1;
                }
                else if (nb[2] == nbcherche[1] && nb[1] != nbcherche[1] && nombre1 == 0)
                {
                    nbjustemauvais += 1;
                    nombre1 = 1;
                }
                else if (nb[2] == nbcherche[3] && nb[3] != nbcherche[3] && nombre3 == 0)
                {
                    nbjustemauvais += 1;
                    nombre3 = 1;
                }
            }
            if (nbcherche[3] != nb[3])
            {
                if (nb[3] == nbcherche[0] && nb[0] != nbcherche[0] && nombre0 == 0)
                {
                    nbjustemauvais += 1;
                    nombre0 = 1;
                }
                else if (nb[3] == nbcherche[1] && nb[1] != nbcherche[1] && nombre1 == 0)
                {
                    nbjustemauvais += 1;
                    nombre1 = 1;
                }
                else if (nb[3] == nbcherche[2] && nb[2] != nbcherche[2] && nombre2 == 0)
                {
                    nbjustemauvais += 1;
                    nombre2 = 1;
                }

            }

            for(int i = 1; i<=nbjustemauvais;i++){
                if(rb1.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.darkgreyroundedbutton).getConstantState())
                        && !rb1.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.redroundedbutton).getConstantState())){
                    rb1.setBackground(getResources().getDrawable(R.drawable.whiteroundedbutton));
                }else if(rb2.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.darkgreyroundedbutton).getConstantState())
                        && !rb2.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.redroundedbutton).getConstantState())) {
                    rb2.setBackground(getResources().getDrawable(R.drawable.whiteroundedbutton));
                }else if(rb3.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.darkgreyroundedbutton).getConstantState())
                        && !rb3.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.redroundedbutton).getConstantState())) {
                    rb3.setBackground(getResources().getDrawable(R.drawable.whiteroundedbutton));
                }else{
                    rb4.setBackground(getResources().getDrawable(R.drawable.whiteroundedbutton));
                }
            }

            if (nb[0] == nbcherche[0] && nb[1] == nbcherche[1] && nb[2] == nbcherche[2] && nb[3] == nbcherche[3])
            {
                generationPopUpReinit(getResources().getString(R.string.felicitations),getResources().getString(R.string.felicitations_contenu));
            }
            else if(nbtentative>=12)
            {
                generationPopUpReinit(getResources().getString(R.string.perdu),getResources().getString(R.string.perdu_contenu));
            }

            txtCombinaisons.setVisibility(View.VISIBLE);
            Line line = new Line(case1.getBackground(),case2.getBackground(),case3.getBackground(),case4.getBackground(),rb1.getBackground(),rb2.getBackground(),rb3.getBackground(),rb4.getBackground());
            lines.add(line);
            lineAdapter.notifyDataSetChanged();
        }
        else
        {
            generationPopUp(getResources().getString(R.string.erreur),getResources().getString(R.string.erreur_contenu));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        // If the "Rules" option is selected in the menu
        if (id == R.id.action_rules) {
            generationPopUp(getResources().getString(R.string.action_rules),getResources().getString(R.string.rules));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}