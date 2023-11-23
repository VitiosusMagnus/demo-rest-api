package com.vitiosusmagnus.demorestapi.business.concretes;

import com.vitiosusmagnus.demorestapi.business.abstracts.FilmService;
import com.vitiosusmagnus.demorestapi.dataaccess.abstracts.FilmRepository;
import com.vitiosusmagnus.demorestapi.entities.concretes.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmManager implements FilmService {

    @Autowired
    private FilmRepository repo;

    @Autowired
    private ReviewManager reviewManager;

    @Override
    public List<Film> getAll() {
        return repo.findAll();
    }

    @Override
    public Film getById(Long id) {
        Optional<Film> temp = repo.findById(id);
        return temp.orElse(null);
    }
    @Override
    public Film create(Film film) {
        return repo.save(film);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    @Override   //I'm not using Film object as reference because  i don't want mess reviews and rating
    public Film updateById(Long id, String name, String description, String url, String actors) {

        Optional<Film> temp = repo.findById(id);
        if (temp.isPresent()){
            Film film = temp.get();
            film.setName(name);
            film.setDescription(description);
            film.setUrl(url);
            film.setActors(actors);
            return repo.save(film);
        }else {
            return null;
        }
    }

    private void updateRatingById(long id){
        Optional<Film> temp = repo.findById(id);
        if (temp.isPresent()){
            Film film = temp.get();
            double rating = reviewManager.findAverageRatingByFilmId(id);
            film.setRating(rating);
            repo.save(film);
        }
    }

}
