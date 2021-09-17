package lt.insoft.webgallery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class WebGalleryBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebGalleryBackApplication.class, args);
    }

}
