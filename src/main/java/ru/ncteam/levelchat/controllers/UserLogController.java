package ru.ncteam.levelchat.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.ncteam.levelchat.entity.CategoryInterest;
import ru.ncteam.levelchat.entity.Interests;
import ru.ncteam.levelchat.entity.UserInfo;
import ru.ncteam.levelchat.service.UserLogService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;


@Controller
public class UserLogController {
    @Autowired
    private UserLogService userLogService;

    private static Logger log = Logger.getLogger("h");

    @RequestMapping("/login")
    public String login(String error,
                        Map<String, Object> map)
    {
        if(error==null)
        {
            return "login";
        }
        if(error.equals("true"))
        {
            map.put("error","Введён неправильный логин или пароль");
        }

        return "login";
    }

    @RequestMapping(value = "/adminpage", method = RequestMethod.GET)
    public String adminPage(Map<String, Object> map) {
        map.put("categoryInterestsList", userLogService.getAllCategory());
        return "adminpage";
    }

    @RequestMapping(value = "/adminpage", method = RequestMethod.POST)
    @ResponseBody
    public String putCategoryInterest(@RequestBody String categoryName) {
        try {
            userLogService.putCategoryInterestByName(categoryName);
            return categoryName;
        } catch (Exception e) {
            return "fail";
        }
    }


    @RequestMapping(value = "/adminpage/{categoryName}", method = RequestMethod.GET)
    @ResponseBody
    public List<Interests> getInterestsByName(@PathVariable String categoryName) {
        return userLogService.getInterestsByCatName(categoryName);
    }

    @RequestMapping(value = "/adminpage/update", method = RequestMethod.POST)
    @ResponseBody
    public String updateInterests(@RequestBody ArrayList<Interests> interests,
                                  BindingResult result) {
        try {
            userLogService.updateInterests(interests);
            return "success";
        } catch (Exception e) {
            return "fail";
        }
    }

    @RequestMapping(value = "/adminpage/put/{categoryName}", method = RequestMethod.POST)
    @ResponseBody
    public List<Long> putInterests(@PathVariable String categoryName,
                                   @RequestBody ArrayList<Interests> interests,
                                   BindingResult result) {
        try {
            return userLogService.putInterests(interests, categoryName);
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping(value = "/adminpage/delete/{categoryName}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteInterests(@PathVariable String categoryName,
                                  @RequestBody ArrayList<Interests> interests,
                                  BindingResult result) {
        try {
            userLogService.deleteInterests(interests, categoryName);
            return "success";
        } catch (Exception e) {
            return "fail";
        }
    }

    @RequestMapping(value = "/adminpage/deleteCategory/{categoryName}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteCategory(@PathVariable String categoryName) {
        try {
            userLogService.deleteCategory(categoryName);
            return "success";
        } catch (Exception e) {
            return "fail";
        }
    }


    @RequestMapping("/registration")
    public String registrationPage() {
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String addContact(@ModelAttribute("usersLog") @Valid UserInfo userInfo,
                             BindingResult result,
                             Model model) {
        String userPassword = userInfo.getPassword();
        if (result.hasErrors()) {
            String code;
            List<FieldError> listErrors = result.getFieldErrors();
            for (FieldError listError : listErrors) {
                code = listError.getCode();
                if (!code.equals("typeMismatch")) {
                    model.addAttribute(listError.getField() + "Error", listError.getDefaultMessage());
                }
            }
            return "registration";
        }
        if (userLogService.addUser(userInfo).equals("success")) {
            userLogService.autoLogin(userInfo.getLogin(), userPassword);
            return "redirect:/userpage";
        } else {
            model.addAttribute("loginError", "Пользователь с таким логином уже существует");
            return "registration";
        }
    }

    @RequestMapping(value = "/registration/check", method = RequestMethod.POST)
    @ResponseBody
    public String checkLogin(@RequestParam(value = "login", required = false) String login) {
        if (userLogService.checkLogin(login)) {
            return "success";
        }
        return "fail";
    }

    @RequestMapping("/postregistration")
    public String postRegistrationPage() {
        return "postregistration";
    }

    @RequestMapping("/postregistrationPhoto")
    public String postRegistrationPhotoPage() {
        return "postregistrationPhoto";
    }

    @RequestMapping("/trial")
    public String trial() {
        return "trial";
    }

    @RequestMapping(value = "/anketa", method = RequestMethod.GET)
    public String anketa (ModelMap model)
    {
        List<CategoryInterest> category = new ArrayList<CategoryInterest>();
        category = userLogService.getAllCategory();
        List<String> l = new ArrayList<String>();
        for(CategoryInterest currСategory : category){
            l.add(currСategory.getCategoryName());
            //получаю лист с интересами и засовываю в model для .jsp
            List<Interests> lost = new ArrayList<Interests>();
            lost = userLogService.getListInterests(currСategory);
            model.put(currСategory.getCategoryName(), lost);
        }
        model.put("CategoryList", l);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserInfo userInfo = userLogService.getUserByLogin(user.getUsername());
        if(userInfo.getPhoto_ava()==null)
        {
            userInfo.setPhoto_ava("photo/ava.png");
        }
        model.put("userInfo",userInfo);
        return "anketa";
    }

    @RequestMapping(value = "/ajaxtest", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> handleGetHelp(Locale loc, String code, HttpServletRequest request) throws JsonSyntaxException, JsonIOException, IOException {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/html; charset=utf-8");

        JSONObject json = new JSONObject();

        JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
        String name = data.get("name").getAsString();
        System.out.println(name);

        CategoryInterest cat = new CategoryInterest();
        cat = userLogService.getCategorie(name);

        List<Interests> interestsList = new ArrayList<Interests>();
        interestsList = userLogService.getInterestsByCatName(name);
        int count = 0;
        for(Interests curr : interestsList)
        {
            json.put( String.valueOf(count), curr.getInterestName() );
            count++;
        }

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<Interests> interests = new HashSet<Interests>();

        UserInfo userInfo = new UserInfo();
        interests = userInfo.getInterests();


        System.out.println(String.valueOf(userLogService.getUSER_ID(user.getUsername())));

        return new ResponseEntity<String>(json.toString(), responseHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/ajaxFullInterest", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> getFullInterests(Locale loc) throws JsonSyntaxException, JsonIOException, IOException {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/html; charset=utf-8");

        JSONObject json = new JSONObject();

        List<CategoryInterest> Category = new ArrayList<CategoryInterest>();
        Category = userLogService.getAllCategory();

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int count = 0;
        for(CategoryInterest nameCategory:Category){
            json.put( String.valueOf(count), nameCategory.getCategoryName());
	    	/*System.out.println(String.valueOf(userLogService.getCategoryIDByCatName(nameCategory.getCategoryName()))+" : ");
	    	List<String> interests = userLogService.getUsersInterests(userLogService.getUSER_ID(user.getUsername()), userLogService.getCategoryIDByCatName(nameCategory.getCategoryName()));

			for(String inter:interests)
			{
				json.put( String.valueOf(count), inter);
				System.out.println(inter+";");
				count++;
			}	*/
            count++;
        }

        return new ResponseEntity<String>(json.toString(), responseHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/ajaxGetInterestCat", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> getUserInterests(Locale loc, String code, HttpServletRequest request) throws JsonSyntaxException, JsonIOException, IOException {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/html; charset=utf-8");

        JSONObject json = new JSONObject();

        JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
        String nameCategory = data.get("name").getAsString();

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        System.out.println(String.valueOf(userLogService.getCategoryIDByCatName(nameCategory)));

        List<String> interests = userLogService.getUsersInterests(userLogService.getUSER_ID(user.getUsername()), userLogService.getCategoryIDByCatName(nameCategory));
        int count = 0;
        for(String inter:interests)
        {
            json.put( String.valueOf(count), inter);
            count++;
        }
        return new ResponseEntity<String>(json.toString(), responseHeaders, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/ajaxSave", method = RequestMethod.POST)
    @ResponseBody
    public void saveInterestsForUser(HttpServletRequest request) throws JsonSyntaxException, JsonIOException, IOException{
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        JSONObject json = new JSONObject();
        JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
        int count = data.get("count").getAsInt();
        List<String> NamesInterests = new ArrayList<String>();
        for(int i = 0; i < count; i++){
            NamesInterests.add(data.get(String.valueOf(i)).getAsString());
        }
        //long id_category = userLogService.getCategoryIDByCatName(data.get("category").getAsString());
        Set<Interests> InterstsList = userLogService.getInterestsByInteresName(NamesInterests);
        long userID = userLogService.getUSER_ID(user.getUsername());
        userLogService.putInterestsUser(userID, InterstsList);
        for(Interests curr:InterstsList){
            System.out.println(curr.getInterestId());
        }

		/*List<String> listInterestsName =  new ArrayList<String>();
		JsonObject massJson = data.
		for(JsonObject curr:data){

		}*/
        //String nameCategory = data.get("1").getAsString();
        //System.out.println("ID Пользователя: "+String.valueOf(userLogService.getUSER_ID(user.getUsername()))+"\nRequets: "+nameCategory);
    }

    @RequestMapping(value = "/ajaxSaveInteres", method = RequestMethod.POST)
    @ResponseBody
    public void ajaxSaveInteres(HttpServletRequest request) throws JsonSyntaxException, JsonIOException, IOException {

        JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
        int count = data.get("count").getAsInt();

        CategoryInterest category = userLogService.getCategorie(data.get("category").getAsString());

        List<Interests> Interes = new ArrayList<Interests>();
        for(int i = 0; i < count; i++){
            Interests curr = new Interests();
            curr.setInterestName(data.get(String.valueOf(i)).getAsString());
            curr.setCategoryInterest(category);
            Interes.add(curr);
        }
        userLogService.putInterests(Interes, category.getCategoryName());
    }


}
