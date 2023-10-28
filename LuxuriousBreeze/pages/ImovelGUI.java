package LuxuriousBreeze.pages;

import LuxuriousBreeze.db.ImovelCSVHandler;
import LuxuriousBreeze.components.Imovel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.List;
import java.util.ArrayList;

public class ImovelGUI extends JFrame {
    private JPanel formPanel;
    private JTextField nomeField;
    private JTextField descricaoField;
    private JTextField precoField;
    private JTextField localizacaoField;
    private JTextField nomeUsuarioField;
    private JTextField contatoUsuarioField;
    private JComboBox<String> tipoImovelComboBox;
    private JCheckBox comodidadePiscinaCheckBox;
    private JCheckBox comodidadeAcademiaCheckBox;
    private JCheckBox comodidadeEstacionamentoCheckBox;
    private JList<String> imovelList;
    private List<Imovel> listaDeImoveis;
    private DefaultListModel<String> listModel;
    private JButton adicionarButton;
    private JButton listarButton;
    private JButton alterarButton;
    private JButton excluirButton;

    public ImovelGUI() {

        setTitle("LuxuriousBreeze - Hospedagem de Imóveis by Liedson Barros");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);

        try {
            BufferedImage iconImage = ImageIO.read(ImovelGUI.class.getResource("../img/luxuriousBreezeLogo.png"));
            setIconImage(iconImage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        listModel = new DefaultListModel<>();
        imovelList = new JList<>(listModel);
        listaDeImoveis = new ArrayList<>();
        imovelList.setFixedCellHeight(120);

        formPanel = new JPanel(new GridLayout(11, 2));
        formPanel.add(new JLabel("Nome do Imóvel:"));
        nomeField = new JTextField();
        formPanel.add(nomeField);
        formPanel.add(new JLabel("Localização:"));
        localizacaoField = new JTextField();
        formPanel.add(localizacaoField);
        formPanel.add(new JLabel("Descrição:"));
        descricaoField = new JTextField();
        formPanel.add(descricaoField);
        formPanel.add(new JLabel("Preço: R$"));
        precoField = new JTextField();
        formPanel.add(precoField);
        formPanel.add(new JLabel("Proprietário:"));
        nomeUsuarioField = new JTextField();
        formPanel.add(nomeUsuarioField);
        formPanel.add(new JLabel("Contato do Proprietário:"));
        contatoUsuarioField = new JTextField();
        formPanel.add(contatoUsuarioField);
        
        String[] tiposImovel = {"Casa", "Apartamento", "Condominio", "Sitio", "Chacara", "Terreno", "Comercio", "Outro"};
        tipoImovelComboBox = new JComboBox<>(tiposImovel);
        adicionarButton = new JButton("Adicionar");
        comodidadePiscinaCheckBox = new JCheckBox("Piscina");
        comodidadeAcademiaCheckBox = new JCheckBox("Academia");
        comodidadeEstacionamentoCheckBox = new JCheckBox("Estacionamento");
        formPanel.add(new JLabel("Tipo de Imóvel:"));
        formPanel.add(tipoImovelComboBox);
        formPanel.add(new JLabel("Comodidades:"));
        formPanel.add(comodidadePiscinaCheckBox);
        formPanel.add(comodidadeAcademiaCheckBox);
        formPanel.add(comodidadeEstacionamentoCheckBox);

        listarButton = new JButton("Atualizar Lista de Imoveis Disponíevis");

        formPanel.add(adicionarButton);
        formPanel.add(listarButton);

        alterarButton = new JButton("Alterar");
        excluirButton = new JButton("Excluir");
        formPanel.add(alterarButton);
        formPanel.add(excluirButton);


        add(formPanel, BorderLayout.NORTH);
        add(new JScrollPane(imovelList), BorderLayout.CENTER);

        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addImovel();
            }
        });

        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> linhasCSV = ImovelCSVHandler.lerDadosCSV();
                listModel.clear();
                listaDeImoveis.clear();
        
                for (String line : linhasCSV) {
                    String[] parts = line.split(",");
                    
                    Imovel imovel = createImovelFromCSV(parts);
                    listaDeImoveis.add(imovel);
        
                    StringBuilder imovelInfo = new StringBuilder();
                    imovelInfo.append("<html>" + "<br>");
                    imovelInfo.append("<b>Nome do Imóvel:</b> " + imovel.getNomeImovel() + "<br>");
                    imovelInfo.append("<b>Localização:</b> " + imovel.getLocalizacao() + "<br>");
                    imovelInfo.append("<b>Descrição:</b> " + imovel.getDescricao() + "<br>");
                    imovelInfo.append("<b>Preço:</b> R$" + imovel.getPreco() + "<br>");
                    imovelInfo.append("<b>Nome do Usuário:</b> " + imovel.getNomeUsuario() + "<br>");
                    imovelInfo.append("<b>Contato do Usuário:</b> " + imovel.getContatoUsuario() + "<br>");
                    imovelInfo.append("<br></html>");
        
                    listModel.addElement(imovelInfo.toString());
                }
            }
        });

        alterarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = imovelList.getSelectedIndex();
                if (selectedIndex >= 0 && selectedIndex < listaDeImoveis.size()) {
                    Imovel imovelSelecionado = listaDeImoveis.get(selectedIndex);
                
                    nomeField.setText(imovelSelecionado.getNomeImovel());
                    localizacaoField.setText(imovelSelecionado.getLocalizacao());
                    descricaoField.setText(imovelSelecionado.getDescricao());
                    precoField.setText(String.valueOf(imovelSelecionado.getPreco()));
                    nomeUsuarioField.setText(imovelSelecionado.getNomeUsuario());
                    contatoUsuarioField.setText(imovelSelecionado.getContatoUsuario());
        
                    imovelSelecionado.setNomeImovel(nomeField.getText());
                    imovelSelecionado.setLocalizacao(localizacaoField.getText());
                    imovelSelecionado.setDescricao(descricaoField.getText());
                    imovelSelecionado.setPreco(Double.parseDouble(precoField.getText()));
                    imovelSelecionado.setNomeUsuario(nomeUsuarioField.getText());
                    imovelSelecionado.setContatoUsuario(contatoUsuarioField.getText());
                
                    listModel.set(selectedIndex, generateImovelInfo(imovelSelecionado));
                    ImovelCSVHandler.atualizarImoveis(listaDeImoveis);
                    listModel.remove(selectedIndex);
                    ImovelCSVHandler.removerImovel(selectedIndex);
                } else {
                    JOptionPane.showMessageDialog(formPanel, "selecione um imóvel para alterar-lo\n Obs.: Atualize a lista de imoveis antes de alterar", "Erro", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });        
    
        excluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = imovelList.getSelectedIndex();
                if (selectedIndex >= 0) {
                    listModel.remove(selectedIndex);
                    ImovelCSVHandler.removerImovel(selectedIndex);
                } else {
                    JOptionPane.showMessageDialog(formPanel, "Selecione um imóvel para excluir", "Erro", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    private String generateImovelInfo(Imovel imovel) {
        StringBuilder imovelInfo = new StringBuilder();
        imovelInfo.append("<html>" + "<br>");
        imovelInfo.append("<b>Nome do Imóvel:</b> " + imovel.getNomeImovel() + "<br>");
        imovelInfo.append("<b>Localização:</b> " + imovel.getLocalizacao() + "<br>");
        imovelInfo.append("<b>Descrição:</b> " + imovel.getDescricao() + "<br>");
        imovelInfo.append("<b>Preço:</b> R$" + imovel.getPreco() + "<br>");
        imovelInfo.append("<b>Nome do Usuário:</b> " + imovel.getNomeUsuario() + "<br>");
        imovelInfo.append("<b>Contato do Usuário:</b> " + imovel.getContatoUsuario() + "<br>");
        imovelInfo.append("<br></html>");
        return imovelInfo.toString();
    }

    private Imovel createImovelFromCSV(String[] csvData) {
        int imovelID = Integer.parseInt(csvData[0]);
        String nomeImovel = csvData[1];
        String localizacao = csvData[2];
        String descricao = csvData[3];
        double preco = 0.0;
        int userID = 0;
        String nomeUsuario = csvData[6];
        String contatoUsuario = csvData[7];
        String tipoImovel = csvData[8];
    
        boolean comodidadePiscina = Boolean.parseBoolean(csvData[9]);
        boolean comodidadeAcademia = Boolean.parseBoolean(csvData[10]);
        boolean comodidadeEstacionamento = Boolean.parseBoolean(csvData[11]);
    
        try {
            preco = Double.parseDouble(csvData[4]);
        } catch (NumberFormatException e) {

        }
    
        return new Imovel(imovelID, nomeImovel, localizacao, descricao, preco, userID, null, nomeUsuario, contatoUsuario, tipoImovel, comodidadePiscina, comodidadeAcademia, comodidadeEstacionamento);
    }    

    public void addImovel() {
        String nome = nomeField.getText();
        String localizacao = localizacaoField.getText();
        String descricao = descricaoField.getText();
        String precoStr = precoField.getText();
        String nomeUsuario = nomeUsuarioField.getText();
        String contatoUsuario = contatoUsuarioField.getText();

        String tipoImovel = (String) tipoImovelComboBox.getSelectedItem();
        boolean comodidadePiscina = comodidadePiscinaCheckBox.isSelected();
        boolean comodidadeAcademia = comodidadeAcademiaCheckBox.isSelected();
        boolean comodidadeEstacionamento = comodidadeEstacionamentoCheckBox.isSelected();

        if (!nome.isEmpty() && !localizacao.isEmpty() && !descricao.isEmpty() && !precoStr.isEmpty() && !nomeUsuario.isEmpty() && !contatoUsuario.isEmpty()) {
            try {
                double preco = Double.parseDouble(precoStr);
                long currentTime = System.currentTimeMillis();
                int imovelID = (int) (currentTime % Integer.MAX_VALUE);

                Imovel imovel = new Imovel(imovelID, nome, localizacao, descricao, preco, 0, null, nomeUsuario, contatoUsuario, tipoImovel, comodidadePiscina, comodidadeAcademia, comodidadeEstacionamento);
                ImovelCSVHandler.salvarImovel(imovel);

                listModel.addElement(generateImovelInfo(imovel));

                nomeField.setText("");
                localizacaoField.setText("");
                descricaoField.setText("");
                precoField.setText("");
                nomeUsuarioField.setText("");
                contatoUsuarioField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(formPanel, "O preço deve ser um número válido", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(formPanel, "Por favor, preencha todos os campos", "Erro", JOptionPane.INFORMATION_MESSAGE);
        }                               
    }
}