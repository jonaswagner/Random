package de.meinefirma.meinprojekt.dao;

import java.util.List;
import de.meinefirma.meinprojekt.buecher.BuchDO;
import de.meinefirma.meinprojekt.buecher.BuecherTO;

public class BuecherUtil
{
    public static final Integer RET_CODE_OK    = new Integer(  0 );
    public static final Integer RET_CODE_ERROR = new Integer( -1 );

    // Finde Buecher nach Suchkriterien:
    public static BuecherTO findeBuecher( Long id, String isbn, String titel )
    {
        BuecherTO    buecherTO    = new BuecherTO();
        List<BuchDO> buecherListe = BuchDoDAO.getInstance().findeBuecher( id, isbn, titel );
        if( buecherListe == null )
            return fehlerBuecherTO();
        if( id == null && isEmpty( isbn ) && isEmpty( titel ) ) {
            buecherTO.setMessage( buecherListe.size() + " Buecher" );
        } else {
            StringBuffer sb = new StringBuffer();
            sb.append( buecherListe.size() + " Ergebnis(se) fuer" );
            if( id != null        ) sb.append( " ID = " + id );
            if( !isEmpty( isbn  ) ) sb.append( " ISBN = " + isbn );
            if( !isEmpty( titel ) ) sb.append( " Titel = " + titel );
            buecherTO.setMessage( sb.toString() );
        }
        buecherTO.getResults().addAll( buecherListe );
        buecherTO.setReturncode( RET_CODE_OK );
        return buecherTO;
    }

    public static BuchDO erzeugeBuchDO( Long id, String isbn, String titel, Double preis )
    {
        BuchDO buchDO = new BuchDO();
        buchDO.setId(    id    );
        buchDO.setIsbn(  isbn  );
        buchDO.setTitel( titel );
        buchDO.setPreis( preis );
        return buchDO;
    }

    public static BuecherTO erzeugeBuecherTO( String msg, BuchDO buchDO )
    {
        if( buchDO == null ) return fehlerBuecherTO();
        BuecherTO buecherTO = new BuecherTO();
        buecherTO.getResults().add( buchDO );
        buecherTO.setMessage( msg );
        buecherTO.setReturncode( RET_CODE_OK );
        return buecherTO;
    }

    public static BuecherTO fehlerBuecherTO()
    {
        BuecherTO buecherTO = new BuecherTO();
        buecherTO.setMessage( "Parameterfehler" );
        buecherTO.setReturncode( RET_CODE_ERROR );
        return buecherTO;
    }

    public static boolean isEmpty( String s )
    {
        return s == null || s.trim().length() <= 0;
    }
}