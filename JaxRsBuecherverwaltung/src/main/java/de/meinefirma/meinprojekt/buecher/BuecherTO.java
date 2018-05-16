package de.meinefirma.meinprojekt.buecher;

import java.util.*;
import javax.xml.bind.annotation.*;

/** Rueckgabe-Transferobjekt */
@XmlRootElement
public class BuecherTO
{
    private Integer      returncode;
    private String       message;
    @XmlElement(nillable = true)
    private List<BuchDO> results = new ArrayList<>();

    public Integer      getReturncode() { return returncode; }
    public String       getMessage()    { return message;    }
    public List<BuchDO> getResults()    { return results;    }
    public void setReturncode( Integer returncode ) { this.returncode = returncode; }
    public void setMessage(    String  message    ) { this.message    = message;    }
}