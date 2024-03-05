package app.tms_lesson_19

data class Note(
    val header: String,
    val text: String,
    val date: String,
    var important: Boolean = false
) : NoteType
