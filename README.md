Todos os casos de testes mapeados estão em em src/test/resources/features/pessoas.feature

A classe Runner executa todos os testes.


O cenário Esquema do Cenário:  Validar Excessao de pessoa informando nome com muitos caracteres (Teste Destrutivo)
possui uma inconsistência, pois está retornando status code 500 ao invés de tratar e retornar 400.

Podemos também verificar um possível ajuste no código fonte da aplicação onde está sendo utilizado um método Post para realizar uma consulta:
Linhas 54 - 56 da classe PessoaResource
@PostMapping("/filtrar")
	@Transactional
	public ResponseEntity<List<Pessoa>> filtrar(@RequestBody PessoaFiltro filtro) {


