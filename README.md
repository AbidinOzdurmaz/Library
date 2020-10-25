# Library

Bu projede Java, Spring Boot, Jpa, Thymeleaf ve Mysql teknolojilerini kullandım.
Eğer sizde projeyi derlemek isterseniz veritabanı olarak ben mysql kullandım, başka bir veritabanı kullanıyorsanız
öncelikle projeye kullandığınız veritabanının driver'ını eklemeniz gerekir. (pom.xml e dependency olarak ekleyebilirsiniz)
Ve application.yml dosyasından driver name değerini kendi veri tabanınıza göre düzenlemelisiniz.

Mysql kullansanız da başka bir veritabanı yönetim sistemi kullansanızda application.yml dosyasında 
url bağlantısındaki veritabanı adı kısmına oluşturduğuz veritabanını vermelisiniz.
Bir de username ve password değerlerini kendi veritabanı bilgilerine göre değiştirmelisiniz.

Oluşturduğunuz veritabanına herhangi bir tablo eklemenize gerek yok bunu jpa bizim için yapacaktır.
Fakat bir kere çalıştıktan sonra application.yml dosyasında ki ddl-auto kısmını create den update yaparsanız
tablolarınız her seferinde yeniden oluşmaz.

Getter setter metotlarını lombok yardımıyla oluşturdum. Intellij Idea 'a da lombok'u etkinleştirmesi zor değil fakat
Eclipse ve Spring Tool Suite de lombok'u etkinleştirmek biraz uğraştırabilir. 
Eğer çözemezseniz tüm sınıfların getter setter ve boş contructer larını eklemeniz gerekir.

Projenin çalıştırıldıktan sonraki hali ana dizindeki kütüphane.mp4 dosyasından görebilirsiniz.
