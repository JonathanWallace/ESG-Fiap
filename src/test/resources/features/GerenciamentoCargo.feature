# language: pt
Funcionalidade: Gerenciamento de Cargos
  Como um usuário autenticado da API
  Eu quero poder criar, consultar e deletar cargos
  Para manter o sistema organizado.

  Contexto:
    Dado que eu sou um usuário autenticado

  Cenário: Cadastro de um novo cargo com sucesso
    Dado que eu tenha os dados de um novo cargo:
      | campo | valor              |
      | nome  | Desenvolvedor Java |
      | nivel | Pleno              |
    E que o arquivo de contrato esperado para cargo é o "Cadastro bem-sucedido de cargo"
    Quando eu enviar a requisição para o endpoint "/api/cargos" de cadastro de cargos
    Então o status code da resposta deve ser 201
    E a resposta da requisição de cargo deve estar em conformidade com o contrato selecionado

  Cenário: Tentativa de cadastro de um cargo com nome vazio
    Dado que eu tenha os dados de um novo cargo:
      | campo | valor   |
      | nome  |         |
      | nivel | Junior  |
    Quando eu enviar a requisição para o endpoint "/api/cargos" de cadastro de cargos
    Então o status code da resposta deve ser 409
    E o corpo de resposta de erro da api deve retornar a mensagem "Entidade não pode possuir dados nulos"

  Cenário: Deletar um cargo com sucesso
    Dado que eu tenha os dados de um novo cargo:
      | campo | valor              |
      | nome  | Analista de Testes |
      | nivel | Sênior             |
    Quando eu enviar a requisição para o endpoint "/api/cargos" de cadastro de cargos
    Então o status code da resposta deve ser 201
    Quando eu enviar a requisição com o ID para o endpoint "/api/cargos" de cargos
    Então o status code da resposta deve ser 204