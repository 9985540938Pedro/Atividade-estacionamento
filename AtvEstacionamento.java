import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AtvEstacionamento {
class Carro {
    private String placa;
    private String modelo;
    private String marca;
    private LocalDateTime horarioEntrada;
    private String tipo;

    public Carro(String placa, String modelo, String marca, String tipo) {
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.tipo = tipo;
        this.horarioEntrada = LocalDateTime.now();
    }

    public String getPlaca() {
        return placa;
    }

    public String getTipo() {
        return tipo;
    }

    public LocalDateTime getHorarioEntrada() {
        return horarioEntrada;
    }
}

class Estacionamento {
    private Carro[] vagas;

    public Estacionamento(int capacidade) {
        this.vagas = new Carro[capacidade];
    }

    public boolean estacionar(Carro carro, int vaga) {
        if (vaga < 0 || vaga >= vagas.length) {
            System.out.println("Vaga inválida.");
            return false;
        }
        if (vagas[vaga] != null) {
            System.out.println("Vaga já ocupada.");
            return false;
        }
        vagas[vaga] = carro;
        return true;
    }

    public boolean liberarVaga(String placa) {
        for (int i = 0; i < vagas.length; i++) {
            if (vagas[i] != null && vagas[i].getPlaca().equals(placa)) {
                calcularPagamento(vagas[i]);
                vagas[i] = null;
                return true;
            }
        }
        System.out.println("Veículo não encontrado.");
        return false;
    }

    public void consultarVagas() {
        for (int i = 0; i < vagas.length; i++) {
            if (vagas[i] == null) {
                System.out.println("Vaga " + i + ": Livre");
            } else {
                System.out.println("Vaga " + i + ": Ocupada por " + vagas[i].getPlaca());
            }
        }
    }

    public int totalCarros() {
        int total = 0;
        for (Carro carro : vagas) {
            if (carro != null) {
                total++;
            }
        }
        return total;
    }

    private void calcularPagamento(Carro carro) {
        LocalDateTime agora = LocalDateTime.now();
        long horas = java.time.Duration.between(carro.getHorarioEntrada(), agora).toHours();
        if (horas == 0) horas = 1; // Cobrar pelo menos 1 hora
        double valor = 0;
        switch (carro.getTipo().toLowerCase()) {
            case "pequeno":
                valor = horas * 16.0;
                break;
            case "grande":
                valor = horas * 25.0;
                break;
            case "moto":
                valor = horas * 8.0;
                break;
            default:
                System.out.println("Tipo de veículo desconhecido.");
                return;
        }
        System.out.println("Total a pagar para o veículo de placa " + carro.getPlaca() + ": R$" + valor);
    }
}
    
}

