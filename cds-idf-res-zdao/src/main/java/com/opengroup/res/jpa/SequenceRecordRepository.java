package com.opengroup.res.jpa;

import java.math.BigInteger;

/**
 * A repository to manage number generator independently
 *
 * @author Open group
 * @since  1.0.0
 */
public interface SequenceRecordRepository {

    /**
     * Number generation of record reference
     * @return
     */
    BigInteger next();
}
