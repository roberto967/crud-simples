package com.example.crudoaktec.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "produto")
public class Produto {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "valor")
    private float valor;

    @Column(name = "disponivel")
    private boolean disponivel;

    public Produto() {
        this.id = UUID.randomUUID();
    }

    // Construtores, getters e setters

    public Produto(String nome, String descricao, float valor, boolean disponivel) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.disponivel = disponivel;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", disponivel=" + disponivel +
                '}';
    }
}
