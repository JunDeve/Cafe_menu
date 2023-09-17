package kr.co.edu.menu.web;

import kr.co.edu.menu.service.MenuService;
import kr.co.edu.menu.service.impl.MenuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;

@Controller
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView main () {
        ModelAndView mav = new ModelAndView("jsonView");
        HashMap<String, Object> result = new HashMap<>();
        result.put("testNumber", 1);
        result.put("testString", "문자열");
        mav.addObject(result);
        return mav;
    }

    @RequestMapping(value = "/menuInsert.request", method = RequestMethod.POST)
    public ModelAndView menuInsert (@RequestBody HashMap<String, Object> param) {
        System.out.println("menuInsert Controller: " + param);
        ModelAndView mav = new ModelAndView("jsonView");
        int insertCount = menuService.menuInsert(param);
        mav.addObject("result", insertCount);
        return mav;
    }

    @RequestMapping(value = "/menuSelectList.request", method = RequestMethod.GET)
    public ModelAndView menuSelectList () {
        ModelAndView mav = new ModelAndView("jsonView");
        List<HashMap<String, Object>> menuList = menuService.menuSelectList();
        mav.addObject("result", menuList);
        return mav;
    }

    @DeleteMapping("/menuDelete.request")
    public int menuDelete(@RequestParam("menu_id") int menuId) {
        // 데이터베이스에서 아이템 삭제 처리
        int deletedCount = menuService.menuDelete(menuId);
        return deletedCount;
    }
}
