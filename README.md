# Sistema de Gerenciamento de Biblioteca

Este é um sistema de gerenciamento de biblioteca desenvolvido em Java, com conexão a um banco de dados MySQL. O sistema permite o cadastro de usuários, cadastro de livros, empréstimos de livros e devoluções.

## Requisitos

- Java 8 ou superior
- MySQL (ou MariaDB)
- Um IDE Java (como IntelliJ IDEA, Eclipse ou NetBeans)

## Passos para execução

### 1. Clonar o Repositório

Primeiro, faça o clone deste repositório para sua máquina local:

```bash
git clone https://github.com/mathcarstens/ProjetoPOO.git
```
### 2. Acesse o MySQL Workbench ou qualquer outra ferramenta que você use para conectar ao MySQL
### 3. Crie o banco de dados e as tabelas:
No MySQL, crie o banco de dados e as tabelas executando os scripts SQL contidos na pasta Banco de Dados/:

#### biblioteca_usuarios.sql: 
Criação da tabela usuarios.
#### biblioteca_livros.sql: 
Criação da tabela livros.
#### biblioteca_emprestimos.sql: 
Criação da tabela emprestimos.
#### Para isso, execute os seguintes comandos:
```sql
-- Criar o banco de dados
CREATE DATABASE IF NOT EXISTS biblioteca;
USE biblioteca;

-- Executar os scripts para criar as tabelas
SOURCE caminho/para/o/repositorio/banco_de_dados/biblioteca_usuarios.sql;
SOURCE caminho/para/o/repositorio/banco_de_dados/biblioteca_livros.sql;
SOURCE caminho/para/o/repositorio/banco_de_dados/biblioteca_emprestimos.sql;
```
### 4. Configuração de Conexão
No código, a conexão com o banco de dados é feita usando o MySQL. O arquivo Conexao.java contém a URL de conexão, que pode ser ajustada conforme necessário. Abaixo estão as informações de conexão:

#### Host: localhost
#### Porta: 3306 
#### Usuário: root 
#### Senha: Woody2005 

### 5. Testando a Conexão
Após configurar o banco de dados e a conexão, você pode testar o funcionamento do sistema executando o código. A interface do sistema irá solicitar entradas de usuário e fornecer as opções de gerenciamento de livros e empréstimos.
