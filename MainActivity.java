package com.example.guenet.messoldes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.view.View.OnClickListener;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {


    RadioButton rb_woman = null;
    RadioButton rb_man = null;

    Button btn_raz = null;
    Button btn_result = null;

    EditText edtx_weight = null;
    EditText edtx_feet = null;
    EditText edtx_inches = null;

    TextView tw_result = null;

    double height = 0;
    double weight = 0;
    double result = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rb_woman = (RadioButton)findViewById(R.id.radioButton_woman);
        rb_man = (RadioButton)findViewById(R.id.radioButton_man);

        btn_raz = (Button)findViewById(R.id.btn_raz);
        btn_result = (Button)findViewById(R.id.btn_result);

        edtx_weight = (EditText)findViewById(R.id.edtx_weight);
        edtx_feet = (EditText)findViewById(R.id.edtx_feet);
        edtx_inches = (EditText)findViewById(R.id.edtx_inches);

        tw_result = (TextView)findViewById(R.id.tw_result);





//   On s'assure que la taille soit renseignée pour pouvoir activer le champs du poids

        edtx_inches.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

//                On réinitialise le résultat si il y a un changement
                result = 0;

                if ("".equals(edtx_inches.getText().toString())) {
                    edtx_weight.setEnabled(false);

                    if ("".equals(edtx_weight.getText().toString()))
                       btn_result.setEnabled(false);
                }

                else
                    edtx_weight.setEnabled(true);


            }

            @Override
            public void afterTextChanged(Editable s) {




            }
        });

//   Vérification que le poids est renseigné pour activer le bouton de calcul

        edtx_weight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

//                On réinitialise le résultat si il y a un changement
                result = 0;

                if (count > 0)
                    btn_result.setEnabled(true);
                else
                    btn_result.setEnabled(false);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    btn_result.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {

//            Récupération du poids
            weight = Double.valueOf(edtx_weight.getText().toString());

//            Récupération de la taille
            float tHeight = Float.valueOf(edtx_inches.getText().toString());
//            On convertit la taille en mètre
            tHeight = tHeight / 100;
//            La taille en mètre au carré
            double heightCarre =  Math.pow(tHeight,2);

//            Calcul de l'imc
            result =  (weight / heightCarre);

//            On arrondit le résultat à deux décimales
            NumberFormat nf = new DecimalFormat("0.##");
            String sResult = nf.format(result);


//          Affichage de l'imc dans le textview
            tw_result.setText("Votre IMC est de " + sResult);

        }
    });

    }
}