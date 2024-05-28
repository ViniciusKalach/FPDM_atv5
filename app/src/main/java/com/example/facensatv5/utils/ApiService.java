package com.example.facensatv5.utils;

import com.example.facensatv5.models.Aluno;
import com.example.facensatv5.models.Endereco;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
public interface ApiService {
    @GET("ws/{cep}/json/")
    Call<Endereco> getEndereco(@Path("cep") String cep);

    @POST("alunos")
    Call<Aluno> createAluno(@Body Aluno aluno);

    @GET("alunos")
    Call<List<Aluno>> getAlunos();
}
