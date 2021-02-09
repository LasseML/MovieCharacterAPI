package no.noroff.characterapi.models;

import java.util.List;

public class Franchise {
    private Long id;
    private String name;
    private String description;
    List<Movie> movies;
    List<Character> characters;
}
