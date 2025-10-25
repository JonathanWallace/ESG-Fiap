# language: pt
Funcionalidade: Gerenciamento de Departamentos
  Como um usuário autenticado da API
  Eu quero poder criar e deletar departamentos
  Para manter os registros do sistema organizados.

  Contexto:
    Dado que eu sou um usuário autenticado

  Cenário: Cadastro de um novo departamento com sucesso
    Dado que eu tenha os dados de um novo departamento:
      | campo | valor            |
      | nome  | Recursos Humanos |
    E que o arquivo de contrato esperado para departamento é o "Cadastro bem-sucedido de departamento"
    Quando eu enviar a requisição para o endpoint "/api/departamentos" de cadastro de departamentos
    Então o status code da resposta deve ser 201
    E a resposta da requisição de departamento deve estar em conformidade com o contrato selecionado

  Cenário: Tentativa de cadastro de um departamento com nome vazio
    Dado que eu tenha os dados de um novo departamento:
      | campo | valor |
      | nome  |       |
    Quando eu enviar a requisição para o endpoint "/api/departamentos" de cadastro de departamentos
    Então o status code da resposta deve ser 409
    E o corpo de resposta de erro da api deve retornar a mensagem "Entidade não pode possuir dados nulos"

  Cenário: Deletar um departamento com sucesso
    Dado que eu tenha os dados de um novo departamento:
      | campo | valor               |
      | nome  | TI - A ser deletado |
    Quando eu enviar a requisição para o endpoint "/api/departamentos" de cadastro de departamentos
    Então o status code da resposta deve ser 201
    Quando eu enviar a requisição com o ID para o endpoint "/api/departamentos" de departamentos
    Então o status code da resposta deve ser 204