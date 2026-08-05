# 📚 Sistema de Gerenciamento de Biblioteca

> Aplicação de console desenvolvida em Java para simular a rotina de uma biblioteca, utilizando **JPA (Jakarta Persistence)** com **Hibernate** para persistência em PostgreSQL. O projeto é uma evolução de uma versão originalmente desenvolvida com **JDBC puro**, servindo como estudo da migração para um ORM e da aplicação de boas práticas de arquitetura em camadas.

![Java](https://img.shields.io/badge/Java-25-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![JPA](https://img.shields.io/badge/JPA-3.x-6DB33F?style=for-the-badge)
![Hibernate](https://img.shields.io/badge/Hibernate-7.x-59666C?style=for-the-badge&logo=hibernate&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![Status](https://img.shields.io/badge/status-em%20desenvolvimento-yellow?style=for-the-badge)

## Índice

- [Sobre o Projeto](#sobre-o-projeto)
- [Demonstração](#demonstração)
- [Funcionalidades](#funcionalidades)
- [Aprendizados e Conceitos Praticados](#aprendizados-e-conceitos-praticados)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Como Executar o Projeto](#como-executar-o-projeto)
- [Próximos Passos / Melhorias Futuras](#próximos-passos--melhorias-futuras)
- [Autor](#autor)

## Sobre o Projeto

O **Sistema de Gerenciamento de Biblioteca (SGB)** é uma aplicação de console desenvolvida em Java, criada como projeto prático de estudos para consolidar conhecimentos em **JPA (Jakarta Persistence), Hibernate, PostgreSQL, Programação Orientada a Objetos e arquitetura em camadas** (DAO, Service, Model e View).

O sistema simula as operações do dia a dia de uma biblioteca: cadastro e gerenciamento de clientes e livros, controle de empréstimos e geração de relatórios, como o ranking dos livros mais emprestados e a quantidade de empréstimos por cliente.

A persistência é realizada por meio da **API Jakarta Persistence (JPA)**, utilizando o **Hibernate** como implementação ORM responsável pelo mapeamento entre as entidades Java e o banco de dados PostgreSQL.

## Demonstração

<table>
  <tr>
    <td><img src="src/assets/menu_principal.png" width="400"></td>
    <td><img src="src/assets/menu_clientes.png" width="400"></td>
  </tr>
  <tr>
    <td><img src="src/assets/menu_livros.png" width="400"></td>
    <td><img src="src/assets/menu_emprestimos.png" width="400"></td>
  </tr>
</table>

### Diagrama do Banco de Dados

O projeto já inclui um diagrama entidade-relacionamento do banco de dados:

![Diagrama do banco de dados](src/assets/diagram_db.svg)

## Funcionalidades

### Clientes
- Cadastrar cliente
- Listar clientes ativos
- Buscar cliente por nome
- Atualizar dados de um cliente
- Desativar cliente

### Livros
- Cadastrar livro
- Listar livros disponíveis em estoque
- Buscar livro por título
- Buscar livro por gênero
- Atualizar dados de um livro
- Remover livro

### Empréstimos
- Registrar um novo empréstimo
- Listar todos os empréstimos (com nome do cliente e do livro)
- Visualizar o **Top 5** livros mais emprestados
- Visualizar a **quantidade de empréstimos por cliente**

## Aprendizados e Conceitos Praticados

Este projeto foi utilizado como campo de prática para os seguintes conceitos:

- **Arquitetura em camadas**: separação entre `DAO`, `Service`, `Model` e `View`
- **JPA (Jakarta Persistence)** para abstração da camada de persistência
- **Hibernate ORM** como implementação da JPA
- **Mapeamento objeto-relacional (ORM)** utilizando anotações (`@Entity`, `@Table`, `@Id`, `@GeneratedValue`, `@OneToMany`, `@ManyToOne`, etc.)
- **Relacionamentos entre entidades** utilizando `@OneToMany`, `@ManyToOne` e `@JoinColumn`
- **JPQL (Java Persistence Query Language)** para consultas orientadas a objetos
- **EntityManager** para gerenciamento do ciclo de vida das entidades
- **Controle de transações** utilizando `EntityTransaction`
- **Soft Delete** para clientes através do campo `estaAtivo`
- **DTOs (Data Transfer Objects)** para consultas específicas e projeções
- **Java Records** para modelagem imutável de DTOs
- **Exceções customizadas** (`ValidacaoException`) para validações da camada de serviço
- **Collections** (`List`, `ArrayList`)
- **Organização do projeto seguindo boas práticas de arquitetura**

## Tecnologias Utilizadas

| Tecnologia                | Versão       | Finalidade                    |
|---------------------------|--------------|-------------------------------|
| Java                      | 25           | Linguagem principal           |
| Maven                     | 4.0.0        | Gerenciamento de dependências |
| PostgreSQL                | 18.4         | Banco de dados                |
| Jakarta Persistence (JPA) | 3.x          | API de persistência           |
| Hibernate ORM             | 6.6.18 Final | Implementação da JPA          |
| PostgreSQL JDBC Driver    | 42.7.13      | Driver de conexão             |
| Git & GitHub              | —            | Controle de versão            |

## Estrutura do Projeto

```text
src/
└── main/
    ├── java/
    │   └── br/
    │       └── com/
    │           └── nyk/
    │               └── sgb/
    │                   ├── DAO/
    │                   ├── database/
    │                   │   └── EntityFactory.java
    │                   ├── DTO/
    │                   ├── exception/
    │                   ├── model/
    │                   ├── service/
    │                   ├── view/
    │                   └── Main.java
    └── resources/
        └── META-INF/
            └── persistence.xml
```

## Como Executar o Projeto

### Pré-requisitos

Antes de executar a aplicação, certifique-se de possuir:

- JDK 25 ou superior
- Maven 3.8+
- PostgreSQL instalado e em execução
- Git

### 1. Clone o repositório

```bash

cd sistema-gerenciamento-biblioteca
```

### 2. Crie o banco de dados

No PostgreSQL, execute:

```sql
CREATE DATABASE biblioteca;
```

### 3. Configure a persistência

O projeto utiliza **JPA (Jakarta Persistence)** com **Hibernate** como implementação ORM.

Crie o arquivo abaixo:

```text
src/main/resources/META-INF/persistence.xml
```

e utilize a seguinte configuração:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<persistence xmlns="https://jakarta.ee/xml/ns/persistence"
             version="3.2">

    <persistence-unit name="biblioteca">

        <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>

        <class>br.com.nyk.sgb.model.Cliente</class>
        <class>br.com.nyk.sgb.model.Livro</class>
        <class>br.com.nyk.sgb.model.Emprestimo</class>

        <properties>

            <!-- Configuração do banco -->
            <property name="jakarta.persistence.jdbc.driver"
                      value="org.postgresql.Driver"/>

            <property name="jakarta.persistence.jdbc.url"
                      value="jdbc:postgresql://localhost:5432/biblioteca"/>

            <property name="jakarta.persistence.jdbc.user"
                      value="SEU_USUARIO"/>

            <property name="jakarta.persistence.jdbc.password"
                      value="SUA_SENHA"/>
            
            <!-- Configuração do HikariCP -->
            <property name="hibernate.connection.provider_class" 
                      value="org.hibernate.hikaricp.internal.HikariCPConnectionProvider" />
            
            <property name="hibernate.hikari.maximumPoolSize" 
                      value="10" />
            
            <property name="hibernate.hikari.minimumIdle" 
                      value="5" />
            
            <property name="hibernate.hikari.connectionTimeout" 
                      value="20000" />
            
            <property name="hibernate.hikari.idleTimeout" 
                      value="600000" />
            
            <property name="hibernate.hikari.maxLifetime" 
                      value="1800000" />

            <!-- Configuração do Hibernate -->
            <property name="hibernate.hbm2ddl.auto"
                      value="update"/>

            <property name="hibernate.show_sql" 
                      value="true"/>

            <property name="hibernate.format_sql"
                      value="true"/>

        </properties>

    </persistence-unit>

</persistence>
```

> **Observação:** altere apenas as propriedades `jakarta.persistence.jdbc.url`, `jakarta.persistence.jdbc.user` e `jakarta.persistence.jdbc.password` conforme o seu ambiente.

### 4. Compile o projeto

```bash
mvn clean compile
```

### 5. Execute a aplicação

Execute a classe:

```text
br.com.nyk.sgb.Main
```

pela sua IDE (IntelliJ IDEA, Eclipse ou VS Code).

---

## Próximos Passos / Melhorias Futuras

- [ ] Implementar testes automatizados (JUnit + Mockito)
- [ ] Evoluir a aplicação para uma API REST com **Spring Boot**
- [ ] Evoluir a camada de persistência para Spring Data JPA em uma API Spring Boot
- [ ] Adicionar autenticação e autorização com **Spring Security**
- [ ] Implementar paginação nas consultas
- [ ] Substituir os `System.out.println` por um framework de logging (SLF4J + Logback)
- [ ] Tornar a leitura do console mais robusta, tratando entradas inválidas
- [ ] Containerizar a aplicação e o banco de dados com Docker / Docker Compose
- [ ] Adicionar uma licença de código aberto (MIT)

## Autor

Desenvolvido por **Bruno Celestino** como projeto de estudos para aprofundar conhecimentos em Java, JPA/Hibernate, PostgreSQL e desenvolvimento backend.

[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/nykovas)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://linkedin.com/in/bruno-celestino-a5b1423a4)