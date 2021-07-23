package com.nurim.mvbunker.movies.model;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.model.Genre;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GenreLists {
    @Autowired TmdbApi tmdbApi;

    @Autowired List<Genre> genreList;

    public Map<Integer, String> getGenreMap() {
        Map<Integer,String> genreMap = new HashMap<>();
        for(int i = 0; i < genreList.size(); i++) {
            genreMap.put(genreList.get(i).getId(), genreList.get(i).getName());
        }
        return genreMap;
    }

    public List<MyMovieDb> getMovieListWithGenresName(MovieResultsPage resultsPage) {
        List<MyMovieDb> results = new ArrayList<>();
        List<MovieDb> originList = resultsPage.getResults();
        for(int i = 0 ; i < originList.size() ; i++) {
            results.add((MyMovieDb) originList.get(i));
            for(int j = 0 ; j < originList.get(i).getGenres().size(); j++) {
                results.get(i).getGenreNames()
                        .add(getGenreMap().get(originList.get(i).getGenres().get(j)));
            }
        }
        return results;
    }

}
