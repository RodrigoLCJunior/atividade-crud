package com.example.demo.service;

import com.example.demo.model.Classe;
import com.example.demo.model.ItemMagico;
import com.example.demo.model.Personagem;
import com.example.demo.model.TipoArma;
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

    @Autowired
    private ItemMagicoService itemMagicoService;

    //Achar um Personagem por ID
    public Optional<Personagem> acharPersonagemPorId(long id){
        return personagemRepository.findById(id);
    }

    //Achar todos os Personagens
    public List<Personagem> acharTodosPersonagens(){
        return personagemRepository.findAll();
    }

    // Buscar Amuleto do Personagess
    public ItemMagico buscarAmuletoDoPersonagem(long id) {
        Personagem personagem = acharPersonagemPorId(id).orElse(null);

        if (personagem != null) {
            for (ItemMagico item : personagem.getItemMagicoList()) {
                if (item.getTipo().toString().equals("AMULETO")) {
                    return item;
                }
            }
        }

        return null;
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

        ItemMagico itemMagicoExistente = itemMagicoService.acharItemMagicoPorId(itemMagico.getId());

        List <ItemMagico> itemMagicoList = personagemAchado.getItemMagicoList();

        int totalItens = itemMagicoList.size();
        int i = 0;
        boolean almuleto = false;

        do {
            if (itemMagicoList.get(i).getTipo() == TipoArma.AMULETO){
                almuleto = true;
            }
            i++;
        } while ((i < totalItens) || almuleto == false);

        if (almuleto){
            return null;
        }

        itemMagicoList.add(itemMagico);
        personagemAchado.setItemMagicoList(itemMagicoList);

        personagemAchado.setForca(personagemAchado.getForca() + itemMagico.getForca());
        personagemAchado.setDefesa(personagemAchado.getDefesa() + itemMagico.getDefesa());
        return personagemRepository.save(personagemAchado);
    }

    public Personagem removerItemMagicoDoPersonagem(long personagemId, long itemId) {
        Optional<Personagem> personagemOptional = personagemRepository.findById(personagemId);

        if (personagemOptional.isPresent()) {
            Personagem personagem = personagemOptional.get();
            List<ItemMagico> itens = personagem.getItemMagicoList();

            int i = 0;
            int total = itens.size();
            ItemMagico itemParaRemover = null;

            if (total == 0) return null;

            do {
                ItemMagico item = itens.get(i);
                if (item.getId().equals(itemId)) {
                    itemParaRemover = item;
                    break;
                }
                i++;
            } while (i < total && itemParaRemover == null);

            if (itemParaRemover != null) {

                personagem.setForca(personagem.getForca() - itemParaRemover.getForca());
                personagem.setDefesa(personagem.getDefesa() - itemParaRemover.getDefesa());

                itens.remove(itemParaRemover);
                personagem.setItemMagicoList(itens);
                return personagemRepository.save(personagem);
            }
        }

        return null;
    }

    // Deletar um Personagem Por Id
    public void deletarPersonagemPorId(long id){
        personagemRepository.deleteById(id);
    }
}
