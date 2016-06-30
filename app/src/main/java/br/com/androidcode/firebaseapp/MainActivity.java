package br.com.androidcode.firebaseapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity {
    //Criamos os objetos que serão usados em nosso APP
    private FirebaseAnalytics fbAnalytics;
    private Button btnPreFormatado;
    private Button btnCustomizado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Inicializamos o objeto Firebase Analytics com getInstance. Obrigatoriamente
        //esse método pede um Context como parâmetro. Nesse caso passamos o context da Activity
        // através do "this".
        fbAnalytics = FirebaseAnalytics.getInstance(this);

        //Inicializamos os objetos dos botões.
        btnPreFormatado = (Button)findViewById(R.id.btnAnalyticsPreformatado);
        btnCustomizado = (Button)findViewById(R.id.btnAnalyticsCustomizado);

        //E instanciamos o setOnClickListener de cada um para
        //executar as ações.
        btnPreFormatado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Lembre-se: primeiro criamos os parâmetros, depois enviamos
                // tudo com o Evento via logEvent.
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "imagem");
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "img758.png");
                fbAnalytics.logEvent(FirebaseAnalytics.Event.SHARE, bundle);

                //O Toast serve somente para gerar um efeito visual.
                Toast.makeText(MainActivity.this,"Evento Firebase Pré-Formatado Disparado!", Toast.LENGTH_SHORT).show();
            }
        });

        btnCustomizado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Nos parâmetros customizados, o nome do parâmetro não pode
                // conter espaços em branco.
                Bundle bundle = new Bundle();
                bundle.putString("tipo_arquivo","mp3");
                bundle.putString("nome_arquivo","xpto.mp3");
                fbAnalytics.logEvent("share_file",bundle);

                Toast.makeText(MainActivity.this, "Evento Firebase Customizado Disparado!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
