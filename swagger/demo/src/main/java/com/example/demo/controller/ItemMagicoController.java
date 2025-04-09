package com.example.demo.controller;

import com.example.demo.model.ItemMagico;
import com.example.demo.service.ItemMagicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens-magicos")
public class ItemMagicoController {

    @Autowired
    private ItemMagicoService itemMagicoService;

    // Cadastrar item mágico
    @PostMapping
    public ItemMagico criarItemMagico(@RequestBody ItemMagico item) {
        return itemMagicoService.criarItemMagico(item);
    }

    // Listar todos os itens mágicos
    @GetMapping
    public List<ItemMagico> listarTodos() {
        return itemMagicoService.listarTodosItensMagicos();
    }

    // Buscar item mágico por ID
    @GetMapping("/{id}")
    public ItemMagico buscarPorId(@PathVariable Long id) {
        return itemMagicoService.acharItemMagicoPorId(id);
    }
}
