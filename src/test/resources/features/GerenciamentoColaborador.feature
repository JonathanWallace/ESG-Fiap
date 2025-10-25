# language: pt
Funcionalidade: Gerenciamento de Colaboradores
  Como um usuário autenticado da API
  Eu quero poder criar e deletar colaboradores
  Para manter os registros do sistema atualizados.

  Contexto: Criação dos pré-requisitos para os cenários de Colaborador
    Dado que eu sou um usuário autenticado
    E que eu tenha os dados de um novo departamento:
      | campo | valor                |
      | nome  | DepartamentoTeste |
    Quando eu enviar a requisição para o endpoint "/api/departamentos" de cadastro de departamentos
    Então o status code da resposta deve ser 201

    E que eu tenha os dados de um novo cargo:
      | campo | valor         |
      | nome  | CargoTeste |
      | nivel | Pleno         |
    Quando eu enviar a requisição para o endpoint "/api/cargos" de cadastro de cargos
    Então o status code da resposta deve ser 201

  Cenário: Cadastro de um novo colaborador com sucesso
    Dado que eu tenha os dados de um novo colaborador e associe as dependências do contexto:
      | campo               | valor          |
      | nome                | Ana Silva      |
      | raca                | Branca         |
      | genero              | Feminino       |
      | dataNascimento      | 1990-05-15     |
      | dataAdmissao        | 2023-01-10     |
      | salario             | 7500.50        |
      | possuiDeficiencia   | false          |
      | orientacaoSexual    | Heterossexual  |
    E que o arquivo de contrato esperado para colaborador é o "Cadastro bem-sucedido de colaborador"
    Quando eu enviar a requisição para o endpoint "/api/colaboradores" de cadastro de colaboradores
    Então o status code da resposta deve ser 201
    E a resposta da requisição de colaborador deve estar em conformidade com o contrato selecionado

  Cenário: Tentativa de cadastro de um colaborador com nome vazio
    Dado que eu tenha os dados de um novo colaborador e associe as dependências do contexto:
      | campo               | valor          |
      | nome                |                |
      | raca                | Parda          |
      | genero              | Masculino      |
      | dataNascimento      | 1995-11-20     |
      | dataAdmissao        | 2024-03-01     |
      | salario             | 4200.00        |
      | possuiDeficiencia   | false          |
      | orientacaoSexual    | Homossexual    |
    Quando eu enviar a requisição para o endpoint "/api/colaboradores" de cadastro de colaboradores
    Então o status code da resposta deve ser 409
    E o corpo de resposta de erro da api deve retornar a mensagem "Entidade não pode possuir dados nulos"

  Cenário: Deletar um colaborador com sucesso
    Dado que eu tenha os dados de um novo colaborador e associe as dependências do contexto:
      | campo               | valor           |
      | nome                | Carlos Pereira  |
      | raca                | Preta           |
      | genero              | Masculino       |
      | dataNascimento      | 1988-02-25      |
      | dataAdmissao        | 2022-07-18      |
      | salario             | 9800.00         |
      | possuiDeficiencia   | false           |
      | orientacaoSexual    | Bissexual       |
    Quando eu enviar a requisição para o endpoint "/api/colaboradores" de cadastro de colaboradores
    Então o status code da resposta deve ser 201
    Dado que eu recupere o ID do colaborador criado no contexto
    Quando eu enviar a requisição com o ID para o endpoint "/api/colaboradores" de colaboradores
    Então o status code da resposta deve ser 204