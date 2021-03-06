package ua.laposhko.hmt.dao;

import ua.laposhko.hmt.entity.Sex;
import ua.laposhko.hmt.entity.User;

import java.util.List;

/**
 * @author Sergey Laposhko
 */
public abstract class SexDAO {

    /**
     * Find all sexes.
     *
     * @return the list
     */
    public abstract List<Sex> findAllSexes();

    /**
     * Find sex by id.
     *
     * @param sexId the sex id
     * @return the sex
     */
    public abstract Sex findSexById(User sexId);

}
