/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package king.flow.db.access;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import king.flow.db.entity.Terminaldevice;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import king.flow.db.access.exceptions.IllegalOrphanException;
import king.flow.db.access.exceptions.NonexistentEntityException;
import king.flow.db.access.exceptions.PreexistingEntityException;
import king.flow.db.entity.Terminalinfo;

/**
 *
 * @author LiuJin
 */
public class TerminalinfoJpaController implements Serializable {

    public TerminalinfoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Terminalinfo terminalinfo) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Terminaldevice terminaldevice = terminalinfo.getTerminaldevice();
            if (terminaldevice != null) {
                terminaldevice = em.getReference(terminaldevice.getClass(), terminaldevice.getTerminalno());
                terminalinfo.setTerminaldevice(terminaldevice);
            }
            em.persist(terminalinfo);
            if (terminaldevice != null) {
                Terminalinfo oldTerminalinfoOfTerminaldevice = terminaldevice.getTerminalinfo();
                if (oldTerminalinfoOfTerminaldevice != null) {
                    oldTerminalinfoOfTerminaldevice.setTerminaldevice(null);
                    oldTerminalinfoOfTerminaldevice = em.merge(oldTerminalinfoOfTerminaldevice);
                }
                terminaldevice.setTerminalinfo(terminalinfo);
                terminaldevice = em.merge(terminaldevice);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTerminalinfo(terminalinfo.getTerminalno()) != null) {
                throw new PreexistingEntityException("Terminalinfo " + terminalinfo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Terminalinfo terminalinfo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Terminalinfo persistentTerminalinfo = em.find(Terminalinfo.class, terminalinfo.getTerminalno());
            Terminaldevice terminaldeviceOld = persistentTerminalinfo.getTerminaldevice();
            Terminaldevice terminaldeviceNew = terminalinfo.getTerminaldevice();
            List<String> illegalOrphanMessages = null;
            if (terminaldeviceOld != null && !terminaldeviceOld.equals(terminaldeviceNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Terminaldevice " + terminaldeviceOld + " since its terminalinfo field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (terminaldeviceNew != null) {
                terminaldeviceNew = em.getReference(terminaldeviceNew.getClass(), terminaldeviceNew.getTerminalno());
                terminalinfo.setTerminaldevice(terminaldeviceNew);
            }
            terminalinfo = em.merge(terminalinfo);
            if (terminaldeviceNew != null && !terminaldeviceNew.equals(terminaldeviceOld)) {
                Terminalinfo oldTerminalinfoOfTerminaldevice = terminaldeviceNew.getTerminalinfo();
                if (oldTerminalinfoOfTerminaldevice != null) {
                    oldTerminalinfoOfTerminaldevice.setTerminaldevice(null);
                    oldTerminalinfoOfTerminaldevice = em.merge(oldTerminalinfoOfTerminaldevice);
                }
                terminaldeviceNew.setTerminalinfo(terminalinfo);
                terminaldeviceNew = em.merge(terminaldeviceNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = terminalinfo.getTerminalno();
                if (findTerminalinfo(id) == null) {
                    throw new NonexistentEntityException("The terminalinfo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Terminalinfo terminalinfo;
            try {
                terminalinfo = em.getReference(Terminalinfo.class, id);
                terminalinfo.getTerminalno();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The terminalinfo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Terminaldevice terminaldeviceOrphanCheck = terminalinfo.getTerminaldevice();
            if (terminaldeviceOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Terminalinfo (" + terminalinfo + ") cannot be destroyed since the Terminaldevice " + terminaldeviceOrphanCheck + " in its terminaldevice field has a non-nullable terminalinfo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(terminalinfo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Terminalinfo> findTerminalinfoEntities() {
        return findTerminalinfoEntities(true, -1, -1);
    }

    public List<Terminalinfo> findTerminalinfoEntities(int maxResults, int firstResult) {
        return findTerminalinfoEntities(false, maxResults, firstResult);
    }

    private List<Terminalinfo> findTerminalinfoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Terminalinfo.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Terminalinfo findTerminalinfo(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Terminalinfo.class, id);
        } finally {
            em.close();
        }
    }

    public int getTerminalinfoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Terminalinfo> rt = cq.from(Terminalinfo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
