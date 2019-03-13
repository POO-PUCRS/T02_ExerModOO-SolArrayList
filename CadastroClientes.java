import java.util.Scanner;
import java.nio.file.*;
import java.nio.charset.*;
import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;

public class CadastroClientes{
    private boolean dadosOK;
    private ArrayList<Cliente> clientes;
 

    public CadastroClientes(){
        clientes = new ArrayList<>();
        dadosOK = false;
    }

    public boolean getDadosOk(){
        return dadosOK;
    }

    public void carregaDados(String nomeArquivo){
        // Monta string com nome absoluto do arquivo
        String dirCorrente = Paths.get("").toAbsolutePath().toString();
        String nomeCompleto = dirCorrente+"\\"+nomeArquivo;

        // Define caminho até o arquivo físico a partir do nome absoluto
        Path path = Paths.get(nomeCompleto);

        // Usa a classe Scanner para ler o arquivo 
        try (Scanner sc = new Scanner(Files.newBufferedReader(path, Charset.forName("UTF-8")))){
            // Consome a linha do cabecalho
            sc.nextLine();
            // Le as demais linhas
            while(sc.hasNext()){
                String line = sc.nextLine();
                String campos[] = line.split("[,\n]");
                Cliente cliente = new Cliente(Integer.parseInt(campos[0].trim()),
                                            campos[1].trim(),
                                            campos[2].trim(),
                                            campos[3].trim(),
                                            campos[4].trim(),
                                            campos[5].trim(),
                                            campos[6].trim(),
                                            campos[7].trim(),
                                            campos[8].trim());
                clientes.add(cliente);
            }
            dadosOK = true;
        }catch (IOException x){
            System.err.format("Erro de E/S: %s%n", x);
            dadosOK = false;
        }
    }

    public Cliente get(int indice){
        if (!dadosOK || indice<0 || indice >= clientes.size()){
            return null;
        }else{
            return clientes.get(indice);
        }
     }

     public int qtdadeClientes(){
         return clientes.size();
     }

    public ArrayList<Cliente> getTodosClientes(){
        if (!dadosOK){
            return null;
        }
        return (ArrayList<Cliente>) clientes.clone(); // Shallow copy !!
    }

    public ArrayList<Cliente> getClientesPorSexo(String sexo){
        if (!dadosOK){
            return null;
        }
        // Monta a lista resposta
        ArrayList<Cliente> resp = new ArrayList<>();
        for(Cliente cli:clientes){
            if (cli.getSexo().equals(sexo)){
                resp.add(cli);
            } 
        }
        return resp;
    }

    /* Exercícios: 
     1) Clientes por cidade
     2) Clientes por pais
     3) Clientes por pais e profissão
     */
}