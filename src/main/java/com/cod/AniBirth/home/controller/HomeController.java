package com.cod.AniBirth.home.controller;

import com.cod.AniBirth.adopt.entity.Adopt;
import com.cod.AniBirth.adopt.entity.AdoptReview;
import com.cod.AniBirth.adopt.service.AdoptReviewService;
import com.cod.AniBirth.adopt.service.AdoptService;
import com.cod.AniBirth.animal.entity.Animal;
import com.cod.AniBirth.animal.service.AnimalService;
import com.cod.AniBirth.article.entity.Article;
import com.cod.AniBirth.article.service.ArticleService;
import com.cod.AniBirth.product.entity.Product;
import com.cod.AniBirth.product.service.ProductService;
import com.cod.AniBirth.volunteer.entity.Volunteer;
import com.cod.AniBirth.volunteer.entity.VolunteerReview;
import com.cod.AniBirth.volunteer.service.VolunteerReviewService;
import com.cod.AniBirth.volunteer.service.VolunteerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final ArticleService articleService;
    private final VolunteerReviewService volunteerReviewService;
    private final AdoptReviewService adoptReviewService;
    private final VolunteerService volunteerService;
    private final AnimalService animalService;
    private final ProductService productService;

    @GetMapping("/")
    public String root(Model model) {
        List<Article> articleList = articleService.getRecentArticles();
        List<VolunteerReview> volunteerReviewList = volunteerReviewService.getRecentVolunteerReviews();
        List<AdoptReview> adoptReviewList = adoptReviewService.getRecentAdoptReviews();
        List<Volunteer> volunteerList = volunteerService.getRecentVolunteers();
        List<Animal> animalList = animalService.getRecentAdoptes();
        List<Product> productList = productService.getTopRatedProducts(4);

        model.addAttribute("articleList", articleList);
        model.addAttribute("volunteerReviewList", volunteerReviewList);
        model.addAttribute("adoptReviewList", adoptReviewList);
        model.addAttribute("volunteerList", volunteerList);
        model.addAttribute("animalList", animalList);
        model.addAttribute("productList", productList);

        return "home/main";
    }
}
