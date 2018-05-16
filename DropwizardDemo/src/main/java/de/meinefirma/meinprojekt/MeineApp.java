package de.meinefirma.meinprojekt;

        import io.dropwizard.Application;
        import io.dropwizard.setup.Environment;

public class MeineApp extends Application<MeineConfiguration>
{
    public static void main( String[] args ) throws Exception
    {
        new MeineApp().run( args );
    }

    @Override
    public void run( MeineConfiguration conf, Environment env ) throws Exception
    {
        MeinRestService meinRestService = new MeinRestService( conf.getMeinConfigParm() );
        MeinRestServiceHealthCheck meinHealthCheck = new MeinRestServiceHealthCheck( conf.getMeinConfigParm() );
        env.jersey().register( meinRestService );
        env.healthChecks().register( "MeinRestServiceHealthCheck", meinHealthCheck );
    }
}