# language: pt

Funcionalidade: Cadastro e Consulta de Pessoas
  Como um usuario consumidor da API.
  Quero cadastrar e filtrar dados do cadastro de pessoas.
  Para encontrar os cadastros.



  @RegressionTest
  Esquema do Cenário:  Cadastrar Pessoa Com Sucesso
    Dado que desejo cadastrar uma pessoa
    Quando consulto o ddd <ddd> e numero <numero>
    E realizo o post em /pessoas
    Entao deve ser exibido o status code <status>
    E a resposta deve conter todos os dados da pessoa cadastrada
    Exemplos:
      | ddd|numero     |status|
      |"11"|"949041329"|201   |

  @SmokeTest
  Esquema do Cenário:  Consultar pessoa ja cadastrada pelo telefone
    Dado possuo o cadastro da pessoa com ddd <ddd> e numero <numero>
    Quando realizo o get em /pessoas informando o ddd <ddd> e numero <numero>
    Entao deve ser exibido o status code <status>
    E a resposta deve conter o <ddd> e numero <numero>
    Exemplos:
      | ddd|numero     |status|
      |"11"|"949041329"|200   |

  @SmokeTest
  Esquema do Cenário:  Consultar pessoa não cadastrada pelo telefone
    Dado que não possuo cadastro da pessoa com ddd <ddd> e numero <numero>
    Quando realizo o get em /pessoas informando o ddd <ddd> e numero <numero>
    Entao deve ser exibido o status code <status>
    E a resposta deve conter a mensagem de erro informando que o ddd <ddd> e numero <numero> não foi encontrado
    Exemplos:
      | ddd|numero     |status|
      |"11"|"949001111"|404   |



  @SmokeTest
  Esquema do Cenário:  Validar Excessao de pessoa ja cadastrada Pelo Telefone
    Dado possuo o cadastro da pessoa com ddd <ddd> e numero <numero>
    Quando realizo o post para cadastrar a mesma pessoa
    Entao deve ser exibido o status code <status>
    Exemplos:
      | ddd|numero     |status|
      |"11"|"949041329"|400   |

  @SmokeTest
  Esquema do Cenário:  Validar Excessao de pessoa ja cadastrada Pelo CPF
    Dado possuo o cadastro da pessoa com cpf <cpf>
    Quando realizo o post para cadastrar a mesma pessoa
    Entao deve ser exibido o status code <status>
    E a mensagem de pessoa ja cadastrada com este cpf <cpf>
    Exemplos:
      | cpf   |status|
      |"12345678909"|400   |

  @RegressionTest
  Esquema do Cenário:  Validar Excessao de pessoa informando nome com muitos caracteres (Teste Destrutivo)
    Dado que desejo cadastrar uma pessoa com nome de muitos caracteres
    Quando realizo post em pessoa informando um body invalido
    Entao deve ser exibido o status code <status>
    E a resposta informando o preenchimento invalido
    Exemplos:
      |status|
      |400   |


