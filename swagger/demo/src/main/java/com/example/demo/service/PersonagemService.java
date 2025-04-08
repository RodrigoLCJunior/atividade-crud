package com.example.demo.service;

import com.example.demo.model.Classe;
import com.example.demo.model.ItemMagico;
import com.example.demo.model.Personagem;
import com.example.demo.repository.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class PersonagemService {

    @Autowired
    private PersonagemRepository personagemRepository;

    //Achar um Personagem por ID
    public Optional<Personagem> acharPersonagemPorId(long id){
        return personagemRepository.findById(id);
    }

    //Achar todos os Personagens
    public List<Personagem> acharTodosPersonagens(){
        return personagemRepository.findAll();
    }

    //Criar um Personagem
    public Personagem criarPersonagem(String nome, Classe classe){
        Personagem personagemNovo = new Personagem(nome, classe);
        return personagemRepository.save(personagemNovo);
    }

    //Atribuir atributos
    public void atribuirForcaEDefesa(long id, int forca, int defesa){
        Personagem personagem = acharPersonagemPorId(id).get();
        if ((personagem.getDefesa() + defesa) > 10) {
            personagem.setDefesa(10);
        }else {
            personagem.setDefesa(defesa);
        }

        if ((personagem.getForca() + forca) > 10){
            personagem.setForca(10);
        } else {
            personagem.setForca(10);
        }
    }

    // Mudar o nome de um Personagem por Id
    public Personagem mudarNomePorId(long id, String nomeNovo){
        Personagem personagemAchado = acharPersonagemPorId(id).get();
        personagemAchado.setNome(nomeNovo);
        return personagemRepository.save(personagemAchado);
    }

    // Incluir um ItemMagico no Personagem
    public Personagem incluirItemMagicoNoPersonagem(long id, ItemMagico itemMagico){
        Personagem personagemAchado = acharPersonagemPorId(id).get();

        List <ItemMagico> itemMagicoList = personagemAchado.getItemMagicoList();
        if(itemMagicoList.size() == 3){
            return null;
        } else {
            itemMagicoList.add(itemMagico);
            personagemAchado.setItemMagicoList(itemMagicoList);
            return personagemRepository.save(personagemAchado);
        }
    }

    // Deletar um Personagem Por Id
    public void deletarPersonagemPorId(long id){
        personagemRepository.deleteById(id);
    }
}
