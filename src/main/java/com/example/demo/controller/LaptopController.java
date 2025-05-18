package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Laptop;
import com.example.demo.service.LaptopService;

@Controller
//@RequestMapping("/admin") giúp bạn không phải lặp đi lặp lại /admin ở mỗi method.
//chuyển hướng màn hình
public class LaptopController {
  @Autowired
  LaptopService lService;

    @GetMapping("/admin/dashboard")//  @GetMapping("/dashboard")
	  public String showDashboard(Model model){
        return "admin/index1";
      }
   
    @PostMapping("/laptop/save")
    public String saveLaptop(@ModelAttribute("laptop") Laptop laptop,RedirectAttributes redirectAttributes) {
        lService.saveLaptop(laptop);
        redirectAttributes.addFlashAttribute("successMessage", "Thêm laptop thành công!");
        return "redirect:/admin/dsSP";
    }

    @PostMapping("/laptop/update")
    public String updateLaptop(@ModelAttribute("laptop") Laptop laptop,RedirectAttributes redirectAttributes) {
      lService.saveLaptop(laptop); // hoặc update tùy cách bạn tách service
      redirectAttributes.addFlashAttribute("successMessage", "Update laptop thành công!");
      return "redirect:/admin/dsSP"; // hoặc route danh sách
}
@GetMapping("/laptop/delete/{id}")
public String deleteLaptop(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
    lService.deleteLaptop(id);
    redirectAttributes.addFlashAttribute("successMessage", "Xóa laptop thành công!");
    return "redirect:/admin/dsSP";  // quay lại trang danh sách sau khi xóa
}
 @GetMapping("/admin/dsSP")
public String listLaptops(
        @RequestParam(name = "keyword", required = false) String keyword,
        @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "size", defaultValue = "10") int size,
        Model model) {

    Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
    Page<Laptop> pageLaptop;

    if (keyword != null && !keyword.trim().isEmpty()) {
        pageLaptop = lService.getLaptopByNameOrModel1(keyword, pageable);
    } else {
        pageLaptop = lService.getLaptops1(pageable);
    }

    model.addAttribute("list", pageLaptop.getContent());
    model.addAttribute("currentPage", pageLaptop.getNumber());
    model.addAttribute("totalPages", pageLaptop.getTotalPages());
    model.addAttribute("keyword", keyword);
    model.addAttribute("laptop", new Laptop());

    return "admin/dsSP";
}
}
