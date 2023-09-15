//Marcos Vinicius de Aragão
package desafioProjedata;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;
public class Principal {

	public static void main(String[] args) {
		System.out.println("3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela acima.");
		List<Funcionarios> funcionarios = new ArrayList<>();
		funcionarios.add(new Funcionarios("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
		funcionarios.add(new Funcionarios("João", LocalDate.of(1990, 12, 05), new BigDecimal("2284.38"), "Operador"));
		funcionarios.add(new Funcionarios("Caio", LocalDate.of(1961,05,02), new BigDecimal("9836.14"), "Coordenador"));
		funcionarios.add(new Funcionarios("Miguel", LocalDate.of(1988,10,14), new BigDecimal("19119.88"), "Diretor"));
		funcionarios.add(new Funcionarios("Alice", LocalDate.of(1995,01,05), new BigDecimal("2234.68"), "Recepcionista"));
		funcionarios.add(new Funcionarios("Heitor", LocalDate.of(1999,11,19), new BigDecimal("1582.72"), "Operador"));
		funcionarios.add(new Funcionarios("Arthur", LocalDate.of(1993,03,31), new BigDecimal("4071.84"), "Contador"));
		funcionarios.add(new Funcionarios("Laura", LocalDate.of(1994,07,8), new BigDecimal("3017.45"), "Gerente"));
		funcionarios.add(new Funcionarios("Heloísa", LocalDate.of(2003,05,24), new BigDecimal("1606.85"), "Eletricista"));
		funcionarios.add(new Funcionarios("Helena", LocalDate.of(1996,9,02), new BigDecimal("2799.93"), "Gerente"));
		
		System.out.println("3.2 – Remover o funcionário “João” da lista.");
		funcionarios = removerFuncionario(funcionarios, "João");
		System.out.println("------------------------");
		
		System.out.println("3.3 – Imprimir todos os funcionários com todas suas informações, sendo que:\r\n"
				+ "• informação de data deve ser exibido no formato dd/mm/aaaa;\r\n"
				+ "• informação de valor numérico deve ser exibida no formatado com separador de milhar como ponto e decimal como vírgula.");
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
		for (Funcionarios funcionario : funcionarios) {
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("Data de Nascimento: " + funcionario.getDataNascimento().format(dateFormat));
            System.out.println("Salário: " + decimalFormat.format(funcionario.getSalario()));
            System.out.println("Função: " + funcionario.getFuncao());
            System.out.println("------------------------");
		}
	
		System.out.println("3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.");
		for(Funcionarios funcionario: funcionarios) {
			System.out.println("Pega Função: "+funcionario.getFuncao());
			System.out.println("------------------------");
			
		}
		System.out.println("3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.");
        Set<String> setFuncao = new HashSet<>();
        Map<String, List<Funcionarios>> mapFunc = new HashMap<>();

        Iterator<Principal.Funcionarios>ii = funcionarios.iterator();
        while (ii.hasNext()){
            setFuncao.add(ii.next().getFuncao());
        }

        for (String funcao : setFuncao) {
            List<Funcionarios> listFunc = new ArrayList<>();
            for (Funcionarios funcionario : funcionarios) {
                if (funcionario.getFuncao().equals(funcao)) {
                    listFunc.add(funcionario);
                }
            }
            mapFunc.put(funcao, listFunc);
        }
        System.out.println(mapFunc);
        System.out.println("------------------------");

        System.out.println("3.6 – Imprimir os funcionários, agrupados por função.\n");
        for (String funcao : setFuncao) {
        	int contador = 0;
        	for(Funcionarios funcionario: funcionarios) {
        		if(funcionario.getFuncao()==funcao) {
        			contador+=1;
        		}
        		
        	}
        	System.out.println("A função: " +funcao+", possui: "+contador+" funcionarios");
        	System.out.println("------------------------");
        	
        }
        System.out.println("3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12.");
        for (Funcionarios funcionario : funcionarios) {
            int mesNascimento = funcionario.getDataNascimento().getMonthValue();
            if (mesNascimento == 10 || mesNascimento == 12) {
                DateTimeFormatter dateFormat1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                System.out.println("Nome: " + funcionario.getNome());
                System.out.println("Data de Nascimento: " + funcionario.getDataNascimento().format(dateFormat1));
                System.out.println("------------------------");
            }
        }
        System.out.println("3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.");
        Funcionarios maisVelho = funcionarios.iterator().next();
        for (Funcionarios funcionario: funcionarios) {
            if (funcionario.getDataNascimento().isEqual(maisVelho.getDataNascimento())){
                maisVelho = funcionario;
            } else if (funcionario.getDataNascimento().isAfter(maisVelho.getDataNascimento())) {
                maisVelho = maisVelho;
            } else if (funcionario.getDataNascimento().isBefore(maisVelho.getDataNascimento())){
                maisVelho = funcionario;
            }
            
        }
        System.out.println("Nome: " + maisVelho.getNome() +" - Nome: " + Period.between(maisVelho.getDataNascimento(), LocalDate.now()).getYears());
        System.out.println("------------------------");
        
        System.out.println("3.10 – Imprimir a lista de funcionários por ordem alfabética.");
        funcionarios.sort(Comparator.comparing(funcionario -> funcionario.getNome()));
        System.out.println(funcionarios);
        System.out.println("------------------------");
        
        System.out.println("3.11 – Imprimir o total dos salários dos funcionários.");
        BigDecimal somaSalario = BigDecimal.valueOf(0);
        for(Funcionarios funcionario: funcionarios) {
        	somaSalario = somaSalario.add(funcionario.getSalario());
        }
        System.out.println("R$ "+somaSalario);
        System.out.println("------------------------");
        
        System.out.println("3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.");
        BigDecimal qtdSalarioMinimo;
        for(Funcionarios funcionario : funcionarios) {
        	qtdSalarioMinimo = funcionario.getSalario().divide(BigDecimal.valueOf(1212),2, RoundingMode.HALF_UP);
        	System.out.println("O funcionario: "+ funcionario.getNome()+ " - Recebe o total de " + qtdSalarioMinimo + " salarios minimos");
        }
        
    }
	
	public static List<Funcionarios> aplicarAumento(List<Funcionarios> funcionarios, BigDecimal percentualAumento){
		for(Funcionarios funcionario : funcionarios) {
		BigDecimal salarioAtual = funcionario.getSalario();
		BigDecimal aumento = salarioAtual.multiply(percentualAumento);
		BigDecimal novoSalario = salarioAtual.add(aumento);
		funcionario.setSalario(novoSalario);
		}
		return funcionarios;
		
	}
	
	public static List<Funcionarios> removerFuncionario(List<Funcionarios> funcionarios, String nome){
		List<Funcionarios> novaLista = new ArrayList<>();
		
		for(Funcionarios funcionario : funcionarios) {
			if(!funcionario.getNome().equals(nome)) {
				novaLista.add(funcionario);
			}
		}
	return novaLista;
}
	
	public class Pessoa{
		public String nome;
		public LocalDate dataNascimento;
		
		public Pessoa(String nome, LocalDate dataNascimento) {
			this.nome = nome;
			this.dataNascimento = dataNascimento;
		}
		public LocalDate getDataNascimento() {
			return dataNascimento;
			
		}
	}
	public static class Funcionarios extends Pessoa{
		private BigDecimal salario;
		private String funcao;
		
		public Funcionarios(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
			new Principal().super(nome, dataNascimento);
			this.salario = salario;
			this.funcao = funcao;
			this.nome = nome;
			
		}
		public String getNome() {
			return nome;
		}
		public BigDecimal getSalario() {
			return salario;
		}
		public void setSalario(BigDecimal salario) {
			this.salario = salario;
		}
		public String getFuncao() {
			return funcao;
		}
		public void setFuncao(String funcao) {
			this.funcao = funcao;
		}
	
	}
	}



