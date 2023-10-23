package View;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import Database.BancoDeDados;
import Domain.Cliente;
import Domain.Empresa;
import Domain.Produto;
import Domain.Usuario;
import Domain.Venda;
import service.LoginUserService;
import service.VendasService;

public class Main {

	
	private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        BancoDeDados.geradorBD();
    }

    public static void executar(List<Usuario> usuarios, List<Cliente> clientes, List<Empresa> empresas,
                                List<Produto> produtos, List<Produto> carrinho, List<Venda> vendas) {

        Usuario usuarioLogado = LoginUserService.realizarLogin(usuarios);

        if (usuarioLogado.IsEmpresa()) {
            var escolha= exibirMenuEnt();
            switch (escolha) {
                case 1: {
                    System.out.println();
                    System.out.println("************************************************************");
                    System.out.println("VENDAS EFETUADAS");
                    vendas.stream().forEach(venda -> {
                        if (venda.getEmpresa().getId().equals(usuarioLogado.getEmpresa().getId())) {
                            System.out.println("************************************************************");
                            System.out.println("Venda de código: " + venda.getCodigo() + " no CPF "
                                    + venda.getCliente().getCpf() + ": ");
                            venda.getItens().stream().forEach(x -> {
                                System.out.println(x.getId() + " - " + x.getNome() + "    R$" + decimalFormat.format(x.getPreco()));
                            });
                            System.out.println("Total Venda: R$ " + decimalFormat.format(venda.getValor()));
                            System.out.println("Total Taxa a ser paga: R$ " + decimalFormat.format(venda.getComissaoSistema()));
                            Double valorLiquido = venda.getValor() - venda.getComissaoSistema();
                            System.out.println("Total Líquido  para empresa: R$" + decimalFormat.format(valorLiquido));
                            System.out.println("************************************************************");
                        }

                    });
                    System.out.println("Saldo Empresa: R$" + decimalFormat.format(usuarioLogado.getEmpresa().getSaldo()));
                    System.out.println("************************************************************");

                    executar(usuarios, clientes, empresas, produtos, carrinho, vendas);
                }
                case 2: {
                    System.out.println();
                    System.out.println("************************************************************");
                    System.out.println("MEUS PRODUTOS");
                    produtos.stream().forEach(produto -> {
                        if (produto.getEmpresa().getId().equals(usuarioLogado.getEmpresa().getId())) {
                            System.out.println("************************************************************");
                            System.out.println("Código: " + produto.getId());
                            System.out.println("Produto: " + produto.getNome());
                            System.out.println("Quantidade em estoque: " + produto.getQuantidade());
                            System.out.println("Valor: R$ " + decimalFormat.format(produto.getPreco()));
                            System.out.println("************************************************************");
                        }

                    });
                    System.out.println("Saldo Empresa: " + usuarioLogado.getEmpresa().getSaldo());
                    System.out.println("************************************************************");

                    executar(usuarios, clientes, empresas, produtos, carrinho, vendas);
                }
                case 3:
                    executar(usuarios, clientes, empresas, produtos, carrinho, vendas);
                case 0: {
                    break;
                }
            }
        } else if (usuarioLogado.IsCliente()) {
            var escolha = exibirMenuUser();
            switch (escolha) {
                case 1: {
                    System.out.println("Para realizar uma compra, escolha a empresa onde deseja comprar: ");
                    empresas.stream().forEach(x -> {
                        System.out.println(x.getId() + " - " + x.getNome());
                    });
                    Integer escolhaEmpresa = sc.nextInt();
                    Integer escolhaProduto = -1;
                    do {
                        System.out.println("Escolha os seus produtos: ");
                        produtos.stream().forEach(x -> {
                            if (x.getEmpresa().getId().equals(escolhaEmpresa)) {
                                System.out.println(x.getId() + " - " + x.getNome() + " - R$" + decimalFormat.format(x.getPreco()));
                            }
                        });
                        System.out.println("0 - Finalizar compra");
                        escolhaProduto = sc.nextInt();
                        for (Produto produtoSearch : produtos) {
                            if (produtoSearch.getId().equals(escolhaProduto))
                                carrinho.add(produtoSearch);
                        }
                    } while (escolhaProduto != 0);
                    System.out.println("************************************************************");
                    System.out.println("Resumo da compra: ");
                    carrinho.stream().forEach(x -> {
                        if (x.getEmpresa().getId().equals(escolhaEmpresa)) {
                            System.out.println(x.getId() + " - " + x.getNome() + "    R$" + decimalFormat.format(x.getPreco()));
                        }
                    });
                    Empresa empresaEscolhida = empresas.stream().filter(x -> x.getId().equals(escolhaEmpresa))
                            .collect(Collectors.toList()).get(0);
                    Cliente clienteLogado = clientes.stream()
                            .filter(x -> x.getUsername().equals(usuarioLogado.getUsername()))
                            .collect(Collectors.toList()).get(0);
                    Venda venda = VendasService.criarVenda(carrinho, empresaEscolhida, clienteLogado, vendas);
                    System.out.println("Total: R$" + decimalFormat.format(venda.getValor()));
                    System.out.println("************************************************************");
                    carrinho.clear();
                    executar(usuarios, clientes, empresas, produtos, carrinho, vendas);
                }
                case 2: {
                    System.out.println();
                    System.out.println("************************************************************");
                    System.out.println("COMPRAS EFETUADAS");
                    vendas.stream().forEach(venda -> {
                        if (venda.getCliente().getUsername().equals(usuarioLogado.getUsername())) {
                            System.out.println("************************************************************");
                            System.out.println("Compra de código: " + venda.getCodigo() + " na empresa "
                                    + venda.getEmpresa().getNome() + ": ");
                            venda.getItens().stream().forEach(x -> {
                                System.out.println(x.getId() + " - " + x.getNome() + "    R$" + decimalFormat.format(x.getPreco()));
                            });
                            System.out.println("Total: R$" + decimalFormat.format(venda.getValor()));
                            System.out.println("************************************************************");
                        }

                    });

                    executar(usuarios, clientes, empresas, produtos, carrinho, vendas);
                }
                case 3: {
                    executar(usuarios, clientes, empresas, produtos, carrinho, vendas);
                }
                case 0: {
                    break;
                }
            }
        }
        
    }
    private static int exibirMenuUser() {
        System.out.println("""
                USUARIO - ESCOLHA UMA OPÇÃO:
                1 - Realizar Compras
                2 - Ver Compras
                3 - Deslogar
                """);
        return sc.nextInt();
        
    } private static int exibirMenuEnt() {
        System.out.println("""
                EMPRESA - ESCOLHA UMA OPÇÃO:
                1 - Listar vendas
                2 - Ver produtos
                3 - Deslogar
                """);
        return sc.nextInt();
    }
}
