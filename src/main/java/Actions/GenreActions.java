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

        public GenreActions() {
            genreList = new ArrayList<>();
        }

        public GenreActions(ArrayList<Genre> genreList) {
            this.genreList = genreList;
        }

    public GenreActions(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
        this.genreList = new ArrayList<>();
    }

    public void addGenre() {
            System.out.println("Add the necessary genre information");
            Genre genre = new Genre();
            genre.setName(Helper.getStringFromUser("Name"));
            genre.setDescription(Helper.getStringFromUser("Description"));
            genreRepository.create(genre);
            System.out.println("Genre with id: " + genre.getId() + " added successfully");
            genreList.add(genre);
        }

}

