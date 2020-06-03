import java.util.*;

public abstract class AbstractTree<E> implements Tree<E> {

    @Override
    public boolean isInternal(Position<E> p) throws IllegalArgumentException {
        return numChildren(p) > 0;
    }

    @Override
    public boolean isExternal(Position<E> p) throws IllegalArgumentException {
        return numChildren(p) == 0;
    }

    @Override
    public boolean isRoot(Position<E> p) throws IllegalArgumentException {
        return root() == p;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    public int depth(Position<E> p) throws IllegalArgumentException{
        if(isRoot(p)){
            return 0;
        }
        else{
            return 1 + depth(parent(p));
        }
    }

    private class ElementIterator implements Iterator<E>{

        Iterator<Position<E>> positionIterator = positions().iterator();

        @Override
        public boolean hasNext() {
            return positionIterator.hasNext();
        }

        @Override
        public E next() {
            return positionIterator.next().getElement();
        }

        @Override
        public void remove(){
            positionIterator.remove();
        }
    }

    public Iterator<E> iterator(){
        return new ElementIterator();
    }

    private void preorderSubtree(Position<E> p, List<Position<E>> temp){
        temp.add(p);
        for(Position<E> child: children(p)){
            preorderSubtree(child, temp);
        }
    }

    public Iterable<Position<E>> preorder(){
        List<Position<E>> temp = new ArrayList<>();
        if(!isEmpty()){
            preorderSubtree(root(), temp);
        }
        return temp;
    }

    private void postorderSubtree(Position<E> p, List<Position<E>> temp){
        for(Position<E> child: children(p)){
            postorderSubtree(child, temp);
        }
        temp.add(p);
    }


    public Iterable<Position<E>> postorder(){
        List<Position<E>> temp = new ArrayList<>();
        if(!isEmpty()){
            postorderSubtree(root(), temp);
        }
        return temp;
    }

    //TODO: WEEK 3 - 3.6 EX 1 & 2

    public Position<E> bfs(E target){
        if(!isEmpty()){
            Queue<Position<E>> queue = new LinkedList<>();
            queue.add(root());
            while(!queue.isEmpty()){
                Position<E> p = queue.remove();
                System.out.println(p.getElement());
                if(p.getElement().equals(target)){
                    return p;
                }
                else{
                    for(Position<E> child: children(p)) {
                        queue.add(child);
                    }
                }
            }
        }
        return null;
    }

    //TODO: WEEK 3 - 3.6 EX 3

    public Position<E> dfs(E target){
        if(!isEmpty()){
            Stack<Position<E>> stack = new Stack<>();
            stack.add(root());
            while(!stack.isEmpty()){
                Position<E> p = stack.pop();
                System.out.println(p.getElement());
                if(p.getElement().equals(target)){
                    return p;
                }
                else{
                    for(Position<E> child: children(p)) {
                        stack.add(child);
                    }
                }
            }
        }
        return null;
    }

}
