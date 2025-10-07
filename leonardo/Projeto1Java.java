package projeto2.java;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Projeto2Java {
    private static final Locale BR = new Locale("pt", "BR");
    private static final NumberFormat nfBR = NumberFormat.getCurrencyInstance(BR);
    private static final BigDecimal COTACAO_USD = new BigDecimal("5.32");
    private static final int MAX_EXTRATO = 10;
    private static final BigDecimal SALDO_INICIAL_REAIS = new BigDecimal("100.00");
    private static final BigDecimal SALDO_INICIAL_USD = BigDecimal.ZERO;

    private BigDecimal saldoReais = SALDO_INICIAL_REAIS;
    private BigDecimal saldoUsd = SALDO_INICIAL_USD;
    private final ArrayDeque<String> extrato = new ArrayDeque<>();
    private final Scanner sc = new Scanner(System.in);

    private static final Integer[] PARCELAS_VALIDAS = {6, 12, 18, 24, 30, 36, 40, 48, 56, 60, 72};

    public static void main(String[] args) {
        Projeto2Java app = new Projeto2Java();
        app.run();
    }

    private void run() {
        boolean sair = false;
        while (!sair) {
            showMenu();
            String opc = sc.nextLine().trim();
            switch (opc) {
                case "1" -> mostrarSaldo();
                case "2" -> deposito();
                case "3" -> mostrarExtrato();
                case "4" -> simularEmprestimo();
                case "5" -> comprarDolar();
                case "0" -> {
                    System.out.println("\nEncerrando o atendimento. Obrigado por utilizar o Caixa Rápido.");
                    sair = true;
                }
                default -> System.out.println("Opção inválida. Digite novamente.");
            }
        }
    }

    private void showMenu() {
        System.out.println("\n=== CAIXA RÁPIDO ===");
        System.out.println("1 - Saldo");
        System.out.println("2 - Depósito");
        System.out.println("3 - Extrato");
        System.out.println("4 - Simular Empréstimo");
        System.out.println("5 - Comprar Dólar");
        System.out.println("0 - Sair");
        System.out.print("\nEscolha uma opção: ");
    }

    private void mostrarSaldo() {
        System.out.println("\nSaldo em Reais: " + formatReais(saldoReais));
        System.out.println("Saldo em Dólares: " + formatUsd(saldoUsd));

        if (!voltarOuEncerrar()) {
            System.exit(0);
        }
    }

    private void deposito() {
        while (true) {
            System.out.print("\nInforme o valor do depósito (R$): ");
            String entrada = sc.nextLine().trim().replace(",", ".");
            BigDecimal valor;
            try {
                valor = new BigDecimal(entrada);
            } catch (Exception e) {
                System.out.println("Valor inválido! Digite novamente.");
                continue;
            }
            if (valor.compareTo(BigDecimal.ZERO) <= 0 || valor.compareTo(new BigDecimal("5000.00")) > 0) {
                System.out.println("\n!!!Valor inválido!!!");
                System.out.println("O depósito deve ser maior que R$ 0,00 e até R$ 5.000,00.");
                continue;
            }
            saldoReais = saldoReais.add(valor);
            String mov = String.format("DEPÓSITO +%s", formatReais(valor));
            addExtrato(mov);
            System.out.println("Depósito realizado com sucesso!");
            if (!voltarOuEncerrar()) System.exit(0);
            break;
        }
    }

    private void mostrarExtrato() {
        System.out.println();
        if (extrato.isEmpty()) {
            System.out.println("Nenhum extrato");
        } else {
            System.out.println("Últimas " + Math.min(MAX_EXTRATO, extrato.size()) + " movimentações:");
            int i = 1;
            for (String e : extrato) {
                System.out.println(i + " - " + e);
                i++;
            }
        }
        if (!voltarOuEncerrar()) System.exit(0);
    }

    private void simularEmprestimo() {
        try {
            BigDecimal valor;
            while (true) {
                System.out.print("Informe o valor do empréstimo (R$): ");
                String in = sc.nextLine().trim().replace(",", ".");
                try {
                    valor = new BigDecimal(in);
                } catch (Exception ex) {
                    System.out.println("Valor inválido. Digite novamente.");
                    continue;
                }
                if (valor.compareTo(BigDecimal.ZERO) <= 0) {
                    System.out.println("Valor inválido. Digite novamente.");
                    continue;
                }
                break;
            }

            BigDecimal salario;
            while (true) {
                System.out.print("Informe o seu salário bruto (R$): ");
                String in = sc.nextLine().trim().replace(",", ".");
                try {
                    salario = new BigDecimal(in);
                } catch (Exception ex) {
                    System.out.println("Valor inválido. Digite novamente.");
                    continue;
                }
                if (salario.compareTo(BigDecimal.ZERO) <= 0) {
                    System.out.println("Salário inválido. Digite novamente.");
                    continue;
                }
                break;
            }

            int parcelas;
            while (true) {
                System.out.print("Quantidade de parcelas (6,12,18,24,30,36,40,48,56,60 ou 72): ");
                String in = sc.nextLine().trim();
                try {
                    parcelas = Integer.parseInt(in);
                } catch (Exception ex) {
                    System.out.println("Número inválido. Digite novamente.");
                    continue;
                }
                if (!Arrays.asList(PARCELAS_VALIDAS).contains(parcelas)) {
                    System.out.println("Quantidade de parcelas inválida.");
                    continue;
                }
                break;
            }

            BigDecimal taxa = new BigDecimal("0.02");
            BigDecimal totalComJuros = valor.multiply(BigDecimal.ONE.add(taxa)).setScale(2, RoundingMode.HALF_UP);
            BigDecimal parcela = totalComJuros.divide(new BigDecimal(parcelas), 2, RoundingMode.HALF_UP);

            System.out.println("\nValor da parcela: " + formatReais(parcela) + " - " + parcelas + "x");
            System.out.println("Valor do empréstimo: " + formatReais(valor));
            System.out.println("Valor total a ser pago: " + formatReais(totalComJuros));

            BigDecimal limite = salario.multiply(new BigDecimal("0.30"));

            if (parcela.compareTo(limite) <= 0) {
                String protocolo = gerarProtocolo();
                System.out.println("\nEmpréstimo disponível! Protocolo: " + protocolo);
            } else {
                System.out.println("\nEmpréstimo indisponível! O valor da parcela não pode ultrapassar " + formatReais(limite));
            }
        } catch (Exception ex) {
            System.out.println("Erro ao simular empréstimo. Tente novamente.");
        }

        if (!voltarOuEncerrar()) System.exit(0);
    }

    private void comprarDolar() {
        boolean loop = true;
        while (loop) {
            System.out.println("\nSaldo disponível em Reais: " + formatReais(saldoReais));
            System.out.print("Quantos dólares deseja comprar (US$): ");
            String in = sc.nextLine().trim().replace(",", ".");
            BigDecimal qtdUsd;
            try {
                qtdUsd = new BigDecimal(in);
            } catch (Exception ex) {
                System.out.println("Quantidade inválida. Digite novamente.");
                continue;
            }
            if (qtdUsd.compareTo(BigDecimal.ZERO) <= 0) {
                System.out.println("Quantidade deve ser maior que zero.");
                continue;
            }
            BigDecimal custo = qtdUsd.multiply(COTACAO_USD).setScale(2, RoundingMode.HALF_UP);
            System.out.println("Custo em Reais: " + formatReais(custo));
            if (custo.compareTo(saldoReais) > 0) {
                System.out.println("Seu saldo atual é insuficiente.");
                System.out.print("Deseja simular novamente (R), voltar ao menu (V) ou encerrar o programa (E)? ");
                String opc = sc.nextLine().trim().toUpperCase();
                if (opc.equals("R")) {
                    continue;
                } else if (opc.equals("V")) {
                    return;
                } else {
                    System.exit(0);
                }
            } else {
                System.out.print("Confirmar a compra? (s/n): ");
                String confirm = sc.nextLine().trim().toLowerCase();
                if (confirm.equals("s")) {
                    saldoReais = saldoReais.subtract(custo);
                    saldoUsd = saldoUsd.add(qtdUsd.setScale(2, RoundingMode.HALF_UP));
                    String mov = String.format("COMPRA DÓLAR -%s | +%s", formatReais(custo), formatUsd(qtdUsd));
                    addExtrato(mov);
                    System.out.println("\nCompra realizada com sucesso.");
                    System.out.println("Novo saldo:");
                    System.out.println(" - Reais: " + formatReais(saldoReais));
                    System.out.println(" - Dólares: " + formatUsd(saldoUsd));
                    if (!voltarOuEncerrar()) System.exit(0);
                    loop = false;
                } else if (confirm.equals("n")) {
                    System.out.print("Operação cancelada.\nDeseja simular novamente (R), voltar ao menu (V) ou encerrar o programa (E)? ");
                    String opc = sc.nextLine().trim().toUpperCase();
                    if (opc.equals("R")) {
                        continue;
                    } else if (opc.equals("V")) {
                        return;
                    } else {
                        System.exit(0);
                    }
                } else {
                    System.out.println("Opção inválida. Voltando ao menu.");
                    return;
                }
            }
        }
    }

    private boolean voltarOuEncerrar() {
        while (true) {
            System.out.print("\nDeseja voltar ao menu inicial (V) ou encerrar o programa (E)? ");
            String r = sc.nextLine().trim().toUpperCase();
            if (r.equals("V")) {
                return true;
            } else if (r.equals("E")) {
                return false;
            } else {
                System.out.println("Opção inválida. Digite V ou E.");
            }
        }
    }

    private void addExtrato(String mov) {
        extrato.addFirst(mov);
        if (extrato.size() > MAX_EXTRATO) {
            extrato.removeLast();
        }
    }

    private String gerarProtocolo() {
        Random rnd = new Random();
        int num = rnd.nextInt(90000) + 10000;
        return "EM" + num;
    }

    private String formatReais(BigDecimal valor) {
        nfBR.setMinimumFractionDigits(2);
        nfBR.setMaximumFractionDigits(2);
        return nfBR.format(valor.setScale(2, RoundingMode.HALF_UP));
    }

    private String formatUsd(BigDecimal valor) {
        nfBR.setMinimumFractionDigits(2);
        nfBR.setMaximumFractionDigits(2);
        return "US$ " + nfBR.format(valor.setScale(2, RoundingMode.HALF_UP)).replace("R$ ", "");
    }
}
