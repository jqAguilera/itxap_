package cl.jdcsolutions.matriz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import com.google.firebase.FirebaseApp;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText etFila, etColumna;

    Button btnGenera, btnAscendente, btnDescendente;

    FirebaseFirestore mFirestore;
    int[][] matriz;
    int f, c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGenera = findViewById(R.id.btnGenerar);

        btnGenera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    etFila = findViewById(R.id.etFila);
                    etColumna = findViewById(R.id.etColumna);
                    f = Integer.parseInt(etFila.getText().toString());
                    c = Integer.parseInt(etColumna.getText().toString());

                    matriz = llenarMatriz(f, c);

                    GridView gridView = findViewById(R.id.vMatriz);
                    gridView.setNumColumns(c);
                    gridView.setAdapter(new mAdapter(matriz));

                    saveMatriz(matriz);
                }catch (Exception e){

                }


            }
        });



        btnAscendente = findViewById(R.id.btnAscendente);
        btnAscendente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    etFila = findViewById(R.id.etFila);
                    etColumna = findViewById(R.id.etColumna);
                    f = Integer.parseInt(etFila.getText().toString());
                    c = Integer.parseInt(etColumna.getText().toString());
                    ordenarAscendente(f, c);
                }catch (Exception e){

                 }


            }
        });

        btnDescendente = findViewById(R.id.btnDescendente);
        btnDescendente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    etFila = findViewById(R.id.etFila);
                    etColumna = findViewById(R.id.etColumna);
                    f = Integer.parseInt(etFila.getText().toString());
                    c = Integer.parseInt(etColumna.getText().toString());
                    ordenarDescendente(f, c);

                }catch (Exception e){


                }

            }
        });

    }
    private int[][] llenarMatriz(int filas, int columnas) {
        matriz = new int[filas][columnas];
        Random random = new Random();

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = random.nextInt(1000) + 1;
            }
        }

        return matriz;
    }

    private void saveMatriz(int[][] matriz) {

        //Guardar Matriz en sharedPreference
        SharedPreferences sharedPreferences = getSharedPreferences("matrizTemp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        StringBuilder matrizString = new StringBuilder();

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matrizString.append(matriz[i][j]).append(",");
            }
        }

        editor.putString("matriz_id", matrizString.toString());
        editor.apply();
    }

    private void ordenarAscendente(int fila, int columna) {

        //Traer Matriz desde sharedPreference
        SharedPreferences sharedPreferences = getSharedPreferences("matrizTemp", Context.MODE_PRIVATE);
        String matrizString = sharedPreferences.getString("matriz_id", "");

        String[] elementos = matrizString.split(",");
        matriz = new int[fila][columna];
        int index = 0;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = Integer.parseInt(elementos[index]);
                index++;
            }
        }

        //Ordenamiento Burbuja
        int filas = matriz.length;
        int columnas = matriz[0].length;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                for (int k = 0; k < filas; k++) {
                    for (int l = 0; l < columnas; l++) {
                        if (matriz[i][j] < matriz[k][l]) {
                            int temp = matriz[i][j];
                            matriz[i][j] = matriz[k][l];
                            matriz[k][l] = temp;
                        }
                    }
                }
            }
        }

        GridView gridView = findViewById(R.id.vMatriz);
        gridView.setNumColumns(columna);
        gridView.setAdapter(new mAdapter(matriz));
    }

    private void ordenarDescendente(int fila, int columna) {

        //Traer Matriz desde sharedPreference
        SharedPreferences sharedPreferences = getSharedPreferences("matrizTemp", Context.MODE_PRIVATE);
        String matrizString = sharedPreferences.getString("matriz_id", "");

        String[] elementos = matrizString.split(",");
        matriz = new int[fila][columna];
        int index = 0;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = Integer.parseInt(elementos[index]);
                index++;
            }
        }

        //Ordenamiento Burbuja
        int filas = matriz.length;
        int columnas = matriz[0].length;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                for (int k = 0; k < filas; k++) {
                    for (int l = 0; l < columnas; l++) {
                        if (matriz[i][j] > matriz[k][l]) { // Cambio a '>' para ordenar de mayor a menor
                            int temp = matriz[i][j];
                            matriz[i][j] = matriz[k][l];
                            matriz[k][l] = temp;
                        }
                    }
                }
            }
        }

        GridView gridView = findViewById(R.id.vMatriz);
        gridView.setNumColumns(columna);
        gridView.setAdapter(new mAdapter(matriz));
    }

}