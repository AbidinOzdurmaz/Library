package net.abidinozdurmaz.library.controller;


import net.abidinozdurmaz.library.model.Author;
import net.abidinozdurmaz.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService service;
    @Autowired
    public AuthorController(AuthorService service) {
        this.service = service;
    }


    //tüm yazarların listesi dönen metot. Pop-up bir şekilde yeni yazar ekleyebilmek için parametre
    //    olarak author nesnesi gönderdim.
    @GetMapping()
    String showAuthor(Model model, Author author){

        model.addAttribute("authors",service.findAll());

        return "author";
    }
    //yazar eklediğimiz metot

    @PostMapping("/add")
    public String addAuthor(@ModelAttribute Author author, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "redirect:";
        }
        service.save(author);
        return "redirect:";
    }

    //güncelle butonuna bastığımızda bizi güncelleme sayfasına yönlendiren metot
    //önceki sayfan id değerini de alıyoruz.

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id,Model model) {

        Author author = service.findById(id);
        model.addAttribute("author", author);

        return "update-author";
    }

    //güncelleme işlemini yapan post metodu

    @PostMapping("update/{id}")
    public String updateAuthor(@PathVariable("id") long id, @ModelAttribute Author author, BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            author.setAuthorId(id);
            return "redirect:/author/edit/"+id;
        }

        author.setAuthorId(id);
        service.save(author);
        model.addAttribute("authors", service.findAll());
        return "author";
    }

    //silme metodumuz eğer yazar kullanılıyorsa yazarı silmez,
    //fakat bu durumu html de hata mesajı olarak yayınlamadım

    @GetMapping("delete/{id}")
    public String deleteAuthor(@PathVariable("id") long id, Model model) {

        Author author = service.findById(id);
        model.addAttribute("author", author);

        try{

            service.delete(author);

        }catch (RuntimeException ex){
            model.addAttribute("error","hata");

            return "redirect:/";
        }
        model.addAttribute("authors", service.findAll());
        return "author";
    }

}
