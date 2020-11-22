package com.lxy.cn.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {

    public int total = 0;

    @RequestMapping("/*")
    public String index(Model model, HttpSession session) {
        return "login";
    }

    @RequestMapping("/add")
    public String add() {
        return "details";
    }

    @PostMapping("/additem")
    public String additem(@RequestBody User user, HttpSession session) {
        user.setId(Integer.toString(total));
        session.setAttribute(Integer.toString(total), user);
        total++;
        return "index";
    }

    @GetMapping("/return")
    public String ret(Model model, String username, String password, HttpSession session) {
        System.out.println("RETURN total:" + total);
        List<User> list = getUser(session);
        if (list != null) {
            model.addAttribute("users", list);
        }
        return "index";

    }

    @PostMapping("/user")
    public String ok(Model model, String username, String password, HttpSession session) {
        System.out.println("返回值是>>>>>>" + username + "---->" + password);
        System.out.println("total:" + total);

        List<User> list = getUser(session);
        if (list != null) {
            model.addAttribute("users", list);
        }

        if (username.equals("123456") && password.equals("111111")) {
            return "index";
        } else {
            return "login";
        }
    }

    @ResponseBody
    @GetMapping("/delete")
    public String Produce(String id, HttpSession session) {
        System.out.println("delete" + id);
        session.removeAttribute(id);
        return null;
    }

    @GetMapping("/edit")
    public String edit(Model model, String id, HttpSession session) {
        System.out.println("edit" + id);
        User user = (User) session.getAttribute(id);
        session.removeAttribute(id);
        model.addAttribute("xingming",user.getName());
        model.addAttribute("dianhua",user.getPhone());
        model.addAttribute("youxiang",user.getMail());
        model.addAttribute("zhuzhi",user.getAddress());
        model.addAttribute("qqhao",user.getQq());
        model.addAttribute("idhao",user.getId());
        return "details";
    }

    public List<User> getUser(HttpSession session) {
        List<User> list = new ArrayList<User>();
        for (int i = 0; i < total; i++) {
            User temp = (User) session.getAttribute(Integer.toString(i));
            while (temp == null && i < total) {
                i++;
                temp = (User) session.getAttribute(Integer.toString(i));
            }
            if (temp != null) {
                System.out.println(temp.getId() + " " + temp.getName() + " " + temp.getPhone() + " " + temp.getMail() + " " + temp.getAddress() + " " + temp.getQq());
                list.add(temp);
            }
        }
        return list;
    }
}
