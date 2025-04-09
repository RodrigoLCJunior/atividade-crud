package com.example.demo.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Personagem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String nomeAventureiro;
    private int level;
    private int forca;
    private int defesa;
    private int pontosAtributos;
    private Classe classe;
    @ManyToMany
    private List<ItemMagico> itemMagicoList = new ArrayList<>();


    public Personagem(String nome, Classe classe) {
        this.nome = nome;
        this.level = 0;
        this.forca = 0;
        this.defesa = 0;
        this.pontosAtributos = 10;
        this.classe = classe;
    }

    public int getPontosAtributos() {
        return pontosAtributos;
    }

    public void setPontosAtributos(int pontosAtributos) {
        this.pontosAtributos = pontosAtributos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeAventureiro() {
        return nomeAventureiro;
    }

    public void setNomeAventureiro(String nomeAventureiro) {
        this.nomeAventureiro = nomeAventureiro;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public int getDefesa() {
        return defesa;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    public List<ItemMagico> getItemMagicoList() {
        return itemMagicoList;
    }

    public List<ItemMagico> setItemMagicoList(List<ItemMagico> itemMagicoList) {
        return this.itemMagicoList = itemMagicoList;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }
}
