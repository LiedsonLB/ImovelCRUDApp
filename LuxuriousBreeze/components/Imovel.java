package LuxuriousBreeze.components;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Imovel {
    private int imovelID;
    private String nomeImovel;
    private String localizacao;
    private String descricao;
    private double preco;
    private Date dataPublicacao;
    private String nomeUsuario;
    private int userID;
    private String contatoUsuario;
    private String tipoImovel;
    private boolean comodidadePiscina;
    private boolean comodidadeAcademia;
    private boolean comodidadeEstacionamento;

    public Imovel(int imovelID, String nomeImovel, String localizacao, String descricao, double preco, int userID, Date dataPublicacao, String nomeUsuario, String contatoUsuario, String tipoImovel, boolean comodidadePiscina, boolean comodidadeAcademia, boolean comodidadeEstacionamento) {
        this.imovelID = imovelID;
        this.nomeImovel = nomeImovel;
        this.localizacao = localizacao;
        this.descricao = descricao;
        this.preco = preco;
        this.dataPublicacao = dataPublicacao;
        this.nomeUsuario = nomeUsuario;
        this.userID = userID;
        this.contatoUsuario = contatoUsuario;
        this.tipoImovel = tipoImovel;
        this.comodidadePiscina = comodidadePiscina;
        this.comodidadeAcademia = comodidadeAcademia;
        this.comodidadeEstacionamento = comodidadeEstacionamento;
    }

    public int getImovelID() {
        return imovelID;
    }

    public String getNomeImovel() {
        return nomeImovel;
    }

    public void setNomeImovel(String nomeImovel) {
        this.nomeImovel = nomeImovel;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public int getUserID(){
        return userID;
    }

    public String getContatoUsuario() {
        return contatoUsuario;
    }

    public void setContatoUsuario(String contato) {
        this.contatoUsuario = contato;
    }

    public Date getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(Date dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }
    
    public String getTipoImovel() {
        return tipoImovel;
    }

    public void setTipoImovel(String tipoImovel) {
        this.tipoImovel = tipoImovel;
    }

    public boolean hasComodidadePiscina() {
        return comodidadePiscina;
    }
    public boolean hasComodidadeAcademia(){
        return comodidadeAcademia;
    }
    public boolean hasComodidadeEstacionamento(){
        return comodidadeEstacionamento;
    }

    public String toCSV() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dataPublicacaoStr = (dataPublicacao != null) ? sdf.format(dataPublicacao) : "";
        return imovelID + "," + nomeImovel + "," + localizacao + "," + descricao + "," + preco + "," + dataPublicacaoStr + "," + nomeUsuario + "," + contatoUsuario + "," + tipoImovel + "," + comodidadePiscina + "," + comodidadeAcademia + "," + comodidadeEstacionamento;
    }   
}