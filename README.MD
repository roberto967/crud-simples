# Pequeno CRUD de produtos

## Como rodar

No linux certifique-se de ter o docker-compose instalado, após isso é só dar o seguinte comando na primeira execução:
`mvn clean package && sudo docker-compose up --build`

execuções posteriores:
`sudo docker-compose up`

Caso faça alguma modificação:
`sudo mvn clean package && sudo docker-compose up --build`

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
