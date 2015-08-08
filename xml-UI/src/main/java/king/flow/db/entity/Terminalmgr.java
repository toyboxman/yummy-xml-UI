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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author LiuJin
 */
@Entity
@Table(name = "TERMINALMGR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Terminalmgr.findAll", query = "SELECT t FROM Terminalmgr t"),
    @NamedQuery(name = "Terminalmgr.findByName", query = "SELECT t FROM Terminalmgr t WHERE t.name = :name"),
    @NamedQuery(name = "Terminalmgr.findByCredentials", query = "SELECT t FROM Terminalmgr t WHERE t.credentials = :credentials")})
public class Terminalmgr implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;
    @Column(name = "CREDENTIALS")
    private String credentials;

    public Terminalmgr() {
    }

    public Terminalmgr(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCredentials() {
        return credentials;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Terminalmgr)) {
            return false;
        }
        Terminalmgr other = (Terminalmgr) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "king.flow.db.entity.Terminalmgr[ name=" + name + " ]";
    }
    
}
