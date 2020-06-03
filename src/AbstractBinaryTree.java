import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTreeADT<E> {

    public Position<E> sibling(Position<E> p){
        Position<E> parent = parent(p);
        if (parent == null){
            return null;
        }
        if (p == left(parent)){
            return right(parent);
        }
        if (p == right(parent)){
            return left(parent);
        }
        return left(parent);
    }

    public int numChildren(Position<E> p){
        int count = 0;
        if(left(p) != null){
            count++;
        }
        if(right(p) != null){
            count++;
        }
        return count;
    }

    public Iterable<Position<E>> children(Position<E> p) {
        List<Position<E>> temp = new ArrayList<>(2);
        if (left(p) != null) {
            temp.add(left(p));
        }
        if (right(p) != null) {
            temp.add(right(p));
        }
        return temp;
    }

    public void preorder(Position<E> p)
    {
         if (p.getElement() == null)
            return;

        /* first print data of node */
        System.out.print(p.getElement() + " ");

        /* then recur on left subtree */
        preorder(left(p));

        /* now recur on right subtree */
        preorder(right(p));
    }

    public void postorder(Position<E> p)
    {
        if (p.getElement() == null)
            return;

        // first recur on left subtree
        postorder(left(p));

        // then recur on right subtree
        postorder(right(p));

        // now deal with the node
        System.out.print(p.getElement()+ " ");
    }

    public Iterable<Position<E>> inorder(){
        List<Position<E>> temp = new ArrayList<>();
        if(!isEmpty()){
            inorderSubtree(root(), temp);
        }
        return temp;
    }

    protected void inorderSubtree(Position<E> p, List<Position<E>> temp){
        if(left(p) != null){
            inorderSubtree(left(p), temp);
        }
        temp.add(p);
        if(right(p) != null){
            inorderSubtree(right(p), temp);
        }
    }
}
