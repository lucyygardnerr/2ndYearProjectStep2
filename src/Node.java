import java.util.ArrayList;
import java.util.List;

public class Node<E> implements Position<E> {

    private E element;
    private Node<E> parent;
    private List<Position<E>> children =  new ArrayList<>();
    private Node<E> left;
    private Node<E> right;
    int y;
    int x;


    public Node(E e, Node<E> p, Node<E> c, Node<E> l, Node<E> r){
        element = e;
        parent = p;
        children.add(c);
        left = l;
        right = r;
    }

    //Accessor Methods
    @Override
    public E getElement() {
        if(element == null) {
            throw new IllegalStateException("There are nodes.");
        }
        return element;
    }

    public Node<E> getParent() {
        return parent;
    }

    public List<Position<E>> getChildren() {
        return children;
    }

    public Node<E> getLeft() {
        return left;
    }

    public Node<E> getRight() {
        return right;
    }

    //Mutator Methods
    public void setElement(E newElem) {
        element = newElem;
    }

    public void setParent(Node<E> newPar){
        parent.setChildren(parent);
        parent= newPar;
    }

    public void setChildren(Node<E> newChil){
        children.add(newChil);
        }

    public void setLeft(Node<E> left) {
        this.left = left;
    }

    public void setRight(Node<E> right) {
        this.right = right;
    }

}
