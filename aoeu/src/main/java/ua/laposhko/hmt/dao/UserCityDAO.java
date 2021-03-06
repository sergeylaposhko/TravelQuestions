package ua.laposhko.hmt.dao;

import ua.laposhko.hmt.dao.exception.NoSuchEntityException;
import ua.laposhko.hmt.entity.User;
import ua.laposhko.hmt.entity.UserCity;

import java.util.List;

/**
 * The Class UserCityDAO.
 *
 * @author Sergey Laposhko
 */
public abstract class UserCityDAO {

    /**
     * Find user cities by user.
     *
     * @param userId the user id
     * @return the list
     */
    public abstract List<UserCity> findUserCitiesByUser(long userId);

    /**
     * Find user cities by city.
     *
     * @param cityId the city id
     * @return the list
     */
    public abstract List<UserCity> findUserCitiesByCity(User cityId);

    /**
     * Creates the user city.
     *
     * @param userCity the user city
     */
    public abstract void createUserCity(UserCity userCity) throws NoSuchEntityException;

    /**
     * Update user city.
     *
     * @param userCity the user city
     */
    public abstract void updateUserCity(UserCity userCity) throws NoSuchEntityException;

    /**
     * Delete user city.
     *
     * @param userCity the user city
     */
    public abstract void deleteUserCity(UserCity userCity);

}
