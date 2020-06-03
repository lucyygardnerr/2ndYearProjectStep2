import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class LinkedBinaryTree<E extends Comparable> extends AbstractBinaryTree<E> {

    private Node<E> root;
    private int size;

    protected static class Node<E> implements Position<E> {

        private E element;
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;

        public Node(E element, Node<E> left, Node<E> right) {
            this.element = element;
//            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        public Node<E> getParent() {
            return parent;
        }

        public void setParent(Node<E> parent) {
            this.parent = parent;
        }

        public Node<E> getLeft() {
            return left;
        }

        public void setLeft(Node<E> left) {
            this.left = left;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setRight(Node<E> right) {
            this.right = right;
        }

        public void setElement(E element) {
            this.element = element;
        }

        @Override
        public E getElement() {
            return element;
        }

    }

    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node)) {
            throw new IllegalArgumentException("Not valid position type");
        }
        Node<E> node = (Node<E>) p;
        if (node.getParent() == node) {
            throw new IllegalArgumentException("p is no longer in the tree");
        }
        return node;
    }

    protected Node<E> createNode(E element, Node<E> parent, Node<E> left, Node<E> right) {
        return new Node<>(element, left, right);
    }

    public Node<E> addRecursive(Node<E> current, E value) {
        if (current == null) {
            current = new Node<>(value, null, null);
            return current;
        }

        if(ifNodeExists(current, value)){
            System.out.println("Node already exists.");
            return current;
        }

        if (value.compareTo(current.getElement()) < 0) {
            current.left = addRecursive(current.left, value);
        } else if (value.compareTo(current.getElement()) > 0) {
            current.right = addRecursive(current.right, value);
        } else {
            return current;
        }
        return current;
    }

    public boolean ifNodeExists(Node<E> node, E key)
    {
        if (node == null)
            return false;

        if (node.getElement() == key)
            return true;

        // then recur on left subtree /
        boolean res1 = ifNodeExists(node.left, key);
        if(res1) return true; // node found, no need to look further

        // node is not found in left, so recur on right subtree /
        boolean res2 = ifNodeExists(node.right, key);

        return res2;
    }

    public LinkedBinaryTree() {
        root = null;
        size = 0;
    }

    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        return validate(p).getRight();
    }

    public Position<E> addRight(Position<E> p, E element) throws IllegalArgumentException {
        Node<E> parent = validate(p);
        if (parent.getRight() != null) {
            System.out.println(parent.getRight().element);
            throw new IllegalArgumentException("p already has a right child");
        }
        Node<E> child = createNode(element, parent, null, null);
        parent.setRight(child);
        size++;
        return child;
    }

    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        return validate(p).getLeft();
    }

    public Position<E> addLeft(Position<E> p, E element) throws IllegalArgumentException {
        Node<E> parent = validate(p);
        if (parent.getLeft() != null) {
            throw new IllegalArgumentException("p already has a left child");
        }
        Node<E> child = createNode(element, parent, null, null);
        parent.setLeft(child);
        size++;
        return child;

    }

    public Position<E> addRoot(Node root) throws IllegalStateException {
        if (!isEmpty()) {
            throw new IllegalStateException("Tree is not empty");
        }
        this.root = root;
        size = 1;
        return root;
    }

    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E temp = node.getElement();
        node.setElement(e);
        return temp;
    }

    @Override
    public Node<E> root() {
        return root;
    }

    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        return validate(p).getParent();
    }

    @Override
    public int size(){
        size = 0;
        calculateSize(root);
        return size;
    }

    private int calculateSize(Node<E> node){
        if (node == null) {
            return 0;
        }
        size++;
        if(node.left != null){
            calculateSize(node.left);
        }
        if(node.right != null){
            calculateSize(node.right);
        }
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Iterable<Position<E>> positions() {
        return null;
    }

    public List<Position<E>> children(Position<E> p) {
        List<Position<E>> temp = new ArrayList<>(2);
        if (left(p) != null) {
            temp.add(left(p));
        }
        if (right(p) != null) {
            temp.add(right(p));
        }
        return temp;
    }

    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        if(numChildren(p) == 2) {
            throw new IllegalArgumentException("p has two children");
        }
        Node<E> child = (node.getLeft() != null ? node.getLeft() : node.getRight());
        if (child != null) {
            child.setParent(node.getParent());
        }

        if (node == root) {
            root = child;
        } else {
            Node<E> parent = node.getParent();
            if(node == parent.getLeft()) {
                parent.setLeft(child);
            } else {
                parent.setRight(child);
            }
        }
        size--;
        E temp = node.getElement();
        node.setElement(null);
        node.setLeft(null);
        node.setRight(null);
        node.setParent(null);
        return temp;
    }

    public void attach(Position<E> p, LinkedBinaryTree<E> tree1, LinkedBinaryTree<E> tree2) throws IllegalArgumentException {
        Node<E> node = validate(p);
        if(isInternal(p)) {
            throw new IllegalArgumentException("node must be a leaf");
        }
        size += tree1.size() + tree2.size();
        if (!tree1.isEmpty()) {
            tree1.root.setParent(node);
            node.setLeft(tree1.root);
            tree1.root = null;
            tree1.size = 0;
        }

        if (!tree2.isEmpty()) {
            tree2.root.setParent(node);
            node.setRight(tree1.root);
            tree2.root = null;
            tree2.size = 0;
        }
    }

    //TODO: WEEK 2 - 2.8 EX 2

    public void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.printf("%s ", node.element);
        preOrder(node.left);
        preOrder(node.right);
    }

    //TODO: WEEK 2 - 2.8 EX 3

    public void inOrder(Node node) {
        if (node == null) {
            return;
        }
        size++;
        inOrder(node.left);
        System.out.printf("%s ", node.element);
        inOrder(node.right);
    }

    //TODO: WEEK 2 - 2.8 EX 4

    public void postOrder(Node root) {
        if(root !=  null) {
            postOrder(root.left);
            postOrder(root.right);
            System.out.printf("%s ", root.element);
        }
    }

    //TODO: WEEK 3 - 3.14 EX 1

    public void eulerTour() {

        Stack<Position<E>> stack = new Stack<>();
        stack.add(root());

        List<Position<E>> nodes = new ArrayList<>();

        while (!stack.isEmpty()) {
            Position<E> p = stack.pop();
            System.out.println(p.getElement());
            if (!nodes.contains(p)) {
                if (!children(p).isEmpty()) {
                    for (Position<E> child : children(p)) {
                        stack.add(p);
                        stack.add(child);
                    }
                }
                nodes.add(p);
            }
        }
    }


}
