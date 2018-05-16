package de.meinefirma.meinprojekt;

public class ResultTO
{
    private String meinConfigParm;
    private String meinAufrufParm;

    public ResultTO() {}

    public ResultTO( String meinConfigParm, String meinAufrufParm ) {
        this.meinConfigParm = meinConfigParm;
        this.meinAufrufParm = meinAufrufParm;
    }

    public String getMeinConfigParm() { return meinConfigParm; }
    public String getMeinAufrufParm() { return meinAufrufParm; }

    public void setMeinConfigParm( String meinConfigParm ) { this.meinConfigParm = meinConfigParm; }
    public void setMeinAufrufParm( String meinAufrufParm ) { this.meinAufrufParm = meinAufrufParm; }
}