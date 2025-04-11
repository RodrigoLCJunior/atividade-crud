package com.example.demo.controller;

import com.example.demo.model.Classe;
import com.example.demo.model.ItemMagico;
import com.example.demo.model.Personagem;
import com.example.demo.service.ItemMagicoService;
import com.example.demo.service.PersonagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personagens")
public class PersonagemController {

    @Autowired
    private PersonagemService personagemService;

    @Autowired
    private ItemMagicoService itemMagicoService;

    // Cadastrar Personagem
    @PostMapping
    public Personagem criarPersonagem(@RequestBody Personagem personagem) {
        return personagemService.criarPersonagem(personagem);
    }

    // Listar todos os personagens
    @GetMapping
    public List<Personagem> listarTodos() {
        return personagemService.acharTodosPersonagens();
    }

    // Buscar personagem por ID
    @GetMapping("/{id}")
    public ResponseEntity<Personagem> buscarPorId(@PathVariable long id) {
        Personagem personagem = personagemService.acharPersonagemPorId(id).get();
        if (personagem == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(personagem);
    }

    // Atualizar nome do personagem por ID
    @PutMapping("/{id}/nome")
    public ResponseEntity<Personagem> atualizarNome(@PathVariable long id, @RequestBody Personagem personagem) {
        Personagem atualizado = personagemService.mudarNomePorId(id, personagem.getNome());
        return ResponseEntity.ok(atualizado);
    }

    // Remover personagem por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id) {
        personagemService.deletarPersonagemPorId(id);
        return ResponseEntity.ok().build();
    }

    // Adicionar item mágico ao personagem
    @PostMapping("/{id}/item-magico/{idIM}")
    public ResponseEntity<Personagem> adicionarItem(@PathVariable Long id, @PathVariable Long idIM) {
        ItemMagico itemMagico = itemMagicoService.acharItemMagicoPorId(idIM);
        if (itemMagico == null){
            return ResponseEntity.notFound().build();
        }
        Personagem atualizado = personagemService.incluirItemMagicoNoPersonagem(id, itemMagico);
        if (atualizado != null) {
            return ResponseEntity.ok(atualizado);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    // Listar itens mágicos do personagem
    @GetMapping("/{id}/itens-magicos")
    public ResponseEntity<List<ItemMagico>> listarItensDoPersonagem(@PathVariable Long id) {
        return personagemService.acharPersonagemPorId(id)
                .map(p -> ResponseEntity.ok(p.getItemMagicoList()))
                .orElse(ResponseEntity.notFound().build());
    }

    // Remover item mágico do personagem
    @DeleteMapping("/{personagemId}/item-magico/{itemId}")
    public ResponseEntity<Personagem> removerItem(@PathVariable Long personagemId, @PathVariable Long itemId) {
        Personagem atualizado = personagemService.removerItemMagicoDoPersonagem(personagemId, itemId);
        if (atualizado != null) {
            return ResponseEntity.ok(atualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Buscar amuleto do personagem
    @GetMapping("/{id}/amuleto")
    public ResponseEntity<ItemMagico> buscarAmuleto(@PathVariable long id) {
        ItemMagico amuleto = personagemService.buscarAmuletoDoPersonagem(id);
        if (amuleto != null) {
            return ResponseEntity.ok(amuleto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
