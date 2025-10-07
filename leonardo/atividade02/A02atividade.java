/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package a02atividade;

/**
 *
 * @author Aluno
 */
public class A02atividade {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // questao 1 
        Scanner entrada = new Scanner(System.in);

    System.out.print("Digite o raio do círculo: ");
    double raio = entrada.nextDouble();
    
    double area = Math.PI * Math.pow(raio, 2);
    double circunferencia = 2 * Math.PI * raio;
    
    System.out.printf("Área do círculo: %.2f\n", area);
    System.out.printf("Circunferência: %.2f\n", circunferencia);
    
    
    
    //questao 2 
    System.out.print("Digite o peso (kg): "); double peso = entrada.nextDouble(); 
    System.out.print("Digite a altura (m): "); double altura = entrada.nextDouble();
    double imc = peso / Math.pow(altura, 2);
    System.out.printf("IMC: %.2f\n", imc); 
    if (imc < 18.5) { System.out.println("Faixa: Abaixo do peso");
    }
    else if (imc < 24.9) { System.out.println("Faixa: Peso normal"); 
    }
    else if (imc < 29.9) { System.out.println("Faixa: Sobrepeso");
    }
    else if (imc < 34.9) { System.out.println("Faixa: Obesidade grau I");
    }
    else if (imc < 39.9) { System.out.println("Faixa: Obesidade grau II");
    }
    else { System.out.println("Faixa: Obesidade grau III (mórbida)"); }
        
    //questao 3
    Scanner entrada = new Scanner(System.in);

    System.out.print("Digite o comprimento do primeiro cateto: ");
    double cateto1 = entrada.nextDouble();
    
    System.out.print("Digite o comprimento do segundo cateto: ");
    double cateto2 = entrada.nextDouble();
    
    double hipotenusa = Math.sqrt(Math.pow(cateto1, 2) + Math.pow(cateto2, 2));
    
    System.out.printf("A hipotenusa é: %.2f\n", hipotenusa);
    
    //questao 4
    System.out.print("Digite o valor em reais (R$): "); 
    double reais = entrada.nextDouble(); 
    System.out.print("Digite a cotação do dólar (R$): ");
    double cotacao = entrada.nextDouble();
    int dolares = (int) (reais / cotacao); 
    double troco = reais - (dolares * cotacao); 
    System.out.println("\nCom R$ " + reais + " e cotação de R$ " + cotacao + ":");
    System.out.println("Você pode comprar " + dolares + " dólar(es) inteiro(s).");
    System.out.printf("Troco em reais: R$ %.2f\n", troco);
    
    //questao 5
    
    Random random = new Random();

    System.out.print("Digite a quantidade de rolagens: ");
    int n = entrada.nextInt();
    
    int menor = 6, maior = 1, soma = 0;
    
    for (int i = 0; i < n; i++) {
        int resultado = random.nextInt(6) + 1;
        soma += resultado;
        if (resultado < menor) menor = resultado;
        if (resultado > maior) maior = resultado;
    }
    
    double media = (double) soma / n;
    
    System.out.printf("Média dos resultados: %.2f\n", media);
    System.out.println("Menor valor obtido: " + menor);
    System.out.println("Maior valor obtido: " + maior);
    
    //qestao 6
    
    Scanner entrada = new Scanner(System.in);

    System.out.print("Digite o ângulo em graus: ");
    double graus = entrada.nextDouble();
    
    double rad = Math.toRadians(graus);
    
    System.out.printf("Seno: %.4f\n", Math.sin(rad));
    System.out.printf("Cosseno: %.4f\n", Math.cos(rad));
    System.out.printf("Tangente: %.4f\n", Math.tan(rad));
    
    //questao 7
    System.out.print("Digite x1: "); 
    double x1 = entrada.nextDouble();
    System.out.print("Digite y1: ");
    double y1 = entrada.nextDouble();
    System.out.print("Digite x2: "); 
    double x2 = entrada.nextDouble();
    System.out.print("Digite y2: "); 
    double y2 = entrada.nextDouble(); 
    double distancia = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)); 
    System.out.printf("Distância entre os pontos: %.2f\n", distancia);
    
    //questao 8
    System.out.print("Digite o primeiro número: ");
    double n1 = entrada.nextDouble(); 
    System.out.print("Digite o segundo número: "); 
    double n2 = entrada.nextDouble(); 
    System.out.print("Digite o terceiro número: "); 
    double n3 = entrada.nextDouble(); 
    double media = (n1 + n2 + n3) / 3; 
    double somaAbsolutos = Math.abs(n1) + Math.abs(n2) + Math.abs(n3); 
    double maiorDif = Math.max(Math.abs(n1 - n2), Math.max(Math.abs(n1 - n3), Math.abs(n2 - n3))); 
    System.out.printf("Média aritmética: %.2f\n", media);
    System.out.printf("Soma dos valores absolutos: %.2f\n", somaAbsolutos); 
    System.out.printf("Maior diferença (valor absoluto): %.2f\n", maiorDif);
    
    //questao 9
    System.out.print("Digite o valor de a: "); 
    double a = entrada.nextDouble(); 
    System.out.print("Digite o valor de b: ");
    double b = entrada.nextDouble(); 
    System.out.print("Digite o valor de c: "); 
    double c = entrada.nextDouble();
    double delta = Math.pow(b, 2) - 4 * a * c;
    if (delta < 0) { System.out.println("A equação não possui raízes reais."); } 
    else { double x1 = (-b + Math.sqrt(delta)) / (2 * a); 
    double x2 = (-b - Math.sqrt(delta)) / (2 * a); 
    System.out.printf("x1 = %.2f\n", x1);
    System.out.printf("x2 = %.2f\n", x2); }
    
    
    //questao 10
     System.out.print("Digite a temperatura em Fahrenheit: ");
    double f = entrada.nextDouble();
    
    double c = (f - 32) * 5 / 9;
    
    System.out.printf("Temperatura em Fahrenheit: %.2f°F\n", f);
    System.out.printf("Temperatura em Celsius: %.2f°C\n", c);
    
    
    entrada.close();
    }
    
}
