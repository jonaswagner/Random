package de.meinefirma.meinprojekt.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import de.meinefirma.meinprojekt.buecher.*;
import de.meinefirma.meinprojekt.dao.*;

/** RESTful-Webservice */
@Produces( MediaType.TEXT_XML )
@Path( "/Artikel/Buecher" )
public class BuecherRestService
{
    private BuchDoDAO dao = BuchDoDAO.getInstance();

    // Per ID definiertes Buch ausgeben:
    @GET @Path("{id}")
    public BuecherTO getBuchById(
            @PathParam("id") String id )
    {
        BuchDO bu = dao.getBuchById( longFromString( id ) );
        return BuecherUtil.erzeugeBuecherTO( "Buch mit ID " + id, bu );
    }

    // Liste von ueber Suchkriterien gefundener Buecher ausgeben:
    @GET
    public BuecherTO getBuecherListe(
            @QueryParam("id")    String id,
            @QueryParam("isbn")  String isbn,
            @QueryParam("titel") String titel )
    {
        return BuecherUtil.findeBuecher( longFromString( id ), isbn, titel );
    }

    // Daten eines per ID definierten Buches aendern:
    @PUT @Path("{id}")
    public BuecherTO updateBuchById(
            @PathParam("id")    String id,
            @FormParam("isbn")  String isbn,
            @FormParam("titel") String titel,
            @FormParam("preis") String preis )
    {
        BuchDO bu = BuecherUtil.erzeugeBuchDO( longFromString( id ), isbn, titel, doubleFromString( preis ) );
        return BuecherUtil.erzeugeBuecherTO( "Buchdaten geaendert", dao.updateBuchById( bu ) );
    }

    // Neues Buch hinzufuegen (ueber Formular):
    @POST
    public BuecherTO createBuch(
            @FormParam("isbn")  String isbn,
            @FormParam("titel") String titel,
            @FormParam("preis") String preis )
    {
        BuchDO bu = BuecherUtil.erzeugeBuchDO( null, isbn, titel, doubleFromString( preis ) );
        return BuecherUtil.erzeugeBuecherTO( "Buch hinzugefuegt", dao.createBuch( bu ) );
    }

    // Neues Buch hinzufuegen (ueber XML):
    @POST @Consumes( MediaType.TEXT_XML )
    public BuecherTO createBuch( BuchDO bu )
    {
        return BuecherUtil.erzeugeBuecherTO( "Buch hinzugefuegt", dao.createBuch( bu ) );
    }

    // Per ID definiertes Buch loeschen:
    @DELETE @Path("{id}")
    public BuecherTO deleteBuchById(
            @PathParam("id") String id )
    {
        BuchDO bu = dao.deleteBuchById( longFromString( id ) );
        return BuecherUtil.erzeugeBuecherTO( "Buch geloescht", bu );
    }

    private static Long longFromString( String s )
    {
        if( !BuecherUtil.isEmpty( s ) ) try { return new Long( s.trim() ); } catch( NumberFormatException e ) {/*ok*/}
        return null;
    }

    private static Double doubleFromString( String s )
    {
        if( !BuecherUtil.isEmpty( s ) ) try { return new Double( s.trim() ); } catch( NumberFormatException e ) {/*ok*/}
        return null;
    }
}