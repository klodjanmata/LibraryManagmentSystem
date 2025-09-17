package Actions;

import Entity.Genre;
import Repository.GenreRepository;
import Util.Helper;

import java.util.ArrayList;
import java.util.List;

public class GenreActions {

        private List<Genre> genreList;
        private GenreRepository genreRepository;

        public GenreActions() {
            genreList = new ArrayList<>();
        }

        public GenreActions(ArrayList<Genre> genreList) {
            this.genreList = genreList;
        }

        public void addAuthor() {
            System.out.println("Add the necessary genre information");
            Genre genre = new Genre();
            genre.setName(Helper.getStringFromUser("Name"));
            genre.setDescription(Helper.getStringFromUser("Nationality"));
            genreRepository.create(genre);
            System.out.println("Genre with id: " + genre.getId() + " added successfully");
            genreList.add(genre);
        }

        public List<Genre> getGenreList() {
            return genreList;
        }

    public void setGenreList(List<Genre> genreList) {
        this.genreList = genreList;
    }
}

