package com.example.demo.service;

import com.example.demo.model.ItemMagico;
import com.example.demo.repository.ItemMagicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemMagicoService {

    @Autowired
    private ItemMagicoRepository itemMagicoRepository;

    //Listar Todos Itens Magicos
    public List<ItemMagico> listarTodosItensMagicos(){
        return itemMagicoRepository.findAll();
    }

    // Achar por Id
    public ItemMagico acharItemMagicoPorId(Long id){
        return itemMagicoRepository.findById(id).get();
    }

    // Criar Um item Magico
    public ItemMagico criarItemMagico(ItemMagico itemMagico){
        ItemMagico itemMagicoNovo = new ItemMagico(itemMagico.getNome(), itemMagico.getTipo(), itemMagico.getForca(), itemMagico.getDefesa());
        return itemMagicoRepository.save(itemMagicoNovo);
    }



}
