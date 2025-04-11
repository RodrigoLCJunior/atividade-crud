# atividade-crud

http://localhost:6060/swagger-ui/index.html#/ <- link do SWAGGER

### 🎮 Personagem

{
    "id": 1,
    "nome": "Teste6",
    "nomeAventureiro": null,
    "level": 0,
    "forca": 0,
    "defesa": 0,
    "pontosAtributos": 10,
    "classe": "Guerreiro",
    "itemMagicoList": []
}

- `POST /personagens` → Cadastrar personagem
- `GET /personagens` → Listar personagens
- `GET /personagens/{id}` → Buscar personagem por ID
- `PUT /personagens/{id}/nome` → Atualizar nome
- `DELETE /personagens/{id}` → Remover personagem
- `POST /personagens/{id}/item-magico` → Adicionar item mágico
- `GET /personagens/{id}/itens-magicos` → Listar itens do personagem
- `DELETE /personagens/{id}/item-magico/{itemId}` → Remover item do personagem
- `GET /personagens/{id}/amuleto` → Buscar amuleto do personagem

---

### 🪄 Item Mágico

{
    "id": 3,
    "nome": "Armadura",
    "tipo": "ARMADURA",
    "forca": 0,
    "defesa": 4
}

- `POST /itens-magicos` → Cadastrar item mágico
- `GET /itens-magicos` → Listar itens mágicos
- `GET /itens-magicos/{id}` → Buscar item mágico por ID

---
