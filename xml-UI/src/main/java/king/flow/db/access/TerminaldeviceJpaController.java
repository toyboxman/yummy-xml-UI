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
import king.flow.db.entity.Terminalinfo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import king.flow.db.access.exceptions.IllegalOrphanException;
import king.flow.db.access.exceptions.NonexistentEntityException;
import king.flow.db.access.exceptions.PreexistingEntityException;
import king.flow.db.entity.Terminaldevice;

/**
 *
 * @author LiuJin
 */
public class TerminaldeviceJpaController implements Serializable {

    public TerminaldeviceJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Terminaldevice terminaldevice) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Terminalinfo terminalinfoOrphanCheck = terminaldevice.getTerminalinfo();
        if (terminalinfoOrphanCheck != null) {
            Terminaldevice oldTerminaldeviceOfTerminalinfo = terminalinfoOrphanCheck.getTerminaldevice();
            if (oldTerminaldeviceOfTerminalinfo != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Terminalinfo " + terminalinfoOrphanCheck + " already has an item of type Terminaldevice whose terminalinfo column cannot be null. Please make another selection for the terminalinfo field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Terminalinfo terminalinfo = terminaldevice.getTerminalinfo();
            if (terminalinfo != null) {
                terminalinfo = em.getReference(terminalinfo.getClass(), terminalinfo.getTerminalno());
                terminaldevice.setTerminalinfo(terminalinfo);
            }
            em.persist(terminaldevice);
            if (terminalinfo != null) {
                terminalinfo.setTerminaldevice(terminaldevice);
                terminalinfo = em.merge(terminalinfo);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTerminaldevice(terminaldevice.getTerminalno()) != null) {
                throw new PreexistingEntityException("Terminaldevice " + terminaldevice + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Terminaldevice terminaldevice) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Terminaldevice persistentTerminaldevice = em.find(Terminaldevice.class, terminaldevice.getTerminalno());
            Terminalinfo terminalinfoOld = persistentTerminaldevice.getTerminalinfo();
            Terminalinfo terminalinfoNew = terminaldevice.getTerminalinfo();
            List<String> illegalOrphanMessages = null;
            if (terminalinfoNew != null && !terminalinfoNew.equals(terminalinfoOld)) {
                Terminaldevice oldTerminaldeviceOfTerminalinfo = terminalinfoNew.getTerminaldevice();
                if (oldTerminaldeviceOfTerminalinfo != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Terminalinfo " + terminalinfoNew + " already has an item of type Terminaldevice whose terminalinfo column cannot be null. Please make another selection for the terminalinfo field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (terminalinfoNew != null) {
                terminalinfoNew = em.getReference(terminalinfoNew.getClass(), terminalinfoNew.getTerminalno());
                terminaldevice.setTerminalinfo(terminalinfoNew);
            }
            terminaldevice = em.merge(terminaldevice);
            if (terminalinfoOld != null && !terminalinfoOld.equals(terminalinfoNew)) {
                terminalinfoOld.setTerminaldevice(null);
                terminalinfoOld = em.merge(terminalinfoOld);
            }
            if (terminalinfoNew != null && !terminalinfoNew.equals(terminalinfoOld)) {
                terminalinfoNew.setTerminaldevice(terminaldevice);
                terminalinfoNew = em.merge(terminalinfoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = terminaldevice.getTerminalno();
                if (findTerminaldevice(id) == null) {
                    throw new NonexistentEntityException("The terminaldevice with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Terminaldevice terminaldevice;
            try {
                terminaldevice = em.getReference(Terminaldevice.class, id);
                terminaldevice.getTerminalno();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The terminaldevice with id " + id + " no longer exists.", enfe);
            }
            Terminalinfo terminalinfo = terminaldevice.getTerminalinfo();
            if (terminalinfo != null) {
                terminalinfo.setTerminaldevice(null);
                terminalinfo = em.merge(terminalinfo);
            }
            em.remove(terminaldevice);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Terminaldevice> findTerminaldeviceEntities() {
        return findTerminaldeviceEntities(true, -1, -1);
    }

    public List<Terminaldevice> findTerminaldeviceEntities(int maxResults, int firstResult) {
        return findTerminaldeviceEntities(false, maxResults, firstResult);
    }

    private List<Terminaldevice> findTerminaldeviceEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Terminaldevice.class));
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

    public Terminaldevice findTerminaldevice(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Terminaldevice.class, id);
        } finally {
            em.close();
        }
    }

    public int getTerminaldeviceCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Terminaldevice> rt = cq.from(Terminaldevice.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
