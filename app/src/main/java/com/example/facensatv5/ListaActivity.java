package com.example.facensatv5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facensatv5.adapters.AlunoAdapter;
import com.example.facensatv5.models.Aluno;
import com.example.facensatv5.utils.ApiClient;
import com.example.facensatv5.utils.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AlunoAdapter alunoAdapter;
    private Button btnCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnCadastro = findViewById(R.id.btnCadastro);
        btnCadastro.setOnClickListener(v -> {
            // Redirecionar para a tela de cadastro
            startActivity(new Intent(ListaActivity.this, MainActivity.class));
        });

        carregarAlunos();
    }

    private void carregarAlunos() {
        ApiService apiService = ApiClient.getRetrofitInstance(ApiClient.BASE_URL).create(ApiService.class);
        Call<List<Aluno>> call = apiService.getAlunos();

        call.enqueue(new Callback<List<Aluno>>() {
            @Override
            public void onResponse(Call<List<Aluno>> call, Response<List<Aluno>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Aluno> alunos = response.body();
                    alunoAdapter = new AlunoAdapter(alunos);
                    recyclerView.setAdapter(alunoAdapter);
                } else {
                    Toast.makeText(ListaActivity.this, "Erro ao carregar alunos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Aluno>> call, Throwable t) {
                Toast.makeText(ListaActivity.this, "Erro na conexão", Toast.LENGTH_SHORT).show();
            }
        });
    }
}