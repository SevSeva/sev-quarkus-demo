import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.acme.app.model.Film;
import org.acme.app.repository.FilmRepo;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static io.smallrye.common.constraint.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class FilmRepoTest {
    @Inject
    FilmRepo filmRepo;

    @Test
    public void test() {
        Optional<Film> film = filmRepo.getFilm((short) 5);
        assertTrue(film.isPresent());
        assertEquals("AFRICAN EGG", film.get().getTitle());
    }
}
