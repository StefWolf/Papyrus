# Papyrus
Trabalho final da disciplina de Desenvolvimento Web II do Mestrado PPGTI. 

Trata-se de um sistema de gestão e organização de artigos científicos para o desenvolvimento de mapeamento, revisões sistemáticas e o estudo ao estado da arte. A aplicação foi projetada por duas sessões: 
- FrontEnd: ReactJS e TailwindCSS
- BackEnd/API: Java Spring, REST. 

Seu principal objetivo é atender aos alunos pesquisadores e professores. Enquanto o sistema ajuda a organizar os artigos de pesquisa, o professor tem total acompanhamento sobre aquelas atividades dos estudantes podendo agregar com sugestões e melhorias para a pesquisa.

## Requisitos Funcionais 
  Total: 48 RQs (eu acho) | Professor - 6 | Estudante - 18 | SProgresso - 4| Administrador - 10 | Regras de Negócio - 10.

### Professor
1. Realizar login no sistema.
2. Cadastrar novos alunos no sistema. 
3. Listar todos os alunos cadastrados.
4. Buscar por um aluno específico.
5. Atualizar dados, incluindo status de um aluno em específico.
6. Visualizar e comentar o progresso de um aluno específico. 

### Estudante
1. Realizar login no sistema.
2. Cadastrar uma nova área de pesquisa.
3. Listar as áreas de pesquisa cadastradas.
4. Atualizar as áreas de pesquisa cadastradas.
5. Excluir áreas cadastradas, mas não podem estar vinculadas à um artigo.
6. Cadastrar autores.
7. Listar os autores cadastrados.
8. Atualizar autores cadastrados.
9. Excluir autores, desde que não estejam vinculados à um artigo.
10. Cadastrar periódicos.
11. Listar os periódicos cadastrados.
12. Atualizar periódicos cadastrados.
13. Excluir periódicos, contanto que não estejam vinculados à um artigo.
14. Cadastrar artigos.
15. Listar todos os artigos cadastrados.
16. Atualizar artigos.
17. Buscar artigos por filtro, como ano, área, autor e status.
18. Excluir artigos cadastrados. 

### Sistema de Progresso do Estudante
1. O sistema calcula o progresso do aluno com base na quantidade de áreas, autores, periódicos e artigos cadastrados.
2. O sistema permite visualizar o status do aluno, como cursando ou finalizado.
3. O sistema permite registrar comentários de acompanhamento feitos por professores.
4. O sistema deve exibir os totais de produções cadastradas por aluno.

### Administrador
1. Realizar login no sistema.
2. Cadastrar um novo professor no sistema
3. Listar os professores cadastrados no sistema.
4. Buscar por um professor específico no sistema.
5. Atualizar dados de um professor específico no sistema. 
6. excluir professor do sistema.
7. Cadastrar um novo aluno no sistema.
8. Listar todos os alunos cadastrados.
9. Buscar pelo aluno cadastrado
10. Atualizar dados, incluindo modificar status de um aluno.

### Regras de Negócio
1. O sistema deve impedir o cadastro de área com nome duplicado.
2. O sistema deve impedir o cadastro de autor com email duplicado.
3. O sistema deve impedir o cadastro de periódico com nome duplicado.
4. O sistema deve impedir a exclusão de área vinculada a artigo.
5. O sistema deve impedir a exclusão de autor vinculado a artigo.
6. O sistema deve impedir a exclusão de periódico vinculado a artigo.
7. O sistema deve exigir pelo menos um autor para cadastrar um artigo.
8. O sistema deve impedir artigo com ano no futuro.
9. O sistema deve exigir observação para artigos com status finalizado.
10. O sistema deve exigir DOI para artigos com status finalizado.

## Entidades do Sistema

| Entidade | Descrição |
|---|---|
| `Usuario` | Entidade base responsável por representar os usuários autenticados do sistema. Armazena informações comuns como nome, email, senha e role/permissão. |
| `UsuarioRole` | Representa os níveis de permissão do sistema (`ROLE_ALUNO`, `ROLE_PROFESSOR`, `ROLE_ADM`) utilizados pelo Spring Security para controle de acesso. |
| `Aluno` | Representa um usuário do tipo aluno. Armazena informações acadêmicas como matrícula, semestre, curso e status do aluno. |
| `Professor` | Representa um usuário do tipo professor. Possui informações como formação acadêmica e permissões de gerenciamento de alunos. |
| `AlunoProgresso` | Responsável pelo acompanhamento do desempenho do aluno no sistema, incluindo comentários do professor, status e métricas de progresso acadêmico. |
| `Artigo` | Representa um artigo científico cadastrado pelo aluno, contendo informações como nome, ano, DOI, observações, status, autores, área e periódico. |
| `Autor` | Representa os autores vinculados aos artigos científicos cadastrados no sistema. |
| `Area` | Representa as áreas de pesquisa ou conhecimento utilizadas para categorizar artigos científicos. |
| `Periodico` | Representa periódicos científicos associados aos artigos cadastrados, contendo nome e link de acesso. |
| `AuditLog` | Entidade responsável por armazenar registros de auditoria das operações realizadas no sistema, como criação, atualização e exclusão de dados. |


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

## Tabela de Cirtérios Atendidos para a atividade

| Critério da Atividade | Atende? | Onde encontrar no código |
|---|---|---|
| Utilizar Spring Data JPA | ✅ Sim | `ArtigoRepository`, `AlunoRepository`, `ProfessorRepository`, `UsuarioRepository`... |
| Possuir ao menos 8 entidades mapeadas | ✅ Sim | Pacote `model/` contendo: `Usuario`, `UsuarioRole`, `Aluno`, `Professor`, `AlunoProgresso`, `Artigo`, `Autor`, `Area`, `Periodico`, `AuditLog`. |
| Relacionamento um-para-muitos / muitos-para-um | ✅ Sim | Em `Artigo.java`: relacionamento com `Area` e `Periodico` usando `@ManyToOne`. |
| Relacionamento muitos-para-muitos | ✅ Sim | Em `Artigo.java` e `Autor.java`: relacionamento `@ManyToMany` entre artigos e autores. |
| Relacionamento um-para-um | ✅ Sim | Em `Aluno.java` ↔ `AlunoProgresso.java` usando `@OneToOne`. Também `Usuario` ↔ `Aluno` e `Usuario` ↔ `Professor`. |
| CRUD para entidade Area | ✅ Sim | `AreaController.java` (`POST`, `GET`, `PUT`, `DELETE`) |
| CRUD para entidade Autor | ✅ Sim | `AutorController.java` (`POST`, `GET`, `PUT`, `DELETE`) |
| CRUD para entidade Periodico | ✅ Sim | `PeriodicoController.java` (`POST`, `GET`, `PUT`, `DELETE`) |
| CRUD para entidade Artigo | ✅ Sim | `ArtigoController.java` (`POST`, `GET`, `PUT`, `DELETE`) |
| CRUD para entidade Aluno | ✅ Sim | `AlunoController.java` (`POST`, `GET`, `PUT`, `PATCH`) |
| CRUD para entidade Professor | ✅ Sim | `ProfessorController.java` (`POST`, `GET`, `PUT`, `DELETE`) |
| Operações com queries personalizadas | ✅ Sim | Repositories como `AreaRepository`, `AutorRepository`, `ArtigoRepository`, `UsuarioRepository`. |
| Query personalizada de verificação de nome | ✅ Sim | `existsByNomeIgnoreCase()` em `AreaRepository` e `PeriodicoRepository`. |
| Query personalizada de verificação de email | ✅ Sim | `existsByEmail()` em `AutorRepository` e `UsuarioRepository`. |
| Query personalizada por relacionamento | ✅ Sim | `existsByArea_Id()`, `existsByAutores_Id()`, `existsByPeriodico_Id()` em `ArtigoRepository`. |
| Query personalizada de autenticação | ✅ Sim | `findByEmail()` em `UsuarioRepository`. |
| Segurança com Spring Security | ✅ Sim | `SecurityConfig.java`, `JwtAuthenticationFilter.java`, `JwtService.java`. |
| Controle de permissões por roles | ✅ Sim | Anotações `@PreAuthorize` nos controllers. |
| Autenticação JWT | ✅ Sim | `AuthController.java`, `JwtService.java`, `JwtAuthenticationFilter.java`. |
| DTOs para desacoplamento | ✅ Sim | Pacote `dto/` com `AlunoDTO`, `ProfessorDTO`, `ArtigoRequestDTO`, `ArtigoResponseDTO`, etc. |
| Validações com Bean Validation | ✅ Sim | Uso de `@Valid`, `@NotBlank`, `@Size`, `@Email` nos DTOs. |
| Uso correto de Status HTTP | ✅ Sim | `ResponseEntity.status(HttpStatus.CREATED)`, `ResponseEntity.noContent()`, `404`, `403`, etc. |

## Explicação do Sistema de Login (passo a passo) 

A segurança nesse sistema papyrus foi implementada utilizando Spring Security com autenticação baseada em JWT. O usuário realiza login com email e senha, o sistema valida as credenciais através do AuthenticationManager e do CustomUserDetailsService, e então gera um token JWT contendo os dados e permissões do usuário. O frontend envia esse token em todas as requisições protegidas, permitindo que o backend valide o usuário autenticado. O controle de autorização é feito utilizando @PreAuthorize, restringindo o acesso aos endpoints conforme as roles ROLE_ALUNO, ROLE_PROFESSOR e ROLE_ADM.

## Front

<img width="1361" height="894" alt="Captura de tela 2026-05-05 213418" src="https://github.com/user-attachments/assets/4211a508-4e31-4c5a-b9d6-552336135307" />

