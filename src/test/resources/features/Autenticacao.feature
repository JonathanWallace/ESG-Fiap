# language: pt
Funcionalidade: Autenticação na API
  Como um cliente da API
  Quero me autenticar para obter um token de acesso
  Para que eu possa acessar os endpoints protegidos

  Cenário: Obter token de autenticação com credenciais válidas
    Dado que eu tenha as credenciais de um usuário válido
      | email | jonathan233@email.com |
      | senha | 124589                |
    Quando eu enviar a requisição para o endpoint "/auth/login" de autenticação
    Então o status code da resposta do login deve ser 200
    E o corpo da resposta deve conter um token de autenticação

  Cenário: Tentativa de obter token de autenticação com credenciais inválidas
    Dado que eu tenha as credenciais de um usuário válido
      | email | jonathan233@email.co |
      | senha | 124589               |
    Quando eu enviar a requisição para o endpoint "/auth/login" de autenticação
    Então o status code da resposta do login deve ser 403