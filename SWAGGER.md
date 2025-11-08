# 📚 Documentação Swagger - Sistema Biblioteca API

## 🎯 Visão Geral

O Swagger (OpenAPI 3.0) foi implementado no projeto `sistema-biblioteca` para fornecer documentação interativa e completa da API.

---

## ✅ O que foi Implementado

### 1. **Dependência SpringDoc OpenAPI**
- Versão: `2.3.0`
- Biblioteca: `springdoc-openapi-starter-webmvc-ui`
- Compatível com Spring Boot 3.x

### 2. **Classe de Configuração**
- Arquivo: `OpenApiConfig.java`
- Localização: `src/main/java/com/library/sistema_biblioteca/config/`
- Funcionalidades:
  - Informações da API (título, versão, descrição)
  - Contato e licença
  - Múltiplos servidores (Produção e Desenvolvimento)

### 3. **Anotações nos Controllers**
- `@Tag`: Agrupa endpoints por recurso
- `@Operation`: Descreve cada endpoint
- `@ApiResponses`: Documenta possíveis respostas
- `@Parameter`: Documenta parâmetros

### 4. **Anotações nos Models**
- `@Schema`: Documenta propriedades das entidades
- Exemplos para cada campo
- Marcação de campos obrigatórios

### 5. **Configurações**
- Arquivo: `application.properties` e `application-prod.properties`
- Swagger UI habilitado
- Documentação JSON/YAML disponível

---

## 🌐 Acessando o Swagger

### 🔴 Produção (Render)

```
https://sistema-biblioteca-api.onrender.com/swagger-ui.html
```

### 🟢 Local (Desenvolvimento)

```
http://localhost:8080/swagger-ui.html
```

### 📄 Documentação JSON

```
# Produção
https://sistema-biblioteca-api.onrender.com/api-docs

# Local
http://localhost:8080/api-docs
```

### 📄 Documentação YAML

```
# Produção
https://sistema-biblioteca-api.onrender.com/api-docs.yaml

# Local
http://localhost:8080/api-docs.yaml
```

---

## 📋 Recursos Documentados

### 📚 **Autores** (`/api/autores`)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/autores` | Lista todos os autores |
| `GET` | `/api/autores/{id}` | Busca autor por ID |
| `POST` | `/api/autores` | Cria novo autor |
| `PUT` | `/api/autores/{id}` | Atualiza autor |
| `DELETE` | `/api/autores/{id}` | Remove autor |

#### Exemplo de Request (POST):
```json
{
  "nome": "Machado de Assis",
  "nacionalidade": "Brasileiro",
  "dataNascimento": "1839-06-21",
  "biografia": "Joaquim Maria Machado de Assis foi um escritor brasileiro..."
}
```

#### Exemplo de Response:
```json
{
  "id": 1,
  "nome": "Machado de Assis",
  "nacionalidade": "Brasileiro",
  "dataNascimento": "1839-06-21",
  "biografia": "Joaquim Maria Machado de Assis foi um escritor brasileiro..."
}
```

### 📖 **Livros** (`/api/livros`)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/livros` | Lista todos os livros |
| `GET` | `/api/livros/{id}` | Busca livro por ID |
| `POST` | `/api/livros` | Cria novo livro |
| `PUT` | `/api/livros/{id}` | Atualiza livro |
| `DELETE` | `/api/livros/{id}` | Remove livro |

### 📖 **Empréstimos** (`/api/emprestimos`)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/emprestimos` | Lista todos os empréstimos |
| `GET` | `/api/emprestimos/{id}` | Busca empréstimo por ID |
| `POST` | `/api/emprestimos` | Cria novo empréstimo |
| `PUT` | `/api/emprestimos/{id}` | Atualiza empréstimo |
| `DELETE` | `/api/emprestimos/{id}` | Remove empréstimo |

---

## 🎨 Interface do Swagger UI

### Funcionalidades Disponíveis:

#### 1. **Explorar Endpoints**
- Lista todos os endpoints disponíveis
- Agrupados por recurso (Autores, Livros, Empréstimos)
- Ordenados alfabeticamente

#### 2. **Try it Out**
- Testar endpoints diretamente na interface
- Preencher parâmetros e body
- Executar requisições reais

#### 3. **Documentação Detalhada**
- Descrição de cada endpoint
- Parâmetros obrigatórios e opcionais
- Códigos de status HTTP
- Exemplos de request/response

#### 4. **Schemas**
- Visualizar estrutura das entidades
- Tipos de dados
- Validações

#### 5. **Servidores**
- Alternar entre Produção e Desenvolvimento
- Testar em diferentes ambientes

---

## ⚙️ Configurações do Swagger

### application.properties

```properties
# ================================
# Configuracao do SpringDoc OpenAPI (Swagger)
# ================================
# Path da documentacao JSON/YAML
springdoc.api-docs.path=/api-docs
springdoc.api-docs.enabled=true

# Path da interface Swagger UI
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.swagger-ui.enabled=true

# Configuracoes da UI
springdoc.swagger-ui.operationsSorter=method
springdoc.swagger-ui.tagsSorter=alpha
springdoc.swagger-ui.tryItOutEnabled=true
springdoc.swagger-ui.filter=true
springdoc.swagger-ui.syntaxHighlight.activated=true
springdoc.swagger-ui.displayRequestDuration=true

# Desabilitar endpoint do Actuator na documentacao
springdoc.show-actuator=false

# Versao da API
springdoc.version=1.0.0
```

### Descrição das Configurações:

| Propriedade | Descrição | Valor |
|------------|-----------|-------|
| `springdoc.api-docs.path` | Caminho da documentação JSON/YAML | `/api-docs` |
| `springdoc.swagger-ui.path` | Caminho da interface Swagger UI | `/swagger-ui.html` |
| `springdoc.swagger-ui.operationsSorter` | Ordem dos endpoints | `method` (GET, POST, PUT, DELETE) |
| `springdoc.swagger-ui.tagsSorter` | Ordem das tags | `alpha` (alfabética) |
| `springdoc.swagger-ui.tryItOutEnabled` | Habilita "Try it out" | `true` |
| `springdoc.swagger-ui.filter` | Habilita busca | `true` |
| `springdoc.swagger-ui.syntaxHighlight` | Destaque de sintaxe | `true` |
| `springdoc.swagger-ui.displayRequestDuration` | Mostra tempo de resposta | `true` |

---

## 🔐 Segurança

### CORS Configurado

A API está configurada para aceitar requisições de:
- `https://bibliotecadjr.pages.dev` (Frontend em Produção)
- `http://localhost:3000` (Frontend em Desenvolvimento)

### Métodos Permitidos:
- `GET`
- `POST`
- `PUT`
- `DELETE`
- `OPTIONS`

---

## 📊 Códigos de Status HTTP

| Código | Descrição | Quando Ocorre |
|--------|-----------|---------------|
| `200` | OK | Requisição bem-sucedida |
| `201` | Created | Recurso criado com sucesso |
| `400` | Bad Request | Dados inválidos fornecidos |
| `404` | Not Found | Recurso não encontrado |
| `500` | Internal Server Error | Erro interno do servidor |

---

## 🚀 Como Usar

### 1. **Acessar o Swagger UI**
```
http://localhost:8080/swagger-ui.html
```

### 2. **Explorar os Endpoints**
- Clique em um recurso (ex: Autores)
- Visualize os endpoints disponíveis

### 3. **Testar um Endpoint**
- Clique no endpoint desejado
- Clique em "Try it out"
- Preencha os parâmetros (se necessário)
- Clique em "Execute"

### 4. **Visualizar a Resposta**
- Código de status HTTP
- Response body
- Response headers
- Tempo de resposta (Request duration)

---

## 🎓 Exemplos de Uso

### Exemplo 1: Criar um Autor

1. Acesse: `http://localhost:8080/swagger-ui.html`
2. Clique em **Autores**
3. Clique em **POST /api/autores**
4. Clique em **Try it out**
5. Preencha o body:
```json
{
  "nome": "Machado de Assis",
  "nacionalidade": "Brasileiro",
  "dataNascimento": "1839-06-21",
  "biografia": "Escritor brasileiro, considerado um dos maiores nomes da literatura nacional"
}
```
6. Clique em **Execute**
7. Visualize a resposta

### Exemplo 2: Listar Todos os Autores

1. Clique em **GET /api/autores**
2. Clique em **Try it out**
3. Clique em **Execute**
4. Visualize a lista de autores

### Exemplo 3: Buscar Autor por ID

1. Clique em **GET /api/autores/{id}**
2. Clique em **Try it out**
3. Preencha o campo `id` com `1`
4. Clique em **Execute**
5. Visualize o autor retornado

---

## 🔧 Personalização

### Alterar Informações da API

Edite o arquivo `OpenApiConfig.java`:

```java
private Info apiInfo() {
    return new Info()
            .title("Seu Título")              // ← Alterar aqui
            .version("2.0.0")                 // ← Alterar versão
            .description("Sua descrição")     // ← Alterar descrição
            .contact(apiContact())
            .license(apiLicense());
}
```

### Adicionar Novo Servidor

```java
private List<Server> apiServers() {
    Server novoServer = new Server()
            .url("https://seu-servidor.com")
            .description("Descrição do servidor");
    
    return List.of(prodServer, devServer, novoServer);
}
```

### Adicionar Autenticação (Futuro)

```java
@Bean
public OpenAPI customOpenAPI() {
    return new OpenAPI()
            .info(apiInfo())
            .servers(apiServers())
            .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
            .components(new Components()
                .addSecuritySchemes("bearerAuth", 
                    new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")));
}
```

---

## 📝 Boas Práticas

### 1. **Sempre Documente**
- Use `@Operation` em todos os endpoints
- Adicione `@ApiResponses` para todos os códigos de status
- Use `@Parameter` para descrever parâmetros

### 2. **Forneça Exemplos**
- Use `example` em `@Schema` para campos
- Exemplos facilitam o entendimento

### 3. **Mantenha Atualizado**
- Atualize a documentação quando alterar endpoints
- Incremente a versão da API quando houver mudanças

### 4. **Agrupe Logicamente**
- Use `@Tag` para agrupar endpoints relacionados
- Mantenha a organização

---

## 🆘 Troubleshooting

### Swagger UI não carrega

```bash
# Verificar se a aplicação está rodando
curl http://localhost:8080/api-docs

# Se retornar 404, verificar application.properties
springdoc.api-docs.enabled=true
springdoc.swagger-ui.enabled=true
```

### Endpoints não aparecem

```bash
# Verificar se os controllers têm @RestController
# Verificar se os endpoints têm @GetMapping, @PostMapping, etc.
# Verificar se o pacote está sendo escaneado pelo Spring
```

### Erro 403 (CORS)

```bash
# Verificar @CrossOrigin nos controllers
# Verificar configuração no application.properties
spring.web.cors.allowed-origins=...
```

---

## 📚 Recursos Adicionais

### Documentação Oficial
- [SpringDoc OpenAPI](https://springdoc.org/)
- [OpenAPI Specification](https://swagger.io/specification/)
- [Swagger UI](https://swagger.io/tools/swagger-ui/)

### Tutoriais
- [Spring Boot + Swagger](https://www.baeldung.com/spring-rest-openapi-documentation)
- [SpringDoc Annotations](https://springdoc.org/v2/#swagger-annotations)

---

## ✅ Checklist de Implementação

- [x] Adicionar dependência SpringDoc OpenAPI
- [x] Criar classe de configuração OpenApiConfig
- [x] Adicionar anotações nos controllers
- [x] Adicionar anotações nos models
- [x] Configurar application.properties
- [x] Testar localmente
- [ ] Fazer deploy para produção
- [ ] Testar em produção
- [ ] Compartilhar documentação com equipe

---

## 🎉 Resultado

### Antes (sem Swagger)
- ❌ Documentação manual desatualizada
- ❌ Necessário ferramentas externas (Postman, curl)
- ❌ Difícil para novos desenvolvedores

### Depois (com Swagger)
- ✅ Documentação automática e sempre atualizada
- ✅ Interface interativa para testar
- ✅ Fácil onboarding de novos desenvolvedores
- ✅ Documentação profissional
- ✅ Geração de código cliente automática

---

**Data de Implementação**: 2025-11-08  
**Versão da API**: 1.0.0  
**SpringDoc Version**: 2.3.0  
**Status**: ✅ Implementado e Funcional

