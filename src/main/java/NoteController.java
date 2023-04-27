import entities.Note;
import entities.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @GetMapping("/list")
    public ModelAndView getAllNotes() {
        ModelAndView result = new ModelAndView("notes.html");
        result.addObject("notes.html", noteService.listAll());
        return result;
    }

    @GetMapping("/add")
    public ModelAndView addNotes() {
        ModelAndView result = new ModelAndView("add");
        result.addObject("note", null);
        return result;
    }

    @PostMapping("/add")
    public RedirectView addNotes(@RequestParam String title, @RequestParam String content) {
        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);
        noteService.add(note);
        return new RedirectView("/note/list");
    }

    @GetMapping("/edit")
    public ModelAndView editNoteById(@RequestParam long id) {
        ModelAndView result = new ModelAndView("edit");
        result.addObject("note", noteService.getById(id));
        return result;
    }

    @PostMapping("/edit")
    public RedirectView edit(@RequestParam long id, @RequestParam String title, @RequestParam String content) {
        Note note = new Note();
        note.setId(id);
        note.setTitle(title);
        note.setContent(content);
        noteService.update(note);
        return new RedirectView("/note/list");
    }

    @PostMapping("/delete")
    public RedirectView deleteNoteById(@RequestParam long id) {
        noteService.deleteById(id);
        return new RedirectView("/note/list");
    }

}