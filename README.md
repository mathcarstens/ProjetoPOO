# ProjetoPOO
# Relatório - Sistema de Gerenciamento de Biblioteca

Este relatório descreve a implementação de um Sistema de Gerenciamento de Biblioteca desenvolvido em Java, utilizando os conceitos de Programação Orientada a Objetos (OO). A seguir, explicarei como os conceitos de OO foram aplicados, exemplos de código que ilustram herança, polimorfismo e encapsulamento, além de uma análise dos padrões utilizados no projeto e os motivos de escolhas feitas durante o desenvolvimento.

## 1. Conceitos de Orientação a Objetos (OO) no Sistema

O sistema foi projetado para gerenciar o cadastro de usuários, livros e empréstimos de uma biblioteca. Através da programação orientada a objetos, foi possível estruturar o código de forma modular e organizada, garantindo maior flexibilidade, reutilização de código e manutenção facilitada.

### Aplicação de Encapsulamento

O encapsulamento foi aplicado para proteger os dados sensíveis das classes, ou seja, variáveis que representam as propriedades de objetos foram mantidas privadas. O acesso a essas propriedades foi feito através de métodos públicos conhecidos como *getters* e *setters*. Isso garante que os dados sejam manipulados de maneira controlada.

Exemplo de encapsulamento:

```java
public class Livro {
    private int id;
    private String titulo;
    private String autor;
    private boolean disponivel;

    // Construtor
    public Livro(int id, String titulo, String autor, boolean disponivel) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.disponivel = disponivel;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public boolean isDisponivel() {
        return disponivel;
    }
}
```
### Aplicação de Herança

A herança foi aplicada no sistema ao criar uma estrutura de classes que permite a reutilização de código. Um exemplo seria se houvesse um tipo de Usuário que fosse Aluno e outro tipo de Usuário que fosse Professor. No entanto, como no exercício os dois tipos de usuário são tratados da mesma forma no código, não foi necessário criar uma hierarquia de classes, mas o sistema está pronto para suportar essa expansão no futuro.

Exemplo de herança:

```java
public class Usuario {
    private int id;
    private String nome;
    private String tipo;

    public Usuario(int id, String nome, String tipo) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }
}

public class Aluno extends Usuario {
    public Aluno(int id, String nome) {
        super(id, nome, "aluno");
    }
}

public class Professor extends Usuario {
    public Professor(int id, String nome) {
        super(id, nome, "professor");
    }
}
```

### Aplicação de Polimorfismo

O polimorfismo foi utilizado de forma implícita, permitindo que métodos em subclasses sobrescrevessem métodos da classe pai. No sistema, caso fosse necessário tratar usuários de maneira diferenciada entre alunos e professores, poderíamos usar o polimorfismo para modificar comportamentos de acordo com o tipo de usuário.

Exemplo de polimorfismo:

```java
public class Usuario {
    public void verificarLimiteDeEmprestimos() {
        System.out.println("Limite de empréstimos padrão.");
    }
}

public class Aluno extends Usuario {
    @Override
    public void verificarLimiteDeEmprestimos() {
        System.out.println("Limite de 3 empréstimos.");
    }
}

public class Professor extends Usuario {
    @Override
    public void verificarLimiteDeEmprestimos() {
        System.out.println("Limite de 5 empréstimos.");
    }
}
```
## 2. Padrões de Projeto Utilizados
Durante o desenvolvimento do sistema, foram adotados alguns padrões de design para garantir que o código fosse modular, reutilizável e fácil de manter. Abaixo estão os padrões mais importantes aplicados:

### Padrão de Projeto Singleton
O padrão Singleton foi utilizado para garantir que apenas uma instância da classe de conexão com o banco de dados (Conexao) fosse criada durante toda a execução do sistema, evitando a criação excessiva de conexões. Isso melhora o desempenho e a eficiência do sistema.

Exemplo de código:
```java
public class Conexao {
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "sua_senha");
        }
        return connection;
    }
}
```
### Padrão DAO (Data Access Object)
O padrão DAO foi utilizado para separar a lógica de acesso ao banco de dados da lógica de negócio, organizando a aplicação e facilitando a manutenção. Cada serviço relacionado ao banco de dados (como LivroService e EmprestimoService) tem uma responsabilidade de gerenciar os dados através de métodos que interagem diretamente com o banco.

Exemplo de código:
```java
public class LivroService {

    public void cadastrarLivro(Livro livro) {
        String sql = "INSERT INTO livros (titulo, autor, disponivel) VALUES (?, ?, ?)";
        
        try (Connection conn = Conexao.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setBoolean(3, livro.isDisponivel());
            stmt.executeUpdate();
            System.out.println("Livro cadastrado com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar livro: " + e.getMessage());
        }
    }
}
```
## 3. Desafios Enfrentados
Durante o desenvolvimento do sistema, enfrentei dificuldades principalmente na parte de integração do código com o banco de dados. Inicialmente, não conseguia fazer com que os dados fossem persistidos corretamente ou que as operações de leitura e escrita no banco de dados funcionassem de forma eficiente.

Após revisar a documentação e testar várias abordagens, consegui corrigir as falhas de conexão e comunicação com o banco, mas foi um processo demorado, principalmente devido a problemas com a configuração de drivers JDBC e a manipulação das queries SQL. Esse foi o maior obstáculo que enfrentei durante o desenvolvimento.

## 4. Conclusão
O Sistema de Gerenciamento de Biblioteca foi desenvolvido utilizando os conceitos de Programação Orientada a Objetos, como encapsulamento, herança e polimorfismo. Além disso, foram aplicados padrões de design como Singleton e DAO para garantir que o código fosse modular e fácil de manter. A implementação do sistema atende aos requisitos do exercício, embora algumas funcionalidades adicionais possam ser exploradas em versões futuras, como a inclusão de campos de data de empréstimo e devolução.

O código está organizado de forma a permitir fácil expansão e manutenção, e a estrutura geral do sistema está bem definida, permitindo a implementação de novos requisitos de forma eficiente.
