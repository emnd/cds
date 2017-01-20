package com.opengroup.res.jpa.impl;

import com.opengroup.res.jpa.SequenceRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.math.BigInteger;

/**
 * A number distribution tool
 *
 * @author Open group
 * @since 1.0.0
 */
@Repository
public class SequenceRecordRepositoryImpl implements SequenceRecordRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public BigInteger next() {
        return (BigInteger) entityManager.createNativeQuery("select nextval (' seq_name_record ')").getSingleResult();
    }
}
