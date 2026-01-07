package User;

public class GerenciadorTarefas<T,C> {
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

    public void setComplete(C course) {
        this.course = course;
    }
}
