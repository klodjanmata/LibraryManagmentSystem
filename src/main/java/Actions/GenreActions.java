package Actions;

import Entity.Genre;
import Repository.GenreRepository;
import Util.Helper;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class GenreActions {

    @Setter
    @Getter
    private List<Genre> genreList;

    private GenreRepository genreRepository;

    // Default constructor
    public GenreActions() {
        this.genreList = new ArrayList<>();
        this.genreRepository = new GenreRepository();
    }

    // Constructor with repository
    public GenreActions(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
        this.genreList = new ArrayList<>();
    }

    // Constructor with predefined list
    public GenreActions(List<Genre> genreList, GenreRepository genreRepository) {
        this.genreList = genreList != null ? genreList : new ArrayList<>();
        this.genreRepository = genreRepository;
    }

    // Add a new genre
    public void addGenre() {
        if (genreRepository == null) {
            System.out.println("Genre repository not initialized!");
            return;
        }

        System.out.println("Add the necessary genre information:");
        Genre genre = new Genre();
        genre.setName(Helper.getStringFromUser("Name"));
        genre.setDescription(Helper.getStringFromUser("Description"));
        genreRepository.create(genre);
        System.out.println("Genre with id: " + genre.getId() + " added successfully");

        genreList.add(genre);
    }
}
