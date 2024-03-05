package app.tms_lesson_19

object SingletonList {
    private val notes = mutableListOf<NoteType>()

    fun addItem(item : NoteType) {
        notes.add(item)
    }

    fun getNotes() : List<NoteType> {
        return notes
    }

    fun importantItem(item : Note) {
        item.important = !item.important
    }
}