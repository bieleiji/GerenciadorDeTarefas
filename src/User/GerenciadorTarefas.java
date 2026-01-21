package User;

import java.util.ArrayList;

public class GerenciadorTarefas<T,C> extends ArrayList<GerenciadorTarefas<Tarefa, Boolean>> {
    T task;
    C course;

    GerenciadorTarefas(T task, C course) {
        this.task = task;
        this.course = course;
    }

    public T getTask() {
        return task;
    }

    public C getCourse() {
        return course;
    }

    public void setTask(T task) {
        this.task = task;
    }

    public void setCourse(C course) {
        this.course = course;
    }
}
