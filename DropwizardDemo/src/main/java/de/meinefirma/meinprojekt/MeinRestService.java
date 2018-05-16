package de.meinefirma.meinprojekt;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.codahale.metrics.annotation.Timed;

@Path( MeinRestService.meinWebContextPfad )
@Produces( MediaType.APPLICATION_JSON )
public class MeinRestService
{
    public static final String meinWebContextPfad = "/zeigeparms";
    private final String meinConfigParm;

    public MeinRestService( String meinConfigParm )
    {
        this.meinConfigParm = meinConfigParm;
    }

    @GET
    @Timed
    public ResultTO zeigeParms( @QueryParam("meinAufrufParm") String meinAufrufParm )
    {
        return new ResultTO( meinConfigParm, meinAufrufParm );
    }
}