package com.shakemate.post.model;
import com.shakemate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PostHibernateDAO implements PostDAO_interface{

    @Override
    public void insert(PostVO postVO) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.save(postVO);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void update(PostVO postVO) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.update(postVO);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Integer postId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            PostVO postVO = session.get(PostVO.class, postId);
            if (postVO != null) {
                session.delete(postVO);
            }
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public PostVO findByPrimaryKey(Integer postId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.get(PostVO.class, postId);
        } finally {
            session.close();
        }
    }

    @Override
    public List<PostVO> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.createQuery("from PostVO order by postId", PostVO.class).list();
        } finally {
            session.close();
        }
    }
}
