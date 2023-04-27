package entities;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class NoteService {
    private final List<Note> noteList = new CopyOnWriteArrayList<>();
    private final Random random = new Random();

    public List<Note> listAll(){
        return noteList;
    }
    public Note add(Note note){
        note.setId(random.nextLong());
        noteList.add(note);
        return note;
    }
    public void deleteById(long id){
        try {
            noteList.remove(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void update(Note note){
        for (Note n : noteList) {
            if (n.getId() == note.getId()){
                n.setTitle(note.getTitle());
                n.setContent(note.getContent());
            }
        }
    }
    public Note getById(long id){
        try {
            return noteList.get((int) id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}