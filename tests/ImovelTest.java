package tests;

import org.junit.Before;
import org.junit.Test;

import LuxuriousBreeze.components.Imovel;

import static org.junit.Assert.*;

public class ImovelTest {
    private Imovel imovel;

    @Before
    public void setUp() {
        // Criando um Imovel teste
        imovel = new Imovel(1, "Hotel C++", "Boa Vista", "Hotel completo proximo a praia do Sol Quente", 200000.0, 0, null, "Liedson LB", "liedson.b9@gmail.com", "Condominio", true, false, true);
    }

    @Test
    public void testPreenchimento() {
        assertEquals(1, imovel.getImovelID());
        assertEquals(imovel.getNomeImovel(), imovel.getNomeImovel());
        assertEquals("Boa Vista", imovel.getLocalizacao());
        assertEquals("Hotel completo proximo a praia do Sol Quente", imovel.getDescricao());
        assertEquals(200000.0, imovel.getPreco(), 0.01);
        assertEquals(0, imovel.getUserID());
        assertEquals("Liedson LB", imovel.getNomeUsuario());
        assertEquals("liedson.b9@gmail.com", imovel.getContatoUsuario());
        assertEquals("Condominio", imovel.getTipoImovel());
        assertTrue(imovel.hasComodidadePiscina());
        assertFalse(imovel.hasComodidadeAcademia());
        assertTrue(imovel.hasComodidadeEstacionamento());
    }

    @Test
    public void testImovelCamposInvalidos() {
        // Testando preço negativo
        imovel = new Imovel(2, "Loja na Uespi", "Campus Piripiri", "Otimo espaço para vendas", -50000.0, 124, null, "anonimo", "12345", "Comercio", false, false, true);

        assertEquals(2, imovel.getImovelID());
        assertEquals("Loja na Uespi", imovel.getNomeImovel());
        assertEquals("Campus Piripiri", imovel.getLocalizacao());
        assertEquals("Otimo espaço para vendas", imovel.getDescricao());
        assertEquals(-50000.0, imovel.getPreco(), 0.01);
        assertEquals(124, imovel.getUserID());
        assertEquals("anonimo", imovel.getNomeUsuario());
        assertEquals("12345", imovel.getContatoUsuario());
        assertEquals("Comercio", imovel.getTipoImovel());
        assertFalse(imovel.hasComodidadePiscina());
        assertFalse(imovel.hasComodidadeAcademia());
        assertTrue(imovel.hasComodidadeEstacionamento());
    }

    // implementando sistema de Data de publicação e ID do Proprietario para um tabela de relação entre Usuarios e Imoveis divulgados
    @Test
    public void testImoveUserID() {

        imovel = new Imovel(4, "Residência de Luxo", "Zona Nobre - SP", "Imovel do mais alto padrão", 550000.0, 301, null, "Ivan", "04188", "Casa", true, true, true);

        assertEquals(4, imovel.getImovelID());
        assertEquals("Residência de Luxo", imovel.getNomeImovel());
        assertEquals("Zona Nobre - SP", imovel.getLocalizacao());
        assertEquals("Imovel do mais alto padrão", imovel.getDescricao());
        assertEquals(550000.0, imovel.getPreco(), 0.01);
        assertEquals(301, imovel.getUserID());
        assertEquals(imovel.getDataPublicacao(), imovel.getDataPublicacao());
        assertEquals("Ivan", imovel.getNomeUsuario());
        assertEquals("04188", imovel.getContatoUsuario());
        assertEquals("Casa", imovel.getTipoImovel());
        assertTrue(imovel.hasComodidadePiscina());
        assertTrue(imovel.hasComodidadeAcademia());
        assertTrue(imovel.hasComodidadeEstacionamento());

    }
    
}
