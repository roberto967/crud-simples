# Pequeno CRUD de produtos

## Como rodar

No linux certifique-se de ter o docker-compose instalado, após isso é só dar o seguinte comando na primeira execução:
`mvn clean package && sudo docker-compose up --build`

execuções posteriores:
`sudo docker-compose up`

Caso faça alguma modificação:
`sudo mvn clean package && sudo docker-compose up --build`

---

### Como acessar o cadastro

Uma vez iniciado o docker o acesso ao cadastro se dá por meio do endereço, uma aplicação react: `http://localhost:3000/`
Mas, além disso o backend roda com Spring e conexão com SQL fornecendo os endpoints:

- **GET** `http://localhost:8080/produtos` - Que retorna uma lista contendo todos os produtos cadastrados na base de dados.
- **POST** `http://localhost:8080/produtos` - Que serve para inserir um produto ou uma lista de produtos no banco.
- **GET** `http://localhost:8080/produtos/{id}` - Serve para consultar um produto cadastrado banco por meio do id.
- **DELETE** `http://localhost:8080/produtos/{id}/` - Serve para deletar um produto cadastrado no banco por meio de um id.
- **GET** `http://localhost:8080/produtos/busca/{nome}` - Serve para consultar um produto cadastrado banco por meio do nome cadastrado (aceitando substrings).

---

### Cadastro

- Formulário com os campos abaixo:

  - Nome do produto - campo de texto
  - Descrição do produto - campo de texto
  - Valor do produto - campo de valor
  - Disponível para venda - campo com 2 opções: sim / não

---

#### Listagem

- Colunas da listagem: nome, valor
- Ordenação por valor do menor para o maior
- Quando cadastrar um novo produto é para abrir a listagem automaticamente
- Deve existir um botão para cadastrar um novo produto a partir da listagem
