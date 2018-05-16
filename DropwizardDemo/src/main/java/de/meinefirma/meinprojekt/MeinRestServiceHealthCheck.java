package de.meinefirma.meinprojekt;

import com.codahale.metrics.health.HealthCheck;

public class MeinRestServiceHealthCheck extends HealthCheck
{
    private final String meinConfigParm;

    public MeinRestServiceHealthCheck( String meinConfigParm )
    {
        this.meinConfigParm = meinConfigParm;
    }

    @Override
    protected Result check() throws Exception
    {
        if( meinConfigParm == null ) {
            return Result.unhealthy( "MeinConfigParm ist nicht definiert." );
        }
        if( meinConfigParm.length() < 2 ) {
            return Result.unhealthy( "MeinConfigParm ist zu kurz." );
        }
        if( !Character.isLetter( meinConfigParm.charAt( 0 ) ) ) {
            return Result.unhealthy( "MeinConfigParm beginnt nicht mit einem Buchstaben." );
        }
        return Result.healthy();
    }
}