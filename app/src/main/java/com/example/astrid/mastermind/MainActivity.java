package com.example.astrid.mastermind;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    Button nouveau = null;
    Button verifier = null;
    Button case1 = null;
    Button case2 = null;
    Button case3 = null;
    Button case4 = null;
    EditText nbt = null;
    EditText nbvb = null;
    EditText nbvm = null;
    EditText ms = null;
    FileOutputStream output = null;
    FileInputStream input = null;
    File mFile = null;
    int color1 = 0, color2 = 0, color3 = 0, color4 = 0;
    int[] nb;
    int [] nbcherche;
    String[] nbdonne;
    String mastermind = "mastermind.txt";
    int nbtentative,nbjustebon, nbjustemauvais;
    int nombre0, nombre1, nombre2, nombre3;
    Random rnd = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nouveau = (Button)findViewById(R.id.nouveau);
        verifier = (Button)findViewById(R.id.verifier);
        case1 = (Button)findViewById(R.id.case1);
        case2 = (Button)findViewById(R.id.case2);
        case3 = (Button)findViewById(R.id.case3);
        case4 = (Button)findViewById(R.id.case4);
        nbt = (EditText)findViewById(R.id.nbt);
        nbvm = (EditText)findViewById(R.id.nbvm);
        nbvb = (EditText)findViewById(R.id.nbvb);
        ms = (EditText)findViewById(R.id.ms);

        nouveau.setOnClickListener(nouveauListener);
        verifier.setOnClickListener(verifierListener);
        case1.setOnClickListener(case1Listener);
        case2.setOnClickListener(case2Listener);
        case3.setOnClickListener(case3Listener);
        case4.setOnClickListener(case4Listener);

        nbcherche = new int[4];
        nb = new int[4];
        nbdonne = new String[4];

        // On crée un fichier qui correspond à l'emplacement extérieur
        mFile = new File(Environment.getExternalStorageDirectory().getPath() + "/Android/data/ " + getPackageName() + "/files/" + mastermind);

        init();
    }

    public void init(){
        try {
            FileInputStream input = openFileInput(mastermind);
            int value;
            // On utilise un StringBuffer pour construire la chaîne au fur et à mesure
            StringBuffer lu = new StringBuffer();
            // On lit les caractères les uns après les autres
            while((value = input.read()) != -1) {
                // On écrit dans le fichier le caractère lu
                lu.append((char)value);
            }
            ms.setText(lu.toString());
            if(input != null)
                input.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        nbtentative = 0;
        nbjustebon = 0;
        nbjustemauvais = 0;
        color1 = 0;
        color2 = 0;
        color3 = 0;
        color4 = 0;
        nbt.setText(String.valueOf(nbtentative));
        nbvm.setText("");
        nbvb.setText("");
        case1.setBackgroundColor(Color.rgb(211, 211, 211));
        case2.setBackgroundColor(Color.rgb(211, 211, 211));
        case3.setBackgroundColor(Color.rgb(211, 211, 211));
        case4.setBackgroundColor(Color.rgb(211, 211, 211));

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
            if (color1 != 0 && color2 != 0 && color3 != 0 && color4 != 0)
            {
                nbjustebon = 0;
                nbjustemauvais = 0;
                nombre0 = 0;
                nombre1 = 0;
                nombre2 = 0;
                nombre3 = 0;

                nbtentative += 1;
                nbt.setText(String.valueOf(nbtentative));

                for (int i = 0; i < 4; i++)
                {
                    if (nbcherche[i] == nb[i])
                    {
                        nbjustebon += 1;
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

                if (nb[0] == nbcherche[0] && nb[1] == nbcherche[1] && nb[2] == nbcherche[2] && nb[3] == nbcherche[3])
                {
                    try {
                        // Flux interne
                        FileOutputStream output = openFileOutput(mastermind, MODE_PRIVATE);

                        // On écrit dans le flux interne
                        output.write(String.valueOf(nbtentative).getBytes());

                        if(output != null)
                            output.close();

                        // Si le fichier est lisible et qu'on peut écrire dedans
                        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                                && !Environment.MEDIA_MOUNTED_READ_ONLY.equals(Environment.getExternalStorageState())) {
                            // On crée un nouveau fichier. Si le fichier existe déjà, il ne sera pas créé
                            mFile.createNewFile();
                            output = new FileOutputStream(mFile);
                            output.write(String.valueOf(nbtentative).getBytes());
                            if(output != null)
                                output.close();
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    alertDialog.setTitle("Félicitations !");
                    alertDialog.setMessage("Vous avez trouvé la bonne combinaison !");
                    alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,"OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            init();
                        }
                    });
                    alertDialog.show();
                }
                else if(nbtentative>=10)
                {
                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    alertDialog.setTitle("Perdu !");
                    alertDialog.setMessage("T'es vraiment nul !");
                    alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,"OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            init();
                        }
                    });
                    alertDialog.show();
                }
                nbvb.setText(String.valueOf(nbjustebon));
                nbvm.setText(String.valueOf(nbjustemauvais));
            }
            else
            {
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Erreur !");
                alertDialog.setMessage("Votre liste de couleurs est incomplète !");
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,"OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alertDialog.show();
            }
        }
    };

    private View.OnClickListener case1Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            color1=changeCouleur(case1,color1,0);
            nb[0]=nombre(color1,0);
        }
    };

    private View.OnClickListener case2Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            color2=changeCouleur(case2,color2,1);
            nb[1]=nombre(color2,1);
        }
    };

    private View.OnClickListener case3Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            color3=changeCouleur(case3,color3,2);
            nb[2]=nombre(color3,2);
        }
    };

    private View.OnClickListener case4Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            color4=changeCouleur(case4,color4,3);
            nb[3]=nombre(color4,3);
        }
    };

    public int changeCouleur(Button txt, int color, int nombre)
    {
        color++;
        if (color > 8)
        {
            color = 1;
        }
        if (color == 1)
        {
            txt.setBackgroundColor(Color.rgb(255, 255, 0));
            nb[nombre] = 1;
        }
        if (color == 2)
        {
            txt.setBackgroundColor(Color.rgb(51,255,255));
            nb[nombre] = 2;
        }
        if (color == 3)
        {
            txt.setBackgroundColor(Color.rgb(255,102,0));
            nb[nombre] = 3;
        }
        if (color == 4)
        {
            txt.setBackgroundColor(Color.rgb(0,153,0));
            nb[nombre] = 4;
        }
        if (color == 5)
        {
            txt.setBackgroundColor(Color.rgb(255,0,0));
            nb[nombre] = 5;
        }
        if (color == 6)
        {
            txt.setBackgroundColor(Color.rgb(0,0,153));
            nb[nombre] = 6;
        }
        if (color == 7)
        {
            txt.setBackgroundColor(Color.rgb(255,153,255));
            nb[nombre] = 7;
        }
        if (color == 8)
        {
            txt.setBackgroundColor(Color.rgb(102,0,204));
            nb[nombre] = 8;
        }
        return color;
    }

    public int nombre(int color,int nombre){
        if (color == 1)
        {
            nb[nombre] = 1;
        }
        if (color == 2)
        {
            nb[nombre] = 2;
        }
        if (color == 3)
        {
            nb[nombre] = 3;
        }
        if (color == 4)
        {
            nb[nombre] = 4;
        }
        if (color == 5)
        {
            nb[nombre] = 5;
        }
        if (color == 6)
        {
            nb[nombre] = 6;
        }
        if (color == 7)
        {
            nb[nombre] = 7;
        }
        if (color == 8)
        {
            nb[nombre] = 8;
        }
        return nb[nombre];
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
