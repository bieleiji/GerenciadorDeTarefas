package User;

public class GerenciadorTarefas<T,C> {
    T task;
    C complete;

    GerenciadorTarefas(T task, C complete) {
        this.task = task;
        this.complete = complete;
    }

    public T getTask() {
        return task;
    }

    public C getComplete() {
        return complete;
    }

    public void setTask(T task) {
        this.task = task;
    }

    public void setComplete(C complete) {
        this.complete = complete;
    }
}
