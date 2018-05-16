package de.meinefirma.meinprojekt.client;

import javax.ws.rs.client.*;
import javax.ws.rs.core.*;

/** RESTful-Webservice-Client */
public class BuecherRestClient
{
    public static void main( String[] args )
    {
        Client c = ClientBuilder.newClient();
        WebTarget webTarget = c.target( "http://localhost:8080/JaxRsBuecherverwaltung/rest/Artikel/Buecher" );
        Response response = null;

        if( args.length > 1 && args[0].toLowerCase().equals( "getbuchbyid" ) ) {
            System.out.println( "\n--- Buch mit ID " + args[1] + ":" );
            System.out.println( getBuchById( webTarget, args[1] ) );
        }
        else if( args.length > 2 && args[0].toLowerCase().equals( "findebuecher" ) ) {
            System.out.println( "\n--- Finde Buecher mit ISBN " + args[1] + " oder Titel '" + args[2] + "':" );
            System.out.println( findeBuecher( webTarget, null, args[1], args[2] ) );
        }
        else if( args.length > 4 && args[0].toLowerCase().equals( "putbuch" ) ) {
            System.out.println( "\n--- Aendere Daten zum Buch mit der ID " + args[1] + ":" );
            response = putBuch( webTarget, args[1], args[2], args[3], args[4] );
        }
        else if( args.length > 3 && args[0].toLowerCase().equals( "postbuch" ) ) {
            System.out.println( "\n--- Neues Buch anlegen:" );
            response = postBuch( webTarget, args[1], args[2], args[3] );
        }
        else if( args.length > 1 && args[0].toLowerCase().equals( "deletebuch" ) ) {
            System.out.println( "\n--- Loesche Buch mit ID " + args[1] + ":" );
            response = deleteBuch( webTarget, args[1] );
        }
        if( response != null ) {
            System.out.println( response.getStatus() + " " + response.getStatusInfo() );
            System.out.println( response );
        }
    }

    static String getBuchById( WebTarget webTarget, String id )
    {
        return webTarget.path( id ).request().get( String.class );
    }

    static String findeBuecher( WebTarget webTarget, String id, String isbn, String titel )
    {
        WebTarget wt = webTarget;
        if( id    != null ) wt = wt.queryParam( "id",    id );
        if( isbn  != null ) wt = wt.queryParam( "isbn",  isbn );
        if( titel != null ) wt = wt.queryParam( "titel", titel );
        return wt.request().get( String.class );
    }

    static Response putBuch( WebTarget webTarget, String id, String isbn, String titel, String preis )
    {
        MultivaluedMap<String,String> formData = new MultivaluedHashMap<>();
        formData.add( "isbn",  isbn );
        formData.add( "titel", titel );
        formData.add( "preis", preis );
        return webTarget.path( id ).request().put( Entity.form( formData ) );
    }

    static Response postBuch( WebTarget webTarget, String isbn, String titel, String preis )
    {
        MultivaluedMap<String,String> formData = new MultivaluedHashMap<>();
        formData.add( "isbn",  isbn );
        formData.add( "titel", titel );
        formData.add( "preis", preis );
        return webTarget.request().post( Entity.form( formData ) );
    }

    static Response deleteBuch( WebTarget webTarget, String id )
    {
        return webTarget.path( id ).request().delete();
    }
}