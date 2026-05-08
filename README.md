# Papyrus
Trabalho final da disciplina de Desenvolvimento Web II do Mestrado PPGTI. 

## Demandas

- [x] Cadastro de Novo aluno {POST} (ROLE_PROFESSOR E ADM)
- [x] Busca de um aluno {GET} (ROLE_PROFESSOR E ADM)
- [x] Busca de todos os alunos {GET} (ROLE_PROFESSOR E ADM) 
- [x] Atualização de aluno {PUT} (ROLE_PROFESSOR E ADM)
- [x] Sistema de Login do usuário {POST}
- [x] CRUD área (ROLE_ALUNO)
- [x] CRUD autor de artigo (ROLE_ALUNO)
- [x] CRUD periódico (ROLE_ALUNO)
- [x] CRUD artigo (ROLE_ALUNO)
- [x] Visualização do progresso de um aluno {GET} (ROLE_PROFESSOR E ADM)
- [x] Adicionar/atualizar comentário de um aluno {PUT} (ROLE_PROFESSOR)
- [x] Mudança de status do aluno de "cursando" para "finalizado" {PUT} (ROLE_PROFESSOR E ADM)



## Matriz de Permissões do Sistema

| Endpoint | Método HTTP | Descrição | ROLE_ALUNO | ROLE_PROFESSOR | ROLE_ADM |
|---|---|---|---|---|---|
| `/auth/login` | POST | Login no sistema | ✅ | ✅ | ✅ |

---

### Alunos

| Endpoint | Método HTTP | Descrição | ROLE_ALUNO | ROLE_PROFESSOR | ROLE_ADM |
|---|---|---|---|---|---|
| `/alunos` | POST | Cadastro de aluno | ❌ | ✅ | ✅ |
| `/alunos` | GET | Listar alunos | ❌ | ✅ | ✅ |
| `/alunos/{id}` | GET | Buscar aluno por ID | ❌ | ✅ | ✅ |
| `/alunos/{id}` | PUT | Atualizar aluno | ❌ | ✅ | ✅ |
| `/alunos/{id}/finalizar` | PATCH | Finalizar aluno | ❌ | ✅ | ✅ |

---

### Progresso do Aluno

| Endpoint | Método HTTP | Descrição | ROLE_ALUNO | ROLE_PROFESSOR | ROLE_ADM |
|---|---|---|---|---|---|
| `/alunos/progresso/{alunoId}` | GET | Visualizar progresso do aluno | ❌ | ✅ | ✅ |
| `/alunos/progresso/{alunoId}/comentario` | PUT | Atualizar comentário do aluno | ❌ | ✅ | ❌ |

---

### Professores

| Endpoint | Método HTTP | Descrição | ROLE_ALUNO | ROLE_PROFESSOR | ROLE_ADM |
|---|---|---|---|---|---|
| `/professores` | POST | Cadastrar professor | ❌ | ❌ | ✅ |
| `/professores` | GET | Listar professores | ❌ | ❌ | ✅ |
| `/professores/{id}` | GET | Buscar professor por ID | ❌ | ❌ | ✅ |
| `/professores/{id}` | PUT | Atualizar professor | ❌ | ❌ | ✅ |
| `/professores/{id}` | DELETE | Remover professor | ❌ | ❌ | ✅ |

---

### Áreas

| Endpoint | Método HTTP | Descrição | ROLE_ALUNO | ROLE_PROFESSOR | ROLE_ADM |
|---|---|---|---|---|---|
| `/areas` | POST | Criar área | ✅ | ❌ | ❌ |
| `/areas` | GET | Listar áreas | ✅ | ❌ | ❌ |
| `/areas/{id}` | PUT | Atualizar área | ✅ | ❌ | ❌ |
| `/areas/{id}` | DELETE | Deletar área | ✅ | ❌ | ❌ |

---

### Autores

| Endpoint | Método HTTP | Descrição | ROLE_ALUNO | ROLE_PROFESSOR | ROLE_ADM |
|---|---|---|---|---|---|
| `/autores` | POST | Criar autor | ✅ | ❌ | ❌ |
| `/autores` | GET | Listar autores | ✅ | ❌ | ❌ |
| `/autores/{id}` | PUT | Atualizar autor | ✅ | ❌ | ❌ |
| `/autores/{id}` | DELETE | Deletar autor | ✅ | ❌ | ❌ |

---

### Periódicos

| Endpoint | Método HTTP | Descrição | ROLE_ALUNO | ROLE_PROFESSOR | ROLE_ADM |
|---|---|---|---|---|---|
| `/periodicos` | POST | Criar periódico | ✅ | ❌ | ❌ |
| `/periodicos` | GET | Listar periódicos | ✅ | ❌ | ❌ |
| `/periodicos/{id}` | PUT | Atualizar periódico | ✅ | ❌ | ❌ |
| `/periodicos/{id}` | DELETE | Deletar periódico | ✅ | ❌ | ❌ |

---

### Artigos

| Endpoint | Método HTTP | Descrição | ROLE_ALUNO | ROLE_PROFESSOR | ROLE_ADM |
|---|---|---|---|---|---|
| `/artigos` | POST | Criar artigo | ✅ | ❌ | ❌ |
| `/artigos` | GET | Listar artigos | ✅ | ❌ | ❌ |
| `/artigos/{id}` | GET | Buscar artigo por ID | ✅ | ❌ | ❌ |
| `/artigos/{id}` | PUT | Atualizar artigo | ✅ | ❌ | ❌ |
| `/artigos/{id}` | DELETE | Deletar artigo | ✅ | ❌ | ❌ |


## Front

<img width="1361" height="894" alt="Captura de tela 2026-05-05 213418" src="https://github.com/user-attachments/assets/4211a508-4e31-4c5a-b9d6-552336135307" />

