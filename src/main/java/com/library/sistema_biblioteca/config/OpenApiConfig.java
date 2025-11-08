package com.library.sistema_biblioteca.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Configuração do OpenAPI (Swagger) para documentação da API
 * 
 * Esta classe configura a documentação interativa da API usando SpringDoc OpenAPI 3.0
 * 
 * @author Sistema Biblioteca
 * @version 1.0
 */
@Configuration
public class OpenApiConfig {

    @Value("${spring.application.name:Sistema Biblioteca API}")
    private String applicationName;

    @Value("${spring.application.version:1.0.0}")
    private String applicationVersion;

    @Value("${server.port:8080}")
    private String serverPort;

    /**
     * Configura a documentação OpenAPI da API
     * 
     * @return Objeto OpenAPI configurado
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(apiInfo())
                .servers(apiServers());
    }

    /**
     * Define as informações básicas da API
     */
    private Info apiInfo() {
        return new Info()
                .title("Sistema Biblioteca API")
                .version("1.0.0")
                .description("""
                        # Sistema de Gerenciamento de Biblioteca
                        
                        API RESTful para gerenciamento completo de biblioteca, incluindo:
                        
                        ## Recursos Disponíveis
                        
                        ### 📚 Livros
                        - Cadastro, consulta, atualização e exclusão de livros
                        - Busca por título, autor e ISBN
                        - Controle de disponibilidade
                        
                        ### ✍️ Autores
                        - Gerenciamento completo de autores
                        - Informações biográficas
                        - Relacionamento com livros
                        
                        ### 📖 Empréstimos
                        - Controle de empréstimos de livros
                        - Gestão de devoluções
                        - Histórico de empréstimos
                        
                        ## Tecnologias
                        - Spring Boot 3.5.7
                        - Java 17
                        - PostgreSQL
                        - SpringDoc OpenAPI 3.0
                        
                        ## Como Usar
                        1. Explore os endpoints disponíveis abaixo
                        2. Clique em "Try it out" para testar
                        3. Preencha os parâmetros necessários
                        4. Clique em "Execute"
                        
                        ## Códigos de Status
                        - `200`: Sucesso
                        - `201`: Criado
                        - `400`: Requisição inválida
                        - `404`: Recurso não encontrado
                        - `500`: Erro interno do servidor
                        """)
                .contact(apiContact())
                .license(apiLicense());
    }

    /**
     * Define as informações de contato
     */
    private Contact apiContact() {
        return new Contact()
                .name("Sistema Biblioteca - Suporte")
                .email("suporte@biblioteca.com")
                .url("https://github.com/DjalmaDeveloper/bibliotecadjr");
    }

    /**
     * Define a licença da API
     */
    private License apiLicense() {
        return new License()
                .name("MIT License")
                .url("https://opensource.org/licenses/MIT");
    }

    /**
     * Define os servidores disponíveis
     */
    private List<Server> apiServers() {
        // Servidor de Produção (Render)
        Server prodServer = new Server()
                .url("https://sistema-biblioteca-api.onrender.com")
                .description("Servidor de Produção (Render)");

        // Servidor Local
        Server devServer = new Server()
                .url("http://localhost:" + serverPort)
                .description("Servidor Local (Desenvolvimento)");

        return List.of(prodServer, devServer);
    }
}

