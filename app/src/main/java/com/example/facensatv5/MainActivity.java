package com.example.facensatv5;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.facensatv5.models.Aluno;
import com.example.facensatv5.models.Endereco;
import com.example.facensatv5.utils.ApiClient;
import com.example.facensatv5.utils.ApiService;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    private EditText etRa, etNome, etCep, etLogradouro, etComplemento, etBairro, etCidade, etUf;
    private Button btnSalvar, btnListar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etRa = findViewById(R.id.etRa);
        etNome = findViewById(R.id.etNome);
        etCep = findViewById(R.id.etCep);
        etLogradouro = findViewById(R.id.etLogradouro);
        etComplemento = findViewById(R.id.etComplemento);
        etBairro = findViewById(R.id.etBairro);
        etCidade = findViewById(R.id.etCidade);
        etUf = findViewById(R.id.etUf);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnListar = findViewById(R.id.btnListar);

        etCep.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                String cep = s.toString();
                if (cep.length() == 8) {
                    buscarEndereco(cep);
                }
            }
        });

        btnSalvar.setOnClickListener(v -> salvarAluno());

        btnListar.setOnClickListener(v -> {
            // Navegar para a tela de listagem de alunos
            startActivity(new Intent(MainActivity.this, ListaActivity.class));
        });
    }

    private void buscarEndereco(String cep) {
        ApiService apiService = ApiClient.getRetrofitInstance(ApiClient.VIACEP_URL).create(ApiService.class);
        Call<Endereco> call = apiService.getEndereco(cep);

        call.enqueue(new Callback<Endereco>() {
            @Override
            public void onResponse(Call<Endereco> call, Response<Endereco> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Endereco endereco = response.body();
                    etLogradouro.setText(endereco.getLogradouro());
                    etComplemento.setText(endereco.getComplemento());
                    etBairro.setText(endereco.getBairro());
                    etCidade.setText(endereco.getLocalidade());
                    etUf.setText(endereco.getUf());
                } else {
                    Toast.makeText(MainActivity.this, "CEP não encontrado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Endereco> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Erro na busca do CEP", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void salvarAluno() {
        int ra = Integer.parseInt(etRa.getText().toString());
        String nome = etNome.getText().toString();
        String cep = etCep.getText().toString();
        String logradouro = etLogradouro.getText().toString();
        String complemento = etComplemento.getText().toString();
        String bairro = etBairro.getText().toString();
        String cidade = etCidade.getText().toString();
        String uf = etUf.getText().toString();

        Aluno aluno = new Aluno(ra, nome, cep, logradouro, complemento, bairro, cidade, uf);

        ApiService apiService = ApiClient.getRetrofitInstance(ApiClient.BASE_URL).create(ApiService.class);
        Call<Aluno> call = apiService.createAluno(aluno);

        call.enqueue(new Callback<Aluno>() {
            @Override
            public void onResponse(Call<Aluno> call, Response<Aluno> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Aluno cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                    limparFormulario();
                } else {
                    Toast.makeText(MainActivity.this, "Erro ao cadastrar aluno", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Aluno> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Erro na conexão", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void limparFormulario() {
        etRa.setText("");
        etNome.setText("");
        etCep.setText("");
        etLogradouro.setText("");
        etComplemento.setText("");
        etBairro.setText("");
        etCidade.setText("");
        etUf.setText("");
    }
}