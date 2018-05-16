package de.meinefirma.meinprojekt.dao;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import de.meinefirma.meinprojekt.buecher.BuchDO;

/** DAO (Data Access Object) fuer CRUD-Operationen (Create, Read, Update, Delete) */
public class BuchDoDAO
{
    // Map als Datenbank-Simulation:
    private              Map<Long,BuchDO> buecherPool = new ConcurrentHashMap<>();
    private static final BuchDoDAO        INSTANCE    = new BuchDoDAO();
    private static final long             DFLT_ID     = 4710;

    private BuchDoDAO()
    {
    }

    public static BuchDoDAO getInstance()
    {
        return INSTANCE;
    }

    // Neues Buch hinzufuegen:
    public BuchDO createBuch( BuchDO bu )
    {
        synchronized( buecherPool ) {
            if( bu.getId() != null ) {
                if( getBuchById( bu.getId() ) != null )
                    throw new RuntimeException(
                            "Fehler: Es gibt bereits ein Buch mit der ID " + bu.getId() + "." );
            } else {
                long maxId = ( buecherPool.size() > 0 )
                        ? Collections.max( buecherPool.keySet() ).longValue() : DFLT_ID;
                bu.setId( Long.valueOf( ++maxId ) );
            }
            buecherPool.put( bu.getId(), bu );
            return bu;
        }
    }

    // Finde Buch mit ID:
    public BuchDO getBuchById( Long id )
    {
        return ( id == null ) ? null : buecherPool.get( id );
    }

    // Finde Buecher nach Suchkriterien:
    public List<BuchDO> findeBuecher( Long id, String isbn, String titel )
    {
        List<BuchDO> resultList = new ArrayList<>();
        List<BuchDO> snapshotList;
        if( id == null && isEmpty( isbn ) && isEmpty( titel ) )
            return Collections.unmodifiableList( new ArrayList<>( buecherPool.values() ) );
        if( id != null && isEmpty( isbn ) && isEmpty( titel ) ) {
            BuchDO bu = getBuchById( id );
            if( bu != null ) resultList.add( bu );
            return resultList;
        }
        synchronized( buecherPool ) {
            snapshotList = new ArrayList<>( buecherPool.values() );
        }
        String isbnLC  = ( isbn  == null ) ? null :  isbn.trim().toLowerCase();
        String titelLC = ( titel == null ) ? null : titel.trim().toLowerCase();
        for( BuchDO bu : snapshotList )
            if( (id != null && bu.getId() != null && id.equals( bu.getId() )) ||
                    (!isEmpty( bu.getIsbn() ) && !isEmpty( isbnLC ) &&
                            bu.getIsbn().trim().toLowerCase().contains( isbnLC )) ||
                    (!isEmpty( bu.getTitel() ) && !isEmpty( titelLC ) &&
                            bu.getTitel().trim().toLowerCase().contains( titelLC )) )
                resultList.add( bu );
        return resultList;
    }

    // Daten eines per ID definierten Buches aendern:
    public BuchDO updateBuchById( BuchDO bu )
    {
        synchronized( buecherPool ) {
            BuchDO buAlt = buecherPool.get( bu.getId() );
            if( buAlt == null )
                throw new RuntimeException( "Fehler: Es gibt kein Buch mit der ID " + bu.getId() + "." );
            buecherPool.put( bu.getId(), bu );
            return bu;
        }
    }

    // Per ID definiertes Buch loeschen:
    public BuchDO deleteBuchById( Long id )
    {
        synchronized( buecherPool ) {
            return buecherPool.remove( id );
        }
    }

    // Alle Buecher loeschen:
    public void deleteAlleBuecher()
    {
        synchronized( buecherPool ) {
            buecherPool.clear();
        }
    }

    private static boolean isEmpty( String s )
    {
        return s == null || s.trim().length() <= 0;
    }
}