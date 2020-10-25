package net.abidinozdurmaz.library.controller;

import net.abidinozdurmaz.library.model.Publisher;
import net.abidinozdurmaz.library.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/publisher")
public class PublisherController {


    private final PublisherService service;

    @Autowired
    public PublisherController(PublisherService service) {
        this.service = service;
    }

    //tüm yayınevlerinin listesini dönen metot.Pop-up bir şekilde yeni yayınevi ekleyebilmek için parametre
    //olarak publisher nesnesi gönderdim.
    @GetMapping()
    String showPublisher(Model model,Publisher publisher){
        model.addAttribute("publishers",service.findAll());
        return "publisher";
    }
    //yayınevi eklediğimiz metot.
    @PostMapping("add")
    public String addPublisher(@ModelAttribute Publisher publisher, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "redirect:";
        }
        service.save(publisher);
        return "redirect:";
    }

    //güncelle butonuna bastığımızda bizi güncelleme sayfasına yönlendiren metot
    //önceki sayfan id değerini de alıyoruz.
    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {

        Publisher publisher = service.findById(id);
        model.addAttribute("publisher", publisher);

        return "update-publisher";
    }

    //güncelleme işlemini yapan post metodu
    @PostMapping("update/{id}")
    public String updatePublisher(@PathVariable("id") long id, @ModelAttribute Publisher publisher, BindingResult result,
                                  Model model) {
        if (result.hasErrors()) {
            publisher.setPublisherId(id);
            return "redirect:/publisher/edit/"+id;
        }

        publisher.setPublisherId(id);
        service.save(publisher);

        model.addAttribute("publishers", service.findAll());
        return "publisher";
    }

    //silme metodumuz eğer yayınevi kullanılıyorsa yayınevini silmez,
    // fakat bu durumu html de hata mesajı olarak yayınlamadım.
    @GetMapping("delete/{id}")
    public String deletePublisher(@PathVariable("id") long id, Model model) {

        Publisher publisher = service.findById(id);
        model.addAttribute("publisher", publisher);
        try{
            service.delete(publisher);
        }catch (RuntimeException ex){
            System.out.println("Yazar bir kitap tarafından kullanılıyor");
            return "redirect:/";
        }
        model.addAttribute("publishers", service.findAll());
        return "publisher";
    }

}
