package LuxuriousBreeze.db;

import LuxuriousBreeze.components.Imovel;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ImovelCSVHandler {
    private static final String CSV_FILE = "imoveis.csv";

    public static void salvarImovel(Imovel imovel) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_FILE, true))) {
            writer.println(imovel.toCSV());
        } catch (IOException e) {
            System.err.println("Erro ao salvar o imóvel no arquivo CSV: " + e.getMessage());
        }
    }

    public static List<String> lerDadosCSV() {
        List<String> linhas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                linhas.add(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return linhas;
    }

    public static List<Imovel> listarImoveis() {
        List<Imovel> imoveis = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length <= 13) {
                    try {
                        int imovelID = Integer.parseInt(parts[0]);
                        String nomeImovel = parts[1];
                        String localizacao = parts[2];
                        String descricao = parts[3];
                        double preco = Double.parseDouble(parts[4]);
                        int userID = Integer.parseInt(parts[5]);

                        java.util.Date dataPublicacao = null;
                        if (!parts[6].isEmpty()) {
                            dataPublicacao = sdf.parse(parts[6]);
                        }

                        String nomeUsuario = parts[7];
                        String contatoUsuario = parts[8];
                        String tipoImovel = parts[9];
                        boolean comodidadePiscina = Boolean.parseBoolean(parts[10]);
                        boolean comodidadeAcademia = Boolean.parseBoolean(parts[11]);
                        boolean comodidadeEstacionamento = Boolean.parseBoolean(parts[12]);

                        Imovel imovel = new Imovel(imovelID, nomeImovel, localizacao, descricao, preco, userID, dataPublicacao, nomeUsuario, contatoUsuario, tipoImovel, comodidadePiscina, comodidadeAcademia, comodidadeEstacionamento);
                        imoveis.add(imovel);
                    } catch (NumberFormatException ex) {
                        System.err.println("Erro de formato numérico: " + ex.getMessage());
                    } catch (ParseException ex) {
                        System.err.println("Erro de análise: " + ex.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo CSV: " + e.getMessage());
        }
        return imoveis;
    }

    public static void atualizarImoveis(List<Imovel> imoveis) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_FILE))) {
            for (Imovel imovel : imoveis) {
                writer.println(imovel.toCSV());
            }
        } catch (IOException e) {
            System.err.println("Erro ao atualizar o arquivo CSV: " + e.getMessage());
        }
    }

    public static void removerImovel(int indice) {
        List<String> linhasCSV = lerDadosCSV();
        
        if (indice >= 0 && indice < linhasCSV.size()) {
            linhasCSV.remove(indice);
            atualizarCSVFile(linhasCSV);
        }
    }
    
    private static void atualizarCSVFile(List<String> linhas) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_FILE))) {
            for (String linha : linhas) {
                writer.println(linha);
            }
        } catch (IOException e) {
            System.err.println("Erro ao atualizar o arquivo CSV: " + e.getMessage());
        }
    }
    
}
