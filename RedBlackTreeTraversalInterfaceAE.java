// --== CS400 Spring 2023 File Header Information ==--
// Name: Matthew Wang
// Email: mewang@wisc.edu
// Team: AN Blue
// TA: Gary Dahl
// Lecturer: Gary Dahl
// Notes to Grader: uwu :3

public interface RedBlackTreeTraversalInterfaceAE <T extends Comparable <T>> extends SortedCollectionInterface<T>
{

    //public RBTree();
    boolean remove(T data) throws NullPointerException, IllegalArgumentException;
    RedBlackTreeTraversalAE.Node<T> getRoot();

}
