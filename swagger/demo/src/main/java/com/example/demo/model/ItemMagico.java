package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ItemMagico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private TipoArma tipo;
    private int forca;
    private int defesa;

    public ItemMagico(){};

    public ItemMagico(String nome, TipoArma tipo, int forca, int defesa) {
        this.tipo = tipo;
        if (tipo == TipoArma.ARMA){
            this.defesa = 0;
            this.forca = forca;
            this.nome = "Arma";
        } else if (tipo == TipoArma.ARMADURA){
            this.forca = 0;
            this.defesa = defesa;
            this.nome = "Armadura";
        } else if (tipo == TipoArma.AMULETO){
            this.forca = forca;
            this.defesa = defesa;
            this.nome = "Almuleto";
        }
    }

    public int getDefesa() {
        return defesa;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public TipoArma getTipo() {
        return tipo;
    }

    public void setTipo(TipoArma tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
