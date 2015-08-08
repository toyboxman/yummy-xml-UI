/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package king.flow.db.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "TERMINALDEVICE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Terminaldevice.findAll", query = "SELECT t FROM Terminaldevice t"),
    @NamedQuery(name = "Terminaldevice.findByTerminalno", query = "SELECT t FROM Terminaldevice t WHERE t.terminalno = :terminalno"),
    @NamedQuery(name = "Terminaldevice.findByPrinterstate", query = "SELECT t FROM Terminaldevice t WHERE t.printerstate = :printerstate"),
    @NamedQuery(name = "Terminaldevice.findByInicstate", query = "SELECT t FROM Terminaldevice t WHERE t.inicstate = :inicstate"),
    @NamedQuery(name = "Terminaldevice.findByKeyboardstate", query = "SELECT t FROM Terminaldevice t WHERE t.keyboardstate = :keyboardstate"),
    @NamedQuery(name = "Terminaldevice.findByMagstate", query = "SELECT t FROM Terminaldevice t WHERE t.magstate = :magstate")})
public class Terminaldevice implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TERMINALNO")
    private String terminalno;
    @Column(name = "PRINTERSTATE")
    private Integer printerstate;
    @Column(name = "INICSTATE")
    private Integer inicstate;
    @Column(name = "KEYBOARDSTATE")
    private Integer keyboardstate;
    @Column(name = "MAGSTATE")
    private Integer magstate;
    @JoinColumn(name = "TERMINALNO", referencedColumnName = "TERMINALNO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Terminalinfo terminalinfo;

    public Terminaldevice() {
    }

    public Terminaldevice(String terminalno) {
        this.terminalno = terminalno;
    }

    public String getTerminalno() {
        return terminalno;
    }

    public void setTerminalno(String terminalno) {
        this.terminalno = terminalno;
    }

    public Integer getPrinterstate() {
        return printerstate;
    }

    public void setPrinterstate(Integer printerstate) {
        this.printerstate = printerstate;
    }

    public Integer getInicstate() {
        return inicstate;
    }

    public void setInicstate(Integer inicstate) {
        this.inicstate = inicstate;
    }

    public Integer getKeyboardstate() {
        return keyboardstate;
    }

    public void setKeyboardstate(Integer keyboardstate) {
        this.keyboardstate = keyboardstate;
    }

    public Integer getMagstate() {
        return magstate;
    }

    public void setMagstate(Integer magstate) {
        this.magstate = magstate;
    }

    public Terminalinfo getTerminalinfo() {
        return terminalinfo;
    }

    public void setTerminalinfo(Terminalinfo terminalinfo) {
        this.terminalinfo = terminalinfo;
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
        if (!(object instanceof Terminaldevice)) {
            return false;
        }
        Terminaldevice other = (Terminaldevice) object;
        if ((this.terminalno == null && other.terminalno != null) || (this.terminalno != null && !this.terminalno.equals(other.terminalno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "king.flow.db.entity.Terminaldevice[ terminalno=" + terminalno + " ]";
    }
    
}
