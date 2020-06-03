import java.util.List;

public interface Position<E> {

    E getElement();
    Position<E> getParent();
}
