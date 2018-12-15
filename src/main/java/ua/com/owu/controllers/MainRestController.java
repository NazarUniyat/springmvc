package ua.com.owu.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.dao.GenericSearchDAO;
import ua.com.owu.models.Account;
import ua.com.owu.models.Place;
import ua.com.owu.service.placeService.PlaceService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MainRestController {

    @Autowired
    PlaceService placeService;

    @Autowired
    GenericSearchDAO searchDAO;



    @RequestMapping(value = "/api/adminsearch/data", method = RequestMethod.GET)
    @ResponseBody
    public List<Account> getBarBySimplePathWithRequestParam(
            @RequestParam(value = "username", defaultValue = "test") String username,
            @RequestParam(value = "email", defaultValue = "test") String email,
            @RequestParam(value = "role", defaultValue = "test") String role

    ) {

        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("email", email);
        params.put("role", role);
        return searchDAO.get(params, "Account");
    }


    @RequestMapping(value = "/api/places/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Account> getPlaces(
            @RequestParam(value = "name", defaultValue = "test") String name,
            @RequestParam(value = "city", defaultValue = "test") String city,
            @RequestParam(value = "specification", defaultValue = "test") String specification

    ) {

        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("city", city);
        params.put("specification", specification);
        return searchDAO.get(params, "Place");
    }

}
