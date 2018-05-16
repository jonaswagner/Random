package de.meinefirma.meinprojekt.buecher;

import javax.xml.bind.annotation.XmlRootElement;

/** Buch-Domainobjekt */
@XmlRootElement
public class BuchDO
{
    private Long   id;
    private String isbn;
    private String titel;
    private Double preis;

    public Long   getId()    { return id; }
    public String getIsbn()  { return isbn; }
    public String getTitel() { return titel; }
    public Double getPreis() { return preis; }
    public void setId(    Long   id    ) { this.id    = id; }
    public void setIsbn(  String isbn  ) { this.isbn  = isbn; }
    public void setTitel( String titel ) { this.titel = titel; }
    public void setPreis( Double preis ) { this.preis = preis; }
}