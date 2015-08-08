/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package king.flow.db.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author LiuJin
 */
@Entity
@Table(name = "TERMINALINFO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Terminalinfo.findAll", query = "SELECT t FROM Terminalinfo t"),
    @NamedQuery(name = "Terminalinfo.findByTerminalno", query = "SELECT t FROM Terminalinfo t WHERE t.terminalno = :terminalno"),
    @NamedQuery(name = "Terminalinfo.findByTerminalip", query = "SELECT t FROM Terminalinfo t WHERE t.terminalip = :terminalip"),
    @NamedQuery(name = "Terminalinfo.findByTerminalstatus", query = "SELECT t FROM Terminalinfo t WHERE t.terminalstatus = :terminalstatus"),
    @NamedQuery(name = "Terminalinfo.findByTerminalid", query = "SELECT t FROM Terminalinfo t WHERE t.terminalid = :terminalid"),
    @NamedQuery(name = "Terminalinfo.findByTerminaladdr", query = "SELECT t FROM Terminalinfo t WHERE t.terminaladdr = :terminaladdr"),
    @NamedQuery(name = "Terminalinfo.findByTerminalcall", query = "SELECT t FROM Terminalinfo t WHERE t.terminalcall = :terminalcall")})
public class Terminalinfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TERMINALNO")
    private String terminalno;
    @Column(name = "TERMINALIP")
    private String terminalip;
    @Column(name = "TERMINALSTATUS")
    private Integer terminalstatus;
    @Column(name = "TERMINALID")
    private String terminalid;
    @Column(name = "TERMINALADDR")
    private String terminaladdr;
    @Column(name = "TERMINALCALL")
    private String terminalcall;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "terminalinfo")
    private Terminaldevice terminaldevice;

    public Terminalinfo() {
    }

    public Terminalinfo(String terminalno) {
        this.terminalno = terminalno;
    }

    public String getTerminalno() {
        return terminalno;
    }

    public void setTerminalno(String terminalno) {
        this.terminalno = terminalno;
    }

    public String getTerminalip() {
        return terminalip;
    }

    public void setTerminalip(String terminalip) {
        this.terminalip = terminalip;
    }

    public Integer getTerminalstatus() {
        return terminalstatus;
    }

    public void setTerminalstatus(Integer terminalstatus) {
        this.terminalstatus = terminalstatus;
    }

    public String getTerminalid() {
        return terminalid;
    }

    public void setTerminalid(String terminalid) {
        this.terminalid = terminalid;
    }

    public String getTerminaladdr() {
        return terminaladdr;
    }

    public void setTerminaladdr(String terminaladdr) {
        this.terminaladdr = terminaladdr;
    }

    public String getTerminalcall() {
        return terminalcall;
    }

    public void setTerminalcall(String terminalcall) {
        this.terminalcall = terminalcall;
    }

    public Terminaldevice getTerminaldevice() {
        return terminaldevice;
    }

    public void setTerminaldevice(Terminaldevice terminaldevice) {
        this.terminaldevice = terminaldevice;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (terminalno != null ? terminalno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Terminalinfo)) {
            return false;
        }
        Terminalinfo other = (Terminalinfo) object;
        if ((this.terminalno == null && other.terminalno != null) || (this.terminalno != null && !this.terminalno.equals(other.terminalno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "king.flow.db.entity.Terminalinfo[ terminalno=" + terminalno + " ]";
    }
    
}
