package ua.laposhko.hmt.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.laposhko.hmt.entity.City;
import ua.laposhko.hmt.entity.User;
import ua.laposhko.hmt.service.city.ICityService;
import ua.laposhko.hmt.service.user.IUserService;
import ua.laposhko.hmt.session.SessionManager;
import ua.laposhko.hmt.web.exception.WrongParamException;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Sergey Laposhko
 */
@Controller
@RequestMapping("/guide")
public class GuideWebService extends AbstractWebService {

    private static final Logger LOGGER = Logger.getLogger(GuideWebService.class);

    private IUserService<User> userService;
    private ICityService<City> cityService;

    @Autowired
    public void setCityService(ICityService<City> cityService) {
        this.cityService = cityService;
    }

    @Autowired
    public void setUserService(IUserService<User> userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/bycity", method = RequestMethod.GET)
    public
    @ResponseBody
    List<User> getGuideListByCity(@RequestParam("cityId") long cityId) {
        LOGGER.debug("Prociding getGuideListByCity command with param "
                + cityId);

        City city = cityService.findById(cityId);
        List<User> users = city.getUsers();

        LOGGER.debug("Users count: " + users.size());
        return users;
    }

    @RequestMapping(value = "/where", method = RequestMethod.GET)
    public
    @ResponseBody
    List<User> getGuideListByKey(@RequestParam("cityId") long cityId,
                                 @RequestParam("key") String key) {
        LOGGER.debug("Prociding getGuideListByCity command with param "
                + cityId);
        List<User> usersInTheCityList = getGuideListByCity(cityId);
        List<User> res = new LinkedList<User>();
        for (User user : usersInTheCityList) {
            if (user.getFirstName().toLowerCase().contains(key.toLowerCase()) ||
                    user.getLastName().toLowerCase().contains(key.toLowerCase())) {
                res.add(user);
            }
        }
        LOGGER.debug("Users count for key " + key + ": " + res.size());
        return res;
    }

    @RequestMapping(value = "/byid", method = RequestMethod.GET)
    public
    @ResponseBody
    User getGuideById(@RequestParam("id") long id) {
        LOGGER.debug("Prociding getGuideListByCity command with param ");
        User user = userService.findById(id);

        if (user == null) {
            LOGGER.warn("User wasn't found with id " + id);
        }
        return user;
    }

    @RequestMapping(value = "/bysession", method = RequestMethod.GET)
    public
    @ResponseBody
    User getGuideBySession(@RequestParam("sessionId") String sessionId) {
        LOGGER.debug("Prociding getGuideBySession command with param "
                + sessionId);
        SessionManager sessionManager = SessionManager.getInstance();
        if (!sessionManager.sessionExists(sessionId)) {
            LOGGER.warn("Trying to get user of unexsisting session");
            throw new WrongParamException("sessionId");
        }
        long id = sessionManager.getUserId(sessionId);
        return getGuideById(id);
    }

}
