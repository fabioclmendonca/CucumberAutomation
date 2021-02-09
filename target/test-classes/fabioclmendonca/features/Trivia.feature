# language: pt
Funcionalidade: cenário negativo para busca invalida
  cenário positivo para busca por categoria com validação de limite de itens por paginas e disponibilização de paginação

  Contexto: Pagina do banco de questões
    Dado Eu navego para "https://opentdb.com/"

  Cenário: Busca por questão inexistente:
    Dado que navego para a página de busca do banco de questões
    E digito "Science: Computers" no campo de busca
    Quando clico no botão de buscar
    Então visualizo uma mensagem de erro com o texto "No questions found."

  Cenário: Busca por categoria valida com máximo de 25 itens e paginação ativa
    Dado que navego para a página de busca do banco de questões
    E digito "Science: Computers" no campo de busca
    E altero o filtro do tipo de busca para "Category"
    Quando clico no botão de buscar
    Então Visualizo "25" itens encontrados na lista de questões
    E visualizo os botões de passagem de pagina

  Esquema do Cenário: Gerar requisição de serviço e conferir dados de entrada
    Dado que navego para a página de API do banco de questões
    E digito "<numero_de_questoes>" no campo de número de questões
    E seleciono a categoria "<categoria>"
    E seleciono a dificuldade "<dificuldade>"
    E seleciono o tipo "<tipo>"
    Quando clico no botão generate API URL
    Então visualizo a URL do serviço gerada
    E executo a chamada ao serviço utilizando a URL gerada validando se dados retornados no JSON conferem com filtros de entrada

    Exemplos:
    | numero_de_questoes |     categoria      | dificuldade |      tipo       |
    |         5          |       Sports       |     Easy    |   True / False  |
    |        10          | Science: Computers |     Hard    | Multiple Choice |
    |         7          |      Geography     |    Medium   |   True / False  |
    |         3          |      Animals       |     Hard    | Multiple Choice |
