package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void varastonTilavuusEiOleNegatiivinen() {
        Varasto varasto2 = new Varasto(-3);
        assertEquals(0.0, varasto2.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void alkusaldoAsettuuOikein() {
        Varasto varasto2 = new Varasto(15, 3);
        assertEquals(3, varasto2.getSaldo(), vertailuTarkkuus);
        Varasto varasto3 = new Varasto(-5, -3);
        assertEquals(0, varasto3.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, varasto3.getSaldo(), vertailuTarkkuus);
        Varasto varasto4 = new Varasto(4, 12);
        assertEquals(4, varasto4.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenLisäysEiMuutaSaldoa() {
        varasto.lisaaVarastoon(4);
        varasto.lisaaVarastoon(-2);
        assertEquals(4, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void ylimeneväMääräMeneeHukkaan() {
        varasto.lisaaVarastoon(14);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenOttoEiMuutaSaldoa() {
        varasto.lisaaVarastoon(5);
        varasto.otaVarastosta(-3);
        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void saldoJääNollaanKunOtetaanEnemmänKuinVarastossaOn() {
        varasto.lisaaVarastoon(5);
        assertEquals(5, varasto.otaVarastosta(8), vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void merkkijonoesitysOnToivotunlainen() {
        varasto.lisaaVarastoon(3);
        assertEquals("saldo = 3.0, vielä tilaa 7.0", varasto.toString());
    }

}