package org.acme.app.resources;


import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.acme.app.model.Film;
import org.acme.app.repository.FilmRepo;

import java.util.Optional;
import java.util.stream.Collectors;

@Path("/")
public class FilmResource {

    @Inject
    FilmRepo filmRepo;

    @GET
    @Path("/helloWorld")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloWorld() {
        return "Hello World";
    }

    @GET
    @Path("/film/{filmId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloWorld2(short filmId) {
        Optional<Film> film = filmRepo.getFilm(filmId);
        return film.isPresent() ? film.get().getTitle() : "No match...";
    }

    @GET
    @Path("/pagedFilms/{page}/{minLength}")
    @Produces(MediaType.TEXT_PLAIN)
    public String paged(long page, short minLength) {
        return filmRepo.paged(page, minLength)
                .map(f -> String.format("%s (%d min)", f.getTitle(), f.getLength()))
                .collect(Collectors.joining("\n"));
    }
}
