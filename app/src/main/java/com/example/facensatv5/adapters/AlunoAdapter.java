package com.example.facensatv5.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facensatv5.R;
import com.example.facensatv5.models.Aluno;

import java.util.List;

public class AlunoAdapter extends RecyclerView.Adapter<AlunoAdapter.AlunoViewHolder> {

    private List<Aluno> alunos;

    public AlunoAdapter(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    @NonNull
    @Override
    public AlunoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_aluno, parent, false);
        return new AlunoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlunoViewHolder holder, int position) {
        Aluno aluno = alunos.get(position);
        holder.ra.setText(String.valueOf(aluno.getRa()));
        holder.nome.setText(aluno.getNome());
        holder.cep.setText(aluno.getCep());
        holder.logradouro.setText(aluno.getLogradouro());
        holder.complemento.setText(aluno.getComplemento());
        holder.bairro.setText(aluno.getBairro());
        holder.cidade.setText(aluno.getCidade());
        holder.uf.setText(aluno.getUf());
    }

    @Override
    public int getItemCount() {
        return alunos.size();
    }

    public static class AlunoViewHolder extends RecyclerView.ViewHolder {
        TextView ra, nome, cep, logradouro, complemento, bairro, cidade, uf;

        public AlunoViewHolder(@NonNull View itemView) {
            super(itemView);
            ra = itemView.findViewById(R.id.tvRa);
            nome = itemView.findViewById(R.id.tvNome);
            cep = itemView.findViewById(R.id.tvCep);
            logradouro = itemView.findViewById(R.id.tvLogradouro);
            complemento = itemView.findViewById(R.id.tvComplemento);
            bairro = itemView.findViewById(R.id.tvBairro);
            cidade = itemView.findViewById(R.id.tvCidade);
            uf = itemView.findViewById(R.id.tvUf);
        }
    }
}

