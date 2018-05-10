package br.com.edu.up.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private TextView txtResult;
    String Conta = "0", previusString = null;
    boolean isTempStringShown = false;
    int currentopperand = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResult = (TextView) findViewById(R.id.txtResult);

        //Da um Set no Array para sempre fazer isso em tds Botoes
        int numBtn[] = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9};
        //Cria um On Click Para os Botoes
        NumberButtonClickListener PressNum = new NumberButtonClickListener();
        //Seleciona O Numero Para Entrar no Metodo
        for (int id : numBtn) {
            View v = findViewById(id);
            v.setOnClickListener(PressNum);
        }


        //Da um Set no Array de Simbolos
        int opBtn[] = {R.id.btnMais, R.id.btnMenos, R.id.btnDividido, R.id.btnVezes, R.id.btnVirgula, R.id.btnLimpar, R.id.btnIgual};
        //Cria o Click do Botao de Operaçao

       OpperandButtonClickListener PressOp=new OpperandButtonClickListener();
//        //Seleciona a Operacao
        for (int id : opBtn) {
            View v = findViewById(id);
            v.setOnClickListener(PressOp);
        }
        setCurrentString("0");
    }


    //Seta a Conta
    void setCurrentString(String s) {
        Conta = s;
        txtResult.setText(s);

    }


    //Metodo que Adiciona Numero para la em cima no Txt
    class NumberButtonClickListener implements OnClickListener {
        @Override
        public void onClick(View v)

        {
            if (isTempStringShown) {
                previusString = Conta;
                Conta = "0";
                isTempStringShown = false;
            }
            String text = (String) ((Button) v).getText();
            //Se for o Primeiro Numero
            if (Conta.equals("0")) setCurrentString(text);
                //Se nao for o Primeiro
            else setCurrentString(Conta + text);
        }
    }


    class OpperandButtonClickListener implements OnClickListener {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            //se for o clear
            if (id == R.id.btnLimpar) {
                isTempStringShown = false;
                setCurrentString("0");
                previusString = null;
                currentopperand=0;
            }
            //Se for Virgula
            if (id == R.id.btnVirgula) if (!Conta.contains(".")) setCurrentString(Conta + ".");


            //Se for +-/* ELE VAI SETAR A OPERAÇAO

            if (id == R.id.btnMais || id == R.id.btnMenos || id == R.id.btnVezes || id == R.id.btnDividido) {
                //Vai Separar os numeros
                currentopperand = id;
                previusString = Conta;
                isTempStringShown = true;
            }

            //Vai Calcular o Result
            if (id == R.id.btnIgual) {
                double curr = Double.parseDouble(Conta);
                double result = 0;
                if (previusString != null) {
                    double prev = Double.parseDouble(previusString);
                    switch (currentopperand) {
                        case R.id.btnMais:
                            result = prev + curr;
                            break;
                        case R.id.btnMenos:
                            result = prev - curr;
                            break;
                        case R.id.btnVezes:
                            result = prev * curr;
                            break;
                        case R.id.btnDividido:
                            result = prev / curr;
                            break;
                    }
                }
                setCurrentString(Double.toString(result));
            }
        }
    }

}








