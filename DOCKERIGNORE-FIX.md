# 🔧 Correção: .dockerignore bloqueando Maven Wrapper

## ❌ Problema

Ao tentar fazer build do Docker, ocorreu o erro:

```
error: failed to solve: failed to compute cache key: failed to calculate checksum of ref xir3asvnlrju8r27ui3sb7fb2::166tsqzsezbyb8fxtxaw85g01: "/.mvn": not found

error: exit status 1
```

## 🔍 Causa Raiz

O arquivo `.dockerignore` estava bloqueando os arquivos necessários do Maven Wrapper:

```dockerignore
# Maven
target/
.mvn/       ← BLOQUEANDO
mvnw        ← BLOQUEANDO
mvnw.cmd    ← BLOQUEANDO
```

Quando o Dockerfile tentava copiar esses arquivos:

```dockerfile
COPY mvnw .
COPY .mvn .mvn
```

O Docker não conseguia encontrá-los porque estavam no `.dockerignore`.

---

## ✅ Solução

Atualizei o `.dockerignore` para **NÃO** bloquear o Maven Wrapper:

```dockerignore
# Maven
target/
# NOTE: .mvn, mvnw and mvnw.cmd are needed for Docker build!
# .mvn/       ← COMENTADO (não bloquear)
# mvnw        ← COMENTADO (não bloquear)
# mvnw.cmd    ← COMENTADO (não bloquear)
```

---

## 📝 Entendendo o Maven Wrapper

### O que é o Maven Wrapper?

O Maven Wrapper (`mvnw`) é um conjunto de scripts que:
1. ✅ Garante que todos usem a mesma versão do Maven
2. ✅ Baixa automaticamente o Maven se não estiver instalado
3. ✅ Não requer Maven pré-instalado no sistema

### Arquivos do Maven Wrapper

```
projeto/
├── mvnw              ← Script para Linux/Mac
├── mvnw.cmd          ← Script para Windows
└── .mvn/
    └── wrapper/
        ├── maven-wrapper.properties  ← Configuração (versão, URL)
        └── maven-wrapper.jar         ← Opcional (pode ser baixado)
```

### Configuração Atual

```properties
# .mvn/wrapper/maven-wrapper.properties
wrapperVersion=3.3.4
distributionType=only-script
distributionUrl=https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/3.9.11/apache-maven-3.9.11-bin.zip
```

- `distributionType=only-script`: O JAR será baixado quando necessário
- Maven 3.9.11 será baixado automaticamente

---

## 📋 .dockerignore Correto

### ✅ O que DEVE ser ignorado

```dockerignore
# Build outputs (não precisamos no contexto do Docker)
target/

# IDEs (não precisamos no container)
.idea/
.vscode/
*.iml

# Git (não precisamos no container)
.git/
.gitignore
.gitattributes

# Logs (não precisamos no build)
*.log
logs/

# Documentação (não precisa no container)
README.md
docs/
*.md

# Testes (pulamos com -DskipTests)
src/test/
```

### ❌ O que NÃO DEVE ser ignorado

```dockerignore
# ❌ NÃO ignore Maven Wrapper (necessário para build)
# .mvn/
# mvnw
# mvnw.cmd

# ❌ NÃO ignore código fonte (óbvio)
# src/

# ❌ NÃO ignore configuração do Maven (necessário para build)
# pom.xml
```

---

## 🔄 Comparação: .gitignore vs .dockerignore

### `.gitignore` (o que não vai para o Git)

```gitignore
# Bloqueia arquivos gerados/temporários
target/                    ✅ Correto (arquivos gerados)
.mvn/wrapper/maven-wrapper.jar  ✅ Correto (será baixado)
*.log                      ✅ Correto (logs temporários)
.idea/                     ✅ Correto (config de IDE)
```

**Objetivo**: Manter o repositório limpo, sem arquivos gerados

### `.dockerignore` (o que não vai para o Docker build)

```dockerignore
# Bloqueia apenas o que NÃO é necessário no build
target/                    ✅ Correto (será recriado no build)
.mvn/                      ❌ ERRADO (necessário para build)
mvnw                       ❌ ERRADO (necessário para build)
mvnw.cmd                   ❌ ERRADO (necessário para build)
README.md                  ✅ Correto (não usado no build)
.git/                      ✅ Correto (não usado no build)
```

**Objetivo**: Reduzir contexto do build, mas manter tudo que é necessário

---

## 🚀 Testando a Correção

### 1. Verificar o .dockerignore

```bash
cat .dockerignore
```

Deve mostrar Maven Wrapper **comentado**:

```dockerignore
# NOTE: .mvn, mvnw and mvnw.cmd are needed for Docker build!
# .mvn/
# mvnw
# mvnw.cmd
```

### 2. Testar Build Local

```bash
# Build
docker build -t sistema-biblioteca .

# Deve mostrar:
# Step X/XX : COPY mvnw .
# Step X/XX : COPY .mvn .mvn
# ✅ SEM ERROS
```

### 3. Verificar que arquivos foram copiados

```bash
# Inspecionar a imagem
docker run --rm sistema-biblioteca ls -la /app/

# Deve mostrar:
# -rwxr-xr-x 1 spring spring 12K mvnw
# drwxr-xr-x 3 spring spring 4.0K .mvn
```

---

## 📊 .dockerignore Otimizado Final

```dockerignore
# =================================
# .dockerignore Otimizado
# Sistema Biblioteca
# =================================

# Build outputs (serão recriados)
target/
build/

# IDEs (não necessários no container)
.idea/
.vscode/
*.iml
*.iws
*.ipr
.classpath
.project
.settings/
.springBeans
.sts4-cache

# Git (não necessário no build)
.git/
.gitignore
.gitattributes
.github/

# Logs (não necessários no build)
*.log
logs/

# Sistema (não necessários)
.DS_Store
Thumbs.db

# Documentação (não necessária no runtime)
*.md
!README.md
docs/

# Testes (pulamos com -DskipTests)
src/test/

# Temporários
*.tmp
*.swp
*.bak

# =================================
# IMPORTANTE: NÃO ignore Maven Wrapper!
# Os arquivos abaixo SÃO necessários para o build
# =================================
# .mvn/
# mvnw
# mvnw.cmd
# pom.xml
# src/
```

---

## 🎯 Resultado

### Antes (com erro)
```bash
docker build -t sistema-biblioteca .
# ❌ error: "/.mvn": not found
```

### Depois (funcionando)
```bash
docker build -t sistema-biblioteca .
# ✅ Step 1/16 : FROM maven:3.9.5-eclipse-temurin-17-alpine AS builder
# ✅ Step 2/16 : WORKDIR /app
# ✅ Step 3/16 : COPY mvnw .
# ✅ Step 4/16 : COPY .mvn .mvn
# ✅ ... build completo com sucesso!
```

---

## 📝 Checklist de Verificação

Após a correção, verifique:

- [x] `.dockerignore` não bloqueia `.mvn/`
- [x] `.dockerignore` não bloqueia `mvnw`
- [x] `.dockerignore` não bloqueia `mvnw.cmd`
- [x] `.dockerignore` não bloqueia `pom.xml`
- [x] `.dockerignore` não bloqueia `src/`
- [x] Build local funciona: `docker build -t sistema-biblioteca .`
- [ ] Push para repositório: `git push origin main`
- [ ] Deploy no Render funciona

---

## 🆘 Se o Problema Persistir

### Limpar cache do Docker

```bash
# Limpar cache de build
docker builder prune -a

# Rebuild sem cache
docker build --no-cache -t sistema-biblioteca .
```

### Verificar contexto do build

```bash
# Ver o que está sendo enviado para o Docker
docker build --progress=plain -t sistema-biblioteca . 2>&1 | grep "COPY"
```

### Verificar permissões do mvnw

```bash
# No Windows (PowerShell)
git update-index --chmod=+x mvnw

# No Linux/Mac
chmod +x mvnw
```

---

## 🎓 Lições Aprendidas

1. **`.dockerignore` ≠ `.gitignore`**: Propósitos diferentes
2. **Maven Wrapper é essencial**: Não deve ser bloqueado no Docker
3. **Sempre teste localmente**: Antes de fazer deploy
4. **Use `--progress=plain`**: Para debug de builds
5. **Entenda cada linha**: Do `.dockerignore` e `.gitignore`

---

**Data**: 2025-11-08  
**Status**: ✅ Corrigido  
**Tempo para corrigir**: < 5 minutos  
**Impacto**: Build agora funciona com Maven Wrapper

