/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package a03atividade;

/**
 *
 * @author Aluno
 */
public class A03atividade {

    /**
     * @param args the command line arguments
     */
  
    public static void main(String[] args) {
        // TODO code application logic here
          Scanner entrada = new Scanner(System.in);

    // questao 1
    
    double acucarBase = 200; // gramas
    double porcoesBase = 8;
    
    System.out.print("Digite a quantidade desejada de porções: ");
    double porcoesDesejadas = entrada.nextDouble();
    
   
    double acucarNecessario = (acucarBase * porcoesDesejadas) / porcoesBase;
    
    System.out.printf("Açúcar necessário: %.2f gramas\n", acucarNecessario);
    
    //questao 2
    
    int impressorasBase = 3; double tempoBase = 12;
     int paginas = 180;
     System.out.print("Digite a quantidade de impressoras: "); 
     int n = entrada.nextInt();
     double tempo = (impressorasBase * tempoBase) / n; 
     System.out.printf("Tempo necessário para imprimir %d páginas: %.2f minutos\n", paginas, tempo);
     
     
     
     //questao3
     
     double litrosBase = 18; double distanciaBase = 240;
     System.out.print("Digite a distância desejada (km): ");
     double d = entrada.nextDouble(); 
     double litrosNecessarios = (litrosBase * d) / distanciaBase;
     System.out.printf("Litros necessários: %.2f L\n", litrosNecessarios);
     
     
     //questao 4
     
     double acucarBase = 200; 
     double porcoesBase = 8; 
     System.out.print("Digite a quantidade desejada de porções: "); 
     double porcoesDesejadas = entrada.nextDouble();
     
     double acucarNecessario = (acucarBase * porcoesDesejadas) / porcoesBase; 
     System.out.printf("Açúcar necessário: %.2f gramas\n", acucarNecessario);
     
     
     
     entrada.close();
    }
    
}
