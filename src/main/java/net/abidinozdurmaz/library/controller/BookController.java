package net.abidinozdurmaz.library.controller;

import net.abidinozdurmaz.library.model.Author;
import net.abidinozdurmaz.library.model.Book;
import net.abidinozdurmaz.library.model.Publisher;
import net.abidinozdurmaz.library.service.AuthorService;
import net.abidinozdurmaz.library.service.BookService;
import net.abidinozdurmaz.library.service.PublisherService;
import net.abidinozdurmaz.library.util.Search;
import net.abidinozdurmaz.library.util.addBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/")
public class BookController {

    private final BookService service;
    private final AuthorService authorService;
    private final PublisherService publisherService;
    private List<String> searchTypes= Arrays.asList("Kitap Adı","Yazar Adı","Isbn No");



    @Autowired
    public BookController(BookService service,AuthorService authorService,PublisherService publisherService) {
        this.service = service;
        this.authorService=authorService;
        this.publisherService=publisherService;
    }

    //anasayfamızda tüm kitapları döndüren metot.Kitaplar eğer search işlemine tabi tutulmazsa else bloğumuz çalışacak

    @GetMapping()
    String showBooks(Model model, @ModelAttribute Search search){
        model.addAttribute("searchTypes",this.searchTypes);

        //arama formumuzdan gelen veri boş değilse aşağıdaki metot çalışacak
        if (search.getSearch()!=null){
            //eğer null değil fakat boş bir şekilde butona basılırsa bütün kitaplarımızı döndüren parametreyi yolluyoruz
            if(search.getSearch().equals("")){
                model.addAttribute("bookDetails",service.findAllBookDetail());
            }

            //search inputunun içeriği doluysa arama türüne göre parametremize o listeyi gönderiyoruz.
            else{
                if(search.getType().equals("Kitap Adı")){
                    model.addAttribute("bookDetails",service.findByNameLike(search.getSearch()));
                }
                else if(search.getType().equals("Isbn No")){
                    model.addAttribute("bookDetails",service.findByIsbnNo(search.getSearch()));
                }
                else if(search.getType().equals("Yazar Adı")){
                    model.addAttribute("bookDetails",service.findByAuthorNameLike(search.getSearch()));
                }
            }
        }
        else {
            model.addAttribute("bookDetails",service.findAllBookDetail());
        }

        return "index";
    }

    //kitap detay sayfasına yönlendiren get metodu, önceki sayfadan listede tıkladığımız nesnenin id'si ile gönderiliyor
    @GetMapping("detail/{id}")
    public String showBooksDetail(@PathVariable("id") long id, Model model) {
        model.addAttribute("bookDetailById",service.findAllBookDetailById(id));
        return "booksDetail";
    }

    //kitap silme işlemini yapan metot
    @GetMapping("delete/{id}")
    public String deleteBook(@PathVariable long id,Model model){

        service.deleteById(id);

        return "redirect:/";
    }

    //kitap ekleme sayfası olan add-book a yönlendiren get metodu
    //içerisinde yeni yazar veya yayınevi ekleyebilme ihtimali nedeniyle parametre olarak onlarıda ekledim
    //ayrıca yazar ve yayınevini birden fazla aynı adda yayınevi ve yazar olmasın diye listeden seçtirdim
    @GetMapping("addbook")
    public String showAddBook(Book book, Model model, Author author, Publisher publisher) {
        model.addAttribute("authors",authorService.findAllSortName());
        model.addAttribute("publishers",publisherService.findAllSortName());
        return "add-book";
    }

    //kitap ekleme işlemini yapan post metodu
    //Book sınıfında yazar ve yayınevi bir nesne olduğu için formdan alamıyordum. Bunu çözmek için bir adet addBook
    //sınıfı oluşturdum. O sınıf üzerinde yazar ve yayınevinin id sini alarak yeni kitaba atadım.

    @PostMapping("add")
    public String addBook(@ModelAttribute addBook book, BindingResult result, Model model){
        if (result.hasErrors()) {
            return "redirect:/addbook";
        }
        Book tempBook=new Book(book.getName(),book.getSubName(),book.getSeriesName(),book.getIsbnNo(),book.getDescription());
        tempBook.setAuthor(authorService.findById(book.getAuthor()));
        tempBook.setPublisher(publisherService.findById(book.getPublisher()));
        service.save(tempBook);
        return "redirect:/";
    }

    //kitap güncelleme sayfasına yönlendirme yapan metot.
    //yeni yazar ve yayınevi oluşturabilir diye parametre olarak gönderdim

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id,Model model,Author author, Publisher publisher) {

        model.addAttribute("authors",authorService.findAllSortName());
        model.addAttribute("publishers",publisherService.findAllSortName());
        Book book = service.findById(id);
        model.addAttribute("book", book);

        return "update-book";
    }

    //add metodundaki mantığın aynısını güncelleme için de uyguladım.
    //tek farkı id 'yi önceki sayfadan alarak atama yaptım. Aksi halde yeni yazar oluştururdu.
    @PostMapping("update/{id}")
    public String updateBook(@PathVariable("id") long id, @ModelAttribute addBook book, BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            book.setBookId(id);
            return "redirect:edit/"+id;
        }
        book.setBookId(id);
        Book updateBook=new Book(book.getName(),book.getSubName(),book.getSeriesName(),book.getIsbnNo(),book.getDescription());
        updateBook.setAuthor(authorService.findById(book.getAuthor()));
        updateBook.setPublisher(publisherService.findById(book.getPublisher()));
        updateBook.setBookId(id);
        service.save(updateBook);
        return "redirect:/";
    }

    //burada yazar oluşturma metodu aslında AuthorController da vardı fakat o metotda yazarı kaydettikten
    //sonra author sayfasına yönlendiriyordu. Biz bu sayfada kalmamız gerektiği için bir daha yazdım.

    @PostMapping("/addAuthor")
    public String addAuthor(@ModelAttribute @Valid Author author, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "redirect:addbook";
        }
        authorService.save(author);
        return "redirect:addbook";
    }

    //yazar oluşturmadaki aynı mantığı kurdum.

    @PostMapping("/addPublisher")
    public String addPublisher(@ModelAttribute Publisher publisher, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "redirect:addbook";
        }
        publisherService.save(publisher);
        return "redirect:addbook";
    }


}
