import React, { useState } from "react";
import {
  Container,
  Form,
  Button,
  Card,
  FormLabel,
  Row,
  Col,
  Modal,
} from "react-bootstrap";
import axios from "axios";

function Home() {
  const [mostrarLista, setMostrarLista] = useState(false);
  const [nome, setNome] = useState("");
  const [descricao, setDescricao] = useState("");
  const [valorTemporario, setValorTemporario] = useState("");
  const [disponibilidade, setDisponibilidade] = useState(false);
  const [listaProdutos, setListaProdutos] = useState([]);
  const [valorErro, setValorErro] = useState(false);
  const [nomeErro, setNomeErro] = useState(false);
  const [descricaoErro, setDescricaoErro] = useState(false);

  // Carrega da api a lista ordenada sem precisar regarregar a pag
  const fetchProdutos = async () => {
    try {
      const response = await axios.get("http://localhost:8080/produtos");
      setListaProdutos(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  const toggleMostrarLista = () => {
    fetchProdutos();
    setMostrarLista(!mostrarLista);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    let temErro = false;

    // Verifica o campo de valor do produto
    if (
      !valorTemporario ||
      isNaN(parseFloat(valorTemporario.replace(",", "."))) ||
      !/^\d+(\.\d+)?$/.test(valorTemporario)
    ) {
      setValorErro(true);
      temErro = true;
    } else {
      setValorErro(false);
    }

    // Verifica o campo de nome do produto
    if (!nome) {
      setNomeErro(true);
      temErro = true;
    } else {
      setNomeErro(false);
    }

    // Verifica o campo de descrição do produto
    if (!descricao) {
      setDescricaoErro(true);
      temErro = true;
    } else {
      setDescricaoErro(false);
    }

    if (temErro) {
      return;
    }

    const novoProduto = {
      nome: nome.trim(),
      descricao: descricao.trim(),
      valor: parseFloat(valorTemporario.replace(",", ".")),
      disponibilidade: disponibilidade,
    };

    try {
      const response = await axios.post(
        "http://localhost:8080/produtos",
        novoProduto
      );
      setListaProdutos([...listaProdutos, response.data]);

      // Limpa os campos do formulário
      setNome("");
      setDescricao("");
      setValorTemporario("");
      setDisponibilidade(false);

      // Atualiza a lista de produtos
      fetchProdutos();
    } catch (error) {
      console.error(error);
    }
    toggleMostrarLista();
  };

  const formatarValor = (valor) => {
    return valor.toLocaleString("pt-BR", {
      minimumFractionDigits: 2,
      maximumFractionDigits: 2,
      style: "currency",
      currency: "BRL",
    });
  };

  return (
    <Container className="mx-auto">
      <Container>
        <Form>
          <Form.Group className="mb-3">
            <Form.Label>Nome do produto</Form.Label>
            <Form.Control
              type="text"
              placeholder="Insira o nome do produto"
              value={nome}
              onChange={(e) => setNome(e.target.value)}
              isInvalid={nomeErro}
            />
            {nomeErro && (
              <Form.Control.Feedback type="invalid">
                O campo nome não pode estar vazio.
              </Form.Control.Feedback>
            )}
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Descrição do produto</Form.Label>
            <Form.Control
              as="textarea"
              rows={3}
              placeholder="Insira a descrição do produto"
              value={descricao}
              onChange={(e) => setDescricao(e.target.value.trimStart())}
              isInvalid={descricaoErro}
            />
            {descricaoErro && (
              <Form.Control.Feedback type="invalid">
                O campo descrição não pode estar vazio.
              </Form.Control.Feedback>
            )}
          </Form.Group>

          <Form.Group>
            <FormLabel>Valor do produto</FormLabel>
            <Form.Control
              type="text"
              placeholder="Insira o preço do produto"
              value={valorTemporario}
              onChange={(e) => setValorTemporario(e.target.value.trim())}
              isInvalid={valorErro}
            />
            {valorErro && (
              <Form.Control.Feedback type="invalid">
                O campo valor do produto não pode estar vazio e deve ser um
                número válido.
              </Form.Control.Feedback>
            )}
          </Form.Group>

          <Form.Label>Disponível para venda:</Form.Label>
          <Form.Group key={`inline-radio`} className="mb-3">
            <Form.Check
              inline
              label="Sim"
              type="radio"
              checked={disponibilidade}
              onChange={() => setDisponibilidade(true)}
            />
            <Form.Check
              inline
              label="Não"
              type="radio"
              checked={!disponibilidade}
              onChange={() => setDisponibilidade(false)}
            />
          </Form.Group>

          <Button variant="primary" type="button" onClick={handleSubmit}>
            Cadastrar
          </Button>
        </Form>
      </Container>

      <Container className="mx-auto">
        <h2 style={{ paddingTop: "1em" }}>Listagem:</h2>

        <Button onClick={toggleMostrarLista} className="mt-3">
          Mostrar Lista
        </Button>

        <Modal
          show={mostrarLista}
          onHide={toggleMostrarLista}
          centered
          size="xl"
          scrollable
        >
          <Modal.Header closeButton>
            <Modal.Title>Listagem:</Modal.Title>
          </Modal.Header>
          <Modal.Body style={{ maxHeight: "60vh", overflowY: "auto" }}>
            {listaProdutos.length > 0 ? (
              <Row className="justify-content-md-between justify-content-xxl-center">
                {listaProdutos.map((produto) => (
                  <Col xxl="3" key={produto.id}>
                    <Card className="m-2">
                      <Card.Body>
                        <Card.Title>{produto.nome}</Card.Title>
                        <Card.Text className="border rounded">
                          {produto.descricao}
                        </Card.Text>
                        <Card.Text>{formatarValor(produto.valor)}</Card.Text>
                        <Card.Text>
                          Disponível para venda:{" "}
                          {produto.disponibilidade ? "Sim" : "Não"}
                        </Card.Text>
                      </Card.Body>
                    </Card>
                  </Col>
                ))}
              </Row>
            ) : (
              <p>Nenhum produto disponível.</p>
            )}
          </Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={toggleMostrarLista}>
              Fechar
            </Button>
          </Modal.Footer>
        </Modal>
      </Container>
    </Container>
  );
}

export default Home;
