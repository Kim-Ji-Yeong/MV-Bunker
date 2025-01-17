package com.nurim.mvbunker.movies;

import com.nurim.mvbunker.common.model.PagingDTO;
import com.nurim.mvbunker.common.security.model.CustomUserPrincipals;
import com.nurim.mvbunker.movies.model.*;
import com.nurim.mvbunker.review.ReviewService;
import com.nurim.mvbunker.review.model.ReviewDomain;
import com.nurim.mvbunker.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/movies")
public class MoviesController {

    @Autowired
    private MoviesService service;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    @Qualifier("reversGenreMap")
    Map<String, Integer> reversGenreMap;

    @Autowired
    @Qualifier("OriginalGenres")
    List<GenreEntity> OriginalGenres;

    @GetMapping("/boxoffice")
    public void boxoffice(Model model){
        List<MovieDomain> result = service.getPopularMovies();
        System.out.println(result.get(0));
        model.addAttribute("boxOfficeList", result);
    }

    @GetMapping("/genre")
    public void genre(Model model, MovieFavEntity param){
        Map<String, List<MovieDomain>> genreList = new HashMap<>();
        for(int i = 0; i < OriginalGenres.size(); i++) {
            List<MovieDomain> list = service.getGenreMovies(OriginalGenres.get(i).getGenreId());
            for(int j = list.size() - 1 ; j > 9 ; j -- ) {
                list.remove(j);
            }
            genreList.put(OriginalGenres.get(i).getGenreName(), list);
        }

        List<HoverVO> hover = service.selHover2(param);
        model.addAttribute("genreList", genreList);
        model.addAttribute("reverseGenreMap", reversGenreMap);
        model.addAttribute("hover", hover);
    }

    @GetMapping("/genreDetail")
    public void genreDetail(Model model, int genreId){
        List<MovieDomain> list = service.getGenreMovies(genreId);
//        List<ReviewDomain> review_list = reviewService.getReviews(movieId);
//        System.out.println("review_list : "+review_list);
        model.addAttribute("movieInfo", list);
//        model.addAttribute("review_list", review_list);
    }
    @ResponseBody
    @GetMapping("/genreDetailScrolling")
    public List<MovieDomain> genreDetailAjax(int genreId, int page) {
        return service.getGenreMovies(genreId, page);
    }

    @GetMapping("/recommendation")
    public void recommendation(Model model){
        List<MovieDomain> fav_list = service.getRcmList_fav();
        List<MovieDomain> revw_list = service.getRcmList_revw();
//        List<HoverVO> selHover1 = service.selHover1(param);
        System.out.println("리스트! : "+fav_list);
        Map<String, List<MovieDomain>> recmdMovieList = new HashMap<>();
        recmdMovieList.put("좋아요 많은 순", fav_list);
        recmdMovieList.put("리뷰 많은 순", revw_list);
        model.addAttribute("recommendation" ,recmdMovieList);

        model.addAttribute("fav_list",fav_list);
        model.addAttribute("revw_list",revw_list);
//        model.addAttribute("hover", selHover1);
    }

    @GetMapping("/search")
    public void search(Model model, String searchText) {
        try {
            model.addAttribute("movieList", service.getMovieSearch(searchText));
        }catch (Exception e) {
            model.addAttribute("movieList", new ArrayList<>());
            model.addAttribute("errorMsg", "찾는 결과가 없습니다.");
        }
    }

}
