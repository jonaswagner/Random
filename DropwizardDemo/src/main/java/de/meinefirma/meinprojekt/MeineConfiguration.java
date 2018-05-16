package de.meinefirma.meinprojekt;

import org.hibernate.validator.constraints.NotEmpty;
import io.dropwizard.Configuration;

public class MeineConfiguration extends Configuration
{
    @NotEmpty
    private String meinConfigParm;

    public String getMeinConfigParm() { return meinConfigParm; }

    public void setMeinConfigParm( String meinConfigParm ) { this.meinConfigParm = meinConfigParm; }
}