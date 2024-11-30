import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class NotebookPresenter {
    private Notebook model;
    private NotebookView view;

    public NotebookPresenter(Notebook model,NotebookView view) {
        this.model = model;
        this.view = view;
    }

    public void addNote() {
        LocalDateTime dateTime = view.getDateTimeInput();
        String description = view.getDescriptionInput();
        model.add(new Note(dateTime, description));
        view.showMessage("Заметка добавлена.");
    }

    public void showNotesForDay() {
        LocalDateTime dateTime = view.getDateTimeInput();
        List<Note> notes = model.getNotesForDay(dateTime);
        view.showNotes(notes);
    }

    public void showNotesForWeek() {
        LocalDateTime startOfWeek = view.getDateTimeInput();
        List<Note> notes = model.getNotesForWeek(startOfWeek);
        view.showNotes(notes);
    }

    public void saveNotes() {
        String fileName = view.getFileNameInput();
        try {
            model.saveToFile(fileName);
            view.showMessage("Заметка сохранена в:  " + fileName);
        }
        catch (IOException e) {
            view.showMessage("Не удалось сохранить заметки: " + e.getMessage());
        }
    }

    public void loadNotes() {
        String fileName = view.getFileNameInput();
        try {
            model.loadFromFile(fileName);
            view.showMessage("Заметки, загруженные из " + fileName);
        }
        catch (IOException e) {
            view.showMessage("Не удалось загрузить заметки: " + e.getMessage());
        }
    }
}