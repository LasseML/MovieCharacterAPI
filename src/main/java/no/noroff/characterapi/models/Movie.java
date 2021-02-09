package no.noroff.characterapi.models;

import java.util.Date;
import java.util.List;

public class Movie {
    private long id;
    private String title;
    private String genre;
    private Date release;
    private String director;
    private String picture;
    private String trailer;
    List<Character> characters;
}
