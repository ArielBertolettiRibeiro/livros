package ariel.livros.dto.books;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookUpdateRequest {
    private String title;
    private String author;
    private Integer publicationYear;
}
