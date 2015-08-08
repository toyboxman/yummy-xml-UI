/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package king.flow.db.access;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import king.flow.db.access.exceptions.NonexistentEntityException;
import king.flow.db.access.exceptions.PreexistingEntityException;
import king.flow.db.entity.Terminalmgr;

/**
 *
 * @author LiuJin
 */
public class TerminalmgrJpaController implements Serializable {

    public TerminalmgrJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Terminalmgr terminalmgr) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(terminalmgr);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTerminalmgr(terminalmgr.getName()) != null) {
                throw new PreexistingEntityException("Terminalmgr " + terminalmgr + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Terminalmgr terminalmgr) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            terminalmgr = em.merge(terminalmgr);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = terminalmgr.getName();
                if (findTerminalmgr(id) == null) {
                    throw new NonexistentEntityException("The terminalmgr with id " + id + " no longer exists.");
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
            Terminalmgr terminalmgr;
            try {
                terminalmgr = em.getReference(Terminalmgr.class, id);
                terminalmgr.getName();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The terminalmgr with id " + id + " no longer exists.", enfe);
            }
            em.remove(terminalmgr);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Terminalmgr> findTerminalmgrEntities() {
        return findTerminalmgrEntities(true, -1, -1);
    }

    public List<Terminalmgr> findTerminalmgrEntities(int maxResults, int firstResult) {
        return findTerminalmgrEntities(false, maxResults, firstResult);
    }

    private List<Terminalmgr> findTerminalmgrEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Terminalmgr.class));
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

    public Terminalmgr findTerminalmgr(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Terminalmgr.class, id);
        } finally {
            em.close();
        }
    }

    public int getTerminalmgrCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Terminalmgr> rt = cq.from(Terminalmgr.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
