Todos os casos de testes mapeados estão em em src/test/resources/features/pessoas.feature

A classe Runner executa todos os testes.



Bug encontrado:

Título: Informar nome com muitos caracteres está retornando status code 500.

Descrição: O cenário: Validar Excessao de pessoa informando nome com muitos caracteres (Teste Destrutivo), possui uma inconsistência, pois ao informar um nome com muitos caracretes (neste caso foram 600) está retornando status code 500 ao invés de uma resposta tratada de status code 400.



Ponto de melhoria: Podemos também verificar um possível ajuste no código fonte da aplicação onde está sendo utilizado um método Post para realizar uma consulta: Linhas 54 - 56 da classe PessoaResource @PostMapping("/filtrar") @Transactional public ResponseEntity<List> filtrar(@RequestBody PessoaFiltro filtro) {

